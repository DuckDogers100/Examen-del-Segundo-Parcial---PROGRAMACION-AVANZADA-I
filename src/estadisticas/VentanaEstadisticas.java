/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadisticas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaEstadisticas extends JFrame {

    private JTextField inputMinutos;
    private JTextArea areaResultados;

    public VentanaEstadisticas() {
        setTitle("Análisis Estadístico de Minutos Jugados");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con el input
        JPanel panelEntrada = new JPanel(new BorderLayout());
        inputMinutos = new JTextField("[28.5, 60.0, 74.5, 56.0, 70.5, 90.0, 45.0, 75.5, 61.0, 29.5]");
        JButton btnCalcular = new JButton("Calcular");

        panelEntrada.add(new JLabel("Minutos jugados (formato: [x, y, z]):"), BorderLayout.NORTH);
        panelEntrada.add(inputMinutos, BorderLayout.CENTER);
        panelEntrada.add(btnCalcular, BorderLayout.SOUTH);

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultados);

        add(panelEntrada, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnCalcular.addActionListener(e -> procesarDatos());
    }

    private void procesarDatos() {
        try {
            String texto = inputMinutos.getText().replace("[", "").replace("]", "");
            String[] partes = texto.split(",");
            ArrayList<Double> datos = new ArrayList<>();
            for (String parte : partes) {
                datos.add(Double.parseDouble(parte.trim()));
            }

            EstadisticasJugador stats = new EstadisticasJugador(datos);
            StringBuilder sb = new StringBuilder();

            sb.append("Media: ").append(stats.calcularMedia()).append(" minutos\n");
            sb.append("Varianza: ").append(stats.calcularVarianza()).append("\n");
            sb.append("Desviación Estándar: ").append(stats.calcularDesviacionEstandar()).append("\n");
            sb.append("Mediana: ").append(stats.calcularMediana()).append(" minutos\n");
            sb.append("Moda: ").append(stats.encontrarModa()).append("\n");
            sb.append("Rango: ").append(stats.calcularRango()).append(" minutos\n");
            sb.append("Outliers: ").append(stats.detectarOutliers()).append("\n");

            areaResultados.setText(sb.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en el formato de entrada.\nUsa: [28.5, 60.0, ...]", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
