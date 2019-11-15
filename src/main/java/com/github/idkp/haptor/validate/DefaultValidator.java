package com.github.idkp.haptor.validate;

import com.github.idkp.haptor.DependencyRef;

/**
 * 7/17/2017
 */
public final class DefaultValidator implements DependencyValidatorService {

    @Override
    public <I, D> boolean validate(DependencyRef<I, D> ref) {
        return ref.isSatisfied();
    }
}