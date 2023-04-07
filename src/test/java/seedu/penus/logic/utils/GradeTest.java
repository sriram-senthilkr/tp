package seedu.penus.logic.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.model.Module;
import seedu.penus.model.ModuleList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GradeTest {
    static final String NO_OVERALL_CAP = "Overall CAP : 0.00\n";
    static final String PERFECT_OVERALL_CAP = "Overall CAP : 5.00\n";
    static final String PERFECT_SEM_CAP = "Semester CAP : 5.00\n";
    static final String NO_SEM_CAP = "Semester CAP : 0.00\n";
    private ModuleList moduleList;
    private List<String[]> semArray;

    @BeforeEach
    public void setUp() {
        moduleList = new ModuleList();
        semArray = new ArrayList<>();
    }
    @Test
    void getGradePoint_throwsInvalidGradeException_success() {
        assertThrows(InvalidGradeException.class, () -> {
            Grade.getGradePoint("SU");
        });
    }
    @Test
    void getGradePoint_grade_success() throws InvalidGradeException {
        assertEquals(0.0, Grade.getGradePoint("F"));
    }

    @Test
    void getGradePoint_gradeAMinus_success() throws InvalidGradeException {
        assertEquals(4.5, Grade.getGradePoint("A-"));
    }

    @Test
    void getGradePoint_gradeBMinus_success() throws InvalidGradeException {
        assertEquals(3.0, Grade.getGradePoint("B-"));
    }

    @Test
    void getGradePoint_gradeCPlus_success() throws InvalidGradeException {
        assertEquals(2.5, Grade.getGradePoint("C+"));
    }

    @Test
    void getGradePoint_gradeC_success() throws InvalidGradeException {
        assertEquals(2.0, Grade.getGradePoint("C"));
    }

    @Test
    void getGradePoint_gradeDPlus_success() throws InvalidGradeException {
        assertEquals(1.5, Grade.getGradePoint("D+"));
    }

    @Test
    void getGradePoint_gradeD_success() throws InvalidGradeException {
        assertEquals(1.0, Grade.getGradePoint("D"));
    }

    @Test
    void isValid_lowercaseGrade_success() {
        assertEquals(true, Grade.isValid("a+"));
    }
    @Test
    void isValid_uppercaseGrade_success() {
        assertEquals(true, Grade.isValid("S"));
    }

    @Test
    void calculateOverallCAP_noSU_success() throws
            InvalidGradeException {
        moduleList.addModule(new Module("CS1010", 1, 1, "B+"));
        moduleList.addModule(new Module("CS2113", 2, 2, "A+"));
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateOverallCAP(moduleList.getModuleList())));
        assertEquals(4.50, cap);
    }
    @Test
    void calculateOverallCAP_noSUGradeF_success() throws
            InvalidGradeException {
        moduleList.addModule(new Module("CS1010", 1, 1, "F"));
        moduleList.addModule(new Module("CS2113", 2, 2, "F"));
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateOverallCAP(moduleList.getModuleList())));
        assertEquals(0.00, cap);
    }
    @Test
    void calculateOverallCAP_allSU_success() throws
            InvalidGradeException {
        moduleList.addModule(new Module("CS1010", 1, 1, "S"));
        moduleList.addModule(new Module("PF1101", 2, 2, "U"));
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateOverallCAP(moduleList.getModuleList())));
        assertEquals(5.00, cap);
    }
    @Test
    void calculateOverallCAP_someSUGradeB_success() throws
            InvalidGradeException {
        moduleList.addModule(new Module("CS1010", 1, 1, "B"));
        moduleList.addModule(new Module("PF1101", 2, 2, "U"));
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateOverallCAP(moduleList.getModuleList())));
        assertEquals(3.50, cap);
    }
    @Test
    void calculateOverallCAP_someSUGradeF_success() throws
            InvalidGradeException {
        moduleList.addModule(new Module("CS1010", 1, 1, "F"));
        moduleList.addModule(new Module("PF1101", 2, 2, "U"));
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateOverallCAP(moduleList.getModuleList())));
        assertEquals(0.00, cap);
    }
    @Test
    void calculateOverallCAP_takenAndPlanGradeF_success() throws
            InvalidGradeException {
        moduleList.addModule(new Module("CS1010", 1, 1, "F"));
        moduleList.addModule(new Module("PF1101", 2, 2));
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateOverallCAP(moduleList.getModuleList())));
        assertEquals(0.00, cap);
    }
    @Test
    void calculateOverallCAP_takenAndPlanGradeSU_success() throws
            InvalidGradeException {
        moduleList.addModule(new Module("CS1010", 1, 1, "CU"));
        moduleList.addModule(new Module("PF1101", 2, 2));
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateOverallCAP(moduleList.getModuleList())));
        assertEquals(5.00, cap);
    }
    @Test
    void calculateOverallCAP_takenAndPlanGradeB_success() throws
            InvalidGradeException {
        moduleList.addModule(new Module("CS1010", 1, 1, "B"));
        moduleList.addModule(new Module("PF1101", 2, 2));
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateOverallCAP(moduleList.getModuleList())));
        assertEquals(3.50, cap);
    }
    @Test
    void calculateOverallCAP_planOnly_success() throws
            InvalidGradeException {
        moduleList.addModule(new Module("EG2501", 2, 1));
        moduleList.addModule(new Module("CG2023", 2, 2));
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateOverallCAP(moduleList.getModuleList())));
        assertEquals(0.00, cap);
    }
    @Test
    void calculateSemCAP_noSU_success() throws InvalidGradeException {
        Module module1 = new Module("CS1010", 1, 1, "A+");
        Module module2 = new Module("CG1111A", 1, 1, "A+");
        Module module3 = new Module("CG2111A", 1, 1, "B+");
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        semArray.add(new String[] { module3.getCode(), module3.getGrade()});
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateSemCAP(semArray)));
        assertEquals(4.67, cap);
    }
    @Test
    void calculateSemCAP_noSUAndFGrade_success() throws InvalidGradeException {
        Module module1 = new Module("CS1010", 1, 1, "F");
        Module module2 = new Module("CG1111A", 1, 1, "F");
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateSemCAP(semArray)));
        assertEquals(0.00, cap);
    }

    @Test
    void calculateSemCAP_someSU_success() throws InvalidGradeException {
        Module module1 = new Module("CS1010", 1, 1, "U");
        Module module2 = new Module("PF1101", 1, 1, "A");
        Module module3 = new Module("PF1101", 1, 1, "B+");
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        semArray.add(new String[] { module3.getCode(), module3.getGrade()});
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateSemCAP(semArray)));
        assertEquals(4.50, cap);
    }
    @Test
    void calculateSemCAP_someSUGradeF_success() throws InvalidGradeException {
        Module module1 = new Module("CS1010", 1, 1, "CU");
        Module module2 = new Module("CG1111A", 1, 1, "U");
        Module module3 = new Module("PF1101", 1, 1, "F");
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        semArray.add(new String[] { module3.getCode(), module3.getGrade()});
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateSemCAP(semArray)));
        assertEquals(0.00, cap);
    }
    @Test
    void calculateSemCAP_allSU_success() throws InvalidGradeException {
        Module module1 = new Module("CS1010", 1, 1, "U");
        Module module2 = new Module("CG1111A", 1, 1, "U");
        Module module3 = new Module("PF1101", 1, 1, "U");
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        semArray.add(new String[] { module3.getCode(), module3.getGrade()});
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateSemCAP(semArray)));
        assertEquals(5.00, cap);
    }
    @Test
    void calculateSemCAP_takenOnly_success() throws
            InvalidGradeException {
        Module module1 = new Module("EG2501", 2, 1, "A");
        Module module2 = new Module("CG2111A", 2, 1, "B+");
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateSemCAP(semArray)));
        assertEquals(4.50, cap);
    }
    @Test
    void calculateSemCAP_takenAndPlanGradeA_success() throws
            InvalidGradeException {
        Module module1 = new Module("EG2501", 2, 1, "A");
        Module module2 = new Module("CG2111A", 2, 1);
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateSemCAP(semArray)));
        assertEquals(5.00, cap);
    }
    @Test
    void calculateSemCAP_takenAndPlanGradeF_success() throws
            InvalidGradeException {
        Module module1 = new Module("EG2501", 2, 1, "F");
        Module module2 = new Module("CG2111A", 2, 1);
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateSemCAP(semArray)));
        assertEquals(0.00, cap);
    }
    @Test
    void calculateSemCAP_takenAndPlanSU_success() throws
            InvalidGradeException {
        Module module1 = new Module("EG2501", 2, 1, "S");
        Module module2 = new Module("CG2111A", 2, 1);
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateSemCAP(semArray)));
        assertEquals(0.00, cap);
    }
    @Test
    void calculateSemCAP_planOnly_success() throws InvalidGradeException {
        Module module1 = new Module("EG2501", 2, 1);
        Module module2 = new Module("CG2111A", 2, 1);
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        double cap = Double.parseDouble(new DecimalFormat("#.##").
                format(Grade.calculateSemCAP(semArray)));
        assertEquals(0.00, cap);
    }
    @Test
    void getOverallCAP_noModsTaken_success() throws InvalidGradeException {
        assertEquals(NO_OVERALL_CAP, Grade.getOverallCAP(moduleList.getModuleList()));
    }
    @Test
    void getOverallCAP_modsTaken_success() throws InvalidGradeException {
        moduleList.addModule(new Module("CS1010", 1, 1, "A+"));
        moduleList.addModule(new Module("PF1101", 2, 2, "U"));
        assertEquals(PERFECT_OVERALL_CAP, Grade.getOverallCAP(moduleList.getModuleList()));
    }
    @Test
    void getOverallCAP_planOnly_success() throws InvalidGradeException {
        moduleList.addModule(new Module("CS1010", 1, 1));
        assertEquals(NO_OVERALL_CAP, Grade.getOverallCAP(moduleList.getModuleList()));
    }
    @Test
    void getSemCAP_noModsTaken_success() throws InvalidGradeException {
        assertEquals(NO_SEM_CAP, Grade.getSemCAP(semArray));
    }
    @Test
    void getSemCAP_modsTaken_success() throws InvalidGradeException {
        Module module1 = new Module("CS1010", 1, 1, "U");
        Module module2 = new Module("CG1111A", 1, 1, "U");
        semArray.add(new String[] { module1.getCode(), module1.getGrade()});
        semArray.add(new String[] { module2.getCode(), module2.getGrade()});
        assertEquals(PERFECT_SEM_CAP, Grade.getSemCAP(semArray));
    }

}
