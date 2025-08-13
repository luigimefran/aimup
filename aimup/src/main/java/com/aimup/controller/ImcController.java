package com.aimup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ImcController {

    @GetMapping("/imc")
    public String mostrarFormulario() {
        return "calculoImc/calculoImc";
    }

    @PostMapping("/imc/calcular")
    public String calcularIMC(@RequestParam double altura, @RequestParam double peso, Model model) {
        double imc = peso / (altura * altura);
        model.addAttribute("altura", altura);
        model.addAttribute("peso", peso);
        model.addAttribute("imc", imc);
        return "calculoImc/calculoImc";
    }
}