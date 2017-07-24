package classificationAlgorithm;


import java.util.ArrayList;

import bugClassification.BugReported;
import stateMachine.State;

public class SearchRelations {

	public static ArrayList<String>	matchGuards(BugReported bug, ArrayList<String> guards){

		ArrayList<String> matches = new ArrayList<>();


		return matches;
	}

	public static ArrayList<String> matchState(BugReported bug, State state){
		ArrayList<String> matches = new ArrayList<>();

		if(bug.getTitle().contains(state.getName()) || state.getName().contains(bug.getTitle())) {
			matches.add(state.getName());
		}

		return matches;
	}

}
