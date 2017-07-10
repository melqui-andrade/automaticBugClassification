package bugClassification;

import java.util.ArrayList;

public class Bug {
	
	private Classification classification;
	private String scenario;
	private ArrayList<String> elements;
	
	public Bug(Classification classification, String scenario, ArrayList<String> elements){
		this.classification = classification;
		this.scenario = scenario;
		this.elements = elements;
	}
	
	public Classification getClassification() {
		return classification;
	}
	public void setClassification(Classification classification) {
		this.classification = classification;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public ArrayList<String> getElements() {
		return elements;
	}

	public void setElements(ArrayList<String> elements) {
		this.elements = elements;
	}
	
	
	

}
