package support;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;

public class Printer {
	
	public static void printDoc2File(ScoreDoc[] hits, IndexSearcher searcher) {
		try {
			
			FileWriter fileWriter = new FileWriter("./file/hits.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for(int i=0;i<hits.length;++i) {
				int docId = hits[i].doc;
	            Document d = searcher.doc(docId);
	            printWriter.println((i + 1) + ". Bug ID: " + d.get("bug_id") + "\t title: " + d.get("title") +
	            		"\t Severity: " + d.get("severity"));
			}
			printWriter.close();
			
		} catch( IOException e) {
			e.printStackTrace();
		}
	}

}
