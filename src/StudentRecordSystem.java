import java.sql.*;
import java.util.*;

class Student {
    int studentId;
    String name;
    float grade;

    Student(int studentId, String name, float grade) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return studentId + "," + name + "," + grade;
    }
}

public class StudentRecordSystem {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentDB";
    private static final String USER = "your_mysql_username"; // your MySQL username
    private static final String PASSWORD = "your_mysql_password"; // your MySQL password

    // Establishing database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // Add a student record to the database
    public static void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Grade: ");
        float grade = scanner.nextFloat();

        try (Connection conn = getConnection()) {
            String query = "INSERT INTO students (student_id, name, grade) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentId);
                stmt.setString(2, name);
                stmt.setFloat(3, grade);
                stmt.executeUpdate();
                System.out.println("Student added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // Display all student records
    public static void displayStudents() {
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM students";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    int studentId = rs.getInt("student_id");
                    String name = rs.getString("name");
                    float grade = rs.getFloat("grade");
                    System.out.println("ID: " + studentId + ", Name: " + name + ", Grade: " + grade);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error displaying students: " + e.getMessage());
        }
    }

    // Delete a student record by ID
    public static void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID to delete: ");
        int studentId = scanner.nextInt();

        try (Connection conn = getConnection()) {
            String query = "DELETE FROM students WHERE student_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("Student ID not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    // Update a student's information by ID
    public static void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID to update: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new grade: ");
        float grade = scanner.nextFloat();

        try (Connection conn = getConnection()) {
            String query = "UPDATE students SET name = ?, grade = ? WHERE student_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setFloat(2, grade);
                stmt.setInt(3, studentId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student updated successfully!");
                } else {
                    System.out.println("Student ID not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Student\n2. Display Students\n3. Delete Student\n4. Update Student\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        displayStudents();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        System.out.println("Exiting program.");
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}