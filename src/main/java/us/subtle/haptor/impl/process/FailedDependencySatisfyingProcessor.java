package us.subtle.haptor.impl.process;

import us.subtle.haptor.exceptions.UnsatisfiedDependencyException;
import us.subtle.haptor.process.DependencySatisfyingProcessor;
import us.subtle.haptor.satisfy.DependencySatisfyingResult;

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