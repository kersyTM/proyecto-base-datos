package com.mycompany.cineclienteservidor;

public class Hamburguesa extends Alimento {

    public Hamburguesa(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String bebidaIncluida() {
        return "Malteada De Cualquier Sabor";
    }
    
}
