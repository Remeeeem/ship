import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Scanner;

public class Game {


    private static Area[][] coordinatesP1;
    private static Area[][] coordinatesP2;
    private Spielfeld gui;
    private static boolean isRotateAllowed = false;
    private static int rDirection;
    private static HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
    boolean playerTurn;

    public Game() {
        coordinatesP1 = new Area[12][12];
        coordinatesP2 = new Area[12][12];
        gui = new Spielfeld();

        fillHashMap();

        playerSetup(coordinatesP1);
    }

    private void fillHashMap() {
        letters.put('A', 1);
        letters.put('B', 2);
        letters.put('C', 3);
        letters.put('D', 4);
        letters.put('E', 5);
        letters.put('F', 6);
        letters.put('G', 7);
        letters.put('H', 8);
        letters.put('I', 9);
        letters.put('J', 10);

    }

    public void changePlayerTurn()
    {if(playerTurn){
        playerTurn=false;
    }else {
        playerTurn=true;
    }
    }
    private void playerSetup(JLabel[][] coord) {
        gui.fair(playerTurn);
        changePlayerTurn();
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

                        inputCoordinates(coord, sc, ship);
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
        for (int x = 0; x < groesse; x++) {
            Spielfeld.setAreaColor(coord[mitte][mitte + x]);
        }
        isRotateAllowed = true;
        Schiff s = new Schiff(mitte, groesse);

    }

    private void inputCoordinates(JLabel[][] coord, Scanner sc, int groesse) {

        int xA = 0;
        int xE = 0;
        int yA = 0;
        int yE = 0;
        int ship = -2;
        int deltaX = 2147483647;
        int deltay = 2147483647;
        boolean darfWaehlen = true;
        boolean player1 = true;


        System.out.println("X-Anker-Koordinate:");
        while (ship < 1 || ship > (Spielfeld.getCoordinatesField().length - 2)) {
            ship = sc.nextInt();
            xA = ship;
        }
        System.out.println("Y-Anker-Koordinate:");
        ship = -2;
        while (ship < 1 || ship > (Spielfeld.getCoordinatesField().length - 2)) {
            ship = letters.get(sc.next().charAt(0));
            yA = ship;
        }
        Spielfeld.setAreaColor(coord[xA][yA]);

        System.out.println("X-End-Koordinate:");
        ship = -2;
        while (ship < 1 || ship > (Spielfeld.getCoordinatesField().length - 2)) {
            ship = sc.nextInt();
            deltaX = Math.abs(ship - xA);
            if (deltaX == (groesse - 1)) {
                xE = ship;
                darfWaehlen = false;
            } else if (deltaX == 0) {
                xE = xA;
                break;
            } else {
                deltaX = 2147483647;
                ship = -2;
                System.out.println("X-End-Koordinate:");
            }
        }

        if (darfWaehlen == true) {
            System.out.println("Y-End-Koordinate:");
            ship = -2;
            while (ship < 1 || ship > (Spielfeld.getCoordinatesField().length - 2)) {
                ship = letters.get(sc.next().charAt(0));
                deltay = Math.abs(ship - yA);
                if (deltay == groesse - 1) {
                    yE = ship;
                } else {
                    deltay = 2147483647;
                    ship = -2;
                    System.out.println("Y-End-Koordinate:");
                }
            }
        } else {
            yE = yA;
        }
        Spielfeld.setAreaColor(coord[xE][yE]);


        if (xA == xE) {
            for (int i = 0; i <= yE - yA; i++) {
                Spielfeld.setAreaColor(coord[xA][yA + i]);
            }
        } else if (yA == yE) {
            for (int i = 0; i <= xE - xA; i++) {
                Spielfeld.setAreaColor(coord[xA + i][yA]);
            }
        } else {
            System.out.println("Fehler beim Erstellen des Schiffes");
        }

    }

    //validate();
    public static void position(int groesse, int xIndexAnchor, int yIndexAnchor) {

    }


    public static Area[][] getCoordinatesP1() {
        return coordinatesP1;
    }

    public static Area[][] getCoordinatesP2() {
        return coordinatesP2;
    }

    public static boolean getisRotateAllowed() {
        return isRotateAllowed;

    }


    public static void setCoordinatesP1(Area[][] coordinatesP1) {
        Game.coordinatesP1 = coordinatesP1;
    }

    public static void setCoordinatesP2(Area[][] coordinatesP2) {
        Game.coordinatesP2 = coordinatesP2;
    }

    public static void setIsRotateAllowed(boolean isRotateAllowed) {
        Game.isRotateAllowed = isRotateAllowed;
    }


    public Spielfeld getGui() {
        return gui;
    }

    public static int getrDirection() {
        return rDirection;
    }

    public static void setrDirection(int rDirection) {
        Game.rDirection = rDirection;
    }
}
