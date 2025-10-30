## Project: SGSafe

SGSafe is a cli application to be used by police agencies that enables front-desk officers to manage the different cases
that they are responsible for in the terminal. The user is able to do everything through a command line interface, and
it aims to be faster than a gui for those who are fast at typing.

Given below are my contributions to this project:

- **Code
  contribution**
  [Link to RepoSense](https://nus-cs2113-ay2526s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-09-19T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=Michael-Low&tabRepo=AY2526S1-CS2113-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- **New Features**: 
  - Implemented the `delete` feature that allows users to add cases into the system:
    - What it does: Allows the user to delete cases that were wrongly entered into the application or are duplicates
    - Justification: This feature helps with the management of records.
    - Highlight: Implemented the soft deletion of cases, allowing users to undelete cases if any mistakes were made by 
    editing the save file directly.
  - Implemented data persistence through a file save and load feature.
    - What it does: Allows the user to retrieve a previously created list of cases and save any changes that they 
    make to it
    - Justification: This feature allows users to use the application over multiple restarts while keeping the same
    list of cases.

- **Documentation**:
    - User Guide:
        - Contributed to the delete command and file saving information in the User guide.
    - Developer Guide:
        - Added the sequence diagrams for the file storage section.
        - Added a writeup on the structure of the Storage component.

- **Contribution to Team Based Task**:
    - Created and set up the team Github repo, including setting up permissions, Github pages and the issues tracker

- **Reviewing/Mentoring Contribution**
  - Changed code in Pull Requests based on teammates suggestions: 
      [Example PR](https://github.com/AY2526S1-CS2113-W13-3/tp/pull/50)
  - Reported Bugs: Identified and reported bugs of the TP to help improve the overall stability of the
    application. [Example Bug Report](https://github.com/AY2526S1-CS2113-W13-3/tp/issues/176)