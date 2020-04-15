package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja entitet vozila koji je definiran naslovom , opisom, snagom u
 * konjskim satima i cijenom
 * 
 * @author deva
 * @version Devcic-3
 */
public class Automobil extends Artikl implements Vozilo {

    BigDecimal snagaKs;

    /**
     * Inicijalizira podatak o naslovu, opisu, snazi u konjskim satima i cijeni
     * automobila
     * 
     * @param naslov  - podatak o naslovu automobila
     * @param opis    - podatak o opisu automobila
     * @param snagaKs - podatak o snazi automobila u konjskim snagama
     * @param cijena  - podatak o cijeni automobila
     */
    public Automobil(String naslov, String opis, BigDecimal snagaKs, BigDecimal cijena) {
	super(naslov, opis, cijena);
	this.snagaKs = snagaKs;
    }

    public BigDecimal getSnagaKs() {
	return snagaKs;
    }

    public void setSnagaKs(BigDecimal snagaKs) {
	this.snagaKs = snagaKs;
    }

    /**
     * Izraèunavanje grupe osiguranja na temelju pretvorbe konjskih snaga u kilovate
     * i rangiranjem automobila u jednu od 5 grupa osiguranja zavisno od broja
     * kilovata snage vozila
     */
    @Override
    public Integer izracunajGrupuOsiguranja() {
	Integer brojGrupeOsiguranja = 0;
	BigDecimal snagaKw = izracunajKilovate(getSnagaKs());
	Integer snagaCijeliBrojKw = snagaKw.intValue();
	if ((snagaCijeliBrojKw >= 55) && (snagaCijeliBrojKw < 70)) {
	    return 1;
	}
	if ((snagaCijeliBrojKw >= 70) && (snagaCijeliBrojKw < 100)) {
	    return 2;
	}
	if ((snagaCijeliBrojKw >= 100) && (snagaCijeliBrojKw < 130)) {
	    return 3;
	}
	if (snagaCijeliBrojKw >= 130) {
	    return 4;
	}
	return brojGrupeOsiguranja;
    }

    /**
     * Pretvaranje pojedinaènih podataka o naslovu, opisu, snage, cijene osiguranja
     * i cijene automobila u znakovni niz za lakše predstavljanje oglasa automobila
     */
    @Override
    public String tekstOglasa() {
	String tekstOglasa = ("Naslov automobila: " + getNaslov() + "\nOpis automobila: " + getOpis()
		+ "\nSnaga automobila: " + getSnagaKs() + "\nCijena osiguranja " + izracunajCijenuOsiguranja()
		+ "\nCijena automobila: " + getCijena());
	return tekstOglasa;
    }

}
