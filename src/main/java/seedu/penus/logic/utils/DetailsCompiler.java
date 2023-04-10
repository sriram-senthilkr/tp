package seedu.penus.logic.utils;

public class DetailsCompiler extends ModuleRetriever{
    protected String moduleCode;

    /**
     * Retrieves details of given module code, and displays error message if information is not available
     * @param module string
     */
    public static String getDetails(String module) {
        String title;
        String description;
        String prereqs;
        String credits;
        String suStatusDescription;

        try {
            if (!isValidMod(module)) {
                return "This module code is invalid. Try again.";
            };
        } catch (Exception e) {
            return "This module code is invalid. Try again.";
        }

        try {
            title = getTitle2223(module);
        } catch (Exception e) {
            title = "\tTitle is not available";
        }
        try {
            description = "\t" + getDescription(module);
        } catch (Exception e) {
            description = "\tDescription is not available";
        }
        try {
            prereqs = "\tPre-Requisites: " + getPrerequisite(module);
        } catch (Exception e) {
            prereqs = "\tPre-Requisites information is not available";
        }
        try {
            credits = "\tMCs: " + getModuleCredit2223(module);
        } catch (Exception e) {
            credits = "\tModular Credits information is not available";
        }
        try {
            boolean suStatus = getSUstatus(module);
            if (suStatus) {
                suStatusDescription = "\tModule can be SU-ed.";
            } else {
                suStatusDescription = "\tModule cannot be SU-ed.";
            }
        } catch (Exception e) {
            suStatusDescription = "\tSU information is not available";
        }

        return title + "\n" + description + "\n" + prereqs + "\n" + credits + "\n" + suStatusDescription;
    }
}
