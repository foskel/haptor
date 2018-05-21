package com.github.foskel.haptor.satisfy;

/**
 * Created by F on 7/17/2017.
 */
public interface DependencySatisfyingResult {
    Object getDependencyIdentifier();

    Object getDependency();

    boolean getValidationResult();
}