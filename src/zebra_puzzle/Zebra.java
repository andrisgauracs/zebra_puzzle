package zebra_puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.ProcessingInstruction;

/**
 * The <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle">Zebra Puzzle</a> solver class.
 * It parses the rules given from a String ArrayList and check all possible puzzle solutions.
 *
 * @author Andris Gauračs
 */

public class Zebra {
	
	ArrayList<House> houses = new ArrayList<House>();
	ArrayList<House[]> solutionList;
	int numberOfHouses;
	ArrayList<String> exactRules, positionRules;
	String[] nationalities,colors,pets,smokes,drinks;
	
	public Zebra(ArrayList<String> rules, OutputStream s) {
		findKeyValues(rules);
		numberOfHouses = getNumberOfHouses(rules);
		exactRules = findExactRules(rules);
		positionRules = findPositionRules(rules);
		generateValidHouses();
		solutionList = new ArrayList<House[]>();
		findSolutionRecursive(houses,solutionList,new House[numberOfHouses],0,numberOfHouses,positionRules);
		System.out.println("Solution count: "+solutionList.size()); 
		for (House[] solution : solutionList) {
			for (House h : solution) {
				System.out.println(h.getPosition()+1+". - "+h.getValue("Color")+" , "+h.getValue("Nationality")+" , "+h.getValue("Drink")+" , "+h.getValue("Pet")+" , "+h.getValue("Smoke"));
			}
		}
		try {
			generateSolutionXML(solutionList,s);
		}catch (ParserConfigurationException e) {
			System.out.println("Error configuring xml parser");
		} 
		catch (FileNotFoundException e) {
			System.out.println("output.xml not found");
		} 
		catch (TransformerException e) {
	        System.out.println("Transforming solution to XML failed");
	    } 
		catch (IOException e) {
	        System.out.println("Unable to create output.xml");
	    }
	}
	
	/**
     * First we detect the number of houses used in this puzzle
     *
     * @param rules List of the rules
     * @return Returns the number of houses
     */
	private int getNumberOfHouses(ArrayList<String> rules) {
		int number = 0;
		for (int i=0; i<rules.size(); i++) {
			if (Character.isDigit(rules.get(i).charAt(0))) {
				number = Integer.parseInt(rules.get(i));
				return number;
			}
		}
		return number;
	}
	
	/**
     * The function retrieves the number of solutions after solving the puzzle 
     *
     * @return Returns the number of found solutions
     */
	public int returnSolutionCount() {
		if (solutionList != null) return solutionList.size();
		else return 0;
	}
	
	/**
     * This function reads all nationality,color,drink,pet and smoke values from the given set of rules.
     *
     * @param rules List of the rules
     */
	private void findKeyValues(ArrayList<String> rules) {
		ArrayList<String> colorsList = new ArrayList<String>();
		ArrayList<String> nationalitiesList = new ArrayList<String>();
		ArrayList<String> drinksList = new ArrayList<String>();
		ArrayList<String> petsList = new ArrayList<String>();
		ArrayList<String> smokesList = new ArrayList<String>();
		for (String rule : rules) {
			String[] parts = rule.split(";");
			if (parts.length >= 2) {
				if (parts[1].equals("color")) {
					if (!colorsList.contains(parts[2])) colorsList.add(parts[2]);
				}
				if (parts[1].equals("drink")) {
					if (!drinksList.contains(parts[2])) drinksList.add(parts[2]);
				}
				if (parts[1].equals("nationality")) {
					if (!nationalitiesList.contains(parts[2])) nationalitiesList.add(parts[2]);
				}
				if (parts[1].equals("pet")) {
					if (!petsList.contains(parts[2])) petsList.add(parts[2]);
				}
				if (parts[1].equals("smoke")) { 
					if (!smokesList.contains(parts[2])) smokesList.add(parts[2]);
				}
			}
			if (parts.length >= 4) {
				if (parts[3].equals("color")) {
					if (!colorsList.contains(parts[4])) colorsList.add(parts[4]);
				}
				if (parts[3].equals("drink")) {
					if (!drinksList.contains(parts[4])) drinksList.add(parts[4]);
				}
				if (parts[3].equals("nationality")) {
					if (!nationalitiesList.contains(parts[4])) nationalitiesList.add(parts[4]);
				}
				if (parts[3].equals("pet")) {
					if (!petsList.contains(parts[4])) petsList.add(parts[4]);
				}
				if (parts[3].equals("smoke")) { 
					if (!smokesList.contains(parts[4])) smokesList.add(parts[4]);
				}
			}
		}
		nationalities = new String[nationalitiesList.size()];
		for (int i=0; i<nationalitiesList.size(); i++) nationalities[i] = nationalitiesList.get(i);
		colors = new String[colorsList.size()];
		for (int i=0; i<colorsList.size(); i++) colors[i] = colorsList.get(i);
		pets = new String[petsList.size()];
		for (int i=0; i<petsList.size(); i++) pets[i] = petsList.get(i);
		drinks = new String[drinksList.size()];
		for (int i=0; i<drinksList.size(); i++) drinks[i] = drinksList.get(i);
		smokes = new String[smokesList.size()];
		for (int i=0; i<smokesList.size(); i++) smokes[i] = smokesList.get(i);
	}
	
	/**
     * This function finds all the rules, that give exact information about a house.
     *
     * @param rules List of the rules
     * @return Returns the list of exact rules
     */
	private ArrayList<String> findExactRules(ArrayList<String> rules) {
		ArrayList<String> resultList = new ArrayList<String>();
		for (int i=0; i<rules.size(); i++) {
			int count = rules.get(i).length() - rules.get(i).replace(";", "").length();
			if (rules.get(i).contains("SAME") && count > 2) {
				resultList.add(rules.get(i));
			}
		}
		return resultList;
	}
	
	/**
     * This function finds all the rules, that give positional information about houses.
     *
     * @param rules List of the rules
     * @return Returns the list of positional rules
     */
	private ArrayList<String> findPositionRules(ArrayList<String> rules) {
		ArrayList<String> resultList = new ArrayList<String>();
		for (int i=0; i<rules.size(); i++) {
			int count = rules.get(i).length() - rules.get(i).replace(";", "").length();
			if (!rules.get(i).contains("SAME") && count > 2) {
				resultList.add(rules.get(i));
			}
		}
		return resultList;
	}
	
	/**
     * By using the information given by the exact rules (ones that start with "SAME")
     * we can filter out all possible house combinations
     *
     */
	private void generateValidHouses() {
		for (int i=0; i<numberOfHouses; i++) {
			for (String color : colors) {
				for (String nationality : nationalities) {
					for (String smoke : smokes) {
						for (String pet : pets) {
							for (String drink : drinks) {
								House house = new House(i,nationality,color,drink,pet,smoke);
								if (checkExactRules(house,exactRules)) houses.add(house);
							}
						}
					}
				}
			}
		}
	}
	
	/**
     * This function checks if the particular house meet all the exact rule requirements.
     *
     * @param house The particular house to be checked
     * @param rules The list of exact rules
     * @return Returns true or false
     */
	private Boolean checkExactRules(House house, ArrayList<String> rules) {
		for (int i=0; i<rules.size(); i++) {
			String parts[] = rules.get(i).split(";");
			if (parts[1].equals("position") || parts[3].equals("position")) {
				if (house.getValue(parts[1]).equals(parts[2])) {
					if ((house.getPosition()+1) != Integer.parseInt(parts[4])) 
						return false;
				}
				if (house.getValue(parts[3]).equals(parts[4])) {
					if ((house.getPosition()+1) != Integer.parseInt(parts[2]))
						return false;
				}
				if (parts[1].equals("position") && (house.getPosition()+1) == Integer.parseInt(parts[2])) {
					if (!house.getValue(parts[3]).equals(parts[4]))
						return false;
				}
				if (parts[3].equals("position") && (house.getPosition()+1) == Integer.parseInt(parts[4])) {
					if (!house.getValue(parts[1]).equals(parts[2]))
						return false;
				}
			} else {
				if (house.getValue(parts[1]).equals(parts[2])) {
					if (!house.getValue(parts[3]).equals(parts[4]))
						return false;
				}
				if (house.getValue(parts[3]).equals(parts[4])) {
					if (!house.getValue(parts[1]).equals(parts[2]))
						return false;
				}
			}
		} return true;
	}
	
	/**
     * This function checks if the particular house meet all the positional rule requirements.
     *
     * @param house The particular house to be checked
     * @param rules The list of exact rules
     * @return Returns true or false
     */
	private static Boolean checkPositionRules(House[] houses, ArrayList<String> rules, int numberOfHouses) {
		if (houses.length != numberOfHouses) {
			return false;
		}
		for (int i=0; i<houses.length; i++) {
			for (int j=0; j<houses.length; j++) {
				if (i != j) {					
					if (houses[i].getValue("Color").equals(houses[j].getValue("Color"))) return false;
					if (houses[i].getValue("nationality").equals(houses[j].getValue("nationality"))) return false;
					if (houses[i].getValue("Pet").equals(houses[j].getValue("Pet"))) return false;
					if (houses[i].getValue("Drink").equals(houses[j].getValue("Drink"))) return false;
					if (houses[i].getValue("Smoke").equals(houses[j].getValue("Smoke"))) return false;
				}
			}
			for (String rule : rules) {
				String[] parts = rule.split(";");
				switch (parts[0]) {
				case "TO_THE_LEFT_OF":
					if (i < (numberOfHouses-1)) {
						if (houses[i].getValue(parts[1]).equals(parts[2])) {
							if (!houses[i+1].getValue(parts[3]).equals(parts[4]))
								return false;
						}
					}
					if (i == (numberOfHouses-1) && houses[i].getValue(parts[1]).equals(parts[2])) {
						return false;
					}
					if (i == 0 && houses[i].getValue(parts[3]).equals(parts[4])) {
						return false;
					}
					break;
				case "NEXT_TO":
					if (i == 0) {
						if (houses[i].getValue(parts[3]).equals(parts[4])) {
							if (!houses[i+1].getValue(parts[1]).equals(parts[2]))
								return false;
						}
						if (houses[i].getValue(parts[1]).equals(parts[2])) {
							if (!houses[i+1].getValue(parts[3]).equals(parts[4]))
								return false;
						}
					}
					if (i == (numberOfHouses-1)) {
						if (houses[i].getValue(parts[3]).equals(parts[4])) {
							if (!houses[i-1].getValue(parts[1]).equals(parts[2]))
								return false;
						}
						if (houses[i].getValue(parts[1]).equals(parts[2])) {
							if (!houses[i-1].getValue(parts[3]).equals(parts[4]))
								return false;
						}
					}
					if ((i > 0) && (i < (numberOfHouses-1))) {
						if (houses[i].getValue(parts[3]).equals(parts[4])) {
							if ((!houses[i-1].getValue(parts[1]).equals(parts[2])) &&
							   (!houses[i+1].getValue(parts[1]).equals(parts[2]))) {
								return false;
							}
						}
						if (houses[i].getValue(parts[1]).equals(parts[2])) {
							if ((!houses[i-1].getValue(parts[3]).equals(parts[4])) &&
							   (!houses[i+1].getValue(parts[3]).equals(parts[4]))) {
								return false;
							}
						}
					}
					break;
				}
			}
		}
		return true;
	}
	
	/**
     * This recursive function iterates through all the house list combinations
     * to find the solution for the puzzle. If the positional rule requirements
     * are met, the house list is a valid puzzle solution.
     *
     */
	private void findSolutionRecursive(
			ArrayList<House> houses, 
			ArrayList<House[]> resultList,
			House[] tempList, 
			int pos, 
			int maxPos,
			ArrayList<String> rules) 
	{
		for (House h : houses) {
			if (h.getPosition() == pos) {
				tempList[pos] = h;
				if (pos < maxPos-1) {
					findSolutionRecursive(houses,resultList,tempList,pos+1,maxPos,rules);
				}
				else if (pos == maxPos-1) {
					if (checkPositionRules(tempList,rules,maxPos)) {
						House[] resultHouse = new House[maxPos];
						for (int i=0; i<maxPos; i++) resultHouse[i] = tempList[i];
						resultList.add(resultHouse);
					}
				}
			}
		}
	}
	
	/**
     * Renders the puzzle solution in an XML format.
     */
	public void generateSolutionXML(ArrayList<House[]> solutions, OutputStream stream) throws TransformerException, ParserConfigurationException, IOException {
            File f = new File("output.xml");
            f.createNewFile();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(generateXML(solutions));
            StreamResult result = new StreamResult(stream);
            //Make a readable output
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, result);
	}
	
	/**
     * Generates the XML document containing the information about the solutions.
     */
	private Document generateXML(ArrayList<House[]> solutions) throws ParserConfigurationException {
		DocumentBuilderFactory buildFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = buildFactory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element root = doc.createElement("solutions");
        doc.appendChild(root);
        doc.setXmlStandalone(true);
        ProcessingInstruction pi = doc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"stylesheet.xsl\"");
        doc.insertBefore(pi, root);
        for (House[] houseList : solutions) {
        	Element solution = doc.createElement("solution");
            root.appendChild(solution);
            for (House h : houseList) {
            	Element house = doc.createElement("house");
            	solution.appendChild(house);
            	//Add position attribute
            	Attr pos = doc.createAttribute("position");
            	pos.setValue(Integer.toString((h.getPosition()+1)));
            	house.setAttributeNode(pos);
            	//Add color attribute
            	Attr color = doc.createAttribute("color");
            	color.setValue(h.getValue("Color"));
            	house.setAttributeNode(color);
            	//Add nationality attribute
            	Attr nationality = doc.createAttribute("nationality");
            	nationality.setValue(h.getValue("Nationality"));
            	house.setAttributeNode(nationality);
            	//Add Drink attribute
            	Attr drink = doc.createAttribute("drink");
            	drink.setValue(h.getValue("Drink"));
            	house.setAttributeNode(drink);
            	//Add Pet attribute
            	Attr pet = doc.createAttribute("pet");
            	pet.setValue(h.getValue("Pet"));
            	house.setAttributeNode(pet);
            	//Add Smoke attribute
            	Attr smoke = doc.createAttribute("smoke");
            	smoke.setValue(h.getValue("Smoke"));
            	house.setAttributeNode(smoke);
            }
        }
        return doc;
	}
}
