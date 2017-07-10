package classificationAlgorithm;

public class Aplication {

	public static void main(String[] args) {
		
		Algorithm algorithm = new Algorithm(args[0], args[1]);
		algorithm.searchRelations();

	}

}
