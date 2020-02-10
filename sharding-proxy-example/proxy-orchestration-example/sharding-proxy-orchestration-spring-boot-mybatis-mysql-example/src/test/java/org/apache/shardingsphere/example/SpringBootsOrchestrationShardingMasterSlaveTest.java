package org.apache.shardingsphere.example;

import org.apache.shardingsphere.example.core.api.senario.AnnotationCommonServiceScenario;
import org.apache.shardingsphere.example.core.mybatis.common.SpringResultAssertUtils;
import org.apache.shardingsphere.example.core.mybatis.service.SpringPojoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTestMain.class)
@ActiveProfiles("sharding-master-slave")
public class SpringBootsOrchestrationShardingMasterSlaveTest {
    
    @Autowired
    private SpringPojoService commonService;
    
    @Test
    public void assertCommonService() throws SQLException {
        AnnotationCommonServiceScenario.process1(commonService);
        SpringResultAssertUtils.assertShardingMasterSlaveResult(commonService);
    }
}
