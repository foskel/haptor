package us.subtle.haptor.registry;

import us.subtle.haptor.scan.DependencyScanningStrategy;

import java.util.Map;

/**
 * Created by Fred on 5/28/2017.
 */
public interface DependencyRegistry<S, I, D> {
    boolean register(S source, DependencyScanningStrategy scanningStrategy);

    boolean registerDirectly(I identifier, D dependency);

    boolean unregister(S source);

    boolean unregisterDirectly(I identifier);

    boolean has(I identifier);

    boolean hasDependencies();

    Map<I, D> findAllDependencies();

    void clearDependencies();
}