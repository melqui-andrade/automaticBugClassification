package support;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class BugReportReader {
	
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
