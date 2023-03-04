package com.link_intersystems.app.context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.*;

public class BeanDefinitionRegitry {

    private BeanDefinitionLocator beanDefinitionLocator;
    private List<BeanDefinition> beanDefinitions;

    private Predicate<BeanDefinition> beanDefinitionFilter = bd -> true;

    public BeanDefinitionRegitry() {
        this(new DefaultBeanDefinitionLocator());
    }

    public BeanDefinitionRegitry(BeanDefinitionLocator beanDefinitionLocator) {
        this.beanDefinitionLocator = requireNonNull(beanDefinitionLocator);
    }

    public void setBeanDefinitionFilter(Predicate<BeanDefinition> beanDefinitionFilter) {
        this.beanDefinitionFilter = beanDefinitionFilter == null ? bd -> true : beanDefinitionFilter;
    }

    public BeanDefinition getBeanDefinition(BeanDeclaration beanDeclaration) throws AmbiguousBeanException {
        List<BeanDefinition> matchingBeanDefinitions = getBeanDefinitions(beanDeclaration);

        if (matchingBeanDefinitions.isEmpty()) {
            throw new RuntimeException("Bean " + beanDeclaration.getType().getName() + " is not available.");
        }

        if (matchingBeanDefinitions.size() == 1) {
            BeanDefinition beanDefinition = matchingBeanDefinitions.get(0);
            return beanDefinition;
        }


        throw new AmbiguousBeanException(ExceptionUtils.ambiguousBean(beanDeclaration, matchingBeanDefinitions), matchingBeanDefinitions);
    }

    public List<BeanDefinition> getBeanDefinitions(BeanDeclaration beanDeclaration) {
        List<BeanDefinition> beanDefinitions = getBeanDefinitions();

        List<BeanDefinition> matchingBeanDefinitions = new ArrayList<>();

        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (!beanDefinitionFilter.test(beanDefinition)) {
                continue;
            }

            BeanDeclaration actualBeanDefinition = beanDefinition.getBeanRef();

            if (!isInstance(beanDeclaration, actualBeanDefinition)) {
                continue;
            }

            if (beanDeclaration.getName() != null) {
                if (!isNamed(actualBeanDefinition, beanDeclaration.getName())) {
                    continue;
                }
            }


            matchingBeanDefinitions.add(beanDefinition);
        }
        return matchingBeanDefinitions;
    }

    private boolean isInstance(BeanDeclaration actBeanDeclaration, BeanDeclaration otherBeanDeclaration) {
        return actBeanDeclaration.getType().isAssignableFrom(otherBeanDeclaration.getType());
    }


    private boolean isNamed(BeanDeclaration actBeanDeclaration, String name) {
        String actualName = actBeanDeclaration.getName();
        if (actualName != null && actualName.equals(name)) {
            return true;
        }

        return actBeanDeclaration.getType().getSimpleName().equals(name);
    }

    private List<BeanDefinition> getBeanDefinitions() {
        if (beanDefinitions == null) {
            try {
                beanDefinitions = beanDefinitionLocator.getBeanDefinitions();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return beanDefinitions;
    }
}
