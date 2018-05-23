package com.github.foskel.haptor.scan;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public enum ClassUnsatisfiedDependencyScanner implements UnsatisfiedDependencyScanner {
    INSTANCE;

    @Override
    public Collection<Class<?>> scan(Object source) {
        Class<?> sourceType = source.getClass();

        if (!sourceType.isAnnotationPresent(Require.class)) {
            return Collections.emptyList();
        }

        Require requireAnnotation = sourceType.getAnnotation(Require.class);

        return Arrays.asList(requireAnnotation.value());
    }
}