package com.darren.center.shardingjdbc.demo.hint;

import com.darren.center.shardingjdbc.demo.hint.service.ExampleService;
import com.darren.center.shardingjdbc.demo.hint.service.OrderServiceImpl;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlMasterSlaveDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 主类
 */
public class HintMain {

    private static final HintType TYPE = HintType.DATABASE_TABLES;
//    private static final HintType TYPE = HintType.DATABASE_ONLY;
//    private static final HintType TYPE = HintType.MASTER_ONLY;


    public static void main(String[] args) throws IOException, SQLException {
        //根据type选择不同的配置文件
        DataSource dataSource = getDataSource();
        //将所有的mapper创建出来
        ExampleService exampleService = getExampleService(dataSource);
        //初始化环境 -> 建表、清空数据、公共表建表并插入数据
        exampleService.initEnvironment();
        //order表和order_detail表插入数据
        exampleService.processSuccess();
        //测试hint规则
        processWithHintValue(dataSource);
        //exampleService.cleanEnvironment();
    }

    private static DataSource getDataSource() throws IOException, SQLException {
        switch (TYPE) {
            case DATABASE_TABLES:
                return YamlShardingDataSourceFactory.createDataSource(getFile("E:\\IDEAWorkSpace\\sharding-jdbc-demo\\src\\main\\resources\\META-INF\\hint-databases-tables.yaml"));
            case DATABASE_ONLY:
                return YamlShardingDataSourceFactory.createDataSource(getFile("E:\\IDEAWorkSpace\\sharding-jdbc-demo\\src\\main\\resources\\META-INF\\hint-databases-only.yaml"));
            case MASTER_ONLY:
                return YamlMasterSlaveDataSourceFactory.createDataSource(getFile("E:\\IDEAWorkSpace\\sharding-jdbc-demo\\src\\main\\resources\\META-INF\\hint-master-only.yaml"));
            default:
                throw new UnsupportedOperationException("unsupported type");
        }
    }

    private static File getFile(final String configFile) {
        return new File(configFile);
    }

    private static ExampleService getExampleService(final DataSource dataSource) {
        return new OrderServiceImpl(dataSource);
    }

    private static void processWithHintValue(final DataSource dataSource) throws SQLException {
        try (HintManager hintManager = HintManager.getInstance();
             Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            //设置hint的value
            setHintValue(hintManager);
            statement.execute("select * from t_order");
            statement.execute("SELECT i.* FROM t_order o, t_order_item i WHERE o.order_id = i.order_id");
            statement.execute("select * from t_order_item");
            statement.execute("INSERT INTO t_order (user_id, address_id, status) VALUES (1, 1, 'init')");
        }
    }

    private static void setHintValue(final HintManager hintManager) {
        switch (TYPE) {
            case DATABASE_TABLES:
                //设置分库的shardingValue
                hintManager.addDatabaseShardingValue("t_order", 0L);
                //设置分表的shardingValue
                hintManager.addTableShardingValue("t_order", 1L);
                return;
            case DATABASE_ONLY:
                hintManager.setDatabaseShardingValue(1L);
                return;
            case MASTER_ONLY:
                hintManager.setMasterRouteOnly();
                return;
            default:
                throw new UnsupportedOperationException("unsupported type");
        }
    }
}