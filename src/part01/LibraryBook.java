/**
 * 
 */
package part01;

/**
 * Represents a book in a Library with extra information (ID, status, loan count, cover image)
 */
public class LibraryBook extends Book implements Lendable {

	private int id;
	private static int nextId = 1;
	private String coverImage;
	private BookStatus status;
	private int loanCount;

	/**
     * Constructs a new LibraryBook with the given details.
     *
     * @param title      the title of the book
     * @param author     the author of the book
     * @param isbn       the ISBN of the book
     * @param type       the type of the book (e.g., Fiction, Non-Fiction, Reference)
     * @param edition    the edition of the book
     * @param summary    a brief summary of the book
     * @param price      the price of the book
     * @param coverImage the cover image file name
     */
	public LibraryBook(String title, String author, String isbn, BookType type, int edition, String summary,
			double price, String coverImage) {
		super(title, author, isbn, type, edition, summary, price);
		this.id = nextId;
		nextId++;

		if (coverImage != null) {
			this.coverImage = coverImage;
		} else {
			this.coverImage = "No Image";
		}

		this.status = BookStatus.AVAILABLE;
		this.loanCount = 0;

	}
	
	/**
     * Sets the cover image for the book.
     * If the cover image is null, a default image ("No Image") will be used.
     *
     * @param coverImage the cover image file name.
     */
	public void setImage(String coverImage) {
		if (coverImage != null) {
			this.coverImage = coverImage;
		} else {
			this.coverImage = "No Image";
		}

		this.status = BookStatus.AVAILABLE;
		this.loanCount = 0;
		
	}
	
	/**
     * Returns the ID of the book.
     * 
     * @return the ID of the book.
     */
	public int getId() {
		return this.id;
	}
	
	/**
     * Returns the current book status (e.g., AVAILABLE, ON_LOAN).
     *
     * @return book status.
     */
	public BookStatus getStatus() {
		return this.status;
	}
	
	/**
     * Returns the cover image of the book.
     *
     * @return the cover image of the book.
     */
	public String getImage() {
		return this.coverImage;
	}
	
	/**
     * Returns the book's loan count.
     *
     * @return the book's loan count.
     */
	public int getLoanCount() {
		return this.loanCount;
	}
	
	/**
     * Sets the status of the book.
     * If an status is invalid, it sets to the default AVAILABLE}.
     *
     * @param status the new status for the book.
     */
	public void setStatus(BookStatus status) {
		if (status != null) {
			this.status = status;
		} else {
			System.out.println("Invalid book status provided, setting to default available.");
			status = BookStatus.AVAILABLE;
		}

	}
	
	/**
     * Returns a string representation of the LibraryBook.
     *
     * @return a string containing the book's ID, title, author, status, cover image, and loan count
     */
	@Override
	public String toString() {
		return String.format("ID: %d, %s, Status: %s, Image: %s, Loan Count: %d", id, super.toString(), status,
				coverImage, loanCount);

	}

	/**
     * Checks out the book. 
     * If the book is available, its status is set to ON_LOAN, and the loan count is incremented.
     *
     * @return true if the book was successfully checked out, false otherwise
     */
	@Override
	public boolean checkout() {
		// attempt to checkout
		// (borrow) a book 
		if (this.status == BookStatus.AVAILABLE) {
            this.status = BookStatus.ON_LOAN;
            this.loanCount++;
            return true;
        } else {
            System.out.println("This book is not available for checkout.");
            return false;
        }
		
	}


	/**
     * Checks out the book. 
     * If the book is ON_LOAN, its status is set to AVAILABLE.
     *
     * @return true if the book was successfully checked out, false otherwise.
     */
	@Override
	public boolean checkin() {
		//attempt to check in 
		// (return a book) a book 
		if (this.status == BookStatus.ON_LOAN) {
	        this.status = BookStatus.AVAILABLE;
	        
	        return true;  
	    } else {
	        System.out.println("This book is not on loan, cannot check it in.");
	        return false;
	    }
		
	}
}
