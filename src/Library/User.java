package Library;



import java.util.ArrayList;
import java.util.List;


    class Book {
        private String title;
        private String author;
        private String isbn;
        private boolean isBorrowed;

        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.isBorrowed = false;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getIsbn() {
            return isbn;
        }

        public boolean isBorrowed() {
            return isBorrowed;
        }

        public void setBorrowed(boolean borrowed) {
            isBorrowed = borrowed;
        }

        @Override
        public String toString() {
            return "Book [Title=" + title + ", Author=" + author + ", ISBN=" + isbn + ", Borrowed=" + isBorrowed + "]";
        }
    }


    class Patron {
        private String name;
        private String patronId;
        private List<Book> borrowedBooks;

        public Patron(String name, String patronId) {
            this.name = name;
            this.patronId = patronId;
            this.borrowedBooks = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public String getPatronId() {
            return patronId;
        }

        public List<Book> getBorrowedBooks() {
            return borrowedBooks;
        }

        public void borrowBook(Book book) {
            if (!book.isBorrowed()) {
                borrowedBooks.add(book);
                book.setBorrowed(true);
            } else {
                throw new IllegalStateException("Book is already borrowed.");
            }
        }

        public void returnBook(Book book) {
            if (borrowedBooks.remove(book)) {
                book.setBorrowed(false);
            } else {
                throw new IllegalStateException("This book was not borrowed by the patron.");
            }
        }

        @Override
        public String toString() {
            return "Patron [Name=" + name + ", Patron ID=" + patronId + ", Borrowed Books=" + borrowedBooks + "]";
        }
    }


    class Library {
        private String name;
        private List<Book> books;

        public Library(String name) {
            this.name = name;
            this.books = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public List<Book> getAvailableBooks() {
            List<Book> availableBooks = new ArrayList<>();
            for (Book book : books) {
                if (!book.isBorrowed()) {
                    availableBooks.add(book);
                }
            }
            return availableBooks;
        }

        public Book findBookByISBN(String isbn) {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn)) {
                    return book;
                }
            }
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found.");
        }

        @Override
        public String toString() {
            return "Library [Name=" + name + ", Books=" + books + "]";
        }
    }


    public class App {
        public static void main(String[] args) {
            Library library = new Library("City Library");


            library.addBook(new Book("1984", "George Orwell", "123456789"));
            library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "987654321"));
            library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "456789123"));

            Patron patron = new Patron("Isaac", "P001");


            Book bookToBorrow = library.findBookByISBN("libya1234");
            patron.borrowBook(bookToBorrow);
            System.out.println("After borrowing:");
            System.out.println(library.getAvailableBooks());


            patron.returnBook(bookToBorrow);
            System.out.println("After returning:");
            System.out.println(library.getAvailableBooks());
        }
    }

}
