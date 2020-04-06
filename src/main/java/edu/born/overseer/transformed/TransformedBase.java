package edu.born.overseer.transformed;

import edu.born.overseer.model.abstraction.HasId;

public abstract class TransformedBase implements HasId {
    protected Integer id;

    public TransformedBase() {
    }

    public TransformedBase(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}