import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;


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

    static ArrayList<Integer> initializeRandomArray(int length, int limit)
    {
        ArrayList<Integer> randomTestParamater = new ArrayList<>();
        for (int i = 0; i < length; i++)
        {
            randomTestParamater.add( (int) (Math.random() * limit));
        }

        return randomTestParamater;
    }

    static int getHerokuAssignedPort()
    {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null)
        {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args)
    {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) ->
            {
                //System.out.println(req.queryParams("input1"));
                //System.out.println(req.queryParams("input2"));

                String input1 = req.queryParams("input1");
                java.util.Scanner sc1 = new java.util.Scanner(input1);
                sc1.useDelimiter("[;\r\n]+");
                java.util.ArrayList<String> inputList = new java.util.ArrayList<>();
                while (sc1.hasNext())
                {
                    String value = sc1.next().replaceAll("\\s","");
                    inputList.add(value);
                }
                System.out.println(inputList);

                ArrayList<String> result = App.randomNumberVersionNaming(initializeRandomArray(5, 100), inputList, 1.0f, '_');
                
                sc1.close();

                return new ModelAndView(result, "compute.mustache");
            }, new MustacheTemplateEngine());


        get("/compute",
            (rq, rs) ->
            {
                ArrayList<String> result = new ArrayList<>();
                result.add("Empty, Initialized.");
                return new ModelAndView(result, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }
}