import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Schiff implements KeyListener {

    int xA,yA,xE,yE;
    private static HashMap <Integer,Integer> coordinates;

    Schiff(int xA, int yA, int xE,int yE) {
        this.xA=xA;
        this.yA=yA;
        this.xE=xE;
        this.yE=yE;
        coordinates=new HashMap<Integer,Integer>();
    }
    Schiff(int a,int groesse) {
        this.xA=a;
        this.yA=a;
        coordinates=new HashMap<Integer,Integer>();
        initiateShip(a,groesse);

    }

    private void initiateShip(int xA, int yA, int xE,int yE){
        if(xA==xE){
            for(int i=yA;i<=yE;i++){
                coordinates.put(xA,i);
            }
        } else if (yA==yE) {
            for(int i=xA;i<=xE;i++){
                coordinates.put(i,yA);
            }
        } else{
            System.out.println("Fehler beim erstellen des Schiffes");
        }


    }
    private void initiateShip(int a ,int groesse){
        for(int i=0;i<=groesse;i++){
            coordinates.put(a,a+i);
        }
        System.out.println(coordinates);


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
