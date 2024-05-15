

import java.util.Scanner;

// Create a class for the Library
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

class Library {
    private Map<Integer, Book> books;

    public Library() {
        books = new HashMap<>();
    }

    public void addBooksFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int booksAdded = 0;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length == 3) {
                    int id = Integer.parseInt(bookData[0].trim());
                    String title = bookData[1].trim();
                    String author = bookData[2].trim();
                    if (!books.containsKey(id)) {
                        books.put(id, new Book(id, title, author));
                        booksAdded++;
                    } else {
                        System.out.println("Book with ID " + id + " already exists in the collection. Skipping...");
                    }
                } else {
                    System.out.println("Invalid book data format: " + line);
                }
            }
            System.out.println("Added " + booksAdded + " books to the collection.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void removeBook(int bookId) {
        if (books.containsKey(bookId)) {
            books.remove(bookId);
            System.out.println("Book with ID " + bookId + " has been removed from the collection.");
        } else {
            System.out.println("Book with ID " + bookId + " not found in the collection.");
        }
    }

    public void listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("The collection is empty.");
        } else {
            System.out.println("Books in the collection:");
            for (Book book : books.values()) {
                System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
    }
}

public class Module2SDLCAssignment {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add books from file");
            System.out.println("2. Remove a book");
            System.out.println("3. List all books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the file path: ");
                    String filePath = scanner.nextLine();
                    library.addBooksFromFile(filePath);
                    break;
                case 2:
                    System.out.print("Enter the book ID to remove: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    library.removeBook(bookId);
                    break;
                case 3:
                    library.listAllBooks();
                    break;
                case 4:
                    System.out.println("Exiting Library Management System...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}