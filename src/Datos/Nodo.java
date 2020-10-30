package Datos;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Gerardo Fac
 */
public class Nodo extends Coordenadas implements Comparable<Nodo> {

    private boolean muro;
    private boolean salida;
    private boolean entrada;
    private float distanciaAlOrigen;
    private Nodo anterior;
    private float distanciaHeuristica;

    public Nodo(int x, int y) {
        super(x, y);
        muro = false;
        salida = false;
        entrada = false;
        setDistanciaAlOrigen(Integer.MAX_VALUE);
        anterior = null;

    }

    public ArrayList<Nodo> dameAdyacentes(int fila, int columna, Nodo[][] mapa, Nodo fin) {
        ArrayList<Nodo> ady = new ArrayList<Nodo>();

        ady.add(new Nodo(1, 1));
        ady.add(new Nodo(1, -1));
        ady.add(new Nodo(-1, 1));
        ady.add(new Nodo(-1, -1));

        ady.add(new Nodo(1, 0));
        ady.add(new Nodo(-1, 0));

        ady.add(new Nodo(0, 1));
        ady.add(new Nodo(0, -1));

        ArrayList<Nodo> adyacentes = new ArrayList<Nodo>();
        Nodo aux = new Nodo(this.getX(), this.getY());

        for (Nodo coord : ady) {

            aux.suma(coord);
            if (aux.getX() >= 0 && aux.getY() >= 0 && aux.getY() < columna && aux.getX() < fila) {
                mapa[aux.getX()][aux.getY()].setDistanciaHeuristica(getDistanceBetween(mapa[aux.getX()][aux.getY()], fin, mapa));

                adyacentes.add(mapa[aux.getX()][aux.getY()]);
            }
            aux.setX(this.getX());
            aux.setY(this.getY());

        }

        Collections.sort(adyacentes);

        return adyacentes;

    }

    public boolean isMuro() {
        return muro;
    }

    public void setMuro(boolean esMuro) {
        this.muro = esMuro;
    }

    public boolean isSalida() {
        return salida;
    }

    public void setSalida(boolean salida) {
        this.salida = salida;
        setDistanciaAlOrigen(0);
    }

    public boolean isEntrada() {
        return entrada;
    }

    public void setEntrada(boolean entrada) {
        this.entrada = entrada;
    }

    public Nodo getAnterior() {

        return anterior;
    }

    public void setAnterior(Nodo anterior) {

        this.anterior = anterior;
    }

    public float getDistanciaAlOrigen() {
        return distanciaAlOrigen;
    }

    public float getHeuristica() {
        return distanciaAlOrigen;
    }

    public void setDistanciaAlOrigen(float distanciaAlOrigen) {
        this.distanciaAlOrigen = distanciaAlOrigen;
    }

    public void setDistanciaHeuristica(float distancia) {

        this.distanciaHeuristica = distancia;

    }

    public int compareTo(Nodo otro) {
        float thisTotalDistanceFromGoal = distanciaHeuristica + distanciaAlOrigen;
        float otherTotalDistanceFromGoal = otro.getHeuristica() + otro.getDistanciaAlOrigen();

        if (thisTotalDistanceFromGoal < otherTotalDistanceFromGoal) {
            return -1;
        } else if (thisTotalDistanceFromGoal > otherTotalDistanceFromGoal) {
            return 1;
        } else {
            return 0;
        }
    }

    public float getDistanceBetween(Nodo node1, Nodo node2, Nodo[][] mapa) {
        //if the nodes are on top or next to each other, return 1

        if (node1.getX() == node2.getX() || node1.getY() == node2.getY()) {
            return 1 * (mapa.length + mapa[0].length);
        } else { //if they are diagonal to each other return diagonal distance: sqrt(1^2+1^2)
            return (float) 1.4 * (mapa.length + mapa[0].length);
        }
    }
}
