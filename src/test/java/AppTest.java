import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class AppTest
{
    private ArrayList<Integer> randomTestParamater;

    

    @Test
    public void nullParameterControlTest()
    {
        randomTestParamater = App.initializeRandomArray(5, 100);
        assertNull(App.randomNumberVersionNaming(
            randomTestParamater,
            null,
            1.0f,
            '_'
            ));
    }

    @Test
    public void parameterLengthControlTest()
    {
        randomTestParamater = App.initializeRandomArray(5, 100);
        assertNull(App.randomNumberVersionNaming(
            randomTestParamater,
            new ArrayList<String>(Arrays.asList(new String[]{"Deneme1", "Deneme2"})),
            1.0f,
            '_'
            ));
    }

    @Test
    public void negativeVersionControlTest()
    {
        randomTestParamater = App.initializeRandomArray(2, 100);
        assertNull(App.randomNumberVersionNaming(
            randomTestParamater,
            new ArrayList<String>(Arrays.asList(new String[]{"Deneme1", "Deneme2"})),
            -5.0f,
            '_'));
    }

    //TODO: Parameter test can be implemented.
    @Test
    public void seperatorCharControlTest()
    {
        randomTestParamater = App.initializeRandomArray(2, 100);
        assertNull(App.randomNumberVersionNaming(
            randomTestParamater,
            new ArrayList<String>(Arrays.asList(new String[]{"Deneme1", "Deneme2"})),
            1.0f,
            '\0'));
    }

    @Test
    public void equalityControlTest()
    {
        //initializeRandomArray(5, 100);
        assertEquals(
            new ArrayList<String>(Arrays.asList(new String[]{"Deneme14_1.5", "Deneme884_1.5", "Deneme54_1.5"})),
            App.randomNumberVersionNaming(
                new ArrayList<Integer>(Arrays.asList(new Integer[]{14, 884, 54})),
                new ArrayList<String>(Arrays.asList(new String[]{"Deneme", "Deneme", "Deneme"})),
                1.5f,
                '_'
            ));
    }
}
