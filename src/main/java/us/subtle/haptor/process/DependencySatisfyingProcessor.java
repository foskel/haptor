package us.subtle.haptor.process;

import us.subtle.haptor.exceptions.UnsatisfiedDependencyException;
import us.subtle.haptor.satisfy.DependencySatisfyingResult;

@FunctionalInterface
public interface DependencySatisfyingProcessor {
    void postSatisfy(DependencySatisfyingResult result) throws UnsatisfiedDependencyException;
}