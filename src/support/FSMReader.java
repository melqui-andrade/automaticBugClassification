package support;

import com.opencsv.CSVReader;

import stateMachine.FSM;
import stateMachine.State;
import stateMachine.Transition;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
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
		String stateTag = "Behavioral_Elements.State_Machines.CompositeState.subvertex";
		String transitionTag = "Behavioral_Elements.State_Machines.StateMachine.transitions";

		try{
			File xmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nListStates = doc.getElementsByTagName(stateTag);

			//System.out.println("----------------------------");

			for (int temp = 0; temp < nListStates.getLength(); temp++) {

				Node nNode = nListStates.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if(nNode.hasChildNodes()){

					NodeList childs = nNode.getChildNodes();

					for(int i = 0; i < childs.getLength(); i++){

						Node child = childs.item(i);
						if(child.getNodeType() == Node.ELEMENT_NODE){
							if(child.hasAttributes()){
								if(child.getNodeName() == "Behavioral_Elements.State_Machines.SimpleState"){
									if(child.hasChildNodes()){

										if(child.getNodeType() == Node.ELEMENT_NODE) {

											Element element = (Element)child;

											State state = new State();

											state.setId(element.getAttribute("xmi.id"));
											state.setName(element.getElementsByTagName("Foundation.Core.ModelElement.name").
													item(0).getTextContent());
											fsm.addState(state);

											//System.out.println(state.getName());

										}

										//										for(int j = 0; j < child.getChildNodes().getLength(); j++){
										//											Node grandSon = child.getChildNodes().item(j);
										//											
										//											if(grandSon.getNodeName() == "Foundation.Core.ModelElement.name"){
										//												
										//												Node name = grandSon.getFirstChild();
										//												State newState = new State();
										//												//newState.setId(grandSon.);
										//												newState.setName(name.getNodeValue());
										//												fsm.addState(newState);
										//												//System.out.println(name.getNodeName());
										//												System.out.println(name.getNodeValue());
										//											}
										//										}
									}									
								}
							}
						}
					}
				}								
			}			

			NodeList nListTrans = doc.getElementsByTagName(transitionTag);
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			//System.out.println("----------------------------");


			for (int temp = 0; temp < nListTrans.getLength(); temp++) {

				Node nNode = nListTrans.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if(nNode.getNodeType() == Node.ELEMENT_NODE) {

					if(nNode.hasChildNodes()){
						NodeList childenTrans = nNode.getChildNodes();

						for (int i = 0; i < childenTrans.getLength(); i++) {
							Node t = childenTrans.item(i);
							if(t.getNodeName() == "Behavioral_Elements.State_Machines.Transition"){

								if(t.getNodeType() == Node.ELEMENT_NODE) {

									Element element = (Element) t;

									Transition transition = new Transition();
									transition.setId(element.getAttribute("xmi.id"));

									Element source = (Element) element.getElementsByTagName("Behavioral_Elements.State_Machines.Transition.source").item(0);
									Element target = (Element) element.getElementsByTagName("Behavioral_Elements.State_Machines.Transition.target").item(0);
									
									Element sourceID = (Element) source.getElementsByTagName("Behavioral_Elements.State_Machines.StateVertex").item(0);
									Element targetID = (Element) target.getElementsByTagName("Behavioral_Elements.State_Machines.StateVertex").item(0);
									
									
									transition.setSourceID(sourceID.getAttribute("xmi.idref"));
									transition.setTargetID(targetID.getAttribute("xmi.idref"));

									fsm.addTransition(transition);
								}

							}
						}

					}					
				}				
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
