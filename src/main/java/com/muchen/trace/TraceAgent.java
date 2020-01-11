package com.muchen.trace;

import com.muchen.trace.intercept.MethodCallInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * @ClassName TraceAgent
 * @Author fuyanzhang
 * @Date 2020/1/11 0011 上午 11:12
 * @Description TODO
 **/
public class TraceAgent {

    public static void premain(String arg, Instrumentation instrumentation) {

//        System.out.println("Enter preMain....");

        new AgentBuilder.Default().type(any()).transform(new AgentBuilder.Transformer() {
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
//                System.out.println("enter Transform");
                //加入处理逻辑
                MethodCallInterceptor interceptor = new MethodCallInterceptor();
                builder = builder.method(not(isStatic()).and(any())).intercept(MethodDelegation.to(interceptor));
                return builder;
            }
        }).with(new AgentBuilder.Listener() {
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {

            }

            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }
        }).installOn(instrumentation);
    }

}
