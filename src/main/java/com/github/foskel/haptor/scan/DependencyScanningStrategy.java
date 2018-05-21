package com.github.foskel.haptor.scan;

import java.util.List;

public interface DependencyScanningStrategy<I, D> {
    List<DependencyScanResult<I, D>> scan(Object source);
}