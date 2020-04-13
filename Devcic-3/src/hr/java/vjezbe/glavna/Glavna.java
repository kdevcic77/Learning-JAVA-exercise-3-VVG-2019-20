package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Kategorija;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.entitet.Usluga;

public class Glavna {
    private static final String FORMAT_DATUMA = "dd.MM.yyyy.";

    public static void main(String[] args) {

	Scanner ucitavac = new Scanner(System.in);

	System.out.print("Unesite broj korisnika koje želite unijeti: ");
	int brojKorisnika = ucitavac.nextInt();
	ucitavac.nextLine();

	Korisnik[] korisnici = new Korisnik[brojKorisnika];
	for (int i = 0; i < brojKorisnika; i++) {
	    System.out.println("Unesite tip " + (i + 1) + ". korisnika");
	    int odabraniBrojTipaKorisnika = 0;
	    do {
		odabraniBrojTipaKorisnika = 1;
		System.out.println(odabraniBrojTipaKorisnika + ". Privatni");
		System.out.println((odabraniBrojTipaKorisnika + 1) + ". Poslovni");
		System.out.print("Odabir ->> ");
		odabraniBrojTipaKorisnika = ucitavac.nextInt();
		ucitavac.nextLine();
	    } while (odabraniBrojTipaKorisnika != 1 && odabraniBrojTipaKorisnika != 2);
	    if (odabraniBrojTipaKorisnika == 1) {

		korisnici[i] = unesiPrivatnogKorisnika(ucitavac, i + 1);
	    }
	    if (odabraniBrojTipaKorisnika == 2) {

		korisnici[i] = unesiPoslovnogKorisnika(ucitavac, i + 1);
	    }

	}

	for (int j = 0; j < 100; j++) {
	    System.out.print("-");
	}
	System.out.println();
	System.out.print("Unesite broj kategorija koje želite unijeti: ");
	int brojKategorija = ucitavac.nextInt();
	ucitavac.nextLine();
	Kategorija[] kategorije = new Kategorija[brojKategorija];

	for (int k = 0; k < kategorije.length; k++) {
	    kategorije[k] = unesiKategoriju(ucitavac, k);

	}
	System.out.print("Unesite broj artikala koji su aktivno na prodaju: ");
	int brojAktivnihOglasa = ucitavac.nextInt();
	ucitavac.nextLine();
	Prodaja[] aktivneProdaje = new Prodaja[brojAktivnihOglasa];

	for (int i = 0; i < aktivneProdaje.length; i++) {
	    aktivneProdaje[i] = unesiProdaju(ucitavac, i, korisnici, kategorije);
	}

	ucitavac.close();

	System.out.println("Trenutno su oglasi na prodaju:");
	for (int j = 0; j < 100; j++) {
	    System.out.print("-");
	}
	for (Prodaja aktivnaprodaja : aktivneProdaje) {
	    System.out.println("\n" + aktivnaprodaja.getArtikl().tekstOglasa());
	    LocalDate datumObjave = aktivnaprodaja.getDatumObjave();
	    String datumObjaveString = datumObjave.format(DateTimeFormatter.ofPattern(FORMAT_DATUMA));
	    System.out.println("Datum objave: " + datumObjaveString);
	    System.out.println(aktivnaprodaja.getKorisnik().dohvatiKontakt());
	    for (int j = 0; j < 100; j++) {
		System.out.print("-");
	    }
	}
    }

    private static Prodaja unesiProdaju(Scanner ucitavac, int i, Korisnik[] korisnici, Kategorija[] kategorije) {
	Integer redniBrojKorisnika = 0;
	System.out.println("Odaberite korisnika: ");
	for (int j = 0; j < korisnici.length; j++) {
	    System.out.println((j + 1) + ". " + korisnici[j].dohvatiKontakt());
	}
	System.out.print("Odabir korisnika >> ");
	redniBrojKorisnika = ucitavac.nextInt();
	ucitavac.nextLine();
	Korisnik odabraniKorisnik = korisnici[redniBrojKorisnika - 1];

	Integer redniBrojKategorije = 0;
	System.out.println("Odaberite kategoriju: ");
	for (int j = 0; j < kategorije.length; j++) {
	    System.out.println((j + 1) + ". " + kategorije[j].getNaziv());
	}
	System.out.print("Odabir kategorije >> ");
	redniBrojKategorije = ucitavac.nextInt();
	ucitavac.nextLine();
	Kategorija odabranaKategorija = kategorije[redniBrojKategorije - 1];

	Artikl[] artikliKategorije = new Artikl[kategorije.length];
	artikliKategorije = odabranaKategorija.getArtikli();
	Integer redniBrojArtikla = 0;

	System.out.println("Odaberite artikl:");
	for (int j = 0; j < odabranaKategorija.getArtikli().length; j++) {
	    System.out.println((j + 1) + ". " + artikliKategorije[j].getNaslov());
	}
	System.out.print("Odabir artikla >> ");
	redniBrojArtikla = ucitavac.nextInt();
	ucitavac.nextLine();
	Artikl odabraniArtikl = artikliKategorije[redniBrojArtikla - 1];

	LocalDate datumObjave = LocalDate.now();
	return new Prodaja(odabraniArtikl, odabraniKorisnik, datumObjave);
    }

    private static Usluga unesiUslugu(Scanner ucitavac, int i) {
	System.out.print("Unesite naslov " + i + ". oglasa usluge: ");
	String naslov = ucitavac.nextLine();
	System.out.print("Unesite opis " + i + ". oglasa usluge: ");
	String opis = ucitavac.nextLine();
	System.out.print("Unesite cijenu " + i + ". oglasa usluge: ");
	BigDecimal cijena = ucitavac.nextBigDecimal();
	ucitavac.nextLine();
	return new Usluga(naslov, opis, cijena);

    }

    private static Automobil unesiAutomobil(Scanner ucitavac, int j) {
	System.out.print("Unesite naslov " + j + ". oglasa automobila: ");
	String naslov = ucitavac.nextLine();
	System.out.print("Unesite opis " + j + ". oglasa automobila: ");
	String opis = ucitavac.nextLine();
	System.out.print("Unesite snagu (u ks) " + j + ". oglasa automobila: ");
	BigDecimal snagaKs = ucitavac.nextBigDecimal();
	ucitavac.nextLine();
	System.out.print("Unesite cijenu " + j + ". oglasa automobila: ");
	BigDecimal cijena = ucitavac.nextBigDecimal();
	ucitavac.nextLine();
	return new Automobil(naslov, opis, snagaKs, cijena);
    }

    private static Kategorija unesiKategoriju(Scanner ucitavac, int i) {
	System.out.print("Unesite naziv " + (i + 1) + ". kategorije: ");
	String naziv = ucitavac.nextLine();
	naziv = naziv.substring(0, 1).toUpperCase() + naziv.substring(1).toLowerCase();
	System.out.print("Unesite broj artikala koji želite unijeti za unesenu kategoriju: ");
	int brojArtikalaKategorije = ucitavac.nextInt();
	ucitavac.nextLine();
	Artikl[] artikliKategorije = new Artikl[brojArtikalaKategorije];
	for (int j = 0; j < artikliKategorije.length; j++) {
	    System.out.println("Unesite tip " + (j + 1) + ". artikla");
	    int k = 0;
	    do {
		k = 1;
		System.out.println(k + ". Usluga");
		System.out.println((k + 1) + ". Automobili");
		System.out.print("Odabir ->> ");
		k = ucitavac.nextInt();
		ucitavac.nextLine();
	    } while (k != 1 && k != 2);
	    if (k == 1) {
		artikliKategorije[j] = unesiUslugu(ucitavac, j + 1);
	    }
	    if (k == 2) {
		artikliKategorije[j] = unesiAutomobil(ucitavac, j + 1);
	    }
	}
	return new Kategorija(naziv, artikliKategorije);
    }

    private static PrivatniKorisnik unesiPrivatnogKorisnika(Scanner ucitavac, int i) {
	System.out.print("Unesite ime " + i + ". osobe: ");
	String ime = ucitavac.nextLine();
	ime = ime.substring(0, 1).toUpperCase() + ime.substring(1).toLowerCase();
	System.out.print("Unesite prezime " + i + ". osobe: ");
	String prezime = ucitavac.nextLine();
	prezime = prezime.substring(0, 1).toUpperCase() + prezime.substring(1).toLowerCase();
	System.out.print("Unesite email " + i + ". osobe: ");
	String email = ucitavac.nextLine();
	System.out.print("Unesite telefon " + i + ". osobe: ");
	String telefon = ucitavac.nextLine();
	return new PrivatniKorisnik(ime, prezime, email, telefon);
    }

    private static PoslovniKorisnik unesiPoslovnogKorisnika(Scanner ucitavac, int i) {
	System.out.print("Unesite naziv " + i + ". tvrtke: ");
	String naziv = ucitavac.nextLine();
	naziv = naziv.substring(0, 1).toUpperCase() + naziv.substring(1).toLowerCase();
	System.out.print("Unesite e-Mail " + i + ". tvrtke: ");
	String email = ucitavac.nextLine();
	System.out.print("Unesite web " + i + ". tvrtke: ");
	String web = ucitavac.nextLine();
	System.out.print("Unesite telefon " + i + ". tvrtke: ");
	String telefon = ucitavac.nextLine();
	return new PoslovniKorisnik(naziv, email, web, telefon);
    }

}
