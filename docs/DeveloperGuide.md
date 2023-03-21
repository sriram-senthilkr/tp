# Developer Guide
<!----------------------------Table of Contents----------------------------------------->
- [Acknowledgements](#acknowledgements)
- [Design](#design)
  - [Architecture](#architecture)
  - [UI Component](#ui-component)
  - [Parser Component](#parser-component)
  - [Storage Component](#storage-component)
  - [Resource Component](#resource-component)
  - [Module Retriever Component](#module-retriever-component)
  - [Module Component](#module-component)
- [Implementation](#implementation)
  - [Add Module](#add-module)
  - [Remove Module](#remove-module)
  - [Mark Module As Taken](#mark-module-as-taken)
  - [List Modules](#list-modules)
  - [Display Status](#display-status)
  - [Get Module Details](#get-module-details)
  - [Initialise User](#initialise-user)
  - [Save To Local Drive](#save-plan-to-local-drive)
- [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
  - [Documentation](#documentation)
  - [Logging](#logging)
  - [Testing](#testing)
  - [Configuration](#configuration)
  - [Dev-ops](#dev-ops)
- [Appendix A: Requirements](#appendix-a--requirements)
- [Appendix B: Instructions for manual testing](#appendix-b--instructions-for-manual-testing)
<!----------------------------Acknowledgements----------------------------------------->
## Acknowledgements
{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

<!----------------------------Design----------------------------------------->
## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Architecture

### UI Component

### Parser Component

### Storage Component

### Resource Component

### Module Retriever Component

### Module Component

<!----------------------------Implementation----------------------------------------->
## Implementation
### Add module
The Add Module features allows users to add two types of modules (taken or planning) to the ModuleList using the commands `plan` or `taken`. The two types of modules are differentiated by its `Module()` overloaded constructor, accepting different type signatures. It is facilitated by `ModuleList`.

Given below is an example usage scenario of the 2 types of modules and how the add module mechanism behaves at each step.

**When a planned module is added:**
Step 1. The user launches the application for the first time. The ModuleList will be initialised with the initial module list state if provided in `penus.txt`.

Step 2. The user executes the `plan CS2113 y/1 s/2` command to plan the module CS2113 for year 1 and semester 2 to be added into the list. The `plan` command is executed within the switch case of the `parseCommand()` method of `CommandParser`.

Step 3. If a valid command is provided, a new `Module` will be instantiated within `ModuleParser` where another switch case is executed. The `ModuleParser` is extracted away from the `CommandParser` for more readability. The string is then split into parts separated by the flags `y/` and `s/` into a `planDetails` array. An overloaded constructor `Module()` is instantiated with the arguments corresponding to the array which sets the `isTaken` parameter `false`. The arguments are `moduleCode`, `year` and `semester`.

Step 4. The new `Module` object is then returned to the `CommandParser` where it is assigned and added to the `ModuleList`. The `addModule()` method is then executed in `ModuleList`. 

Step 5. It first checks if the module already exists in the list by iterating through the current list and checking for the same `moduleCode`. If so, `DuplicateModuleException` is thrown. If the module does not exist, it is added to the `ModuleList` by calling the `add()` method of the class. 

Step 6. A confirmation message is prited to indicate the success of the module addition. This is called using the `printMessage()` method from `Ui` which accpets an array of strings as message lines.

**When a taken module is added:**
Step 1. The user launches the application for the first time. The ModuleList will be initialised with the initial module list state if provided in `penus.txt`.

Step 2. The user executes the `taken CS2113 y/1 s/2 g/A+` command to plan the module CS2113 for year 1 and semester 2 to be added into the list. The `plan` command is executed within the switch case of the `parseCommand()` method of `CommandParser`.

Step 3. If a valid command is provided, a new `Module` will be instantiated within `ModuleParser` where another switch case is executed. The `ModuleParser` is extracted away from the `CommandParser` for more readability. The string is then split into parts separated by the flags `y/`, `s/` and `g/` into a `takenDetails` array. An overloaded constructor `Module()` is instantiated with the arguments corresponding to the array which sets the `isTaken` parameter `true`. The arguments are `moduleCode`, `year`, `semester` and `grade`.

Step 4 - 6. Identical to that of a `plan` command as mentioned above.

### Remove module
(TBA)

### Mark module as taken
(TBA)

### List modules
(TBA)

### Display status
(TBA)

### Get module details
(TBA)

### Initialise user
(TBA)

### Calculate CAP
(TBA)

### Save planner to local drive
(TBA)

<!----------------------------Documentation----------------------------------------->
## Documentation, logging, testing, configuration, dev-ops
### Documentation
### Logging
### Testing
### Configuration
### Dev-ops

<!----------------------------Appendix A----------------------------------------->
## Appendix A: Requirements
### Product scope

#### Target user profile
- NUS engineering students
- prefers desktop CLI over other available planner application(s)
- prefers typing to mouse interactions

#### Value proposition
Manage a planner faster and more efficiently than a typical mouse/GUI driven application

### User Stories
Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | Version |         As a ...         | I want to ...                          | So that I can ...                                                   |
|:--------:|:-------:|:------------------------:|:---------------------------------------|:--------------------------------------------------------------------|
|   ***    |  v1.0   |         student          | add modules to the planner             | keep track of my planned and taken modules for my study term        |
|   ***    |  v1.0   |         student          | remove modules                         | remove modules that I have accidentally added                       |
|   ***    |  v1.0   |         student          | list all the modules in my planner     | see all modules I am planning for and have taken for the study term |
|   ***    |  v1.0   |         student          | mark a planned module as taken         | update my planner accordingly                                       |
|   ***    |  v1.0   | penultimate year student | display graduation status              | graduate on time                                                    |
|    **    |  v2.0   |         student          | see realtime module details            | understand module details without referring to other websites       |
|    **    |  v2.0   |         student          | check whether I meet the prerequisites | plan my timetable with less worries                                 |
|   ***    |  v2.0   |         student          | keep track of my CAP                   | I do not need to calculate my CAP after every semester              |
|   ***    |  v2.0   |         new user         | see usage instructions                 | refer to them in case I forget the application commands             |
|    **    |  v2.0   |      returning user      | save my planner                        | I do not need to re-add my modules                                  |
|   ***    |  v2.0   |         new user         | include my course                      | plan for course-specific core modules                               |


### Use cases

### Non-Functional Requirements
- PENUS should work on any mainstream OS as long as it has Java 11 or above installed.
- PENUS should be able to hold up to 1000 modules without a noticeable sluggishness in performance for typical usage.
- A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* *Mainstream OS* - Windows, Linux, Unix, OS-X
* *CLI* - Command Line Interface


<!----------------------------Appendix B----------------------------------------->
##  Appendix B: Instructions for manual testing
Given below are instructions to test the app manually.
> Note: These instructions only provide a starting point for testers to work on;
> testers are expected to do more *exploratory* testing. 