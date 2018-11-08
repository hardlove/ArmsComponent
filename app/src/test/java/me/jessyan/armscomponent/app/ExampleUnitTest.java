package me.jessyan.armscomponent.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void tesView() {
        int childCount = 5;
        int index = 0;
        for (int i = 0; i < childCount; i++) {
            index = i;
            break;
        }
        assertEquals(1, index);
    }

}