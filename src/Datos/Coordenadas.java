package Datos;

import java.util.ArrayList;

/**
 *
 * @author Gerardo Fac
 */
public class Coordenadas {

    private int x;
    private int y;

    public Coordenadas(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void suma(Coordenadas pto) {
        this.x += pto.getX();
        this.y += pto.getY();

    }

}
