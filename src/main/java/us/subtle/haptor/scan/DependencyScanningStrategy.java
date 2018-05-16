package us.subtle.haptor.scan;

import java.util.List;

public interface DependencyScanningStrategy<I, D> {
    List<DependencyScanResult<I, D>> scan(Object source);
}