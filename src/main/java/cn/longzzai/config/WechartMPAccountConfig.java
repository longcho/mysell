package cn.longzzai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信账号相关配置
 *
 * @author longcho
 * 2017-09-04-9:55
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat.mp")
public class WechartMPAccountConfig {
    private String appId;
    private String secret;
}
