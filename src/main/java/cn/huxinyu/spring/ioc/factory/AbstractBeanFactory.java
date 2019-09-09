package cn.huxinyu.spring.ioc.factory;


import cn.huxinyu.spring.ioc.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> map = new HashMap<String, BeanDefinition>();

    @Override
    public Object getBean(String name) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        BeanDefinition beanDefinition = map.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreate(beanDefinition);
        }
        return bean;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        Object bean = doCreate(beanDefinition);
        beanDefinition.setBean(bean);
        map.put(name, beanDefinition);
    }

    abstract Object doCreate(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException, NoSuchFieldException;
}
