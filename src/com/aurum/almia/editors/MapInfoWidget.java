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

import com.aurum.almia.game.map.MapInfo;

public class MapInfoWidget extends javax.swing.JPanel {
    private MapInfo entry;
    
    public MapInfoWidget(MapInfo entry) {
        this.entry = entry;
        
        initComponents();
        
        spnWidth.setValue(entry.width);
        spnHeight.setValue(entry.height);
        spnUnk8.setValue(entry.unk8);
        spnUnkC.setValue(entry.unkC);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblWidth = new javax.swing.JLabel();
        lblHeight = new javax.swing.JLabel();
        lblUnk8 = new javax.swing.JLabel();
        lblUnkC = new javax.swing.JLabel();
        spnWidth = new javax.swing.JSpinner();
        spnHeight = new javax.swing.JSpinner();
        spnUnk8 = new javax.swing.JSpinner();
        spnUnkC = new javax.swing.JSpinner();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setLayout(new java.awt.GridBagLayout());

        lblWidth.setText("Width");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblWidth, gridBagConstraints);

        lblHeight.setText("Height");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblHeight, gridBagConstraints);

        lblUnk8.setText("unk8");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnk8, gridBagConstraints);

        lblUnkC.setText("unkC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(lblUnkC, gridBagConstraints);

        spnWidth.setModel(new javax.swing.SpinnerNumberModel());
        spnWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnWidthStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnWidth, gridBagConstraints);

        spnHeight.setModel(new javax.swing.SpinnerNumberModel());
        spnHeight.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnHeightStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnHeight, gridBagConstraints);

        spnUnk8.setModel(new javax.swing.SpinnerNumberModel());
        spnUnk8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnk8StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnk8, gridBagConstraints);

        spnUnkC.setModel(new javax.swing.SpinnerNumberModel());
        spnUnkC.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnUnkCStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(spnUnkC, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void spnWidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnWidthStateChanged
        entry.width = (int)spnWidth.getValue();
    }//GEN-LAST:event_spnWidthStateChanged

    private void spnHeightStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnHeightStateChanged
        entry.height = (int)spnHeight.getValue();
    }//GEN-LAST:event_spnHeightStateChanged

    private void spnUnk8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnk8StateChanged
        entry.unk8 = (int)spnUnk8.getValue();
    }//GEN-LAST:event_spnUnk8StateChanged

    private void spnUnkCStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnUnkCStateChanged
        entry.unkC = (int)spnUnkC.getValue();
    }//GEN-LAST:event_spnUnkCStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel lblHeight;
    private javax.swing.JLabel lblUnk8;
    private javax.swing.JLabel lblUnkC;
    private javax.swing.JLabel lblWidth;
    private javax.swing.JSpinner spnHeight;
    private javax.swing.JSpinner spnUnk8;
    private javax.swing.JSpinner spnUnkC;
    private javax.swing.JSpinner spnWidth;
    // End of variables declaration//GEN-END:variables
}