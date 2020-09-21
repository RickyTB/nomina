/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.gui;

import java.math.BigDecimal;
import java.util.ArrayList;
import nomina.entities.Concepto;
import nomina.entities.Empleado;
import nomina.entities.Rol;
import nomina.interfaces.SimpleDocumentListener;

/**
 *
 * @author RickyTB
 */
public class AddPaymentForm extends javax.swing.JFrame {

    private final Empleado empleado;
    private final Rol rol = new Rol(2020, 1, 40, 0, 0);
    private final ArrayList<Concepto> conceptos = new ArrayList<>();

    /**
     * Creates new form AddPaymentForm
     *
     * @param empleado
     */
    public AddPaymentForm(Empleado empleado) {
        this.empleado = empleado;
        initComponents();
        prepareUI();
    }

    private void prepareUI() {
        employeeLabel.setText(empleado.getNombre() + " " + empleado.getApellido());
        positionLabel.setText(empleado.getCargoId().getNombre() + " - Sueldo: $" + empleado.getSueldo().toPlainString());

        updateTotal();

        hoursField.getDocument().addDocumentListener((SimpleDocumentListener) e -> {
            try {
                rol.setHorasTrabajadas(Integer.parseInt((String) hoursField.getText()));
                updateTotal();
            } catch (NumberFormatException ex) {
            }
        });
        extra50Field.getDocument().addDocumentListener((SimpleDocumentListener) e -> {
            try {
                rol.setHorasTrabajadasCincuenta(Integer.parseInt((String) extra50Field.getText()));
                updateTotal();
            } catch (NumberFormatException ex) {
            }
        });
        extra100Field.getDocument().addDocumentListener((SimpleDocumentListener) e -> {
            try {
                rol.setHorasTrabajadasCien(Integer.parseInt((String) extra100Field.getText()));
                updateTotal();
            } catch (NumberFormatException ex) {
            }
        });
    }

    private BigDecimal calculateTotal() {
        BigDecimal sueldo = empleado.getSueldo().multiply(BigDecimal.valueOf(rol.getHorasTrabajadas())).divide(BigDecimal.valueOf(40.0));
        BigDecimal extra50 = empleado.getSueldo().multiply(BigDecimal.valueOf(1.5)).multiply(BigDecimal.valueOf(rol.getHorasTrabajadasCincuenta())).divide(BigDecimal.valueOf(240.0));
        BigDecimal extra100 = empleado.getSueldo().multiply(BigDecimal.valueOf(2.0)).multiply(BigDecimal.valueOf(rol.getHorasTrabajadasCien())).divide(BigDecimal.valueOf(240.0));
        return sueldo.add(extra50).add(extra100);
    }

    private void updateTotal() {
        totalLabel.setText("$" + calculateTotal().toPlainString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        employeeLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        positionLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        extra100Field = new javax.swing.JTextField();
        monthComboBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        hoursField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        extra50Field = new javax.swing.JTextField();
        totalLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        conceptsTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Rol de Pago");

        jSplitPane1.setDividerLocation(420);

        employeeLabel.setText("Nombre empleado");

        jLabel11.setText("Horas extras 50%");

        positionLabel.setText("Cargo");

        jLabel13.setText("Mes");

        extra100Field.setText("0");

        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        monthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthComboBoxActionPerformed(evt);
            }
        });

        jLabel14.setText("Horas extras 100%");

        jLabel15.setText("Año");

        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
        yearComboBox.setSelectedIndex(5);
        yearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearComboBoxActionPerformed(evt);
            }
        });

        jLabel16.setText("Horas trabajadas");

        hoursField.setText("40");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Nuevo Rol de Pago");

        extra50Field.setText("0");

        totalLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalLabel.setText("$4500.00");

        jLabel1.setText("Valor total:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(extra100Field)
                    .addComponent(extra50Field)
                    .addComponent(hoursField)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(monthComboBox, 0, 208, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(employeeLabel)
                            .addComponent(positionLabel)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(positionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hoursField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(extra50Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(extra100Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalLabel)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel1);

        conceptsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Concepto", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(conceptsTable);

        jLabel9.setText("Conceptos");

        jButton1.setText("Agregar");

        jButton2.setText("Eliminar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel2);

        saveButton.setText("Guardar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveButton))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void monthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthComboBoxActionPerformed
        rol.setMes(Integer.parseInt((String) monthComboBox.getSelectedItem()));
    }//GEN-LAST:event_monthComboBoxActionPerformed

    private void yearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearComboBoxActionPerformed
        rol.setAnio(Integer.parseInt((String) yearComboBox.getSelectedItem()));
    }//GEN-LAST:event_yearComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable conceptsTable;
    private javax.swing.JLabel employeeLabel;
    private javax.swing.JTextField extra100Field;
    private javax.swing.JTextField extra50Field;
    private javax.swing.JTextField hoursField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JComboBox<String> monthComboBox;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JComboBox<String> yearComboBox;
    // End of variables declaration//GEN-END:variables
}
