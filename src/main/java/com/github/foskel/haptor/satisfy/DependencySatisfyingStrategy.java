package com.github.foskel.haptor.satisfy;

import com.github.foskel.haptor.process.DependencySatisfyingProcessor;
import com.github.foskel.haptor.registry.DependencyRegistry;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Fred on 5/28/2017.
 */
public interface DependencySatisfyingStrategy {
    DependencySatisfyingResult satisfy(Object dependencyIdentifier,
                                       Object dependency,
                                       DependencyRegistry registry,
                                       Set<DependencySatisfyingProcessor> processors);

    List<DependencySatisfyingResult> satisfy(DependencyRegistry registry,
                                             Set<DependencySatisfyingProcessor> processors,
                                             Map<Object, Object> dependencies);
}