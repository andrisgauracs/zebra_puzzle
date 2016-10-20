package zebra_puzzle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
* This program solves the <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle">Zebra Puzzle</a> 
* It reads the contents of the "input.txt" file and prints out the results on the command line.
* It also creates an "output.xml" file with the results of the puzzle.
* @author  Andris Gauračs
*/
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> rules = AppHelper.readFile("input.txt");
		new Zebra(rules,new FileOutputStream("output.xml"));
	}
}
