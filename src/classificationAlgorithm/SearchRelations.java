package classificationAlgorithm;


import java.util.ArrayList;

import bugClassification.BugMatch;
import bugClassification.BugReported;
import stateMachine.FSM;
import stateMachine.State;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class SearchRelations {
	
	private ArrayList<BugReported> bugReport;
	private StandardAnalyzer analyzer;
	private Directory index;
	private IndexWriterConfig config;
	private IndexWriter w;
	private FSM fsm;
	
	
	public SearchRelations(ArrayList<BugReported> bugReport) {
		this.bugReport = bugReport;
		
		// 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
		analyzer = new StandardAnalyzer();
		// 1. create the index
        index = new RAMDirectory();

        config = new IndexWriterConfig(analyzer);
        
        IndexWriter w;
        try {
        	w = new IndexWriter(index, config);        
        	addDoc(w);
        	w.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	public SearchRelations(FSM fsm) throws IOException, ParseException{
		
		this.fsm = fsm;
		
		// 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
		try {
		analyzer = new StandardAnalyzer();
		// 1. create the index
        index = new RAMDirectory();

        config = new IndexWriterConfig(analyzer);
        
        IndexWriter w;
        	w = new IndexWriter(index, config);        
        	addDoc(w);
        	w.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}

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

	public BugMatch matchState(State state) throws IOException, ParseException {        
        

        // 2. query
        String querystr =  state.getName();

        // the "title" arg specifies the default field to use
        // when no field is explicitly specified in the query.
        Query q = new QueryParser("title", analyzer).parse(querystr);
        

        // 3. search
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        // 4. display results
        System.out.println("Found on State \"" + state.getName() + "\": " + hits.length + " hits.");
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". Bug ID: " + d.get("bug_id") + "\t title: " + d.get("title"));
        }

        // reader can only be closed when there
        // is no need to access the documents any more.
        reader.close();
        
        return null;

	}
	
	public ArrayList<String> matchState(BugReported bug) {
		
		String querystr = bug.getTitle();
		
		
		querystr = querystr.replaceAll("[\\p{Punct}]", " ");
		
		
		
		querystr = querystr.trim();
		
		Query q;
		try {
			q = new QueryParser("title", analyzer).parse(querystr);
			


			int hitsPerPage = 10;
			IndexReader reader = DirectoryReader.open(index);
			IndexSearcher searcher = new IndexSearcher(reader);
			TopDocs docs = searcher.search(q, hitsPerPage);
			ScoreDoc[] hits = docs.scoreDocs;

			System.out.println("Found on Bug: \"" + bug.getTitle() + "\" :" + hits.length + " hits.");
			for(int i = 0; i < hits.length; i++) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				System.out.println("\t" + (i + 1) + " - State: " + d.get("title"));
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void addDoc(IndexWriter w){
		try {
		for(State s : fsm.getStates()) {
			Document docState = new Document();
			docState.add(new TextField("title", s.getName(), Field.Store.YES));
			for(String action : s.getActions()) {
				docState.add(new StringField("action", action, Field.Store.YES));
			}
			w.addDocument(docState);
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		busca antiga
//		 for(BugReported bug : bugReport) {
//			 Document doc = new Document();
//			 doc.add(new TextField("title", bug.getTitle(), Field.Store.YES));
//			 //doc.add(new TextField("description", bug.getComment(), Field.Store.YES));
//			 doc.add(new StringField("bug_id", bug.getId(), Field.Store.YES));
//			 w.addDocument(doc);
//		 }
		
	}


}
