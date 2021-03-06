package org.apache.shardingsphere.example;

import org.apache.shardingsphere.example.core.api.ExampleExecuteTemplate;
import org.apache.shardingsphere.example.core.api.service.ExampleService;
import org.apache.shardingsphere.example.core.jdbc.common.RawJdbcAssertUtils;
import org.apache.shardingsphere.example.core.jdbc.repository.mysql.AddressRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.repository.mysql.OrderItemRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.repository.mysql.OrderRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.repository.mysql.UserRepositoryImpl;
import org.apache.shardingsphere.example.core.jdbc.service.OrderServiceImpl;
import org.apache.shardingsphere.example.core.jdbc.service.UserServiceImpl;
import org.apache.shardingsphere.example.factory.DataSourceFactory;
import org.apache.shardingsphere.example.type.ShardingType;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

public class RawJDBCJavaShardingMasterSlaveEncrypt {
    @Test
    public void assertCommonService() throws SQLException {
        DataSource dataSource = DataSourceFactory.newInstance(ShardingType.SHARDING_MASTER_SLAVE_ENCRYPT);
        ExampleService exampleService = new UserServiceImpl(new UserRepositoryImpl(dataSource));
        ExampleExecuteTemplate.run(exampleService);
        RawJdbcAssertUtils.assertShardingMasterSlaveEncrypt(exampleService);
    }
}
