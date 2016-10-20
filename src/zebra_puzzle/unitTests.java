package zebra_puzzle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import zebra_puzzle.Zebra;

public class unitTests {

	@Test
	public void get_empty_file() throws FileNotFoundException {
		new Zebra(new ArrayList<String>(),new FileOutputStream("output.xml"));
		Assert.fail("The file is empty");
	}
	
	@Test
	public void check_for_solution() throws FileNotFoundException {
		Zebra zebra = new Zebra(AppHelper.getTestRules(5),new FileOutputStream("output.xml"));
		int solutionCount =  zebra.returnSolutionCount();
		Assert.assertTrue("Solution was not is found",solutionCount > 0);
	}
	
	@Test
	public void test_invalid_rules() throws FileNotFoundException {
		Zebra zebra = new Zebra(AppHelper.getIncorrectTestRules(),new FileOutputStream("output.xml"));
		int solutionCount =  zebra.returnSolutionCount();
		Assert.assertTrue("Solution was not is found",solutionCount > 0);
	}
	
	@Test
	public void test_incorrect_rule_data() throws FileNotFoundException {
		Zebra zebra = new Zebra(AppHelper.getIncorrectData(),new FileOutputStream("output.xml"));
		int solutionCount =  zebra.returnSolutionCount();
		Assert.assertTrue("Solution was not is found",solutionCount > 0);
	}
	
	@Test
	public void test_weird_data() throws FileNotFoundException {
		Zebra zebra = new Zebra(AppHelper.generateWeirdData(),new FileOutputStream("output.xml"));
		int solutionCount =  zebra.returnSolutionCount();
		Assert.assertTrue("Solution was not is found",solutionCount > 0);
	}
	
	@Test
	public void test_incorrect_house_number() throws FileNotFoundException {
		Zebra zebra = new Zebra(AppHelper.getTestRules(4),new FileOutputStream("output.xml"));
		int solutionCount =  zebra.returnSolutionCount();
		Assert.assertTrue("Solution was not is found",solutionCount > 0);
	}

}
