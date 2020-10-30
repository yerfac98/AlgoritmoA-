package Datos;

import java.util.ArrayList;

/**
 *
 * @author Gerardo Fac
 */
public class Camino {

    ArrayList<Nodo> camino;

    public Camino() {

        camino = new ArrayList<Nodo>();
    }

    public Integer distanciaCamino() {

        return camino.size();

    }

    public Nodo getCoordenadasCamino(int index) {
        return camino.get(index);
    }

    public int getX(int index) {
        return getCoordenadasCamino(index).getX();
    }

    public int getY(int index) {
        return getCoordenadasCamino(index).getY();
    }

    // este a�ade el nodo al final
    public void aniadeNodo(Nodo n) {
        camino.add(n);
    }

}
