package com.went.play.ground.design.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyDemo {

    public static void main(String[] args) {
        
        // JDK Proxy
        DanceableAndSingable actor = new Actress("韩雪");
        BusinessHandler handler = new BusinessHandler(actor);
        DanceableAndSingable proxy = (DanceableAndSingable) Proxy.newProxyInstance(
            actor.getClass().getClassLoader(), 
            actor.getClass().getInterfaces(), 
            handler);
        proxy.dance();
        proxy.sing();
        
        // CGLIB Proxy
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Actor.class);
        enhancer.setCallback(new BusinessInterceptor());
        Actor agent = (Actor)enhancer.create(new Class[] {String.class}, new Object[] {"韩雷"});
        agent.dance();
        agent.sing();
    }

}

// 会唱跳的
interface DanceableAndSingable {
    void dance();

    void sing();
}

class Actress implements DanceableAndSingable {
    private String name;

    public Actress(String name) {
        this.name = name;
    }

    @Override
    public void dance() {
        System.out.println( name + " is dancing slowly");
    }

    @Override
    public void sing() {
        System.out.println( name + " is singing softly");
    }
}

class Actor implements DanceableAndSingable {

    private String name;

    public Actor(String name) {
        this.name = name;
    }

    @Override
    public void dance() {
        System.out.println(name + " is dancing fast");
    }

    @Override
    public void sing() {
        System.out.println(name + " is singing loudly");
    }
}

// 商务代理
class BusinessHandler implements InvocationHandler {
    private Object target;
    public BusinessHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk:先商务谈判");
        Object result = method.invoke(target, args);
        System.out.println("jdk:结账");
        return result;
    }
}

class BusinessInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib:先商务谈判");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("cglib:结账");
        return result;
    }
}