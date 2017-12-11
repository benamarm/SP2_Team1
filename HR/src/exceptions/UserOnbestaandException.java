package exceptions;

@SuppressWarnings("serial")
public class UserOnbestaandException extends Exception{

	public void displayErrorMessage() {
		System.out.println("De user die u zojuist probeerde te raadplegen bestaat niet.");
	}
}
