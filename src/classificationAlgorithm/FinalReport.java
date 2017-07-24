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
	
	public void printReport() {
		System.out.println("Bugs founded: " + bugsFound.size());
		System.out.println("----------------------------");
		
		for(BugMatch bug : bugsFound) {
			System.out.println(bug.getClassification() + "on: ");
			for(String element : bug.getElements()) {
				System.out.println(element);
			}
			System.out.println();
		}
	}

}
