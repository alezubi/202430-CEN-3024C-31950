
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//Alejandro Zubillaga , CEN-3024C-31950 05/16/2024
//This class will encapsulate the book information (ID, title, and author) and provide methods to access and modify the book data.
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

//Alejandro Zubillaga , CEN-3024C-31950 05/16/2024
//This class will be responsible for managing the collection of books. It will have the following methods:
//addBooksFromFile(String filePath): This method will read the book information from the provided text file and add the books to the collection.
//removeBook(int bookId): This method will remove a book from the collection using its unique ID number.
//listAllBooks(): This method will display the list of all books currently in the collection.
class Library {
    private Map<Integer, Book> books;

    public Library() {
        books = new HashMap<>();
    }
  //addBooksFromFile(String filePath): Read book data from file and add to collection
  //filePath (String): The file path of the text file containing the book information.
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
//removeBook(int bookId): Remove a book from the collection
//bookId (int): The unique ID number of the book to be removed from the collection.
    public void removeBook(int bookId) {
        if (books.containsKey(bookId)) {
            books.remove(bookId);
            System.out.println("Book with ID " + bookId + " has been removed from the collection.");
        } else {
            System.out.println("Book with ID " + bookId + " not found in the collection.");
        }
    }
//listAllBooks(): Display the list of all books in the collection
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

//Alejandro Zubillaga , CEN-3024C-31950 05/16/2024
//main controller
//The Library Management System (LMS) is a console-based application designed to manage the collection of books in a library. The key features and functionality of the LMS are:
//Add new books to the collection from a text file: The system should be able to read book information (unique ID, title, and author) from a text file and add the books to the collection.
//Remove a book from the collection using its ID number: The system should allow users to remove a book from the collection by providing the book's unique ID number.
//List all books currently in the collection: The system should display a list of all the books currently in the collection, including their ID, title, and author.
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
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the file path: ");
                    String filePath = scanner.nextLine();
                    library.addBooksFromFile(filePath);
                    break;
                case 2:
                    System.out.print("Enter the book ID to remove: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();
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