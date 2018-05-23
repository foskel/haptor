package com.github.foskel.haptor.validate;

import com.github.foskel.haptor.validate.DependencyValidatorService;

import java.util.Collection;

/**
 * Created by F on 7/17/2017.
 */
public enum NullCheckingDependencyValidator implements DependencyValidatorService {
    INSTANCE;

    @Override
    public boolean validate(Object dependency) {
        return dependency != null;
    }

    @Override
    public boolean validate(Collection<Object> dependencies) {
        return dependencies.stream().allMatch(this::validate);
    }
}