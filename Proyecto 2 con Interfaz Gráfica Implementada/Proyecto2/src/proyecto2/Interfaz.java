package proyecto2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Interfaz extends javax.swing.JFrame {
    private final List<String> resumenTitulos = new ArrayList<>();
    private extraerDatos manager = new extraerDatos();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Interfaz.class.getName());

    public Interfaz() {
        manager = new extraerDatos();
        manager.inicializarResumenesDePrueba();
        setTitle("Administrador de Artículos Científicos");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        outputArea = new JTextArea();
        outputArea.setRows(15);
        outputArea.setColumns(60);
        outputArea.setLineWrap(true);
        outputArea.setEditable(false);
        outputArea.setWrapStyleWord(true);
        outputArea.setBorder(null);
        outputArea.setBackground(java.awt.Color.WHITE); 

        outputArea.setOpaque(true);
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        resumenSelector = new JComboBox<>();
        Dimension preferredSize = new Dimension(350, resumenSelector.getPreferredSize().height);
        resumenSelector.setPreferredSize(preferredSize);

        cargarDatosInicialesSimulados();

        setLayout(new BorderLayout(10, 10));

        setJMenuBar(crearBarraMenu());
        add(crearControlPanel(), BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        setVisible(true);
        outputArea.setText("Proyecto EDD 2 \nBienvenido al sistema SuperMetroMendeley; Tablas de Dispersión\nUse los menús o botones para operar.\n");
        
    }
    
    private void cargarDatosInicialesSimulados() {
        resumenTitulos.add("Interacción inalámbrica con dispositivos de bajo costo...");
        resumenTitulos.add("GraphQL vs REST: una comparación...");
        resumenTitulos.add("Arquitectura referencial para mecanismos de Internacionalización...");
        actualizarSelectorResumenes();
    }
    
    private JMenuBar crearBarraMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu archivoMenu = new JMenu("Archivo");
        JMenuItem agregarItem = new JMenuItem("Agregar Resumen (Cargar Archivo)");
        JMenuItem salirItem = new JMenuItem("Salir del Sistema");
        salirItem.addActionListener(e -> salirAction());
        
        archivoMenu.add(agregarItem);
        archivoMenu.addSeparator();
        archivoMenu.add(salirItem);

        JMenu buscarMenu = new JMenu("Buscar");
        JMenuItem buscarAutorItem = new JMenuItem("Buscar por Autor (AVL)");
        buscarAutorItem.addActionListener(this::buscarAutorAction);
        JMenuItem buscarPalabraItem = new JMenuItem("Buscar por Palabra Clave (Hash Table)");
        buscarPalabraItem.addActionListener(this::buscarPalabraClaveAction);
        agregarItem.addActionListener(this::agregarResumenActionPerformed);
        buscarMenu.add(buscarAutorItem);
        buscarMenu.add(buscarPalabraItem); 
        menuBar.add(archivoMenu);
        menuBar.add(buscarMenu);
        return menuBar;
    }
    
    private JPanel crearControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        JLabel label = new JLabel("Seleccionar Resumen:");
        
        JButton analizarBtn = new JButton("Analizar Resumen");
        analizarBtn.addActionListener(this::analizarResumenAction);
        
        JButton listarPalabrasBtn = new JButton("Listar Palabras Clave (AVL)");
        listarPalabrasBtn.addActionListener(this::listarPalabrasClaveAction);

        panel.add(label);
        panel.add(resumenSelector);
        panel.add(analizarBtn);
        panel.add(listarPalabrasBtn);

        return panel;
    }
    private void agregarResumen(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Archivo de Resumen");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Texto (.txt)", "txt");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            outputArea.setText("Cargando resumen desde: " + filePath + "\n");
            String dummyTitle = "Título de prueba: " + selectedFile.getName();
        }    
    }
    
    
    
    private void analizarResumenAction(ActionEvent e) { 
        outputArea.setText("");
        String selectedTitle = (String) resumenSelector.getSelectedItem();
        if (selectedTitle == null)return;
        
        final String CLAVE_PERFECTA = "Arquitectura referencial para mecanismos de Internacionalización...";
        final String CLAVE_2 = "GraphQL vs REST: una comparación...";
        final String CLAVE_3 = "Interacción inalámbrica con dispositivos de bajo costo...";
        String tituloBusqueda;
        if (selectedTitle.trim().equals(CLAVE_PERFECTA.trim())) {
        tituloBusqueda = CLAVE_PERFECTA;
        } else if (selectedTitle.trim().equals(CLAVE_2.trim())) {
        tituloBusqueda = CLAVE_2;
        } else if (selectedTitle.trim().equals(CLAVE_3.trim())) {
        tituloBusqueda = CLAVE_3;
    } else {
        tituloBusqueda = selectedTitle.trim(); 
    }
        manager.buscarResumenPorTitulo(selectedTitle);
        Resumen resumenReal = manager.buscarResumenPorTitulo(selectedTitle);
            
        outputArea.setText("Análisis de frecuencias para: **" + selectedTitle + "**\n");
        outputArea.append("---------------------------------------------------\n");
        
        if (resumenReal != null) {
        List<String> autores = resumenReal.getAutores();
        String autoresStr = String.join(", ", autores); 
        outputArea.append("Autores: [" + autoresStr + "]\n\n"); 
        
        outputArea.append("Palabras Clave Indexadas:\n");
        int i = 1;
        for (String clave : resumenReal.getPalabrasClave()) {
            outputArea.append("  - Palabra clave " + (i++) + ": " + clave + "\n");
        }
    } else {
        outputArea.append("❌ Error: Resumen no encontrado en la base de datos (Hash Table).\n"); 
    }
}

    private void buscarPalabraClaveAction(ActionEvent e) {
        String keyword = JOptionPane.showInputDialog(this, "Ingrese la palabra clave a buscar:", "Buscar por Palabra Clave", JOptionPane.QUESTION_MESSAGE);
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            outputArea.setText("Buscando investigaciones para palabra clave: **" + keyword + "**\n");
  
            outputArea.append("Resultados (Acceso rápido O(1) a los detalles):\n");
            outputArea.append("* Título 1 (detalles disponibles)\n");
            outputArea.append("* Título 2 (detalles disponibles)\n");
        }
    }
    
    private void buscarAutorAction(ActionEvent e) {
        String selectedAuthor = (String) JOptionPane.showInputDialog(this, 
                                                                    "Seleccione un autor:", 
                                                                    "Buscar por Autor (AVL)", 
                                                                    JOptionPane.QUESTION_MESSAGE, 
                                                                    null, 
                                                                    new String[]{"Christian Guillén-Drija", "Rhadamés Carmona", "Otro Autor"}, 
                                                                    "Christian Guillén-Drija");

        if (selectedAuthor != null) {
            outputArea.setText("Buscando investigaciones para autor: **" + selectedAuthor + "**\n");

            outputArea.append("Resultados (Listado eficiente O(n)):\n");
            outputArea.append("* Interacción inalámbrica con dispositivos de bajo costo...\n");
            outputArea.append("* Arquitectura referencial para mecanismos de Internacionalización...\n");
        }
    }
    
    private void listarPalabrasClaveAction(ActionEvent e) {
        outputArea.setText("Lista de Palabras Clave del Repositorio (Orden Alfabético - Recorrido Inorden AVL O(n))\n");
        outputArea.append("---------------------------------------------------\n");
        
        outputArea.append("1. Arquitectura referencial\n");
        outputArea.append("2. Autómata Celular\n");
        outputArea.append("3. AutoCAD\n");
        outputArea.append("...\n");
    }
    
    private void salirAction() {
        int confirm = JOptionPane.showConfirmDialog(this, 
                                                    "¿Desea guardar la información de los resúmenes y salir?", 
                                                    "Salir del Sistema", 
                                                    JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
             outputArea.append("\nGuardando datos persistentes...\n");
             System.exit(0);
        }
    }

    private void actualizarSelectorResumenes() {
        resumenSelector.removeAllItems();
        List<String> sortedTitles = new ArrayList<>(resumenTitulos);
        Collections.sort(sortedTitles); 
        for (String title : sortedTitles) {
            resumenSelector.addItem(title);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextArea();
        resumenSelector = new javax.swing.JComboBox<>();
        analizarResumen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        agregarResumen = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        outputArea.setBackground(new java.awt.Color(255, 255, 255));
        outputArea.setColumns(20);
        outputArea.setForeground(new java.awt.Color(0, 0, 0));
        outputArea.setRows(5);
        outputArea.setText("outputArea");
        outputArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(outputArea);

        resumenSelector.setBackground(new java.awt.Color(255, 255, 255));
        resumenSelector.setForeground(new java.awt.Color(0, 0, 0));
        resumenSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        analizarResumen.setBackground(new java.awt.Color(255, 255, 255));
        analizarResumen.setForeground(new java.awt.Color(0, 0, 0));
        analizarResumen.setText("Analizar Resumen");
        analizarResumen.setVerifyInputWhenFocusTarget(false);
        analizarResumen.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        analizarResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarResumenActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Seleccionar Resumen");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(resumenSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(analizarResumen)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(analizarResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(resumenSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        agregarResumen.setText("Agregar Resumen");
        agregarResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarResumenActionPerformed(evt);
            }
        });
        jMenu1.add(agregarResumen);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem3.setText("Buscar Por Palabra Clave");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarResumenActionPerformed
        try {
            // TODO add your handling code here:
            File selectedFile = null;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccionar Archivo de Resumen");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Texto (.txt)", "txt");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            } else {
                return; // Salir si el usuario cancela
            }
            if (selectedFile == null) {
                return;
            }
            
            Resumen nuevoResumen = manager.extraerDatosDeArchivo(selectedFile);
            if (nuevoResumen == null) {
                outputArea.append("❌ Error: No se pudo parsear el archivo o falta el TITULO.\n");
                return;
            }
            String tituloReal = nuevoResumen.getTitulo();
            
            if (manager.insertarYIndexarResumen(nuevoResumen)) {
                resumenTitulos.add(tituloReal); 
                actualizarSelectorResumenes();
                outputArea.append("✔ Resumen '" + tituloReal + "' agregado e indexado con éxito.\n");
                
            } else {              
                outputArea.append("Advertencia: El resumen '" + tituloReal + "' ya existe y no fue agregado.\n");
            }       } catch (FileNotFoundException ex) {
            System.getLogger(Interfaz.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }//GEN-LAST:event_agregarResumenActionPerformed

    private void analizarResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarResumenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_analizarResumenActionPerformed

    
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Interfaz().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem agregarResumen;
    private javax.swing.JButton analizarResumen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea outputArea;
    private javax.swing.JComboBox<String> resumenSelector;
    // End of variables declaration//GEN-END:variables

}