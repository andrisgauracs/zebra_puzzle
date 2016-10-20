package zebra_puzzle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AppHelper {
	public static ArrayList<String> readFile(String fileName) {
		ArrayList<String> rules = new ArrayList<String>();
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                rules.add(line);
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                 
        }
        return rules;
	}
	
	public static ArrayList<String> generateWeirdData() {
		ArrayList<String> rules = new ArrayList<String>();
		rules.add("vadsvrew er");
		rules.add(";efvcqew;ecewv;e");
		rules.add("/nw;c;w;c;");
		return rules;
	}
	
	public static ArrayList<String> getTestRules(int houseCount) {
		ArrayList<String> rules = new ArrayList<String>();
		rules.add(Integer.toString(houseCount));
		rules.add("SAME;nationality;English;color;Red");
		rules.add("SAME;nationality;Spaniard;pet;Dog");
		rules.add("SAME;drink;Coffee;color;Green");
		rules.add("SAME;drink;Tea;nationality;Ukrainian");
		rules.add("TO_THE_LEFT_OF;color;Ivory;color;Green");
		rules.add("SAME;smoke;Old gold;pet;Snails");
		rules.add("SAME;smoke;Kools;color;Yellow");
		rules.add("SAME;drink;Milk;position;3");
		rules.add("SAME;nationality;Norwegian;position;1");
		rules.add("NEXT_TO;smoke;Chesterfields;pet;Fox");
		rules.add("NEXT_TO;smoke;Kools;pet;Horse");
		rules.add("SAME;smoke;Lucky strike;drink;Orange juice");
		rules.add("SAME;smoke;Parliaments;nationality;Japanese");
		rules.add("NEXT_TO;color;Blue;nationality;Norwegian");
		rules.add("SAME;drink;Water");
		rules.add("SAME;pet;Zebra");
		return rules;
	}
	
	public static ArrayList<String> getIncorrectData() {
		ArrayList<String> rules = new ArrayList<String>();
		rules.add("5");
		rules.add("SAME;nationality;English;color;Red");
		rules.add("SAME;nationality;Spaniard;pet;Dog");
		rules.add("SAME;drink;Coffee;color;Green");
		rules.add("SAME;drink;Tea;nationality;Ukrainian");
		rules.add("TO_THE_LEFT_OF;color;Ivory;color;Green");
		rules.add("SAME;smoke;Old gold;pet;Snails");
		rules.add("SAME;smoke;Kools;color;Yellow");
		rules.add("SAME;drink;Milk;position;3");
		//Next line has a wrong spelling for Norwegian
		rules.add("SAME;nationality;Norvegan;position;1");
		rules.add("NEXT_TO;smoke;Chesterfields;pet;Fox");
		rules.add("NEXT_TO;smoke;Kools;pet;Horse");
		rules.add("SAME;smoke;Lucky strike;drink;Orange juice");
		rules.add("SAME;smoke;Parliaments;nationality;Japanese");
		rules.add("NEXT_TO;color;Blue;nationality;Norwegian");
		rules.add("SAME;drink;Water");
		rules.add("SAME;pet;Zebra");
		return rules;
	}
	
	public static ArrayList<String> getIncorrectTestRules() {
		ArrayList<String> rules = new ArrayList<String>();
		rules.add("5");
		rules.add("SAME;nationality;English;color;Red");
		rules.add("SAME;nationality;Spaniard;pet;Dog");
		rules.add("SAME;drink;Coffee;color;Green");
		rules.add("SAME;drink;Tea;nationality;Ukrainian");
		rules.add("TO_THE_LEFT_OF;color;Ivory;color;Green");
		rules.add("SAME;smoke;Old gold;pet;Snails");
		//rules.add("SAME;smoke;Kools;color;Yellow");
		rules.add("SAME;drink;Milk;position;3");
		rules.add("SAME;nationality;Norwegian;position;1");
		rules.add("NEXT_TO;smoke;Chesterfields;pet;Fox");
		rules.add("NEXT_TO;smoke;Kools;pet;Horse");
		rules.add("SAME;smoke;Lucky strike;drink;Orange juice");
		rules.add("SAME;smoke;Parliaments;nationality;Japanese");
		rules.add("NEXT_TO;color;Blue;nationality;Norwegian");
		rules.add("SAME;drink;Water");
		rules.add("SAME;pet;Zebra");
		return rules;
	}
}
