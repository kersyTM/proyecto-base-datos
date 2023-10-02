package com.mycompany.cineclienteservidor;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class FormCartelera extends javax.swing.JFrame {

    public static ArrayList<Pago> pagos = new ArrayList<>();

    /**
     * Creates new form FormCartelera
     */
    public FormCartelera() {
        initComponents();

        Pelicula pelicula = new Pelicula();
        DefaultTableModel modelo = pelicula.Mostrar();
        peliculasTb.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        peliculasTb = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 3, 36)); // NOI18N
        jLabel1.setText("Cartelera de CineCrud!");

        peliculasTb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        peliculasTb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                peliculasTbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(peliculasTb);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(533, 533, 533)
                .addComponent(jLabel1)
                .addContainerGap(612, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(99, 99, 99)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void peliculasTbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_peliculasTbMouseClicked
        int fila = peliculasTb.getSelectedRow();
        String peliculaSeleccionada = peliculasTb.getValueAt(fila, 1).toString();
        int optConfirm = JOptionPane.showConfirmDialog(null, "Desea comprar entradas para: " + peliculasTb.getValueAt(fila, 1) + "?");
        if (optConfirm == 0) {
            int optPago = JOptionPane.showOptionDialog(null, "Seleccione su metodo de pago preferido", "PROCEDER A PAGO",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new String[]{"1. PAGO EN PERSONA", "2. PAGO EN LINEA"}, 0);
            if (optPago == 0) {
                String montoString = peliculasTb.getValueAt(fila, 5).toString();
                double monto = Double.parseDouble(montoString);
                MetodoPago pagoenpersona = new PagoEnPersona();
                if (pagoenpersona.realizarPago(monto)) {
                    String nombre = JOptionPane.showInputDialog("Ingrese su nombre: ");
                    pagos.add(new Pago(nombre, peliculaSeleccionada, monto, "En Persona"));
                    pagoenpersona.darRegalia();
                }
            } else if (optPago == 1) {
                String montoString = peliculasTb.getValueAt(fila, 5).toString();
                double monto = Double.parseDouble(montoString);
                MetodoPago pagoenlinea = new PagoEnLinea();
                if (pagoenlinea.realizarPago(monto)) {
                    String nombre = JOptionPane.showInputDialog("Ingrese su nombre: ");
                    pagos.add(new Pago(nombre, peliculaSeleccionada, monto, "En Linea"));
                    pagoenlinea.darRegalia();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Cancelando...");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Cancelando...");
        }
    }//GEN-LAST:event_peliculasTbMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormCartelera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCartelera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCartelera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCartelera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCartelera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable peliculasTb;
    // End of variables declaration//GEN-END:variables
}
