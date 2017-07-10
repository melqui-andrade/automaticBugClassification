package classificationAlgorithm;

import java.util.ArrayList;

import bugClassification.Bug;
import bugClassification.Classification;
import stateMachine.FSM;
import stateMachine.State;
import stateMachine.Transition;
import support.BugReportReader;
import support.FSMReader;

public class Algorithm {
	private ArrayList<String> bugReport;
	private FSM fsm;
	private FinalReport relationsFound;

	public Algorithm(String pathFSM, String pathBugReport) {
		
		readBugReport(pathBugReport);
		readFSM(pathFSM);

	}
	
	public FinalReport searchRelations(){
		FinalReport finalReport = new FinalReport();
		
		for(String term : bugReport){
			
			for(Transition transition : fsm.getTransitions()){
				handleTransition(term, transition);
			}
			
			for(State state : fsm.getStates()){
				handleState(term, state);
			}
			
		}
		
		return finalReport;
	}
	
	private void handleTransition(String term, Transition transition) {
		
		ArrayList<String> matches = SearchRelations.matchGuards(term, transition.getGuards());
		if(!matches.isEmpty()){
			Bug newBug = new Bug(Classification.FAILURE_OF_GUARD, "Guard failure", matches);
			relationsFound.addBug(newBug);
			
		}
		
	}
	
	private void handleState(String term, State state) {
		// TODO Auto-generated method stub
		
	}


	private void readBugReport(String path){
		bugReport = new ArrayList<>();
		
		BugReportReader.readCSV(path);
	}
	
	private void readFSM(String path){
		fsm = new FSM();
		
		FSMReader.readCSV(path);
	}

}