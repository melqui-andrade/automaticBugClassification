package classificationAlgorithm;

public class Aplication {

	public static void main(String[] args) {
		
		String pathFSM = "./file/fsmcasos.xml";
		String pathBug = "./file/showbug.xml";
		
		Algorithm algorithm = new Algorithm(pathFSM, pathBug);
		FinalReport finalReport = algorithm.searchRelations();
		finalReport.printReport();

	}

}
