package Frames;

import DAO.Estados;
import DAO.ITramiteDAO;
import DAO.TramiteDAO;
import Entidades.Licencia;
import Entidades.Persona;
import Entidades.Placa;
import Entidades.Tramite;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import swing_propio.GobiernoButton;
import swing_propio.IButton;
import swing_propio.IPanel;

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
     * NOTA Jose, como estamos haciendo los reportes generales y los reportes de
     * una persona en general en el mismo JFrame, vamos a tener que poner un
     * modelo de tabla donde salga el nombre de la persona en caso de ser los
     * reportes generales y un modelo de tabla diferente para las personas en
     * particular
     *
     */
    /**
     * Creates new form Reporte
     */
    private Persona consultante;
    private final ITramiteDAO daotramite;

    /**
     * inicio es desde que valor a va a iniciar la consulta fin donde va a
     * terminar la toma de datos limit el limite de cuantos registros nos vamos
     * a traer de la base de datos
     */
    private int inicio = 0, fin = 10, limite = 100;

    private List<Tramite> tramites;

    public Reporte(boolean p, Persona consultante) {
        daotramite = new TramiteDAO();
        initComponents();
        if (p) {
            this.btnBuscarporNombre.setEnabled(false);
            this.txtNombre.setEnabled(false);
            this.txtPrimerApellido.setEnabled(false);
            this.txtSegundoApellido.setEnabled(false);
            this.consultante = consultante;
            configurarHistorialPersona();
        } else {
            configurarTodosLosTramites();
        }

        IPanel fondo1 = (IPanel) panelFondo;
        fondo1.setBotonReferencia(this.btnTipo);
        fondo1.setColor(new Color(35, 91, 78));
        fondo1.setPanel(panelOpcionesTipo);
        desactivarPanelFondo();
        
        inicializarBotones();
        
    }

    private void inicializarBotones() {
        
        this.btnLicencia.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent evt) {
                
                btnLicencia.setBackground(new Color(16,49,43));
                
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                btnLicencia.setBackground(new Color(35,91,78));
            }
            
        });
        this.btnPlaca.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent evt) {
                
                btnPlaca.setBackground(new Color(16,49,43));
                
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                btnPlaca.setBackground(new Color(35,91,78));
            }
            
        });
        this.btnPDF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                btnPDF.setBackground(new Color(188,149,92));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                btnPDF.setBackground(new Color(255,255,255));
            }
        });
        
        
    }
    
    /**
     * Configura todos los tramites de una persona guiados por el limite
     */
    private void configurarHistorialPersona() {

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
     * Este es el metodo que hiciste Carlos, el otro que dice llenarTabla es
     * para llenar la tabla, no hay que hacer consultas en metodos con nombres
     * genericos
     */
    public void consultarPorNombre() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//      String nombre = txtNombre.getText().toLowerCase();

        Persona p = new Persona();
        p.setNombre(txtNombre.getText().toUpperCase());
        p.setPrimerApellido(txtPrimerApellido.getText().toUpperCase());
        p.setSegundoApellido(txtSegundoApellido.getText().toUpperCase());

        List<Tramite> listaTramite = daotramite.listaTramiteNombre(p, inicio, fin);
        System.out.println(listaTramite.size());
        DefaultTableModel def = (DefaultTableModel) tabla.getModel();
        def.setRowCount(0);
        for (int i = 0; i < listaTramite.size(); i++) {
            Object[] datos = new Object[def.getColumnCount()];
            if (listaTramite.get(i) instanceof Placa) {
                datos[0] = "Expedicion de Placa";
                Placa t = (Placa) listaTramite.get(i);
                datos[1] = t.getActiva();
            }
            if (listaTramite.get(i) instanceof Licencia) {
                datos[0] = "Expedicion de Licencia";
                Licencia t = (Licencia) listaTramite.get(i);
                datos[1] = t.getEstado();
            }

            datos[2] = formato.format(listaTramite.get(i).getFechaEmision().getTime());
            datos[3] = listaTramite.get(i).getCosto();
            datos[4] = listaTramite.get(i).getPersona().getNombre();
            def.addRow(datos);
        }
    }

//    public List<Tramite> listaTablaActual(){
//        List<Tramite> listaTramiteActual = new ArrayList();   
//        
//       if(txtNombre.getText().equalsIgnoreCase("")){
//          return daotramite.listaTramite(inicio, limite);
//       }
//       
//       if (!txtNombre.getText().equalsIgnoreCase("")) {
//             listaTramiteActual = daotramite.listaTramite(inicio, limite);
//            
//            listaTramiteActual = this.buscarporNombre(listaTramiteActual);
//            return listaTramiteActual;
//        }
//    }
//    
//    private List<Persona> buscarporNombre(List<Persona> listapersona){
//        List<Persona> listaAuxiliar = new ArrayList<Persona>();
//        for (Persona persona: listapersona) {
//                String nombreCompleto = persona.getNombre()+" "
//                        + ""+persona.getPrimerApellido()+" "
//                        + ""+persona.getSegundoApellido();
//                if(nombreCompleto.toLowerCase().contains(txtNombre.getText().toLowerCase())){
//                    listaAuxiliar.add(persona);
//                }
//        }
//        return listaAuxiliar;
//    }
    /**
     * Metodo para llenar la tabla con tramites
     *
     * @param tramites los tramites que se llenaran en la tabla
     */
    public void llenarTabla(List<Tramite> tramites) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        DefaultTableModel def = (DefaultTableModel) tabla.getModel();

        List<Tramite> listaAcortada = new ArrayList();
        for (int i = inicio; i < fin; i++) {
            if (i >= tramites.size()) {
                break;
            }
            listaAcortada.add(tramites.get(i));
        }

        def.setRowCount(0);
        for (int i = 0; i < listaAcortada.size(); i++) {
            Object[] datos = new Object[def.getColumnCount()];

            if (listaAcortada.get(i) instanceof Placa) {
                datos[0] = "Expedicion de Placa";
                Placa t = (Placa) listaAcortada.get(i);
                datos[1] = t.getActiva();
            }
            if (listaAcortada.get(i) instanceof Licencia) {
                datos[0] = "Expedicion de Licencia";
                Licencia t = (Licencia) listaAcortada.get(i);
                datos[1] = t.getEstado();
            }

            datos[2] = formato.format(listaAcortada.get(i).getFechaEmision().getTime());
            datos[3] = listaAcortada.get(i).getCosto();
            datos[4] = listaAcortada.get(i).getPersona().getNombre()
                    + " " + listaAcortada.get(i).getPersona().getPrimerApellido()
                    + " " + listaAcortada.get(i).getPersona().getSegundoApellido();
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

        background = new javax.swing.JPanel();
        btnPDF = new IButton();
        btnFecha = new GobiernoButton();
        btnBuscarporNombre = new GobiernoButton();
        btnCancelar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnPeriodo = new GobiernoButton();
        btnSigPagina = new GobiernoButton();
        btnAntPagina = new GobiernoButton();
        btnTipo = new GobiernoButton();
        panelFondo = new IPanel();
        panelOpcionesTipo = new JPanel();
        btnLicencia = new javax.swing.JButton();
        btnPlaca = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrimerApellido = new javax.swing.JTextField();
        txtSegundoApellido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        baner = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(542, 520));
        setResizable(false);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPDF.setBackground(new java.awt.Color(255, 255, 255));
        btnPDF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnPDF.setForeground(new java.awt.Color(0, 0, 0));
        btnPDF.setText("Generar PDF");
        btnPDF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(188, 149, 92), 5, true));
        btnPDF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPDF.setFocusPainted(false);
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        background.add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 100, 70));

        btnFecha.setBackground(new java.awt.Color(255, 255, 255));
        btnFecha.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnFecha.setForeground(new java.awt.Color(0, 0, 0));
        btnFecha.setText("Fecha");
        btnFecha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFecha.setFocusPainted(false);
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });
        background.add(btnFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 100, 40));

        btnBuscarporNombre.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarporNombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscarporNombre.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscarporNombre.setText("Nombre");
        btnBuscarporNombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnBuscarporNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarporNombre.setFocusPainted(false);
        btnBuscarporNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarporNombreActionPerformed(evt);
            }
        });
        background.add(btnBuscarporNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 100, 40));

        btnCancelar.setBackground(new java.awt.Color(157, 36, 73));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(null);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        background.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 110, 40));
        background.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 160, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre:");
        background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        btnPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        btnPeriodo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnPeriodo.setForeground(new java.awt.Color(0, 0, 0));
        btnPeriodo.setText("Periodo");
        btnPeriodo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnPeriodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPeriodo.setFocusPainted(false);
        btnPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeriodoActionPerformed(evt);
            }
        });
        background.add(btnPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 100, 40));

        btnSigPagina.setBackground(new java.awt.Color(255, 255, 255));
        btnSigPagina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSigPagina.setForeground(new java.awt.Color(0, 0, 0));
        btnSigPagina.setText(">");
        btnSigPagina.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnSigPagina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSigPagina.setFocusPainted(false);
        btnSigPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSigPaginaActionPerformed(evt);
            }
        });
        background.add(btnSigPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 50, -1));

        btnAntPagina.setBackground(new java.awt.Color(255, 255, 255));
        btnAntPagina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAntPagina.setForeground(new java.awt.Color(0, 0, 0));
        btnAntPagina.setText("<");
        btnAntPagina.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnAntPagina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAntPagina.setFocusPainted(false);
        btnAntPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAntPaginaActionPerformed(evt);
            }
        });
        background.add(btnAntPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 50, -1));

        btnTipo.setBackground(new java.awt.Color(255, 255, 255));
        btnTipo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnTipo.setForeground(new java.awt.Color(0, 0, 0));
        btnTipo.setText("Tipo");
        btnTipo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTipo.setFocusPainted(false);
        btnTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoActionPerformed(evt);
            }
        });
        background.add(btnTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 100, 40));

        panelFondo.setOpaque(false);
        panelFondo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                panelFondoFocusLost(evt);
            }
        });
        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelOpcionesTipo.setBackground(new java.awt.Color(35, 91, 78));
        panelOpcionesTipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                panelOpcionesTipoFocusLost(evt);
            }
        });
        panelOpcionesTipo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLicencia.setBackground(new java.awt.Color(35, 91, 78));
        btnLicencia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnLicencia.setForeground(new java.awt.Color(255, 255, 255));
        btnLicencia.setText("Licencias");
        btnLicencia.setBorder(null);
        btnLicencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLicencia.setFocusPainted(false);
        btnLicencia.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                btnLicenciaComponentResized(evt);
            }
        });
        btnLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLicenciaActionPerformed(evt);
            }
        });
        panelOpcionesTipo.add(btnLicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 110, 130));

        btnPlaca.setBackground(new java.awt.Color(35, 91, 78));
        btnPlaca.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnPlaca.setForeground(new java.awt.Color(255, 255, 255));
        btnPlaca.setText("Placas");
        btnPlaca.setBorder(null);
        btnPlaca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlaca.setFocusPainted(false);
        btnPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlacaActionPerformed(evt);
            }
        });
        panelOpcionesTipo.add(btnPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 130));

        panelFondo.add(panelOpcionesTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 220, 130));

        background.add(panelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 330));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Primer Apellido");
        background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Segundo Apellido");
        background.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));
        background.add(txtPrimerApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 160, -1));
        background.add(txtSegundoApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 160, -1));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tipo de Tramite", "Estado", "Fecha realizacion", "Costo", "Nombre Persona"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        background.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 542, 180));

        baner.setBackground(new java.awt.Color(16, 49, 43));
        baner.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reporte de Tramites");
        baner.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 80));

        background.add(baner, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 542, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        consultarPorNombre();
    }//GEN-LAST:event_btnBuscarporNombreActionPerformed

    private void btnSigPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSigPaginaActionPerformed
        this.inicio += 10;
        this.fin += 10;

        //En caso de que inicio sea igual al limite significa que tenemos que traer
        //Mas tramites de la base de datos
        if (this.inicio == limite) {
            if (consultante != null) {
                configurarHistorialPersona();
            } else {
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
        //esta suma es para saber en que posicion de la pagina esta
        this.inicio -= 10;
        this.fin -= 10;
        llenarTabla(tramites);
    }//GEN-LAST:event_btnAntPaginaActionPerformed

    boolean opt = false;
    private void btnTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoActionPerformed
//        buscarPorTramite();
        if (opt) {
            desactivarPanelFondo();
            opt = false;
        } else {
            activarPanelFondo();
            opt = true;
        }

    }//GEN-LAST:event_btnTipoActionPerformed

    private void btnPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeriodoActionPerformed
        Calendar fecha1 = new GregorianCalendar(), fecha2 = new GregorianCalendar();
        buscarPorPeriodo(fecha1, fecha2);
    }//GEN-LAST:event_btnPeriodoActionPerformed

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        Calendar fecha = new GregorianCalendar();
        buscarPorFecha(fecha);
    }//GEN-LAST:event_btnFechaActionPerformed

    private void panelFondoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelFondoFocusLost
        desactivarPanelFondo();
    }//GEN-LAST:event_panelFondoFocusLost

    private void panelOpcionesTipoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelOpcionesTipoFocusLost
        desactivarPanelFondo();
    }//GEN-LAST:event_panelOpcionesTipoFocusLost

    private void btnLicenciaComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_btnLicenciaComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLicenciaComponentResized

    private void btnPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlacaActionPerformed
        StringBuffer respuesta = new StringBuffer();
        respuesta.append(Estados.TIPO_PLACA);

        if (this.consultante != null) {
            buscarPorTipoTramiteConsultante(respuesta);
        } else {
            buscarPorTipoTramiteTodos(respuesta);
        }

        desactivarPanelFondo();

        llenarTabla(tramites);
    }//GEN-LAST:event_btnPlacaActionPerformed

    private void btnLicenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLicenciaActionPerformed
        StringBuffer respuesta = new StringBuffer();
        respuesta.append(Estados.TIPO_LICENCIA);

        if (this.consultante != null) {
            buscarPorTipoTramiteConsultante(respuesta);
        } else {
            buscarPorTipoTramiteTodos(respuesta);
        }

        desactivarPanelFondo();

        llenarTabla(tramites);
    }//GEN-LAST:event_btnLicenciaActionPerformed

    private void activarPanelFondo() {
        this.panelFondo.setEnabled(true);
        this.panelFondo.setVisible(true);
        opt = true;
    }

    private void desactivarPanelFondo() {
        this.panelFondo.setEnabled(false);
        this.panelFondo.setVisible(false);
        opt = false;
    }

    /*AQUI EMPIEZAN LOS METODOS PARA BUSCAR POR FECHA*/
    private void buscarPorFecha(Calendar fecha) {
        if (consultante != null) {
            buscarPorFechaConsultante(fecha);
        } else {
            buscarPorFechaTodos(fecha);
        }

        llenarTabla(tramites);
    }

    private void buscarPorFechaTodos(Calendar c) {
        new SeleccionarFecha(this, true, c);
        tramites = daotramite.listaTramiteFechaTodos(c, inicio, limite);
    }

    private void buscarPorFechaConsultante(Calendar c) {
        new SeleccionarFecha(this, true, c);
        tramites = daotramite.listaTramitePersonaNacimiento(consultante, c, inicio, limite);
    }

    /*AQUI TERMINAN LOS METODOS PARA BUSCAR POR FECHA*/

 /*AQUI EMPIZAN LOS METODOS PARA LA BUSQUEDA POR PERIODO*/
    private void buscarPorPeriodo(Calendar f1, Calendar f2) {
        if (consultante != null) {
            buscarPorPeriodoConsultante(f1, f2);
        } else {
            buscarPorPeriodoTodos(f1, f2);
        }

        llenarTabla(tramites);
    }

    private void buscarPorPeriodoConsultante(Calendar f1, Calendar f2) {
        new SeleccionarPeriodo(this, true, f1, f2);
        tramites = daotramite.listaPeriodoPersona(consultante, f1, f2, inicio, limite);
    }

    private void buscarPorPeriodoTodos(Calendar f1, Calendar f2) {
        new SeleccionarPeriodo(this, true, f1, f2);
        tramites = daotramite.listaPeriodoTodos(f1, f2, inicio, limite);
    }

    /*AQUI TERMINAN LOS METODOS PARA LA BUSQUEDA POR PERIODO*/
    private void buscarPorTramite() {
        StringBuffer respuesta = new StringBuffer();
        if (consultante != null) {
            buscarPorTipoTramiteConsultante(respuesta);
        } else {
            buscarPorTipoTramiteTodos(respuesta);
        }

        llenarTabla(tramites);
    }

    private void buscarPorTipoTramiteConsultante(StringBuffer respuesta) {
        tramites = daotramite.listaPorTipoPersona(consultante, respuesta, inicio, limite);
    }

    private void buscarPorTipoTramiteTodos(StringBuffer respuesta) {
        tramites = daotramite.listaPorTipoTodos(respuesta, inicio, limite);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel baner;
    private javax.swing.JButton btnAntPagina;
    private javax.swing.JButton btnBuscarporNombre;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnLicencia;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnPeriodo;
    private javax.swing.JButton btnPlaca;
    private javax.swing.JButton btnSigPagina;
    private javax.swing.JButton btnTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelOpcionesTipo;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrimerApellido;
    private javax.swing.JTextField txtSegundoApellido;
    // End of variables declaration//GEN-END:variables
}
