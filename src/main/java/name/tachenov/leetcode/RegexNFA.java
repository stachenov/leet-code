/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alqualos
 */
public class RegexNFA {
    public boolean isMatch(String s, String p) {
        State start = compile(p);
        return execute(start, s);
    }
    
    private static State compile(String p) {
        State start = null, current = null, prev = null;
        State finish = new MatchingState();
        for (int i = 0; i < p.length(); ++i) {
            char c = p.charAt(i);
            switch (c) {
                case '*': {
                    if (current == null)
                        throw new IllegalArgumentException("The pattern can't start with a *");
                    ForkState fork = new ForkState();
                    fork.addNextState(current);
                    current.addNextState(fork);
                    if (prev == null) {
                        start = fork;
                    } else {
                        prev.replaceNextState(current, fork);
                    }
                    current = fork;
                    break;
                }
                case '.':
                default: {
                    prev = current;
                    current = c == '.' ? new WildcardState() : new LiteralState(c);
                    if (prev == null) {
                        start = current; // the first state
                    } else {
                        prev.addNextState(current); // link them
                    }
                    break;
                }
            }
        }
        if (current == null) {
            return finish;
        } else {
            current.addNextState(finish);
            return start;
        }
    }
    
    private static boolean execute(State start, String s) {
        List<State> current = new StateCollection(-1);
        current.add(start);
        for (int i = 0; !current.isEmpty() && i < s.length(); ++i) {
            char c = s.charAt(i);
            List<State> next = new StateCollection(i);
            for (State state : current) {
                //System.out.println("Current state: " + state + " i = " + i);
                state.enterNextStates(next, c);
            }
            current = next;
        }
        for (State state : current) {
            if (state.matches())
                return true;
        }
        return false;
    }
    
    private static abstract class State {
        int collectionID = -2;
        abstract void enterNextStates(final List<State> next, char c);
        abstract void followForks(final List<State> next);
        abstract void addNextState(State next);
        abstract void replaceNextState(State oldState, State newState);
        abstract boolean matches();
    }
    
    private static abstract class SingleCharState extends State {
        State nextState = null;
        
        @Override
        void addNextState(State next) {
            if (nextState != null)
                throw new IllegalStateException("Already has a next state");
            nextState = next;
        }

        @Override
        void replaceNextState(State oldState, State newState) {
            nextState = newState;
        }

        @Override
        boolean matches() {
            return false; // never "just" matches, needs a char
        }

        @Override
        void followForks(List<State> next) {
            if (nextState instanceof ForkState) {
                nextState.followForks(next);
            } else {
                next.add(nextState);
            }
        }
    }
    
    private static class WildcardState extends SingleCharState {
        
        @Override
        void enterNextStates(final List<State> next, char c) {
            //System.out.println("Entering " + nextState);
            followForks(next);
        }
    }
    
    private static class LiteralState extends SingleCharState {
        char literal;

        LiteralState(char literal) {
            this.literal = literal;
        }

        @Override
        void enterNextStates(final List<State> next, char c) {
            if (c != literal)
                return;
            //System.out.println("Entering " + nextState);
            followForks(next);
        }
    }
    
    private static class ForkState extends State {
        State left = null, right = null;

        @Override
        void enterNextStates(final List<State> next, char c) {
            left.enterNextStates(next, c);
            right.enterNextStates(next, c);
        }
         
        @Override
        void addNextState(State next) {
            if (left == null) {
                left = next;
            } else if (right == null) {
                right = next;
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        void replaceNextState(State oldState, State newState) {
            if (right == oldState) {
                right = newState;
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        boolean matches() {
            return right.matches(); // left is always a char
        }

        @Override
        void followForks(List<State> next) {
            next.add(left);
            if (right instanceof ForkState) {
                right.followForks(next);
            } else {
                next.add(right);
            }
        }
   }
    
    private static class MatchingState extends State {
        @Override
        void enterNextStates(final List<State> next, char c) {
            // already matched, can't move forward
        }
         
        @Override
        void addNextState(State next) {
            throw new UnsupportedOperationException("No next state is allowed for the matching state");
        }

        @Override
        void replaceNextState(State oldState, State newState) {
            throw new UnsupportedOperationException("No next state is allowed for the matching state");
        }

        @Override
        boolean matches() {
            return true;
        }

        @Override
        void followForks(List<State> next) {
            // no next states, hence no forks either
        }
    }
    
    private static class StateCollection extends ArrayList<State> {
        private static final long serialVersionUID = 0L;
        private final int id;

        public StateCollection(int id) {
            this.id = id;
        }

        @Override
        public boolean add(State e) {
            if (e.collectionID != id) {
                e.collectionID = id;
                return super.add(e);
            } else {
                return false; // already on the list
            }
        }
        
    }
    
    public static void main(String[] args) {
        RegexNFA nfa = new RegexNFA();
        char[] s = new char[30];
        Random rnd = new Random(0);
        for (int i = 0; i < 1000000; ++i) {
            for (int j = 0; j < s.length; ++j) {
                s[j] = (char) (rnd.nextInt(3) + 'a');
            }
            nfa.isMatch(new String(s), "a*b*c*abcc*a*b*");
        }
    }
    
}
