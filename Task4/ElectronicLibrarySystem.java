import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Book {
    private String title;
    private String author;
    private String category;
    private boolean isAvailable;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void borrowBook(String username, String title) {
        User user = findUser(username);
        Book book = findBook(title);
        if (user != null && book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println(username + " has borrowed '" + title + "'.");
        } else {
            System.out.println("Book not available or user not found.");
        }
    }

    public void returnBook(String username, String title) {
        User user = findUser(username);
        Book book = findBook(title);
        if (user != null && book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println(username + " has returned '" + title + "'.");
        } else {
            System.out.println("Book not borrowed by the user or book not found.");
        }
    }

    private User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }
}

public class ElectronicLibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Add users
        library.addUser(new User("user1", "password1"));
        library.addUser(new User("user2", "password2"));

        // Add books
        library.addBook(new Book("Java Programming", "John Doe", "Programming"));
        library.addBook(new Book("Data Structures and Algorithms", "Jane Smith", "Programming"));
        library.addBook(new Book("Introduction to Algorithms", "Thomas Cormen", "Programming"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"));

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter your username:");
                    String username = scanner.nextLine();
                    System.out.println("Enter your password:");
                    String password = scanner.nextLine();

                    User currentUser = null;
                    for (User user : library.getUsers()) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                            currentUser = user;
                            break;
                        }
                    }

                    if (currentUser != null) {
                        System.out.println("Enter the title of the book you want to borrow:");
                        String titleToBorrow = scanner.nextLine();
                        library.borrowBook(currentUser.getUsername(), titleToBorrow);
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 2:
                    System.out.println("Enter your username:");
                    username = scanner.nextLine();
                    System.out.println("Enter your password:");
                    password = scanner.nextLine();

                    currentUser = null;
                    for (User user : library.getUsers()) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                            currentUser = user;
                            break;
                        }
                    }

                    if (currentUser != null) {
                        System.out.println("Enter the title of the book you want to return:");
                        String titleToReturn = scanner.nextLine();
                        library.returnBook(currentUser.getUsername(), titleToReturn);
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 3:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
