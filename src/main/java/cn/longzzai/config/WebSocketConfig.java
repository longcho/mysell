package cn.longzzai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;

/**
 * @author longcho
 * 2017-09-11-21:48
 */
@Component
public class WebSocketConfig {
    @Bean
    private ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
