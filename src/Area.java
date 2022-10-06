import javax.swing.*;
import java.awt.*;

public class Area extends JLabel
{
    // Eigenschaften
    private boolean feldWurdeBeschossen;
    private boolean schiffIstAufFeld;
    // Konstruktor
    public Area()
    {
        feldWurdeBeschossen=false;
        schiffIstAufFeld=false;
    }

    // Methoden
    public boolean beschiesseFeld(){
        feldWurdeBeschossen=true;
        if (schiffIstAufFeld) {
            versenkeSchiff();
            return true;
        }
        else return false;
    }

    public void setzeSchiff(){
        schiffIstAufFeld=true;
        setForeground(Color.GREEN);
        repaint();

    }

    public void versenkeSchiff(){
        schiffIstAufFeld=false;

    }

    public boolean isFeldWurdeBeschossen(){
        return feldWurdeBeschossen;

    }

    public boolean isSchiff(){
        return schiffIstAufFeld;
    }



}
