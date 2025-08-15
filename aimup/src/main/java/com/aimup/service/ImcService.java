package com.aimup.service;

import org.springframework.stereotype.Service;

@Service
public class ImcService {
    public double calcularImc(double pesoKg, double alturaM) {
        if (pesoKg <= 0 || alturaM <= 0) {
            throw new IllegalArgumentException("Peso e altura devem ser maiores que zero.");
        }
        double imc = pesoKg / (alturaM * alturaM);
        return Math.round(imc * 100.0) / 100.0; // 2 casas
    }
}
