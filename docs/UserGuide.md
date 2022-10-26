---
layout: page
title: User Guide
---

YellowBook is a **desktop application for university students** who are involved in many projects to organize their project contacts and tasks.
YellowBook is optimised for use via a Command Line Interface (CLI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `yellowbook.jar` from [here]().

1. Copy the file to the folder you want to use as the _home folder_ for your YellowBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`listC`** : Lists all contacts.

   * **`addC`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to YellowBook.

   * **`deleteC`**`3` : Deletes the 3rd contact shown in the current contact list.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/LABEL]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/LABEL]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `listC`, `listT`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## Section 1: Contacts

### Add a contact: `addC`

Adds a contact to the address book.

Format:  `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]`

* The remark of a contact is optional.

Examples:

* `addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

* `addC n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 r/Weird person.`

### Listing all contacts: `listC`

Shows all contacts stored in the address book.

Format: `listC`

### Deleting a contact: `deleteC`

Removes the specified contact and all its associated information from the address book.

Format: `deleteC INDEX`

* Index of a contact is its index number on the contact list.

* INDEX must be a positive integer more than 0.

Examples:

* `listC` followed by `deleteC 1` deletes the first contact in the address book.

* `findC John` followed by `deleteC 1` deletes the first result of the `findC` command.

### Editing a contact: `editC`

Edits the information fields (e.g. name, mobile number, email address) of an existing contact in the address book.

Format: `editC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]`

* Index of a contact is its index number on the contact list.

* INDEX must be a positive integer more than 0.

* At least one of the optional fields must be provided.

* Input values will replace existing values.

Example:

* `editC 1 n/John p/12345678` edits the first contact’s name to be John and phone number to be 12345678.

### Find a contact: `findC`

Finds a contact using one or more information fields (e.g. name, mobile number, email, and/or address)

Format: `findC [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`

* At least one information field has to be provided.

* The search is case-insensitive, e.g. `dr. doofenshmirtz` will match `Dr. Doofenshmirtz`.

* The order of the keywords does not matter, e.g. `findC n/John p/91231234` will return the same result as `findC p/91231234 n/John`.

* Only full words will be matched. e.g. `John` will not match `Johnny`.

* Contacts matching at least one keyword will be returned. e.g. `n/Perry Dr.`
  will match contacts with name `Perry the Platypus` and `Dr. Doofenshmirtz`.

Example:

* `findC n/john p/81113210 e/john@gmail.com a/123 kent ridge road` will return a contact whose name contains the word `john`, phone number `81113210`, email `john@gmail.com`, and address `123 kent ridge road`.

* `findC n/flynn p/91231234 e/flynn@gmail.com` will return `Candace Flynn` and `Phineas Flynn`, provided they have the same phone and email.


### Filtering contacts by label: `filterC`

Filters contacts whose label(s) contain any of the given keywords.

Format: `filterC KEYWORD [MORE_KEYWORDS]`

* The filter is case-insensitive, e.g. `cs2103t` will match `CS2103T`.

* The order of the keywords does not matter, e.e. `software cs2103t` will match
  `CS2103T Software Engineering`.

* Only the contact's label is filtered.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

* Labels matching at least one keyword will be returned. e.g. `cs2103t cs2101` will match
  `CS2103T Software Engineering` and `CS2101 Effective Communication for Computing Professionals`.

Example:

* `filterC cs2103t` may return a contact with label `CS2103T Software Engineering`.

## Section 2: Tasks
### Listing all tasks: `listT`

Shows all tasks stored in the task list.

Format: `listT`

### Adding Tasks: `addT`

Adds a task to the task list.
The description and deadline of the task are not allowed to be empty. Newly added tasks are marked as not done.
Throws an exception if the description of the task is empty.
Throws an exception if the deadline of the task is empty.
Throws an exception if the deadline of the task is not in dd-mm-yyyy format.

Format: `addT d/DESCRIPTION D/DEADLINE`

Example:

* `addT d/buy milk D/12-09-2022` will add the task "buy milk" with deadline 12 September 2022.

### Marking task as done: `markT`

Marks a task in the task list as done.

Format: `markT INDEX`

* Index of a task is its index number on the task list.

* INDEX must be a positive integer more than 0.

Examples:

* `listT` followed by `markT 1` marks the first task in the displayed task list as done.

* `findT book` followed by `markT 1` marks the first result of the `findT` command as done.

### Marking task as undone: `unmarkT`

Marks a task in the task list as undone.

Format: `unmarkT INDEX`

* Index of a task is its index number on the task list.

* INDEX must be a positive integer more than 0.

Examples:

* `listT` followed by `unmarkT 1` marks the first task in the displayed task list as undone.
* `findT book` followed by `unmarkT 1` marks the first result of the `findT` command as undone.

### Find a task: `findT`

Finds a task using one or more information fields (e.g. description, and/or deadline)

Format: `findT [d/DESCRIPTION] [D/DEADLINE (dd-mm-yyyy)]`

* At least one information field has to be provided.

* The search is case-insensitive, e.g. `homework` will match `HOMEWORK`.

* The order of the keywords does not matter, e.g. `math homework` will match
  `homework math`.

* Only full words will be matched. e.g. `math` will not match `mathematics`.

* Task fields matching at least one keyword will be returned. e.g. `d/cs2103t cs2101` will match
  `cs2103t tutorial` and `cs2101 reflection`.

Example:

* `findT d/cs2103t D/25-12-2022` will return tasks with description containing `cs2103t` and deadline `25th December 2022`.

### Filtering tasks by label: `filterT`

Filters tasks whose label(s) contain any of the given keywords.

Format: `filterT KEYWORD [MORE_KEYWORDS]`

* The filter is case-insensitive, e.g. `cs2103t` will match `CS2103T`.

* The order of the keywords does not matter, e.e. `software cs2103t` will match
  `CS2103T Software Engineering`.

* Only the tasks's label is filtered.

* Only full words will be matched. e.g. `cs2103t` will not match `cs2103`.

* Labels matching at least one keyword will be returned. e.g. `cs2103t cs2101` will match
  `CS2103T Software Engineering` and `CS2101 Effective Communication for Computing Professionals`.

Example:

* `filterT cs2103t` may return a tasks with label `CS2103T Software Engineering`.

## Section 3: Labels

### Listing all labels: `listL`

Shows a list of all existing labels in the address book.

### Adding a label to a contact/task: `addL`

Adds a label to an existing contact/task in YellowBook. Each contact/task can have multiple labels. 
At the same time, the label is added to the label list, shown under the "tags" tab of the app. 
This list is unique, meaning each label with a distinct name is only shown once, even if more than one contact/task has the same label.

Multiple labels can be added in the same command. 
However, only a maximum of one contact and one task can be labelled within the same command.

Throws an exception if: 
- Contact/task does not exist
- Contact/task already has the required label
- No label is provided
- No contact/task is provided
- More than one contact or more than one task is specified

Format: `addL c/INDEX t/INDEX l/label_NAME`

Example:

* `addL c/3 t/12 l/CS2103T` will add the label "CS2103T" to the 3rd contact on the contact list and 12th task on the task list.

### Removing a label from a contact/task: `deleteL`

Removes a label from an existing contact/task in YellowBook. 

If contact/task is last remaining contact/task with said label, label is removed from the label list. 
Otherwise, it is only removed from the specified contact/task label list.

Multiple labels can be deleted in the same command. 
However, only a maximum of one contact and one task can be edited within the same command.

Throws an exception if:
- Contact/task does not exist
- Label does not exist on specified contact/task
- No contact/task is provided
- No label is provided
- More than one contact or more than one task is specified

Format: `deleteL t/INDEX l/label_NAME`

Example:

* `deleteL t/14 l/CS2101` will remove the label "CS2101" from the 14th task on the task list.

## Automatic tab switching

Depending on the command you enter, you will see the open tab in the GUI switch automatically.
For example, when using a task-related command, the tab switches to "Task" and the task list is displayed.

The result of the entered command is displayed.
For example, after adding a new contact, the list shown on the GUI is the updated list with your new contact included.

## YellowBook data
### Saving the data

YellowBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

YellowBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, YellowBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous YellowBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action      | Format, Examples                                                                                                                                       |
|-------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| **listC**   | `listC`                                                                                                                                                |
| **addC**    | `addC n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]` <br> e.g., `addC n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| **deleteC** | `deleteC INDEX` <br> e.g., `deleteC 1`                                                                                                                 |
| **editC**   | `editC INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK]` <br> e.g., `editC 1 n/John p/12345678`                                               |
| **findC**   | `findC KEYWORD [MORE_KEYWORDS]` <br> e.g., `findC Phineas Ferb`                                                                                        |
| **filterC** | `filterC KEYWORD [MORE_KEYWORDS]` <br> e.g., `filterT cs2103t`                                                                                         |                                                                                                 |
| **listT**   | `listT`                                                                                                                                                |
| **addT**    | `addT d/DESCRIPTION D/DEADLINE`                                                                                                                        |
| **markT**   | `markT INDEX` <br> e.g., `mark 1`                                                                                                                      |
| **unmarkT** | `unmarkT INDEX` <br> e.g., `unmark 1`                                                                                                                  |
| **findT**   | `findT KEYWORD [MORE_KEYWORDS]` <br> e.g., `findT cs2103t`                                                                                             |
| **filterT** | `filterT KEYWORD [MORE_KEYWORDS]` <br> e.g., `filterT cs2103t`                                                                                         |
| **listL**   | `listL`                                                                                                                                                |
| **addL**    | `addL c/INDEX n/LABEL_NAME` OR  `addL t/INDEX n/LABEL_NAME`                                                                                            |
| **deleteL** | `deleteL c/INDEX n/LABEL_NAME` OR `deleteL t/INDEX n/LABEL_NAME`                                                                                       |
