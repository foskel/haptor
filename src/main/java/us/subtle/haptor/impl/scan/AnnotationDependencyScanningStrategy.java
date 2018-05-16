package us.subtle.haptor.impl.scan;

import us.subtle.haptor.scan.DependencyScanningStrategy;

import java.util.*;

public enum AnnotationDependencyScanningStrategy implements DependencyScanningStrategy {
    INSTANCE;

    @Override
    public Map<Object, Object> scan(Object source) {
        Class<?> sourceType = source.getClass();

        if (!sourceType.isAnnotationPresent(Require.class)) {
            return Collections.emptyMap();
        }

        Require requireAnnotation = sourceType.getAnnotation(Require.class);

        List<Class<?>> annotationDependencyIdentifiers = Arrays.asList(requireAnnotation.value());
        Map<Object, Object> unsatisfiedDependencies = new HashMap<>(annotationDependencyIdentifiers.size());

        for (Class<?> unsatisfiedDependency : annotationDependencyIdentifiers) {
            unsatisfiedDependencies.put(unsatisfiedDependency, null);
        }

        return unsatisfiedDependencies;
    }
}