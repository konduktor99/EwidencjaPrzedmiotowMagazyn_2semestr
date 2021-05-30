

public class Rower extends Przedmiot {
    private int liczbaPrzerzutek;

    public Rower(int liczbaPrzerzutek, String nazwa, double rozmiarPrzedm) {
        super(nazwa, rozmiarPrzedm);
        this.liczbaPrzerzutek = liczbaPrzerzutek;
    }

    public String toString() {
        return "|ROWER| NAZWA: " + super.getNazwa() + " LICZBA PRZERZUTEK: " + liczbaPrzerzutek + " ROZMIAR: " + super.getRozmiarPrzedm();
    }


}