package Datos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Gerardo Fac
 */
public class A_Estrella {

    private Nodo inicio;
    private Nodo fin;
    private Nodo mapa[][];
    private ArrayList<Nodo> cerrada;
    private ArrayList<Nodo> abierta;

    public A_Estrella(Nodo inicio, Nodo fin, Nodo[][] mapa) {
        this.inicio = inicio;
        this.fin = fin;
        this.mapa = mapa;

    }

    public Camino calcularCaminoMasCorto() {

        cerrada = new ArrayList<Nodo>();
        abierta = new ArrayList<Nodo>();

        abierta.add(inicio);

        while (abierta.size() != 0) {
            Nodo actual = abierta.get(0);

            for (int i = 0; i < abierta.size(); ++i) {
                if (actual.getHeuristica() > abierta.get(i).getHeuristica()) {
                    actual = abierta.get(i);
                }

            }

            //SI ES EL NODO FINAL
            if (actual.isSalida()) {
                return creaCamino(actual);
            }

            abierta.remove(actual);
            cerrada.add(actual);

            ArrayList<Nodo> vecinos = actual.dameAdyacentes(mapa.length, mapa[0].length, mapa, fin);

            for (Nodo vecino : vecinos) {
                boolean esMejorVecino;

                if (cerrada.contains(vecino)) {
                    continue;
                }

                if (!vecino.isMuro()) {

                    float distanciaDesdeElPrincipioAlVecino = (actual.getDistanciaAlOrigen() + getDistanceBetween(actual, vecino));

                    if (!abierta.contains(vecino)) {
                        abierta.add(vecino);
                        esMejorVecino = true;

                    } else if (distanciaDesdeElPrincipioAlVecino < actual.getDistanciaAlOrigen()) {
                        esMejorVecino = true;
                    } else {
                        esMejorVecino = false;
                    }

                    if (esMejorVecino) {
                        vecino.setAnterior(actual);
                        vecino.setDistanciaAlOrigen(distanciaDesdeElPrincipioAlVecino);
                        vecino.setDistanciaHeuristica(calculaDistancia(vecino.getX(), vecino.getY(), fin.getX(), fin.getY()));
                    }
                }

            }

        }
        return null;
    }

    private float calculaDistancia(int startX, int startY, int goalX, int goalY) {

        //Distancia x
        float dx = goalX - startX;
        //Distancia y
        float dy = goalY - startY;
        return (float) Math.sqrt((dx * dx) + (dy * dy));
    }

    private Camino creaCamino(Nodo inicio) {

        Nodo aux = inicio;

        Camino camino = new Camino();

        camino.aniadeNodo(inicio);

        while (!aux.isEntrada()) {
            aux = aux.getAnterior();
            camino.aniadeNodo(aux);

        }

        return camino;

    }

    public float getDistanceBetween(Nodo node1, Nodo node2) {
        //if the nodes are on top or next to each other, return 1

        if (node1.getX() == node2.getX() || node1.getY() == node2.getY()) {
            return 1 * (mapa.length + mapa[0].length);
        } else { //if they are diagonal to each other return diagonal distance: sqrt(1^2+1^2)
            return (float) 1.4 * (mapa.length + mapa[0].length);
        }
    }

}
