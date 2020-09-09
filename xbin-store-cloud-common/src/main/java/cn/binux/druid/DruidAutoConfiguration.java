package cn.binux.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ConditionalOnClass({DruidDataSource.class})
@EnableConfigurationProperties({DruidProperties.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
public class DruidAutoConfiguration
{
    private static final Logger logger = LoggerFactory.getLogger(DruidAutoConfiguration.class);

    @Autowired
    private DruidProperties properties;

    @Bean
    public DataSource dataSource()
    {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(this.properties.getUrl());
        dataSource.setUsername(this.properties.getUsername());
        dataSource.setPassword(this.properties.getPassword());
        if (this.properties.getInitialSize().intValue() > 0) {
            dataSource.setInitialSize(this.properties.getInitialSize().intValue());
            logger.info("setInitialSize --->" + this.properties.getInitialSize());
        }
        if (this.properties.getMinIdle().intValue() > 0) {
            dataSource.setMinIdle(this.properties.getMinIdle().intValue());
            logger.info("setInitialSize --->" + this.properties.getInitialSize());
        }
        if (this.properties.getMaxActive().intValue() > 0) {
            dataSource.setMaxActive(this.properties.getMaxActive().intValue());
            logger.info("setMaxActive --->" + this.properties.getMaxActive());
        }
        if (this.properties.getTestOnBorrow() != null) {
            dataSource.setTestOnBorrow(this.properties.getTestOnBorrow().booleanValue());
            logger.info("setTestOnBorrow --->" + this.properties.getTestOnBorrow());
        }
        if (this.properties.getMaxWait().intValue() > 0) {
            dataSource.setMaxWait(this.properties.getMaxWait().intValue());
            logger.info("setMaxWait --->" + this.properties.getMaxWait());
        }
        if (this.properties.getTimeBetweenEvictionRunsMillis().intValue() > 0) {
            dataSource.setTimeBetweenEvictionRunsMillis(this.properties.getTimeBetweenEvictionRunsMillis().intValue());
            logger.info("setTimeBetweenEvictionRunsMillis --->" + this.properties.getTimeBetweenEvictionRunsMillis());
        }
        if (this.properties.getMinEvictableIdleTimeMillis().intValue() > 0) {
            dataSource.setMinEvictableIdleTimeMillis(this.properties.getMinEvictableIdleTimeMillis().intValue());
            logger.info("setMinEvictableIdleTimeMillis --->" + this.properties.getMinEvictableIdleTimeMillis());
        }
        if (this.properties.getValidationQuery() != null) {
            dataSource.setValidationQuery(this.properties.getValidationQuery());
            logger.info("setValidationQuery --->" + this.properties.getValidationQuery());
        }
        if (this.properties.getTestWhileIdle() != null) {
            dataSource.setTestWhileIdle(this.properties.getTestWhileIdle().booleanValue());
            logger.info("setTestWhileIdle --->" + this.properties.getTestWhileIdle());
        }
        if (this.properties.getTestOnReturn() != null) {
            dataSource.setTestOnReturn(this.properties.getTestOnReturn().booleanValue());
            logger.info("setTestOnReturn --->" + this.properties.getTestOnReturn());
        }
        if (this.properties.getPoolPreparedStatements() != null) {
            dataSource.setPoolPreparedStatements(this.properties.getPoolPreparedStatements().booleanValue());
            logger.info("setPoolPreparedStatements --->" + this.properties.getPoolPreparedStatements());
        }
        if (this.properties.getMaxPoolPreparedStatementPerConnectionSize().intValue() > 0) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.properties.getMaxPoolPreparedStatementPerConnectionSize().intValue());
            logger.info("setMaxPoolPreparedStatementPerConnectionSize --->" + this.properties.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if (this.properties.getFilters() != null)
            try {
                dataSource.setFilters(this.properties.getFilters());
                logger.info("setFilters --->" + this.properties.getFilters());
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("setInitialSize error");
            }

        if (this.properties.getConnectionProperties() != null) {
            dataSource.setConnectionProperties(this.properties.getConnectionProperties());
            logger.info("setConnectionProperties --->" + this.properties.getConnectionProperties());
        }
        try
        {
            dataSource.init();
            logger.info("dataSource.init()");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}