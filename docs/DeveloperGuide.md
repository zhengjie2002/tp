# Developer Guide

## Table of Contents

1. [Acknowledgements](#acknowledgements)
2. [Setting up, Getting Started](#setting-up-getting-started)
3. [Design & Implementation](#design--implementation)
4. [Appendix A: Product Scope](#appendix-a-product-scope)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
5. [Appendix B: User Stories](#appendix-b-user-stories)
6. [Appendix C: Non-Functional Requirements](#appendix-c-non-functional-requirements)
7. [Appendix D: Glossary](#appendix-d-glossary)
8. [Appendix E: Instructions for Manual Testing](#appendix-e-instructions-for-manual-testing)

---

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

---

## Setting up, Getting Started

## Design & implementation

### Architecture 

![Architecture Diagram](images/ArchitectureDiagramOverall.png)

The Architecture Diagram given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

### Overall logic

![Overall Sequence Diagram](images/SequenceDiagramGenericCombined.png)

The main code flow is as follows:
1. Print welcome message
2. Initialize CaseManager with saved info from `data.txt` if it exists
3. Read user input from System.in
4. Parse user input to obtain a Command object that contains the 
information necessary to execute the command
5. execute user command
6. until the command is to exit the program, repeat steps 3 to 5
7. Save the updated list of commands to `data.txt`
8. Print exit message

### UI Component

The API of this component is primarily specified in `Display.java`, with parsing functionality in `Parser.java` and validation in `Validator.java`.

---

#### Structure of the UI Component

The UI consists of three main parts: `Display`, `Parser`, and `Validator`. All these work together to handle user interaction through a console-based command-line interface.

The UI component uses standard input/output streams. The interaction flow is managed in the `SGSafe` main class.

---

#### Responsibilities

The UI component:

- **Displays formatted output** to the user using `Display`, which wraps messages in visual dividers
- **Parses user commands** using `Parser` to convert raw input into `Command` objects
- **Validates input** using `Validator` to ensure data integrity before execution
- **Keeps a reference to the Command component**, as it relies on Commands to execute user actions
- **Depends on classes in the Domain component**, as it displays `Case` objects from `CaseManager`

#### Key Classes

**Display**: Handles all user-facing output with formatted messages, including welcome/goodbye messages and command results.

**Parser**: Interprets raw input and creates structured `Command` objects by extracting keywords, validating flags, and enforcing input constraints.

**Validator**: Provides utility methods to validate flags, check required fields, and verify case ID format (6-character hexadecimal).

#### Interaction Flow

1. `SGSafe.mainLoop()` reads user input from console
2. `Parser.parseInput()` validates input and creates the appropriate `Command` object
3. `Command.execute()` is called to perform the action
4. Commands interact with `CaseManager` to modify or retrieve `Case` objects
5. Results are displayed via `Display.printMessage()`
6. Exceptions are caught and error messages shown through `Display`

### Model Component

#### Structure of the Model Component

The Model component represents the **core domain** of SGSafe. It encapsulates the application’s state and business logic, independent of how input is parsed or output is displayed. The Model is designed to be self‑contained, exposing APIs that allow the Logic layer to manipulate and query data without needing to know about persistence or UI details.

---

#### Responsibilities

The Model component:

- **Maintains the central state** of the application through `CaseManager`, which stores and manages all `Case` objects
- **Provides APIs** for adding, deleting, updating, and retrieving cases
- **Ensures data integrity** by enforcing constraints (e.g., unique case IDs, valid status values)
- **Supports queries and filtering** for commands like `list` or `find`
- **Acts as the single source of truth** for the rest of the system — Logic and Storage interact with the Model to read or update state

---

#### Key Classes

- **CaseManager**: The main entry point for managing cases. Provides methods to add, delete, update, and retrieve `Case` objects.
- **Case**: Represents an individual case, with fields such as ID, description, status, and metadata.
- **Supporting Entities**:
    - `CaseStatus` (enum): Defines allowed states (e.g., OPEN, CLOSED).
    - `CaseFilter` or similar utility classes: Encapsulate filtering logic for list/search operations.

---

#### Interaction Flow

1. A `Command` object (from the Logic component) calls into `CaseManager` to perform an operation.
2. `CaseManager` updates or queries its internal collection of `Case` objects.
3. The result (e.g. an added case, a modified case, a list of cases, or a success flag) is returned to the `Command`.
4. The `Command` passes results back to the UI for display.
5. After execution, `SGSafe` triggers `Storage.saveToFile()` to persist the updated Model state.

---

## Appendix A: Product scope

### Target user profile

- police officer who needs to manage a significant number of cases
- prefers desktop apps over other types of apps
- can type fast
- prefers typing to using the mouse
- comfortable using CLI apps

### Value proposition

- manage and track cases faster than a typical mouse/GUI driven app

---

## Appendix B: User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priorities | Version | As a ...           | I want to ...           | So that I can ...                                 |
|------------|---------|--------------------|-------------------------|---------------------------------------------------|
| ***        | v1.0    | front-desk officer | create case             | record cases into the system                      |
| ***        | v1.0    | front-desk officer | edit case details       | edit cases that are typed wrongly                 |
| ***        | v1.0    | front-desk officer | mark case as closed     | close cases that have been attended to            |
| ***        | v1.0    | front-desk officer | delete case             | delete duplicates                                 |
| ***        | v1.0    | front-desk officer | list all cases          | see all the cases that are currently being worked on |



---

## Appendix C: Non-Functional Requirements

- Should work on any mainstream OS as long as it has Java 17 or above installed.
- Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
- A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
{More to be added}

---

## Appendix D: Glossary

* *glossary item* - Definition

---

## Appendix E: Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
