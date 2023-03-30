package seedu.penus.logic.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DetailsCompilerTest {

    private DetailsCompiler details;
    @Test
    void getDetailsForCS2113() {
        String ModuleCode = "CS2113";
        String ActualOutput = details.getDetails(ModuleCode);
        String ExpectedOutput = "Software Engineering & Object-Oriented Programming\n\tThis module introduces the " +
                "necessary skills for systematic and rigorous development of software systems. It covers " +
                "requirements, design, implementation, quality assurance, and project management aspects of " +
                "small-to-medium size multi-person software projects. The module uses the Object Oriented Programming" +
                " paradigm. Students of this module will receive hands-on practice of tools commonly used in the " +
                "industry, such as test automation tools, build automation tools, and code revisioning tools will be " +
                "covered.\n\tPre-Requisites: CS2040C or ((CS2030 or its equivalent) and CS2040/S)\n\tMCs: 4\n\tModule" +
                " cannot be SU-ed.";

        assertEquals(ExpectedOutput, ActualOutput);
    }
    @Test
    void getDetailsForInvalidModule() {
        String ModuleCode = "INVALID MODULE";
        String ActualOutput = details.getDetails(ModuleCode);
        String ExpectedOutput = "This information is not available";

        assertEquals(ExpectedOutput, ActualOutput);
    }

    @Test
    void getDetailsForNull() {
        String ModuleCode = null;
        String ActualOutput = details.getDetails(ModuleCode);
        String ExpectedOutput = "This information is not available";

        assertEquals(ExpectedOutput, ActualOutput);
    }
}
