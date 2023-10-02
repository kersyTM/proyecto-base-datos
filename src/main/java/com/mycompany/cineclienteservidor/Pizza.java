package com.mycompany.cineclienteservidor;

public class Pizza extends Alimento {

    public Pizza(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String bebidaIncluida() {
        return "Pepsi Mediana";
    }
    
}
