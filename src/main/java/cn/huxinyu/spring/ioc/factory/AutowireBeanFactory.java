package cn.huxinyu.spring.ioc.factory;


import cn.huxinyu.spring.ioc.BeanDefinition;
import cn.huxinyu.spring.ioc.BeanReference;
import cn.huxinyu.spring.ioc.PropertyValue;

import java.lang.reflect.Field;

/**
 * 实现自动注入和递归注入(spring 的标准实现类 DefaultListableBeanFactory)
 *
 * @author huxinyu  2019/6/17
 */
public class AutowireBeanFactory extends AbstractBeanFactory {
    @Override
    Object doCreate(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Object bean = beanDefinition.getBeanClass().newInstance();
        addPropertyValue(bean, beanDefinition);
        return bean;
    }

    /**
     * 给定一个bean定义和一个bean实例，为给定的bean中的属性注入实例。
     */
    private void addPropertyValue(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                // 依赖注入
                value = getBean(beanReference.getName());
            }
            // 反射注入bean的属性
            declaredField.set(bean, value);
        }
    }
}
