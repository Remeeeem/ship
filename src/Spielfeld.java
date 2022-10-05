import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spielfeld extends JFrame {
int[][] coordinatesField,coordinatesP1,coordinatesP2;
boolean playerTurn;
JSplitPane jsp;
    public static void main(String[] args) {

    }


    public Spielfeld()
    {
        coordinatesField =new int[12][12];
        coordinatesP1 =new int[12][12];
        coordinatesP2 =new int[12][12];
        StartGame();

    }

    private void StartGame() {

        playerSetup(coordinatesP1);

        //playerSetup(coordinatesP2);
        createField();
    }

    private void playerSetup(int[][] coord) {
        Scanner sc = new Scanner(System.in);
        coord =new int[12][12];
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
            switch (ship){
                case (1):{
                    if(miniBoat!=0)
                    {
                        inputCoordinates(coord);
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
                        inputCoordinates(coord);
                        submarine--;
                        break;
                    }
                }
                case (3):{
                    if(destroyer!=0)
                    {
                        inputCoordinates(coord);
                        destroyer--;
                        break;
                    }
                }
                case (4):{
                    if(carrier!=0)
                    {
                        inputCoordinates(coord);
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

    private void inputCoordinates(int[][] coord) {
    }

    public void createField() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout()); //setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel player1= new JPanel();
        player1.setLayout(new GridLayout(coordinatesField.length, coordinatesField.length));
        player1.setSize(1000, 1000);
        JPanel player2= new JPanel();
        player2.setLayout(new GridLayout(coordinatesField.length, coordinatesField.length));
        player2.setVisible(true);player1.setVisible(true);
        player2.setBackground(Color.decode("#016D79"));
        player1.setBackground(Color.decode("#016D79"));
        setVisible(true);
        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,player1,player2);
        jsp.setDividerLocation(1000);
        jsp.setDividerSize(0);
        setContentPane(jsp);
        fill();
        try {
            initField(player1);
            initField(player2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


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
    public void initField(JPanel j) throws FileNotFoundException {
        File fileName=new File("src/field.txt");
        Scanner sc = new Scanner(fileName);
        for (int[] ints : coordinatesField) {
            for (int c = 0; c < coordinatesField.length; c++) {
                JLabel l = new JLabel();
                if (ints[c] == -1) {
                    if (sc.hasNext()) {
                        l.setText(sc.nextLine());
                        l.setForeground(Color.decode("#C2F8FF"));
                    }
                } else if (ints[c] == -2) {
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
                validate();

            }
        }
        sc.close();
    }
}