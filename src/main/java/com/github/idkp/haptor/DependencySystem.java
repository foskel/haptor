package com.github.idkp.haptor;

import com.github.idkp.haptor.process.DependencyProcessor;
import com.github.idkp.haptor.registry.DependencyRegistry;
import com.github.idkp.haptor.satisfy.UnsatisfiedDependencyException;
import com.github.idkp.haptor.scan.UnsatisfiedDependencyScanner;

import java.util.function.Function;

public interface DependencySystem<I, D> {
    boolean register(Object source, UnsatisfiedDependencyScanner scanner);

    boolean unregister(Object source);

    boolean registerProcessor(DependencyProcessor processor);

    boolean unregisterProcessor(DependencyProcessor processor);

    void satisfy(Function<I, D> depSupplier) throws UnsatisfiedDependencyException;

    DependencyRegistry<I, D> getRegistry();

    <T extends D> T find(Object... args);

    <T extends D> T find(I identifier);

    void setCustomLocator(Function<Object[], ? extends D> customLocator);
}