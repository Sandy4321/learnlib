/* Copyright (C) 2013-2019 TU Dortmund
 * This file is part of LearnLib, http://www.learnlib.de/.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.learnlib.filter.cache.mealy;

import java.util.Collection;

import de.learnlib.api.oracle.MembershipOracle;
import de.learnlib.api.oracle.StateLocalInputOracle;
import net.automatalib.automata.transducers.OutputAndLocalInputs;
import net.automatalib.commons.util.mappings.Mapping;
import net.automatalib.incremental.mealy.dag.IncrementalMealyDAGBuilder;
import net.automatalib.words.Alphabet;
import net.automatalib.words.Word;

public final class MealyCaches {

    private MealyCaches() {
        throw new IllegalStateException();
    }

    /**
     * Creates a cache oracle for a Mealy machine learning setup, using a DAG for internal cache organization.
     *
     * @param alphabet
     *         the input alphabet
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a DAG-based implementation
     */
    public static <I, O> MealyCacheOracle<I, O> createDAGCache(Alphabet<I> alphabet,
                                                               MembershipOracle<I, Word<O>> mqOracle) {
        return MealyCacheOracle.createDAGCacheOracle(alphabet, mqOracle);
    }

    /**
     * Creates a cache oracle for a Mealy machine learning setup, using a DAG for internal cache organization.
     *
     * @param alphabet
     *         the input alphabet
     * @param errorSyms
     *         a mapping for the prefix-closure filter
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a DAG-based implementation
     */
    public static <I, O> MealyCacheOracle<I, O> createDAGCache(Alphabet<I> alphabet,
                                                               Mapping<? super O, ? extends O> errorSyms,
                                                               MembershipOracle<I, Word<O>> mqOracle) {
        return MealyCacheOracle.createDAGCacheOracle(alphabet, errorSyms, mqOracle);
    }

    /**
     * Creates a cache oracle for a Mealy machine learning setup, using a tree for internal cache organization.
     *
     * @param alphabet
     *         the input alphabet
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a tree-based implementation
     */
    public static <I, O> MealyCacheOracle<I, O> createTreeCache(Alphabet<I> alphabet,
                                                                MembershipOracle<I, Word<O>> mqOracle) {
        return MealyCacheOracle.createTreeCacheOracle(alphabet, mqOracle);
    }

    /**
     * Creates a cache oracle for a Mealy machine learning setup, using a tree for internal cache organization.
     *
     * @param alphabet
     *         the input alphabet
     * @param errorSyms
     *         a mapping for the prefix-closure filter
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a tree-based implementation
     */
    public static <I, O> MealyCacheOracle<I, O> createTreeCache(Alphabet<I> alphabet,
                                                                Mapping<? super O, ? extends O> errorSyms,
                                                                MembershipOracle<I, Word<O>> mqOracle) {
        return MealyCacheOracle.createTreeCacheOracle(alphabet, errorSyms, mqOracle);
    }

    /**
     * Creates a cache oracle for a Mealy machine learning setup with a dynamic alphabet storage, using a tree for
     * internal cache organization.
     * <p>
     * Note: Due to the dynamic alphabet storage, memory consumption of a dense tree may be higher than normal caches
     * with a predefined alphabet. However, for sparse data, the memory consumption may be lower as only memory for the
     * actual data of the tree is allocated.
     *
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a tree-based implementation
     */
    public static <I, O> MealyCacheOracle<I, O> createDynamicTreeCache(MembershipOracle<I, Word<O>> mqOracle) {
        return MealyCacheOracle.createDynamicTreeCacheOracle(mqOracle);
    }

    /**
     * Creates a cache oracle for a Mealy machine learning setup with a dynamic alphabet storage, using a tree for
     * internal cache organization.
     * <p>
     * Note: Due to the dynamic alphabet storage, memory consumption of a dense tree may be higher than normal caches
     * with a predefined alphabet. However, for sparse data, the memory consumption may be lower as only memory for the
     * actual data of the tree is allocated.
     *
     * @param errorSyms
     *         a mapping for the prefix-closure filter
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a tree-based implementation
     */
    public static <I, O> MealyCacheOracle<I, O> createDynamicTreeCache(Mapping<? super O, ? extends O> errorSyms,
                                                                       MembershipOracle<I, Word<O>> mqOracle) {
        return MealyCacheOracle.createDynamicTreeCacheOracle(errorSyms, mqOracle);
    }

    /**
     * Creates a cache oracle for a Mealy machine learning setup with observable state local inputs for every state of
     * the system under learning.
     *
     * @param initialLocalInputs
     *         a collection of input symbols that can be executed in the initial state of the system under learning
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a tree-based implementation
     */
    public static <I, O> MealyCacheOracle<I, OutputAndLocalInputs<I, O>> createStateLocalInputTreeCache(Collection<I> initialLocalInputs,
                                                                                                        MembershipOracle<I, Word<OutputAndLocalInputs<I, O>>> mqOracle) {
        return MealyCacheOracle.createStateLocalInputTreeCacheOracle(initialLocalInputs, mqOracle);
    }

    /**
     * Creates a cache oracle for a Mealy machine learning setup with observable state local inputs for every state of
     * the system under learning.
     *
     * @param initialLocalInputs
     *         a collection of input symbols that can be executed in the initial state of the system under learning
     * @param errorSyms
     *         a mapping for the prefix-closure filter
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a tree-based implementation
     */
    public static <I, O> MealyCacheOracle<I, OutputAndLocalInputs<I, O>> createStateLocalInputTreeCache(Collection<I> initialLocalInputs,
                                                                                                        Mapping<? super OutputAndLocalInputs<I, O>, ? extends OutputAndLocalInputs<I, O>> errorSyms,
                                                                                                        MembershipOracle<I, Word<OutputAndLocalInputs<I, O>>> mqOracle) {
        return MealyCacheOracle.createStateLocalInputTreeCacheOracle(initialLocalInputs, errorSyms, mqOracle);
    }

    /**
     * A variation of {@link #createStateLocalInputTreeCache(Collection, MembershipOracle)} that returns a {@link
     * StateLocalInputMealyCacheOracle}.
     *
     * @param initialLocalInputs
     *         a collection of input symbols that can be executed in the initial state of the system under learning
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a tree-based implementation
     */
    public static <I, O> StateLocalInputMealyCacheOracle<I, O> createStateLocalInputTreeCache(Collection<I> initialLocalInputs,
                                                                                              StateLocalInputOracle<I, Word<OutputAndLocalInputs<I, O>>> mqOracle) {
        return StateLocalInputMealyCacheOracle.createStateLocalInputTreeCacheOracle(initialLocalInputs, mqOracle);
    }

    /**
     * A variation of {@link #createStateLocalInputTreeCache(Collection, MembershipOracle)} that returns a {@link
     * StateLocalInputMealyCacheOracle}.
     *
     * @param initialLocalInputs
     *         a collection of input symbols that can be executed in the initial state of the system under learning
     * @param errorSyms
     *         a mapping for the prefix-closure filter
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a tree-based implementation
     */
    public static <I, O> StateLocalInputMealyCacheOracle<I, O> createStateLocalInputTreeCache(Collection<I> initialLocalInputs,
                                                                                              Mapping<? super OutputAndLocalInputs<I, O>, ? extends OutputAndLocalInputs<I, O>> errorSyms,
                                                                                              StateLocalInputOracle<I, Word<OutputAndLocalInputs<I, O>>> mqOracle) {
        return StateLocalInputMealyCacheOracle.createStateLocalInputTreeCacheOracle(initialLocalInputs,
                                                                                    errorSyms,
                                                                                    mqOracle);
    }

    /**
     * Creates a cache oracle for a Mealy machine learning setup.
     * <p>
     * Note that this method does not specify the implementation to use for the cache. Currently, a DAG ({@link
     * IncrementalMealyDAGBuilder}) is used; however, this may change in the future.
     *
     * @param alphabet
     *         the input alphabet
     * @param mqOracle
     *         the membership oracle
     *
     * @return a Mealy learning cache with a default implementation
     */
    public static <I, O> MealyCacheOracle<I, O> createCache(Alphabet<I> alphabet,
                                                            MembershipOracle<I, Word<O>> mqOracle) {
        return MealyCacheOracle.createDAGCacheOracle(alphabet, mqOracle);
    }
}
