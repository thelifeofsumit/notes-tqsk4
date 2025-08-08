import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Notes App =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    // Method to add a note to the file
    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { // append mode
            fw.write(note + System.lineSeparator());
            System.out.println("Note added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read and display notes from the file
    private static void viewNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n----- Your Notes -----");
            boolean empty = true;
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }
            if (empty) {
                System.out.println("(No notes found)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Please add a note first.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
