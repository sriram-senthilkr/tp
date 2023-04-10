package seedu.penus.logic.utils;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import seedu.penus.common.exceptions.InvalidModuleAPIException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.NoInternetException;


// test comment
public class ModuleRetriever {
    public static JSONObject moduleInfo2223;
    public static JSONObject moduleInfo2122;
    public static void connectionChecker() throws NoInternetException {
        try {
            // Public API:
            // https://api.nusmods.com/v2/2022-2023/modules/<module_code>.json


            URL url = new URL("https://api.nusmods.com/v2/2022-2023/modules/CS1231.json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new NoInternetException();
        }
    }

    public static void getData2223(String module) throws InvalidModuleAPIException{
        try {
            // Public API:
            // https://api.nusmods.com/v2/2022-2023/modules/<module_code>.json

            module = module.toUpperCase();

            URL url = new URL("https://api.nusmods.com/v2/2022-2023/modules/" + module + ".json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new InvalidModuleAPIException();
            } else {
                Scanner scanner = new Scanner(url.openStream());
                String informationString = scanner.nextLine();

                // Close scanner
                scanner.close();

                // JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(String.valueOf(informationString));
                JSONArray dataObject = new JSONArray();
                dataObject.add(obj);

                // Get the first JSON object in the JSON array
                moduleInfo2223 = (JSONObject) dataObject.get(0);
            }
        } catch (Exception e) {
            moduleInfo2122 = null;
        }
    }

    public static void getData2122(String module) throws InvalidModuleAPIException {
        try {
            // Public API:
            // https://api.nusmods.com/v2/2021-2022/modules/<module_code>.json

            module = module.toUpperCase();

            URL url = new URL("https://api.nusmods.com/v2/2021-2022/modules/" + module + ".json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new InvalidModuleAPIException();
            } else {
                Scanner scanner = new Scanner(url.openStream());
                String informationString = scanner.nextLine();

                // Close scanner
                scanner.close();

                // JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(String.valueOf(informationString));
                JSONArray dataObject = new JSONArray();
                dataObject.add(obj);

                // Get the first JSON object in the JSON array
                moduleInfo2122 = (JSONObject) dataObject.get(0);

                //test code
            }
        } catch (Exception e) {
            moduleInfo2122 = null;
        }
    }

    public static String getPrerequisite(String module) {
        try {
            getData2122(module);
            return (String) moduleInfo2122.get("prerequisite");
        } catch (InvalidModuleAPIException e) {
            return null;
        }
    }

    public static String getDescription(String module) {
        try {
            getData2223(module);
            return (String) moduleInfo2223.get("description");
        } catch (InvalidModuleAPIException e) {
            return null;
        }
    }

    public static String getTitle2223(String module) {
        try {
            getData2223(module);
            return (String) moduleInfo2223.get("title");
        } catch (InvalidModuleAPIException e) {
            return null;
        }

    }

    public static String getModuleCredit2223(String module) {
        try {
            getData2223(module);
            return (String) moduleInfo2223.get("moduleCredit");
        } catch (InvalidModuleAPIException e) {
            return null;
        }
    }

    public static String getTitle2122(String module) {
        try {
            getData2122(module);
            return (String) moduleInfo2122.get("title");
        } catch (InvalidModuleAPIException e) {
            return null;
        }
    }

    public static String getModuleCredit2122(String module) {
        //Temporary fix, will make code cleaner if I can think of a better solution
        if (module.equals("ES2631")) {
            return "4";
        }
        try {
            getData2122(module);
            return (String) moduleInfo2122.get("moduleCredit");
        } catch (InvalidModuleAPIException e) {
            return null;
        }
    }

    public static Boolean getSUstatus(String module) {
        try {
            getData2223(module);
            JSONObject attributes = (JSONObject) moduleInfo2223.get("attributes");
            Boolean suStatus = (Boolean) attributes.get("su");

            return suStatus != null;
        } catch (InvalidModuleAPIException e) {
            return null;
        }
    }

    public static Boolean isValidMod(String module) throws InvalidModuleException {
        try {
            moduleInfo2223 = null;
            getData2223(module);
        } catch (InvalidModuleAPIException e) {
            throw new InvalidModuleException();
        }
        return moduleInfo2223 != null;

    }
}
