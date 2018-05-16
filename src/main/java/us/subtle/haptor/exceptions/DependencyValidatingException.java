package us.subtle.haptor.exceptions;

/**
 * @author Fred
 * @since 7/17/2017
 */
public class DependencyValidatingException extends Exception {
    DependencyValidatingException(String message) {
        super(message);
    }
}