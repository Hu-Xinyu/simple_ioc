package cn.huxinyu.spring.ioc;

import cn.huxinyu.spring.ioc.factory.AutowireBeanFactory;
import cn.huxinyu.spring.ioc.factory.BeanFactory;
import org.junit.Test;

public class BeanFactoryTest {
    private final String className = "cn.huxinyu.spring.ioc.HelloWorld";
    private final String propertyName = "name";
    private final String propertyValue = "huxinyu";
    private final String name = "helloworld";

    @Test
    public void getBean() throws Exception{
        BeanFactory factory = new AutowireBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClassName(className);
        beanDefinition.getPropertyValues().setPropertyValueList(new PropertyValue(propertyName, propertyValue));
        factory.registerBeanDefinition(name, beanDefinition);
        HelloWorld helloWorld = (HelloWorld) factory.getBean(name);
        helloWorld.say();
        System.out.println(helloWorld.getName());
    }
}
