package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.math.RoundingMode;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

public interface Nekretnina {

    default public BigDecimal izracunajPorez(BigDecimal cijenaNekretnine) {
	BigDecimal iznosPoreza = new BigDecimal(0);
	BigDecimal minimalnaCijenaNekretnine = new BigDecimal(10000);
	if (cijenaNekretnine.compareTo(minimalnaCijenaNekretnine) < 0) {
		throw new CijenaJePreniskaException("Cijena nekretnine ne smije biti manja od 10000kn" );
	}
	
	iznosPoreza=(cijenaNekretnine.multiply(new BigDecimal(3)).divide(new BigDecimal(100),RoundingMode.HALF_UP));
	
	return iznosPoreza;

    }
}
