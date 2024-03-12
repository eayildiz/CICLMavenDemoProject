import java.util.ArrayList;
import java.util.ListIterator;

public class App
{
    /**
     * @param randomNumbers
     * @param stringInput
     * @param version
     * @param seperatorChar
     * @return
     */
    public static ArrayList<String> randomNumberVersionNaming(ArrayList<Integer> randomNumbers, ArrayList<String> stringInput, float version, char seperatorChar)
    {
        if(randomNumbers == null || stringInput == null)
        {
            return null;
        }
        
        if(randomNumbers.size() != stringInput.size())
        {
            return  null;
        }

        if(version < 0)
        {
            return null;
        }

        ListIterator<Integer> randomNumIterator =  randomNumbers.listIterator();
        ListIterator<String> stringIterator =  stringInput.listIterator();

        while(randomNumIterator.hasNext() && stringIterator.hasNext())
        {
            String string = stringIterator.next() + randomNumIterator.next() + seperatorChar + version;
            stringIterator.set(string);
        }

        return stringInput;
    }
}