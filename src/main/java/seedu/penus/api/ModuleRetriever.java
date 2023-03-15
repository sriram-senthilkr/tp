package seedu.penus.api;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ModuleRetriever {
    public static JSONObject moduleInfo;

    public static void getData(String module) {
        try {
            //Public API:
            //https://api.nusmods.com/v2/2022-2023/modules/<module_code>.json

            module = module.toUpperCase();

            URL url = new URL("https://api.nusmods.com/v2/2022-2023/modules/" + module + ".json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode + " NO SUCH MODULE WAS FOUND");
            } else {
                Scanner scanner = new Scanner(url.openStream());
                String informationString = scanner.nextLine();

                //Close scanner
                scanner.close();

                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parser = new JSONParser();
                Object obj =  parser.parse(String.valueOf(informationString));
                JSONArray dataObject = new JSONArray();
                dataObject.add(obj);

                //Get the first JSON object in the JSON array
                moduleInfo = (JSONObject) dataObject.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printPrerequisite(){
        String prereq = (String) moduleInfo.get("prerequisite");
        prereq = prereq.replace("if undertaking an Undergraduate Degree then", "")
                .replace("If undertaking an Undergraduate Degree THEN ", "")
                .replace("\\t", "")
                .replace("\\nthen\\n", " then")
                //.replace("or\\n", "or")
                .replace("\\n", " ")
                .replace("( ", "\\n(")
                .replace(" )", ")\\n");
        System.out.println(prereq);
    }

    public static void printPreclusion(){
        String preclusion = (String) moduleInfo.get("preclusion");
        preclusion = preclusion.replace("if undertaking an Undergraduate Degree then", "")
                .replace("If undertaking an Undergraduate Degree THEN ", "")
                .replace("\\t", "")
                .replace("\\nthen\\n", " then")
                //.replace("or\\n", "or")
                .replace("\\n", " ")
                .replace("( ", "\\n(")
                .replace(" )", ")\\n");
        System.out.println(preclusion);
    }

    public static void printDescription(){
        String description = (String) moduleInfo.get("description");
        System.out.println(description);
    }

    public static void printTitle(){
        String title = (String) moduleInfo.get("title");
        System.out.println(title);
    }

    public static void printModuleCredit(){
        String moduleCredit = (String) moduleInfo.get("moduleCredit");
        System.out.println(moduleCredit);
    }

    public static void printPrereqRule(){
        String prereqRule = (String) moduleInfo.get("prerequisiteRule");
        System.out.println(prereqRule);
    }

}
