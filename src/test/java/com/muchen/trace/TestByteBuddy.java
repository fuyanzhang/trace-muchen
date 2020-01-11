package com.muchen.trace;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @ClassName TestByteBuddy
 * @Author fuyanzhang
 * @Date 2020/1/11 0011 上午 11:15
 * @Description TODO
 **/
public class TestByteBuddy {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(MethodDelegation.to(ToStringInterceptor.class))
                .make()
                .load(TestByteBuddy.class.getClassLoader(),
                        ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        System.out.println(dynamicType.newInstance().toString());
    }
}
