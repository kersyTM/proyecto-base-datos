package com.mycompany.cineclienteservidor;

public class HotDog extends Alimento {

    public HotDog(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String bebidaIncluida() {
        return "Te Frio de Limon";
    }
    
}
