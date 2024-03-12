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

        if(seperatorChar == '\0' || seperatorChar == '\n' || seperatorChar == '\t')
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
                java.util.ArrayList<String> stringtList = new java.util.ArrayList<>();
                while (sc1.hasNext())
                {
                    String value = sc1.next().replaceAll("\\s","");
                    stringtList.add(value);
                }
                System.out.println(stringtList);

                String input2 = req.queryParams("input2");
                float version = 1.0f;
                try {
                    version = Float.parseFloat(input2);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                String input3 = req.queryParams("input3");
                char seperatorChar = input3.charAt(0);

                ArrayList<String> resultList = App.randomNumberVersionNaming(
                                                    initializeRandomArray(stringtList.size(), 100),
                                                    stringtList,
                                                    version,
                                                    seperatorChar);
                
                sc1.close();

                StringBuilder htmlResult = new StringBuilder();
                for(String element : resultList)
                {
                    htmlResult.append(element);
                    htmlResult.append("\n");
                }

                String result = htmlResult.toString();
        
                Map map = new HashMap();
                map.put("result", result);

                return new ModelAndView(map, "compute.mustache");
            }, new MustacheTemplateEngine());


        get("/compute",
            (rq, rs) ->
            {
                String result = "Empty, Uninitialized.";
                
                Map map = new HashMap();
                map.put("result", result);

                return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }
}