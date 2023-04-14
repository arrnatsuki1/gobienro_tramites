/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Frames;

import DAO.IPersonaDAO;
import DAO.PersonaDAO;
import Entidades.Persona;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rosa Rodriguez y Jose Trista
 */
public class SeleccionarPersonaDialog extends javax.swing.JDialog {

    private final IPersonaDAO daopersona;

    private Persona personaSeleccionada;

    /**
     * Creates new form SeleccionarPersonaDialog
     */
    public SeleccionarPersonaDialog(java.awt.Frame parent, boolean modal, Persona selectedPersona) {
        super(parent, modal);
        initComponents();
        
        daopersona = new PersonaDAO();

        this.personaSeleccionada = selectedPersona;

        tablaPersonas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {

                    dobleClick(evt);

                }
            }

        });
       mostrarTabla(listaTablaActual());
        this.setLocationRelativeTo(parent);
        
        this.setVisible(true);
  
    }

    private void dobleClick(MouseEvent evt) {
        JTable tabla = (JTable) evt.getSource();
        int row = tabla.getSelectedRow();

        Persona dummy = daopersona.consultarRFC((String) tabla.getValueAt(row, 3));
        
        personaSeleccionada.setId(dummy.getId());
        personaSeleccionada.setDiscapacitado(dummy.getDiscapacitado());
        personaSeleccionada.setFechaNacimiento(dummy.getFechaNacimiento());
        personaSeleccionada.setNombre(dummy.getNombre());
        personaSeleccionada.setPrimerApellido(dummy.getPrimerApellido());
        personaSeleccionada.setRFC(dummy.getRFC());
        personaSeleccionada.setSegundoApellido(dummy.getSegundoApellido());
        personaSeleccionada.setTelefono(dummy.getTelefono());
        personaSeleccionada.setTramites(dummy.getTramites());
        personaSeleccionada.setVehiculos(dummy.getVehiculos());
        
        if (personaSeleccionada == null) {
            System.out.println("ERROR DOBLE CLICK");
        }
        
        this.dispose();
        
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
        jLabel1 = new javax.swing.JLabel();
        txtRfc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPersonas = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        calendario = new com.github.lgooddatepicker.components.CalendarPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar persona");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("RFC Persona");
        background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 14, -1, -1));

        txtRfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRfcKeyReleased(evt);
            }
        });
        background.add(txtRfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 12, 160, -1));

        jLabel2.setText("Nombre");
        background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        background.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 100, -1));

        jLabel3.setText("Fecha de nacimiento :");
        background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tablaPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Primer Apellido", "Segundo Apellido", "RFC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPersonas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaPersonas.setShowGrid(true);
        jScrollPane1.setViewportView(tablaPersonas);

        background.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 360, 370));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        background.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, -1, -1));

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        background.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));
        background.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 390));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
      mostrarTabla(listaTablaActual());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        personaSeleccionada.setNombre("NULL");
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void txtRfcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRfcKeyReleased
        mostrarTabla(listaTablaActual());
    }//GEN-LAST:event_txtRfcKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        mostrarTabla(listaTablaActual());
    }//GEN-LAST:event_txtNombreKeyReleased
    
    public List<Persona> listaTablaActual(){
        List<Persona> listaPersonasActual = new ArrayList();
        

        if (txtNombre.getText().equals("") && txtRfc.getText().equals("")
                && calendario.getSelectedDate() == null) {
            return daopersona.consultarTodos();
        }

        boolean nombre = false, nacimiento = false;
        
        if (!txtRfc.getText().equals("")) {

            listaPersonasActual = daopersona.consultarRFClista(txtRfc.getText());
          
            return listaPersonasActual;
        }

        if (!txtNombre.getText().equalsIgnoreCase("")) {
            nombre = true;
        }

        LocalDate fecha = calendario.getSelectedDate();
        Calendar fecha_nacimiento = new GregorianCalendar();

        if (fecha != null) {
            nacimiento = true;
            fecha_nacimiento.set(fecha.getYear(), fecha.getMonthValue() - 1, fecha.getDayOfMonth());
        }

        if (nombre && nacimiento) {
            
           listaPersonasActual = daopersona.buscarPorNacimiento(fecha_nacimiento);
           listaPersonasActual = this.buscarporNombre(listaPersonasActual);
            
            return listaPersonasActual;
        }

        if (nombre) {
            
            listaPersonasActual = daopersona.consultarTodos();
            
            listaPersonasActual = this.buscarporNombre(listaPersonasActual);
            return listaPersonasActual;
        }

        if (nacimiento) {
            
            listaPersonasActual = daopersona.buscarPorNacimiento(fecha_nacimiento);
            if (listaPersonasActual == null || listaPersonasActual.isEmpty()) {
                listaPersonasActual = new ArrayList();
            }
            return listaPersonasActual;
        }
        return listaPersonasActual;
               
    }
    
//    private Persona obtenerPersona() {
//        Persona p = new Persona();
//        LocalDate fecha = calendario.getSelectedDate();
//
//        Calendar nacimiento = new GregorianCalendar();
//
//        if (fecha != null) {
//            nacimiento.set(fecha.getYear(), fecha.getMonthValue() - 1, fecha.getDayOfMonth());
//            p.setFechaNacimiento(nacimiento);
//        }
//        p.setPrimerApellido(txtPrimerApellido.getText());
//        p.setSegundoApellido(txtSegundoApellido.getText());
//        p.setNombre(txtNombre.getText());
//        p.setRFC(txtRfc.getText());
//        return p;
//    }

    private Persona buscarPorRFC() {

        Persona p = daopersona.consultarRFC(txtRfc.getText().toUpperCase());

        if (p == null) {
            return null;
        }

        return p;
    }
    
    private List<Persona> buscarporNombre(List<Persona> listapersona){
        List<Persona> listaAuxiliar = new ArrayList<Persona>();
        for (Persona persona: listapersona) {
                String nombreCompleto = persona.getNombre()+" "
                        + ""+persona.getPrimerApellido()+" "
                        + ""+persona.getSegundoApellido();
                if(nombreCompleto.toLowerCase().contains(txtNombre.getText().toLowerCase())){
                    listaAuxiliar.add(persona);
                }
        }
        return listaAuxiliar;
    }

    private void mostrarTabla(List<Persona> lista) {
               DefaultTableModel modelo = (DefaultTableModel) tablaPersonas.getModel();
        modelo.setRowCount(0);
        for (Persona persona : lista) {
            Object[] datos = new Object[modelo.getColumnCount()];
            datos[0] = persona.getNombre();
            datos[1] = persona.getPrimerApellido();
            datos[2] = persona.getSegundoApellido();
            datos[3] = persona.getRFC();
            modelo.addRow(datos);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRegresar;
    private com.github.lgooddatepicker.components.CalendarPanel calendario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPersonas;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRfc;
    // End of variables declaration//GEN-END:variables
}
