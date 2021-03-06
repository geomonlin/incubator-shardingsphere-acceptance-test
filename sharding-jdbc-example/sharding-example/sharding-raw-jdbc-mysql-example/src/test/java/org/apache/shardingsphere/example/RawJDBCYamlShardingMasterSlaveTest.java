package org.apache.shardingsphere.example;

import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.jdbc.common.RawJdbcAssertUtils;
import org.apache.shardingsphere.example.core.jdbc.repository.mysql.AddressRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.repository.mysql.OrderItemRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.repository.mysql.OrderRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.service.OrderServiceImpl;
import org.apache.shardingsphere.example.factory.YamlDataSourceFactory;
import org.apache.shardingsphere.example.type.ShardingType;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class RawJDBCYamlShardingMasterSlaveTest {
    @Test
    public void assertCommonService() throws SQLException, IOException {
        DataSource dataSource = YamlDataSourceFactory.newInstance(ShardingType.SHARDING_MASTER_SLAVE);
        ExampleService exampleService = new OrderServiceImpl(new OrderRepositoryImpl(dataSource),new OrderItemRepositoryImpl(dataSource), new AddressRepositoryImpl(dataSource));
        ExampleExecuteTemplate.run(exampleService);
        RawJdbcAssertUtils.assertShardingMasterSlaves(exampleService);
    }
}
