package com.github.foskel.haptor.scan;

import java.util.Collection;

@FunctionalInterface
public interface UnsatisfiedDependencyScanner {
    Collection<?> scan(Object source);
}