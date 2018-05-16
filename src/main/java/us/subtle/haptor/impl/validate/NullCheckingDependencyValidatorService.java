package us.subtle.haptor.impl.validate;

import us.subtle.haptor.validate.DependencyValidatorService;

import java.util.Collection;

/**
 * Created by F on 7/17/2017.
 */
public enum NullCheckingDependencyValidatorService implements DependencyValidatorService {
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