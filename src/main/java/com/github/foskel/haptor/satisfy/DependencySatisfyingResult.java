package com.github.foskel.haptor.satisfy;

/**
 * @author Fred
 * @since 7/17/2017
 */
public final class DependencySatisfyingResult<I, D> {
    private final I identifier;
    private final D dependency;
    private final boolean validationResult;

    public DependencySatisfyingResult(I identifier, D dependency, boolean validationResult) {
        this.identifier = identifier;
        this.dependency = dependency;
        this.validationResult = validationResult;
    }

    public I getIdentifier() {
        return this.identifier;
    }

    public D getDependency() {
        return this.dependency;
    }

    public boolean getValidationResult() {
        return this.validationResult;
    }

    @Override
    public String toString() {
        return "DependencySatisfyingResult{id=" +
                this.identifier + "," + "value=" +
                this.dependency + "," + "result=" +
                this.validationResult + "}";
    }
}