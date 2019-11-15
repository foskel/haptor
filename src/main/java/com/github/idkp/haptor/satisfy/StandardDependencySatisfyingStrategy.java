package com.github.idkp.haptor.satisfy;

import com.github.idkp.haptor.DependencyRef;
import com.github.idkp.haptor.process.DependencyProcessor;
import com.github.idkp.haptor.registry.DependencyRegistry;
import com.github.idkp.haptor.validate.DependencyValidatorService;

import java.util.*;
import java.util.function.Function;

/**
 * 5/28/2017
 */
public final class StandardDependencySatisfyingStrategy implements DependencySatisfyingStrategy {
    private final DependencyValidatorService validator;

    public StandardDependencySatisfyingStrategy(DependencyValidatorService validator) {
        this.validator = validator;
    }

    @Override
    public <I, D> void satisfy(DependencyRegistry<I, D> registry, Function<I, D> depSupplier, Collection<DependencyProcessor> processors) throws UnsatisfiedDependencyException {
        for (DependencyRef<I, D> ref : registry.getAllDependencies().values()) {
            if (ref.isSatisfied()) {
                continue;
            }

            I id = ref.getIdentifier();
            D depValue = depSupplier.apply(id);

            ref.setValue(depValue);

            DependencySatisfyingResult<I, D> result = new DependencySatisfyingResult<>(id, depValue, validator.validate(ref));

            for (DependencyProcessor processor : processors) {
                processor.postSatisfy(result);
            }
        }
    }
}