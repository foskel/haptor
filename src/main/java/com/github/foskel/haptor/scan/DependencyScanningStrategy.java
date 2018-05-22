package com.github.foskel.haptor.scan;

import java.util.Collection;

@FunctionalInterface
public interface DependencyScanningStrategy<T> {
    Collection<T> scan(Object source);
}