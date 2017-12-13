package testing;

import email.Email;

import java.io.IOException;
import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;

public class EmailTest {

	@Before
	public void setUp() {
		new EmailTest();
	}

	@Test
	public void testSendInRegualarJavaMail() throws MessagingException, IOException {

		if (Email.sendPassword("wout.ampe@student.ehb.be", "test12345") == true) {
			System.out.println("Sent message successfully....");
		} else {
			System.out.println("Sending message failed....");
		}
	}
}