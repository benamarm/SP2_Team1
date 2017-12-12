package exceptions;

public class BoekNietGevondenException extends Exception {
	public void displayErrorMessage() {
		System.out.println("Dit boek lag niet op zijn plaats zoals beloofd. Het ontbreekt in deze opleiding.");
	}
}
