import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class AppTest
{
    private ArrayList<Integer> randomTestParamater;

    private void initializeRandomArray(int length, int limit)
    {
        randomTestParamater = new ArrayList<>();
        for (int i = 0; i < length; i++)
        {
            randomTestParamater.add( (int) (Math.random() * limit));
        }
    }

    @Test
    public void nullParameterControlTest()
    {
        initializeRandomArray(5, 100);
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
        initializeRandomArray(5, 100);
        assertNull(App.randomNumberVersionNaming(
            randomTestParamater,
            new ArrayList<String>(Arrays.asList(new String[]{"Deneme1", "Deneme2"})),
            1.0f,
            '_'
            ));
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
