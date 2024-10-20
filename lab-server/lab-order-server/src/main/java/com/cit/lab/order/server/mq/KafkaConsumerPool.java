package com.cit.lab.order.server.mq;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * @Author: Richard
 * @Description: kafka顺序消费工具类线程池 平滑扩容缩容待设计，stopped的钩子可以支持
 * @CreateDate: 2023/9/24 14:15
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/24 14:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Data
public class KafkaConsumerPool<E> {

    /**
     * kafka线程名前缀
     */
    private static final String KAFKA_CONSUMER_WORK_THREAD_PREFIX = "kafka-consumer-";
    /**
     * 线程并发级别
     */
    private Integer concurrentSize;
    /**
     * 任务处理队列
     */
    private List<ConcurrentLinkedQueue<E>> queues;

    /**
     * 是否全量停止任务,留个钩子，以便后续动态扩容
     */
    private volatile boolean stopped;

    /**
     * 待提交的记录数
     */
    private AtomicLong pendingOffsets;
    /**
     * 工作线程线程
     */
    private List<Thread> workThreads;

    /**
     * 顺序消费任务池初始化
     *
     * @param config 业务配置
     */
    public KafkaConsumerPool(KafkaSortConsumerConfig<E> config) {
        this.concurrentSize = config.getConcurrentSize();
        //初始化任务队列
        this.initQueue();
        this.workThreads = new ArrayList<>();
        this.stopped = false;
        this.pendingOffsets = new AtomicLong(0L);
        //初始化线程
        this.initWorkThread(config.getBizName(), config.getBizService());
    }

    /**
     * 初始化队列
     */
    private void initQueue() {
        this.queues = new ArrayList<>();
        for (int i = 0; i < this.concurrentSize; i++) {
            this.queues.add(new ConcurrentLinkedQueue<>());
        }
    }

    /**
     * 初始化工作线程
     */
    private void initWorkThread(String bizName, Consumer<E> bizService) {
        //创建规定的线程
        for (int i = 0; i < this.concurrentSize; i++) {
            String threadName = KAFKA_CONSUMER_WORK_THREAD_PREFIX + bizName + i;
            int num = i;
            Thread workThread = new Thread(() -> {
                //如果队列不为空 或者 线程标识为false则进入循环
                while (!queues.get(num).isEmpty() || !stopped) {
                    E task = null;
                    try {
                        task = pollTask(threadName, bizName);
                        if (Objects.nonNull(task)) {

                            //模拟业务处理耗时
                            bizService.accept(task);
                            log.info("finished tasK {}", task);
                            //执行完成的任务加1
                            pendingOffsets.incrementAndGet();
                        }
                    } catch (Exception e) {
                        log.error("fail to execute task {}", task, e);
                    }
                }
                log.info("线程：{}退出", threadName);
            }, threadName);
            //加入线程管理
            workThreads.add(workThread);
            //开启线程
            workThread.start();
        }
    }

    /**
     * 根据id取模，将需要保证顺序的任务添加至同一队列
     *
     * @param id   能够取模的键
     * @param task 需要提交处理的任务
     */
    public void submitTask(Long id, E task) {
        ConcurrentLinkedQueue<E> taskQueue = queues.get((int) (id % this.concurrentSize));
        taskQueue.offer(task);
    }

    /**
     * 根据线程名获取对应的待执行的任务
     *
     * @param threadName 线程名称
     * @return 队列内的任务
     */
    private E pollTask(String threadName, String bizName) {
        int threadNum = Integer.parseInt(threadName.replace(KAFKA_CONSUMER_WORK_THREAD_PREFIX + bizName, ""));
        ConcurrentLinkedQueue<E> taskQueue = queues.get(threadNum);
        return taskQueue.poll();
    }
}
