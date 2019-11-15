package com.github.idkp.haptor;

import com.github.idkp.haptor.registry.StandardDependencyRegistry;
import com.github.idkp.haptor.satisfy.StandardDependencySatisfyingStrategy;
import com.github.idkp.haptor.validate.DefaultValidator;

public final class Haptor {
    private Haptor() {
    }

    public static <I, D> DependencySystem<I, D> newDependencySystem() {
        return new StandardDependencySystem<>(new StandardDependencyRegistry<>(), new StandardDependencySatisfyingStrategy(new DefaultValidator()));
    }
}