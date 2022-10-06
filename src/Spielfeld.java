import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spielfeld extends JFrame {
final int[][] coordinatesField;
final JLabel[][] coordinatesP1;
    final JLabel[][] coordinatesP2;
boolean playerTurn;
private JSplitPane jsp;


    public Spielfeld()
    {
        coordinatesField =new int[12][12];
        coordinatesP1 =new JLabel[12][12];
        coordinatesP2 =new JLabel[12][12];
        StartGame();

    }

    private void StartGame() {
        createField();
        playerSetup(coordinatesP1);

        //playerSetup(coordinatesP2);

    }

    private void playerSetup(JLabel[][] coord) {
        Scanner sc = new Scanner(System.in);
        int miniBoat=4;
        int submarine=3;
        int destroyer=2;
        int carrier=1;
        while(miniBoat!=0||submarine!=0||destroyer!=0||carrier!=0)
        {
            System.out.println("Geben Sie hier die Art des Schiffes an:");
            System.out.println(1 + " \tMini Boote (1 Feld)\t\t\t\t(Noch "+miniBoat+" uebrig)");
            System.out.println(2 + " \tUBoote (2 Felder)\t\t\t\t(Noch "+submarine+" uebrig)");
            System.out.println(3 + " \tZerstörer (3 Felder\t\t\t\t(Noch "+destroyer +" uebrig)");
            System.out.println(4 + " \tFlugzeugträger (4 Felder)\t\t(Noch "+carrier+" uebrig)");
            int ship=sc.nextInt();
            Timer t=new Timer(1,null);
            switch (ship){
                case (1):{
                    if(miniBoat!=0)
                    {

                        inputCoordinates(coord,ship);
                        miniBoat--;

                    }
                    else
                    {
                        System.out.println("Error");
                    }
                    break;
                }
                case (2):{
                    if(submarine!=0)
                    {
                        inputCoordinates(coord,ship);
                        submarine--;
                        break;
                    }
                }
                case (3):{
                    if(destroyer!=0)
                    {
                        inputCoordinates(coord,ship);
                        destroyer--;
                        break;
                    }
                }
                case (4):{
                    if(carrier!=0)
                    {
                        inputCoordinates(coord,ship);
                        carrier--;
                        break;
                    }
                }
                default:{
                    System.out.println("Fehler");
                }
            }

        }

    }

    private void inputCoordinates(JLabel[][] coord,int groesse) {
        int mitte=(int)((coordinatesField.length-2)/2);
        for(int x=0;x<groesse;x++) {
            JLabel j = coord[mitte+groesse][mitte];
            j.setForeground(Color.RED);
            repaint();
        }
        //validate();
    }

    public void createField() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        //setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel player1= new JPanel();
        player1.setLayout(new GridLayout(coordinatesField.length, coordinatesField.length));
        //player1.setSize(1000, 1000);
        JPanel player2= new JPanel();
        player2.setLayout(new GridLayout(coordinatesField.length, coordinatesField.length));
        player2.setBackground(Color.decode("#016D79"));
        player1.setBackground(Color.decode("#016D79"));
        setVisible(true);
        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,player1,player2);
        player1.setBorder(new EmptyBorder(0, 0, 0, 0));
        player2.setBorder(new EmptyBorder(0, 0, 0, 0));
        player2.setVisible(true);player1.setVisible(true);

        //Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();


        jsp.setDividerSize(0);
        setContentPane(jsp);
        jsp.setResizeWeight(0.5);
        fill();
        try {
            initField(player1,coordinatesP1);
            initField(player2,coordinatesP2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    pack();

    }


    public void fill()
    {
        for(int x = 0; x< coordinatesField.length; x++) {
            for(int y = 0; y< coordinatesField.length; y++){
                if((x==0||y==0||x==(coordinatesField.length-1)||y==(coordinatesField.length-1)))
                {
                    if(x==y||(x==0&y==(coordinatesField.length-1))||(y==0&x==(coordinatesField.length-1))) {
                    coordinatesField[x][y]=-2;
                    }
                    else { coordinatesField[x][y]=-1;}
                }
                else{
                    coordinatesField[x][y]=0;
                }
            }
        }
    }
    public void initField(JPanel j,JLabel[][]coords) throws FileNotFoundException {
        File fileName=new File("src/field.txt");
        Scanner sc = new Scanner(fileName);
        for (int r=0;r<coordinatesField.length;r++) {
            for (int c = 0; c < coordinatesField.length; c++) {
                JLabel l = new JLabel();
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
                coords[r][c]=l;
                validate();
            }
        }
        sc.close();
    }
}