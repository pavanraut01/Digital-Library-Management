import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }
}

class Member {
    String name;
    String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Library {
    Map<String, Book> books = new HashMap<>();
    Map<String, Member> members = new HashMap<>();

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.put(title, book);
    }

    public void addMember(String name, String email) {
        Member member = new Member(name, email);
        members.put(email, member);
    }

    public void issueBook(String title, String email) {
        if (books.containsKey(title) && members.containsKey(email)) {
            Book book = books.get(title);
            if (book.available) {
                book.available = false;
                System.out.println("Book issued successfully!");
            } else {
                System.out.println("Book is not available for issue.");
            }
        } else {
            System.out.println("Invalid book title or member email.");
        }
    }

    public void returnBook(String title) {
        if (books.containsKey(title)) {
            Book book = books.get(title);
            book.available = true;
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Invalid book title.");
        }
    }

    public void displayBooks() {
        System.out.println("Available Books:");
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            Book book = entry.getValue();
            if (book.available) {
                System.out.println(book.title + " by " + book.author);
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Admin module
        library.addBook("Book1", "Author1");
        library.addBook("Book2", "Author2");
        library.addMember("User1", "user1@example.com");

        // User module
        library.displayBooks();
        System.out.println("Enter book title to issue: ");
        String titleToIssue = scanner.nextLine();
        System.out.println("Enter user email: ");
        String userEmail = scanner.nextLine();
        library.issueBook(titleToIssue, userEmail);

        library.displayBooks();
        System.out.println("Enter book title to return: ");
        String titleToReturn = scanner.nextLine();
        library.returnBook(titleToReturn);

        scanner.close();
    }
}
