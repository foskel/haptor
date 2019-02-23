package com.github.foskel.haptor.process;

import com.github.foskel.haptor.satisfy.UnsatisfiedDependencyException;
import com.github.foskel.haptor.satisfy.DependencySatisfyingResult;

public final class ExceptionDependencyProcessor implements DependencyProcessor {

    @Override
    public void postSatisfy(DependencySatisfyingResult result) throws UnsatisfiedDependencyException {
        if (!result.getValidationResult()) {
            throw new UnsatisfiedDependencyException(result.getIdentifier());
        }
    }
}