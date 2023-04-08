### Project: PENUS
PENUS is a desktop app for NUS engineering students to manage and plan their modules in their university life. The user interacts with it using a CLI. It is written in Java, and has about 6 kLoC.

Given below are my contributions to the project.

- **Feature**: Plan and Taken command
    - What it does: Adds a module (plan or taken) to the module list.
    - Highlights: Decided on design and chose to implement by overloading a constructor.

- **Feature**: Mark Command
    - What it does: converts a plan module into a taken module and adds a grade

- **Feature**: Remove Command
    - What it does: searches the module list for the modulecode and removes that Module

- **Feature**: Storage saving and loading
    - What it does: enables saving data from the module list by encoding it and loads upon initialising the application
    - Highlights: Required in-depth understanding of Scanner API as 2 different types of classes needed to be read (User and Module). Challenging as all errors that the Parser class handled will need to be implemented for the loading of the .txt file.

- **Feature**: Resource retrieving from .txt file
    - Justification: Allows user to read preset core module details for the status command without seeing the folder in the root folder of .jar file.
    - What it does: retrieves data from a .txt file with all core module details gathered. Saves it as a resource.
    - Highlights: Used `getResourceAsStream()` java API. Challenging as it required in-depth understanding of folder architecture and gradle. The implementation was also challenging as the API had little info and required a different way to scan input. Had to manually edit vscode and intellij settings to enable resources.

- **Feature**: Exit Command
    - What it does: when user input is detected to be the exit command, exits the `runCommandLoopUntilExitCommand()` and exits the application

- **Enhancement**: Overhaul architecture to adopt MVC design pattern
    - Justification: Improves abstraction and OOP significantly and made it easier for teammates to work on features independently as working on previous codebase asynchronously made the code messy. Made architecture diagrams cleaner and simple.
    - Highlights: This enhancement affected all existing code. It required an in-depth analysis of design patterns. The implementation too was challenging as it required changes to existing commands on top of additional methods and classes.
    - Credits: [AddressBook 2](https://github.com/se-edu/addressbook-level2) and [AddressBook 3](https://github.com/se-edu/addressbook-level3).

- **Code contributed**: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=bentohset&breakdown=true)

- **Contributions to UG**:
    - Table of contents and links
    - Quick start, FAQ, Command summary
    - Emojis to enhance look

- **Contributions to DG**:
    - Table of contents and overall structure
    - Acknowledgements, Design, Appendix A, B, C, D, E sections
    - Implementation section: Add module, Remove module, Mark module, Save planner, [Proposed] handle CS/CU
    - Diagrams (refer to extract): Architecture, Component classes (Ui, Logic, Model, Storage), Add module sequence, remove module sequence, mark module sequence, list sequence, Grade class, Save feature class

- **Contributions to team-based tasks**:
    - Set up GitHub team org and repo
    - Release management for v1.0 on GitHub
    - Set up labels for issue
    - Maintain issue tracker and milestones
    - Add javadoc to most methods

- **Review/mentoring contributions:**:
    - PRs reviewed ([\#14](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/14), [\#30](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/30), [\#44](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/44), [\#48](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/48), [\#65](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/65), [\#67](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/67), [\#72](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/72), [\#186](https://github.com/AY2223S2-CS2113-T11-2/tp/pull/186))

- **Contributions beyond the project team**:
    - Reported 16 bugs for PE-D

- **Contributions to the Developer Guide (Extracts)**:

Architecture diagram:

![ArchitectureDiagram](/docs/uml/diagrams/Architecture.png)

Ui class diagram:

![UIClassDiagram](/docs/uml/diagrams/UiClass.png)

Logic oject diagram:

![LogicObjDiagram](/docs/uml/diagrams/LogicClass.png)

Model object diagram:

![ModelObjDiagram](/docs/uml/diagrams/ModelClass.png)

Storage object diagram:

![StorageObjDiagram](/docs/uml/diagrams/StorageClass.png)

Add module sequence diagram:

![AddModuleSequenceDiagram](/docs/uml/diagrams/AddModSequence.png)

Remove module sequence diagram:

![RemoveModuleSequenceDiagram](/docs/uml/diagrams/RemoveModSequence.png)

Mark module sequence diagram:

![MarkModuleSequenceDiagram](/docs/uml/diagrams/MarkModSequence.png)

List sequence diagram:

![ListSequenceDiagram](/docs/uml/diagrams/ListSequence.png)

Grade class diagram:

![Grade Class Diagram](/docs/uml/diagrams/GradeClass.png)

Save feature class diagram:

![SaveFeatureClassDiagram](/docs/uml/diagrams/SaveFeatureClass.png)