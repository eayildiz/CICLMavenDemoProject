import java.util.ArrayList;
import java.util.ListIterator;

public class App
{
    public static ArrayList<String> directStrings(ArrayList<Integer> randomNumbers, ArrayList<String> stringInput, int version, char seperatorChar)
    {
        if(randomNumbers.size() != stringInput.size())
        {
            return  null;
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