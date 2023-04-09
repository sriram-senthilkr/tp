### Tay Jiun Yuan's Project Portfolio Page
### Project: PENUS
PENUS is a desktop app for NUS engineering students to manage and plan their modules in their university life. The user interacts with it using a CLI. It is written in Java, and has about 6 kLoC.
Given below are my contributions to the project.

- **New Feature**: Initialization Command
    - What it does: Initialises the User's Name and Course
    - Justification: Name allows users to personalize their PENUS experience and course allows the status command to retrieve the core modules of the user.
    - Highlights: Improved usability as it is not feasible to have the user type out their entire course name as it would be prone to misspelling/ wrong format. Decided to map each course to a number instead. Created a new class User to store the information.

- **New Feature**: Get core modules of user
  - What it does: Retrieves a list of the core modules of the user.
  - Justification: Allows the Status Command to retrieve the core module information of the user based on his/her course.
  - Highlights: Hardcoded each course's core modules (found through each course's website) into a txt file and created a method getCoreMods() to read the txt file and add each core module code under the correct course in a hashmap.

- **New Feature**: Status Command 
  - What it does: Displays the user's name, course, core module status and total module credits taken
  - Justification: Provides a summary of the user's progress to graduation. 
  - Highlights: Very challenging to implement due to the number of components. Retrieving the status of each core module requires calling the methods to get the core module codes, checking if the user has taken it. Also required API calls to retrieve the module information and separate methods to get the MC progress of the user. Very difficult to achieve everything whilst maintaining strict OOP practices.

- **New Feature**: Sample Data
  - What it does: Creates an instance of the model, with sample data.
  - Justification: When testing, the model can be instantiated with pre-added data, instead of manually adding the data in through commands.

- **Tests Written**: 
  - Initialisation Command Tests
  - Status Command Tests
  - Parser Tests (for initialisation parser and status parser)
  - ModuleList Tests (for getGEXX() methods)
  - Sample Data Tests
  - Clear Command Tests

- **Enhancement**: Planned new architecture to adopt MVC design pattern
    - Justification: Improves abstraction and OOP significantly and made it easier for teammates to work on features independently as working on previous codebase asynchronously made the code messy. Made architecture diagrams cleaner and simple.
    - Highlights: Came up with the rough planning of refactored architecture adopting MVC pattern with Benjamin. Only responsible for planning and not implementation.
    - Credits: [AddressBook 2](https://github.com/se-edu/addressbook-level2) and [AddressBook 3](https://github.com/se-edu/addressbook-level3).

- **Code contributed**: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=tayjiunyuan&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Contributions to UG**:
    - Reformatted the entire UG from Google Docs into md file
    - Written Documentation for:
      - Introduction
      - NUSModsAPI
      - Status Feature
      - Initialisation Feature
      - Editing Data File (partial)

- **Contributions to DG**:
  - Status Command
    - Wrote documentation for Implementation and drew UML Sequence Diagram
  - Initialisation Command
    - Wrote documentation for Implementation and drew UML Sequence Diagram

- **Contributions to team-based tasks**:
    - Assigned teammates for milestones 
    - Opened and managed milestone v2.1
    - User Stories 

- **Review/mentoring contributions:**:
    - PRs reviewed/merged: [#14](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/14), [#25](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/25), [#27](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/27), [#186](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/186), [#191](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/191), [#196](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/196), [#198](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/198)

- **Contributions beyond the project team**:
    - Reported 5 bugs for PE-D
