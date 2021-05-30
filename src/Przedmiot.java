
public class Przedmiot<T extends Przedmiot> implements Comparable<T>{

    private String nazwa;
    private double rozmiarPrzedm;

    public  Przedmiot(String nazwa, double rozmiarPrzedm){

        this.nazwa=nazwa;
        this.rozmiarPrzedm=rozmiarPrzedm;
    }


    public String getNazwa() {
        return nazwa;
    }


    public double getRozmiarPrzedm() {
        return rozmiarPrzedm;
    }
    public void setRozmiarPrzedm(Double rozmiarPrzedm) {
        this.rozmiarPrzedm=rozmiarPrzedm;
    }



    @Override
    public int compareTo(T o) {
        if(this.rozmiarPrzedm<o.getRozmiarPrzedm())
        return 1;
        if(this.rozmiarPrzedm>o.getRozmiarPrzedm())
            return -1;
        else{
            for(int i=0;i<(nazwa.length()<o.getNazwa().length()?nazwa.length():o.getNazwa().length());i++) {
                if (nazwa.charAt(i) < o.getNazwa().charAt(i))
                    return 1;
                if (nazwa.charAt(i) > o.getNazwa().charAt(i))
                    return -1;
            }
            return nazwa.length()<=o.getNazwa().length()? 1:-1;


        }

    }
   public String toString(){
        return "NAZWA: "+nazwa+" ROZMIAR: "+ rozmiarPrzedm;
   }

}
