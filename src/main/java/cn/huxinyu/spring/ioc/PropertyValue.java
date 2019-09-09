package cn.huxinyu.spring.ioc;

/**
 * 属性定义
 *
 * @author huxinyu  2019/6/14
 */
public class PropertyValue {
    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性对象
     */
    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
