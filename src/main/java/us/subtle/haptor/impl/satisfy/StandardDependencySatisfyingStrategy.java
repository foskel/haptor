package us.subtle.haptor.impl.satisfy;

import us.subtle.haptor.exceptions.UnsatisfiedDependencyException;
import us.subtle.haptor.process.DependencySatisfyingProcessor;
import us.subtle.haptor.registry.DependencyRegistry;
import us.subtle.haptor.satisfy.DependencySatisfyingResult;
import us.subtle.haptor.satisfy.DependencySatisfyingStrategy;
import us.subtle.haptor.validate.DependencyValidatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Fred
 * @since 5/28/2017
 */
public final class StandardDependencySatisfyingStrategy implements DependencySatisfyingStrategy {
    private final DependencyValidatorService validator;

    public StandardDependencySatisfyingStrategy(DependencyValidatorService validator) {
        this.validator = validator;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DependencySatisfyingResult satisfy(Object dependencyIdentifier,
                                              Object dependency,
                                              DependencyRegistry registry,
                                              Set<DependencySatisfyingProcessor> processors) {
        if (dependency != null) {
            if (registry.has(dependencyIdentifier)) {
                registry.registerDirectly(dependencyIdentifier, dependency);
            }

            boolean validationResult = this.validator.validate(dependency);
            DependencySatisfyingResult result = new SimpleDependencySatisfyingResult(dependency.getClass(),
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

        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DependencySatisfyingResult> satisfy(DependencyRegistry registry,
                                                    Set<DependencySatisfyingProcessor> processors,
                                                    Map<Object, Object> dependencies) {
        if (!dependencies.isEmpty()) {
            dependencies.forEach((identifier, dependency) -> {
                if (dependency != null) {
                    if (registry.has(identifier)) {
                        registry.registerDirectly(identifier, dependency);
                    }
                }
            });
        }

        List<DependencySatisfyingResult> results = new ArrayList<>(registry.findAllDependencies().size());

        for (Map.Entry dependencyEntry : (Set<Map.Entry>) registry.findAllDependencies().entrySet()) {
            Object dependencyIdentifier = dependencyEntry.getKey();
            Object dependency = dependencyEntry.getValue();

            boolean validationResult = this.validator.validate(dependency);
            DependencySatisfyingResult result = new SimpleDependencySatisfyingResult(dependencyIdentifier, dependency, validationResult);

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
}