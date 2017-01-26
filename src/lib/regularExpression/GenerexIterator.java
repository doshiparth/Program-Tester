package lib.regularExpression;

import java.util.ArrayDeque;
import java.util.Deque;

import lib.regularExpression.util.Iterator;

import dk.brics.automaton.State;
import dk.brics.automaton.Transition;

/**
 * An implementation of {@code Iterator} class that iterate over the list of Strings that matches a given Regex.
 * 
 * @author neel patel
 *
 */
public class GenerexIterator implements Iterator {

	private final Deque<Step> steps;
	private final StringBuilder stringBuilder;
	private boolean found;

	public GenerexIterator(State initialState) {
		steps = new ArrayDeque<Step>();
		int initialCapacity;
		if (initialState.isAccept() && initialState.getTransitions().isEmpty()) {
			found = true;
			initialCapacity = 0;
		} else {
			steps.push(new Step(initialState));
			initialCapacity = 16; // Use default initial capacity
		}
		stringBuilder = new StringBuilder(initialCapacity);
	}

	public boolean hasNext() {
		if (found) {
			return true;
		}
		if (steps.isEmpty()) {
			return false;
		}
		nextImpl();
		return found;
	}

	private void nextImpl() {
		Step currentStep;

		while (!steps.isEmpty() && !found) {
			currentStep = steps.pop();
			found = currentStep.build(stringBuilder, steps);
		}
	}

	public String next() {
		if (!found) {
			nextImpl();
		}
		if (!found) {
			throw new IllegalStateException();
		}
		found = false;
		return stringBuilder.toString();
	}

	/**
	 * A step, in the iteration process, to build a string using {@code State}s.
	 * <p>
	 * It's responsible to keep the information of a {@code State}, like current char and transitions that need to be followed.
	 * Also it adds (and removes) the characters while iterating over the characters (when the state has a range) and
	 * transitions.
	 * <p>
	 * Implementation based on {@code SpecialOperations.getFiniteStrings(Automaton,int)}, but in a non-recursive way to avoid
	 * {@code StackOverflowError}s.
	 * 
	 * //@see State
	 * //@see dk.brics.automaton.SpecialOperations#getFiniteStrings(dk.brics.automaton.Automaton,int)
	 */
	private static class Step {

		private java.util.Iterator<Transition> iteratorTransitions;
		private Transition currentTransition;
		private char currentChar;

		public Step(State state) {
			this.iteratorTransitions = state.getSortedTransitions(true).iterator();
		}

		public boolean build(StringBuilder stringBuilder, Deque<Step> steps) {
			if (hasCurrentTransition()) {
				currentChar++;
			} else if (!moveToNextTransition()) {
				removeLastChar(stringBuilder);
				return false;
			}

			if (currentChar <= currentTransition.getMax()) {
				stringBuilder.append(currentChar);
				if (currentTransition.getDest().isAccept()) {
					pushForDestinationOfCurrentTransition(steps);
					if (currentChar >= currentTransition.getMax()) {
						currentTransition = null;
					}
					return true;
				}
				pushForDestinationOfCurrentTransition(steps);
				return false;
			}
			steps.push(this);
			currentTransition = null;
			return false;
		}

		private boolean hasCurrentTransition() {
			return currentTransition != null;
		}

		private boolean moveToNextTransition() {
			if (!iteratorTransitions.hasNext()) {
				return false;
			}
			currentTransition = iteratorTransitions.next();
			currentChar = currentTransition.getMin();
			return true;
		}

		private static void removeLastChar(StringBuilder stringBuilder) {
			int len = stringBuilder.length();
			if (len > 0) {
				stringBuilder.deleteCharAt(len - 1);
			}
		}

		private void pushForDestinationOfCurrentTransition(Deque<Step> steps) {
			steps.push(this);
			steps.push(new Step(currentTransition.getDest()));
		}
	}
}