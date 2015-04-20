/*
 * Copyright 2015, Teradata, Inc. All rights reserved.
 */

package com.teradata.test.fulfillment.table;

import com.teradata.test.Requirement;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.teradata.test.fulfillment.table.MutableTableRequirement.State.LOADED;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class MutableTableRequirement
        implements Requirement
{
    public enum State
    {
        CREATED,
        LOADED
    }

    private final TableDefinition tableDefinition;
    private final String name;
    private final State state;

    public MutableTableRequirement(TableDefinition tableDefinition)
    {
        this(tableDefinition, tableDefinition.getName(), LOADED);
    }

    public MutableTableRequirement(TableDefinition tableDefinition, String name, State state)
    {
        this.tableDefinition = checkNotNull(tableDefinition);
        this.name = checkNotNull(name);
        this.state = checkNotNull(state);
    }

    public TableDefinition getTableDefinition()
    {
        return tableDefinition;
    }

    public String getName()
    {
        return name;
    }

    public State getState()
    {
        return state;
    }

    @Override
    public boolean equals(Object o)
    {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode()
    {
        return reflectionHashCode(this);
    }

    public static MutableTableRequirementBuilder builder(TableDefinition tableDefinition)
    {
        return new MutableTableRequirementBuilder(tableDefinition);
    }

    public static class MutableTableRequirementBuilder
    {
        private final TableDefinition tableDefinition;
        private String name;
        private State state;

        public MutableTableRequirementBuilder(TableDefinition tableDefinition)
        {
            this.tableDefinition = tableDefinition;
        }

        public MutableTableRequirementBuilder withName(String name)
        {
            this.name = name;
            return this;
        }

        public MutableTableRequirementBuilder withState(State state)
        {
            this.state = state;
            return this;
        }

        public MutableTableRequirement build()
        {
            return new MutableTableRequirement(tableDefinition, name, state);
        }
    }
}
