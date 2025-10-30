# SGSafe User Guide

SGSafe is a *Command Line Interface (CLI) based case management system* that is designed specifically for law
enforcement agencies in Singapore to manage, track and process cases. Built with the diverse needs of police personnel
in mind, this application allows frontline officers to efficiently manage and process cases from creation to closure. By
providing an easy-to-use yet efficient interface, SGSafe transforms traditional case management processes into an
organised digital workflow that enhances operational efficiency for the public sector.

## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a case: `add`](#adding-a-case-add)
    - [Listing cases: `list`](#listing-cases-list)
    - [Closing a case: `close`](#closing-a-case-close)
    - [Opening a case: `open`](#opening-a-case-open)
    - [Updating a case: `edit`](#editing-a-case-edit)
    - [Deleting a case: `delete`](#deleting-a-case-delete)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Settings: `setting`](#settings-setting)
    - [File storage](#file-storage)
    - [Case categories](#case-categories)
- [FAQ](#faq)
- [Command Summary](#command-summary)
- [Coming Soon](#coming-soon)

## Quick Start

1. Ensure that you have Java 17 installed on your computer.
2. Download the latest version of `SGSafe` from
   the [releases page](https://github.com/AY2526S1-CS2113-W13-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for SGSafe.
4. Open a command terminal, navigate to the folder containing the JAR file, and run `java -jar SGSafe.jar` to start the
   application.
5. Type commands in the command line and press Enter to execute them. Refer to the [Command Summary](#command-summary)
   section
   below for a quick overview of available commands.

---

## Features

> **Notes about the command format:**
>
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.\
    >   e.g., in `add --title TITLE`, `TITLE` is a parameter which can be used as `add --title Murder at Yishun`.
>
> * Parameters with command line flags can be in any order.\
    >   e.g., if the command specifies `--title TITLE --date DATE`, `--date DATE --title TITLE` is also acceptable.
>
> * Parameters that are not preceded by command line flags must appear in the order specified.\
    >   e.g., if the command specifies `close INDEX` INDEX must come after close.
>
> * Optional parameters are enclosed in square brackets.\
    >   e.g., `--title TITLE [--victim VICTIM]` means the victim parameter is optional.
> * The double-dash `--` is a reserved prefix used to identify flags, so it cannot be used as part of an input value.
> * You cannot use the character | in your input as well as it is used in the save file format.
---

### Adding a case: `add`

Adds a new case to the case management system.

**Format:** `add --category CATEGORY --title TITLE --date DATE --info INFO [--victim VICTIM] [--officer OFFICER]`

* `CATEGORY`: The category of the case
* `TITLE`: The title or summary of the case
* `DATE`: The date the case was recorded or occurred
* `INFO`: Additional information or notes about the case
* `VICTIM`: (Optional) The name(s) of the victim involved
* `OFFICER`: (Optional) The name(s) of the officer assigned

> ℹ️ Note: The above are stored as strings (except date). No special formatting is required for those inputs.\
> ℹ️ Note: For accepted CATEGORY tags, refer to [Case Categories](#case-categories).\
> ℹ️ Note: Date is stored as a Java LocalDate. The default input format is `dd/MM/yyyy`. You may wish to change it using
> the settings command below.\
> ⚠️ Warning: A maximum of 5000 characters is allowed for all the fields.

**Examples:**

* `add --category Theft --title Theft case --date 2024-01-15 --info Stolen wallet --victim John Doe --officer Officer Smith`
* `add --category Burglary --info Burglary at 123 Main St --date 2024-02-20 --title Burglary case`

---

### Listing Cases: `list`

Displays all cases in the system, with optional filters and formatting modes.

#### **Format:** `list --status <open|closed|all> --mode <summary|verbose>`

#### Flags
- `--status` (optional): Filters cases by their status.
  - `open`: Show only open cases.
  - `closed`: Show only closed cases.
  - `all`: Show all cases (default).
- `--mode` (optional): Controls the level of detail in the output.
  - `summary`: One-line display per case.
  - `verbose`: Multi-line display with labeled fields.

#### Summary Mode Output
Each case is shown in a single line with:
- Status: `[Open]` or `[Closed]`
- Category (e.g., `Theft`, `Scam`)
- Case ID (6-character hexadecimal)
- Date
- Title

Example:
```
You currently have 3 cases in total
---
Note: Only very basic case details are shown here.
For more in depth information about the case (e.g. Info, Victim, Officer)
run: list --mode verbose
---
STATUS   CATEGORY         ID     DATE       TITLE
[Open]   Theft            0001a3 2025-10-14 Robbery
[Closed] Scam             0001a4 2025-10-15 Fraud
[Closed] Traffic accident 0001a5 2025-10-15 Fraud
```

#### Verbose Mode Output
Each case is shown in multiple lines with:
- Status
- Category (e.g., `Theft`, `Scam`)
- Case ID
- Date
- Title
- Info
- The time the case was created
- The last updated time of the case
- Victim (if available)
- Officer (if available)

Example:
```
You currently have 1 case in total
---
Note: Only basic case details (e.g. Title) are shown here and is truncated if too long.
For full case information (e.g. case-specific details like murder weapon),
use the read command
To see how to use the read command, run: help read
To use the read command, run: read <caseID>
---
======== CASE ID 000000 ========
Status     : Open
Category   : Murder
Title      : TITLE
Date       : 22/04/2023
Info       : Masked suspect entered victim's bedroom
Created at : 2025-10-27T18:28:25.170780500
Updated at : 2025-10-27T18:28:25.170780500
Victim     : Jane Doe
Officer    : Officer John Lee
```

> Note: 
> - Deleted cases are excluded from all listings.
> - More specific case information (e.g. murder weapon) can only be accessed from read. This is an intentional design choice as we do not want to clutter the list command with unnecessary details.


---

### Closing a case: `close`

Marks a case as closed.

**Format:** `close ID`

* Closes the case with the specified `ID`.
* The id refers to the id of the case itself.
* The id **must be exactly 6 hexadecimal digits** 000001, 000fab, 00beef, … and the case must exist.

**Examples:**

* `close 000003` closes the case with the id 000003 in the list.

---

### Opening a case: `open`

Marks a case as open.

**Format:** `open ID`

* Reopens the case with the specified `ID`.
* The id refers to the id of the case itself.
* The id **must be exactly 6 hexadecimal digits** 000001, 000fab, 00beef, … and the case must exist.

**Examples:**

* `open 000003` opens the case with the id 000003 in the list.

---

### Editing a case: `edit`

Updates the details of an existing case.

**Format:** `edit INDEX [--title TITLE] [--date DATE] [--info INFO] [--victim VICTIM] [--officer OFFICER]`

* Updates the case at the specified `INDEX`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

**Examples:**

* `edit 1 --victim Jane Smith --officer Officer Lee` updates the victim and officer of the 1st case in the list.
* `edit 3 --title Updated title --date 2024-03-01` updates the title and date of the 3rd case in the list.

> ℹ️ Note: The above are stored as strings (except date). No special formatting is required for those inputs.\
> ℹ️ Note: Date is stored as a Java LocalDate. The default input format is `dd/MM/yyyy`. You may wish to change it using
> the settings command below.\
> ⚠️ Warning: A maximum of 5000 characters is allowed for all the fields.

---

### Deleting a case: `delete`

**Format:** `delete ID`

* Deletes the case with the specified ID.
* The id refers to the id of the case itself.
* The id **must be exactly 6 hexadecimal digits** 000001, 000fab, 00beef, … and the case must exist.

**Examples:**

* `delete 00012a` deletes the case with the id 00012a in the list.

---

### Exiting the program: `bye`

Exits the program.

**Format:** `bye`

---

### Settings: `setting`

This is a function to perform user defined setting for the program. User can set the date input format and output
format.

**Format:** `setting --type TYPE --value VALUE`
> ℹ️ Note: Type can only be `dateinput` representing the input format and `dateoutput` representing the format where the
> date will be printed.\
> ⚠️ Warning: The value must be a valid date format, according to Java's DateFormatter. Stray characters that are not 
> date and time-related will flag as an error.
> For more information, please refer
> to [Java DateTimeFormatter](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html).\
> ⚠️ Warning: Note that month is capitalised in the date-time-formatter. `MM` represents month, while `mm` represents minutes.\
> The default input and output format is `dd/MM/yyyy`
> ⚠️ Warning: If the input format has repeated characters (e.g., `dd-MM-yyyy-dd`), the user is expected to key in the same 
> day for both `dd`.

**Examples:**

* `setting --type dateinput --value dd-MM-yyyy` means that all inputs for date must follow dd-MM-yyyy format to be
  considered valid.
* `setting --type dateoutput --value dd/MM/yyyy` means that all output for date will be printed in dd/MM/yyyy format.

---

### File storage

This is a feature that saves your case info to `data.txt` in the folder that you run the program in. 
There is no specific command that will execute this function, 
but it will automatically run every time you run any command.

#### Save file modification (only for advanced users)

_Beware that you may corrupt your data if you modify any data incorrectly, do this at your own risk!_

The data in the save file is stored in this format:

> `key1:value1|key2:value2|key3:value3|...`

each key corresponds to a field, and each line corresponds to a case. An empty value means that the field has not been initialised.

Fields that you SHOULD NOT EDIT:
1. id
2. category
3. created-at

You cannot add new cases through editing the file, do not add new lines to the save file.

The date field must be stored in the format dd/mm/yyyy (day/month/year).

The modified-at field must be stored in the format dd/mm/yyyy hh:mm:ss (day/month/year hour:minute:second).

***Examples***
- To change the victim in a case from `alice` to `bob`, modify the line in `data.txt` 
from `...|victim:alice|...` to `...|victim:bob|...`.
- To change the date in a case from fifth of november 2024 (05/11/2025) to second of january 2025 (02/01/2025),
modify the line corresponding to the case that you want to edit in `data.txt` from `...|date:05/11/2025|...`
to `...|date:02/01/2025|...`.

---

### Case categories
> **Notes:**
>
> * If the category is not in the list, type 'others' as the category.
> * Some categories have additional attributes, which can be edited using the `edit` feature.
> * For more information on how to add and edit categories,
refer to [`add`](#adding-a-case-add) and [`edit`](#editing-a-case-edit)

**`CATEGORY` inputs:** 
- indicated under `CATEGORY` column

**`edit` tags:**
- `STRING`: attributes are stored as strings and accept any input
- `INTEGER`: attributes are stored as integers and only accept positive whole numbers as input.

| `CATEGORY`  | `edit` tags (if any)                                                                                                          |
|-----------|-------------------------------------------------------------------------------------------------------------------------------|
| burglary  | `--financial-value INTEGER`, `--location STRING`                                                                              |
| scam      | `--financial-value INTEGER`                                                                                                   |
| theft     | `--financial-value INTEGER`, `--stolen-object STRING`                                                                         |
| arson     | `--location STRING`, `--monetary-damage INTEGER`                                                                              |
| vandalism | `--location STRING`, `--monetary-damage INTEGER`                                                                              |
| rape      |                                                                                                                               |
| voyeurism |                                                                                                                               |
| accident  | `--vehicle-type STRING`, `--vehicle-plate STRING`, `--road-name STRING`, `--number-of-casualties INTEGER`                     |
| speeding  | `--vehicle-type STRING`, `--vehicle-plate STRING`, `--road-name STRING`, `--speed-limit INTEGER` , `--exceeded-speed INTEGER` |
| assault   | `--weapon STRING`, `--number-of-victims INTEGER`                                                                              |
| murder    | `--weapon STRING`, `--number-of-victims INTEGER`                                                                              |
| robbery   | `--weapon STRING`, `--number-of-victims INTEGER`                                                                              |
| others    | `--custom-category STRING`                                                                                                    |

---


## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Unfortunately, there is no built-in feature to transfer data between computers. This feature will be coming soon
in the next iteration of SGSafe. Stay turned.

**Q**: What happens if a case is marked as closed?

**A**: Closed cases are still visible in the list but are marked with `[closed]` instead of `[Open]`.

---

## Command Summary

| Action          | Format                                                                                                | Example                                                                                                                    |
|-----------------|-------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| **Add case**    | `add --category CATEGORY --title TITLE --date DATE --info INFO [--victim VICTIM] [--officer OFFICER]` | `add --category Theft --title Theft case --date 2024-01-15 --info Stolen wallet --victim John Doe --officer Officer Smith` |
| **List cases**  | `list`                                                                                                | `list`                                                                                                                     |
| **Close case**  | `close ID`                                                                                            | `close 000003`                                                                                                             |
| **Open case**   | `open ID`                                                                                             | `open 000003`                                                                                                              |
| **Edit case**   | `edit INDEX [--title TITLE] [--date DATE] [--info INFO] [--victim VICTIM] [--officer OFFICER]`        | `edit 1 --victim Jane Smith --officer Officer Lee`                                                                         |
| **Delete case** | `delete ID`                                                                                           | `delete 00beef`                                                                                                            |
| **Setting**     | `setting --type TYPE --value VALUE`                                                                   | `setting --type dateinput --value dd-MM-yyyy`                                                                              |
| **Exit**        | `bye`                                                                                                 | `bye`                                                                                                                      |

## Coming Soon

- Data transfer between computers
- Support for multiple formats for date input
- More advanced search and filter options
- Escape character for `--` in input values