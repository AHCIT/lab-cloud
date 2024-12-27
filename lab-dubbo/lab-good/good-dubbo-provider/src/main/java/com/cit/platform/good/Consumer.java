package com.cit.platform.good;

import com.cit.platform.good.api.DemoGoodService;
import com.cit.platform.good.dao.entity.Administrator;
import com.cit.platform.good.dao.mapper.AdministratorMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Consumer implements CommandLineRunner {
    @DubboReference
    private DemoGoodService demoGoodService;

    private final AdministratorMapper administratorMapper;

    @Autowired
    public Consumer(AdministratorMapper administratorMapper) {
        this.administratorMapper = administratorMapper;
    }
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void run(String... args) throws InterruptedException {

        String result = demoGoodService.sayHello("world");
        log.info("Receive result ======> " + result);
        List<Administrator> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Administrator administrator = new Administrator();
            administrator.setName("admin" + i);
            list.add(administrator);
        }
        administratorMapper.insert(list);
        Thread.sleep(30000);
        log.info("insert administrator finish: {}", list.size());
    }
}
