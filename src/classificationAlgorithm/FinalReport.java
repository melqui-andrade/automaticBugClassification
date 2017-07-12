package classificationAlgorithm;

import java.util.ArrayList;
import java.util.List;

import bugClassification.BugMatch;

public class FinalReport {
	
	private List<BugMatch> bugsFound;
	
	public FinalReport(){
		bugsFound = new ArrayList<>();
	}
	
	public void addBug(BugMatch bug){
		bugsFound.add(bug);
	}

}
