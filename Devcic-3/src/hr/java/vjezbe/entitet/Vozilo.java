package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public interface Vozilo {

    default public BigDecimal izracunajKilovate(BigDecimal snagaKs) {
	BigDecimal snagaKw = new BigDecimal(0);
	snagaKw = snagaKs.multiply(new BigDecimal(0.735499));
	return snagaKw;
    }

    public Integer izracunajGrupuOsiguranja();

    default public BigDecimal izracunajCijenuOsiguranja() {

	BigDecimal iznosOsiguranja = new BigDecimal(0);
	
	    switch (izracunajGrupuOsiguranja()) {
	    //5 grupa osiguranja
	    case 0:
		iznosOsiguranja = new BigDecimal(300);
		break;
	    case 1:
		iznosOsiguranja = new BigDecimal(600);
		break;
	    case 2:
		iznosOsiguranja = new BigDecimal(900);
		break;
	    case 3:
		iznosOsiguranja = new BigDecimal(1200);
		break;
	    case 4:
		iznosOsiguranja = new BigDecimal(1500);
		break;
	    }
	return iznosOsiguranja;

    }
}
