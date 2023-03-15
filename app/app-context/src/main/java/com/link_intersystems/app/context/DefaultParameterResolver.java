package com.link_intersystems.app.context;

import java.lang.reflect.Executable;

public class DefaultParameterResolver implements ParameterResolver {
    private BeanFactory beanFactory;

    public DefaultParameterResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public boolean canResolve(Executable executable, int index) {
        return true;
    }

    @Override
    public Object resolveParameter(Executable executable, int index) {
        Class<?> parameterType = executable.getParameterTypes()[index];
        return beanFactory.getBean(parameterType);
    }
}
