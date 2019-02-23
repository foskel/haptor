package com.github.foskel.haptor.validate;

import com.github.foskel.haptor.DependencyRef;

/**
 * Created by F on 7/17/2017.
 */
public interface DependencyValidatorService {
    <I, D> boolean validate(DependencyRef<I, D> ref);
}
