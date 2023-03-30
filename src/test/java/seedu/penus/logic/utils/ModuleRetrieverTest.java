package seedu.penus.logic.utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleRetrieverTest {

    @Test
    void testGetDescription() {
        String module = "CS2103T";
        String expectedDescription =
                "This module introduces the necessary conceptual and analytical tools for " +
                "systematic and rigorous development of software systems." +
                " It covers four main areas of software development, " +
                "namely object-oriented system analysis, object-oriented system" +
                " modelling and design, implementation, and testing, " +
                "with emphasis on system modelling and design and implementation of " +
                "software modules that work cooperatively to " +
                "fulfill the requirements of the system. Tools and techniques for software development," +
                " such as Unified Modelling Language (UML)," +
                " program specification, and testing methods, will be taught." +
                " Major software engineering issues such as modularisation criteria, " +
                "program correctness, and software quality will also be covered.";

        assertEquals(expectedDescription, ModuleRetriever.getDescription(module));
    }

    @Test
    void testGetPrerequisite() {
        String module = "CS2103T";
        String expectedPrerequisite = "For SoC students only. (CS1020 or its equivalent) or CS2020 or" +
                " ((CS2030 or its equivalent) and (CS2040 or its equivalent))";

        assertEquals(expectedPrerequisite, ModuleRetriever.getPrerequisite(module));
    }

    @Test
    void testGetTitle2223() {
        String module = "CS2103T";
        String expectedTitle = "Software Engineering";

        assertEquals(expectedTitle, ModuleRetriever.getTitle2223(module));
    }

    @Test
    void testGetModuleCredit2223() {
        String module = "CS2103T";
        String expectedModuleCredit = "4";

        assertEquals(expectedModuleCredit, ModuleRetriever.getModuleCredit2223(module));
    }

    @Test
    void testGetTitle2122() {
        String module = "CS2103T";
        String expectedTitle = "Software Engineering";

        assertEquals(expectedTitle, ModuleRetriever.getTitle2122(module));
    }

    @Test
    void testGetModuleCredit2122() {
        String module = "CS2103T";
        String expectedModuleCredit = "4";

        assertEquals(expectedModuleCredit, ModuleRetriever.getModuleCredit2122(module));
    }

    @Test
    void testGetSUstatus() {
        String module = "PF1101";
        Boolean expectedSUstatus = true;

        assertEquals(expectedSUstatus, ModuleRetriever.getSUstatus(module));
    }

}

