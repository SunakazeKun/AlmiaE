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

import com.aurum.almia.game.map.TargetData;

public class TargetWidget extends javax.swing.JPanel {
    private TargetData.Entry entry;
    
    public TargetWidget(TargetData.Entry entry) {
        this.entry = entry;
        
        initComponents();
        
        spnPosX.setValue(entry.posX);
        spnPosY.setValue(entry.posY);
        spnUnk4.setValue(entry.unk4);
        spnUnk5.setValue(entry.unk5);
        cmoID.setSelectedIndex(entry.obstacleID);
        spnUnk8.setValue(entry.unk8);
        spnUnk9.setValue(entry.unk9);
        spnUnkA.setValue(entry.unkA);
        spnUnkB.setValue(entry.unkB);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblPosX = new javax.swing.JLabel();
        lblPosY = new javax.swing.JLabel();
        lblUnk4 = new javax.swing.JLabel();
        lblUnk5 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblUnk8 = new javax.swing.JLabel();
        lblUnk9 = new javax.swing.JLabel();
        lblUnkA = new javax.swing.JLabel();
        lblUnkB = new javax.swing.JLabel();
        spnPosX = new javax.swing.JSpinner();
        spnPosY = new javax.swing.JSpinner();
        spnUnk4 = new javax.swing.JSpinner();
        spnUnk5 = new javax.swing.JSpinner();
        cmoID = new javax.swing.JComboBox<>();
        spnUnk8 = new javax.swing.JSpinner();
        spnUnk9 = new javax.swing.JSpinner();
        spnUnkA = new javax.swing.JSpinner();
        spnUnkB = new javax.swing.JSpinner();
        filler = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

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

        lblUnk4.setText("byte (0x4)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnk4, gridBagConstraints);

        lblUnk5.setText("byte (0x5)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnk5, gridBagConstraints);

        lblID.setText("Obstacle");
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

        lblUnkA.setText("byte (0xA)");
        lblUnkA.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnkA, gridBagConstraints);

        lblUnkB.setText("byte (0xB)");
        lblUnkB.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnkB, gridBagConstraints);

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

        spnUnk4.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        spnUnk4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnk4StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnk4, gridBagConstraints);

        spnUnk5.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        spnUnk5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnk5StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnk5, gridBagConstraints);

        cmoID.setModel(new javax.swing.DefaultComboBoxModel(entry.parent.layer.map.game.getTargetList().toArray()));
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

        spnUnkA.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        spnUnkA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnkAStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnkA, gridBagConstraints);

        spnUnkB.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        spnUnkB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnkBStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnkB, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(filler, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void spnPosXStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPosXStateChanged
        entry.posX = (short)spnPosX.getValue();
    }//GEN-LAST:event_spnPosXStateChanged

    private void spnPosYStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPosYStateChanged
        entry.posY = (short)spnPosY.getValue();
    }//GEN-LAST:event_spnPosYStateChanged

    private void spnUnk4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk4StateChanged
        entry.unk4 = (byte)spnUnk4.getValue();
    }//GEN-LAST:event_spnUnk4StateChanged

    private void spnUnk5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk5StateChanged
        entry.unk5 = (byte)spnUnk5.getValue();
    }//GEN-LAST:event_spnUnk5StateChanged

    private void cmoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmoIDActionPerformed
        entry.obstacleID = cmoID.getSelectedIndex();
    }//GEN-LAST:event_cmoIDActionPerformed

    private void spnUnk8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk8StateChanged
        entry.unk8 = (byte)spnUnk8.getValue();
    }//GEN-LAST:event_spnUnk8StateChanged

    private void spnUnk9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk9StateChanged
        entry.unk9 = (byte)spnUnk9.getValue();
    }//GEN-LAST:event_spnUnk9StateChanged

    private void spnUnkAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnkAStateChanged
        entry.unkA = (byte)spnUnkA.getValue();
    }//GEN-LAST:event_spnUnkAStateChanged

    private void spnUnkBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnkBStateChanged
        entry.unkB = (byte)spnUnkB.getValue();
    }//GEN-LAST:event_spnUnkBStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmoID;
    private javax.swing.Box.Filler filler;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblPosX;
    private javax.swing.JLabel lblPosY;
    private javax.swing.JLabel lblUnk4;
    private javax.swing.JLabel lblUnk5;
    private javax.swing.JLabel lblUnk8;
    private javax.swing.JLabel lblUnk9;
    private javax.swing.JLabel lblUnkA;
    private javax.swing.JLabel lblUnkB;
    private javax.swing.JSpinner spnPosX;
    private javax.swing.JSpinner spnPosY;
    private javax.swing.JSpinner spnUnk4;
    private javax.swing.JSpinner spnUnk5;
    private javax.swing.JSpinner spnUnk8;
    private javax.swing.JSpinner spnUnk9;
    private javax.swing.JSpinner spnUnkA;
    private javax.swing.JSpinner spnUnkB;
    // End of variables declaration//GEN-END:variables
}