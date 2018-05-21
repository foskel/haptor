package com.github.foskel.haptor.impl.process;

import com.github.foskel.haptor.process.DependencySatisfyingProcessor;
import com.github.foskel.haptor.satisfy.DependencySatisfyingResult;

import java.util.List;
import java.util.function.Consumer;

public final class ObservableDependencySatisfyingProcessor implements DependencySatisfyingProcessor {
    private final List<Consumer<Object>> succeededValidationListeners;
    private final List<Consumer<Object>> failedValidationListeners;

    ObservableDependencySatisfyingProcessor(List<Consumer<Object>> succeededValidationListeners,
                                            List<Consumer<Object>> failedValidationListeners) {
        this.succeededValidationListeners = succeededValidationListeners;
        this.failedValidationListeners = failedValidationListeners;
    }

    @Override
    public void postSatisfy(DependencySatisfyingResult result) {
        Object dependencyIdentifier = result.getDependencyIdentifier();

        if (result.getValidationResult()) {
            this.succeededValidationListeners.forEach(succeededValidationListener -> succeededValidationListener.accept(dependencyIdentifier));
        } else {
            this.failedValidationListeners.forEach(failedValidationListener -> failedValidationListener.accept(dependencyIdentifier));
        }
    }
}