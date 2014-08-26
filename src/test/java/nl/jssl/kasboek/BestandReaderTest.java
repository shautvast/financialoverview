package nl.jssl.kasboek;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nl.jssl.kasboek.BestandReader.Transactie;

import org.junit.Test;

public class BestandReaderTest {
	@Test
	public void test() {
		List<Transactie> transacties = new BestandReader().lees("/record.txt");
		Transactie transactie = transacties.get(0);
		assertEquals(transactie.getRekeningnummer(), "495329789");
		assertEquals(transactie.getValuta(), "EUR");
		assertEquals(transactie.getTransactiedatum(), getDatum("20140802"));
		assertEquals(transactie.getBedragVoor(), 213750L);
		assertEquals(transactie.getBedragNa(), 207756L);
		assertEquals(transactie.getDatum(), getDatum("20140802"));
		assertEquals(transactie.getBedrag(), -5994L);
		assertEquals(transactie.getOmschrijving(), "BEA               02.08.14/14.50 ESSOLAFERTE CHAUMONT SUR,PAS017 ");
	}

	private Date getDatum(String string) {
		try {
			return new SimpleDateFormat("yyyymmdd").parse(string);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
