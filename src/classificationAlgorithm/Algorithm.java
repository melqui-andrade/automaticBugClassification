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
	private SearchRelations searcher;

	public Algorithm(String pathFSM, String pathBugReport) {
		relationsFound = new FinalReport();
		readBugReport(pathBugReport);
		readFSM(pathFSM);


		try {
			searcher = new SearchRelations(fsm);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public FinalReport searchRelations(){
		FinalReport finalReport = new FinalReport();
		
		for(BugReported bug : bugReport){
			findMatches(bug);
			System.out.println("\t\t---------------");
		}
		
		return finalReport;
	}
	
	private void findMatches(BugReported bug) {
		ArrayList<String> matches = searcher.matchState(bug);
	}


	private void readBugReport(String path){
				
		bugReport = BugReportReader.extractBugReportFromXML(path);
	}
	
	private void readFSM(String path){
				
		fsm = FSMReader.extractFSMfromXML(path);
	}

}