package cn.binux.redis;

import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

@Configuration
@EnableConfigurationProperties({RedisProperties.class})
@ConditionalOnClass({JedisPool.class})
@ConditionalOnProperty(prefix="redis", havingValue="true", value={"cluster"}, matchIfMissing=false)
public class RedisClusterAutoConfiguration
{
    private static final Logger logger = LoggerFactory.getLogger(RedisClusterAutoConfiguration.class);

    @Autowired
    private RedisProperties properties;

    @Bean
    public JedisCluster jedisCluster()
    {
        String clusterNodes = this.properties.getClusterNodes();

        if (StringUtils.isEmpty(clusterNodes)) {
            logger.error("clusterNodes不能为空!");
            throw new RuntimeException();
        }

        String[] nodes = clusterNodes.split(",");

        HashSet hostAndPorts = new HashSet();

        String[] arrayOfString1 = nodes; int i = arrayOfString1.length; for (int j = 0; j < i; ++j) { String node = arrayOfString1[j];
        String[] split = node.split(":");
        HostAndPort hostAndPort = new HostAndPort(split[0], Integer.parseInt(split[1]));
        hostAndPorts.add(hostAndPort);
    }

        JedisCluster jedisCluster = new JedisCluster(hostAndPorts);

        return jedisCluster;
    }
}