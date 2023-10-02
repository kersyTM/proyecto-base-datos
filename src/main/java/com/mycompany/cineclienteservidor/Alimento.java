package com.mycompany.cineclienteservidor;

import java.util.ArrayList;

public abstract class Alimento {
    private String nombre;
    private double precio;

    public Alimento(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public static ArrayList<Alimento> generarMenu() {
        ArrayList<Alimento> menu = new ArrayList<>();
        menu.add(new Hamburguesa("Hamburguesa Clasica", 2000));
        menu.add(new Hamburguesa("Hamburguesa de Cerdo", 2500));
        menu.add(new Hamburguesa("Hamburguesa Suprema", 3000));
        menu.add(new Pizza("Pizza de Jamon y Queso", 2150));
        menu.add(new Pizza("Pizza de Hawaiana", 2300));
        menu.add(new Pizza("Pizza de Suprema", 2550));
        menu.add(new HotDog("Hot Dog Clasico", 1500));
        menu.add(new HotDog("Chili-Dog", 2000));
        menu.add(new HotDog("Hot Dog Supremo", 2250));

        return menu;
    }
      
    public abstract String bebidaIncluida();
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
