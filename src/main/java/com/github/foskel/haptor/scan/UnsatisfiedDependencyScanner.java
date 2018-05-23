package com.github.foskel.haptor.scan;

import java.util.Collection;

@FunctionalInterface
public interface UnsatisfiedDependencyScanner<T> {
    Collection<T> scan(Object source);
}