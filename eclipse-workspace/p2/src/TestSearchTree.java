import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          cs400_p2_201801
// FILES:            TestSearchTree.java
//                   SearchTreeADT.java
//                   BalancedSearchTree.java
//
// USER:             deppeler
//
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs:             no known bugs, but not complete either
//
// 2018 Feb 8, 2018 5:13:18 PM TestSearchTree.java 
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * @author 
 *
 */
public class TestSearchTree {

	SearchTreeADT<String> tree = null;
	String expected = null;
	String actual = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tree = new BalancedSearchTree<String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	//fails if isEmpty() returns false for a newly constructed search tree
	public void test01_isEmpty_on_empty_tree() {
		expected = "true";
		actual = "" + tree.isEmpty();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	public void test02_ascending_order_on_empty_tree() {
		expected = "";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height of an empty tree is 0 */
	public void test03_height_on_empty_tree() {
		expected = "0";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	public void test04_isEmpty_after_one_insert() {
		tree.insert("1");
		expected = "false";
		actual = "" + tree.isEmpty();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the ascending order after inserting A item is "A," */
	public void test05_ascending_order_after_one_insert() {
		tree.insert("A");
		expected = "A,";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height after inserting A is 1 */
	public void test06_height_after_one_insert() {
		tree.insert("A");
		expected = "1";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height after inserting A and deleting it is 0 */
	public void test07_height_after_one_insert_and_delete() {
		tree.insert("A");
		tree.delete("A");
		expected = "0";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

}

