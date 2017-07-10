package support;

import com.opencsv.CSVReader;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FSMReader {
	
	public static Document readXML(String path){
		
		Document doc = null;
		
		try{
		File xmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(xmlFile);
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return doc;
	}

	public static CSVReader readCSV(String path){
		
		CSVReader reader = null;
		
		try{
			
			reader = new CSVReader(new FileReader(path));
			String[] line;
			
			while((line = reader.readNext()) != null){
				System.out.println("Bug [id= " + line[0] + ", Product= " 
						+ line[1] + " , Title=" + line[6] + "]");
			}
			
		} catch(IOException e){
			
			e.printStackTrace();
			
		}
		
		return reader;
	}
}
