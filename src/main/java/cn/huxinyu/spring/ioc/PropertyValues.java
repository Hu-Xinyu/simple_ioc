package cn.huxinyu.spring.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性集合
 *
 * @author huxinyu  2019/6/14
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void setPropertyValueList(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }
}
