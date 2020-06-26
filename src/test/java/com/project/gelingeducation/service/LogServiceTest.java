package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author: LL
 * @Description: 项目错误日志的serviceTest
 * 备注：
 */
@Slf4j
@ActiveProfiles("development")
@WebAppConfiguration
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LogServiceTest {

    @Autowired
    private ILogService logService;

    /**
     * 导入测试数据
     */
    @Test
    public void insertTestData() {
        for (int i = 0; i < 10; i++) {
            Log log = new Log();
            log.setExceptionDetail("测试").setAddress("测试").setParams("测试")
                    .setMethod("测试").setDescription("测试").setRequestIp("测试")
                    .setBrowser("测试").setUsername("测试").setIsSolve(0);
            logService.save(log);
        }
    }

    /**
     * 搜索所有日志
     */
    @Test
    public void queryAll() {
        logService.queryAll(null, null);
    }

    /**
     * 删除一个日志
     */
    @Test
    public void delOneLog() {
        logService.delOneLog(1L);
    }

}
