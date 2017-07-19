package stateMachine;

import java.util.ArrayList;

public class Transition {
	
	private String id;
	private String sourceID;
	private String targetID;
	private String trigger;
	private ArrayList<String> guards;
	private ArrayList<String> actions;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSource() {
		return sourceID;
	}
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}
	public String getTargetID() {
		return targetID;
	}
	public void setTargetID(String targetID) {
		this.targetID = targetID;
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
