package stateMachine;

import java.util.ArrayList;

public class State {
	
	private int id;
	private String name;
	private ArrayList<String> action;
	
	public State(){
		action = new ArrayList<String>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getActions(){
		return this.action;
	}
	
	public void addAction(String newAction){
		this.action.add(newAction);
	}

}
