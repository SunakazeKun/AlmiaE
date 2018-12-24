/*
 * Copyright (C) 2018 Aurum
 *
 * AlmiaE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AlmiaE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aurum.almia.editors;

import com.aurum.almia.game.map.MonData;

public class MonWidget extends javax.swing.JPanel {
    private MonData.Entry entry;
    
    public MonWidget(MonData.Entry entry) {
        this.entry = entry;
        
        initComponents();
        
        spnPosX.setValue(entry.posX);
        spnPosY.setValue(entry.posY);
        spnBehavior.setValue(entry.behavior);
        cmoAppearance.setSelectedIndex(entry.appearance);
        cmoID.setSelectedIndex(entry.monID);
        spnUnk8.setValue(entry.unk8);
        spnUnk9.setValue(entry.unk9);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblPosX = new javax.swing.JLabel();
        lblPosY = new javax.swing.JLabel();
        lblBehavior = new javax.swing.JLabel();
        lblAppearance = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblUnk8 = new javax.swing.JLabel();
        lblUnk9 = new javax.swing.JLabel();
        spnPosX = new javax.swing.JSpinner();
        spnPosY = new javax.swing.JSpinner();
        spnBehavior = new javax.swing.JSpinner();
        cmoAppearance = new javax.swing.JComboBox<>();
        cmoID = new javax.swing.JComboBox<>();
        spnUnk8 = new javax.swing.JSpinner();
        spnUnk9 = new javax.swing.JSpinner();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setLayout(new java.awt.GridBagLayout());

        lblPosX.setText("X Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblPosX, gridBagConstraints);

        lblPosY.setText("Y Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblPosY, gridBagConstraints);

        lblBehavior.setText("Behavior");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblBehavior, gridBagConstraints);

        lblAppearance.setText("Appearance");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblAppearance, gridBagConstraints);

        lblID.setText("Pokémon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblID, gridBagConstraints);

        lblUnk8.setText("byte (0x8)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnk8, gridBagConstraints);

        lblUnk9.setText("byte (0x9)");
        lblUnk9.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnk9, gridBagConstraints);

        spnPosX.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        spnPosX.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPosXStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnPosX, gridBagConstraints);

        spnPosY.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        spnPosY.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPosYStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnPosY, gridBagConstraints);

        spnBehavior.setModel(new javax.swing.SpinnerNumberModel(Short.valueOf((short)0), Short.valueOf((short)0), Short.valueOf((short)255), Short.valueOf((short)1)));
        spnBehavior.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnBehaviorStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnBehavior, gridBagConstraints);

        cmoAppearance.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00: Inactive", "01: Active", "02: Tree" }));
        cmoAppearance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmoAppearanceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(cmoAppearance, gridBagConstraints);

        cmoID.setModel(new javax.swing.DefaultComboBoxModel(entry.parent.layer.map.game.getPokemonList().toArray()));
        cmoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmoIDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(cmoID, gridBagConstraints);

        spnUnk8.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        spnUnk8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnk8StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnk8, gridBagConstraints);

        spnUnk9.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        spnUnk9.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnk9StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnk9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void spnPosXStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPosXStateChanged
        entry.posX = (short)spnPosX.getValue();
    }//GEN-LAST:event_spnPosXStateChanged

    private void spnPosYStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPosYStateChanged
        entry.posY = (short)spnPosY.getValue();
    }//GEN-LAST:event_spnPosYStateChanged

    private void spnBehaviorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnBehaviorStateChanged
        entry.behavior = (short)spnBehavior.getValue();
    }//GEN-LAST:event_spnBehaviorStateChanged

    private void cmoAppearanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmoAppearanceActionPerformed
        entry.appearance = (short)cmoAppearance.getSelectedIndex();
    }//GEN-LAST:event_cmoAppearanceActionPerformed

    private void cmoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmoIDActionPerformed
        entry.monID = cmoID.getSelectedIndex();
    }//GEN-LAST:event_cmoIDActionPerformed

    private void spnUnk8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk8StateChanged
        entry.unk8 = (byte)spnUnk8.getValue();
    }//GEN-LAST:event_spnUnk8StateChanged

    private void spnUnk9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk9StateChanged
        entry.unk9 = (byte)spnUnk9.getValue();
    }//GEN-LAST:event_spnUnk9StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmoAppearance;
    private javax.swing.JComboBox<String> cmoID;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel lblAppearance;
    private javax.swing.JLabel lblBehavior;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblPosX;
    private javax.swing.JLabel lblPosY;
    private javax.swing.JLabel lblUnk8;
    private javax.swing.JLabel lblUnk9;
    private javax.swing.JSpinner spnBehavior;
    private javax.swing.JSpinner spnPosX;
    private javax.swing.JSpinner spnPosY;
    private javax.swing.JSpinner spnUnk8;
    private javax.swing.JSpinner spnUnk9;
    // End of variables declaration//GEN-END:variables
}