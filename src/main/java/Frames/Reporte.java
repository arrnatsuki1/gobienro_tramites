package Frames;

import DAO.Estados;
import DAO.ITramiteDAO;
import DAO.TramiteDAO;
import Entidades.Licencia;
import Entidades.Persona;
import Entidades.Placa;
import Entidades.Tramite;
import Excepciones.FechaDisparejaException;
import PDF.PdfReporte;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import swing_propio.GobiernoButton;
import swing_propio.IButton;
import swing_propio.IPanel;

/**
 *  Clase para poder ver los tramites de todas las personas ya sea por fecha,
 *  periodo o tipos.
 * @author Rosa Rodriguez y Jose Trista
 */
public class Reporte extends javax.swing.JFrame {

    /**
     * NOTA Jose, como estamos haciendo los reportes generales y los reportes de
     * una persona en general en el mismo JFrame, vamos a tener que poner un
     * modelo de tabla donde salga el nombre de la persona en caso de ser los
     * reportes generales y un modelo de tabla diferente para las personas en
     * particular
     *  Pues quedara como recuerdo pero al final no lo hicimos
     */
    /**
     * Persona que realiza los tramites
     */
    private Persona consultante;
    /**
     * TramiteDAO para poder hacer las consultas
     */
    private final ITramiteDAO daotramite;
    /**
     * Lista de tramites que se muestran
     */
    private List<Tramite> listaTabla;
    /**
     * inicio es desde que valor a va a iniciar la consulta fin donde va a
     * terminar la toma de datos limit el limite de cuantos registros nos vamos
     * a traer de la base de datos
     */
    private int inicio = 0, fin = 10, limite = 100;
    /**
     * Lista de tramites que se muestran
     */
    private List<Tramite> tramites;
    /**
     * Metodo constructor que recibe una persona y un booleano, el booleano nos dice
     * si los tramites a consultar son para una persona y asi habilitar unas casillas o desabilitar otras
     * @param p boolean si hay persona: true, no hay: false
     * @param consultante Persona persona con la cual se realizaran las consultas
     */
    public Reporte(boolean p, Persona consultante) {
        daotramite = new TramiteDAO();
        initComponents();
        this.listaTabla = new ArrayList<Tramite>();
      
        if (p) {
            this.txtNombre.setEnabled(false);
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
        mostrarPanelOpcionesPeriodo(false);
        mostrarPanelFecha(false);
        inicializarBotones();
    }
    /**
     * Agrega oyentes a todos los botones del JFrame
     */
    private void inicializarBotones() {

        this.btnLicencia.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent evt) {

                btnLicencia.setBackground(new Color(16, 49, 43));

            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btnLicencia.setBackground(new Color(35, 91, 78));
            }

        });
        this.btnPlaca.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent evt) {

                btnPlaca.setBackground(new Color(16, 49, 43));

            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btnPlaca.setBackground(new Color(35, 91, 78));
            }

        });
        this.btnPDF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                btnPDF.setBackground(new Color(188, 149, 92));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btnPDF.setBackground(new Color(255, 255, 255));
            }
        });
        this.btnBuscar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent evt) {

                btnBuscar.setBackground(new Color(16, 49, 43));

            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btnBuscar.setBackground(new Color(35, 91, 78));
            }

        });
        this.btnBuscarFecha.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent evt) {

                btnBuscarFecha.setBackground(new Color(16, 49, 43));

            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btnBuscarFecha.setBackground(new Color(35, 91, 78));
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
     * Metodo para obtener una lista nueva con un nuevo inicio
     * pero para buscar por nombres
     * @return Lista de tramites
     */
    public List<Tramite> listaTablaActual() {
        List<Tramite> listaTramiteActual = new ArrayList();

        if (txtNombre.getText().equalsIgnoreCase("")) {
            return daotramite.listaTramite(inicio, limite);
        }

        if (!txtNombre.getText().equalsIgnoreCase("")) {
            listaTramiteActual = daotramite.listaTramite(inicio, limite);

            listaTramiteActual = this.buscarporNombre(listaTramiteActual);
            return listaTramiteActual;
        }
        return listaTramiteActual;
    }
    
    /**
     * Metodo para obtener una lista de tramites con nombres parecidos 
     * dentro de todos los tramites de la base de datos
     * @param listaTramite List<Tramite> con los tramites donde se buscaran nombres parecidos
     * @return List<Tramite> con todas las coincidencias
     */
    private List<Tramite> buscarporNombre(List<Tramite> listaTramite) {
        List<Tramite> listaAuxiliar = new ArrayList<Tramite>();
        for (Tramite tramite : listaTramite) {
            String nombreCompleto = tramite.getPersona().getNombre() + " "
                    + "" + tramite.getPersona().getPrimerApellido() + " "
                    + "" + tramite.getPersona().getSegundoApellido();
            if (nombreCompleto.toLowerCase().contains(txtNombre.getText().toLowerCase())) {
                listaAuxiliar.add(tramite);
            }
        }
        return listaAuxiliar;
    }

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
        listaTabla = listaAcortada;
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
        panelFecha = new javax.swing.JPanel();
        calendario = new com.github.lgooddatepicker.components.CalendarPanel();
        btnBuscarFecha = new javax.swing.JButton();
        btnPDF = new IButton();
        btnFecha = new GobiernoButton();
        btnCancelar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnPeriodo = new GobiernoButton();
        btnSigPagina = new GobiernoButton();
        btnAntPagina = new GobiernoButton();
        btnTipo = new GobiernoButton();
        panelOpcionesPeriodo = new javax.swing.JPanel();
        calendario1 = new com.github.lgooddatepicker.components.DatePicker();
        calendario2 = new com.github.lgooddatepicker.components.DatePicker();
        btnBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelFondo = new IPanel();
        panelOpcionesTipo = new JPanel();
        btnLicencia = new javax.swing.JButton();
        btnPlaca = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        baner = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(542, 520));
        setResizable(false);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelFecha.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelFecha.add(calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnBuscarFecha.setText("Buscar");
        btnBuscarFecha.setBackground(new java.awt.Color(35, 91, 78));
        btnBuscarFecha.setBorder(null);
        btnBuscarFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarFecha.setFocusPainted(false);
        btnBuscarFecha.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnBuscarFecha.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFechaActionPerformed(evt);
            }
        });
        panelFecha.add(btnBuscarFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 110, 200));

        background.add(panelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 340, 200));

        btnPDF.setText("Generar PDF");
        btnPDF.setBackground(new java.awt.Color(255, 255, 255));
        btnPDF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(188, 149, 92), 5, true));
        btnPDF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPDF.setFocusPainted(false);
        btnPDF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        background.add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 100, 70));

        btnFecha.setText("Fecha");
        btnFecha.setBackground(new java.awt.Color(255, 255, 255));
        btnFecha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFecha.setFocusPainted(false);
        btnFecha.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });
        background.add(btnFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 100, 40));

        btnCancelar.setText("Cancelar");
        btnCancelar.setBackground(new java.awt.Color(157, 36, 73));
        btnCancelar.setBorder(null);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        background.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 110, 40));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        background.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, 160, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");
        background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        btnPeriodo.setText("Periodo");
        btnPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        btnPeriodo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnPeriodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPeriodo.setFocusPainted(false);
        btnPeriodo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeriodoActionPerformed(evt);
            }
        });
        background.add(btnPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 100, 40));

        btnSigPagina.setText(">");
        btnSigPagina.setBackground(new java.awt.Color(255, 255, 255));
        btnSigPagina.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnSigPagina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSigPagina.setFocusPainted(false);
        btnSigPagina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSigPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSigPaginaActionPerformed(evt);
            }
        });
        background.add(btnSigPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 50, -1));

        btnAntPagina.setText("<");
        btnAntPagina.setBackground(new java.awt.Color(255, 255, 255));
        btnAntPagina.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnAntPagina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAntPagina.setFocusPainted(false);
        btnAntPagina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAntPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAntPaginaActionPerformed(evt);
            }
        });
        background.add(btnAntPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 50, -1));

        btnTipo.setText("Tipo");
        btnTipo.setBackground(new java.awt.Color(255, 255, 255));
        btnTipo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 36, 73), 2, true));
        btnTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTipo.setFocusPainted(false);
        btnTipo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoActionPerformed(evt);
            }
        });
        background.add(btnTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 100, 40));

        panelOpcionesPeriodo.setBackground(new java.awt.Color(35, 91, 78));
        panelOpcionesPeriodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelOpcionesPeriodo.add(calendario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 190, -1));
        panelOpcionesPeriodo.add(calendario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 190, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.setBackground(new java.awt.Color(35, 91, 78));
        btnBuscar.setBorder(null);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        panelOpcionesPeriodo.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 220, 40));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha de comienzo");
        panelOpcionesPeriodo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha de terminacion");
        panelOpcionesPeriodo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        background.add(panelOpcionesPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 220, 190));

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

        btnLicencia.setText("Licencias");
        btnLicencia.setBackground(new java.awt.Color(35, 91, 78));
        btnLicencia.setBorder(null);
        btnLicencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLicencia.setFocusPainted(false);
        btnLicencia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnLicencia.setForeground(new java.awt.Color(255, 255, 255));
        btnLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLicenciaActionPerformed(evt);
            }
        });
        panelOpcionesTipo.add(btnLicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 110, 130));

        btnPlaca.setText("Placas");
        btnPlaca.setBackground(new java.awt.Color(35, 91, 78));
        btnPlaca.setBorder(null);
        btnPlaca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlaca.setFocusPainted(false);
        btnPlaca.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnPlaca.setForeground(new java.awt.Color(255, 255, 255));
        btnPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlacaActionPerformed(evt);
            }
        });
        panelOpcionesTipo.add(btnPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 130));

        panelFondo.add(panelOpcionesTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 220, 130));

        background.add(panelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 330));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setFocusable(false);
        jScrollPane1.setRequestFocusEnabled(false);
        jScrollPane1.setVerifyInputWhenFocusTarget(false);

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
        tabla.setEnabled(false);
        tabla.setFocusable(false);
        tabla.setRequestFocusEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setVerifyInputWhenFocusTarget(false);
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

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Buscar por Nombre similar:");
        background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

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
    /**
     * Comienza el proceso para generar pdf con los tramites que hay en memoria
     * @param evt 
     */
    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de ejecutar este comando?", "Confirmar", JOptionPane.YES_NO_OPTION);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        if (opcion == JOptionPane.YES_OPTION) {
            List<PdfReporte> reportePDf = new ArrayList<PdfReporte>();
            if (listaTabla.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tabla vacia");
                return;
            }

            for (Tramite tramite : listaTabla) {
                PdfReporte reporte = new PdfReporte();
                Persona persona = tramite.getPersona();
                String nombreCompleto = persona.getNombre() + " "
                        + " " + persona.getPrimerApellido() + " "
                        + " " + persona.getSegundoApellido();

                reporte.setNombrePersona(nombreCompleto);
                if (tramite instanceof Placa) {
                    reporte.setTipoTramite("Expedicion de Placa");
                    reporte.setEstado(((Placa) tramite).getActiva());
                }
                if (tramite instanceof Licencia) {
                    reporte.setTipoTramite("Expedicion de Licencia");
                    reporte.setEstado(((Licencia) tramite).getEstado());
                }

                reporte.setCosto(String.valueOf(tramite.getCosto()));

                reporte.setFecha(formato.format(tramite.getFechaEmision().getTime()));
                reportePDf.add(reporte);
            }
            try {
                Map parametro = new HashMap();
                LocalDateTime fechaHoraActual = LocalDateTime.now();
                DateTimeFormatter formatEscrito = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy, hh:mm a");
                String fechaHoraEscrita = fechaHoraActual.format(formatEscrito);
                parametro.put("fechaReporte", fechaHoraEscrita);
                parametro.put("historial", "Reporte General");

                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(reportePDf);

                // Cargar el archivo JRXML del reporte
                InputStream reportFile = getClass().getResourceAsStream("/reportePDF.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);

                // Llenar el reporte con los datos
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametro, beanColDataSource);

                JasperViewer.viewReport(jasperPrint, false);

                // Visualizar el reporte
//                JasperExportManager.exportReportToPdfFile(jasperPrint, "./ReporteTramites.pdf");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnPDFActionPerformed
    /**
     * Regresa a la pagina principal cerrando la de reportes
     * @param evt 
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (consultante != null) {
            Principal pl = new Principal(true, consultante);
            pl.setVisible(true);
            this.dispose();
        } else {
            Principal pl = new Principal(false, consultante);
            pl.setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_btnCancelarActionPerformed
    /**
     * Avanza a una siguiente pagina
     * @param evt 
     */
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
    /**
     * Se mueve a una pagina anterior
     * @param evt 
     */
    private void btnAntPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAntPaginaActionPerformed
        if (this.inicio == 0) {
            return;
        }
        //esta suma es para saber en que posicion de la pagina esta
        this.inicio -= 10;
        this.fin -= 10;
        llenarTabla(tramites);
    }//GEN-LAST:event_btnAntPaginaActionPerformed
    /**
     * Activa o desactiva el panel de tipo
     * desactivando todos los demas paneles
     * @param evt 
     */
    private void btnTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoActionPerformed
//        buscarPorTramite();
        String[] lista = {"periodo", "fecha"};
        apagarBotones(lista);

        if (panelFondo.isEnabled()) {
            desactivarPanelFondo();
        } else {
            activarPanelFondo();
        }

    }//GEN-LAST:event_btnTipoActionPerformed
    /**
     * Activa o desactiva el panel del periodo
     * desacticando todos los demas paneles
     * @param evt 
     */
    private void btnPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeriodoActionPerformed
//        Calendar fecha1 = new GregorianCalendar(), fecha2 = new GregorianCalendar();
//        buscarPorPeriodo(fecha1, fecha2);
        String[] lista = {"tipo", "fecha"};
        apagarBotones(lista);
        boolean opcion = !panelOpcionesPeriodo.isEnabled();

        mostrarPanelOpcionesPeriodo(opcion);

    }//GEN-LAST:event_btnPeriodoActionPerformed
    /**
     * Activa o desactiva el panel que muestra la opcion para el periodo
     * @param opt boolean activar: true, desactivar: false
     */
    private void mostrarPanelOpcionesPeriodo(boolean opt) {
        if (opt) {
            panelOpcionesPeriodo.setEnabled(true);
            panelOpcionesPeriodo.setVisible(true);
        } else if (opt == false) {
            panelOpcionesPeriodo.setEnabled(false);
            panelOpcionesPeriodo.setVisible(false);
        }
    }
    /**
     * Apaga/Cierra los paneles activos de los botones Fecha, Periodo, Tipo
     * @param cuales String[] con los botones a desactivar
     * [periodo, tipo, fecha]
     */
    private void apagarBotones(String[] cuales) {
        for (String boton : cuales) {
            if (boton.equalsIgnoreCase("periodo")) {
                mostrarPanelOpcionesPeriodo(false);
            } else if (boton.equalsIgnoreCase("fecha")) {
                mostrarPanelFecha(false);
            } else if (boton.equalsIgnoreCase("tipo")) {
                desactivarPanelFondo();
            }
        }
    }
    /**
     * Activa el proceso para mostrar el menu de fecha desactivando los
     * demas menus/paneles
     * @param evt 
     */
    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
//        Calendar fecha = new GregorianCalendar();
//        buscarPorFecha(fecha);
        String[] lista = {"tipo", "periodo"};
        apagarBotones(lista);
        mostrarPanelFecha(!panelFecha.isEnabled());
    }//GEN-LAST:event_btnFechaActionPerformed
    /**
     * Activa o desactiva el panel que muestra la fecha
     * @param opt boolean, desactiva: false, activa: true
     */
    private void mostrarPanelFecha(boolean opt) {
        if (opt == false) {
            panelFecha.setEnabled(false);
            panelFecha.setVisible(false);
        } else if (opt == true) {
            panelFecha.setEnabled(true);
            panelFecha.setVisible(true);
        }
    }
    /**
     * En caso de que el panel de opciones tipo pierda el foco este se cerrara
     * (tampoco sirve)
     * @param evt 
     */
    private void panelFondoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelFondoFocusLost
        desactivarPanelFondo();
    }//GEN-LAST:event_panelFondoFocusLost
    /**
     * En caso de que el panelOpciones pierda el foco este se cerrara
     * (No sirve)
     * @param evt 
     */
    private void panelOpcionesTipoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelOpcionesTipoFocusLost
        desactivarPanelFondo();
    }//GEN-LAST:event_panelOpcionesTipoFocusLost
    /**
     * Empieza el proceso de buscar por tipo, pero con el atributo StringBuffer
     * de placa
     * @param evt 
     */
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
    /**
     * Empieza el proceso para buscar por tipo, pero con el atributo
     * StringBuffer de licencia
     * @param evt 
     */
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
    /**
     * Llena la tabla de personas cada que se escribe para tener una 
     * relacion de personas cercanas al nombre
     * @param evt 
     */
    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        llenarTabla(listaTablaActual());
    }//GEN-LAST:event_txtNombreKeyReleased
    /**
     * Empieza el proceso para buscar por periodo en caso de que la fecha1
     * y la fecha 2 no sean nulas
     * @param evt 
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Calendar fecha1 = new GregorianCalendar();
        Calendar fecha2 = new GregorianCalendar();
        try {
            LocalDate desde = calendario1.getDate();
            LocalDate hasta = calendario2.getDate();
            fecha1.set(desde.getYear(), desde.getMonthValue() - 1, desde.getDayOfMonth());
            fecha2.set(hasta.getYear(), hasta.getMonthValue() - 1, hasta.getDayOfMonth());

            if (desde.isAfter(hasta)) {
                throw new FechaDisparejaException("Las fecha hasta esta antes de la fecha desde");
            }

            buscarPorPeriodo(fecha1, fecha2);

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Desde o Hasta esta vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (FechaDisparejaException fde) {
            JOptionPane.showMessageDialog(this, fde.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        mostrarPanelOpcionesPeriodo(false);

    }//GEN-LAST:event_btnBuscarActionPerformed
    /**
     * Metodo para empezar el proceso de buscar por fecha, llama al metodo
     * buscarPorFecha en caso de que la fecha que se seleccione no sea null
     * @param evt 
     */
    private void btnBuscarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFechaActionPerformed
        Calendar fecha = new GregorianCalendar();
        try {
            LocalDate fseleccionada = calendario.getSelectedDate();
            fecha.set(fseleccionada.getYear(), fseleccionada.getMonthValue() - 1, fseleccionada.getDayOfMonth());
            mostrarPanelFecha(false);
            buscarPorFecha(fecha);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "NO SELECCIONASTE NINGUNA FECHA", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarFechaActionPerformed
    /**
     * Activa el panel que muestra el panel con las opciones para escoger
     * los tipos de tramites
     */
    private void activarPanelFondo() {
        this.panelFondo.setEnabled(true);
        this.panelFondo.setVisible(true);
    }
    /**
     * Desactiva el panel que muestra las opciones para escoger los tipos de 
     * tramites
     */
    private void desactivarPanelFondo() {
        this.panelFondo.setEnabled(false);
        this.panelFondo.setVisible(false);
    }

    /*AQUI EMPIEZAN LOS METODOS PARA BUSCAR POR FECHA*/
    /**
     * Metodo para buscar por fecha
     * @param fecha Calendar fecha a buscar
     */
    private void buscarPorFecha(Calendar fecha) {
        if (consultante != null) {
            buscarPorFechaConsultante(fecha);
        } else {
            buscarPorFechaTodos(fecha);
        }

        llenarTabla(tramites);
    }
    /**
     * Busca todos los tramites que tengan una fecha en espesifico
     * @param c Calendar fecha
     */
    private void buscarPorFechaTodos(Calendar c) {
//        new SeleccionarFecha(this, true, c);
        tramites = daotramite.listaTramiteFechaTodos(c, inicio, limite);
    }
    /**
     * Busca todos los tramites de una persona con una fecha en espesifico
     * @param c Calendar fecha
     */
    private void buscarPorFechaConsultante(Calendar c) {
//        new SeleccionarFecha(this, true, c);
        tramites = daotramite.listaTramitePersonaNacimiento(consultante, c, inicio, limite);
    }

    /*AQUI TERMINAN LOS METODOS PARA BUSCAR POR FECHA*/

 /*AQUI EMPIZAN LOS METODOS PARA LA BUSQUEDA POR PERIODO*/
    /**
     * Metodo para buscar tramites por periodo
     * @param f1 Calendar fecha de comienzo
     * @param f2 Calendar fecha de final
     */
    private void buscarPorPeriodo(Calendar f1, Calendar f2) {
        if (consultante != null) {
            buscarPorPeriodoConsultante(f1, f2);
        } else {
            buscarPorPeriodoTodos(f1, f2);
        }

        llenarTabla(tramites);
    }
    /**
     * Metodo que busca todos los tramites de una persona en un periodo dado
     * @param f1 Calendar fecha de comienzo
     * @param f2 Calendar fecha final
     */
    private void buscarPorPeriodoConsultante(Calendar f1, Calendar f2) {
//        new SeleccionarPeriodo(this, true, f1, f2);
        tramites = daotramite.listaPeriodoPersona(consultante, f1, f2, inicio, limite);
    }
    /**
     * Metodo que busca todo los tramites de un periodo
     * @param f1 Calendar fecha inicio
     * @param f2 Calendar fecha final
     */
    private void buscarPorPeriodoTodos(Calendar f1, Calendar f2) {
//        new SeleccionarPeriodo(this, true, f1, f2);
        tramites = daotramite.listaPeriodoTodos(f1, f2, inicio, limite);
    }

    /*AQUI TERMINAN LOS METODOS PARA LA BUSQUEDA POR PERIODO*/
    /**
     * Metodo para buscar tramites de un cierto tipo
     */
    private void buscarPorTramite() {
        StringBuffer respuesta = new StringBuffer();
        if (consultante != null) {
            buscarPorTipoTramiteConsultante(respuesta);
        } else {
            buscarPorTipoTramiteTodos(respuesta);
        }

        llenarTabla(tramites);
    }
    /**
     * Metodo que busca todos los tramites de un cierto tipo de un consultante
     * @param respuesta StringBuffer con el tipo de tramite buscado
     */
    private void buscarPorTipoTramiteConsultante(StringBuffer respuesta) {
        tramites = daotramite.listaPorTipoPersona(consultante, respuesta, inicio, limite);
    }
    /**
     * Tramote que bisca todos los tramites en general de un cierto tipo
     * @param respuesta StringBuffer con el tipo de tramite buscado
     */
    private void buscarPorTipoTramiteTodos(StringBuffer respuesta) {
        tramites = daotramite.listaPorTipoTodos(respuesta, inicio, limite);
    }
/**
 * Variables declaration - do not modify  
 */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel baner;
    private javax.swing.JButton btnAntPagina;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarFecha;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnLicencia;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnPeriodo;
    private javax.swing.JButton btnPlaca;
    private javax.swing.JButton btnSigPagina;
    private javax.swing.JButton btnTipo;
    private com.github.lgooddatepicker.components.CalendarPanel calendario;
    private com.github.lgooddatepicker.components.DatePicker calendario1;
    private com.github.lgooddatepicker.components.DatePicker calendario2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelFecha;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelOpcionesPeriodo;
    private javax.swing.JPanel panelOpcionesTipo;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
