import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spielfeld extends JFrame {
    private static int[][] coordinatesField;
    boolean playerTurn;
    private JSplitPane jsp;


    public Spielfeld() {
        coordinatesField = new int[12][12];
        StartGame();
    }

    private void StartGame() {
        createField();
    }


    public void createField() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        //setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel player1 = new JPanel();
        player1.setLayout(new GridLayout(coordinatesField.length, coordinatesField.length));
        //player1.setSize(1000, 1000);
        JPanel player2 = new JPanel();
        player2.setLayout(new GridLayout(coordinatesField.length, coordinatesField.length));
        player2.setBackground(Color.decode("#016D79"));
        player1.setBackground(Color.decode("#016D79"));
        setVisible(true);
        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, player1, player2);
        player1.setBorder(new EmptyBorder(0, 0, 0, 0));
        player2.setBorder(new EmptyBorder(0, 0, 0, 0));
        player2.setVisible(true);
        player1.setVisible(true);

        //Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();


        jsp.setDividerSize(0);
        setContentPane(jsp);
        jsp.setResizeWeight(0.5);
        fill();
        try {
            initField(player1, Game.getCoordinatesP1());
            initField(player2, Game.getCoordinatesP2());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pack();

    }


    public void fill() {
        for (int x = 0; x < coordinatesField.length; x++) {
            for (int y = 0; y < coordinatesField.length; y++) {
                if ((x == 0 || y == 0 || x == (coordinatesField.length - 1) || y == (coordinatesField.length - 1))) {
                    if (x == y || (x == 0 & y == (coordinatesField.length - 1)) || (y == 0 & x == (coordinatesField.length - 1))) {
                        coordinatesField[x][y] = -2;
                    } else {
                        coordinatesField[x][y] = -1;
                    }
                } else {
                    coordinatesField[x][y] = 0;
                }
            }
        }
    }

    public void initField(JPanel j, Area[][] coords) throws FileNotFoundException {
        File fileName = new File("src/field.txt");
        Scanner sc = new Scanner(fileName);
        for (int r = 0; r < coordinatesField.length; r++) {
            for (int c = 0; c < coordinatesField.length; c++) {
                Area l = new Area();
                if (coordinatesField[r][c] == -1) {
                    if (sc.hasNext()) {
                        l.setText(sc.nextLine());
                        l.setForeground(Color.decode("#C2F8FF"));
                    }
                } else if (coordinatesField[r][c] == -2) {
                    l.setText(" ");
                } else {
                    l.setText("F");
                    l.setForeground(Color.decode("#748384"));
                    /*try {
                        Image img = ImageIO.read(getClass().getResource("Schiff.bmp"));
                        l.setIcon(new ImageIcon(img));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }*/
                    l.setBorder(null);
                }
                j.add(l);
                l.setHorizontalAlignment(JLabel.CENTER);
                coords[r][c] = l;
                validate();
            }
        }
        sc.close();
    }

    public static void setCoordinatesField(int[][] coordinatesField) {
        Spielfeld.coordinatesField = coordinatesField;
    }

    public static int[][] getCoordinatesField() {
        return coordinatesField;
    }
}