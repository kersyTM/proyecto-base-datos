package com.mycompany.cineclienteservidor;


public class Hilo extends Thread {
    
    private Alimento alimento;
    
    public void generandoDatos(){
        try {
            System.out.println("Eligiendo alimento aleatorio...");
            Thread.sleep(3000);
            System.out.println("Dato generado! " + this.alimento.getNombre() + " esta disponible hoy! Recordatorio: Este alimento incluye " + this.alimento.bebidaIncluida() + 
            "\n Precio: " + this.alimento.getPrecio() + "\n");
        } catch (InterruptedException e) {
            System.out.println("Proceso interurmpido");            
        }
    }    
    
    
    @Override
    public void run() {
        generandoDatos();
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }
    
    
}
