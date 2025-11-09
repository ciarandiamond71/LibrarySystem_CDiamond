/**
 * 
 */
package part01;

/**
 * Enum representing the possible type values for a book (FICTION/NON_FICTION/REFERENCE).
 */
public enum BookType {
	FICTION("Fiction"), NON_FICTION("Non-Fiction"), REFERENCE("Reference");

	private String typeStr;

	private BookType(String type) {
		typeStr = type;
	}

	public String toString() {
		return typeStr;
	}

	/**
	 * Converts an integer 1, 2, 3 to a specific BookType.
	 * 
	 * @param choice - 1, 2, or 3
	 * @return BookType or null if the choice is invalid
	 */
	public static BookType fromChoice(int choice) {
		switch (choice) {
		case 1:
			return FICTION;
		case 2:
			return NON_FICTION;
		case 3:
			return REFERENCE;
		default:
			return null;
		}
	}
}
