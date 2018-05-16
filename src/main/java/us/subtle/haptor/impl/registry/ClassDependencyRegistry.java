package us.subtle.haptor.impl.registry;

import us.subtle.haptor.registry.DependencyRegistry;
import us.subtle.haptor.scan.DependencyScanningStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Fred on 5/28/2017.
 */
public final class ClassDependencyRegistry<D> implements DependencyRegistry<Object, Class<? extends D>, D> {
    private final Map<Object, Class<? extends D>> dependencyHolders = new HashMap<>();
    private final Map<Class<? extends D>, D> dependencies = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public boolean register(Object source, DependencyScanningStrategy scanningStrategy) {
        Map<Object, Object> foundDependencies = scanningStrategy.scan(source);

        if (foundDependencies.isEmpty()) {
            return false;
        }

        Set<Class<? extends D>> foundDependencyIdentifiers = (Set<Class<? extends D>>) (Set<?>) foundDependencies.keySet();
        Set<Class<? extends D>> dependencyIdentifiers = this.dependencies.keySet();

        if (dependencyIdentifiers.containsAll(foundDependencyIdentifiers)) {
            return false;
        }

        this.registerAllDependencies(source, (Map<Class<? extends D>, D>) (Map<?, ?>) foundDependencies);

        return true;
    }

    @SuppressWarnings("unchecked")
    private void registerAllDependencies(Object source, Map<Class<? extends D>, D> dependencies) {
        this.dependencies.putAll(dependencies);

        dependencies.forEach((identifier, dependency) -> this.dependencyHolders.put(source, identifier));
    }

    @Override
    public boolean registerDirectly(Class<? extends D> identifier, D value) {
        this.dependencies.put(identifier, value);

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean unregister(Object source) {
        Class<? extends D> identifier = this.dependencyHolders.get(source);

        return this.dependencies.remove(identifier) != null;
    }

    private void unregisterAllDependencies(Map<Class<? extends D>, D> dependencies) {
        this.dependencies.keySet().removeAll(dependencies.keySet());
    }

    @Override
    public boolean unregisterDirectly(Class<? extends D> identifier) {
        if (this.dependencies.containsKey(identifier)) {
            this.dependencies.remove(identifier);

            return true;
        }

        return false;
    }

    @Override
    public boolean has(Class<? extends D> identifier) {
        return this.dependencies.containsKey(identifier);
    }

    @Override
    public boolean hasDependencies() {
        return !this.dependencies.isEmpty();
    }

    @Override
    public Map<Class<? extends D>, D> findAllDependencies() {
        return Collections.unmodifiableMap(this.dependencies);
    }

    @Override
    public void clearDependencies() {
        this.dependencies.clear();
    }
}