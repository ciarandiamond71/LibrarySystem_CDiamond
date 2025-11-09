/**
 * 
 */
package part01;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents the QUB Library system. 
 * Users can list, list by status, add, remove, borrow, return books and display them in a ranked list.
 */
public class QUBLibrary {

	private static Library myLibrary;
	private static Scanner input = new Scanner(System.in);
	 private static String[] options = { "List All Books", "List Books by Status", "Add a Book", "Remove a Book",
				"Borrow a Book", "Return a Book", "Display Ranked List", "Exit" };
	    private static String title = "QUB Library";

	/**
	 * Initialises a new library with 5 books.
	 *
	 * @return A Library object containing a set of initialised books.
	 */
	private static Library initialise() {
		Library aLibrary = new Library();
		LibraryBook bk1 = new LibraryBook("The Great Gatsby", "F. Scott Fitzgerald", "9780743273", BookType.FICTION, 1,
				"A novel about the American dream, set in the 1920s.", 10.99, "great_gatsby_cover.jpg");
		LibraryBook bk2 = new LibraryBook("Introduction to Java Programming", "Y. Daniel Liang", "9780134670",
				BookType.NON_FICTION, 10,
				"A comprehensive guide to programming in Java, covering basic to advanced topics.", 79.99,
				"java_programming_cover.jpg");
		LibraryBook bk3 = new LibraryBook("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "9780062316",
				BookType.NON_FICTION, 1,
				"A fascinating exploration of human history from the Stone Age to modern times.", 15.99,
				"sapiens_cover.jpg");
		LibraryBook bk4 = new LibraryBook("The Catcher in the Rye", "J.D. Salinger", "9780316769", BookType.FICTION, 1,
				"A story about the angst and alienation of a teenage boy in New York City.", 8.99,
				"catcher_in_the_rye_cover.jpg");
		LibraryBook bk5 = new LibraryBook("The Art of Computer Programming", "Donald E. Knuth", "9780201896",
				BookType.REFERENCE, 3,
				"A multi-volume work on algorithms and programming techniques in computer science.", 150.00,
				"art_of_computer_programming_cover.jpg");

		aLibrary.add(bk1);
		aLibrary.add(bk2);
		aLibrary.add(bk3);
		aLibrary.add(bk4);
		aLibrary.add(bk5);

		return aLibrary;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		myLibrary = initialise();
		boolean done = false;
		do {
			// Display the menu options to the user
			display();
			int choice = getUserChoice();
			
			// Execute the corresponding action based on the user's choice
			switch (choice) {
			case 1:
				listAllBooks();
				break;
			case 2:
				listBooksbyStatus();
				break;
			case 3:
				addNewBook();
				break;
			case 4:
				removeBook();
				break;
			case 5:
				borrowBook();
				break;
			case 6:
				returnBook();
				break;
			case 7:
				displayRankedList();
				break;
			case 8:
				done = true;// Exit the loop if the user selects option 8
				break;
			default:
				System.out.println("Invalid Option");
			}
		} while (!done);
		System.out.println("Goodbye!");
	}
	
	/**
	 * Displays the menu to the user with the specified title and options.
	 */
	public static void display() {
		if (title != null && options !=null) {
			// title and options are valid
			// display title and underline with '+'
			System.out.println(title);
			for(int i=0;i<title.length();i++) {
				System.out.print("+");
			}
			System.out.println("\n");
			// display the menu options
			// prefix each with an int starting at 1
			int count = 1;
			for(String str : options) {
				System.out.println(count+". "+str);
				count++;
			}
			System.out.println();
		}
		else {
			// title and/or options are not valid
			System.out.println("Menu not defined.");
		}
	}
	
	/**
     * Requests and reads a user choice.
     *
     * @return the user choice (integer)
     */
    private static int getUserChoice() {
        int choice = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter choice: ");
            try {
                choice = input.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.nextLine();
            }
        }

        return choice;
    }
    
    /**
     * Requests and reads a user choice.
     *
     * @return the user choice (double)
     */
    private static double getDoubleChoice() {
        double choice = -1;
        boolean valid = false;

        while (!valid) {
            try {
                choice = input.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid double value.");
                input.nextLine();
            }
        }

        return choice;
    }


	/**
	 * Lists all the current books in the Library.
	 */
	private static void listAllBooks() {
		System.out.println("List All Books.\n");

		LibraryBook[] booksArray = myLibrary.list();
		if (booksArray == null || booksArray.length == 0) {
			System.out.println("No books available in the library.");
		} else {
			for (LibraryBook book : booksArray) {
				 // Print each book in the 'booksArray'
				System.out.println(book);

			}
		}
	}

	/**
	 * Lists the books by their status chosen by the user (Available, On Loan or Withdraw).
	 */
	private static void listBooksbyStatus() {
		System.out.println("List Books by Status.\n");
		int choice = 0;
		while (choice < 1 || choice > 3) {
			try {
				// Keep prompting the user for a valid choice
				System.out.println(
						"--->Enter status of books would you like to list(1 for Available, 2 for On Loan, 3 for Withdraw).");
				choice =getUserChoice();

				if (choice < 1 || choice > 3) {
					// Inform the user if the choice is out of the valid range
					System.out.println("Invalid choice. Choice must be between 1 and 3.");
				}
			} catch (Exception e) {
				// Handle invalid input and clear the invalid input from the scanner
				System.out.println("Invalid Input.Choice must be between 1 and 3. ");
				input.nextLine();//Clear invalid input
			}
		}

		BookStatus status = BookStatus.fromChoice(choice);

		LibraryBook[] booksArray = myLibrary.list(status);

		if (booksArray.length == 0) {
			System.out.println("No books found with status: " + status);
		} else {
			System.out.println("Books with status: " + status + "\n");
			for (LibraryBook book : booksArray) {
				// Print each book from the 'booksArray'
				System.out.println(book);
			}
		}
	}

	/**
	 * Adds new book to the library using the users input.
	 */
	private static void addNewBook() {
		System.out.println("Add a Book.\n");
		System.out.println("---> Enter Title (5-40 characters): ");
		input.nextLine();
		String title = input.nextLine();
		System.out.println("---> Enter Author (5-40 characters): ");
		String author = input.nextLine();
		System.out.println("---> Enter ISBN (Must be 10 digits): ");
		String isbn = input.nextLine();
		System.out.println("---> Enter Book Type (1 for Fiction, 2 for Non-Fiction, 3 for Reference)");
		int bookTypeChoice = 0;
		while (bookTypeChoice < 1 || bookTypeChoice > 3) {
			// Prompt the user for input until they enter a valid choice
			bookTypeChoice = getUserChoice();

			if (bookTypeChoice < 1 || bookTypeChoice > 3) {
				// Inform the user if the input is invalid
				System.out.println("Invalid choice, please enter a number between 1 and 3.");
			}
		}
		BookType type = BookType.fromChoice(bookTypeChoice);

		System.out.println("---> Enter Edition: ");
		int edition = getUserChoice();
		System.out.println("--->Enter Summary (20-150 characters): ");
		input.nextLine();
		String summary = input.nextLine();
		System.out.println("--->Enter Price: ");
		double price = getDoubleChoice();
		input.nextLine();
		System.out.println("--->Enter Image: ");
		String image = input.nextLine();

		LibraryBook bk = new LibraryBook(title, author, isbn, type, edition, summary, price, image);

		boolean success = myLibrary.add(bk);

		if (success) {
			System.out.println("Book was successfully added to the library!");
			System.out.println();
		} else {
			System.out.println("There was an issue. Failed to add the book.");
			System.out.println();
		}
	}

	/**
	 * Removes a book from the library identified by its ID.
	 */
	private static void removeBook() {
		System.out.println("Remove a Book.\n");
		System.out.println("--->Enter ID of the Book you would like to remove: ");
		int id = getUserChoice();
		boolean result = myLibrary.remove(id);
		if (result) {
			System.out.println("Book with ID: " + id + " was removed");

		} else {
			System.out.println("Book with ID: " + id + " does not exist");
		}
		System.out.println();
	}

	/**
	 * Borrows a book from the library identified by its ID.
	 */
	private static void borrowBook() {
		System.out.println("Borrow a Book.\n");
		System.out.println("--->Enter ID of the Book you would like to borrow: ");
		int id = getUserChoice();
		boolean result = myLibrary.borrowBook(id);
		if (result) {
			System.out.println("Book with ID: " + id + " was borrowed successfully!");
		} else {
			System.out.println("Book with ID:" + id + " was not borrowed successfully.");
		}

	}

	/**
	 * Returns a book from the library identified by its ID.
	 */
	private static void returnBook() {
		System.out.println("Return a Book.\n");
		System.out.println("--->Enter ID of the Book you would like to return: ");
		int id = getUserChoice();
		boolean result = myLibrary.returnBook(id);
		if (result) {
			System.out.println("Book with ID: " + id + " was returned successfully!");
		} else {
			System.out.println("Book with ID:" + id + " was not returned successfully.");
		}
	}

	/**
	 * Displays a ranked list of all books by popularity (Loan Count).
	 */
	private static void displayRankedList() {
		System.out.println("Display Ranked List.\n");

		LibraryBook[] booksArray = myLibrary.mostPopular();
		if (booksArray == null || booksArray.length == 0) {
			System.out.println("No books available in the library.");
		} else {
			System.out.println("Most Popular Books (Ranked by Loan Count");
			for (LibraryBook book : booksArray) {
				// Print each book in the 'booksArray'
				System.out.println(book);

			}
		}

	}

}
