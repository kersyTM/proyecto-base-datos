package com.mycompany.cineclienteservidor;

import javax.swing.JOptionPane;

public class PagoEnPersona implements MetodoPago {

    @Override
    public boolean realizarPago(double monto) {
        JOptionPane.showMessageDialog(null, "Si desea comprar su boleto en persona, favor ir a la ventanilla fisica de CineCrud."
        + "\n Recibira un descuento del 10%, lo cual equivale a: "  + monto*0.1
        + "\n Su total sera: " + (monto - (monto*0.1)));
        return true;
    }

    @Override
    public void darRegalia() {
        JOptionPane.showMessageDialog(null, "Si compra sus boletos en persona durante este mes, recibirá unas palomitas pequeñas complementarias!");
    }
    
}
