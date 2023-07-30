import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Zheyuan Gao
 * @author Cedric Fausey
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size
    /*
     * Test cases for SortingMachine add method.
     */
    /**
     * Test SortingMachine add method small case.
     */
    @Test
    public final void testAddSmall() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        m.add("green");
        mExpected.add("green");
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine add method routine case.
     */
    @Test
    public final void testAddRoutine1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "A",
                "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "A", "a");
        m.add("green");
        mExpected.add("green");
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine add method routine case.
     */
    @Test
    public final void testAddRoutine2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "A",
                "a", "1", "123", "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "A", "a", "1", "123", "a");
        m.add("A");
        mExpected.add("A");
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine add method routine case.
     */
    @Test
    public final void testAddRoutine3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "A",
                "a", "1", "123", "a", "apple", "beer");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "A", "a", "1", "123", "a", "apple", "beer");
        m.add("A");
        mExpected.add("A");
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine add method challenge case (calling add method twice).
     */
    @Test
    public final void testAddChallenging1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "A",
                "a", "1", "123", "a", "apple", "beer");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "A", "a", "1", "123", "a", "apple", "beer");
        m.add("Super");
        m.add("Apple");
        mExpected.add("Super");
        mExpected.add("Apple");
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine add method challenge case (repeated labels in heap).
     */
    @Test
    public final void testAddChallenging2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "42",
                "42", "42", "42", "42", "42", "42", "42", "42", "42", "42",
                "42", "42", "42", "42", "42", "42", "42", "42", "42", "42",
                "42", "42", "42", "42", "42", "42", "42", "42", "42", "42",
                "42", "42");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "42", "42", "42", "42", "42", "42", "42", "42", "42", "42",
                "42", "42", "42", "42", "42", "42", "42", "42", "42", "42",
                "42", "42", "42", "42", "42", "42", "42", "42", "42", "42",
                "42", "42", "42");
        m.add("42");
        mExpected.add("42");
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine add method large case.
     */
    @Test
    public final void testAddLarge() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beer",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True",
                "apple", "beer", "chair", "door", "electron", "friend", "A",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "friend", "A",
                "apple", "beer", "chair", "door", "electron", "electron",
                "friend", "A", "apple", "beer", "chair", "door", "electron",
                "friend", "42", "Ultimate answer of the universe", "whatever",
                "!", "*SUPER*", "*TEST*", "*CASE*");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "beer", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "true",
                "True", "apple", "beer", "chair", "door", "electron", "friend",
                "A", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "friend",
                "A", "apple", "beer", "chair", "door", "electron", "electron",
                "friend", "A", "apple", "beer", "chair", "door", "electron",
                "friend", "42", "Ultimate answer of the universe", "whatever",
                "!", "*SUPER*", "*TEST*", "*CASE*");
        m.add("Super");
        mExpected.add("Super");
        assertEquals(mExpected, m);
    }

    /*
     * Test cases for SortingMachine changeToExtractionMode method.
     */

    /**
     * Test SortingMachine changeToExtractionMode method small case.
     */
    @Test
    public final void testchangeToExtractionModeSmall() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine changeToExtractionMode method routine case.
     */
    @Test
    public final void testchangeToExtractionModeRoutine1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "A");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "A");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine changeToExtractionMode method routine case.
     */
    @Test
    public final void testchangeToExtractionModeRoutine2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "A",
                "1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "A", "1");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine changeToExtractionMode method routine case.
     */
    @Test
    public final void testchangeToExtractionModeRoutine3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "A",
                "1", "1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "A", "1", "1");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine changeToExtractionMode method routine case.
     */
    @Test
    public final void testchangeToExtractionModeRoutine4() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "A", "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "A", "b");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine changeToExtractionMode method routine case(mExpected
     * default is extraction mode).
     */
    @Test
    public final void testchangeToExtractionModeRoutine5() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "A",
                "1", "3", "abc");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "A", "1", "3", "abc");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine changeToExtractionMode method routine case(mExpected
     * default is extraction mode).
     */
    @Test
    public final void testchangeToExtractionModeRoutine6() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "A",
                "1", "3", "abc", "yes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "A", "1", "3", "abc", "yes");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine changeToExtractionMode method large case(mExpected
     * default is extraction mode).
     */
    @Test
    public final void testchangeToExtractionModeLarge1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beer",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True",
                "apple", "beer", "chair", "door", "electron", "friend", "A",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "beer", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "true",
                "True", "apple", "beer", "chair", "door", "electron", "friend",
                "A", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True");
        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    /**
     * Test SortingMachine changeToExtractionMode method large case.
     */
    @Test
    public final void testchangeToExtractionModeLarge2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beer",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True",
                "apple", "beer", "chair", "door", "electron", "friend", "A",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "beer", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "true",
                "True", "apple", "beer", "chair", "door", "electron", "friend",
                "A", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /*
     * Test cases for SortingMachine removeFirst method.
     */

    /**
     * Test SortingMachine removeFirst method small case.
     */
    @Test
    public final void testRomoveFirstSmall() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "A");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "A");
        String str = m.removeFirst();
        String strExpected = mExpected.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(strExpected, str);
    }

    /**
     * Test SortingMachine removeFirst method routine case.
     */
    @Test
    public final void testRomoveFirstRoutine1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "A",
                "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "A", "a");
        String str = m.removeFirst();
        String strExpected = mExpected.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(strExpected, str);

    }

    /**
     * Test SortingMachine removeFirst method routine case.
     */
    @Test
    public final void testRomoveFirstRoutine2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "A",
                "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "A", "a");
        String str = m.removeFirst();
        String strExpected = mExpected.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(strExpected, str);

    }

    /**
     * Test SortingMachine removeFirst method routine case.
     */
    @Test
    public final void testRomoveFirstRoutine3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "A",
                "a", "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "A", "a", "b");
        String str = m.removeFirst();
        String strExpected = mExpected.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(strExpected, str);

    }

    /**
     * Test SortingMachine removeFirst method routine case.
     */
    @Test
    public final void testRomoveFirstRoutine4() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "z",
                "z", "a", "b", "1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "z", "z", "a", "b", "1");
        String str = m.removeFirst();
        String strExpected = mExpected.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(strExpected, str);

    }

    /**
     * Test SortingMachine removeFirst method challenging case(default in
     * insertion mode and call method to change to extraction mode).
     */
    @Test
    public final void testRomoveFirstChallenge() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "z",
                "z", "a", "b", "1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "z", "z", "a", "b", "1");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        String str = m.removeFirst();
        String strExpected = mExpected.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(strExpected, str);

    }

    /**
     * Test SortingMachine removeFirst method large case(use a for loop to
     * remove all the elements in the m and compare every elements with the
     * expected elements).
     */
    @Test
    public final void testRomoveFirstLarge() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "beer",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True",
                "apple", "beer", "chair", "door", "electron", "friend", "A",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "electron",
                "electron", "friend", "A", "apple", "beer", "chair", "door",
                "electron", "friend", "42", "Ultimate answer of the universe",
                "whatever", "!", "*SUPER*", "*TEST*", "*CASE*");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "beer", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "true",
                "True", "apple", "beer", "chair", "door", "electron", "friend",
                "A", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "electron",
                "electron", "friend", "A", "apple", "beer", "chair", "door",
                "electron", "friend", "42", "Ultimate answer of the universe",
                "whatever", "!", "*SUPER*", "*TEST*", "*CASE*");
        for (int i = 0; i < m.size(); i++) {
            String str = m.removeFirst();
            String strExpected = mExpected.removeFirst();
            assertEquals(mExpected, m);
            assertEquals(strExpected, str);
        }

    }

    /*
     * Test cases for SortingMachine isInInsertionMode method.
     */

    /**
     * Test SortingMachine isInInsertionMode method small case.
     */
    @Test
    public final void testIsInInsertionModeSmall1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /**
     * Test SortingMachine isInInsertionMode method small case.
     */
    @Test
    public final void testIsInInsertionModeSmall2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /**
     * Test SortingMachine isInInsertionMode method routine case.
     */
    @Test
    public final void testIsInInsertionModeRoutine1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beer");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "beer");

        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /**
     * Test SortingMachine isInInsertionMode method routine case.
     */
    @Test
    public final void testIsInInsertionModeRoutine2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "beer");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "beer");

        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /**
     * Test SortingMachine isInInsertionMode method routine case.
     */
    @Test
    public final void testIsInInsertionModeRoutine3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "beer",
                "c", "eyes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "beer", "c", "eyes");

        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /**
     * Test SortingMachine isInInsertionMode method routine case.
     */
    @Test
    public final void testIsInInsertionModeRoutine4() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beer",
                "c", "eyes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "beer", "c", "eyes");

        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /**
     * Test SortingMachine isInInsertionMode method challenging case(m default
     * in insertion mode and call the method to change it to extraction mode).
     */
    @Test
    public final void testIsInInsertionModeChallenge1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beer",
                "c", "eyes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "beer", "c", "eyes");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /**
     * Test SortingMachine isInInsertionMode method challenging case(m and
     * mExpected default in insertion mode and call the method to change it to
     * extraction mode).
     */
    @Test
    public final void testIsInInsertionModeChallenge2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beer",
                "c", "eyes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "beer", "c", "eyes");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /**
     * Test SortingMachine isInInsertionMode method large case.
     */
    @Test
    public final void testIsInInsertionModeLarge1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beer",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True",
                "apple", "beer", "chair", "door", "electron", "friend", "A",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "electron",
                "electron", "friend", "A", "apple", "beer", "chair", "door",
                "electron", "friend", "42", "Ultimate answer of the universe",
                "whatever", "!", "*SUPER*", "*TEST*", "*CASE*");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "beer", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "true",
                "True", "apple", "beer", "chair", "door", "electron", "friend",
                "A", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "electron",
                "electron", "friend", "A", "apple", "beer", "chair", "door",
                "electron", "friend", "42", "Ultimate answer of the universe",
                "whatever", "!", "*SUPER*", "*TEST*", "*CASE*");
        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /**
     * Test SortingMachine isInInsertionMode method large case.
     */
    @Test
    public final void testIsInInsertionModeLarge2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "beer",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True",
                "apple", "beer", "chair", "door", "electron", "friend", "A",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "electron",
                "electron", "friend", "A", "apple", "beer", "chair", "door",
                "electron", "friend", "42", "Ultimate answer of the universe",
                "whatever", "!", "*SUPER*", "*TEST*", "*CASE*");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "beer", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "true",
                "True", "apple", "beer", "chair", "door", "electron", "friend",
                "A", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "electron",
                "electron", "friend", "A", "apple", "beer", "chair", "door",
                "electron", "friend", "42", "Ultimate answer of the universe",
                "whatever", "!", "*SUPER*", "*TEST*", "*CASE*");
        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /*
     * Test cases for SortingMachine order method.
     */

    /**
     * Test SortingMachine order method small case.
     */
    @Test
    public final void testOrderSmall() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected, m);
        assertEquals(m.order(), mExpected.order());
    }

    /**
     * Test SortingMachine order method routine case.
     */
    @Test
    public final void testOrderRoutine1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a");

        assertEquals(mExpected, m);
        assertEquals(m.order(), mExpected.order());
    }

    /**
     * Test SortingMachine order method routine case.
     */
    @Test
    public final void testOrderRoutine2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "a");

        assertEquals(mExpected, m);
        assertEquals(m.order(), mExpected.order());
    }

    /**
     * Test SortingMachine order method routine case.
     */
    @Test
    public final void testOrderRoutine3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "a", "beer");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "a", "beer");

        assertEquals(mExpected, m);
        assertEquals(m.order(), mExpected.order());
    }

    /**
     * Test SortingMachine order method routine case.
     */
    @Test
    public final void testOrderRoutine4() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "z",
                "a", "1", "1234", "4321");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "z", "a", "1", "1234", "4321");

        assertEquals(mExpected, m);
        assertEquals(m.order(), mExpected.order());
    }

    /**
     * Test SortingMachine order method large case.
     */
    @Test
    public final void testOrderLarge() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beer",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True",
                "apple", "beer", "chair", "door", "electron", "friend", "A",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "electron",
                "electron", "friend", "A", "apple", "beer", "chair", "door",
                "electron", "friend", "42", "Ultimate answer of the universe",
                "whatever", "!", "*SUPER*", "*TEST*", "*CASE*");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "beer", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "true",
                "True", "apple", "beer", "chair", "door", "electron", "friend",
                "A", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "electron",
                "electron", "friend", "A", "apple", "beer", "chair", "door",
                "electron", "friend", "42", "Ultimate answer of the universe",
                "whatever", "!", "*SUPER*", "*TEST*", "*CASE*");

        assertEquals(mExpected, m);
        assertEquals(m.order(), mExpected.order());
    }

    /*
     * Test cases for SortingMachine size method.
     */

    /**
     * Test SortingMachine size method small case.
     */
    @Test
    public final void testSizeSmall1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected, m);
        assertEquals(m.size(), mExpected.size());
    }

    /**
     * Test SortingMachine size method small case.
     */
    @Test
    public final void testSizeSmall2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        assertEquals(mExpected, m);
        assertEquals(m.size(), mExpected.size());
    }

    /**
     * Test SortingMachine size method routine case.
     */
    @Test
    public final void testSizeRoutine1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "1");

        assertEquals(mExpected, m);
        assertEquals(m.size(), mExpected.size());
    }

    /**
     * Test SortingMachine size method routine case.
     */
    @Test
    public final void testSizeRoutine2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "2");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "2");

        assertEquals(mExpected, m);
        assertEquals(m.size(), mExpected.size());
    }

    /**
     * Test SortingMachine size method routine case.
     */
    @Test
    public final void testSizeRoutine3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "2", "Abstract");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "2", "Abstract");

        assertEquals(mExpected, m);
        assertEquals(m.size(), mExpected.size());
    }

    /**
     * Test SortingMachine size method routine case.
     */
    @Test
    public final void testSizeRoutine4() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "2", "Abstract", ":");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "2", "Abstract", ":");

        assertEquals(mExpected, m);
        assertEquals(m.size(), mExpected.size());
    }

    /**
     * Test SortingMachine size method challenging case(m default in
     * extactionMode, mExpected default in insertion mode).
     */
    @Test
    public final void testSizeChallenging() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "1",
                "2", "Abstract", ":");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "2", "Abstract", ":");

        assertEquals(m.size(), mExpected.size());
    }

    /**
     * Test SortingMachine size method routine case.
     */
    @Test
    public final void testSizeLarge() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "beer",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "true", "True",
                "apple", "beer", "chair", "door", "electron", "friend", "A",
                "apple", "beer", "chair", "door", "electron", "friend", "42",
                "Ultimate answer of the universe", "Not True", "electron",
                "electron", "friend", "A", "apple", "beer", "chair", "door",
                "electron", "friend", "42", "Ultimate answer of the universe",
                "whatever", "!", "*SUPER*", "*TEST*", "*CASE*");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "beer", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "true",
                "True", "apple", "beer", "chair", "door", "electron", "friend",
                "A", "apple", "beer", "chair", "door", "electron", "friend",
                "42", "Ultimate answer of the universe", "Not True", "electron",
                "electron", "friend", "A", "apple", "beer", "chair", "door",
                "electron", "friend", "42", "Ultimate answer of the universe",
                "whatever", "!", "*SUPER*", "*TEST*", "*CASE*");

        assertEquals(mExpected, m);
        assertEquals(m.size(), mExpected.size());
    }

}
