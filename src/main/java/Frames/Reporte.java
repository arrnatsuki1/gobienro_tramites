package Frames;

import DAO.ITramiteDAO;
import DAO.TramiteDAO;
import Entidades.Automovil;
import Entidades.Licencia;
import Entidades.Persona;
import Entidades.Placa;
import Entidades.Tramite;
import Utilidades.Encriptacion;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author jctri
 */
public class Reporte extends javax.swing.JFrame {

    
    /**
     * NOTA 
     * Jose, como estamos haciendo los reportes generales y los reportes
     * de una persona en general en el mismo JFrame, vamos a tener
     * que poner un modelo de tabla donde salga el nombre de la persona
     * en caso de ser los reportes generales y un modelo de tabla diferente
     * para las personas en particular
     * 
     */
    
    /**
     * Creates new form Reporte
     */
    
    private Persona consultante;
    private final ITramiteDAO daotramite;
    
    /**
     * inicio es desde que valor a va a iniciar la consulta
     * fin donde va a terminar la toma de datos
     * limit el limite de cuantos registros nos vamos a traer de la base de datos
     */
    private int inicio = 0, fin = 10, limite = 100;
    
    private List<Tramite> tramites;
    
    public Reporte(boolean p) {
        daotramite = new TramiteDAO();
        initComponents();
        if (p) {
            configurarHistorialPersona();
        }else{
            configurarTodosLosTramites();
        }

    }
    /**
     * Configura todos los tramites de una persona guiados por el limite
     */
    private void configurarHistorialPersona() {
        this.consultante = new Persona();
        SeleccionarPersonaDialog dialog = new SeleccionarPersonaDialog(null, true, consultante);

        if (consultante == null) {
            System.out.println("ERROR NO SE QUE PASO");
            this.dispose();
        }
        
        txtNombre.setText(consultante.getNombre());
        obtenerTramitesPersonas();
    }
    
    /**
     * Configura todos los tramites de la base de datos guiados por el limite
     */
    private void configurarTodosLosTramites() {
        tramites = daotramite.listaTramite(inicio, limite);
        llenarTabla(tramites);
    }
    /**
     * Obtiene tramites de la persona guiados por el limite
     */
    private void obtenerTramitesPersonas() {
        tramites = daotramite
                .listaTramitePersona(consultante, inicio, limite);
        llenarTabla(tramites);
    }

    /**
     * Este es el metodo que hiciste Carlos, el otro que dice llenarTabla es para
     * llenar la tabla, no hay que hacer consultas en metodos con nombres genericos
     */
    public void llenarTabla2() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String nombre = txtNombre.getText().toLowerCase();
        ITramiteDAO dao = new TramiteDAO();
        List<Tramite> listaTramite = dao.listaTramite(inicio, fin);

        List<Tramite> listaTramiteNombre = new ArrayList<Tramite>();
        for (Tramite tramite : listaTramite) {
            if (tramite.getPersona().getNombre().toLowerCase().contains(nombre)) {
                listaTramiteNombre.add(tramite);
            }
        }
        DefaultTableModel def = (DefaultTableModel) tabla.getModel();
        def.setRowCount(0);
        for (int i = 0; i < listaTramiteNombre.size(); i++) {
            Object[] datos = new Object[def.getColumnCount()];
            if (listaTramiteNombre.get(i) instanceof Placa) {
                datos[0] = "Expedicion de Placa";
            }
            if (listaTramiteNombre.get(i) instanceof Licencia) {
                datos[0] = "Expedicion de Licencia";
            }
            datos[1] = listaTramiteNombre.get(i).getPersona().getNombre();
            datos[2] = formato.format(listaTramiteNombre.get(i).getFechaEmision().getTime());
            datos[3] = listaTramiteNombre.get(i).getCosto();
            def.addRow(datos);
        }
    }
    
    /**
     * Metodo para llenar la tabla con tramites
     * @param tramites los tramites que se llenaran en la tabla
     */
    public void llenarTabla(List<Tramite> tramites) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        DefaultTableModel def = (DefaultTableModel) tabla.getModel();
        
        List<Tramite> listaAcortada = new ArrayList();
        for(int i = inicio; i<fin; i++) {
            if(i>=tramites.size()){
                break;
            }
            listaAcortada.add(tramites.get(i));
        }
        
        def.setRowCount(0);
        for (int i = 0; i < listaAcortada.size(); i++) {
            Object[] datos = new Object[def.getColumnCount()];
            
            if (listaAcortada.get(i) instanceof Placa) {
                datos[0] = "Expedicion de Placa";
                Placa t = (Placa)listaAcortada.get(i);
                datos[1] = t.getActiva();
            }
            if (listaAcortada.get(i) instanceof Licencia) {
                datos[0] = "Expedicion de Licencia";
                Licencia t = (Licencia)listaAcortada.get(i);
                datos[1] = t.getEstado();
            }

            datos[2] = formato.format(listaAcortada.get(i).getFechaEmision().getTime());
            datos[3] = listaAcortada.get(i).getCosto();
            def.addRow(datos);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnPDF = new javax.swing.JButton();
        btnFecha = new javax.swing.JButton();
        btnTipo = new javax.swing.JButton();
        btnBuscarporNombre = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnPeriodo = new javax.swing.JButton();
        btnSigPagina = new javax.swing.JButton();
        btnAntPagina = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 0, 51));

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Reporte de Tramites");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 542, -1));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tipo de Tramite", "Estado", "Fecha realizacion", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 540, 180));

        btnPDF.setText("Generar PDF");
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        jPanel1.add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, -1, -1));

        btnFecha.setText("Buscar por Fecha");
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });
        jPanel1.add(btnFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        btnTipo.setText("Buscar por Tipo de Tramite");
        btnTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoActionPerformed(evt);
            }
        });
        jPanel1.add(btnTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 170, -1));

        btnBuscarporNombre.setText("Buscar por nombre");
        btnBuscarporNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarporNombreActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarporNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 170, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, -1));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 230, -1));

        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        btnPeriodo.setText("Periodo");
        btnPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeriodoActionPerformed(evt);
            }
        });
        jPanel1.add(btnPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 120, -1));

        btnSigPagina.setText(">");
        btnSigPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSigPaginaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSigPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, -1, -1));

        btnAntPagina.setText("<");
        btnAntPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAntPaginaActionPerformed(evt);
            }
        });
        jPanel1.add(btnAntPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Principal pl = new Principal(false, new Persona());
        pl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarporNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarporNombreActionPerformed
        llenarTabla2();
    }//GEN-LAST:event_btnBuscarporNombreActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnSigPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSigPaginaActionPerformed
        this.inicio += 10;
        this.fin += 10;
        
        //En caso de que inicio sea igual al limite significa que tenemos que traer
        //Mas tramites de la base de datos
        if(this.inicio == limite) {
            if(consultante!=null) {
                configurarHistorialPersona();
            }else{
                configurarTodosLosTramites();
            }
            this.inicio = 0;
            this.fin = 10;
        }
        
        llenarTabla(tramites);
    }//GEN-LAST:event_btnSigPaginaActionPerformed

    private void btnAntPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAntPaginaActionPerformed
        if (this.inicio == 0) {
            return;
        }
        this.inicio -= 10;
        this.fin -= 10;
        llenarTabla(tramites);
    }//GEN-LAST:event_btnAntPaginaActionPerformed

    private void btnTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoActionPerformed
        buscarPorTramite();
    }//GEN-LAST:event_btnTipoActionPerformed

    private void btnPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeriodoActionPerformed
        buscarPorPeriodo();
    }//GEN-LAST:event_btnPeriodoActionPerformed

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        Calendar fecha = new GregorianCalendar();
        buscarPorFecha(fecha);
    }//GEN-LAST:event_btnFechaActionPerformed

    private void buscarPorFecha(Calendar fecha){
        if(consultante!=null){
            buscarPorFechaConsultante(fecha);
        }else{
            buscarPorFechaTodos(fecha);
        }
        
        llenarTabla(tramites);
    }
    
    private void buscarPorFechaTodos(Calendar c) {
        new SeleccionarFecha(this, true, c);
        tramites = daotramite.listaTramiteFechaTodos( c, inicio, limite);
    }
    
    private void buscarPorFechaConsultante(Calendar c) {
        new SeleccionarFecha(this, true, c);
        tramites = daotramite.listaTramitePersona(consultante, c, inicio, limite);
    }
    
    private void buscarPorPeriodo(){};
    
    private void buscarPorTramite() {
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAntPagina;
    private javax.swing.JButton btnBuscarporNombre;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnPeriodo;
    private javax.swing.JButton btnSigPagina;
    private javax.swing.JButton btnTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
