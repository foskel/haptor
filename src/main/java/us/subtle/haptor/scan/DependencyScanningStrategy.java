package us.subtle.haptor.scan;

import java.util.Map;

public interface DependencyScanningStrategy {
    Map<Object, Object> scan(Object source);
}