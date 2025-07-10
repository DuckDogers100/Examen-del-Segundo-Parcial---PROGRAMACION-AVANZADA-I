/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estadisticas;

import java.util.*;

public class EstadisticasJugador {
    private ArrayList<Double> minutos;

    public EstadisticasJugador(ArrayList<Double> minutos) {
        this.minutos = new ArrayList<>(minutos);
        Collections.sort(this.minutos); // Ordenar para mediana y cuartiles
    }

    public double calcularMedia() {
        double suma = 0;
        for (double m : minutos) suma += m;
        return suma / minutos.size();
    }

    public double calcularVarianza() {
        double media = calcularMedia();
        double suma = 0;
        for (double m : minutos) suma += Math.pow(m - media, 2);
        return suma / minutos.size();
    }

    public double calcularDesviacionEstandar() {
        return Math.sqrt(calcularVarianza());
    }

    public double calcularMediana() {
        int n = minutos.size();
        if (n % 2 == 0) {
            return (minutos.get(n / 2 - 1) + minutos.get(n / 2)) / 2.0;
        } else {
            return minutos.get(n / 2);
        }
    }

    public List<Double> encontrarModa() {
        Map<Double, Integer> conteo = new HashMap<>();
        for (double m : minutos) {
            conteo.put(m, conteo.getOrDefault(m, 0) + 1);
        }

        int maxFrecuencia = Collections.max(conteo.values());
        if (maxFrecuencia == 1) return new ArrayList<>();

        List<Double> modas = new ArrayList<>();
        for (Map.Entry<Double, Integer> entry : conteo.entrySet()) {
            if (entry.getValue() == maxFrecuencia) {
                modas.add(entry.getKey());
            }
        }
        return modas;
    }

    public double calcularRango() {
        return minutos.get(minutos.size() - 1) - minutos.get(0);
    }

    public List<Double> detectarOutliers() {
        double q1 = calcularCuartil(25);
        double q3 = calcularCuartil(75);
        double iqr = q3 - q1;
        double limInferior = q1 - 1.5 * iqr;
        double limSuperior = q3 + 1.5 * iqr;

        List<Double> outliers = new ArrayList<>();
        for (double m : minutos) {
            if (m < limInferior || m > limSuperior) {
                outliers.add(m);
            }
        }
        return outliers;
    }

    private double calcularCuartil(int percentil) {
        int n = minutos.size();
        double pos = (percentil / 100.0) * (n + 1) - 1;
        int index = (int) Math.floor(pos);
        double frac = pos - index;

        if (index + 1 < n) {
            return minutos.get(index) + frac * (minutos.get(index + 1) - minutos.get(index));
        } else {
            return minutos.get(n - 1);
        }
    }
}
