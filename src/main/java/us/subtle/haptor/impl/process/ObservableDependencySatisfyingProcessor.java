package us.subtle.haptor.impl.process;

import us.subtle.haptor.process.DependencySatisfyingProcessor;
import us.subtle.haptor.satisfy.DependencySatisfyingResult;

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