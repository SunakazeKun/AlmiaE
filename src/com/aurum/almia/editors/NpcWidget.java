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

import com.aurum.almia.game.map.NpcData;

public class NpcWidget extends javax.swing.JPanel {
    private NpcData.Entry entry;
    
    public NpcWidget(NpcData.Entry entry) {
        this.entry = entry;
        
        initComponents();
        
        spnPosX.setValue(entry.posX);
        spnPosY.setValue(entry.posY);
        cmoDirection.setSelectedIndex(entry.direction);
        chkIsVisible.setSelected(entry.isVisible);
        cmoNpc.setSelectedIndex(entry.npcID);
        spnUnk8.setValue(entry.unk8);
        spnUnk9.setValue(entry.unk9);
        chkUnkA.setSelected(entry.unkA);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblPosX = new javax.swing.JLabel();
        lblPosY = new javax.swing.JLabel();
        lblDirection = new javax.swing.JLabel();
        lblNpc = new javax.swing.JLabel();
        lblUnk8 = new javax.swing.JLabel();
        lblUnk9 = new javax.swing.JLabel();
        spnPosX = new javax.swing.JSpinner();
        spnPosY = new javax.swing.JSpinner();
        cmoDirection = new javax.swing.JComboBox<>();
        cmoNpc = new javax.swing.JComboBox<>();
        chkIsVisible = new javax.swing.JCheckBox();
        spnUnk9 = new javax.swing.JSpinner();
        spnUnk8 = new javax.swing.JSpinner();
        chkUnkA = new javax.swing.JCheckBox();
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

        lblDirection.setText("Direction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblDirection, gridBagConstraints);

        lblNpc.setText("Sprite");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblNpc, gridBagConstraints);

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

        cmoDirection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00: Down", "01: Left", "02: Up", "03: Right", "04: Down(?)", "05: Left(?)", "06: Up(?)", "07: Right(?)", "08: Down(?)", "09: Left(?)", "0A: Up(?)", "0B: Right(?)" }));
        cmoDirection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmoDirectionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(cmoDirection, gridBagConstraints);

        cmoNpc.setModel(new javax.swing.DefaultComboBoxModel(entry.parent.layer.map.game.getNpcList().toArray()));
        cmoNpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmoNpcActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(cmoNpc, gridBagConstraints);

        chkIsVisible.setText("Is visible?");
        chkIsVisible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIsVisibleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(chkIsVisible, gridBagConstraints);

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

        chkUnkA.setText("bool (0xA)");
        chkUnkA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkUnkAStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(chkUnkA, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
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

    private void cmoDirectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmoDirectionActionPerformed
        entry.direction = (byte)cmoDirection.getSelectedIndex();
    }//GEN-LAST:event_cmoDirectionActionPerformed

    private void chkIsVisibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIsVisibleActionPerformed
        entry.isVisible = chkIsVisible.isSelected();
    }//GEN-LAST:event_chkIsVisibleActionPerformed

    private void spnUnk8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk8StateChanged
        entry.unk8 = (byte)spnUnk8.getValue();
    }//GEN-LAST:event_spnUnk8StateChanged

    private void spnUnk9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk9StateChanged
        entry.unk9 = (byte)spnUnk9.getValue();
    }//GEN-LAST:event_spnUnk9StateChanged

    private void chkUnkAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkUnkAStateChanged
        entry.unkA = chkUnkA.isSelected();
    }//GEN-LAST:event_chkUnkAStateChanged

    private void cmoNpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmoNpcActionPerformed
        entry.npcID = cmoNpc.getSelectedIndex();
    }//GEN-LAST:event_cmoNpcActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkIsVisible;
    private javax.swing.JCheckBox chkUnkA;
    private javax.swing.JComboBox<String> cmoDirection;
    private javax.swing.JComboBox<String> cmoNpc;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel lblDirection;
    private javax.swing.JLabel lblNpc;
    private javax.swing.JLabel lblPosX;
    private javax.swing.JLabel lblPosY;
    private javax.swing.JLabel lblUnk8;
    private javax.swing.JLabel lblUnk9;
    private javax.swing.JSpinner spnPosX;
    private javax.swing.JSpinner spnPosY;
    private javax.swing.JSpinner spnUnk8;
    private javax.swing.JSpinner spnUnk9;
    // End of variables declaration//GEN-END:variables
}