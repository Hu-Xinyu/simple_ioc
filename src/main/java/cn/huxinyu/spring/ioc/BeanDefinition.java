package cn.huxinyu.spring.ioc;

/**
 * Bean的基本数据结构
 *
 * @author huxinyu  2019/6/14
 */
public class BeanDefinition {
    private Object bean;
    private Class beanClass;
    private String className;
    private PropertyValues propertyValues = new PropertyValues();

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public void setClassName(String name) {
        this.className = name;
        try {
            this.beanClass = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
