### Project: PENUS
PENUS is a desktop app for NUS engineering students to manage and plan their modules in their university life. The user interacts with it using a CLI. It is written in Java, and has about 6 kLoC.

Given below are my contributions to the project.

- **New Feature**: Initialization Command
    - What it does: Initialises the User's Name and Course
    - Justification: Name allows users to personalize their PENUS experience and course allows the status command to retrieve the core modules of the user.
    - Highlights: Tricky implementation as it is not feasible to have the user type out their entire course name as it would be prone to misspelling/ wrong format. Decided to map each course to a number instead.

- **New Feature**: Get core modules of user
  - What it does: Retrieves a list of the core modules of the user.
  - Justification: Allows the Status Command to retrieve the core module information of the user based on his/her course.
  - Highlights: Tricky to implement there were many components. Hardcoded each course's core modules (found through each course's website) into a txt file and created a method getCoreMods() to read the txt file and add each core module code under the correct course in a hashmap.

- **New Feature**: Status Command 
  - What it does: Displays the Each 
  - Justification: Allows the Status Command to retrieve the core module information of the user based on his/her course.
  - Highlights: Tricky to implement there were many components. Hardcoded each course's core modules (found through each course's website) into a txt file and created a method getCoreMods() to read the txt file and add each core module code under the correct course in a hashmap.

- **Enhancement**: Planned new architecture to adopt MVC design pattern
    - Justification: Improves abstraction and OOP significantly and made it easier for teammates to work on features independently as working on previous codebase asynchronously made the code messy. Made architecture diagrams cleaner and simple.
    - Highlights: Came up with the rough planning of refactored architecture adopting MVC pattern with Benjamin. Implementation not done by me and I do not claim credit for it.
    - Credits: [AddressBook 2](https://github.com/se-edu/addressbook-level2) and [AddressBook 3](https://github.com/se-edu/addressbook-level3).

- **Code contributed**: [RepoSense link]

- **Contributions to UG**:
    - Reformatted the entire UG from Google Docs into md file. 
    - Status Feature
    - Initialisation Feature
    - Edit txt (partial)

- **Contributions to DG**:


- **Contributions to team-based tasks**:
    - Assigned teammates for issues
    - Opened and managed milestone v2.1

- **Review/mentoring contributions:**:
    - PRs reviewed:

- **Contributions beyond the project team**:
    - Reported 5 bugs for PE-D

- **Contributions to the Developer Guide (Extracts)**:

