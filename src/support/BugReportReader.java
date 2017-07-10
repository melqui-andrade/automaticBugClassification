package support;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BugReportReader {

	public static Document readXML(String path){

		Document doc = null;

		try{
			File xmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("bug");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Bug id : " + eElement.getAttribute("bug_id"));
					System.out.println("Short desc : " + eElement.getElementsByTagName("short_desc").item(0).getTextContent());
					System.out.println("Product : " + eElement.getElementsByTagName("product").item(0).getTextContent());
					System.out.println("Reporter : " + eElement.getElementsByTagName("reporter").item(0).getTextContent());
					//System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

				}
			}

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
