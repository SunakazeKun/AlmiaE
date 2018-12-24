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

import com.aurum.almia.Lists;
import com.aurum.almia.Main;
import com.aurum.almia.Resources;
import com.aurum.almia.game.param.PokeID;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PokeIDEditor extends JFrame {
    private PokeID data;
    private PokeID.Entry selected;
    private DefaultListModel model;
    
    public PokeIDEditor(PokeID mons) {
        initComponents();
        
        this.data = mons;
        this.selected = null;
        this.model = new DefaultListModel();
        
        initGUI();
        
        for (PokeID.Entry mon : mons.entries)
            model.addElement(mon.toString());
    }
    
    protected void initGUI() {
        enableComponents(false);
        list.setModel(model);
        
        Object[] assist = Lists.assist_mes.toArray();
        
        cmoType.setModel(new DefaultComboBoxModel(assist));
        
        cmoAssist.setModel(new DefaultComboBoxModel(assist));
        cmoAssist.addActionListener((java.awt.event.ActionEvent evt) -> {
            lblAssist.setIcon(new ImageIcon(Resources.loadImage(String.format("assist/%d.png", cmoAssist.getSelectedIndex()))));
        });
        cmoAssist.setSelectedIndex(0);
        
        cmoField.setModel(new DefaultComboBoxModel(Lists.fieldwaza_name.toArray()));
        cmoField.addActionListener((java.awt.event.ActionEvent evt) -> {
            lblField.setIcon(new ImageIcon(Resources.loadImage(String.format("field/%d.png", cmoField.getSelectedIndex()))));
        });
        cmoField.setSelectedIndex(0);
        
        cmoPokemon.setModel(new DefaultComboBoxModel(Lists.pokemon_name.toArray()));
    }
    
    public void enableComponents(boolean b) {
        cmoPokemon.setEnabled(b);
        spnForm.setEnabled(b);
        cmoType.setEnabled(b);
        cmoAssist.setEnabled(b);
        cmoField.setEnabled(b);
        spnFieldLevel.setEnabled(b);
        spnShadowWidth.setEnabled(b);
        spnShadowHeight.setEnabled(b);
        spnUnk8.setEnabled(b);
        spnUnkA.setEnabled(b);
        spnUnkE.setEnabled(b);
        spnUnkF.setEnabled(b);
        spnUnk14.setEnabled(b);
        spnUnk16.setEnabled(b);
        btnSave.setEnabled(b);
        btnRemove.setEnabled(b);
    }
    
    public void saveAll() {
        try {
            data.save();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        toolbar = new javax.swing.JToolBar();
        saveAllButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();
        scr = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        pneSettings = new javax.swing.JPanel();
        lblPokemon = new javax.swing.JLabel();
        lblForm = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        lblAssist = new javax.swing.JLabel();
        lblField = new javax.swing.JLabel();
        lblFieldLevel = new javax.swing.JLabel();
        lblShadowWidth = new javax.swing.JLabel();
        lblShadowHeight = new javax.swing.JLabel();
        lblUnk8 = new javax.swing.JLabel();
        lblUnkA = new javax.swing.JLabel();
        lblUnkE = new javax.swing.JLabel();
        lblUnkF = new javax.swing.JLabel();
        lblUnk14 = new javax.swing.JLabel();
        lblUnk16 = new javax.swing.JLabel();
        cmoPokemon = new javax.swing.JComboBox<>();
        spnForm = new javax.swing.JSpinner();
        cmoType = new javax.swing.JComboBox<>();
        cmoAssist = new javax.swing.JComboBox<>();
        cmoField = new javax.swing.JComboBox<>();
        spnFieldLevel = new javax.swing.JSpinner();
        spnShadowWidth = new javax.swing.JSpinner();
        spnShadowHeight = new javax.swing.JSpinner();
        spnUnk8 = new javax.swing.JSpinner();
        spnUnkA = new javax.swing.JSpinner();
        spnUnkE = new javax.swing.JSpinner();
        spnUnkF = new javax.swing.JSpinner();
        spnUnk14 = new javax.swing.JSpinner();
        spnUnk16 = new javax.swing.JSpinner();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        btnSave = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pokémon Editor");
        setIconImage(Main.ICON);
        setResizable(false);

        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        saveAllButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/tl_save.png"))); // NOI18N
        saveAllButton.setText("Save all data");
        saveAllButton.setFocusable(false);
        saveAllButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        saveAllButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAllButtonActionPerformed(evt);
            }
        });
        toolbar.add(saveAllButton);

        warningLabel.setForeground(new java.awt.Color(255, 0, 0));
        warningLabel.setText(" ");
        warningLabel.setToolTipText("");

        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listValueChanged(evt);
            }
        });
        scr.setViewportView(list);

        pneSettings.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pneSettings.setLayout(new java.awt.GridBagLayout());

        lblPokemon.setText("Pokémon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblPokemon, gridBagConstraints);

        lblForm.setText("Form");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblForm, gridBagConstraints);

        lblType.setText("Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblType, gridBagConstraints);

        lblAssist.setText("Assist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblAssist, gridBagConstraints);

        lblField.setText("Field");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblField, gridBagConstraints);

        lblFieldLevel.setText("Field level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblFieldLevel, gridBagConstraints);

        lblShadowWidth.setText("Shadow width");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblShadowWidth, gridBagConstraints);

        lblShadowHeight.setText("Shadow height");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblShadowHeight, gridBagConstraints);

        lblUnk8.setText("ushort (0x8)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblUnk8, gridBagConstraints);

        lblUnkA.setText("ushort (0xA)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblUnkA, gridBagConstraints);

        lblUnkE.setText("byte (0xE)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblUnkE, gridBagConstraints);

        lblUnkF.setText("byte (0xF)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblUnkF, gridBagConstraints);

        lblUnk14.setText("short (0x14)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblUnk14, gridBagConstraints);

        lblUnk16.setText("short (0x16)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(lblUnk16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(cmoPokemon, gridBagConstraints);

        spnForm.setModel(new javax.swing.SpinnerNumberModel(Short.valueOf((short)0), Short.valueOf((short)0), Short.valueOf((short)255), Short.valueOf((short)1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(spnForm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(cmoType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(cmoAssist, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(cmoField, gridBagConstraints);

        spnFieldLevel.setModel(new javax.swing.SpinnerNumberModel(Short.valueOf((short)0), Short.valueOf((short)0), Short.valueOf((short)255), Short.valueOf((short)1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(spnFieldLevel, gridBagConstraints);

        spnShadowWidth.setModel(new javax.swing.SpinnerNumberModel(Short.valueOf((short)0), Short.valueOf((short)0), Short.valueOf((short)255), Short.valueOf((short)1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(spnShadowWidth, gridBagConstraints);

        spnShadowHeight.setModel(new javax.swing.SpinnerNumberModel(Short.valueOf((short)0), Short.valueOf((short)0), Short.valueOf((short)255), Short.valueOf((short)1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(spnShadowHeight, gridBagConstraints);

        spnUnk8.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65535, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(spnUnk8, gridBagConstraints);

        spnUnkA.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65535, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(spnUnkA, gridBagConstraints);

        spnUnkE.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(spnUnkE, gridBagConstraints);

        spnUnkF.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(spnUnkF, gridBagConstraints);

        spnUnk14.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65535, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(spnUnk14, gridBagConstraints);

        spnUnk16.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65535, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneSettings.add(spnUnk16, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pneSettings.add(filler3, gridBagConstraints);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scr, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove)
                        .addGap(0, 254, Short.MAX_VALUE))
                    .addComponent(pneSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pneSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnRemove)
                            .addComponent(btnSave)))
                    .addComponent(scr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warningLabel)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAllButtonActionPerformed
        saveAll();
    }//GEN-LAST:event_saveAllButtonActionPerformed

    private void listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listValueChanged
        int index = list.getSelectedIndex();
        
        if (index < 0) {
            enableComponents(false);
            return;
        }
        
        selected = data.entries.get(index);
        
        cmoPokemon.setSelectedIndex(selected.nameID);
        spnForm.setValue(selected.formID);
        cmoType.setSelectedIndex(selected.typeID);
        cmoAssist.setSelectedIndex(selected.assistID);
        cmoField.setSelectedIndex(selected.fieldID);
        spnFieldLevel.setValue(selected.fieldLevel);
        spnShadowWidth.setValue(selected.shadowWidth);
        spnShadowHeight.setValue(selected.shadowHeight);
        spnUnk8.setValue(selected.unk8);
        spnUnkA.setValue(selected.unkA);
        spnUnkE.setValue(selected.unkE);
        spnUnkF.setValue(selected.unkF);
        spnUnk14.setValue(selected.unk14);
        spnUnk16.setValue(selected.unk16);
        
        enableComponents(true);
    }//GEN-LAST:event_listValueChanged

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (selected == null)
            return;
        
        selected.nameID = cmoPokemon.getSelectedIndex();
        selected.formID = (short) spnForm.getValue();
        selected.typeID = (short) cmoType.getSelectedIndex();
        selected.assistID = (short) cmoAssist.getSelectedIndex();
        selected.fieldID = (short) cmoField.getSelectedIndex();
        selected.fieldLevel = (short) spnFieldLevel.getValue();
        selected.shadowWidth = (short) spnShadowWidth.getValue();
        selected.shadowHeight = (short) spnShadowHeight.getValue();
        selected.unk8 = (int) spnUnk8.getValue();
        selected.unkA = (int) spnUnkA.getValue();
        selected.unkE = (byte) spnUnkE.getValue();
        selected.unkF = (byte) spnUnkF.getValue();
        selected.unk14 = (int) spnUnk14.getValue();
        selected.unk16 = (int) spnUnk16.getValue();
        
        model.setElementAt(selected.toString(), list.getSelectedIndex());
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        PokeID.Entry mon = data.new Entry();
        
        data.entries.add(mon);
        model.addElement(mon.toString());
        
        int index = data.entries.size() - 1;
        
        if (index < 0)
            return;
        
        list.setSelectedIndex(index);
        list.ensureIndexIsVisible(index);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int index = list.getSelectedIndex();
        
        data.entries.remove(index);
        model.remove(index);
    }//GEN-LAST:event_btnRemoveActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmoAssist;
    private javax.swing.JComboBox<String> cmoField;
    private javax.swing.JComboBox<String> cmoPokemon;
    private javax.swing.JComboBox<String> cmoType;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel lblAssist;
    private javax.swing.JLabel lblField;
    private javax.swing.JLabel lblFieldLevel;
    private javax.swing.JLabel lblForm;
    private javax.swing.JLabel lblPokemon;
    private javax.swing.JLabel lblShadowHeight;
    private javax.swing.JLabel lblShadowWidth;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblUnk14;
    private javax.swing.JLabel lblUnk16;
    private javax.swing.JLabel lblUnk8;
    private javax.swing.JLabel lblUnkA;
    private javax.swing.JLabel lblUnkE;
    private javax.swing.JLabel lblUnkF;
    private javax.swing.JList<String> list;
    private javax.swing.JPanel pneSettings;
    private javax.swing.JButton saveAllButton;
    private javax.swing.JScrollPane scr;
    private javax.swing.JSpinner spnFieldLevel;
    private javax.swing.JSpinner spnForm;
    private javax.swing.JSpinner spnShadowHeight;
    private javax.swing.JSpinner spnShadowWidth;
    private javax.swing.JSpinner spnUnk14;
    private javax.swing.JSpinner spnUnk16;
    private javax.swing.JSpinner spnUnk8;
    private javax.swing.JSpinner spnUnkA;
    private javax.swing.JSpinner spnUnkE;
    private javax.swing.JSpinner spnUnkF;
    protected javax.swing.JToolBar toolbar;
    protected javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
}