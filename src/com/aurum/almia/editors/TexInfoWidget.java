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

import com.aurum.almia.game.map.TextureInfo;

public class TexInfoWidget extends javax.swing.JPanel {
    private TextureInfo.Entry entry;
    
    public TexInfoWidget(TextureInfo.Entry entry) {
        this.entry = entry;
        
        initComponents();
        
        spnUnk0.setValue(entry.unk0);
        spnTextureID.setValue(entry.textureID);
        spnUnk4.setValue(entry.unk4);
        spnUnk6.setValue(entry.unk6);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblUnk0 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblUnk4 = new javax.swing.JLabel();
        lblUnk6 = new javax.swing.JLabel();
        spnUnk0 = new javax.swing.JSpinner();
        spnTextureID = new javax.swing.JSpinner();
        spnUnk4 = new javax.swing.JSpinner();
        spnUnk6 = new javax.swing.JSpinner();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setLayout(new java.awt.GridBagLayout());

        lblUnk0.setText("unk0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnk0, gridBagConstraints);

        lblID.setText("Texture ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblID, gridBagConstraints);

        lblUnk4.setText("unk4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnk4, gridBagConstraints);

        lblUnk6.setText("unk6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnk6, gridBagConstraints);

        spnUnk0.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        spnUnk0.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnk0StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnk0, gridBagConstraints);

        spnTextureID.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65535, 1));
        spnTextureID.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnTextureIDStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnTextureID, gridBagConstraints);

        spnUnk4.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
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

        spnUnk6.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        spnUnk6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnk6StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnk6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void spnUnk0StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk0StateChanged
        entry.unk0 = (short)spnUnk0.getValue();
    }//GEN-LAST:event_spnUnk0StateChanged

    private void spnTextureIDStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnTextureIDStateChanged
        entry.textureID = (int)spnTextureID.getValue();
    }//GEN-LAST:event_spnTextureIDStateChanged

    private void spnUnk4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk4StateChanged
        entry.unk4 = (short)spnUnk4.getValue();
    }//GEN-LAST:event_spnUnk4StateChanged

    private void spnUnk6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk6StateChanged
        entry.unk6 = (short)spnUnk6.getValue();
    }//GEN-LAST:event_spnUnk6StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblUnk0;
    private javax.swing.JLabel lblUnk4;
    private javax.swing.JLabel lblUnk6;
    private javax.swing.JSpinner spnTextureID;
    private javax.swing.JSpinner spnUnk0;
    private javax.swing.JSpinner spnUnk4;
    private javax.swing.JSpinner spnUnk6;
    // End of variables declaration//GEN-END:variables
}