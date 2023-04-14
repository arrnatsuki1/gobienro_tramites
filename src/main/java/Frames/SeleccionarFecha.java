/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Frames;

import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan Rodriguez
 */
public class SeleccionarFecha extends javax.swing.JDialog {

    /**
     * Creates new form SeleccionarFecha
     */
    private Calendar fecha;

    public SeleccionarFecha(java.awt.Frame parent, boolean modal, Calendar fecha) {
        super(parent, modal);
        initComponents();
        this.fecha = fecha;
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        calendario = new com.github.lgooddatepicker.components.CalendarPanel();
        btnAceptar = new javax.swing.JButton();
        btnCalcelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(250, 300));
        setMinimumSize(new java.awt.Dimension(250, 300));
        setPreferredSize(new java.awt.Dimension(250, 300));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setMaximumSize(new java.awt.Dimension(227, 248));
        background.setMinimumSize(new java.awt.Dimension(227, 248));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        background.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        background.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        btnCalcelar.setText("Cancelar");
        btnCalcelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcelarActionPerformed(evt);
            }
        });
        background.add(btnCalcelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            LocalDate fseleccionada = calendario.getSelectedDate();
            this.fecha.set(fseleccionada.getYear(), fseleccionada.getMonthValue() - 1, fseleccionada.getDayOfMonth());
            this.dispose();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "NO SELECCIONASTE NINGUNA FECHA", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCalcelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCalcelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCalcelar;
    private com.github.lgooddatepicker.components.CalendarPanel calendario;
    // End of variables declaration//GEN-END:variables
}