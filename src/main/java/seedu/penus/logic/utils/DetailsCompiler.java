package seedu.penus.logic.utils;

public class DetailsCompiler extends ModuleRetriever{
    protected String moduleCode;

    public static String getDetails(String module) {
        try {
            String title = getTitle2223(module);
            String description = "\t" + getDescription(module);
            String prereqs = "\tPre-Requisites: " + getPrerequisite(module);
            String credits = "\tMCs: " + getModuleCredit2223(module);

            boolean suStatus = getSUstatus(module);
            String suStatusDescription;
            if (suStatus) {
                suStatusDescription = "\tModule can be SU-ed.";
            } else {
                suStatusDescription = "\tModule cannot be SU-ed.";
            }
            return title + "\n" + description + "\n" + prereqs + "\n" + credits + "\n" + suStatusDescription;
        } catch (Exception e) {
            return "This information is not available";
        }
    }
}
