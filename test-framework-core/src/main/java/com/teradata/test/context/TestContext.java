/*
 * Copyright 2015, Teradata, Inc. All rights reserved.
 */

package com.teradata.test.context;

import static java.util.Arrays.asList;

public interface TestContext
{
    /**
     * Allows obtaining runtime dependency from within test body.
     * Common types of dependencies would be State instance coming out of RequirementFulfillers
     * and services for performing work on cluster (like QueryExecutor, HdfsClient, RemoteExecutor)
     *
     * @param dependencyClass Class of dependency to be obtained
     */
    <T> T getDependency(Class<T> dependencyClass);

    <T> T getDependency(Class<T> dependencyClass, String dependencyName);

    /**
     * Creates a new child {@link TestContext} with new {@link com.google.inject.Injector}
     * that contains new states.
     */
    TestContext createChildContext(Iterable<State> states);

    default TestContext createChildContext(State... states)
    {
        return createChildContext(asList(states));
    }

    /**
     * Registers a callback that will be executed on {@link TestContext} close.
     */
    void registerCloseCallback(TestContextCloseCallback callback);

    /**
     * Closes this {@link TestContext} and all children {@link TestContext}s.
     */
    void close();
}
