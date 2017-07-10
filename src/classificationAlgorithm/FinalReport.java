package classificationAlgorithm;

import java.util.ArrayList;
import java.util.List;

import bugClassification.Bug;

public class FinalReport {
	
	private List<Bug> bugsFound;
	
	public FinalReport(){
		bugsFound = new ArrayList<>();
	}
	
	public void addBug(Bug bug){
		bugsFound.add(bug);
	}

}
