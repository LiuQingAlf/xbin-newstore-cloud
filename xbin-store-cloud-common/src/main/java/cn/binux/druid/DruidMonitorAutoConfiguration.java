package cn.binux.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({DruidDataSource.class})
@EnableConfigurationProperties({DruidMonitorProperties.class})
@ConditionalOnProperty(prefix="druid.monitor", havingValue="enabled", value={"enabled"}, matchIfMissing=false)
@AutoConfigureAfter({DataSource.class})
public class DruidMonitorAutoConfiguration
{
    private static final Logger logger = LoggerFactory.getLogger(DruidMonitorAutoConfiguration.class);

    @Autowired
    private DruidMonitorProperties properties;

    @Bean
    public ServletRegistrationBean druidStatViewServlet()
    {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), new String[] { this.properties.getDruidStatView() });

        servletRegistrationBean.addInitParameter("allow", this.properties.getAllow());
        logger.info("allow ---> " + this.properties.getAllow());

        servletRegistrationBean.addInitParameter("deny", this.properties.getDeny());
        logger.info("deny ---> " + this.properties.getDeny());

        servletRegistrationBean.addInitParameter("loginUsername", this.properties.getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", this.properties.getLoginPassword());
        logger.info("loginUsername ---> " + this.properties.getLoginUsername() + "loginPassword ---> " + this.properties.getLoginPassword());

        servletRegistrationBean.addInitParameter("resetEnable", this.properties.getResetEnable());
        logger.info("resetEnable ---> " + this.properties.getResetEnable());
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter(), new ServletRegistrationBean[0]);

        filterRegistrationBean.addUrlPatterns(new String[] { this.properties.getDruidWebStatFilter() });
        logger.info("urlPatterns ---> " + this.properties.getDruidWebStatFilter());

        filterRegistrationBean.addInitParameter("exclusions", this.properties.getExclusions());
        logger.info("exclusions ---> " + this.properties.getExclusions());
        return filterRegistrationBean;
    }
}