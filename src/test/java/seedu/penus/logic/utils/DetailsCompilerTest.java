package seedu.penus.logic.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DetailsCompilerTest {

    private DetailsCompiler details;

    @Test
    void testGetDetailsForCS2113() {
        String moduleCode = "CS2113";
        String actualOutput = details.getDetails(moduleCode);
        String expectedOutput = "Software Engineering & Object-Oriented Programming\n\tThis module introduces the " +
                "necessary skills for systematic and rigorous development of software systems. It covers " +
                "requirements, design, implementation, quality assurance, and project management aspects of " +
                "small-to-medium size multi-person software projects. The module uses the Object Oriented Programming" +
                " paradigm. Students of this module will receive hands-on practice of tools commonly used in the " +
                "industry, such as test automation tools, build automation tools, and code revisioning tools will be " +
                "covered.\n\tPre-Requisites: CS2040C or ((CS2030 or its equivalent) and CS2040/S)\n\tMCs: 4\n\tModule" +
                " cannot be SU-ed.";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testGetDetailsForInvalidModule() {
        String moduleCode = "INVALID MODULE";
        String actualOutput = details.getDetails(moduleCode);
        String expectedOutput = "This module code is invalid. Try again.";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testGetDetailsForNull() {
        String moduleCode = null;
        String actualOutput = details.getDetails(moduleCode);
        String expectedOutput = "This module code is invalid. Try again.";
        assertEquals(expectedOutput, actualOutput);
    }

    // Test ES2631, exists in 22/23 but not 21/22
    @Test
    void testGetDetailsForNewModule() {
        String moduleCode = "ES2631";
        String actualOutput = details.getDetails(moduleCode);
        String expectedOutput = "Critique and Communication of Thinking and Design\n\tThis " +
                "module equips students with competencies requiring students to analyze, critique, " +
                "and communicate engineering ideas in a systematic and thoughtful manner. " +
                "Students will be introduced to a reasoning in engineering framework " +
                "(Paul et al., 2019), as well as key principles of effective communication " +
                "in the field of engineering, such as being purpose- and context-conscious " +
                "and audience-centric (Irish & Weiss, 2013). These will be applied to analyze " +
                "engineering ideas in both written and oral communication. Students will also " +
                "engage in a group engineering conceptual design project aimed at promoting " +
                "critical analysis and communication within groups.\n\tPre-Requisites " +
                "information is not available\n\tMCs: 4\n\tModule can be SU-ed.";

        assertEquals(expectedOutput, actualOutput);
    }
}
