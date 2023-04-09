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

    //Test ES2631, exists in 22/23 but not 21/22
    @Test
    void testGetDetailsForNewModule() {
        String moduleCode = "ES2631";
        String actualOutput = details.getDetails(moduleCode);
        String expectedOutput = ": This information is not available";

        assertEquals(expectedOutput, actualOutput);
    }

    // Test ES2531, exists in 21/22 but not 22/23
    @Test
    void testGetDetailsForDiscontinuedModule() {
        String moduleCode = "ES2531";
        String actualOutput = details.getDetails(moduleCode);
        String expectedOutput = "Critical Thinking And Writing\n\tThe challenges of a global " +
                "engineer increasingly call upon engineers to think critically and communicate effectively " +
                "to undertake developmental leadership. This course aims to develop and practise students’ " +
                "critical thinking and writing skills through analysing case studies in engineering leadership, " +
                "constructing complex engineering-related problems and solutions, presenting arguments " +
                "effectively and reflecting on personal leadership development. Relevance to engineering practice " +
                "is emphasized with references to grounded theories of engineering leadership and the seven " +
                "missing basics of engineering education.\n\tPre-Requisites: 1. Students who are required " +
                "to take ES1000 Foundation Academic English and/or ES1103 English for Academic Purposes must " +
                "pass the modules before they are allowed to read this module. \n2. Students who matriculated " +
                "in AY2014/15 and AY2015/16 are to read the cross-listed modules, GEK1549 and GET1021, " +
                "respectively.\n\tMCs: 4\n\tModule can be SU-ed.";

        assertEquals(expectedOutput, actualOutput);
    }
}
