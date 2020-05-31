package com.training.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {
    /**
     * 修复Invalid character found in the request target. The valid characters are defined in RFC 7230 and RFC 3986
     * @return
     */
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((Connector connector) -> {
                connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
                connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
        });
        return factory;
    }
}
