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
package de.learnlib.algorithms.lstargeneric.closing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.learnlib.algorithms.lstargeneric.table.ObservationTable;
import de.learnlib.algorithms.lstargeneric.table.Row;
import de.learnlib.api.MembershipOracle;

public class CloseRandomStrategy<I,O> implements ClosingStrategy<I, O> {
	
	private static final CloseRandomStrategy<?,?> INSTANCE
		= new CloseRandomStrategy<Object,Object>();
	
	@SuppressWarnings("unchecked")
	public static <I,O> CloseRandomStrategy<I,O> getInstance() {
		return (CloseRandomStrategy<I,O>)INSTANCE;
	}
	
	private final Random random;
	
	public CloseRandomStrategy() {
		this(new Random());
	}
	
	public CloseRandomStrategy(Random random) {
		this.random = random;
	}

	@Override
	public List<Row<I>> selectClosingRows(List<List<Row<I>>> unclosedClasses,
			ObservationTable<I, O> table, MembershipOracle<I, O> oracle) {
		List<Row<I>> result = new ArrayList<>(unclosedClasses.size());
		
		for(List<Row<I>> clazz : unclosedClasses) {
			int card = clazz.size();
			result.add(clazz.get(random.nextInt(card)));
		}
		
		return result;
	}
	
}
