// StudentRecordSystem.java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections; // NEW: For sorting
import java.util.Comparator;  // NEW: For custom sorting logic
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentRecordSystem {

    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;
    private static final String DATA_FILE = "students.txt";

    public static void main(String[] args) {
        loadStudentsFromFile();

        boolean running = true;

        while (running) {
            System.out.println("\n--- Student Record Management System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student Record");
            System.out.println("4. Delete Student Record");
            System.out.println("5. Search Student by Name");
            System.out.println("6. Sort Students"); // NEW: Sort option
            System.out.println("7. Exit");          // Option number changed
            System.out.print("Enter your choice (1-7): "); // Prompt updated

            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the remaining newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7."); // Prompt updated
                scanner.nextLine(); // Clear the invalid input from the scanner buffer
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudentByName();
                    break;
                case 6: // NEW case for Sort Students
                    sortStudents();
                    break;
                case 7: // Exit choice updated to 7
                    running = false;
                    saveStudentsToFile();
                    System.out.println("Exiting Student Record System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7."); // Prompt updated
            }
        }
        scanner.close();
    }

    // --- NEW: Method to Sort Students ---
    private static void sortStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        System.out.println("\n--- Sort Students ---");
        System.out.println("Sort by:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Marks");
        System.out.print("Enter your choice (1-3): ");

        int sortByChoice = 0;
        try {
            sortByChoice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
            scanner.nextLine();
            return;
        }

        System.out.println("Order:");
        System.out.println("1. Ascending (A-Z, 1-100)");
        System.out.println("2. Descending (Z-A, 100-1)");
        System.out.print("Enter your choice (1-2): ");

        int orderChoice = 0;
        try {
            orderChoice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter 1 or 2.");
            scanner.nextLine();
            return;
        }

        Comparator<Student> comparator = null;

        switch (sortByChoice) {
            case 1: // Sort by ID
                comparator = Comparator.comparing(Student::getId); // Lambda for ID
                break;
            case 2: // Sort by Name
                comparator = Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER); // Case-insensitive name sort
                break;
            case 3: // Sort by Marks
                comparator = Comparator.comparing(Student::getMarks); // Lambda for Marks
                break;
            default:
                System.out.println("Invalid sort by choice. Sorting cancelled.");
                return;
        }

        if (comparator != null) {
            if (orderChoice == 2) { // Descending order
                comparator = comparator.reversed();
            } else if (orderChoice != 1) { // Invalid order choice, but still sort ascending
                System.out.println("Invalid order choice. Defaulting to Ascending.");
            }
            
            Collections.sort(studentList, comparator); // List ko sort karo
            System.out.println("Students sorted successfully.");
            viewStudents(); // Sorted list ko dikhao
        }
    }


    // --- Existing Methods (No change in logic, just re-arranged for readability) ---

    private static void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            int maxId = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        double marks = Double.parseDouble(parts[2].trim());
                        studentList.add(new Student(id, name, marks));
                        if (id > maxId) {
                            maxId = id;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing line in file (Number Format): " + line);
                    }
                } else {
                    System.err.println("Skipping malformed line in file: " + line);
                }
            }
            nextId = maxId + 1;
            System.out.println("Loaded " + studentList.size() + " students from " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("No existing student data found. Starting with an empty record system.");
        }
    }

    private static void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Student student : studentList) {
                writer.write(student.getId() + "," + student.getName() + "," + student.getMarks());
                writer.newLine();
            }
            System.out.println("Saved " + studentList.size() + " students to " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Error saving student data to file: " + e.getMessage());
        }
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty. Please try again.");
            return;
        }

        System.out.print("Enter student marks: ");
        double marks = 0;
        try {
            marks = scanner.nextDouble();
            scanner.nextLine();
            if (marks < 0 || marks > 100) {
                System.out.println("Marks must be between 0 and 100.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid marks input. Please enter a valid number.");
            scanner.nextLine();
            return;
        }

        Student newStudent = new Student(nextId, name, marks);
        studentList.add(newStudent);
        System.out.println("Student added successfully with ID: " + nextId);
        nextId++;
        saveStudentsToFile();
    }

    private static void viewStudents() {
        System.out.println("\n--- All Student Records ---");
        if (studentList.isEmpty()) {
            System.out.println("No students enrolled yet.");
            return;
        }
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student Record ---");
        System.out.print("Enter Student ID to update: ");
        int idToUpdate = 0;
        try {
            idToUpdate = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid ID input. Please enter a valid number.");
            scanner.nextLine();
            return;
        }

        Student foundStudent = null;
        for (Student student : studentList) {
            if (student.getId() == idToUpdate) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent);
            System.out.print("Enter new name (leave blank to keep current: '" + foundStudent.getName() + "'): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                foundStudent.setName(newName);
            }

            System.out.print("Enter new marks (leave blank to keep current: " + foundStudent.getMarks() + "): ");
            String marksStr = scanner.nextLine().trim();
            if (!marksStr.isEmpty()) {
                try {
                    double newMarks = Double.parseDouble(marksStr);
                    if (newMarks < 0 || newMarks > 100) {
                        System.out.println("Marks must be between 0 and 100. Marks not updated.");
                    } else {
                        foundStudent.setMarks(newMarks);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid marks input. Marks not updated.");
                }
            }
            System.out.println("Student record updated successfully.");
            saveStudentsToFile();
        } else {
            System.out.println("Student with ID " + idToUpdate + " not found.");
        }
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student Record ---");
        System.out.print("Enter Student ID to delete: ");
        int idToDelete = 0;
        try {
            idToDelete = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid ID input. Please enter a valid number.");
            scanner.nextLine();
            return;
        }

        final int finalIdToDelete = idToDelete;

        Student studentToRemove = null;
        for (Student s : studentList) {
            if (s.getId() == finalIdToDelete) {
                studentToRemove = s;
                break;
            }
        }

        if (studentToRemove != null) {
            System.out.print("Are you sure you want to delete student " + studentToRemove.getName() + " (ID: " + finalIdToDelete + ")? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (confirmation.equals("yes")) {
                boolean removed = studentList.removeIf(student -> student.getId() == finalIdToDelete);
                if (removed) {
                    System.out.println("Student with ID " + finalIdToDelete + " deleted successfully.");
                    saveStudentsToFile();
                } else {
                    System.out.println("Error: Could not delete student (unexpected).");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("Student with ID " + finalIdToDelete + " not found.");
        }
    }

    private static void searchStudentByName() {
        System.out.println("\n--- Search Student by Name ---");
        System.out.print("Enter student name (or part of name) to search: ");
        String searchName = scanner.nextLine().trim().toLowerCase();

        if (searchName.isEmpty()) {
            System.out.println("Search name cannot be empty.");
            return;
        }

        ArrayList<Student> foundStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getName().toLowerCase().contains(searchName)) {
                foundStudents.add(student);
            }
        }

        if (foundStudents.isEmpty()) {
            System.out.println("No students found with name containing '" + searchName + "'.");
        } else {
            System.out.println("\n--- Search Results for '" + searchName + "' ---");
            for (Student student : foundStudents) {
                System.out.println(student);
            }
        }
    }
}