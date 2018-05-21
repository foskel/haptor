package com.github.foskel.haptor.process;

import com.github.foskel.haptor.exceptions.UnsatisfiedDependencyException;
import com.github.foskel.haptor.satisfy.DependencySatisfyingResult;

@FunctionalInterface
public interface DependencySatisfyingProcessor {
    void postSatisfy(DependencySatisfyingResult result) throws UnsatisfiedDependencyException;
}