package com.github.foskel.haptor.satisfy;

/**
 * @author Fred
 * @since 7/17/2017
 */
public final class DependencySatisfyingResult<I, D> {
    private final I dependencyIdentifier;
    private final D dependency;
    private final boolean validationResult;

    public DependencySatisfyingResult(I dependencyIdentifier,
                                      D dependency,
                                      boolean validationResult) {
        this.dependencyIdentifier = dependencyIdentifier;
        this.dependency = dependency;
        this.validationResult = validationResult;
    }

    public I getDependencyIdentifier() {
        return this.dependencyIdentifier;
    }

    public D getDependency() {
        return this.dependency;
    }

    public boolean getValidationResult() {
        return this.validationResult;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + this.dependencyIdentifier + ","
                + this.dependency + ","
                + this.validationResult + "}";
    }
}