package com.github.foskel.haptor.scan;

public interface DependencyScanResult<I, D> {
    I getDependencyIdentifier();

    D getDependency();
}
