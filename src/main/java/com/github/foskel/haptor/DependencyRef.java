package com.github.foskel.haptor;

public final class DependencyRef<I, D> {
    private final I identifier;
    private Object value;

    public DependencyRef(I identifier) {
        this.identifier = identifier;
    }

    public I getIdentifier() {
        return identifier;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isSatisfied() {
        return value != null;
    }
}
