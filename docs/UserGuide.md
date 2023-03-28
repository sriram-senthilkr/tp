# 📜 PENUS User Guide

## Introduction

{Give a product intro}

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
1. Download the latest version of `PENUS` from [here](http://link.to/duke).

## Features 👾

### Initialisation: `init`


### Help: `help`


### Add taken modules: `taken`


### Plan untaken modules: `plan`


### Remove a module: `remove`


### Mark module as taken: `mark`


### View modules: `list`


### View graduation status: `status`


### View module details: `details`


### Exit the program: `exit`


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
| **list**     | `list`                                        |
| **status**   | `status`                                      |
| **details**  | `details MODULE_CODE`                         |
| **exit**     | `exit`                                        |