package Entrada;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Datos.A_Estrella;
import Datos.Camino;
import Datos.Nodo;

/**
 *
 * @author Gerardo Fac
 */
public class Matriz extends JFrame {

    /**
     *
     */
    private Camino solucion;
    private static final long serialVersionUID = 1L;
    private JPanel panelPrincipal;
    private JComboBox<String> chooser;
    private JPanel botonPanel;
    private JButton aceptar;
    private Integer filas;
    private Integer columnas;
    private JTextField matriz[][]; //Matriz de JTextField
    private JTextField inicio;
    private JTextField fin;
    private JMenuBar menu;
    private JMenuItem boton;

    public Matriz(int filas, int columnas) {
        super("Algoritmo A*");
        solucion = null;
        this.filas = filas;
        this.columnas = columnas;
        initComponents();
        this.setLocationRelativeTo(null);

    }

    private void initComponents() {

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        WindowAdapter exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "¿Quieres salir de la aplicacion?",
                        "Confirmacion de salida", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    System.exit(0);
                }
            }
        };
        this.addWindowListener(exitListener);

        matriz = new JTextField[filas][columnas];

        panelPrincipal = new JPanel();
        botonPanel = new JPanel();

        inicio = null;
        fin = null;

        menu = new JMenuBar();
        boton = new JMenuItem("Nuevo mapa");

        boton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                nuevoMapa();
            }

        });
        menu.add(boton);
        panelPrincipal.setLayout(new GridLayout(filas, columnas));

        for (int i = 0; i < filas; ++i) {
            for (int k = 0; k < columnas; ++k) {
                matriz[i][k] = new JTextField();

                matriz[i][k].setEditable(false);
                matriz[i][k].setFont(new Font("Verdana", 0, 0));

                matriz[i][k].addMouseListener(new MouseListener() {

                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        pintaCuadrado(arg0);
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        // TODO Auto-generated method stub

                    }
                });
                panelPrincipal.add(matriz[i][k]);
            }

        }
        String[] op = {"", "Inicio", "Final", "Muro"};
        chooser = new JComboBox<String>(op);
        aceptar = new JButton("Camino");

        aceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                calcularCamino();
            }
        });

        botonPanel.add(chooser);
        botonPanel.add(aceptar);
        botonPanel.setSize(100, 100);
        this.add(panelPrincipal, BorderLayout.CENTER);
        this.add(botonPanel, BorderLayout.SOUTH);
        this.add(menu, BorderLayout.NORTH);
        this.pack();
        this.setSize(500, 500);
        this.setVisible(true);

    }

    private void pintaCuadrado(MouseEvent e) {

        JTextField selected = (JTextField) e.getSource();

        Color color = null;
        String texto = "";

        switch (chooser.getSelectedIndex()) {

            case 1:
                if (selected != fin) {
                    color = Color.GREEN;
                    texto = "entrada";

                    if (inicio != null) {
                        inicio.setBackground(null);
                        inicio.setText("");
                        inicio = selected;
                    } else {

                        inicio = selected;

                    }
                } else {
                    color = Color.RED;
                    texto = "fin";

                }

                break;
            case 2:
                if (selected != inicio) {
                    color = Color.RED;
                    texto = "fin";

                    if (fin != null) {
                        fin.setBackground(null);
                        fin.setText("");
                        fin = selected;

                    } else {

                        fin = selected;

                    }
                } else {
                    color = Color.GREEN;
                    texto = "entrada";

                }
                break;
            case 3:
                if (selected != inicio && selected != fin) {
                    color = Color.BLACK;
                    texto = "muro";
                } else {

                    if (selected == inicio) {

                        color = Color.GREEN;
                        texto = "entrada";

                    } else {

                        color = Color.RED;
                        texto = "fin";
                    }

                }
                break;

            default:
                color = null;
                texto = "";
                break;

        };

        selected.setBackground(color);
        selected.setText(texto);

    }

    private void nuevoMapa() {
        // TODO Auto-generated method stub
        this.setVisible(false);
        PreguntaFilasColumnas.getInstance().setVisible(true);
        this.dispose();

    }

    private void calcularCamino() {

        Nodo mapa[][] = new Nodo[filas][columnas];

        Nodo inicio = null, fin = null;
        for (int i = 0; i < filas; ++i) {
            for (int k = 0; k < columnas; ++k) {
                mapa[i][k] = new Nodo(i, k);
                switch (matriz[i][k].getText()) {
                    case ("muro"):
                        mapa[i][k].setMuro(true);
                        break;

                    case ("entrada"):

                        inicio = new Nodo(i, k);
                        inicio.setEntrada(true);
                        mapa[i][k].setEntrada(true);
                        break;
                    case ("fin"):
                        fin = new Nodo(i, k);
                        fin.setEntrada(true);
                        mapa[i][k].setSalida(true);

                        break;

                    default:
                        break;
                }
            }

        }
        if (solucion != null) {
            for (int i = 1; i < solucion.distanciaCamino() - 1; ++i) {
                Nodo aPintar = solucion.getCoordenadasCamino(i);
                if (matriz[aPintar.getX()][aPintar.getY()].getBackground() != Color.BLACK && matriz[aPintar.getX()][aPintar.getY()].getBackground() != Color.GREEN && matriz[aPintar.getX()][aPintar.getY()].getBackground() != Color.RED) {
                    matriz[aPintar.getX()][aPintar.getY()].setBackground(null);
                }

            }
        }
        if (inicio != null && fin != null) {
            A_Estrella resolucion = new A_Estrella(inicio, fin, mapa);

            solucion = resolucion.calcularCaminoMasCorto();
            if (solucion != null) {
                for (int i = 1; i < solucion.distanciaCamino() - 1; ++i) {
                    Nodo aPintar = solucion.getCoordenadasCamino(i);
                    matriz[aPintar.getX()][aPintar.getY()].setBackground(Color.BLUE);

                }

            } else {
                JOptionPane.showMessageDialog(null, "No existe camino");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe inicio o fin");
        }
    }
}
