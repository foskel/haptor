package us.subtle.haptor.impl.satisfy;

import us.subtle.haptor.satisfy.DependencySatisfyingResult;

/**
 * @author Fred
 * @since 7/17/2017
 */
public final class SimpleDependencySatisfyingResult implements DependencySatisfyingResult {
    private final Object dependencyIdentifier;
    private final Object dependency;
    private final boolean validationResult;

    public SimpleDependencySatisfyingResult(Object dependencyIdentifier,
                                            Object dependency,
                                            boolean validationResult) {
        this.dependencyIdentifier = dependencyIdentifier;
        this.dependency = dependency;
        this.validationResult = validationResult;
    }

    @Override
    public Object getDependencyIdentifier() {
        return this.dependencyIdentifier;
    }

    @Override
    public Object getDependency() {
        return this.dependency;
    }

    @Override
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