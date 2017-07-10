package stateMachine;

import java.util.ArrayList;

public class Transition {
	
	private int id;
	private State source;
	private State target;
	private String trigger;
	private ArrayList<String> guards;
	private ArrayList<String> actions;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public State getSource() {
		return source;
	}
	public void setSource(State source) {
		this.source = source;
	}
	public State getTarget() {
		return target;
	}
	public void setTarget(State target) {
		this.target = target;
	}
	public String getTrigger() {
		return trigger;
	}
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	public ArrayList<String> getGuards() {
		return guards;
	}
	public void addGuard(String guard) {
		guards.add(guard);
	}
	public ArrayList<String> getActions() {
		return actions;
	}
	public void addAction(String action) {
		actions.add(action);
	}
	
		
	
}
