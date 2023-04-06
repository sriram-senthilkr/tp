package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.logic.utils.MCsTaken;
import seedu.penus.logic.utils.ModuleRetriever;
import seedu.penus.model.ModelManager;
import seedu.penus.model.ModuleList;
import seedu.penus.ui.Ui;

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

    public int getNumberOfCoreMCsTaken (ModelManager model) {
        int numberOfElectiveMCs = 0;
        List<String> takenCoreModsList = getTakenCoreModsList(model);
        for (String moduleCode : takenCoreModsList) {
            numberOfElectiveMCs = numberOfElectiveMCs +
                                  Integer.parseInt(ModuleRetriever.getModuleCredit2223(moduleCode));
        }
        return numberOfElectiveMCs;
    }

    public String moduleCodeToString(String moduleCode) {
        return moduleCode + " "+ ModuleRetriever.getTitle2223(moduleCode)
                    + " MCs: " + ModuleRetriever.getModuleCredit2223(moduleCode);
    }


    @Override
    public CommandResult execute(ModelManager model) throws PenusException {
        if (model.getUserName().equals("")) {
            throw new InvalidCommandException(
                "Please initialise first with the init command!"
            );
        }
        Ui.showLoadingAnimation();
        StringBuilder sb = new StringBuilder();
        List<String> takenCoreModsList = getTakenCoreModsList(model);
        List<String> untakenCoreModsList = getUntakenCoreModsList(model);
        int totalMCsTaken = MCsTaken.numberOfMcsTaken(model.getModuleList().modules);
        int coreMCsTaken = getNumberOfCoreMCsTaken(model);
        int electiveMCsTaken = totalMCsTaken - coreMCsTaken;
        List<String> messageArray = new ArrayList<>();
        messageArray.add("-------------------------- User --------------------------");
        messageArray.add("\tUser: " + model.getUserName());
        messageArray.add("\tCourse: " + model.getUserCourse());
        messageArray.add("\t------------------- Core Modules Taken --------------------");
        for (String s : takenCoreModsList){
            messageArray.add("\t" + moduleCodeToString(s));
        }
        messageArray.add("\t----------------- Core Modules Not Taken ------------------");
        if (model.getGEC().equals("")){
            messageArray.add("\tGECXXXX");
        }
        if (model.getGESS().equals("")){
            messageArray.add("\tGESSXXXX");
        }
        if (model.getGEN().equals("")){
            messageArray.add("\tGENXXXX");
        }
        for (String s : untakenCoreModsList){
            messageArray.add("\t" + moduleCodeToString(s));
        }
        messageArray.add("\t------------------------ MCs Status -----------------------");
        messageArray.add("\tCore Modules MCs Taken: " + Integer.toString(coreMCsTaken));
        messageArray.add("\tElective MCs Taken: " + Integer.toString(electiveMCsTaken));
        messageArray.add("\tTotal MCs Taken: " + Integer.toString(totalMCsTaken) + "/160");

        for (String s : messageArray){
            sb.append(s).append("\n");
        }
        
        String message = sb.toString();
        Ui.stopLoadingAnimation();
        return new CommandResult(message, false);
    }
}
