package us.subtle.haptor.impl.scan;

import us.subtle.haptor.scan.DependencyScanResult;
import us.subtle.haptor.scan.DependencyScanningStrategy;

import java.util.*;

public enum AnnotationDependencyScanningStrategy implements DependencyScanningStrategy {
    INSTANCE;

    @Override
    public List<DependencyScanResult<Object, Object>> scan(Object source) {
        Class<?> sourceType = source.getClass();

        if (!sourceType.isAnnotationPresent(Require.class)) {
            return Collections.emptyList();
        }

        Require requireAnnotation = sourceType.getAnnotation(Require.class);

        List<Class<?>> annotationDependencyIdentifiers = Arrays.asList(requireAnnotation.value());
        List<DependencyScanResult<Object, Object>> unsatisfiedDependencies = new ArrayList<>(annotationDependencyIdentifiers.size());

        for (Class<?> unsatisfiedDependency : annotationDependencyIdentifiers) {
            unsatisfiedDependencies.add(new SimpleDependencyScanResult<>(unsatisfiedDependency, null));
        }

        return unsatisfiedDependencies;
    }
}