package com.github.idkp.haptor.satisfy;

/**
 * 5/28/2017
 */
public final class UnsatisfiedDependencyException extends Exception {
    public UnsatisfiedDependencyException(String message) {
        super(message);
    }

    public UnsatisfiedDependencyException(Object dependency) {
        super(String.format("Unable to satisfy dependency (or dependency with identifier) \"%s\"", dependency));
    }
}