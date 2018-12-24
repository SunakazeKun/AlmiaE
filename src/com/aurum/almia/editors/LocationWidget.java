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

import com.aurum.almia.game.map.LocationData;

public class LocationWidget extends javax.swing.JPanel {
    private LocationData.Entry entry;
    
    public LocationWidget(LocationData.Entry entry) {
        this.entry = entry;
        
        initComponents();
        
        spnPosX.setValue(entry.posX);
        spnPosY.setValue(entry.posY);
        spnWidth.setValue(entry.width);
        spnHeight.setValue(entry.height);
        spnUnk8.setValue(entry.unk8);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblPosX = new javax.swing.JLabel();
        lblPosY = new javax.swing.JLabel();
        lblWidth = new javax.swing.JLabel();
        lblHeight = new javax.swing.JLabel();
        lblUnk8 = new javax.swing.JLabel();
        spnPosX = new javax.swing.JSpinner();
        spnPosY = new javax.swing.JSpinner();
        spnWidth = new javax.swing.JSpinner();
        spnHeight = new javax.swing.JSpinner();
        spnUnk8 = new javax.swing.JSpinner();
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

        lblWidth.setText("Width");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblWidth, gridBagConstraints);

        lblHeight.setText("Height");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblHeight, gridBagConstraints);

        lblUnk8.setText("short (0x8)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnk8, gridBagConstraints);

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

        spnWidth.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65535, 1));
        spnWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnWidthStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnWidth, gridBagConstraints);

        spnHeight.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65535, 1));
        spnHeight.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnHeightStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnHeight, gridBagConstraints);

        spnUnk8.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        spnUnk8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnk8StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnk8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
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

    private void spnWidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnWidthStateChanged
        entry.width = (int)spnWidth.getValue();
    }//GEN-LAST:event_spnWidthStateChanged

    private void spnHeightStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnHeightStateChanged
        entry.height = (int)spnHeight.getValue();
    }//GEN-LAST:event_spnHeightStateChanged

    private void spnUnk8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk8StateChanged
        entry.unk8 = (short)spnUnk8.getValue();
    }//GEN-LAST:event_spnUnk8StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel lblHeight;
    private javax.swing.JLabel lblPosX;
    private javax.swing.JLabel lblPosY;
    private javax.swing.JLabel lblUnk8;
    private javax.swing.JLabel lblWidth;
    private javax.swing.JSpinner spnHeight;
    private javax.swing.JSpinner spnPosX;
    private javax.swing.JSpinner spnPosY;
    private javax.swing.JSpinner spnUnk8;
    private javax.swing.JSpinner spnWidth;
    // End of variables declaration//GEN-END:variables
}