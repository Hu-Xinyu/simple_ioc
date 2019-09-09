package cn.huxinyu.spring.ioc.factory;

import cn.huxinyu.spring.ioc.BeanDefinition;

/**
 * 实例化bean对象，通过反射获取
 * 定义ioc容器的一些行为，如获取bean实例，注册bean
 *
 * @author huxinyu  2019/6/13
 */
public interface BeanFactory {

    /**
     * 获取bean对象
     *
     * @param name bean名称
     * @return bean
     */
    Object getBean(String name) throws IllegalAccessException, NoSuchFieldException, InstantiationException;

    /**
     * 注册bean
     *
     * @param name bean名称
     * @param beanDefinition  bean
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws IllegalAccessException, NoSuchFieldException, InstantiationException;
}
