package cn.huxinyu.spring.ioc;

import cn.huxinyu.spring.ioc.io.ResourceLoader;
import cn.huxinyu.spring.ioc.io.ResourceUrl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    public void readerXml(String location) throws IOException, ParserConfigurationException, SAXException {
        ResourceLoader resourceLoader = new ResourceLoader();
        ResourceUrl resourceUrl = resourceLoader.getResource(location);
        InputStream in = resourceUrl.getInputStream();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(in);
        registerBeanDefinitions(document);
        in.close();
    }

    private void registerBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) {
        NodeList n1 = root.getChildNodes();
        for (int i = 0; i < n1.getLength(); i++) {
            Node node = n1.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                processBeanDefinition(element);
            }
        }
    }

    private void processBeanDefinition(Element element) {
        String name = element.getAttribute("name");
        String className = element.getAttribute("class");
        BeanDefinition bean = new BeanDefinition();
        bean.setClassName(className);
        addPropertyValues(element, bean);
        getRegistry().put(name, bean);
    }

    private void addPropertyValues(Element element, BeanDefinition bean) {
        NodeList nodeList = element.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                String name = ele.getAttribute("name");
                String value = ele.getAttribute("value");
                if (value != null && value.length() > 0) {
                    bean.getPropertyValues().setPropertyValueList(new PropertyValue(name, value));
                }else {
                    String ref = ele.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    bean.getPropertyValues().setPropertyValueList(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
