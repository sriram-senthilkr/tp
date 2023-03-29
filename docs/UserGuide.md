# 📜 PENUS User Guide

## Introduction

Planning Engineering with NUS (PENUS) is a desktop app that helps engineering students oversee and plan their modules in their university life! It is optimised for use via a Command Line Interface (CLI). For students that can type fast, PENUS can help them plan and track their modules for all four years of their time in university more efficiently.

## Table of Contents 📔
- [Quick Start](#quick-start-⚙️)
- [Features](#features-👾)
    + [Initialisation: `init`](#initialisation-init)
    + [Help: `help`](#help-help)
    + [Add taken modules: `taken`](#add-taken-modules-taken)
    + [Plan untaken modules: `plan`](#plan-untaken-modules-plan)
    + [Remove a module: `remove`](#remove-a-module-remove)
    + [Mark module as taken: `mark`](#mark-module-as-taken-mark)
    + [View modules: `list`](#view-modules-list)
    + [View graduation status: `status`](#view-graduation-status-status)
    + [View module details: `details`](#view-module-details-details)
    + [Exit: `exit`](#exit-the-program-exit)
    + [Saving the data](#saving-the-data)
    + [Editing the data file](#editing-the-data-file)
- [FAQ](#faq-💻)
- [Command Summary](#command-summary-🔑)

## Quick Start ⚙️

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of PENUS from [here](https://github.com/AY2223S2-CS2113-T11-2/tp/releases/download/v2.0/penus.jar).
3. Copy the file to the folder you want to use as the home folder for your PENUS.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar penus.jar` command to run the application.
5. Type the command in the Command Line Interface and press Enter to execute it.
_Some example commands you can try:_
    - `init`:
    - `taken CS2113 y/2 s/2 g/A+`: Adds CS2113 to Year 2 Semester 2 with grade A+.
    - `plan CS2040C y/1 s/2`: Adds CS2040C to Year 1 Semester 2 as untaken
    - `list`: list all modules in the planner with overall CAP
    - `remove CS2113`: Deletes the module CS2113
    - `status`: gets the status of core modules and MCs taken.
    - `exit`: exits the application.
6. Refer to features below for details of each command



## Features 👾

### Initialisation: `init`
Initialise a user to the planner
Format: `init n/[NAME] c/[COURSE_INDEX]`
- COMPULSORY
- 

Example:
```
```
Output:
```
```

### Help: `help`
Display the help page of the program
Format: `help`
Output:
```
```

### Add taken modules: `taken`
Adds a module to the planner as a taken module.
Format: `taken [MODULE_CODE] y/[YEAR] s/[SEMESTER] g/[GRADE]`
- `MODULE_CODE` is compulsory
- 

Example:
```

```
Output:
```
```


### Plan untaken modules: `plan`
Adds a module to the planner as a planned module
Format: `plan [MODULE_CODE] y/[YEAR] s/[SEMESTER]`
- `MODULE_CODE` is compulsory
- 

Example:
```
```
Output:
```
```


### Remove a module: `remove`
Removes a module from the planner
Format: `remove [MODULE_CODE]`
- `MODULE_CODE` is compulsory
- 

Example:
```
remove CS2113
```
Output:
```
```


### Mark module as taken: `mark`
Marks the mod that has been cleared and update its grade.
Format: `mark [MODULE_CODE] g/[GRADE]`
- `MODULE_CODE` is compulsory
- 

Example:
```
mark CG2111A g/A+
```
Output:
```
```

### View modules: `list`
Displays the list of modules in the planner.


### View graduation status: `status`
Displays the status of Core Modules and MCs Taken.
Format: `status`
- TBA
- 

Example: 
```
status
```
Output:
```
```

### View module details: `details`
Displays the relevant details of a module from NUSMods
Format: `details [MODULE_CODE]`
- `MODULE_CODE` is compulsory
- 

Example:
```
details CS2113
```
Output:
```
```


### Exit the program: `exit`
Exit the application.
Format : `exit`

### Saving the data
PENUS's data are saved in the hard disk automatically after exiting the program. 
There is no need to save manually.


### Editing the data file
PENUS's data are saved as a .txt file in `[JAR file location]/data/penus.txt`. 
Edits must be made according to the formatting of the data.



## FAQ 💻

**Q**: How do I transfer my data to another computer? 

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file in `/data/penus.txt` that contains the data of your previous PENUS application.


## Command Summary 🔑

| Command      | Format                                        |
|--------------|-----------------------------------------------|
| **init**     | `init`                                        |
| **help**     | `help`                                        |
| **taken**    | `taken MODULE_CODE y/YEAR s/SEMESTER g/GRADE` |
| **plan**     | `plan MODULE_CODE y/YEAR s/SEMESTER`          |
| **remove**   | `remove MODULE_CODE`                          |
| **mark**     | `mark MODULE_CODE`                            |
| **list**     | `list (FILTER)`                                        |
| **status**   | `status`                                      |
| **details**  | `details MODULE_CODE`                         |
| **exit**     | `exit`                                        |