import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Game {


    private static Area[][] coordinatesP1;
    private static Area[][] coordinatesP2;
    private Spielfeld gui;

    public Game() {
        coordinatesP1 = new Area[12][12];
        coordinatesP2 = new Area[12][12];
        gui = new Spielfeld();

        playerSetup(coordinatesP1);
    }

    private void playerSetup(JLabel[][] coord) {
        Scanner sc = new Scanner(System.in);
        int miniBoat = 4;
        int submarine = 3;
        int destroyer = 2;
        int carrier = 1;
        while (miniBoat != 0 || submarine != 0 || destroyer != 0 || carrier != 0) {
            System.out.println("Geben Sie hier die Art des Schiffes an:");
            System.out.println(1 + " \tMini Boote (1 Feld)\t\t\t\t(Noch " + miniBoat + " uebrig)");
            System.out.println(2 + " \tUBoote (2 Felder)\t\t\t\t(Noch " + submarine + " uebrig)");
            System.out.println(3 + " \tZerstörer (3 Felder\t\t\t\t(Noch " + destroyer + " uebrig)");
            System.out.println(4 + " \tFlugzeugträger (4 Felder)\t\t(Noch " + carrier + " uebrig)");
            int ship = sc.nextInt();
            Timer t = new Timer(1, null);
            switch (ship) {
                case (1): {
                    if (miniBoat != 0) {

                        inputCoordinates(coord, ship);
                        miniBoat--;

                    } else {
                        System.out.println("Error");
                    }
                    break;
                }
                case (2): {
                    if (submarine != 0) {
                        inputCoordinates(coord, ship);
                        submarine--;
                        break;
                    }
                }
                case (3): {
                    if (destroyer != 0) {
                        inputCoordinates(coord, ship);
                        destroyer--;
                        break;
                    }
                }
                case (4): {
                    if (carrier != 0) {

                        inputCoordinates(coord, ship);
                        carrier--;
                        break;

                    }


                }

                default: {
                    System.out.println("Fehler");
                }
            }

        }

    }

    private void inputCoordinates(JLabel[][] coord, int groesse) {

        int mitte = (int) ((Spielfeld.getCoordinatesField().length - 2) / 2);


        Thread thread = new Thread(new Runnable(){
            @Override
            public void run()
            {
                try
                {
                    for (int x = 0; x < groesse; x++) {
                        JLabel j = coord[mitte + groesse][mitte];
                        j.setForeground(Color.RED);
                        j.repaint();
                        Thread.sleep(100);
                    }

                }
                catch(Exception ex){}
            }
        });
        thread.start();
    }
    //validate();







    public static Area[][] getCoordinatesP1() {
        return coordinatesP1;
    }

    public static Area[][] getCoordinatesP2() {
        return coordinatesP2;
    }

    public static void setCoordinatesP1(Area[][] coordinatesP1) {
        Game.coordinatesP1 = coordinatesP1;
    }

    public static void setCoordinatesP2(Area[][] coordinatesP2) {
        Game.coordinatesP2 = coordinatesP2;
    }
}
