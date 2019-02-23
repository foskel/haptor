package com.github.foskel.haptor.validate;

import com.github.foskel.haptor.DependencyRef;

import java.util.Collection;

/**
 * Created by F on 7/17/2017.
 */
public final class DefaultValidator implements DependencyValidatorService {

    @Override
    public <I, D> boolean validate(DependencyRef<I, D> ref) {
        return ref.isSatisfied();
    }
}