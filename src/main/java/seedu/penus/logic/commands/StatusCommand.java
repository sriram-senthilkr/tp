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
        if (model.getGEC() != ""){
            takenCoreMods.add(model.getGEC());
        }
        if (model.getGESS() != ""){
            takenCoreMods.add(model.getGESS());
        }
        if (model.getGEN() != ""){
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
        /*
          TODO: implement status
          HOW:
          use model.getUser to retrieve user data
          use model.getXXX (scroll to bottom) to retrieve core mod stuff

          should resource be under models?

          NOTE:
          modelManager should only have simple commands to edit the moduleList

          additional helper functions: create in utils

          printing stuff: edit the MESSAGE, (refer to other commands for example)
          if need more, add a new command to Ui.java

          TO RETURN:
          one string of status in with taken and untaken core mods
          lines separated by /n
          useful to use String.format with "%s"
        */
        StringBuilder sb = new StringBuilder();
        ModuleList moduleList = model.getModuleList();
        List<String> takenCoreModsList = getTakenCoreModsList(model) ;
        List<String> untakenCoreModsList = getUntakenCoreModsList(model);
        List<String> statusList = new ArrayList<>();
        statusList.add("--------- Taken ---------");
        for (String s : takenCoreModsList){
            statusList.add(moduleCodeToString(s));
        }
        statusList.add("--------- Not Taken ---------");
        if (model.getGEC() == ""){
            statusList.add("GECXXXX");
        }
        if (model.getGESS() == ""){
            statusList.add("GESSXXXX");
        }
        if (model.getGEN() == ""){
            statusList.add("GENXXXX");
        }
        for (String s : untakenCoreModsList){
            statusList.add(moduleCodeToString(s));
        }
        statusList.add("MCs Taken: " + Integer.toString(MCsTaken.numberOfMcsTaken(model.getModuleList().modules))
                        + "/160");
        for (String s : statusList){
            sb.append(s).append("\n");
        }
        String message = sb.toString();
        return new CommandResult(message);
    }
}
