public class Schiff {

    int groesse,xKoordinate,yKoordinate;
    int[]felder;
    Schiff(int schiffsGroeße,int xKoordinateInput,int yKoordinateInput)
    {
        groesse=schiffsGroeße;
        xKoordinate=xKoordinateInput;
        yKoordinate=yKoordinateInput;
        felder=new int[groesse];
    }


}
