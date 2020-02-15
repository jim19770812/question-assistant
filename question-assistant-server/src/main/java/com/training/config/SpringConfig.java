package com.training.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

public class SpringConfig {

@Configuration
public class LazyInitBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    for (String beanName : beanFactory.getBeanDefinitionNames()) {
      beanFactory.getBeanDefinition(beanName).setLazyInit(true);
    }
  }
}
}
