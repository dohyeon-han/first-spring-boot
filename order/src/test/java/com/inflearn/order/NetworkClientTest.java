package com.inflearn.order;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class NetworkClientTest {
    @Test
    public void beanTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(config.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        System.out.println("hello!");
        ac.close();
    }

    @Configuration
    static class config{
        //@Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://test.com");
            return networkClient;
        }
    }

}