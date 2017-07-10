package stateMachine;

import java.util.ArrayList;

public class FSM {
	
	private ArrayList<State> states;
	private ArrayList<Transition> transitions;
	
	public FSM(){
		states = new ArrayList<State>();
		transitions = new ArrayList<Transition>();
	}
	
	public ArrayList<State> getStates(){
		return this.states;
	}
	
	public ArrayList<Transition> getTransitions(){
		return this.transitions;
	}
	
	public void addState(State state){
		states.add(state);
	}
	
	public void addTransition(Transition transition){
		transitions.add(transition);
	}

}
