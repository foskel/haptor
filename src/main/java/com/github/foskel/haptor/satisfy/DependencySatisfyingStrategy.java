package com.github.foskel.haptor.satisfy;

import com.github.foskel.haptor.process.DependencyProcessor;
import com.github.foskel.haptor.registry.DependencyRegistry;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Fred on 5/28/2017.
 */
public interface DependencySatisfyingStrategy<I, D> {
    List<DependencySatisfyingResult<I, D>> satisfy(DependencyRegistry<I, D> registry,
                                                   Set<DependencyProcessor> processors,
                                                   Map<I, D> dependencies);
}