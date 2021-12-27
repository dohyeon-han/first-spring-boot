package com.inflearn.order;

import com.inflearn.order.config.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter 를 입력 후 tab키를 누르면 자동으로 for문이 만들어집니다.
        for (String name : beanDefinitionNames) {
            //정의된 이름을 하나하나 꺼내면서 bean이라는 Object자료형을 가진 변수에 넣어줍니다.
            Object bean = ac.getBean(name);
            //출력
            System.out.println("name = " + name + "  object = " + bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        //마찬가지로 정의된 빈의 이름들을 가져옵니다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //BeanDefinition 자료형을 반환합니다.
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "  object = " + bean);
            }
        }
    }
    
    @Test
    @DisplayName("빈 조회")
    void bean(){
        Map<String, Object> beans = ac.getBeansOfType(Object.class);
        for (String s : beans.keySet()) {
            System.out.println(ac.getBean(s));
        }
    }
}
