## Project: SGSafe

SGSafe is a CLI application designed for police officers to efficiently manage and track different types of cases, such as thefts, traffic accidents and assaults. It provides features to add, edit, close, and reopen cases, ensuring that officers can maintain accurate, up-to-date records.

Given below are my contributions to the project:
- **Code contribution**:
  [link to Reposense](https://nus-cs2113-ay2526s1.github.io/tp-dashboard/?search=shennontay&breakdown=true)
  
- **New Features**:
    - `edit` command: Implemented edit functionality for cases.
        - What it does: Allows the user to edit case fields such as title, date, police officer in charge, and fill in category-specific fields such as 'exceeded-speed' for speeding cases. If no fields are provided, a message containing all editable fields of the case is shown.
        - Justification: This feature improves the product significantly because users may need to update case details as new information becomes available.
        - Highlights: Carried out robust validation to ensure that only valid fields can be edited.
    - `read` command: Implemented read functionality for cases.
        - What it does: allows users to view the full information of a specific case.
        - Justification: This is an important feature of the product because users need to be able to view detailed case information.
        - Highlights: Used detailed formatting to present case information clearly.
        - Credits: Refactored by Celeste Tan for better code structure and readability.
    - `help` command: Implemented help functionality.
        - What it does: Provides users with a list of available commands and information on how to use them.
        - Justification: This feature enhances the user experience by making it easier for users to understand how to use the application.
        - Highlights: Created a comprehensive help message that covers all commands and their usage.

- **Project Management**:
    - Assisted in managing v1.0 and v2.0 on GitHub

- **Enhancements to existing features**:
    - Updated `bye` command to only accept `bye` as a valid input to exit the application, preventing accidental exits.

- **Documentation**:
    - User Guide:
        - Added documentation for the `edit`, `read`, and `help` commands.
        - Updated documentation for `bye` command.
    - Developer Guide:
        - Added description for UI component

- **Community**:
    - Modified code in PR based on feedback: [#27](https://github.com/AY2526S1-CS2113-W13-3/tp/pull/27), [#130](https://github.com/AY2526S1-CS2113-W13-3/tp/pull/130)