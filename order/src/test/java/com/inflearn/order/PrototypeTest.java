package com.inflearn.order;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.*;

public class PrototypeTest {
    @Test
    public void test(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(config.class);
        System.out.println(ac.getBean(Single.class).getProto());
        System.out.println(ac.getBean(Single.class).getProto());
    }
    @Configuration
    static class config{
        @Bean
        public Single single(){
            return new Single(proto());
        }
        @Bean
        @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
        public Proto proto(){
            return new Proto();
        }
    }
}
