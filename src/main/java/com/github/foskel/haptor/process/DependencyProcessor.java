package com.github.foskel.haptor.process;

import com.github.foskel.haptor.satisfy.UnsatisfiedDependencyException;
import com.github.foskel.haptor.satisfy.DependencySatisfyingResult;

@FunctionalInterface
public interface DependencyProcessor {
    void postSatisfy(DependencySatisfyingResult result) throws UnsatisfiedDependencyException;
}