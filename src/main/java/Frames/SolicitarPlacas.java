package Frames;

import DAO.AutomovilDAO;
import DAO.Estados;
import DAO.IAutomovilDAO;
import DAO.IPlacaDAO;
import DAO.PlacaDAO;
import Entidades.Automovil;
import Entidades.Persona;
import Entidades.Placa;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jctri
 */
public class SolicitarPlacas extends javax.swing.JFrame {

    private boolean carronuevo;
    private Automovil autoEncontrado;
    IAutomovilDAO dao = new AutomovilDAO();
    
    /**
     * Creates new form AutomovilUsado
     */
    private Persona persona;

    public SolicitarPlacas(Persona persona) {
        this.persona = persona;
        initComponents();
        this.panelDatosCarro.setVisible(false);
        this.panelDatosCarro.setEnabled(false);
    }

    private String generarNumeroPlaca() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        StringBuilder sb = new StringBuilder();

        // Generamos las 3 letras aleatorias
        for (int i = 0; i < 3; i++) {
            int index = (int) (letras.length() * Math.random());
            sb.append(letras.charAt(index));
        }

        // Agregamos el guión
        sb.append("-");

        // Generamos los 3 números aleatorios
        for (int i = 0; i < 3; i++) {
            int index = (int) (numeros.length() * Math.random());
            sb.append(numeros.charAt(index));
        }

        return sb.toString();
    }

    /**
     * Esto se tiene que cambiar y hacer un metodo espesifico para generar el
     * numero de las placas
     */
    private void generarPlaca() {

        //Generamos el automovil que vamos a guardar
        //Al ser un generar de un auto que no esta registrado
        //ocupamos guardarlos
        Automovil auto = new Automovil();
        auto.setColor(txtColor.getText());
        auto.setDuenio(persona);
        auto.setId(0);
        auto.setLinea(txtLinea.getText());
        auto.setMarca(txtMarca.getText());
        auto.setModelo(txtModelo.getText());
        auto.setNserie(txtNumeroSerie.getText());
        List<Placa> placas = new ArrayList();
        //Esto lo vamos a borrar, porque tenemos que buscar una mejor forma
        //de generar el codigo de las placas
        String codigo = generarNumeroPlaca();

        
        
        Placa placa = new Placa(codigo, new GregorianCalendar(), 0, new GregorianCalendar(),
                new BigDecimal("1500"), auto, persona);

        placa.setAuto(auto);
        placa.setActiva(Estados.PLACA_ACTIVA);
        placas.add(placa);
        auto.setPlacas(placas);
        //Como en el cascada del automovil pusimos que fuera persist
        //Cuando guardemos el automovil se va a guardar la placa
        IAutomovilDAO daoAutomovil = new AutomovilDAO();
        Automovil conf = daoAutomovil.insertarAutomovil(auto);

        if (conf != null) {

            String msg = String
                    .format("Se genero la placa de auto: %s",
                            conf.obtenerPlacaActiva().getCodigo());

            generarMensaje("Exito", msg,
                    JOptionPane.INFORMATION_MESSAGE);

            //Despues de generar la placa regresamos al menu principal
            Principal p = new Principal(true, persona);
            p.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "fallo");
        }

    }

    private void generarPlacaUsado(Automovil autoEncontrado) {
        
        autoEncontrado = dao.actualizarAutomovil(autoEncontrado);
        
        boolean robado = false;

        //Encontrar si tiene una placa activa
        if (autoEncontrado.tienePlacaActiva()) {
            //Cancelar placa
            if (!cancelarPlaca(autoEncontrado)) {
                return;
            }
            //este de aqui es en caso de robo
            robado = true;
        }

        String codigo = generarNumeroPlaca();

        Placa placa = null;
        //Si el auto es robado, crea una placa con la fecha de recepcion actual
        //Lo que significa que da las placas el mismo dia que las pide
        if (robado) {
            placa = new Placa(codigo, new GregorianCalendar(), 0, new GregorianCalendar(),
                    new BigDecimal("1000"), autoEncontrado, persona);
        } else {
            //En cambio si no es robado, deja la fecha de recepcion como
            //nula y tendra que hacerce un tipo de actualizacion para 
            //poner la fecha de recepcion
            placa = new Placa(codigo, null, 0, new GregorianCalendar(),
                    new BigDecimal("1000"), autoEncontrado, persona);
            
            JOptionPane.showConfirmDialog(this, "Tendra que generar una recepcion a estas placas", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            
        }

        placa.setAuto(autoEncontrado);
        placa.setActiva(Estados.PLACA_ACTIVA);

        

        List<Placa> placas = autoEncontrado.getPlacas();
        placas.add(placa);
        autoEncontrado.setPlacas(placas);

        /**
         * Lo que se tiene que guardar es el automovil porque si guardas solo la
         * placa cuando solicites el automovil no va a guardar la placa quiza a
         * menos que ponga un cascadeType en las placas abra que ver
         */
        //La confirmacion nomas es para ver si regresa algo de la base de datos
        //Si no regresa nada es porque dio error
        Automovil confirmacion = dao.actualizarAutomovil(autoEncontrado);

        if (confirmacion != null) {

            String msg = String
                    .format("Se genero la placa de auto: %s",
                            confirmacion.obtenerPlacaActiva().getCodigo());

            generarMensaje("Exito", msg,
                    JOptionPane.INFORMATION_MESSAGE);

            //Despues de generar la placa regresamos al menu principal
            Principal p = new Principal(true, persona);
            p.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "fallo");
        }
        
    }

    /**
     * Cancela la placa activa que tenga el auto que se pasa como parametro
     *
     * @param auto automovil del cual se quiere cancelar su ultima placa
     * @return regresa un booleano, verdadero en caso de si haberla cancelado
     * falso en caso contrario
     */
    private boolean cancelarPlaca(Automovil auto) {
        Placa activa = auto.obtenerPlacaActiva();
        activa.setActiva(Estados.PLACA_NO_ACTIVA);
        IPlacaDAO dao = new PlacaDAO();
        activa = dao.actualizar(activa);
        if (activa == null) {
            return false;
        }

        return true;
    }

    private void generarMensaje(String titutlo, String mensaje, int tipo) {

        JOptionPane.showMessageDialog(this, mensaje, titutlo, tipo);

    }
    private void limpiarTXT() {
       txtColor.setText("");
        txtLinea.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
    }
    private void generarAutoNuevo(String serie) {
        activarPanelDatosCarro();
        txtNumeroSerie.setText(serie);
    }

    private void mostrarAutoViejo() {
        txtColor.setText(autoEncontrado.getColor());
        txtLinea.setText(autoEncontrado.getLinea());
        txtMarca.setText(autoEncontrado.getMarca());
        txtModelo.setText(autoEncontrado.getModelo());
        txtNumeroSerie.setText(txtNumeroSeriePrincipal.getText());
        activarPanelDatosCarro();
    }

    private void activarBackground() {
        this.background.setVisible(true);
        this.background.setEnabled(true);
        this.panelDatosCarro.setVisible(false);
        this.panelDatosCarro.setEnabled(false);
    }

    private void activarPanelDatosCarro() {
        this.background.setVisible(false);
        this.background.setEnabled(false);
        panelDatosCarro.setVisible(true);
        panelDatosCarro.setEnabled(true);
    }

    private boolean estanVacios() {
        if (txtColor.getText().isBlank() || txtLinea.getText().isBlank()
                || txtMarca.getText().isBlank() || txtModelo.getText().isBlank()) {
            
            JOptionPane.showMessageDialog(this, "NO DEBE DE HABER CAMPOS VACIOS", "ERROR!", JOptionPane.ERROR_MESSAGE);
            
            return true;
        }
        return false;
    }
    
    
    /**
     * De aqui para abajo no escribir nuevos metodos
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumeroSeriePrincipal = new javax.swing.JTextField();
        btnRegresarPrincipal = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        panelDatosCarro = new javax.swing.JPanel();
        datosCarro = new javax.swing.JPanel();
        txtColor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNumeroSerie = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLinea = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        btnRegresarNuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 0, 51));

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Solicitud Placas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(30, 30, 30))
        );

        background.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, -1));

        jLabel2.setText("Numero de serie del vehiculo:");
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));
        background.add(txtNumeroSeriePrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 170, -1));

        btnRegresarPrincipal.setText("Regresar");
        btnRegresarPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarPrincipalActionPerformed(evt);
            }
        });
        background.add(btnRegresarPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        background.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        datosCarro.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Automovil"));
        datosCarro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColorActionPerformed(evt);
            }
        });
        datosCarro.add(txtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 210, -1));

        jLabel5.setText("Color");
        datosCarro.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        datosCarro.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 210, -1));

        jLabel6.setText("Marca");
        datosCarro.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel7.setText("Numero de serie");
        datosCarro.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        txtNumeroSerie.setEditable(false);
        datosCarro.add(txtNumeroSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 210, -1));

        jLabel8.setText("Linea");
        datosCarro.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));
        datosCarro.add(txtLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 210, -1));

        jLabel9.setText("Modelo");
        datosCarro.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));
        datosCarro.add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 210, -1));

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnRegresarNuevo.setText("Regresar");
        btnRegresarNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDatosCarroLayout = new javax.swing.GroupLayout(panelDatosCarro);
        panelDatosCarro.setLayout(panelDatosCarroLayout);
        panelDatosCarroLayout.setHorizontalGroup(
            panelDatosCarroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosCarroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(datosCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnRegresarNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnGenerar)
                .addContainerGap())
        );
        panelDatosCarroLayout.setVerticalGroup(
            panelDatosCarroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosCarroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(datosCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelDatosCarroLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addGroup(panelDatosCarroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresarNuevo)
                    .addComponent(btnGenerar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelDatosCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelDatosCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarPrincipalActionPerformed
        Principal pl = new Principal(true, persona);
        pl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarPrincipalActionPerformed

    private void btnRegresarNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarNuevoActionPerformed
        activarBackground();
        autoEncontrado = null;
        limpiarTXT();
    }//GEN-LAST:event_btnRegresarNuevoActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        if (estanVacios()) {
            return;
        }

        if (autoEncontrado == null) {
            generarPlaca();
        } else {
            generarPlacaUsado(autoEncontrado);
        }


    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (txtNumeroSeriePrincipal.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "NO DEBE DE HABER CAMPOS VACIOS", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Automovil auto = new Automovil();
        auto.setNserie(txtNumeroSeriePrincipal.getText());
        //Busca el automovil en la base de datos
        autoEncontrado = dao.obtenerAutomovil(auto);
        autoEncontrado = dao.actualizarAutomovil(autoEncontrado);
        
        //Si no encontro el carro, o este no tiene placas activas
        if (autoEncontrado == null) {
            generarAutoNuevo(txtNumeroSeriePrincipal.getText());
        } else {
            mostrarAutoViejo();
            //Si encontro un carro y este no tiene placas activas
            if (!autoEncontrado.tienePlacaActiva()) {
                return;
            }

            String msg = "Este automovil ya cuenta con placas activas \n"
                    + " si continua esta generarndo placas nuevas y cancelando"
                    + " las anteriores";

            generarMensaje("ADVERTENCIA", msg,
                    JOptionPane.WARNING_MESSAGE);

        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnRegresarNuevo;
    private javax.swing.JButton btnRegresarPrincipal;
    private javax.swing.JPanel datosCarro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelDatosCarro;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtLinea;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNumeroSerie;
    private javax.swing.JTextField txtNumeroSeriePrincipal;
    // End of variables declaration//GEN-END:variables
}
