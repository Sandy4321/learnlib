/* Copyright (C) 2013 TU Dortmund
 * This file is part of LearnLib, http://www.learnlib.de/.
 * 
 * LearnLib is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 3.0 as published by the Free Software Foundation.
 * 
 * LearnLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with LearnLib; if not, see
 * <http://www.gnu.de/documents/lgpl.en.html>.
 */
package de.learnlib.algorithms.lstargeneric;

import java.util.List;

import net.automatalib.automata.MutableDeterministic;
import net.automatalib.words.Alphabet;
import net.automatalib.words.Word;
import de.learnlib.algorithms.lstargeneric.ce.ObservationTableCEXHandler;
import de.learnlib.algorithms.lstargeneric.closing.ClosingStrategy;
import de.learnlib.algorithms.lstargeneric.table.Row;
import de.learnlib.api.MembershipOracle;
import de.learnlib.oracles.DefaultQuery;

public abstract class ExtensibleAutomatonLStar<A,I,O,S,T,SP,TP,AI extends MutableDeterministic<S,I,T,SP,TP>> extends
AbstractAutomatonLStar<A, I, O,S,T,SP,TP,AI> {
	
	protected final ObservationTableCEXHandler<I, O> cexHandler;
	protected final ClosingStrategy<I, O> closingStrategy;
	protected final List<Word<I>> initialSuffixes;
	
	public ExtensibleAutomatonLStar(Alphabet<I> alphabet,
			MembershipOracle<I,O> oracle, AI internalHyp,
			List<Word<I>> initialSuffixes,
			ObservationTableCEXHandler<I,O> cexHandler,
			ClosingStrategy<I,O> closingStrategy) {
		super(alphabet, oracle, internalHyp);
		this.initialSuffixes = initialSuffixes;
		this.cexHandler = cexHandler;
		this.closingStrategy = closingStrategy;
	}
	
	@Override
	protected void doRefineHypothesis(DefaultQuery<I, O> ceQuery) {
		List<List<Row<I>>> unclosed = cexHandler.handleCounterexample(ceQuery, table, oracle);
		completeConsistentTable(unclosed, cexHandler.needsConsistencyCheck());
	}
	
	@Override
	protected List<Row<I>> selectClosingRows(List<List<Row<I>>> unclosed) {
		return closingStrategy.selectClosingRows(unclosed, table, oracle);
	}

	@Override
	protected List<Word<I>> initialSuffixes() {
		return initialSuffixes;
	}
	
	
}
