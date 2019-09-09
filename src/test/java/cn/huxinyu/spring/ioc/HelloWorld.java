package cn.huxinyu.spring.ioc;

public class HelloWorld {
    private String name;

    public void say() {
        System.out.println("Hello World");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
