# Student Record Management System (CLI)

## Project Overview

This is a simple, command-line interface (CLI) based Student Record Management System built in Java. It allows users to manage student records, including adding new students, viewing all records, updating existing records, deleting records, searching by name, and sorting records by various criteria. The data is persisted to a text file, ensuring that student information is saved even after the application is closed.

This project demonstrates core Java programming concepts, object-oriented programming (OOP) principles, data structures (`ArrayList`), file I/O for data persistence, and basic error handling, making it a robust and user-friendly console application.

## Features

* **Add New Student:** Add new student records with a unique auto-generated ID, name, and marks.
* **View All Students:** Display all existing student records in a formatted list.
* **Update Student Record:** Modify the name and/or marks of an existing student using their ID.
* **Delete Student Record:** Remove a student's record using their ID, with a confirmation prompt to prevent accidental deletions.
* **Search Student by Name:** Find students whose names contain a specific search string (case-insensitive).
* **Sort Students:** Sort student records by ID, Name, or Marks, in either ascending or descending order.
* **Data Persistence:** All student data is automatically saved to and loaded from a `students.txt` file, ensuring data is not lost upon application exit.
* **Robust Input Validation:** Handles invalid input gracefully (e.g., non-numeric input where numbers are expected, empty names, marks out of range).

## Technologies Used

* **Language:** Java
* **Core Libraries:** `java.util.ArrayList`, `java.util.Scanner`, `java.io.*` (BufferedReader, BufferedWriter, FileReader, FileWriter), `java.util.Collections`, `java.util.Comparator`.

## How to Run the Application

To run this application on your local machine, follow these steps:

1.  **Prerequisites:**
    * Java Development Kit (JDK) 8 or higher installed on your system.
    * A code editor (like VS Code) or an Integrated Development Environment (IDE) to compile and run Java code.

2.  **Clone the Repository (or Download):**
    * **Using Git:**
        ```bash
        github  https://github.com/Aditichoubey-adi/StudentRecordSystemtem.git)
        ```
        
    * **Direct Download:**
        You can also download the project as a ZIP file from GitHub and extract it.

3.  **Navigate to the Project Directory:**
    Open your terminal or command prompt and navigate to the extracted project folder:
    ```bash
    cd StudentRecordSystem
    ```
    (or wherever you extracted/cloned the project)

4.  **Compile the Java Code:**
    Compile both `Student.java` and `StudentRecordSystem.java` files. Ensure both files are in the same directory.
    ```bash
    javac *.java
    ```

5.  **Run the Application:**
    After successful compilation, run the main application:
    ```bash
    java StudentRecordSystem
    ```

--- Student Record Management System ---

Add New Student
View All Students
Update Student Record
Delete Student Record
Search Student by Name
Sort Students
Exit Enter your choice (1-7):
<!-- end list -->


## Future Enhancements (Potential Ideas)

* Implement search by marks range.
* Add basic reporting features (e.g., total students, average marks, highest/lowest marks).
* Improve UI for larger datasets (e.g., pagination for viewing students).
* Implement more complex data structures if performance becomes an issue with very large datasets.
* Explore GUI development (e.g., JavaFX or Swing) for a visual interface.
