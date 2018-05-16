package us.subtle.haptor.impl.scan;

import us.subtle.haptor.scan.DependencyScanResult;

public final class SimpleDependencyScanResult<I, D> implements DependencyScanResult<I, D>  {
    private final I dependencyIdentifier;
    private final D dependency;

    public SimpleDependencyScanResult(I dependencyIdentifier, D dependency) {
        this.dependencyIdentifier = dependencyIdentifier;
        this.dependency = dependency;
    }

    @Override
    public I getDependencyIdentifier() {
        return this.dependencyIdentifier;
    }

    @Override
    public D getDependency() {
        return this.dependency;
    }
}