/**
 * 
 */
package part01;

/**
 * Represents a book with various attributes
 * If  an attribute is invalid it sets a default value.
 */
public class Book {
	private String title;
	private String isbn;
	private String author;
	private BookType type;
	private int edition;
	private String summary;
	private double price;

	public Book(String title, String author,String isbn, BookType type, int edition, String summary, double price) {
		
		if (title != null && title.length() >= 5 && title.length() < 40) {
			this.title = title;
		} else {
			this.title = "No Title";
		}

		if (author != null && author.length() >= 5 && author.length() < 40) {
			this.author = author;
		} else {
			this.author = "No Author";
		}

		if (isValidISBN(isbn)) {
			this.isbn = isbn;
		} else {
			this.isbn = "No ISBN";
		}
		
		if(type!=null) {
			this.type= type;
		}else {
			System.out.println("Invalid book type provided, setting to default 'Fiction'.");
			this.type= BookType.FICTION;
		}

		if (edition > 0) {
			this.edition = edition;
		} else {
			this.edition = 1;
		}

		if (summary != null && summary.length() >= 20 && summary.length() <= 150) {
			this.summary = summary;
		} else {
			this.summary = "No Summary";
		}

		if (price >= 0.0) {
			this.price = price;
		} else {
			this.price = 0.0;
		}
	}
	
	/**
	 * Sets title of a book given the title length is between 5 and 40 characters.
	 * @param title- the title of the book, if invalid sets title as 'No Title'. 
	 */
	public void setTitle(String title) {
		if (title != null && title.length() >= 5 && title.length() < 40) {
			this.title = title;
		} else {
			this.title = "No Title";
		}
	}
	
	/**
	 * Sets author of a book given the author length is between 5 and 40 characters.
	 * @param title - the title of the book, if invalid sets title as 'No Title'. 
	 */
	public void setAuthor(String author) {
		if (author != null && author.length() >= 5 && author.length() < 40) {
			this.author = author;
		} else {
			this.author = "No Author";
		}

	}
	
	/**
	 * Sets ISBN of a book.
	 * @param isbn - ISBN of the book. If invalid sets isbn as 'No ISBN'.
	 */
	public void setIsbn(String isbn) {
		if (isValidISBN(isbn)) {
			this.isbn = isbn;
		} else {
			this.isbn = "No ISBN";
		}
	}
	
	/**
	 * Sets the book type.
	 * @param type - fiction, non-fiction or reference. If invalid it's set to'Fiction'. 
	 */
	public void setType(BookType type) {
		if(type!=null) {
			this.type= type;
		}else {
			System.out.println("Invalid book type provided, setting to default 'Fiction'.");
			this.type= BookType.FICTION;
		}
	}
	/**
	 * Sets the edition of the book given it is greater than zero.
	 * @param edition - edition of the book. If invalid sets edition to 1.
	 */
	public void setEdition(int edition) {
		if (edition > 0) {
			this.edition = edition;
		} else {
			this.edition = 1;
		}
	}
	/**
	 * Sets the price of the book given it is greater than 0.
	 * @param price - price of the book. If invalid it's set to 0.0.
	 */
	public void setPrice(double price) {
		if (price >= 0.0) {
			this.price = price;
		} else {
			this.price = 0.0;
		}
	}
	
	/**
	 * Sets the summary of the book.
	 * @param summary - summary of the book. If invalid it's set to 'No Summary'.
	 */
	public void setSummary(String summary) {
		if (summary != null && summary.length() >= 20 && summary.length() <= 150) {
			this.summary = summary;
		} else {
			this.title = "No Summary";
		}
	}
	
	/**
	 * Validates the ISBN number to ensure it is a valid 10-digit ISBN.
	 * @param isbn - ISBN to be validated.
	 * @return - 'true' if valid. Otherwise 'False'.
	 */
	private boolean isValidISBN(String isbn) {
		if (isbn == null || isbn.length() != 10) {
			return false;
		}
		for (int i = 0; i < isbn.length(); i++) {
			if (!Character.isDigit(isbn.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns title of the book
	 * @return title of the book.
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Returns author of the book
	 * @return author of the book.
	 */
	public String getAuthor() {
		return this.author;
	}
	
	/**
	 * Returns ISBN of the book
	 * @return isbn of the book.
	 */
	public String getIsbn() {
		return this.isbn;
	}
	
	/**
	 * Returns the book type.
	 * @return the book type.
	 */
	public BookType getType() {
		return this.type;
	}
	
	/**
	 * Returns summary of the book
	 * @return summary of the book.
	 */
	public String getSummary() {
		return this.summary;
	}
	
	/**
	 * Returns the edition of the book
	 * @return edition of the book.
	 */
	public int getEdition() {
		return this.edition;
	}
	
	/**
	 * Returns price of the book
	 * @return price of the book.
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Returns a string representation of the book
	 * @return res - a string representing book details in a readable format. 
	 */
	public String toString() {
		String res;
		res = String.format("Title: %s, ISBN: %s, Author: %s, %d, %s, £%.2f", title, isbn, author, edition, summary,
				price);
		return res;
	}

}
