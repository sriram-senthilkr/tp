package seedu.penus.logic.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DetailsCompilerTest {

    private DetailsCompiler details;
    @Test
    void getDetailsForCS2113() {
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
    void getDetailsForInvalidModulePreRequisities() {
        String moduleCode = "INVALID MODULE";
        String actualOutput = details.getDetails(moduleCode);
        String expectedOutput = "Programming Methodology\n" +
                "\tThis module introduces the fundamental concepts of problem solving by computing and programming " +
                "using an imperative programming language. It is the first and foremost introductory course to " +
                "computing. Topics covered include computational thinking and computational problem solving, " +
                "designing and specifying an algorithm, basic problem formulation and problem solving approaches, " +
                "program development, coding, testing and debugging, fundamental programming constructs (variables, " +
                "types, expressions, assignments, functions, control structures, etc.), fundamental data structures " +
                "(arrays, strings, composite data types), basic sorting, and recursion.\n" +
                "\tPre-Requisites information is not available\n" +
                "\tMCs: 4\n" +
                "\tModule can be SU-ed.";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getDetailsForNull() {
        String moduleCode = null;
        String actualOutput = details.getDetails(moduleCode);
        String expectedOutput = ": This information is not available";

        assertEquals(expectedOutput, actualOutput);
    }
}
