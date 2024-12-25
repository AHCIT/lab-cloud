package com.example.canal.study.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.example.canal.study.common.CanalDataParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 获取binlog数据并发送到es中
 *
 * @author haha
 */
@Slf4j
@Component
public class BinLogElasticSearch {
    @Autowired
    private CanalConnector canalSimpleConnector;
//    @Autowired
//    private ElasticUtils elasticUtils;
    //@Qualifier("canalHaConnector")使用名为canalHaConnector的bean
//    @Autowired
//    @Qualifier("canalHaConnector")
//    private CanalConnector canalHaConnector;

    public void binLogToElasticSearch() {
        openCanalConnector(canalSimpleConnector);
        // 轮询拉取数据
        int batchSize = 5 * 1024;
        try {
            while (true) {
                Message message = canalSimpleConnector.getWithoutAck(batchSize);
                long id = message.getId();
                int size = message.getEntries().size();
                if (id == -1 || size == 0) {
                    try {
                        // 等待4秒
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    log.info("当前监控到binLog消息数量{}", size);
                    parseMessage(message, id);
                }
            }
        } finally {
            closeCanalConnector(canalSimpleConnector);
        }
    }

    private void parseMessage(Message message, long id) {
        //1. 解析message对象
        List<CanalEntry.Entry> entries = message.getEntries();
        List<CanalDataParser.TwoTuple<CanalEntry.EventType, Map<String, Object>>> rows = CanalDataParser.printEntry(entries);

        for (CanalDataParser.TwoTuple<CanalEntry.EventType, Map<String, Object>> tuple : rows) {
            if (tuple.eventType == CanalEntry.EventType.INSERT) {
                String student = createStudent(tuple);
                // 2。将解析出的对象同步到elasticSearch中
                log.info("add student {}", student);
                // 3.消息确认已处理
                canalSimpleConnector.ack(id);
            }
            if (tuple.eventType == CanalEntry.EventType.UPDATE) {
                String student = createStudent(tuple);
                log.info("update student {}", student);
                // 3.消息确认已处理
                canalSimpleConnector.ack(id);
            }
            if (tuple.eventType == CanalEntry.EventType.DELETE) {
                log.info("delete student {}", tuple.columnMap.get("id").toString());
                canalSimpleConnector.ack(id);
            }
        }
    }

    /**
     * 封装数据至Student对象中
     */
    private String createStudent(CanalDataParser.TwoTuple<CanalEntry.EventType, Map<String, Object>> tuple) {
        return JSON.toJSONString(tuple);
    }

    /**
     * 打开canal连接
     */
    private void openCanalConnector(CanalConnector canalConnector) {
        //连接CanalServer
        canalConnector.connect();
        // 订阅destination
        canalConnector.subscribe();
    }

    /**
     * 关闭canal连接
     */
    private void closeCanalConnector(CanalConnector canalConnector) {
        //关闭连接CanalServer
        canalConnector.disconnect();
        // 注销订阅destination
        canalConnector.unsubscribe();
    }
}