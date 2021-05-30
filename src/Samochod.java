

public class Samochod extends Przedmiot{
    private Paliwo paliwko;

    public Samochod( Paliwo paliwko,String nazwa, double rozmiarPrzedm){
        super(nazwa,rozmiarPrzedm);
        this.paliwko=paliwko;
    }

    public String toString() {
        return "|SAMOCHÓD| NAZWA: " + super.getNazwa() + " TYP PALIWA: " + paliwko + " ROZMIAR: " + super.getRozmiarPrzedm();
    }



}
