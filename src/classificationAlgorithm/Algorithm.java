package classificationAlgorithm;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryparser.classic.ParseException;

import bugClassification.BugMatch;
import bugClassification.BugReported;
import bugClassification.Classification;
import stateMachine.FSM;
import stateMachine.State;
import stateMachine.Transition;
import support.BugReportReader;
import support.FSMReader;

public class Algorithm {	
	private ArrayList<BugReported> bugReport;
	private FSM fsm;
	private FinalReport relationsFound;

	public Algorithm(String pathFSM, String pathBugReport) {
		relationsFound = new FinalReport();
		readBugReport(pathBugReport);
		readFSM(pathFSM);

	}
	
	public FinalReport searchRelations(){
		FinalReport finalReport = new FinalReport();
		try {
			
			SearchRelations searcher = new SearchRelations(bugReport);
			System.out.println(bugReport.size() + " bugs analysed");
			for(State state : fsm.getStates()) {
				searcher.matchState(state);
			}
			
		} catch (IOException | ParseException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Oh my....");
			e.printStackTrace();
		}
		
		
		for(BugReported bug : bugReport){
			
			for(Transition transition : fsm.getTransitions()){
				handleTransition(bug, transition);
			}
			
			for(State state : fsm.getStates()){
				handleState(bug, state);
			}
			
		}
		
		return finalReport;
	}
	
	private void handleTransition(BugReported bug, Transition transition) {
		
		ArrayList<String> matches = SearchRelations.matchGuards(bug, transition.getGuards());
		if(!matches.isEmpty()){
			BugMatch newBug = new BugMatch(Classification.FAILURE_OF_GUARD, "Guard failure", matches);
			relationsFound.addBug(newBug);
			
		}
		
	}
	
	private void handleState(BugReported bug, State state) {
		ArrayList<String> matches = SearchRelations.matchState(bug, state);
		if(!matches.isEmpty()) {
			BugMatch newBug = new BugMatch(Classification.FAILURE_OF_STATE, "Failure of state", matches);
			relationsFound.addBug(newBug);
		}
		
	}


	private void readBugReport(String path){
				
		bugReport = BugReportReader.extractBugReportFromXML(path);
	}
	
	private void readFSM(String path){
				
		fsm = FSMReader.extractFSMfromXML(path);
	}

}