/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.gui;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import nomina.controllers.EmpleadoJpaController;
import nomina.entities.Empleado;
import nomina.interfaces.AddEmployeeListener;
import nomina.singletons.Constants;

/**
 *
 * @author RickyTB
 */
public class Main extends javax.swing.JFrame implements AddEmployeeListener {
    private List<Empleado> empleados;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        fillTable();
    }

    private void fillTable() {
        EmpleadoJpaController empController = new EmpleadoJpaController(Constants.EMF);
        empleados = empController.findEmpleadoEntities();
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        empleados.forEach((Empleado emp) -> model.addRow(emp.toTableRow()));
        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            detailButton.setEnabled(employeeTable.getSelectedRow() > -1);
        });
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
        employeeTable = new javax.swing.JTable();
        detailButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        addMenu = new javax.swing.JMenu();
        employeeMenuItem = new javax.swing.JMenuItem();
        positionMenuItem = new javax.swing.JMenuItem();
        departmentMenuItem = new javax.swing.JMenuItem();
        contractMenuItem = new javax.swing.JMenuItem();
        reportsMenu = new javax.swing.JMenu();
        paymentsReportMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nomina");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Empleados");

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido", "Cédula"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(employeeTable);

        detailButton.setText("Detalles");
        detailButton.setEnabled(false);
        detailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailButtonActionPerformed(evt);
            }
        });

        fileMenu.setMnemonic('a');
        fileMenu.setText("Archivo");

        exitMenuItem.setMnemonic('s');
        exitMenuItem.setText("Salir");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        addMenu.setMnemonic('g');
        addMenu.setText("Agregar");

        employeeMenuItem.setMnemonic('e');
        employeeMenuItem.setText("Empleado");
        employeeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(employeeMenuItem);

        positionMenuItem.setMnemonic('c');
        positionMenuItem.setText("Cargo");
        positionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(positionMenuItem);

        departmentMenuItem.setMnemonic('d');
        departmentMenuItem.setText("Departamento");
        departmentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(departmentMenuItem);

        contractMenuItem.setMnemonic('o');
        contractMenuItem.setText("Contrato");
        contractMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contractMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(contractMenuItem);

        menuBar.add(addMenu);

        reportsMenu.setMnemonic('g');
        reportsMenu.setText("Reportes");

        paymentsReportMenuItem.setMnemonic('e');
        paymentsReportMenuItem.setText("Rol de pagos");
        paymentsReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentsReportMenuItemActionPerformed(evt);
            }
        });
        reportsMenu.add(paymentsReportMenuItem);

        menuBar.add(reportsMenu);

        helpMenu.setMnemonic('y');
        helpMenu.setText("Ayuda");

        aboutMenuItem.setMnemonic('c');
        aboutMenuItem.setText("Créditos");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(detailButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(detailButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void employeeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeMenuItemActionPerformed
        AddEmployeeForm form = new AddEmployeeForm(this);
        form.setVisible(true);
    }//GEN-LAST:event_employeeMenuItemActionPerformed

    private void positionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positionMenuItemActionPerformed
        AddPositionForm form = new AddPositionForm();
        form.setVisible(true);
    }//GEN-LAST:event_positionMenuItemActionPerformed

    private void contractMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contractMenuItemActionPerformed
        AddContractForm form = new AddContractForm();
        form.setVisible(true);
    }//GEN-LAST:event_contractMenuItemActionPerformed

    private void departmentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentMenuItemActionPerformed
        AddDepartmentForm form = new AddDepartmentForm();
        form.setVisible(true);
    }//GEN-LAST:event_departmentMenuItemActionPerformed

    private void detailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailButtonActionPerformed
        EmployeeDetailFrame frame = new EmployeeDetailFrame(empleados.get(employeeTable.getSelectedRow()));
        frame.setVisible(true);
    }//GEN-LAST:event_detailButtonActionPerformed

    private void paymentsReportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentsReportMenuItemActionPerformed
        PaymentsReportFrame frame = new PaymentsReportFrame();
        frame.setVisible(true);
    }//GEN-LAST:event_paymentsReportMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu addMenu;
    private javax.swing.JMenuItem contractMenuItem;
    private javax.swing.JMenuItem departmentMenuItem;
    private javax.swing.JButton detailButton;
    private javax.swing.JMenuItem employeeMenuItem;
    private javax.swing.JTable employeeTable;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem paymentsReportMenuItem;
    private javax.swing.JMenuItem positionMenuItem;
    private javax.swing.JMenu reportsMenu;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onEmployeeAdded(Empleado empleado) {
        empleados.add(empleado);
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        model.addRow(empleado.toTableRow());
    }

}
