package com.github.foskel.haptor.satisfy;

import com.github.foskel.haptor.process.DependencyProcessor;
import com.github.foskel.haptor.registry.DependencyRegistry;
import com.github.foskel.haptor.validate.DependencyValidatorService;

import java.util.*;

/**
 * @author Fred
 * @since 5/28/2017
 */
public final class StandardDependencySatisfyingStrategy<I, D> implements DependencySatisfyingStrategy<I, D> {
    private final DependencyValidatorService validator;

    public StandardDependencySatisfyingStrategy(DependencyValidatorService validator) {
        this.validator = validator;
    }

    @Override
    public List<DependencySatisfyingResult<I, D>> satisfy(DependencyRegistry<I, D> registry,
                                                          Set<DependencyProcessor> processors,
                                                          Map<I, D> dependencies) {
        if (!dependencies.isEmpty()) {
            dependencies.forEach((identifier, dependency) -> {
                if (dependency != null) {
                    if (registry.has(identifier)) {
                        registry.registerDirectly(identifier, dependency);
                    }
                }
            });
        }

        List<DependencySatisfyingResult<I, D>> results = new ArrayList<>(registry.findAllDependencies().size());

        for (Map.Entry dependencyEntry : registry.findAllDependencies().entrySet()) {
            @SuppressWarnings("unchecked") I dependencyIdentifier = (I) dependencyEntry.getKey();
            @SuppressWarnings("unchecked") D dependency = (D) dependencyEntry.getValue();

            boolean validationResult = this.validator.validate(dependency);
            DependencySatisfyingResult<I, D> result = new DependencySatisfyingResult<>(dependencyIdentifier, dependency, validationResult);

            processors.forEach(processor -> {
                try {
                    processor.postSatisfy(result);
                } catch (UnsatisfiedDependencyException e) {
                    e.printStackTrace();
                }
            });

            results.add(result);
        }

        return results;
    }

    private DependencySatisfyingResult<I, D> satisfy(I dependencyIdentifier,
                                                     D dependency,
                                                     DependencyRegistry<I, D> registry,
                                                     Set<DependencyProcessor> processors) {
        Objects.requireNonNull(dependency, "dependency");

        if (registry.has(dependencyIdentifier)) {
            registry.registerDirectly(dependencyIdentifier, dependency);
        }

        boolean validationResult = this.validator.validate(dependency);
        DependencySatisfyingResult<I, D> result = new DependencySatisfyingResult<>(dependencyIdentifier,
                dependency,
                validationResult);

        processors.forEach(processor -> {
            try {
                processor.postSatisfy(result);
            } catch (UnsatisfiedDependencyException e) {
                e.printStackTrace();
            }
        });

        return result;
    }
}