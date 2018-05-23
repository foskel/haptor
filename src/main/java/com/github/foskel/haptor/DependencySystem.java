package com.github.foskel.haptor;

import com.github.foskel.haptor.process.DependencyProcessor;
import com.github.foskel.haptor.registry.DependencyRegistry;
import com.github.foskel.haptor.satisfy.DependencySatisfyingResult;

import java.util.List;
import java.util.Map;

public interface DependencySystem<I, D> {
    boolean register(Object source);

    boolean unregister(Object source);

    boolean registerProcessor(DependencyProcessor processor);

    boolean unregisterProcessor(DependencyProcessor processor);

    <T extends D> T find(I identifier);;

    List<DependencySatisfyingResult> satisfy(Map<I, D> dependencies);

    DependencyRegistry<I, D> getRegistry();
}