package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class Automobil extends Artikl implements Vozilo {

    BigDecimal snagaKs;

    public Automobil(String naslov, String opis,BigDecimal snagaKs, BigDecimal cijena ) {
	super(naslov, opis, cijena);
	this.snagaKs = snagaKs;
    }

    public BigDecimal getSnagaKs() {
	return snagaKs;
    }

    public void setSnagaKs(BigDecimal snagaKs) {
	this.snagaKs = snagaKs;
    }

    @Override
    public Integer izracunajGrupuOsiguranja() {
	Integer brojGrupeOsiguranja = 0;
	BigDecimal snagaKw = izracunajKilovate(getSnagaKs());
	Integer snagaCijeliBrojKw =snagaKw.intValue();
	if ((snagaCijeliBrojKw>=55)&&(snagaCijeliBrojKw<70)) {
	    return 1;
	}
	if ((snagaCijeliBrojKw>=70)&&(snagaCijeliBrojKw<100)) {
	    return 2;
	}
	if ((snagaCijeliBrojKw>=100)&&(snagaCijeliBrojKw<130)) {
	    return 3;
	}
	if (snagaCijeliBrojKw>=130) {
	    return 4;
	}
	return brojGrupeOsiguranja;
    }

    @Override
    public String tekstOglasa() {
	String tekstOglasa = ("Naslov automobila: " + getNaslov() + "\nOpis automobila: " + getOpis()
		+ "\nSnaga automobila: " + getSnagaKs() + "\nCijena osiguranja "+izracunajCijenuOsiguranja()+"\nCijena automobila: "+getCijena());
	return tekstOglasa;
    }

}
