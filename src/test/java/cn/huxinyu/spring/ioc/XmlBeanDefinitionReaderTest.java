package cn.huxinyu.spring.ioc;

import cn.huxinyu.spring.ioc.factory.AutowireBeanFactory;
import cn.huxinyu.spring.ioc.factory.BeanFactory;
import cn.huxinyu.spring.ioc.io.ResourceLoader;
import org.junit.Test;

import java.util.Map;

public class XmlBeanDefinitionReaderTest {

    @Test
    public void getBean() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.readerXml("myioc.xml");

        BeanFactory factory = new AutowireBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            factory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }

        HelloWorld helloWorld = (HelloWorld) factory.getBean("helloWorld");
        helloWorld.say();
        System.out.println(helloWorld.getName());

        ReferenceBean referenceBean = (ReferenceBean) factory.getBean("referenceBean");
        referenceBean.getHelloWorld().say();
        System.out.println(referenceBean.getHelloWorld().getName());
    }
}
