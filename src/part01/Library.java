/**
 * 
 */
package part01;

import java.util.ArrayList;

/**
 * Represents a library that manages a collection of books
 */
public class Library {

	private ArrayList<LibraryBook> books;

	public Library() {
		books = new ArrayList<LibraryBook>();

	}

	/**
	 * Using Book ID a book is borrowed and checked if available for checkout. It's
	 * then marked as checked out.
	 * 
	 * @param id The ID of the book being borrowed.
	 * @return true if book is borrowed and false if it is not.
	 */
	public boolean borrowBook(int id) {
		LibraryBook book = search(id);
		if (book != null) {
			if (book.checkout()) {
				System.out.println("You've successfully borrowed: " + book.getTitle());
				return true;
			} else {
				System.out.println(book.getTitle() + " is not available for checkout.");
				return false;
			}
		}
		System.out.println("Book with ID " + id + " not found.");
		return false;
	}

	/**
	 * Using Book ID a book is returned and checked if it's on loan for check in.
	 * It's then marked as checked in.
	 * 
	 * @param id The ID of book being returned
	 * @return true if book is returned and false if it is not.
	 */
	public boolean returnBook(int id) {
		LibraryBook book = search(id);
		if (book != null) {
			if (book.checkin()) {
				System.out.println("You've successfully returned: " + book.getTitle());
				return true;
			} else {
				System.out.println(book.getTitle() + " was not borrowed.");
				return false;
			}
		}
		System.out.println("Book with ID " + id + " not found.");
		return false;
	}

	/**
	 * Adds book to the Library with a unique ID
	 * 
	 * @param bk LibraryBook Object which is being added to Library
	 * @return true if book is added successfully, false if book ID is already
	 *         exists or is null
	 */
	public boolean add(LibraryBook bk) {
		if (bk != null) {
			LibraryBook temp = search(bk.getId());
			if (temp == null) {
				books.add(bk);
				return true;
			}

		}
		return false;
	}

	/**
	 * Iterates through the list of books in the library and looks for a book whose
	 * ID matches the provided ID.
	 * 
	 * @param id The ID of the target book being searched.
	 * @return LibraryBook Object that has a matching id or null if book isn't
	 *         found.
	 */
	public LibraryBook search(int id) {
		LibraryBook target = null;
		//retrieve book at index
		for (int index = 0; index < books.size(); index++) {
			LibraryBook bk = books.get(index);
			//check if book's id is equal target id
			if (bk.getId() == id) {
				target = bk;
				break;//exit loop
			}
		}
		return target;
	}

	/**
	 * Removes book from the Library, identified by its ID.
	 * 
	 * @param bk LibraryBook Object which is being removed from Library.
	 * @return true if book is removed successfully, false if not.
	 */
	public boolean remove(int id) {
		LibraryBook bk = search(id);
		if (bk != null) {
			return books.remove(bk);
		}
		return false;
	}

	/**
	 * Creates a LibraryBook array and populates it with all LibraryBooks in
	 * Library.
	 * 
	 * @return an array of all LibraryBook objects in the library.
	 */
	public LibraryBook[] list() {
		LibraryBook[] bookArray = new LibraryBook[books.size()];

		for (int i = 0; i < books.size(); i++) {
			// Copy each book from the 'books' list into the 'bookArray' at the corresponding index
			bookArray[i] = books.get(i);
		}
		return bookArray;

	}

	/**
	 * Lists all the LibraryBooks that match the given BookStatus.
	 * 
	 * @param status The BookStatus filters the books by AVAILABLE, ON_LOAN,
	 *               WITHDRAWN).
	 * @return An array of LibraryBook objects that match the given status.
	 */
	public LibraryBook[] list(BookStatus stat) {
		ArrayList<LibraryBook> filteredBooks = new ArrayList<>();

		for (int i = 0; i < books.size(); i++) {
			//retrieve book at index
			LibraryBook bk = books.get(i);

			// Check if the book's status matches the given status
			if (bk.getStatus() == stat) {
				//add to filtered list
				filteredBooks.add(bk);
			}
		}
		LibraryBook[] bookArray = new LibraryBook[filteredBooks.size()];

		for (int i = 0; i < filteredBooks.size(); i++) {
			// Copy each book from the 'filteredBooks' list into the 'bookArray' at the corresponding index
			bookArray[i] = filteredBooks.get(i);
		}

		return bookArray;

	}
	
	/**
	 * Lists all the LibraryBooks ordered by their Loan Count.
	 * @return An array of LibraryBook objects sorted by their Loan Count .
	 */
	public LibraryBook[] mostPopular() {
	int n = books.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				// Compare the loan count of the current book with the next one
				if (books.get(j).getLoanCount() < books.get(j + 1).getLoanCount()) {
					// Swap books if the current one has less loans than the next
					LibraryBook temp = books.get(j); 
	                books.remove(j); 
	                books.add(j, books.get(j)); 
	                books.remove(j + 1); 
	                books.add(j + 1, temp);
				}
			}
		}
		LibraryBook[] sortedBooks = new LibraryBook[books.size()];
		for (int i = 0; i < books.size(); i++) {
			// Copy each book from the 'books' list into the 'sortedBooks' array at the corresponding index
			sortedBooks[i] = books.get(i);
		}

		return sortedBooks;

	}
	/**
	 * Returns an array of all books currently in the library.
	 * @return data - array of all books currently in the library.
	 */
	public LibraryBook[] getAllBooks() {
		LibraryBook[] data = new LibraryBook[books.size()];
		int index = 0;
		for(LibraryBook bk:books) {
			// Add book to 'data' array and move to next index
			data[index]= bk;
			index++;
		}
		return data;
	}

}
