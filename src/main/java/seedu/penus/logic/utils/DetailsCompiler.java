package seedu.penus.logic.utils;

public class DetailsCompiler extends ModuleRetriever{
    protected String moduleCode;

    public static String getDetails(String module) {
//        try {
//            String title = getTitle2223(module);
//            String description = "\t" + getDescription(module);
//            String prereqs = "\tPre-Requisites: " + getPrerequisite(module);
//            String credits = "\tMCs: " + getModuleCredit2223(module);
//
//            boolean suStatus = getSUstatus(module);
//            String suStatusDescription;
//            if (suStatus) {
//                suStatusDescription = "\tModule can be SU-ed.";
//            } else {
//                suStatusDescription = "\tModule cannot be SU-ed.";
//            }
//            return title + "\n" + description + "\n" + prereqs + "\n" + credits + "\n" + suStatusDescription;
//        } catch (Exception e) {
//            return ": This information is not available";
//        }

        String title;
        String description;
        String prereqs;
        String credits;
        String suStatusDescription;

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
