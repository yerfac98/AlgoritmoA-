package Entrada;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 *
 * @author Gerardo Fac
 */
public class PreguntaFilasColumnas extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JPanel panelPrincipal;
    JPanel botonPanel;
    JLabel jLaFilas;
    JLabel jLaColumnas;
    JTextField jTeFilas;
    JTextField jTeColumnas;
    JButton boton;

    static PreguntaFilasColumnas instance;

    public static PreguntaFilasColumnas getInstance() {

        if (instance == null) {
            instance = new PreguntaFilasColumnas();
        }

        return instance;

    }

    private PreguntaFilasColumnas() {

        super("Algoritmo A*");
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

        panelPrincipal = new JPanel();
        botonPanel = new JPanel();

        jLaFilas = new JLabel("Filas");
        jLaColumnas = new JLabel("Columnas");
        jTeFilas = new JTextField(20);
        jTeColumnas = new JTextField(20);
        boton = new JButton("Aceptar");

        this.setLayout(new GridLayout(2, 1));
        botonPanel.add(boton);

        panelPrincipal.setLayout(new GridLayout(2, 2));

        panelPrincipal.add(jLaFilas);
        panelPrincipal.add(jTeFilas);

        panelPrincipal.add(jLaColumnas);
        panelPrincipal.add(jTeColumnas);

        boton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                procesaFilasColumnas();
            }
        });

        this.add(panelPrincipal);
        this.add(botonPanel);
        pack();
        this.setVisible(true);

    }

    public void procesaFilasColumnas() {

        if (!jTeFilas.getText().isEmpty() && !jTeColumnas.getText().isEmpty()) {
            try {
                int fila = Integer.parseInt(jTeFilas.getText());
                int columnas = Integer.parseInt(jTeColumnas.getText());;

                Matriz miMatriz = new Matriz(fila, columnas);
                this.setVisible(false);
                this.dispose();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La fila y/o columnas deben de ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Introduzca filas y/o columnas", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
;
}
