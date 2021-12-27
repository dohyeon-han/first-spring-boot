package com.inflearn.order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Single {
    // 컨테이너에 빈 요청
//    @Autowired
//    private ObjectProvider<Proto> prototypeBeanProvider;
//
//    public void logic() {
//        Proto prototypeBean = prototypeBeanProvider.getObject();
//        System.out.println(prototypeBean);
//    }

    private final Proto proto;
    public Single(Proto proto) {
        this.proto = proto;
    }

    public Proto getProto() {
        return proto;
    }

    @PostConstruct
    public void init() {
        System.out.println("싱글톤 빈 초기화");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("싱글톤 빈 소멸");
    }
}
