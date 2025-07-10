package com.mycompany.mavenproject1;

/*Gerardo Arturo Galindo Garcia 202410020410
Manuel Antonio Escalante Palma 202420110246
Marlon Jared Saenz Blanco 202230010131
*/

import javax.swing.*;
import java.awt.*;

public class Mavenproject1 extends JFrame {
    private final JLabel tiempoLabel;
    private final JButton iniciarBtn;
    private final JButton pausarBtn;
    private final JButton reanudarBtn;
    private final JButton resetearBtn;

    private int tiempoRestante = 30;
    private Thread hiloTemporizador;
    private boolean corriendo = false;
    private boolean pausado = false;

    public Mavenproject1() {
        setTitle("Temporizador");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        tiempoLabel = new JLabel("30", SwingConstants.CENTER);
        tiempoLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(tiempoLabel);

        iniciarBtn = new JButton("Iniciar");
        pausarBtn = new JButton("Pausar");
        reanudarBtn = new JButton("Reanudar");
        resetearBtn = new JButton("Resetear");

        add(iniciarBtn);
        add(pausarBtn);
        add(reanudarBtn);
        add(resetearBtn);

        
        iniciarBtn.addActionListener(e -> iniciarTemporizador());
        pausarBtn.addActionListener(e -> pausarTemporizador());
        reanudarBtn.addActionListener(e -> reanudarTemporizador());
        resetearBtn.addActionListener(e -> resetearTemporizador());

        setVisible(true);
    }

    private void iniciarTemporizador() {
        if (corriendo) return;

        corriendo = true;
        pausado = false;

        hiloTemporizador = new Thread(() -> {
            while (tiempoRestante > 0 && corriendo) {
                if (!pausado) {
                    tiempoRestante--;
                    SwingUtilities.invokeLater(() -> tiempoLabel.setText(String.valueOf(tiempoRestante)));
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    break;
                }
            }
            
            if (tiempoRestante == 0) {
                corriendo = false;
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "¡Tiempo terminado!"));
            }
        });

        hiloTemporizador.start();
    }

    private void pausarTemporizador() {
        if (corriendo && !pausado) {
            pausado = true;
        }
    }

    private void reanudarTemporizador() {
        if (corriendo && pausado) {
            pausado = false;
        }
    }

    private void resetearTemporizador() {
        corriendo = false;
        pausado = false;
        tiempoRestante = 30;

        if (hiloTemporizador != null && hiloTemporizador.isAlive()) {
            hiloTemporizador.interrupt();
        }

        tiempoLabel.setText("30");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Mavenproject1());
    }
}
