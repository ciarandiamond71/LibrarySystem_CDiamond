/**
 * 
 */
package part01;

/**
 * Enum representing the possible status values for a book (AVAILABLE/ON_LOAN/WITHDRAW).
 */
public enum BookStatus {
	AVAILABLE("Available"), ON_LOAN("On Loan"), WITHDRAW("Withdraw");

	private String statusStr;

	private BookStatus(String status) {
		statusStr = status;
	}

	public String toString() {
		return statusStr;
	}

	/**
	 * Converts an integer 1, 2, 3 to a specific BookStatus.
	 * 
	 * @param choice - 1, 2, or 3
	 * @return BookStatus or null if the choice is invalid
	 */
	public static BookStatus fromChoice(int choice) {
		switch (choice) {
		case 1:
			return AVAILABLE;
		case 2:
			return ON_LOAN;
		case 3:
			return WITHDRAW;
		default:
			return null;
		}
	}
}
