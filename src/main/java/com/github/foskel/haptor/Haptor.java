package com.github.foskel.haptor;

import com.github.foskel.haptor.registry.StandardDependencyRegistry;
import com.github.foskel.haptor.satisfy.StandardDependencySatisfyingStrategy;
import com.github.foskel.haptor.scan.UnsatisfiedDependencyScanner;
import com.github.foskel.haptor.validate.NullCheckingDependencyValidator;

public final class Haptor {
    private Haptor() {
    }

    public static <S, I, D> DependencySystem<S, I, D> newDependencySystem(UnsatisfiedDependencyScanner<I> unsatisfiedScanner) {
        return new StandardDependencySystem<>(new StandardDependencyRegistry<>(),
                new StandardDependencySatisfyingStrategy<>(NullCheckingDependencyValidator.INSTANCE),
                unsatisfiedScanner);
    }
}