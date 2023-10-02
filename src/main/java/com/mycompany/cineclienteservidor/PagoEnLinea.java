package com.mycompany.cineclienteservidor;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class PagoEnLinea implements MetodoPago {

    @Override
    public boolean realizarPago(double monto) {

        try { 
            long numTarjeta = Long.parseLong(JOptionPane.showInputDialog("Ingrese su numero de tarjeta: "));
            if (String.valueOf(numTarjeta).length() != 16) {
                JOptionPane.showMessageDialog(null, "Numero de tarjeta invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                int securityNumbers = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los 3 numeros de seguridad (atras de la tarjeta): "));
                if (String.valueOf(securityNumbers).length() != 3) {
                    JOptionPane.showMessageDialog(null, "Numero de tarjeta invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                JOptionPane.showMessageDialog(null, "Pago exitoso");
                return true;
            }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numero de tarjeta invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

//        String numTarjeta = JOptionPane.showInputDialog("Ingrese su numero de tarjeta: ");
//
//        if (numTarjeta.length() != 16) {
//            JOptionPane.showMessageDialog(null, "Numero de tarjeta invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else {
//            String securityNumbers = JOptionPane.showInputDialog("Ingrese los 3 numeros de seguridad (atras de la tarjeta): ");
//            if (securityNumbers.length() != 3) {
//                JOptionPane.showMessageDialog(null, "Numero de tarjeta invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
//                return false;
//            } else {
//                JOptionPane.showMessageDialog(null, "Pago exitoso");
//                return true;
//            }
//        }

    }

    @Override
    public void darRegalia() {
        JOptionPane.showMessageDialog(null, "Por comprar la entrada en linea, presenta el codigo " + ((int) (Math.random() * 900000) + 100000)
                + " para un refresco complementario!!");
    }

}
