package com.github.foskel.haptor.impl.process;

import com.github.foskel.haptor.process.DependencySatisfyingProcessor;
import com.github.foskel.haptor.exceptions.UnsatisfiedDependencyException;
import com.github.foskel.haptor.satisfy.DependencySatisfyingResult;

public final class FailedDependencySatisfyingProcessor implements DependencySatisfyingProcessor {
    private final String exceptionMessage;

    public FailedDependencySatisfyingProcessor(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public void postSatisfy(DependencySatisfyingResult result) throws UnsatisfiedDependencyException {
        if (!result.getValidationResult()) {
            throw new UnsatisfiedDependencyException(this.exceptionMessage);
        }
    }
}