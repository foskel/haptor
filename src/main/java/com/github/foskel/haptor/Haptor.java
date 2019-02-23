package com.github.foskel.haptor;

import com.github.foskel.haptor.registry.StandardDependencyRegistry;
import com.github.foskel.haptor.satisfy.StandardDependencySatisfyingStrategy;
import com.github.foskel.haptor.validate.DefaultValidator;

public final class Haptor {
    private Haptor() {
    }

    public static <I, D> DependencySystem<I, D> newDependencySystem() {
        return new StandardDependencySystem<>(new StandardDependencyRegistry<>(), new StandardDependencySatisfyingStrategy(new DefaultValidator()));
    }
}