package com.github.idkp.haptor.scan;

import java.util.Collection;

@FunctionalInterface
public interface UnsatisfiedDependencyScanner {
    Collection<?> scan(Object source);
}