package support;

import com.opencsv.CSVReader;

import stateMachine.FSM;
import stateMachine.State;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FSMReader {

	public static FSM extractFSMfromXML(String path){

		FSM fsm = new FSM();
		Document doc = null;

		try{
			File xmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Behavioral_Elements.State_Machines.CompositeState.subvertex");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if(nNode.hasChildNodes()){

					NodeList childs = nNode.getChildNodes();

					for(int i = 0; i < childs.getLength(); i++){

						Node child = childs.item(i);
						if(child.getNodeType() == Node.ELEMENT_NODE){
							if(child.hasAttributes()){
								if(child.getNodeName() == "Behavioral_Elements.State_Machines.SimpleState"){
									if(child.hasChildNodes()){
										for(int j = 0; j < child.getChildNodes().getLength(); j++){
											Node grandSon = child.getChildNodes().item(j);
											if(grandSon.getNodeName() == "Foundation.Core.ModelElement.name"){
												
												Node name = grandSon.getFirstChild();
												State newState = new State();
												newState.setName(name.getNodeValue());
												fsm.addState(newState);
												//System.out.println(name.getNodeName());
												System.out.println(name.getNodeValue());
											}
										}
									}
									
								}
							}
						}
					}
				}

				/*if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					eElement.getElementsByTagName("<Behavioral_Elements.State_Machines.SimpleState");
					if(eElement.getTagName() == "<Behavioral_Elements.State_Machines.SimpleState"){

					}

					System.out.println("Bug id : " + eElement.getAttribute("Foundation.Core.ModelElement.name"));
					//System.out.println("Short desc : " + eElement.getElementsByTagName("short_desc").item(0).getTextContent());
					//System.out.println("Product : " + eElement.getElementsByTagName("product").item(0).getTextContent());
					//System.out.println("Reporter : " + eElement.getElementsByTagName("reporter").item(0).getTextContent());
					//System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

				}*/
			}

		} catch(Exception e){
			e.printStackTrace();
		}

		return fsm;
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
