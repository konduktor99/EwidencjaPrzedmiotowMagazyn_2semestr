
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws TooManyThingsException, NeverRentException {

        Pomieszczenie pom0 = new Pomieszczenie(45);
        Pomieszczenie pom1 = new Pomieszczenie(30);
        Pomieszczenie pom2 = new Pomieszczenie(35, "Malowanie");
        Pomieszczenie pom3 = new Pomieszczenie(20);
        Pomieszczenie pom4 = new Pomieszczenie(30, "Awaria zamka");
        Pomieszczenie pom5 = new Pomieszczenie(60);
        Pomieszczenie pom6 = new Pomieszczenie(15);
        Pomieszczenie pom7 = new Pomieszczenie(25);
        Pomieszczenie pom8 = new Pomieszczenie(40);
        Pomieszczenie pom9 = new Pomieszczenie(30, "Malowanie");
        Pomieszczenie pom10 = new Pomieszczenie(55);

        Osoba os1 = new Osoba("Konrad", "Dusza", "05.06.99");
        Osoba os2 = new Osoba("Lebron", "James", "30.12.84");
        Osoba os3 = new Osoba("Kevin", "Love", "07.09.88");
        Osoba os4 = new Osoba("Kyrie", "Irving", "23.03.92");
        Osoba os5 = new Osoba("Andre", "Drummond", "10.08.93");

        List<Pomieszczenie> listaPom = new ArrayList<>();
        listaPom.add(pom0);
        listaPom.add(pom1);
        listaPom.add(pom2);
        listaPom.add(pom3);
        listaPom.add(pom4);
        listaPom.add(pom5);
        listaPom.add(pom6);
        listaPom.add(pom7);
        listaPom.add(pom8);
        listaPom.add(pom9);
        listaPom.add(pom10);

        Collections.sort(listaPom);


        List<Osoba> listaOs = new ArrayList<>();
        listaOs.add(os1);
        listaOs.add(os2);
        listaOs.add(os3);
        listaOs.add(os4);
        listaOs.add(os5);


        Motocykl yamaha = new Motocykl(true, "Yamaha", 0.5);
        Motocykl bmw = new Motocykl(true, "Suzuki", 1.5);
        Samochod alfa = new Samochod(Paliwo.diesel, "Mercedes", 1);
        Rower romet = new Rower(7, "Romet", 1);


        wczytaj(listaOs,listaPom);
        Osoba osTmp=wyborOsoby(listaOs);
        while(true){
        System.out.println("*MENU*");
        System.out.println("_ZOBACZ SWOJE DANE_ 1\n_WYSWIETL ZAWARTOSC TWOICH POMIESZCZEN_ 2\n_W?????? PRZEDMIOT_ 3\n_WYJMIJ PRZEDMIOT_ 4\n_WY??WIETL WOLNE POMIESZCZENIA_ 5\n_WYNAJMIJ NOWE POMIESZCZENIE_ 6\n_ZAPISZ_ 7\n_ZMIEN ROZMIAR PRZEDMIOTU_ 8\n_ZMIEN OSOBE_ 9\n_WYJD??_ 0");

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        switch(x) {
            case 1: wypiszDane(osTmp);
                break;
            case 2: wyswietlZawPom(osTmp.listaPomOs);
                break;
            case 3: wlozPrzedmiot(osTmp.listaPomOs);
                break;
            case 4: wyjmijPrzedmiot(osTmp.listaPomOs);
                break;
            case 5: wypiszWolnePom(listaPom);
                break;
            case 6: wynajmijPom(osTmp, listaPom);
                break;
            case 7: zapiszStan(listaOs, listaPom);
                break;
            case 8: zmienRozmiar(osTmp.listaPomOs);;
                break;
            case 9: osTmp=wyborOsoby(listaOs);
                break;
            case 0: System.exit(0);
                break;
        }
    }

    }

    public static void zapiszStan(List<Osoba> listaOs, List<Pomieszczenie> listaPom) {

        try {
            FileWriter fw = new FileWriter("stanMagazynu.txt");
            fw.write("**||SPIS OS??B ORAZ WYNAJ??TYCH PRZEZ NIE POMIESZCZEN||**\n");
            for (Osoba os : listaOs) {

                fw.write(os.toString()+ "\n");

                if(os.listaPomOs.size()==0)
                    fw.write("brak pomieszcze??\n");
                else
                    for (Pomieszczenie pom:os.listaPomOs) {

                        fw.write(pom.toString() + '\n');
                    }
            }
            fw.write("**||SPIS POMIESZCZEN ORAZ ZNAJDUJ??CYCH SI?? W NICH PRZEDMIOT??W||**\n");
            for (Pomieszczenie pom : listaPom){

                fw.write(pom.toString()+"\n");

                if(pom.listaPrzedmiotow.size()==0)
                    fw.write("brak przedmiot??w\n");
                else
                    for (int j = 0; j < pom.listaPrzedmiotow.size(); j++)
                        fw.write(pom.listaPrzedmiotow.get(j).toString()+"\n");


            }
            fw.write("===========================");


            fw.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }



    public static void wczytaj(List<Osoba> listaOs, List<Pomieszczenie> listaPom) {

        File file = new File("stanMagazynu.txt");
        Scanner in = null;

        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        in.nextLine();

        String tab1[];
        String tab2[];
        String linia1;
        String linia2;

        linia1 = in.nextLine();
        tab1 = linia1.split(" ");

        for (Osoba os : listaOs) {


            linia2 = in.nextLine();
            tab2 = linia2.split(" ");

            if (linia2.equals("brak pomieszcze??")) {
                linia1 = in.nextLine();
                tab1 = linia1.split(" ");
            } else {
                while (tab2[0].equals("POMIESZCZENIE")) {

                    int tmp = Integer.parseInt(tab2[2]);

                    for (Pomieszczenie pom : listaPom) {
                        if (tmp == pom.getIdPom())
                            os.wynajmij(pom, tab1[11]);
                    }
                    linia2 = in.nextLine();
                    tab2 = linia2.split(" ");
                }
                linia1 = linia2;
                tab1 = linia1.split(" ");
            }

        }

        in.nextLine();

        for (Pomieszczenie pom : listaPom) {

            linia2 = in.nextLine();
            tab2 = linia2.split(" ");


            if (linia2.equals("brak przedmiot??w"))
                in.nextLine();
            else {
                while (tab2[0].equals("|MOTOCYKL|") || tab2[0].equals("|SAMOCH??D|") || tab2[0].equals("|ROWER|") || tab2[0].equals("NAZWA:")) {
                    System.out.println(linia2 + " X");
                    try {
                        if (tab2[0].equals("|MOTOCYKL|")) {
                            double tmpDouble = Double.parseDouble(tab2[6]);
                            pom.wloz(new Motocykl((tab2[4].equals("posiada") ? true : false), tab2[2], tmpDouble));
                        }
                        if (tab2[0].equals("|SAMOCH??D|")) {
                            double tmpDouble = Double.parseDouble(tab2[7]);
                            if(tab2[5].equals("benzyna"))
                                pom.wloz(new Samochod(Paliwo.benzyna,tab2[2], tmpDouble));
                            if(tab2[5].equals("gaz"))
                                pom.wloz(new Samochod(Paliwo.gaz,tab2[2], tmpDouble));
                            if(tab2[5].equals("diesel"))
                                pom.wloz(new Samochod(Paliwo.diesel,tab2[2], tmpDouble));
                        }
                        if (tab2[0].equals("|ROWER|")) {
                            double tmpDouble = Double.parseDouble(tab2[7]);
                            int tmpInt = Integer.parseInt(tab2[5]);
                            pom.wloz(new Rower(tmpInt, tab2[2], tmpDouble));
                        }
                        if (tab2[0].equals("NAZWA:")) {
                            double tmpDouble = Double.parseDouble(tab2[3]);
                            pom.wloz(new Przedmiot( tab2[1], tmpDouble));
                        }
                    }catch (TooManyThingsException e){};

                    linia2 = in.nextLine();
                    tab2 = linia2.split(" ");
                }

            }

        }

    }

    public static Osoba wyborOsoby(List<Osoba> listaOs) {
        System.out.println("*WYBOR U??YTKOWNIKA*");
        Osoba osTmp;
        int i = 1;
        for (Osoba osoba : listaOs) {
            System.out.println("Aby wybra?? " + osoba.getImie() + " " + osoba.getNazwisko() + " wci??nij " + i);
            i++;
        }
        System.out.println("Aby wyj???? wci??nij 0");
        Scanner scanner = new Scanner(System.in);
        int wybor = scanner.nextInt();
        if (wybor == 0)
            System.exit(0);

        System.out.println("Wybra??e?? " + listaOs.get(wybor - 1).getImie() + " " + listaOs.get(wybor - 1).getNazwisko());
        osTmp = listaOs.get(wybor - 1);

        return osTmp;
    }

    public static void wypiszDane(Osoba osoba) {
        System.out.println(osoba);
        if(osoba.listaPomOs.size()==0)
            System.out.println("Brak pomieszcze?? w posiadaniu.\n");
        else
        System.out.println(osoba.listaPomOs);
    }

    public static void wypiszWolnePom(List<Pomieszczenie> listaPom) {
        for (Pomieszczenie pom : listaPom) {
            if (pom.isJuzWynajete() == false && pom.getPowodWylaczenia()==null)
                System.out.println(pom);
        }

    }

    public static void wlozPrzedmiot(List<Pomieszczenie> listaPomOs) {
        if (listaPomOs.size() == 0)
            System.out.println("Nie masz wyaj??tych pomieszcze??");
        else {

            int i = 1;
            for (Pomieszczenie pom : listaPomOs) {
                System.out.println("Aby w??o??y?? przedmiot do pomieszczenia nr. " + pom.getIdPom() + " wci??nij " + i);
                i++;
            }
            System.out.println("Aby wyj???? wci??nij 0");

            Scanner scanner = new Scanner(System.in);
            int wybPom = scanner.nextInt();
            if (wybPom == 0)
                System.exit(0);

            System.out.println("Aby doda?? samoch??d wci??nij 1\nAby doda?? motocykl wci??nij 2\nAby doda?? rower wci??nij 3\nAby doda?? inny przedmiot wci??nij 4\nAby wyj???? wci??nij 0");
            int wybor = scanner.nextInt();
            if (wybor == 0)
                System.exit(0);
            Scanner scan = new Scanner(System.in);

            System.out.println("Podaj nazwe przedmiotu\nAby wyj???? wci??nij 0");
            String nazwa = scan.nextLine();
            if (nazwa == "0")
                System.exit(0);

            System.out.println("Podaj rozmiar\nAby wyj???? wci??nij 0");
            Double rozmiar = scan.nextDouble();
            if (rozmiar == 0)
                System.exit(0);
            try {
                if (wybor == 1) {
                    System.out.println("Podaj typ paliwa:\ndiesel - wci??nij 1\nbenzyna - wci??nij 2\ngaz - wci??nij 3\nAby wyj???? wci??nij 0");
                    int typ = scanner.nextInt();
                    if (typ == 0)
                        System.exit(0);

                    if (typ == 1)
                        listaPomOs.get(wybPom - 1).wloz(new Samochod(Paliwo.diesel, nazwa, rozmiar));
                    if (typ == 2)
                        listaPomOs.get(wybPom - 1).wloz(new Samochod(Paliwo.benzyna, nazwa, rozmiar));
                    if (typ == 3)
                        listaPomOs.get(wybPom - 1).wloz(new Samochod(Paliwo.gaz, nazwa, rozmiar));
                }

                if (wybor == 2) {
                    System.out.println("Je??li motocykl posiada homologacje wci??nij 1\nJe??li motocykl nie posiada homologacji wci??nij 2\nAby wyj???? wci??nij 0");
                    int homologacja = scanner.nextInt();
                    if (homologacja == 0)
                        System.exit(0);
                    listaPomOs.get(wybPom - 1).wloz(new Motocykl(homologacja == 1 ? true : false, nazwa, rozmiar));
                }
                if (wybor == 3) {
                    System.out.println("Podaj liczbe przerzutek\nAby wyj???? wci??nij 0");
                    int biegi = scanner.nextInt();
                    if (biegi == 0)
                        System.exit(0);
                    listaPomOs.get(wybPom - 1).wloz(new Rower(biegi, nazwa, rozmiar));
                }if (wybor == 4)
                    listaPomOs.get(wybPom - 1).wloz(new Przedmiot( nazwa, rozmiar));
            } catch (TooManyThingsException e) {
                System.out.println("Brak wystarczaj??cej ilo??ci miejsca w pomieszczeniu nr. " + listaPomOs.get(wybPom).getIdPom());
            }
        }

    }

    public static void wyjmijPrzedmiot(List<Pomieszczenie> listaPomOs) {

        if (listaPomOs.size() == 0)
            System.out.println("Nie masz wyaj??tych pomieszcze??");
        else {

            int i = 1;
            for (Pomieszczenie pom : listaPomOs) {
                System.out.println("Aby wyj???? przedmiot z pomieszczenia nr. " + pom.getIdPom() + " wci??nij " + i);
                i++;
            }
            System.out.println("Aby wyj???? wci??nij 0");

            Scanner scanner = new Scanner(System.in);
            int wybPom = scanner.nextInt();

            if (wybPom == 0)
                System.exit(0);

            System.out.println("Wybra??e?? pomieszczenie nr. " + listaPomOs.get(wybPom - 1).getIdPom());

            for (int j = 1; j <= listaPomOs.get(wybPom - 1).listaPrzedmiotow.size(); j++)
                System.out.println("Aby usun???? " + listaPomOs.get(wybPom - 1).listaPrzedmiotow.get(j - 1) + " wci??nij " + j);
            System.out.println("Aby wyj???? wci??nij 0");

            int wybPrzed = scanner.nextInt();

            if (wybPrzed == 0)
                System.exit(0);

            listaPomOs.get(wybPom - 1).wyjmij((Przedmiot) listaPomOs.get(wybPom - 1).listaPrzedmiotow.get(wybPrzed - 1));
        }
    }

    public static void wyswietlZawPom(List<Pomieszczenie> listaPomOs) {

        if (listaPomOs.size() == 0)
            System.out.println("Nie masz wyaj??tych pomieszcze??");
        else {

            int i = 1;
            for (Pomieszczenie pom : listaPomOs) {
                System.out.println("Aby wy??wietli?? przedmioty z pomieszczenia nr. " + pom.getIdPom() + " wci??nij " + i);
                i++;
            }
            System.out.println("Aby wyj???? wci??nij 0");

            Scanner scanner = new Scanner(System.in);
            int wybPom = scanner.nextInt();

            if (wybPom == 0)
                System.exit(0);

            for (int j = 0; j < listaPomOs.get(wybPom - 1).listaPrzedmiotow.size(); j++)
                System.out.println(listaPomOs.get(wybPom - 1).listaPrzedmiotow.get(j));
        }
        System.out.println();

    }


    public static void wynajmijPom(Osoba os, List<Pomieszczenie> listaPom) {

        int i = 1;
        System.out.println("Wolne pomieszczenia: ");
        for (Pomieszczenie pom : listaPom) {
            if (pom.isJuzWynajete() == false) {
                System.out.println("Aby wybra?? " + pom + " wci??nij " + i);
            }
            i++;
        }
        System.out.println("Aby wyj???? wci??nij 0");

        Scanner scanner = new Scanner(System.in);
        int wybPom = scanner.nextInt();

        if (wybPom == 0)
            System.exit(0);

        System.out.println("Podaj dat?? wynajmu");
        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine();

        if (data == "0")
            System.exit(0);

        os.wynajmij(listaPom.get(wybPom - 1), data);
    }



    public static void zmienRozmiar(List<Pomieszczenie> listaPomOs) {

        if (listaPomOs.size() == 0)
            System.out.println("Nie masz wyaj??tych pomieszcze??");
        else {

            int i = 1;
            for (Pomieszczenie pom : listaPomOs) {
                System.out.println("Aby zmieni?? rozmiar przedmiotu z pomieszczenia nr. " + pom.getIdPom() + " wci??nij " + i);
                i++;
            }
            System.out.println("Aby wyj???? wci??nij 0");

            Scanner scanner = new Scanner(System.in);
            int wybPom = scanner.nextInt();

            if (wybPom == 0)
                System.exit(0);

            System.out.println("Wybra??e?? pomieszczenie nr. " + listaPomOs.get(wybPom - 1).getIdPom());

            for (int j = 1; j <= listaPomOs.get(wybPom - 1).listaPrzedmiotow.size(); j++)
                System.out.println("Aby zmieni?? rozmiar przedmiotu " + listaPomOs.get(wybPom - 1).listaPrzedmiotow.get(j - 1) + " wci??nij " + j);
            System.out.println("Aby wyj???? wci??nij 0");

            int wybPrzed = scanner.nextInt();

            if (wybPrzed == 0)
                System.exit(0);

            System.out.println("Podaj nowy rozmiar\nAby wyj???? wci??nij 0");
            Double nowyRoz = scanner.nextDouble();
            if (wybPrzed == 0)
                System.exit(0);
            try {
                listaPomOs.get(wybPom - 1).zmienRozmiar((Przedmiot) listaPomOs.get(wybPom - 1).listaPrzedmiotow.get(wybPrzed - 1), nowyRoz);
            } catch (TooManyThingsException e) {
                System.out.println("Brak wystarczaj??cej ilo??ci miejsca w tym pomieszczeniu");
            }
        }
    }



}