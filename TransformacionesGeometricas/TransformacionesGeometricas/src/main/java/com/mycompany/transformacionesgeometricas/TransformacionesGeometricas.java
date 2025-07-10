/*Gerardo Arturo Galindo Garcia 202410020410
Manuel Antonio Escalante Palma 202420110246
Marlon Jared Saenz Blanco 202230010131
*/
package com.mycompany.transformacionesgeometricas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class TransformacionesGeometricas extends JFrame {
    private PanelDibujo panelDibujo;

    public TransformacionesGeometricas() {
        setTitle("Transformaciones Geométricas");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panelDibujo = new PanelDibujo();
        add(panelDibujo, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnTrasladar = new JButton("Trasladar");
        JButton btnEscalar = new JButton("Escalar");
        JButton btnRotar = new JButton("Rotar");
        JButton btnReset = new JButton("Resetear");

        panelBotones.add(btnTrasladar);
        panelBotones.add(btnEscalar);
        panelBotones.add(btnRotar);
        panelBotones.add(btnReset);

        add(panelBotones, BorderLayout.SOUTH);

        btnTrasladar.addActionListener(e -> {
            panelDibujo.trasladar(50, 30);
        });

        btnEscalar.addActionListener(e -> {
            panelDibujo.escalar(1.2, 1.2);
        });

        btnRotar.addActionListener(e -> {
            panelDibujo.rotar(Math.toRadians(15));
        });

        btnReset.addActionListener(e -> {
            panelDibujo.resetear();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TransformacionesGeometricas().setVisible(true);
        });
    }
}

class PanelDibujo extends JPanel {
    private AffineTransform transformacion;

    public PanelDibujo() {
        transformacion = new AffineTransform();
    }

    public void trasladar(double dx, double dy) {
        transformacion.translate(dx, dy);
        repaint();
    }

    public void escalar(double sx, double sy) {
        transformacion.scale(sx, sy);
        repaint();
    }

    public void rotar(double angulo) {
        Rectangle bounds = getBounds();
        double cx = bounds.getWidth() / 2;
        double cy = bounds.getHeight() / 2;
        transformacion.rotate(angulo, cx, cy);
        repaint();
    }

    public void resetear() {
        transformacion = new AffineTransform();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

       
        g2.setTransform(transformacion);

        
        g2.setColor(Color.BLUE);
        g2.fillRect(200, 150, 100, 100);

       
        g2.setColor(Color.RED);
        g2.fillOval(400, 150, 100, 100);
    }
}
