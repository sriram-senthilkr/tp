//         case LIST:
//             if (inputArray.length == 1) {
//                 moduleList.printModules(-1, -1); // Print all modules
//             } else {
//                 String[] rangeToPrint = inputArray[1].split("y/| s/", 3);

//                 if (rangeToPrint.length > 3) {
//                     throw new InvalidFormatException("\tTry again in the format: list y/YEAR s/SEM\n"
//                             + "\tTo show all modules, do not enter year and semester");
//                 }

//                 for (String s : inputArray) {
//                     if (s.contains("s/") && !s.contains("y/")) { // Semester specified but year not specified
//                         throw new InvalidFormatException(
//                                 "\tTry again, y/ must not be empty if s/ is not empty. " +
//                                         "To show modules for that semester, please specify the year of study.");
//                     }
//                 }
//                 // Default values
//                 int yearSpecified = 0;
//                 int semesterSpecified = 0;
//                 try {
//                     yearSpecified = Integer.parseInt(rangeToPrint[1]);
//                 } catch (NumberFormatException e) {
//                     throw new InvalidFormatException("\tYear must be specified as an integer!");
//                 }
                
//                 if (yearSpecified < 1 || yearSpecified > 4) {
//                     throw new InvalidYearException("\tYear must be within 1 to 4!");
//                 }

//                 if (rangeToPrint.length == 2) { // rangeToPrint = ["","1"]
//                     moduleList.printModules(yearSpecified, -1); // Print all modules for the year
//                 } else if (rangeToPrint.length == 3) {

//                     if (inputArray[1].contains("s/")) {
//                         try {
//                             semesterSpecified = Integer.parseInt(rangeToPrint[2]);
//                         } catch (NumberFormatException e) {
//                             throw new InvalidFormatException("\tSemester must be specified as an integer!");
//                         }
//                         if (semesterSpecified != 1 && semesterSpecified != 2) {
//                             throw new InvalidFormatException("\tSemester must be 1 or 2!");
//                         }
//                         moduleList.printModules(yearSpecified, semesterSpecified);
//                     }
//                 } else {
//                     throw new InvalidFormatException("Format is wrong.Type \"help\"for a list of accepted commands");
//                 }
//             }
//             break;

