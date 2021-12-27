package com.inflearn.order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Proto {
    @PostConstruct
    public void init() {
        System.out.println("프로토타입 빈 초기화");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("프로토타입 빈 소멸");
    }
}
