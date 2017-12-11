//Om te kijken of de klasse zelf goed werkt.

package testing;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import logic.Adres;
import logic.Event;

public class JUnitAdresTest {

	
	
	
	
	@Test
	public void constructorTest() {
		Event e = new Event();
		Set<Event> events = new HashSet<Event>();
		events.add(e);
		Adres a = new Adres();
		a.setStraat("Teststraat");
		a.setNummer(1);
		a.setPostcode(1840);
		a.setLand("Belgie"); //Voor de veiligheid heb ik hier geen ë gebruikt.
		a.setEvents(events);
		assertEquals("Teststraat",a.getStraat());
		assertEquals(1, a.getNummer());
		assertEquals(1840, a.getPostcode());
		assertEquals("Belgie", a.getLand());
		assertEquals(events,a.getEvents());
		
		
		
	}

}
