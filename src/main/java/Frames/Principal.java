package Frames;

import DAO.ILicenciaDAO;
import DAO.IPersonaDAO;
import DAO.LicenciaDAO;
import DAO.PersonaDAO;
import Entidades.Licencia;
import Entidades.Persona;
import Excepciones.RFCExistenteException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import swing_propio.GobiernoButton;


/**
 *
 * @author ib
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    
    private Persona persona;
    private final Persona consultante = new Persona();
    public Principal(boolean logIn, Persona p) {
        initComponents();
        persona = p;
        if (logIn) {
            this.botonLicencia.setEnabled(true);
            this.botonPlaca.setEnabled(true);
            this.botonSalir.setEnabled(true);
            this.setTitle(p.getNombre());
        } else {
            this.botonLicencia.setEnabled(false);
            this.botonPlaca.setEnabled(false);
            this.botonSalir.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        botonLicencia = new swing_propio.GobiernoButton();
        baner = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        botonSalir = new javax.swing.JButton();
        botonSolicitante = new javax.swing.JButton();
        botonPlaca = new swing_propio.GobiernoButton();
        jButton7 = new swing_propio.GobiernoButton();
        btnRecepcion = new swing_propio.GobiernoButton();
        BtnConsultas = new swing_propio.GobiernoButton();
        btnGenerarRegistros = new swing_propio.GobiernoButton();

        jButton2.setText("Licencia");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonLicencia.setText("Solicitar Licencia");
        botonLicencia.setBackground(new java.awt.Color(255, 255, 255));
        botonLicencia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        botonLicencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonLicencia.setFocusPainted(false);
        botonLicencia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonLicencia.setForeground(new java.awt.Color(0, 0, 0));
        botonLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLicenciaActionPerformed(evt);
            }
        });
        jPanel1.add(botonLicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 160, 55));

        baner.setBackground(new java.awt.Color(16, 49, 43));

        jLabel1.setText("Tramites");
        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        botonSalir.setText("Salir");
        botonSalir.setBackground(new java.awt.Color(157, 36, 73));
        botonSalir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        botonSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonSalir.setEnabled(false);
        botonSalir.setFocusPainted(false);
        botonSalir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonSalir.setForeground(new java.awt.Color(255, 255, 255));
        botonSalir.setRequestFocusEnabled(false);
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        botonSolicitante.setText("Ingresar solicitante");
        botonSolicitante.setBackground(new java.awt.Color(188, 149, 92));
        botonSolicitante.setBorder(null);
        botonSolicitante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonSolicitante.setFocusPainted(false);
        botonSolicitante.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonSolicitante.setForeground(new java.awt.Color(0, 0, 0));
        botonSolicitante.setPreferredSize(new java.awt.Dimension(35, 25));
        botonSolicitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSolicitanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout banerLayout = new javax.swing.GroupLayout(baner);
        baner.setLayout(banerLayout);
        banerLayout.setHorizontalGroup(
            banerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(banerLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(botonSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        banerLayout.setVerticalGroup(
            banerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(banerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(banerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(banerLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1)
                        .addGap(4, 13, Short.MAX_VALUE))
                    .addGroup(banerLayout.createSequentialGroup()
                        .addGroup(banerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonSolicitante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        jPanel1.add(baner, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 541, -1));

        botonPlaca.setText("Placas");
        botonPlaca.setBackground(new java.awt.Color(255, 255, 255));
        botonPlaca.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        botonPlaca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonPlaca.setFocusPainted(false);
        botonPlaca.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonPlaca.setForeground(new java.awt.Color(0, 0, 0));
        botonPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPlacaActionPerformed(evt);
            }
        });
        jPanel1.add(botonPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 160, 55));

        jButton7.setText("Reportes");
        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setFocusPainted(false);
        jButton7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 160, 55));

        btnRecepcion.setText("Recepcion");
        btnRecepcion.setBackground(new java.awt.Color(255, 255, 255));
        btnRecepcion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnRecepcion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRecepcion.setFocusPainted(false);
        btnRecepcion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRecepcion.setForeground(new java.awt.Color(0, 0, 0));
        btnRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 160, 55));

        BtnConsultas.setText("Historial Persona");
        BtnConsultas.setBackground(new java.awt.Color(255, 255, 255));
        BtnConsultas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        BtnConsultas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnConsultas.setFocusPainted(false);
        BtnConsultas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        BtnConsultas.setForeground(new java.awt.Color(0, 0, 0));
        BtnConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConsultasActionPerformed(evt);
            }
        });
        jPanel1.add(BtnConsultas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 160, 55));

        btnGenerarRegistros.setText("20 registros");
        btnGenerarRegistros.setBackground(new java.awt.Color(255, 255, 255));
        btnGenerarRegistros.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnGenerarRegistros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarRegistros.setFocusPainted(false);
        btnGenerarRegistros.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGenerarRegistros.setForeground(new java.awt.Color(0, 0, 0));
        btnGenerarRegistros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGenerarRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarRegistrosActionPerformed(evt);
            }
        });
        jPanel1.add(btnGenerarRegistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 160, 55));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonLicenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLicenciaActionPerformed
        
        Calendar fechaActual = new GregorianCalendar();
        long edad = fechaActual.get(Calendar.YEAR)- persona.getFechaNacimiento().get(Calendar.YEAR);
        
        if(edad < 18) {
            JOptionPane.showMessageDialog(this,
                    "UN MENOR DE EDAD NO PUEDE SACAR UNA LICENCIA(SI PUEDE PERO AQUI NO)",
                    "Informacion", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
            
        SolicitarLicencia sl = new SolicitarLicencia(persona);
        sl.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonLicenciaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void botonPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPlacaActionPerformed
        buscarLicenciasVigentes(persona);
    }//GEN-LAST:event_botonPlacaActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Reporte rp = new Reporte(false,null);
        rp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        this.persona = null;
        this.botonLicencia.setEnabled(false);
        this.botonPlaca.setEnabled(false);
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonSolicitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSolicitanteActionPerformed
        IngresaSolicitante i = new IngresaSolicitante();
        i.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonSolicitanteActionPerformed

    private void btnGenerarRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarRegistrosActionPerformed
        IPersonaDAO dao = new PersonaDAO();
        List<Persona> personas;
        
        try{
            personas = dao.agregar20Personas();
        } catch(RFCExistenteException e) {
            JOptionPane.showMessageDialog(this, "estas personas ya estan agregadas");
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Va a tener 20 mensajes seguidos mostrando la informacion de cada persona agregada");
        
        for(Persona p : personas) {
            
            String msg = String.format("LA PERSONA %s CON RFC %s esta lista para hacer tramites",
                    p.getNombre()+" "+p.getPrimerApellido()+" "+p.getSegundoApellido(),
                    p.getRFC());
                    JOptionPane.showMessageDialog(this, msg);
            
        }
        
        
    }//GEN-LAST:event_btnGenerarRegistrosActionPerformed

    private void btnRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionActionPerformed
        RecepcionDialog r = new RecepcionDialog(this, true);
    }//GEN-LAST:event_btnRecepcionActionPerformed

    private void BtnConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConsultasActionPerformed
        SeleccionarPersona selecP = new SeleccionarPersona();
        selecP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnConsultasActionPerformed
    public void buscarLicenciasVigentes(Persona persona){
        ILicenciaDAO licenciadao = new LicenciaDAO();
        List <Licencia> listalicencia = licenciadao.listaLicenciasVigentes(persona);
        if(listalicencia.isEmpty()){
            JOptionPane.showMessageDialog(this, "Usted no tiene licencia vigente");
            return;
        }
        SolicitarPlacas sp = new SolicitarPlacas(persona);
        sp.setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConsultas;
    private javax.swing.JPanel baner;
    private javax.swing.JButton botonLicencia;
    private javax.swing.JButton botonPlaca;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton botonSolicitante;
    private javax.swing.JButton btnGenerarRegistros;
    private javax.swing.JButton btnRecepcion;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
