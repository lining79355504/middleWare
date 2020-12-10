package com.mort.middle.ware.consumer.kafka.spring;

import com.mort.middle.ware.consumer.kafka.service.ConsumerServiceInIt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;

/**
 * @author mort
 * @Description
 * @date 2020/12/10
 **/
@Configuration
public class SpringBootRegiester implements BeanDefinitionRegistryPostProcessor {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition bean = new RootBeanDefinition(ConsumerServiceInIt.class);
        registry.registerBeanDefinition("consumerServiceInIt", bean);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
