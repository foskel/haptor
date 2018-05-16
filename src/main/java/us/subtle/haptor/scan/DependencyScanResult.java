package us.subtle.haptor.scan;

public interface DependencyScanResult<I, D> {
    I getDependencyIdentifier();

    D getDependency();
}
