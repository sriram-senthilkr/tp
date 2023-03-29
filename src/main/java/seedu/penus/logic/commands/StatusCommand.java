package seedu.penus.logic.commands;

import seedu.penus.logic.utils.MCsTaken;
import seedu.penus.logic.utils.ModuleRetriever;
import seedu.penus.model.ModelManager;
import seedu.penus.model.ModuleList;

import java.util.ArrayList;
import java.util.List;

public class StatusCommand extends Command {
    public static final String COMMAND_WORD = "status";

    public List<String> getTakenCoreModsList(ModelManager model) {
        List<String> coreMods = model.getCoreModList().get(model.getUserCourse());
        List<String> takenCoreMods = new ArrayList<>();
        ModuleList moduleList = model.getModuleList();
        if (!model.getGEC().equals("")){
            takenCoreMods.add(model.getGEC());
        }
        if (!model.getGESS().equals("")){
            takenCoreMods.add(model.getGESS());
        }
        if (!model.getGEN().equals("")){
            takenCoreMods.add(model.getGEN());
        }
        for (String coreModCode : coreMods) {
            for (int i = 0; i < moduleList.size(); i++) {
                String moduleCode = moduleList.getModule(i).getCode();
                boolean isCurrentUserModuleTaken = moduleList.getModule(i).getStatus().equals("Taken");
                if (coreModCode.equals(moduleCode) && isCurrentUserModuleTaken) {
                    takenCoreMods.add(moduleCode);
                    break;
                }
            }
        }
        return takenCoreMods;
    }

    public List<String> getUntakenCoreModsList(ModelManager model) {
        List<String> coreMods = model.getCoreModList().get(model.getUserCourse());
        List<String> untakenCoreMods = new ArrayList<>();
        ModuleList moduleList = model.getModuleList();
        for (String coreModCode : coreMods) {
            boolean isCoreModTaken = false;
            for (int i = 0; i < moduleList.size(); i++) {
                String moduleCode = moduleList.getModule(i).getCode();
                boolean isCurrentUserModuleTaken = moduleList.getModule(i).getStatus().equals("Taken");
                if (coreModCode.equals(moduleCode) && isCurrentUserModuleTaken) {
                    isCoreModTaken = true;
                    break;
                }
            }
            if (!isCoreModTaken) {
                untakenCoreMods.add(coreModCode);
            }
        }
        return untakenCoreMods;
    }

    public String moduleCodeToString(String moduleCode) {
        return moduleCode + " "+ ModuleRetriever.getTitle(moduleCode)
                    + " MCs: " + ModuleRetriever.getModuleCredit(moduleCode);
    }


    @Override
    public CommandResult execute(ModelManager model) {
        StringBuilder sb = new StringBuilder();
        List<String> takenCoreModsList = getTakenCoreModsList(model) ;
        List<String> untakenCoreModsList = getUntakenCoreModsList(model);
        List<String> messageArray = new ArrayList<>();
        messageArray.add("--------- Taken ---------");
        for (String s : takenCoreModsList){
            messageArray.add(moduleCodeToString(s));
        }
        messageArray.add("--------- Not Taken ---------");
        if (model.getGEC().equals("")){
            messageArray.add("GECXXXX");
        }
        if (model.getGESS().equals("")){
            messageArray.add("GESSXXXX");
        }
        if (model.getGEN().equals("")){
            messageArray.add("GENXXXX");
        }
        for (String s : untakenCoreModsList){
            messageArray.add(moduleCodeToString(s));
        }
        messageArray.add("MCs Taken: " + Integer.toString(MCsTaken.numberOfMcsTaken(model.getModuleList().modules))
                        + "/160");

        for (String s : messageArray){
            sb.append(s).append("\n");
        }

        String message = sb.toString();
        return new CommandResult(message, false);
    }
}
