package com.github.idkp.haptor.scan;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public final class AnnotationDependencyScanner implements UnsatisfiedDependencyScanner {

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