package com.github.idkp.haptor.process;

import com.github.idkp.haptor.satisfy.UnsatisfiedDependencyException;
import com.github.idkp.haptor.satisfy.DependencySatisfyingResult;

@FunctionalInterface
public interface DependencyProcessor {
    void postSatisfy(DependencySatisfyingResult result) throws UnsatisfiedDependencyException;
}