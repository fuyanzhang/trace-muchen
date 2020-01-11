package com.muchen.trace.intercept;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @ClassName MethodCallInterceptor
 * @Author fuyanzhang
 * @Date 2020/1/11 0011 下午 22:35
 * @Description TODO
 **/
public class MethodCallInterceptor {
    @RuntimeType
    public Object intercept(@This Object obj, @AllArguments Object[] allArguments, @Origin Method method,
                            @SuperCall Callable<?> zuper) throws Throwable {

        System.out.println("intercept " + method.getName() + "obj"+obj.getClass().getName());
        Object result = null;
        try {
            result = zuper.call();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return result;

    }
}
