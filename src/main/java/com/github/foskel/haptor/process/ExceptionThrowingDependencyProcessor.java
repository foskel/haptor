package com.github.foskel.haptor.process;

import com.github.foskel.haptor.satisfy.UnsatisfiedDependencyException;
import com.github.foskel.haptor.satisfy.DependencySatisfyingResult;

public final class ExceptionThrowingDependencyProcessor implements DependencyProcessor {
    private final String exceptionMessage;

    public ExceptionThrowingDependencyProcessor(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public void postSatisfy(DependencySatisfyingResult result) throws UnsatisfiedDependencyException {
        if (!result.getValidationResult()) {
            throw new UnsatisfiedDependencyException(this.exceptionMessage);
        }
    }
}