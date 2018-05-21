package com.github.foskel.haptor.impl.process;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class ObservableDependencySatisfyingProcessorBuilder {
    private final List<Consumer<Object>> succeededValidationListeners = new ArrayList<>();
    private final List<Consumer<Object>> failedValidationListeners = new ArrayList<>();

    public ObservableDependencySatisfyingProcessorBuilder withSuccessValidationListener(Consumer<Object> successValidationListener) {
        this.succeededValidationListeners.add(successValidationListener);

        return this;
    }

    public ObservableDependencySatisfyingProcessorBuilder withFailedValidationListener(Consumer<Object> failedValidationListener) {
        this.failedValidationListeners.add(failedValidationListener);

        return this;
    }

    public ObservableDependencySatisfyingProcessor build() {
        return new ObservableDependencySatisfyingProcessor(this.succeededValidationListeners, this.failedValidationListeners);
    }
}