/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.gui.panels;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import nomina.entities.Empleado;
import nomina.entities.Rol;
import nomina.gui.AddPaymentForm;

/**
 *
 * @author RickyTB
 */
public class PaymentsPanel extends javax.swing.JPanel {

    private final Empleado empleado;
    private List<Rol> roles;

    /**
     * Creates new form PaymentsPanel
     *
     * @param empleado
     */
    public PaymentsPanel(Empleado empleado) {
        this.empleado = empleado;
        initComponents();
        fillTable();
    }

    private void fillTable() {
        roles = empleado.getRolList();
        DefaultTableModel model = (DefaultTableModel) paymentsTable.getModel();
        roles.forEach((Rol rol) -> model.addRow(rol.toTableRow()));
        paymentsTable.getSelectionModel().addListSelectionListener(e -> {
            removeButton.setEnabled(paymentsTable.getSelectedRow() > -1);
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

        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        paymentsTable = new javax.swing.JTable();

        addButton.setText("Agregar");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Eliminar");
        removeButton.setEnabled(false);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        paymentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Año", "Mes", "Horas trabajadas", "Horas extras 100%", "Horas extras 50%", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(paymentsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        AddPaymentForm form = new AddPaymentForm(empleado);
        form.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
       /*
        try {
            CargaFamiliar carga = cargas.get(paymentsTable.getSelectedRow());
            cargas.remove(carga);
            carga.setEmpleadoId(null);
            CargaFamiliarJpaController cargaController = new CargaFamiliarJpaController(Constants.EMF);
            cargaController.destroy(carga.getId());
            DefaultTableModel model = (DefaultTableModel) paymentsTable.getModel();
            model.removeRow(paymentsTable.getSelectedRow());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PaymentsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }//GEN-LAST:event_removeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable paymentsTable;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
/*
    @Override
    public void onFamiliarAdded(CargaFamiliar carga) {
        cargas.add(carga);
        DefaultTableModel model = (DefaultTableModel) paymentsTable.getModel();
        model.addRow(carga.toTableRow());
    }
*/
}
