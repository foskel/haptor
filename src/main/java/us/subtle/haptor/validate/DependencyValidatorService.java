package us.subtle.haptor.validate;

import java.util.Collection;

/**
 * Created by F on 7/17/2017.
 */
public interface DependencyValidatorService {
    boolean validate(Object dependency);

    boolean validate(Collection<Object> dependencies);
}
