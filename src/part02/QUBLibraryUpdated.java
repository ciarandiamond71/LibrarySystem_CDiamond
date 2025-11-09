/**
 * 
 */
package part02;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

import console.Console;

import part01.BookStatus;
import part01.BookType;
import part01.Library;
import part01.LibraryBook;

/**
 * This class represents the QUB Library system. Users can list, list by status,
 * add, remove, borrow, return books and display them in a ranked list.
 */
public class QUBLibraryUpdated {

	private static Console conIn = new Console(true); // Input Console
	private static Console conOut = new Console(false); // Output Console

	private static Library myLibrary;

	private static String[] options = { "List All Books", "List Books by Status", "Add a Book", "Remove a Book",
			"Borrow a Book", "Return a Book", "Display Ranked List", "Settings", "Exit" };

	private static String title = "QUB Library";

	// Initialise the library with books
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

	public static void main(String[] args) {
		// display output console
		myLibrary = initialise();
		conOut.setVisible(true);
		conOut.setSize(800, 400);
		conOut.setLocation(0, 0);
		conOut.setTitle("QUB Library");
		conOut.setBgColour(Color.WHITE);
		conOut.setColour(Color.BLACK);
		conOut.setFont(new Font("Consolas", Font.BOLD, 20));

		// display input console
		conIn.setVisible(true);
		conIn.setSize(500, 500);
		conIn.setLocation(0, 400);
		conIn.setTitle("User Input");
		conIn.setBgColour(Color.WHITE);
		conIn.setColour(Color.BLACK);
		conIn.setFont(new Font("Consolas", Font.BOLD, 20));
		boolean done = false;
		do {
			// Display the menu options to the user
			conIn.clear();
			display();
			int choice = getUserChoice();
			conOut.clear();

			// Execute the corresponding action based on the user's choice
			switch (choice) {
			case 1:
				conOut.clear();
				conIn.clear();
				listAllBooks();
				break;
			case 2:
				conOut.clear();
				conIn.clear();
				listBooksbyStatus();
				break;
			case 3:
				conOut.clear();
				conIn.clear();
				addNewBook();
				break;
			case 4:
				conIn.clear();
				conOut.clear();
				removeBook();
				break;
			case 5:
				conIn.clear();
				conOut.clear();
				borrowBook();
				break;
			case 6:
				conIn.clear();
				conOut.clear();
				returnBook();
				break;
			case 7:
				conIn.clear();
				conOut.clear();
				displayRankedList();
				break;
			case 8:
				conIn.clear();
				conOut.clear();
				settings();
				break;

			case 9:
				done = true;// Exit the loop if the user selects option 8
				break;
			default:
				conIn.println("Invalid Option");
			}
		} while (!done);
		conOut.println("Goodbye!");
		conOut.setVisible(false);// close output console
		conIn.setVisible(false);// close input console
	}

	/**
	 * Displays the menu to the user with the specified title and options.
	 */
	private static void display() {
		if (title != null && options != null) {
			// display title and underline with '+'
			conOut.println(title);
			for (int i = 0; i < title.length(); i++) {
				conOut.print("+");
			}
			conOut.println("\n");

			// display the menu options
			int count = 1;
			for (String str : options) {
				conOut.println(count + ". " + str);
				count++;
			}
			conOut.println();
		} else {
			conOut.println("Menu not defined.");
		}
	}

	/**
	 * Gets user choice for an integer value.
	 * 
	 * @return choice the users integer value inputed.
	 */
	private static int getUserChoice() {
		int choice = -1;
		boolean valid = false;

		while (!valid) {
			conIn.print("Enter choice: ");
			String userInput = conIn.readLn();
			try {
				// change input to integer
				choice = Integer.parseInt(userInput);
				valid = true;
			} catch (NumberFormatException e) {
				conIn.clear();
				conIn.println("Invalid input. Please enter a valid integer.");
			}
		}

		return choice;
	}

	/**
	 * Gets user choice for a double value.
	 * 
	 * @return choice the users double value inputed
	 */
	private static double getDoubleChoice() {
		double choice = -1;
		boolean valid = false;

		while (!valid) {
			conIn.print("Enter choice: ");
			String userInput = conIn.readLn();
			try {
				// change input to double
				choice = Double.parseDouble(userInput);
				valid = true;
			} catch (NumberFormatException e) {
				conIn.clear();
				conIn.println("Invalid input. Please enter a valid double value.");
			}
		}

		return choice;
	}

	/**
	 * Displays a list of books in a library. Allows user to press enter to return
	 * to menu or enter IDto view more details of a book.
	 */
	private static void listAllBooks() {
		conOut.println("List All Books");
		conOut.println("++++++++++++++ \n");

		LibraryBook[] data = myLibrary.getAllBooks();// Store all LibraryBook objects from the library into the data
														// array

		conOut.println(String.format("%-3s %-40s", "ID", "Title"));
		conOut.println(String.format("%-3s %-40s", "--", "-----"));
		for (LibraryBook bk : data) {
			// Print the ID and title of each book in 'data' in a formatted manner
			conOut.println(String.format("%-3s %-40s", bk.getId(), bk.getTitle()));
		}

		conIn.println("HIT ENTER to return to menu or type ID for details.");

		boolean validInput = false;

		// Loop until valid input or enter is pressed
		while (!validInput) {
			String text = conIn.readLn();
			text = text.trim();

			if (text.length() == 0) {
				conOut.clear();
				validInput = true;
			} else {
				try {
					int id = Integer.parseInt(text);

					LibraryBook bk = myLibrary.search(id);

					if (bk != null) {
						conOut.clear();
						conIn.clear();

						ImageIcon img = new ImageIcon("Images\\" + bk.getImage());
						conOut.println(img);
						conOut.println(String.format("%-10s %-2s %-40s", "ID: ", ":", bk.getId()));
						conOut.println(String.format("%-10s %-2s %-40s", "Title", ":", bk.getTitle()));
						conOut.println(String.format("%-10s %-2s %-40s", "Author", ":", bk.getAuthor()));
						conOut.println(String.format("%-10s %-2s %-40s", "ISBN: ", ":", bk.getIsbn()));
						conOut.println(String.format("%-10s %-2s %-40s", "Type", ":", bk.getType()));
						conOut.println(String.format("%-10s %-2s %-40s", "Edition", ":", bk.getEdition()));
						conOut.println(String.format("%-10s %-2s %-40s", "Summary", ":", bk.getSummary()));
						conOut.println(String.format("%-10s %-2s £%.2f", "Price: ", ":", bk.getPrice()));
						conOut.println(String.format("%-10s %-2s %-40s", "Status", ":", bk.getStatus()));
						conOut.println(String.format("%-10s %-2s %-40s", "Loan Count", ":", bk.getLoanCount()));

						conIn.println("HIT ENTER to return to menu.");
						conIn.readLn();
						conOut.clear();
						validInput = true;// leave loop
					} else {

						conIn.clear();
						conIn.println("No book found with that ID. Please try again.");
						conIn.println("HIT ENTER to return to menu or type ID for details.");
					}
				} catch (NumberFormatException e) {
					conIn.clear();
					conIn.println("Invalid ID format. Please enter a valid number.");
					conIn.println("HIT ENTER to return to menu or type ID for details.");
				}
			}
		}

	}

	/**
	 * Users can enter choice for book status (Available, On Loan, or Withdraw).
	 * Lists all books with the chosen status.
	 */
	private static void listBooksbyStatus() {
		int choice = 0;
		//loop until user input is valid (integer between 1-3)
		while (choice < 1 || choice > 3) {
			conOut.println("List Books by Status");
			conOut.println("++++++++++++++++++++ \n");
			conOut.println(
					"--->Enter status of books you would like to list (1 for Available, 2 for On Loan, 3 for Withdraw).");
			choice = getUserChoice();
			conIn.clear();

			if (choice < 1 || choice > 3) {
				conIn.println("Invalid choice. Choice must be between 1 and 3.");
				conOut.clear();
			}
		}

		BookStatus status = BookStatus.fromChoice(choice);

		LibraryBook[] booksArray = myLibrary.list(status);

		if (booksArray.length == 0) {
			conOut.println("\nNo books found with status: " + status);
		} else {
			conOut.println("\nBooks with status: " + status + "\n");
			conOut.println(String.format("%-3s %-40s %-10s", "ID", "Title", "Status"));
			conOut.println(String.format("%-3s %-40s %-10s", "--", "-----", "------"));
			for (LibraryBook book : booksArray) {
				// Print the ID, title, and status of each book in 'booksArray' with formatted output
				conOut.println(String.format("%-3s %-40s %-10s", book.getId(), book.getTitle(), book.getStatus()));
			}
		}
		conIn.println("HIT ENTER to return to menu.");
		conIn.readLn();
		conOut.clear();
	}

	/**
	 * Prompts the user to enter details for a new book and adds it to the library's
	 * collection
	 */
	private static void addNewBook() {
		conOut.println("Add a Book");
		conOut.println("++++++++++\n");
		conOut.println("---> Enter Title (5-40 characters): ");
		conOut.println("\nIf input is invalid it will be set to 'No Title'.");
		conIn.println("Enter: ");
		String title = conIn.readLn();
		conIn.clear();
		conOut.clear();

		conOut.println("---> Enter Author (5-40 characters): ");
		conOut.println("\nIf input is invalid, it will be set to 'No Author'.");
		conIn.println("Enter: ");
		String author = conIn.readLn();
		conIn.clear();
		conOut.clear();

		conOut.println("---> Enter ISBN (Must be 10 digits): ");
		conOut.println("\nIf input is invalid, it will be set to 'No ISBN'.");
		conIn.println("Enter: ");
		String isbn = conIn.readLn();
		conIn.clear();
		conOut.clear();

		conOut.println("---> Enter Book Type (1 for Fiction, 2 for Non-Fiction, 3 for Reference)");
		int bookTypeChoice = 0;
		
		//loop until input is valid (integer between 1-3)
		while (bookTypeChoice < 1 || bookTypeChoice > 3) {
			bookTypeChoice = getUserChoice();
			conIn.clear();

			if (bookTypeChoice < 1 || bookTypeChoice > 3) {
				conIn.println("Invalid choice, please enter a number between 1 and 3.");
			}
		}
		BookType type = BookType.fromChoice(bookTypeChoice);
		conOut.clear();

		conOut.println("---> Enter Edition (Must be greater than zero): ");
		conOut.println("\nIf input is invalid, it will be set to '1'.");
		int edition = getUserChoice();
		conIn.clear();

		conOut.clear();
		conOut.println("--->Enter Summary (20-150 characters): ");
		conOut.println("\nIf input is invalid, it will be set to 'No Summary'.");
		conIn.println("Enter: ");
		String summary = conIn.readLn();
		conIn.clear();

		conOut.clear();
		conOut.println("--->Enter Price (Must be greater than zero): ");
		conOut.println("\nIf input is invalid, it will be set to '£0.00.'");
		double price = getDoubleChoice();
		conIn.clear();

		conOut.clear();
		conOut.println("--->Enter Image: ");
		conOut.println("If input is invalid. No image will be set.");
		conIn.println("Enter: ");
		String image = conIn.readLn();
		conIn.clear();

		conOut.clear();

		LibraryBook bk = new LibraryBook(title, author, isbn, type, edition, summary, price, image);

		boolean success = myLibrary.add(bk);
		conIn.println("HIT ENTER to return to menu.");

		if (success) {
			conOut.println("Book was successfully added to the library!");
			conOut.println();
		} else {
			conOut.println("There was an issue. Failed to add the book.");
			conOut.println();
		}
		conIn.readLn();
		conOut.clear();
	}

	/**
	 * Prompts the user to remove a book from the library by providing its ID.
	 */
	private static void removeBook() {
		conOut.println("Remove a Book");
		conOut.println("+++++++++++++\n");
		conOut.println("--->Enter ID of the Book you would like to remove: ");
		LibraryBook[] data = myLibrary.getAllBooks();
		conOut.println();
		conOut.println(String.format("%-3s %-40s", "ID", "Title"));
		conOut.println(String.format("%-3s %-40s", "--", "-----"));
		
		for (LibraryBook bk : data) {
			// Print the ID and title of each book in a formatted manner
			conOut.println(String.format("%-3s %-40s", bk.getId(), bk.getTitle()));
		}
		
		int id = getUserChoice();
		conIn.clear();
		boolean result = myLibrary.remove(id);
		conIn.println("HIT ENTER to return to menu.");
		if (result) {
			conOut.println("\nBook with ID: " + id + " was removed");
		} else {
			conOut.println("\nBook with ID: " + id + " does not exist");
		}

		conIn.readLn();
		conOut.clear();
	}

	/**
	 * Prompts the user to borrow a book from the library by providing its ID.
	 */
	private static void borrowBook() {
		conOut.println("Borrow a Book");
		conOut.println("+++++++++++++\n");
		conOut.println("--->Enter ID of the Book you would like to borrow: ");
		
		LibraryBook[] data = myLibrary.getAllBooks();
		
		conOut.println();
		conOut.println(String.format("%-3s %-40s", "ID", "Title"));
		conOut.println(String.format("%-3s %-40s", "--", "-----"));
		for (LibraryBook bk : data) {
			// Print the ID and title of each book in a formatted manner
			conOut.println(String.format("%-3s %-40s", bk.getId(), bk.getTitle()));
		}
		int id = getUserChoice();
		conIn.clear();

		boolean result = myLibrary.borrowBook(id);
		conIn.println("HIT ENTER to return to menu.");
		if (result) {
			conOut.println("Book with ID: " + id + " was borrowed successfully!");
		} else {
			conOut.println("Book with ID:" + id + " not borrowed successfully.");
		}
		conIn.readLn();
		conOut.clear();
	}

	/**
	 * Prompts the user to return a book from the library by providing its ID.
	 */
	private static void returnBook() {
		conOut.println("Return a Book");
		conOut.println("+++++++++++++\n");
		conOut.println("--->Enter ID of the Book you would like to return: ");

		LibraryBook[] data = myLibrary.getAllBooks();
		conOut.println();
		conOut.println(String.format("%-3s %-40s", "ID", "Title"));
		conOut.println(String.format("%-3s %-40s", "--", "-----"));
		
		for (LibraryBook bk : data) {
			// Print the ID and title of each book in a formatted manner
			conOut.println(String.format("%-3s %-40s", bk.getId(), bk.getTitle()));
		}

		int id = getUserChoice();
		conIn.clear();

		boolean result = myLibrary.returnBook(id);
		conIn.println("HIT ENTER to return to menu.");

		if (result) {
			conOut.println("\nBook with ID: " + id + " was returned successfully!");
		} else {
			conOut.println("\nBook with ID:" + id + " was not returned successfully ");
		}
		conIn.readLn();
		conOut.clear();
	}

	/**
	 * Displays a ranked list of all books by popularity (Loan Count).
	 */
	private static void displayRankedList() {
		conOut.println("Display Ranked List");
		conOut.println("+++++++++++++++++++\n");

		LibraryBook[] booksArray = myLibrary.mostPopular();
		if (booksArray == null || booksArray.length == 0) {
			conOut.println("No books available in the library.");
		} else {
			conOut.println("Most Popular Books (Ranked by Loan Count)");
			conOut.println(String.format("%-10s %-10s", "Loan Count", "Title"));
			conOut.println(String.format("%-10s %-10s", "----------", "-----"));

			for (LibraryBook book : booksArray) {
				// Print the loan count and title of each book in 'booksArray' in a formatted manner
				conOut.println(String.format("%-10s %-10s", book.getLoanCount(), book.getTitle()));
			}
		}

		conIn.println("HIT ENTER to return to the menu.");
		conIn.readLn();

		conOut.clear();
		conIn.clear();

	}

	/**
	 * Displays and manages the settings menu for the user, including options for
	 * changing the appearance, font,font size and the option to exit settings.
	 */
	private static void settings() {
		conOut.clear();
		conOut.println("Settings");
		conOut.println("++++++++ \n");
		conOut.println("1. Appearance");
		conOut.println("2. Change Font");
		conOut.println("3. Change Font Size");
		conOut.println("4. Exit Settings");

		int choice = getUserChoice();

		switch (choice) {
		case 1:
			conIn.clear();
			appearance();
			break;
		case 2:
			changeFont();
			break;
		case 3:
			conIn.clear();
			changeFontSize();
			break;
		case 4:
			conOut.clear();
			conIn.clear();
			break;
		default:
			conOut.clear();
			conIn.clear();
			conOut.println("Invalid option, please return to menu.");
			conIn.println("HIT ENTER to return to the menu.");
			conIn.readLn();
			conOut.clear();
		}

	}

	/**
	 * Displays the appearance settings menu, allowing the user to choose between
	 * light mode, dark mode or a QUB colour scheme. Once a choice is made, the
	 * appropriate appearance settings are applied.
	 */
	private static void appearance() {
		conOut.clear();
		conOut.println("Select Appearance");
		conOut.println("++++++++++++++++++");
		conOut.println("1. Light Mode");
		conOut.println("2. Dark Mode");
		conOut.println("3. QUB Colour scheme (Red Background, White text)");

		int appearanceChoice = getUserChoice();
		conIn.clear();
		conOut.clear();

		switch (appearanceChoice) {
		case 1:
			conOut.setColour(Color.BLACK);
			conOut.setBgColour(Color.WHITE);

			conIn.setColour(Color.BLACK);
			conIn.setBgColour(Color.WHITE);

			conOut.println("Appearance set to 'Light'.");
			conIn.println("HIT ENTER to return to the menu.");
			conIn.readLn();
			conOut.clear();
			break;
		case 2:
			conOut.setColour(Color.WHITE);
			conOut.setBgColour(Color.BLACK);

			conIn.setColour(Color.WHITE);
			conIn.setBgColour(Color.BLACK);

			conOut.println("Appearance set to 'Dark'.");
			conIn.println("HIT ENTER to return to the menu.");
			conIn.readLn();
			conOut.clear();
			break;
		case 3:
			conOut.setColour(Color.WHITE);
			conOut.setBgColour(Color.RED);

			conIn.setColour(Color.WHITE);
			conIn.setBgColour(Color.RED);

			conOut.println("Appearance set to 'QUB'.");
			conIn.println("HIT ENTER to return to the menu.");
			conIn.readLn();
			conOut.clear();
			break;
		default:
			conOut.println("Invalid color choice.");
			conIn.println("HIT ENTER to return to the menu.");
			conIn.readLn();
			conOut.clear();
			break;
		}
	}

	/**
	 * Displays the font settings menu, allowing the user to choose a font, Once a
	 * choice is made, the appropriate appearance settings are applied.
	 */
	private static void changeFont() {
		conOut.clear();
		conIn.clear();
		conOut.println("Choose a font:");
		conOut.println("1. Arial");
		conOut.println("2. Times New Roman");
		conOut.println("3. Courier New");
		conOut.println("4. Verdana");
		conOut.println("5. Consolas");

		int choice = getUserChoice();

		String fontName = "";
		switch (choice) {
		case 1:
			fontName = "Arial";
			break;
		case 2:
			fontName = "Times New Roman";
			break;
		case 3:
			fontName = "Courier New";
			break;
		case 4:
			fontName = "Verdana";
			break;
		case 5:
			fontName = "Consolas";
			break;
		default:
			conOut.clear();
			conIn.clear();
			conOut.println("Invalid option. Font not changed.");
			conIn.println("HIT ENTER to return to the menu.");
			conIn.readLn();
			conOut.clear();
			return;//return to menu
		}

		int currentFontSize = conOut.getFont().getSize();

		conOut.setFont(new Font(fontName, Font.BOLD, currentFontSize));
		conIn.setFont(new Font(fontName, Font.BOLD, currentFontSize));

		conOut.clear();
		conIn.clear();
		conOut.println("Font changed to " + fontName + ".");
		conIn.println("HIT ENTER to return to the menu.");
		conIn.readLn();
		conOut.clear();
	}

	/**
	 * Displays the font size settings menu, allowing the user to choose between 20,
	 * 30 or 40 Once a choice is made, the appropriate appearance settings are
	 * applied.
	 */
	private static void changeFontSize() {
		conOut.clear();
		conOut.println("Choose a font size option:");
		conOut.println("1. Font size 20");
		conOut.println("2. Font size 30");
		conOut.println("3. Font size 40");

		int choice = getUserChoice();
		conIn.clear();

		int fontSize = 0;

		switch (choice) {
		case 1:
			fontSize = 20;
			break;
		case 2:
			fontSize = 30;
			break;
		case 3:
			fontSize = 40;
			break;
		default:
			conOut.clear();
			conOut.println("Invalid option. Font size not changed.");
			conIn.println("HIT ENTER to return to the menu.");
			conIn.readLn();
			conOut.clear();
			return; //return to menu
		}
	
		conOut.setFont(new Font(conOut.getFont().getName(), Font.BOLD, fontSize));
		conOut.println("Font size changed to " + fontSize + ".");
		conIn.println("HIT ENTER to return to the menu.");
		conIn.readLn();
		conOut.clear();
		
	
	}

}
