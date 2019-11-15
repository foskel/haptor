package com.github.idkp.haptor.validate;

import com.github.idkp.haptor.DependencyRef;

/**
 * 7/17/2017
 */
public interface DependencyValidatorService {
    <I, D> boolean validate(DependencyRef<I, D> ref);
}
