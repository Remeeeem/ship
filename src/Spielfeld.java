import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spielfeld extends JFrame {
    private static int[][] coordinatesField;

    private JSplitPane jsp;
    private KeyListener k;
    private JPanel player1;
    private JPanel player2;


    public Spielfeld() {
        coordinatesField = new int[12][12];
        StartGame();
        k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (Game.getisRotateAllowed()) {
                    switch (e.getKeyCode()) {
                        case (KeyEvent.VK_ENTER):
                            Game.setIsRotateAllowed(false);
                            break;
                        case (KeyEvent.VK_DOWN):
                            Game.setrDirection(1);
                            break;
                        case (KeyEvent.VK_UP):
                            Game.setrDirection(2);
                            break;
                        case (KeyEvent.VK_RIGHT):
                            Game.setrDirection(3);
                            break;
                        case (KeyEvent.VK_LEFT):
                            Game.setrDirection(4);
                            break;
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        addKeyListener(k);
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
        player1 = new JPanel();
        player1.setLayout(new GridLayout(coordinatesField.length, coordinatesField.length));
        //player1.setSize(1000, 1000);
        player2 = new JPanel();
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
        for (int c = 0; c < coordinatesField.length; c++) {
            for (int r = 0; r < coordinatesField.length; r++) {
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
                    l.setForeground(Color.BLACK);
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

    public static void setAreaColor(JLabel a) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() {
                return null;
            }


            @Override
            public void done() {
                a.setForeground(Color.GREEN);
                a.repaint();
            }
        };

        // Call the SwingWorker from within the Swing thread
        worker.execute();
    }

    public void fair(boolean turn){
        if(!turn){
            player1.setBackground(Color.decode("#016D79"));
            player2.setBackground(Color.BLACK);
        }
        else {
            player2.setBackground(Color.decode("#016D79"));
            player1.setBackground(Color.BLACK);

        }
    }


    public static void setCoordinatesField(int[][] coordinatesField) {
        Spielfeld.coordinatesField = coordinatesField;
    }

    public static int[][] getCoordinatesField() {
        return coordinatesField;
    }
}