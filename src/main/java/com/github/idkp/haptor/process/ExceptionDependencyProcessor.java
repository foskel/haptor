package com.github.idkp.haptor.process;

import com.github.idkp.haptor.satisfy.UnsatisfiedDependencyException;
import com.github.idkp.haptor.satisfy.DependencySatisfyingResult;

public final class ExceptionDependencyProcessor implements DependencyProcessor {

    @Override
    public void postSatisfy(DependencySatisfyingResult result) throws UnsatisfiedDependencyException {
        if (!result.getValidationResult()) {
            throw new UnsatisfiedDependencyException(result.getIdentifier());
        }
    }
}