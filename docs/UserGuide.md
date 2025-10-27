# SGSafe User Guide

SGSafe is a *Command Line Interface (CLI) based case management system* that is designed specifically for law
enforcement agencies in Singapore to manage, track and process cases. Built with the diverse needs of police personnel
in mind, this application allows frontline officers to efficiently manage and process cases from creation to closure. By
providing an easy to use yet efficient interface, SGSafe transforms traditional case management processes into an
organised digital workflow that enhances operational efficiency for the public sector.

## Table of Contents

- [Quick Start](#quick-start)
- [Case Categories](#case-categories)
- [Features](#features)
  - [Adding a case: `add`](#adding-a-case-add)
  - [Listing cases: `list`](#listing-cases-list)
  - [Closing a case: `close`](#closing-a-case-close)
  - [Opening a case: `open`](#opening-a-case-open)
  - [Updating a case: `edit`](#editing-a-case-edit)
  - [Deleting a case: `delete`](#deleting-a-case-delete)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
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

## Case Categories
- The category tag for the add command should be from the list below.
  If the category is not in the list, type 'others' as the category.
- Some categories have tags attributed to them, which can be edited using the edit command.
  Click on the category to view their attributed tags, if any.


- Burglary
- Scam
- Theft
  - Stolen object
- Arson
- Property
- Vandalism
- Rape
- Voyeurism
- <details>
<summary>Accident</summary>
  - Vehicle type
  - Vehicle plate
  - Road name
</details>
- Speeding
  - Vehicle type
  - Vehicle plate
  - Road name
  - Speed limit
  - Exceeded speed
- Assault
- Murder
  - Weapon 
  - Number of victims
- Robbery
- Others
  - Custom category

**Financial Cases** 💰

**Categories:** Scam, Theft  
**Tags:**  
Theft:
- Stolen Object  


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

> ℹ️ Note: The above are stored as strings. No special formatting is required.\
> ⚠️ Warning: A maximum of 5000 characters is allowed for all the fields.

**Examples:**

* `add --category Theft --title Theft case --date 2024-01-15 --info Stolen wallet --victim John Doe --officer Officer Smith`
* `add --category Burglary --info Burglary at 123 Main St --date 2024-02-20 --title Burglary case`

---

### Listing cases: `list`

Displays all cases in the system.

**Format:** `list [--status STATUS] [--mode MODE]`

**Optional Flags:**
- `--status STATUS`: `open`, `closed`, or `all` (default: all)
- `--mode MODE`: `summary` or `verbose` (default: summary)

**Examples:**
- `list` — Lists all cases in summary mode
- `list --status open` — Lists only open cases in summary mode
- `list --status closed --mode verbose` — Lists closed cases with full details
- `list --mode verbose` — Lists all cases with detailed output

**Summary Mode Output:**
```
____________________________________________________________
You currently have 1 case
1. [O] 2024-01-15 Theft case
____________________________________________________________
```

**Verbose Mode Output:**
```
You currently have 2 cases in total
==== CASE ID 000001 ====
Status  : Open
Title   : Robbery
Date    : 2025-10-01
Info    : Masked suspect entered the premises and demanded cash...
Victim  : John Doe
Officer : Officer Tan
==== CASE ID 000002 ====
Status  : Closed
Title   : Fraud
Date    : 2025-10-02
Info    : Email scam targeting elderly victims...
Victim  : Jane Doe
Officer : Officer Lim
```

> ℹ️ In verbose mode, the `info` field is truncated to 100 characters with `...` if too long.

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

> ℹ️ Note: The above are stored as strings. No special formatting is required.\
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

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Unfortunately, there is no built-in feature to transfer data between computers. This feature will be coming soon
in the next iteration of SGSafe. Stay turned.

**Q**: What happens if a case is marked as closed?

**A**: Closed cases are still visible in the list but are marked with `[C]` instead of `[O]`.

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
| **Exit**        | `bye`                                                                                                 | `bye`                                                                                                                      |

## Coming Soon
- Data transfer between computers
- Support for multiple formats for date input
- More advanced search and filter options
- Escape character for `--` in input values