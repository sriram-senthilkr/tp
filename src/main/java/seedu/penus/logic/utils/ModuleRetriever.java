package seedu.penus.logic.utils;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ModuleRetriever {

    public static JSONObject moduleInfo2223;
    public static JSONObject moduleInfo2122;

    public static void getData2223(String module) {
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
                throw new RuntimeException("HttpResponseCode: " + responseCode + " NO SUCH MODULE WAS FOUND");
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
            e.printStackTrace();
        }
    }

    public static void getData2122(String module) {
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
                throw new RuntimeException("HttpResponseCode: " + responseCode + " NO SUCH MODULE WAS FOUND");
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
            e.printStackTrace();
        }
    }

    private static String getPrerequisite(String module) {
        getData2122(module);
        return (String) moduleInfo2122.get("prerequisite");
    }

    private static String getDescription(String module) {
        getData2223(module);
        return (String) moduleInfo2223.get("description");
    }

    public static String getTitle(String module) {
        getData2223(module);
        return (String) moduleInfo2223.get("title");
    }

    public static String getModuleCredit(String module) {
        getData2223(module);
        return (String) moduleInfo2223.get("moduleCredit");
    }

    private static Boolean getSUstatus(String module) {
        getData2223(module);
        JSONObject attributes = (JSONObject) moduleInfo2223.get("attributes");
        Boolean suStatus = (Boolean) attributes.get("su");

        return suStatus == null;
    }

    public static String getDetails(String module) {
        String title = "\t" + getTitle(module);
        String description = "\t" + getDescription(module);
        String prereqs = "\tPre-Requisites: " + getPrerequisite(module);
        String credits = "\tMCs: " + getModuleCredit(module);

        boolean suStatus = getSUstatus(module);
        String suStatusDescription;
        if (suStatus) {
            suStatusDescription = "\tModule can be SU-ed.";
        } else {
            suStatusDescription = "\tModule cannot be SU-ed.";
        }

        return title + description + prereqs + credits + suStatusDescription;
    }

    // public static void printPrerequisite(String module) {
    //     String[] messagePacket = {retrievePrerequisite(module)};
    //     Ui.printMessage(messagePacket);
    // }

    // public static void printDescription(String module) {
    //     String description = "\t" + retrieveDescription(module);

    //     String[] messagePacket = { description };
    //     Ui.printMessage(messagePacket);
    // }

    // public static void printTitle(String module) {
    //     String title = "\t" + retrieveTitle(module);

    //     String[] messagePacket = { title };
    //     Ui.printMessage(messagePacket);
    // }

    // public static void printModuleCredit(String module) {
    //     String moduleCredit = "\t" + retrieveModuleCredit(module) + " MCs";
    //     String[] messagePacket = {moduleCredit};
    //     Ui.printMessage(messagePacket);
    // }

    // public static void printSUstatus(String module) {
    //     Boolean suStatus = retrieveSUstatus(module);
    //     String suStatusDescription;
    //     if (suStatus) {
    //         suStatusDescription = "\tModule can be SU-ed.";
    //     } else {
    //         suStatusDescription = "\tModule cannot be SU-ed.";
    //     }


    //     String[] messagePacket = {suStatusDescription};
    //     Ui.printMessage(messagePacket);
    // }

    // public static void printDetails(String module) {
    //     String moduleTitle = "\t" + retrieveTitle(module);
    //     String moduleDescription = "\t" + retrieveDescription(module);
    //     String modulePrereqs = "\tPre-Requisites: " + retrievePrerequisite(module);
    //     String moduleCredits = "\tMCs: " + retrieveModuleCredit(module);

    //     boolean suStatus = retrieveSUstatus(module);
    //     String suStatusDescription;
    //     if (suStatus) {
    //         suStatusDescription = "\tModule can be SU-ed.";
    //     } else {
    //         suStatusDescription = "\tModule cannot be SU-ed.";
    //     }

    //     String[] messagePacket = {moduleTitle, moduleDescription, modulePrereqs, moduleCredits, suStatusDescription};
    //     Ui.printMessage(messagePacket);
    // }
}
