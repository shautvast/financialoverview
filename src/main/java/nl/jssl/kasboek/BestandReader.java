package nl.jssl.kasboek;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class BestandReader {
	private final static SimpleDateFormat dateformat = new SimpleDateFormat("yyyymmdd");
	private final BigDecimal ONE_HUNDRED = new BigDecimal("100");

	public List<Transactie> lees(String bestand) {
		List<Transactie> transacties = new ArrayList<>();
		int i = 0;
		try {
			CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream(bestand)), '\t');
			String[] c;
			while ((c = reader.readNext()) != null) {
				Transactie t = new Transactie();
				t.setRekeningnummer(c[0]);
				t.setValuta(c[1]);
				t.setTransactiedatum(maakDatum(c[2]));
				t.setBedragVoor(maakBedrag(c[3]));
				t.setBedragNa(maakBedrag(c[4]));
				t.setDatum(maakDatum(c[5]));
				t.setBedrag(maakBedrag(c[6]));
				System.out.println((++i) + ": " + t.getBedrag());
				t.setOmschrijving(c[7]);
				transacties.add(t);
			}
			reader.close();
			return transacties;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private long maakBedrag(String string) {
		try {
			NumberFormat formatter = NumberFormat.getNumberInstance();
			Number number = formatter.parse(string);
			BigDecimal decimal = BigDecimal.valueOf(number.doubleValue());
			return decimal.multiply(ONE_HUNDRED).longValue();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private Date maakDatum(String c) {
		try {
			return dateformat.parse(c);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	class Transactie {
		private String rekeningnummer;
		private String valuta;
		private Date transactiedatum;
		private long bedragVoor;
		private long bedragNa;
		private Date datum;
		private long bedrag;
		private String type;
		private String incassant;
		private String omschrijving;

		public String getRekeningnummer() {
			return rekeningnummer;
		}

		public void setRekeningnummer(String rekeningnummer) {
			this.rekeningnummer = rekeningnummer;
		}

		public String getValuta() {
			return valuta;
		}

		public void setValuta(String valuta) {
			this.valuta = valuta;
		}

		public Date getTransactiedatum() {
			return transactiedatum;
		}

		public void setTransactiedatum(Date transactiedatum) {
			this.transactiedatum = transactiedatum;
		}

		public long getBedragVoor() {
			return bedragVoor;
		}

		public void setBedragVoor(long bedragVoor) {
			this.bedragVoor = bedragVoor;
		}

		public long getBedragNa() {
			return bedragNa;
		}

		public void setBedragNa(long bedragNa) {
			this.bedragNa = bedragNa;
		}

		public Date getDatum() {
			return datum;
		}

		public void setDatum(Date datum) {
			this.datum = datum;
		}

		public long getBedrag() {
			return bedrag;
		}

		public void setBedrag(long bedrag) {
			this.bedrag = bedrag;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getIncassant() {
			return incassant;
		}

		public void setIncassant(String incassant) {
			this.incassant = incassant;
		}

		public String getOmschrijving() {
			return omschrijving;
		}

		public void setOmschrijving(String omschrijving) {
			this.omschrijving = omschrijving;
		}

	}
}
