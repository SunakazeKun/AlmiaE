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

package com.aurum.almia.swing;

import com.aurum.almia.Lists;
import com.aurum.almia.Main;
import com.aurum.almia.game.map.Layer;
import com.aurum.almia.game.map.Map;
import com.aurum.almia.game.map.MapInfo;
import com.aurum.almia.game.map.MonData;
import com.aurum.almia.game.map.NpcData;
import com.aurum.almia.game.map.TargetData;
import com.aurum.almia.game.map.TextureInfo;
import com.aurum.almia.game.map.WarpData;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.SpinnerNumberModel;

public class MapEditor extends JFrame {
    public Map map;
    
    protected DefaultListModel modelTexInfo;
    protected DefaultListModel modelWarps;
    protected DefaultListModel modelMons;
    protected DefaultListModel modelTargets;
    protected DefaultListModel modelNpcs;
    
    public MapEditor(Map map) {
        initComponents();
        
        this.map = map;
        
        initGUI();
        initData();
    }
    
    protected void initGUI() {
        modelTexInfo = new DefaultListModel();
        modelWarps = new DefaultListModel();
        modelMons = new DefaultListModel();
        modelTargets = new DefaultListModel();
        modelNpcs = new DefaultListModel();
        
        setTitle(String.format("Map Editor: Editing %s", map.name));
        updateLayers();
        
        listTexInfo.setModel(modelTexInfo);
        listWarps.setModel(modelWarps);
        listMons.setModel(modelMons);
        listTargets.setModel(modelTargets);
        listNpcs.setModel(modelNpcs);
        
        for (int i = 0 ; i < map.game.monDataBase.entries.size() ; i++) {
            cmoMonDataMon.addItem(String.format("%03X: %s", i, map.game.monDataBase.entries.get(i).toShortString()));
        }
        
        for (int i = 0 ; i < Lists.yakumono_name.size() ; i++) {
            cmoTargetID.addItem(String.format("%03X: %s", i, Lists.obstacles.get(i)));
        }
    }
    
    protected void initData() {
        spnMapInfoWidth.setValue(map.mapInfo.width);
        spnMapInfoHeight.setValue(map.mapInfo.height);
        spnMapInfoUnk8.setValue(map.mapInfo.unk8);
        spnMapInfoUnkC.setValue(map.mapInfo.unkC);
        spnTexInfoUnk2.setValue(map.texInfo.unk2);
        
        reloadTexInfoUI();
        reloadLayer();
    }
    
    protected void reloadLayer() {
        modelWarps.clear();
        
        for (WarpData.Entry warp : getCurLayer().warpData.entries) {
            modelWarps.addElement(warp.toString());
        }
        
        reloadWarpUI();
        
        
        modelMons.clear();
        
        for (MonData.Entry mon : getCurLayer().monData.entries) {
            modelMons.addElement(mon.toString());
        }
        
        reloadMonUI();
        
        
        modelTargets.clear();
        
        for (TargetData.Entry target : getCurLayer().targetData.entries) {
            modelTargets.addElement(target.toString());
        }
        
        reloadTargetUI();
        
        
        modelNpcs.clear();
        
        for (NpcData.Entry npc : getCurLayer().npcData.entries) {
            modelNpcs.addElement(npc.toString());
        }
        
        reloadNpcUI();
    }
    
    protected void reloadTexInfoUI() {
        modelTexInfo.clear();
        
        for (int i = 0 ; i < map.texInfo.entries.size() ; i++) {
            modelTexInfo.addElement(String.format("Texture Info No. %d", i));
        }
        
        boolean b = map.texInfo.entries.size() > 0;
        listTexInfo.setEnabled(b);
        spnTexInfoUnk0.setEnabled(b);
        spnTexInfoTextureID.setEnabled(b);
        spnTexInfoUnk4.setEnabled(b);
        spnTexInfoUnk6.setEnabled(b);
    }
    
    protected void reloadWarpUI() {
        boolean b = getCurLayer().warpData.entries.size() > 0;
        listWarps.setEnabled(b);
        spnWarpPosX.setEnabled(b);
        spnWarpPosY.setEnabled(b);
        spnWarpUnk4.setEnabled(b);
        spnWarpUnk6.setEnabled(b);
        spnWarpUnk8.setEnabled(b);
    }
    
    protected void reloadMonUI() {
        boolean b = getCurLayer().monData.entries.size() > 0;
        listMons.setEnabled(b);
        spnMonDataPosX.setEnabled(b);
        spnMonDataPosY.setEnabled(b);
        spnMonDataBehavior.setEnabled(b);
        spnMonDataUnk5.setEnabled(b);
        cmoMonDataMon.setEnabled(b);
        spnMonDataUnk8.setEnabled(b);
        spnMonDataUnk9a.setEnabled(b);
    }
    
    protected void reloadTargetUI() {
        boolean b = getCurLayer().targetData.entries.size() > 0;
        listTargets.setEnabled(b);
        spnTargetPosX.setEnabled(b);
        spnTargetPosY.setEnabled(b);
        spnTargetUnk4.setEnabled(b);
        spnTargetUnk5.setEnabled(b);
        cmoTargetID.setEnabled(b);
        spnTargetUnk8.setEnabled(b);
        spnTargetUnk9.setEnabled(b);
        spnTargetUnkA.setEnabled(b);
        spnTargetUnkB.setEnabled(b);
    }
    
    protected void reloadNpcUI() {
        boolean b = getCurLayer().npcData.entries.size() > 0;
        listNpcs.setEnabled(b);
        spnNpcPosX.setEnabled(b);
        spnNpcPosY.setEnabled(b);
        cmoNpcDirection.setEnabled(b);
        chkNpcIsVisible.setEnabled(b);
        spnNpcID.setEnabled(b);
        spnNpcUnk8.setEnabled(b);
        spnNpcUnk9.setEnabled(b);
        chkNpcUnkA.setEnabled(b);
    }
    
    public void saveAll() {
        MapInfo mapinfo = map.mapInfo;
        mapinfo.width = (int)spnMapInfoWidth.getValue();
        mapinfo.height = (int)spnMapInfoHeight.getValue();
        mapinfo.unk8 = (int)spnMapInfoUnk8.getValue();
        mapinfo.unkC = (int)spnMapInfoUnkC.getValue();
        
        map.texInfo.unk2 = (short)spnTexInfoUnk2.getValue();
        
        try {
            map.save();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    protected Layer getCurLayer() {
        return map.layers.get((int)spnSelectedLayer.getValue());
    }
    
    protected void updateLayers() {
        int numLayers = map.layers.size();
        
        if (numLayers > 0) {
            spnSelectedLayer.setValue(0);
            ((SpinnerNumberModel)spnSelectedLayer.getModel()).setMaximum(numLayers - 1);
        }
        else {
            spnSelectedLayer.setValue(-1);
            ((SpinnerNumberModel)spnSelectedLayer.getModel()).setMinimum(-1);
            ((SpinnerNumberModel)spnSelectedLayer.getModel()).setMaximum(-1);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        toolbar = new javax.swing.JToolBar();
        saveAllButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        lblSelectedLayer = new javax.swing.JLabel();
        spnSelectedLayer = new javax.swing.JSpinner();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnLayerAdd = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnLayerDelete = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        tabPane = new javax.swing.JTabbedPane();
        pneMapInfo = new javax.swing.JPanel();
        lblMapInfoWidth = new javax.swing.JLabel();
        lblMapInfoHeight = new javax.swing.JLabel();
        lblMapInfoUnk8 = new javax.swing.JLabel();
        lblMapInfoUnkC = new javax.swing.JLabel();
        spnMapInfoWidth = new javax.swing.JSpinner();
        spnMapInfoHeight = new javax.swing.JSpinner();
        spnMapInfoUnk8 = new javax.swing.JSpinner();
        spnMapInfoUnkC = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        lblTexInfoUnk2 = new javax.swing.JLabel();
        spnTexInfoUnk2 = new javax.swing.JSpinner();
        pneTexInfo = new javax.swing.JPanel();
        scrTexInfo = new javax.swing.JScrollPane();
        listTexInfo = new javax.swing.JList<>();
        pneTexInfoSettings = new javax.swing.JPanel();
        lblTexInfoUnk0 = new javax.swing.JLabel();
        lblTexInfoID = new javax.swing.JLabel();
        lblTexInfoUnk4 = new javax.swing.JLabel();
        lblTexInfoUnk6 = new javax.swing.JLabel();
        spnTexInfoUnk0 = new javax.swing.JSpinner();
        spnTexInfoTextureID = new javax.swing.JSpinner();
        spnTexInfoUnk4 = new javax.swing.JSpinner();
        spnTexInfoUnk6 = new javax.swing.JSpinner();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        btnTexInfoSave = new javax.swing.JButton();
        btnTexInfoAdd = new javax.swing.JButton();
        btnTexInfoRemove = new javax.swing.JButton();
        pneWarps = new javax.swing.JPanel();
        scrWarps = new javax.swing.JScrollPane();
        listWarps = new javax.swing.JList<>();
        pneWarpSettings = new javax.swing.JPanel();
        lblWarpPosX = new javax.swing.JLabel();
        lblWarpPosY = new javax.swing.JLabel();
        lblWarpUnk4 = new javax.swing.JLabel();
        lblWarpUnk6 = new javax.swing.JLabel();
        lblWarpUnk8 = new javax.swing.JLabel();
        spnWarpPosX = new javax.swing.JSpinner();
        spnWarpPosY = new javax.swing.JSpinner();
        spnWarpUnk4 = new javax.swing.JSpinner();
        spnWarpUnk6 = new javax.swing.JSpinner();
        spnWarpUnk8 = new javax.swing.JSpinner();
        fillerWarp = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        btnWarpSave = new javax.swing.JButton();
        btnWarpAdd = new javax.swing.JButton();
        btnWarpRemove = new javax.swing.JButton();
        pneTargets = new javax.swing.JPanel();
        scrTargets = new javax.swing.JScrollPane();
        listTargets = new javax.swing.JList<>();
        pneTargetSettings = new javax.swing.JPanel();
        lblTargetPosX = new javax.swing.JLabel();
        lblTargetPosY = new javax.swing.JLabel();
        lblTargetUnk4 = new javax.swing.JLabel();
        lblTargetUnk5 = new javax.swing.JLabel();
        lblTargetID = new javax.swing.JLabel();
        lblTargetUnk8 = new javax.swing.JLabel();
        lblTargetUnk9 = new javax.swing.JLabel();
        lblTargetUnkA = new javax.swing.JLabel();
        lblTargetUnkB = new javax.swing.JLabel();
        spnTargetPosX = new javax.swing.JSpinner();
        spnTargetPosY = new javax.swing.JSpinner();
        spnTargetUnk4 = new javax.swing.JSpinner();
        spnTargetUnk5 = new javax.swing.JSpinner();
        cmoTargetID = new javax.swing.JComboBox<>();
        spnTargetUnk8 = new javax.swing.JSpinner();
        spnTargetUnk9 = new javax.swing.JSpinner();
        spnTargetUnkA = new javax.swing.JSpinner();
        spnTargetUnkB = new javax.swing.JSpinner();
        fillerTarget = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        btnTargetSave = new javax.swing.JButton();
        btnTargetAdd = new javax.swing.JButton();
        btnTargetRemove = new javax.swing.JButton();
        pneMons = new javax.swing.JPanel();
        scrMons = new javax.swing.JScrollPane();
        listMons = new javax.swing.JList<>();
        pneMonSettings = new javax.swing.JPanel();
        lblMonDataPosX = new javax.swing.JLabel();
        lblMonDataPosY = new javax.swing.JLabel();
        lblMonDataBehavior = new javax.swing.JLabel();
        lblMonDataUnk5 = new javax.swing.JLabel();
        lblMonDataMon = new javax.swing.JLabel();
        lblMonDataUnk8 = new javax.swing.JLabel();
        lblMonDataUnk9a = new javax.swing.JLabel();
        spnMonDataPosX = new javax.swing.JSpinner();
        spnMonDataPosY = new javax.swing.JSpinner();
        spnMonDataBehavior = new javax.swing.JSpinner();
        spnMonDataUnk5 = new javax.swing.JSpinner();
        cmoMonDataMon = new javax.swing.JComboBox<>();
        spnMonDataUnk8 = new javax.swing.JSpinner();
        spnMonDataUnk9a = new javax.swing.JSpinner();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        btnMonSave = new javax.swing.JButton();
        btnMonAdd = new javax.swing.JButton();
        btnMonRemove = new javax.swing.JButton();
        pneNpcs = new javax.swing.JPanel();
        scrNpcs = new javax.swing.JScrollPane();
        listNpcs = new javax.swing.JList<>();
        pneNpcSettings = new javax.swing.JPanel();
        lblNpcPosX = new javax.swing.JLabel();
        lblNpcPosY = new javax.swing.JLabel();
        lblNpcDirection = new javax.swing.JLabel();
        lblNpcNpc = new javax.swing.JLabel();
        lblNpcUnk8 = new javax.swing.JLabel();
        lblNpcUnk9 = new javax.swing.JLabel();
        spnNpcPosX = new javax.swing.JSpinner();
        spnNpcPosY = new javax.swing.JSpinner();
        cmoNpcDirection = new javax.swing.JComboBox<>();
        chkNpcIsVisible = new javax.swing.JCheckBox();
        spnNpcID = new javax.swing.JSpinner();
        spnNpcUnk8 = new javax.swing.JSpinner();
        spnNpcUnk9 = new javax.swing.JSpinner();
        chkNpcUnkA = new javax.swing.JCheckBox();
        fillerNpc = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        btnNpcSave = new javax.swing.JButton();
        btnNpcAdd = new javax.swing.JButton();
        btnNpcRemove = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Map Editor: Editing <null>");
        setIconImage(Main.icon);
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
        toolbar.add(jSeparator2);

        lblSelectedLayer.setText("Selected layer: ");
        toolbar.add(lblSelectedLayer);

        spnSelectedLayer.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));
        spnSelectedLayer.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnSelectedLayerStateChanged(evt);
            }
        });
        toolbar.add(spnSelectedLayer);
        toolbar.add(jSeparator3);

        btnLayerAdd.setText("Add");
        btnLayerAdd.setFocusable(false);
        btnLayerAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLayerAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLayerAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayerAddActionPerformed(evt);
            }
        });
        toolbar.add(btnLayerAdd);
        toolbar.add(jSeparator4);

        btnLayerDelete.setText("Delete");
        btnLayerDelete.setFocusable(false);
        btnLayerDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLayerDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLayerDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayerDeleteActionPerformed(evt);
            }
        });
        toolbar.add(btnLayerDelete);
        toolbar.add(filler1);

        lblMapInfoWidth.setText("Map width");

        lblMapInfoHeight.setText("Map height");

        lblMapInfoUnk8.setText("int (0x8)");

        lblMapInfoUnkC.setText("int (0xC)");

        spnMapInfoWidth.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));

        spnMapInfoHeight.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));

        spnMapInfoUnk8.setModel(new javax.swing.SpinnerNumberModel());

        spnMapInfoUnkC.setModel(new javax.swing.SpinnerNumberModel());

        lblTexInfoUnk2.setText("short (TXIF 0x2)");

        spnTexInfoUnk2.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));

        javax.swing.GroupLayout pneMapInfoLayout = new javax.swing.GroupLayout(pneMapInfo);
        pneMapInfo.setLayout(pneMapInfoLayout);
        pneMapInfoLayout.setHorizontalGroup(
            pneMapInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(pneMapInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pneMapInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pneMapInfoLayout.createSequentialGroup()
                        .addComponent(lblMapInfoWidth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spnMapInfoWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pneMapInfoLayout.createSequentialGroup()
                        .addComponent(lblMapInfoUnkC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spnMapInfoUnkC, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pneMapInfoLayout.createSequentialGroup()
                        .addComponent(lblMapInfoHeight)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spnMapInfoHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pneMapInfoLayout.createSequentialGroup()
                        .addComponent(lblMapInfoUnk8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spnMapInfoUnk8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pneMapInfoLayout.createSequentialGroup()
                        .addComponent(lblTexInfoUnk2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 357, Short.MAX_VALUE)
                        .addComponent(spnTexInfoUnk2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pneMapInfoLayout.setVerticalGroup(
            pneMapInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pneMapInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pneMapInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMapInfoWidth)
                    .addComponent(spnMapInfoWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pneMapInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMapInfoHeight)
                    .addComponent(spnMapInfoHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pneMapInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMapInfoUnk8)
                    .addComponent(spnMapInfoUnk8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pneMapInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMapInfoUnkC)
                    .addComponent(spnMapInfoUnkC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pneMapInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTexInfoUnk2)
                    .addComponent(spnTexInfoUnk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(255, Short.MAX_VALUE))
        );

        tabPane.addTab("Map Info", pneMapInfo);

        listTexInfo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listTexInfo.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listTexInfoValueChanged(evt);
            }
        });
        scrTexInfo.setViewportView(listTexInfo);

        pneTexInfoSettings.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pneTexInfoSettings.setLayout(new java.awt.GridBagLayout());

        lblTexInfoUnk0.setText("unk0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTexInfoSettings.add(lblTexInfoUnk0, gridBagConstraints);

        lblTexInfoID.setText("Texture ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTexInfoSettings.add(lblTexInfoID, gridBagConstraints);

        lblTexInfoUnk4.setText("unk4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTexInfoSettings.add(lblTexInfoUnk4, gridBagConstraints);

        lblTexInfoUnk6.setText("unk6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTexInfoSettings.add(lblTexInfoUnk6, gridBagConstraints);

        spnTexInfoUnk0.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTexInfoSettings.add(spnTexInfoUnk0, gridBagConstraints);

        spnTexInfoTextureID.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65535, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTexInfoSettings.add(spnTexInfoTextureID, gridBagConstraints);

        spnTexInfoUnk4.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTexInfoSettings.add(spnTexInfoUnk4, gridBagConstraints);

        spnTexInfoUnk6.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTexInfoSettings.add(spnTexInfoUnk6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pneTexInfoSettings.add(filler3, gridBagConstraints);

        btnTexInfoSave.setText("Save");
        btnTexInfoSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTexInfoSaveActionPerformed(evt);
            }
        });

        btnTexInfoAdd.setText("Add");
        btnTexInfoAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTexInfoAddActionPerformed(evt);
            }
        });

        btnTexInfoRemove.setText("Remove");
        btnTexInfoRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTexInfoRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pneTexInfoLayout = new javax.swing.GroupLayout(pneTexInfo);
        pneTexInfo.setLayout(pneTexInfoLayout);
        pneTexInfoLayout.setHorizontalGroup(
            pneTexInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pneTexInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrTexInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pneTexInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pneTexInfoSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pneTexInfoLayout.createSequentialGroup()
                        .addComponent(btnTexInfoSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTexInfoAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTexInfoRemove)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pneTexInfoLayout.setVerticalGroup(
            pneTexInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pneTexInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pneTexInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrTexInfo)
                    .addGroup(pneTexInfoLayout.createSequentialGroup()
                        .addComponent(pneTexInfoSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pneTexInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTexInfoSave)
                            .addComponent(btnTexInfoAdd)
                            .addComponent(btnTexInfoRemove))))
                .addContainerGap())
        );

        tabPane.addTab("Texture Info", pneTexInfo);

        listWarps.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listWarps.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listWarpsValueChanged(evt);
            }
        });
        scrWarps.setViewportView(listWarps);

        pneWarpSettings.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pneWarpSettings.setLayout(new java.awt.GridBagLayout());

        lblWarpPosX.setText("X Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneWarpSettings.add(lblWarpPosX, gridBagConstraints);

        lblWarpPosY.setText("Y Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneWarpSettings.add(lblWarpPosY, gridBagConstraints);

        lblWarpUnk4.setText("short (0x4)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneWarpSettings.add(lblWarpUnk4, gridBagConstraints);

        lblWarpUnk6.setText("short (0x6)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneWarpSettings.add(lblWarpUnk6, gridBagConstraints);

        lblWarpUnk8.setText("short (0x8)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneWarpSettings.add(lblWarpUnk8, gridBagConstraints);

        spnWarpPosX.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneWarpSettings.add(spnWarpPosX, gridBagConstraints);

        spnWarpPosY.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneWarpSettings.add(spnWarpPosY, gridBagConstraints);

        spnWarpUnk4.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneWarpSettings.add(spnWarpUnk4, gridBagConstraints);

        spnWarpUnk6.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneWarpSettings.add(spnWarpUnk6, gridBagConstraints);

        spnWarpUnk8.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneWarpSettings.add(spnWarpUnk8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pneWarpSettings.add(fillerWarp, gridBagConstraints);

        btnWarpSave.setText("Save");
        btnWarpSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWarpSaveActionPerformed(evt);
            }
        });

        btnWarpAdd.setText("Add");
        btnWarpAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWarpAddActionPerformed(evt);
            }
        });

        btnWarpRemove.setText("Remove");
        btnWarpRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWarpRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pneWarpsLayout = new javax.swing.GroupLayout(pneWarps);
        pneWarps.setLayout(pneWarpsLayout);
        pneWarpsLayout.setHorizontalGroup(
            pneWarpsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pneWarpsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrWarps, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pneWarpsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pneWarpSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pneWarpsLayout.createSequentialGroup()
                        .addComponent(btnWarpSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnWarpAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnWarpRemove)
                        .addGap(0, 112, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pneWarpsLayout.setVerticalGroup(
            pneWarpsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pneWarpsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pneWarpsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pneWarpsLayout.createSequentialGroup()
                        .addComponent(pneWarpSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pneWarpsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnWarpSave)
                            .addComponent(btnWarpAdd)
                            .addComponent(btnWarpRemove)))
                    .addComponent(scrWarps))
                .addContainerGap())
        );

        tabPane.addTab("Warps", new javax.swing.ImageIcon(getClass().getResource("/res/tp_warp.png")), pneWarps); // NOI18N

        listTargets.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listTargets.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listTargetsValueChanged(evt);
            }
        });
        scrTargets.setViewportView(listTargets);

        pneTargetSettings.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pneTargetSettings.setLayout(new java.awt.GridBagLayout());

        lblTargetPosX.setText("X Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(lblTargetPosX, gridBagConstraints);

        lblTargetPosY.setText("Y Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(lblTargetPosY, gridBagConstraints);

        lblTargetUnk4.setText("byte (0x4)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(lblTargetUnk4, gridBagConstraints);

        lblTargetUnk5.setText("byte (0x5)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(lblTargetUnk5, gridBagConstraints);

        lblTargetID.setText("Obstacle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(lblTargetID, gridBagConstraints);

        lblTargetUnk8.setText("byte (0x8)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(lblTargetUnk8, gridBagConstraints);

        lblTargetUnk9.setText("byte (0x9)");
        lblTargetUnk9.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(lblTargetUnk9, gridBagConstraints);

        lblTargetUnkA.setText("byte (0xA)");
        lblTargetUnkA.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(lblTargetUnkA, gridBagConstraints);

        lblTargetUnkB.setText("byte (0xB)");
        lblTargetUnkB.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(lblTargetUnkB, gridBagConstraints);

        spnTargetPosX.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(spnTargetPosX, gridBagConstraints);

        spnTargetPosY.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(spnTargetPosY, gridBagConstraints);

        spnTargetUnk4.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(spnTargetUnk4, gridBagConstraints);

        spnTargetUnk5.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(spnTargetUnk5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(cmoTargetID, gridBagConstraints);

        spnTargetUnk8.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(spnTargetUnk8, gridBagConstraints);

        spnTargetUnk9.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(spnTargetUnk9, gridBagConstraints);

        spnTargetUnkA.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(spnTargetUnkA, gridBagConstraints);

        spnTargetUnkB.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneTargetSettings.add(spnTargetUnkB, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pneTargetSettings.add(fillerTarget, gridBagConstraints);

        btnTargetSave.setText("Save");
        btnTargetSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTargetSaveActionPerformed(evt);
            }
        });

        btnTargetAdd.setText("Add");
        btnTargetAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTargetAddActionPerformed(evt);
            }
        });

        btnTargetRemove.setText("Remove");
        btnTargetRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTargetRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pneTargetsLayout = new javax.swing.GroupLayout(pneTargets);
        pneTargets.setLayout(pneTargetsLayout);
        pneTargetsLayout.setHorizontalGroup(
            pneTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pneTargetsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrTargets, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pneTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pneTargetSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pneTargetsLayout.createSequentialGroup()
                        .addComponent(btnTargetSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTargetAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTargetRemove)
                        .addGap(0, 112, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pneTargetsLayout.setVerticalGroup(
            pneTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pneTargetsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pneTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pneTargetsLayout.createSequentialGroup()
                        .addComponent(pneTargetSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pneTargetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTargetSave)
                            .addComponent(btnTargetAdd)
                            .addComponent(btnTargetRemove)))
                    .addComponent(scrTargets))
                .addContainerGap())
        );

        tabPane.addTab("Targets", new javax.swing.ImageIcon(getClass().getResource("/res/tp_target.png")), pneTargets); // NOI18N

        listMons.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listMons.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listMonsValueChanged(evt);
            }
        });
        scrMons.setViewportView(listMons);

        pneMonSettings.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pneMonSettings.setLayout(new java.awt.GridBagLayout());

        lblMonDataPosX.setText("X Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(lblMonDataPosX, gridBagConstraints);

        lblMonDataPosY.setText("Y Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(lblMonDataPosY, gridBagConstraints);

        lblMonDataBehavior.setText("Behavior");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(lblMonDataBehavior, gridBagConstraints);

        lblMonDataUnk5.setText("byte (0x5)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(lblMonDataUnk5, gridBagConstraints);

        lblMonDataMon.setText("Pokémon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(lblMonDataMon, gridBagConstraints);

        lblMonDataUnk8.setText("byte (0x8)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(lblMonDataUnk8, gridBagConstraints);

        lblMonDataUnk9a.setText("byte (0x9)");
        lblMonDataUnk9a.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(lblMonDataUnk9a, gridBagConstraints);

        spnMonDataPosX.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(spnMonDataPosX, gridBagConstraints);

        spnMonDataPosY.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(spnMonDataPosY, gridBagConstraints);

        spnMonDataBehavior.setModel(new javax.swing.SpinnerNumberModel(Short.valueOf((short)0), Short.valueOf((short)0), Short.valueOf((short)255), Short.valueOf((short)1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(spnMonDataBehavior, gridBagConstraints);

        spnMonDataUnk5.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(spnMonDataUnk5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(cmoMonDataMon, gridBagConstraints);

        spnMonDataUnk8.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(spnMonDataUnk8, gridBagConstraints);

        spnMonDataUnk9a.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneMonSettings.add(spnMonDataUnk9a, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pneMonSettings.add(filler2, gridBagConstraints);

        btnMonSave.setText("Save");
        btnMonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonSaveActionPerformed(evt);
            }
        });

        btnMonAdd.setText("Add");
        btnMonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonAddActionPerformed(evt);
            }
        });

        btnMonRemove.setText("Remove");
        btnMonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pneMonsLayout = new javax.swing.GroupLayout(pneMons);
        pneMons.setLayout(pneMonsLayout);
        pneMonsLayout.setHorizontalGroup(
            pneMonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pneMonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrMons, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pneMonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pneMonSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pneMonsLayout.createSequentialGroup()
                        .addComponent(btnMonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMonRemove)
                        .addGap(0, 112, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pneMonsLayout.setVerticalGroup(
            pneMonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pneMonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pneMonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pneMonsLayout.createSequentialGroup()
                        .addComponent(pneMonSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pneMonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMonSave)
                            .addComponent(btnMonAdd)
                            .addComponent(btnMonRemove)))
                    .addComponent(scrMons))
                .addContainerGap())
        );

        tabPane.addTab("Pokémon", new javax.swing.ImageIcon(getClass().getResource("/res/tp_pokemon.png")), pneMons); // NOI18N

        listNpcs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listNpcs.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listNpcsValueChanged(evt);
            }
        });
        scrNpcs.setViewportView(listNpcs);

        pneNpcSettings.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pneNpcSettings.setLayout(new java.awt.GridBagLayout());

        lblNpcPosX.setText("X Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(lblNpcPosX, gridBagConstraints);

        lblNpcPosY.setText("Y Position");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(lblNpcPosY, gridBagConstraints);

        lblNpcDirection.setText("Direction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(lblNpcDirection, gridBagConstraints);

        lblNpcNpc.setText("Sprite");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(lblNpcNpc, gridBagConstraints);

        lblNpcUnk8.setText("byte (0x8)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(lblNpcUnk8, gridBagConstraints);

        lblNpcUnk9.setText("byte (0x9)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(lblNpcUnk9, gridBagConstraints);

        spnNpcPosX.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(spnNpcPosX, gridBagConstraints);

        spnNpcPosY.setModel(new javax.swing.SpinnerNumberModel((short)0, null, null, (short)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(spnNpcPosY, gridBagConstraints);

        cmoNpcDirection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0x00: Down", "0x01: Left", "0x02: Up", "0x03: Right", "0x04: Down(?)", "0x05: Left(?)", "0x06: Up(?)", "0x07: Right(?)", "0x08: Down(?)", "0x09: Left(?)", "0x0A: Up(?)", "0x0B: Right(?)" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(cmoNpcDirection, gridBagConstraints);

        chkNpcIsVisible.setText("Is visible?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(chkNpcIsVisible, gridBagConstraints);

        spnNpcID.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(spnNpcID, gridBagConstraints);

        spnNpcUnk8.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(spnNpcUnk8, gridBagConstraints);

        spnNpcUnk9.setModel(new javax.swing.SpinnerNumberModel((byte)0, null, null, (byte)1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(spnNpcUnk9, gridBagConstraints);

        chkNpcUnkA.setText("bool (0xA)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        pneNpcSettings.add(chkNpcUnkA, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pneNpcSettings.add(fillerNpc, gridBagConstraints);

        btnNpcSave.setText("Save");
        btnNpcSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNpcSaveActionPerformed(evt);
            }
        });

        btnNpcAdd.setText("Add");
        btnNpcAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNpcAddActionPerformed(evt);
            }
        });

        btnNpcRemove.setText("Remove");
        btnNpcRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNpcRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pneNpcsLayout = new javax.swing.GroupLayout(pneNpcs);
        pneNpcs.setLayout(pneNpcsLayout);
        pneNpcsLayout.setHorizontalGroup(
            pneNpcsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pneNpcsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrNpcs, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pneNpcsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pneNpcSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGroup(pneNpcsLayout.createSequentialGroup()
                        .addComponent(btnNpcSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNpcAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNpcRemove)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pneNpcsLayout.setVerticalGroup(
            pneNpcsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pneNpcsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pneNpcsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrNpcs, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                    .addGroup(pneNpcsLayout.createSequentialGroup()
                        .addComponent(pneNpcSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pneNpcsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNpcSave)
                            .addComponent(btnNpcAdd)
                            .addComponent(btnNpcRemove))))
                .addContainerGap())
        );

        tabPane.addTab("NPCs", new javax.swing.ImageIcon(getClass().getResource("/res/tp_npc.png")), pneNpcs); // NOI18N

        warningLabel.setForeground(new java.awt.Color(255, 0, 0));
        warningLabel.setText(" ");
        warningLabel.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPane)
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

    private void listTexInfoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listTexInfoValueChanged
        int index = listTexInfo.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        TextureInfo.Entry tex = map.texInfo.entries.get(index);
        
        spnTexInfoUnk0.setValue(tex.unk0);
        spnTexInfoTextureID.setValue(tex.textureID);
        spnTexInfoUnk4.setValue(tex.unk4);
        spnTexInfoUnk6.setValue(tex.unk6);
    }//GEN-LAST:event_listTexInfoValueChanged

    private void btnTexInfoSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTexInfoSaveActionPerformed
        int index = listTexInfo.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        TextureInfo.Entry tex = map.texInfo.entries.get(index);
        
        tex.unk0 = (short)spnTexInfoUnk0.getValue();
        tex.textureID = (int)spnTexInfoTextureID.getValue();
        tex.unk4 = (short)spnTexInfoUnk4.getValue();
        tex.unk6 = (short)spnTexInfoUnk6.getValue();
    }//GEN-LAST:event_btnTexInfoSaveActionPerformed

    private void btnTexInfoAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTexInfoAddActionPerformed
        TextureInfo.Entry tex = map.texInfo.new Entry();
        
        map.texInfo.entries.add(tex);
        
        reloadTexInfoUI();
    }//GEN-LAST:event_btnTexInfoAddActionPerformed

    private void btnTexInfoRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTexInfoRemoveActionPerformed
        int index = listTexInfo.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        map.texInfo.entries.remove(index);
        
        reloadTexInfoUI();
    }//GEN-LAST:event_btnTexInfoRemoveActionPerformed

    private void listMonsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listMonsValueChanged
        int index = listMons.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        MonData.Entry mon = getCurLayer().monData.entries.get(index);
        
        spnMonDataPosX.setValue(mon.posX);
        spnMonDataPosY.setValue(mon.posY);
        spnMonDataBehavior.setValue(mon.behavior);
        spnMonDataUnk5.setValue(mon.enabled);
        cmoMonDataMon.setSelectedIndex(mon.monID);
        spnMonDataUnk8.setValue(mon.unk8);
        spnMonDataUnk9a.setValue(mon.unk9);
    }//GEN-LAST:event_listMonsValueChanged

    private void btnMonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonSaveActionPerformed
        int index = listMons.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        MonData.Entry mon = getCurLayer().monData.entries.get(index);
        
        mon.posX = (short)spnMonDataPosX.getValue();
        mon.posY = (short)spnMonDataPosY.getValue();
        mon.behavior = (short)spnMonDataBehavior.getValue();
        mon.enabled = (byte)spnMonDataUnk5.getValue();
        mon.monID = cmoMonDataMon.getSelectedIndex();
        mon.unk8 = (byte)spnMonDataUnk8.getValue();
        mon.unk9 = (byte)spnMonDataUnk9a.getValue();
        
        modelMons.setElementAt(mon.toString(), index);
    }//GEN-LAST:event_btnMonSaveActionPerformed

    private void btnMonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonAddActionPerformed
        MonData mons = getCurLayer().monData;
        MonData.Entry mon = mons.new Entry();
        
        mons.entries.add(mon);
        modelMons.addElement(mon.toString());
        
        reloadMonUI();
    }//GEN-LAST:event_btnMonAddActionPerformed

    private void btnMonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonRemoveActionPerformed
        int index = listMons.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        getCurLayer().monData.entries.remove(index);
        modelMons.remove(index);
        
        reloadMonUI();
    }//GEN-LAST:event_btnMonRemoveActionPerformed

    private void listNpcsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listNpcsValueChanged
        int index = listNpcs.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        NpcData.Entry npc = getCurLayer().npcData.entries.get(index);
        
        spnNpcPosX.setValue(npc.posX);
        spnNpcPosY.setValue(npc.posY);
        cmoNpcDirection.setSelectedIndex(npc.direction);
        chkNpcIsVisible.setSelected(npc.isVisible);
        spnNpcID.setValue(npc.npcID);
        spnNpcUnk8.setValue(npc.unk8);
        spnNpcUnk9.setValue(npc.unk9);
        chkNpcUnkA.setSelected(npc.unkA);
    }//GEN-LAST:event_listNpcsValueChanged

    private void btnNpcSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNpcSaveActionPerformed
        int index = listNpcs.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        NpcData.Entry npc = getCurLayer().npcData.entries.get(index);
        
        npc.posX = (short)spnNpcPosX.getValue();
        npc.posY = (short)spnNpcPosY.getValue();
        npc.direction = (byte)cmoNpcDirection.getSelectedIndex();
        npc.isVisible = chkNpcIsVisible.isSelected();
        npc.npcID = (int)spnNpcID.getValue();
        npc.unk8 = (byte)spnNpcUnk8.getValue();
        npc.unk9 = (byte)spnNpcUnk9.getValue();
        npc.unkA = chkNpcUnkA.isSelected();
        
        modelNpcs.setElementAt(npc.toString(), index);
    }//GEN-LAST:event_btnNpcSaveActionPerformed

    private void btnNpcAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNpcAddActionPerformed
        NpcData npcs = getCurLayer().npcData;
        NpcData.Entry npc = npcs.new Entry();
        
        npcs.entries.add(npc);
        modelNpcs.addElement(npc.toString());
        
        reloadNpcUI();
    }//GEN-LAST:event_btnNpcAddActionPerformed

    private void btnNpcRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNpcRemoveActionPerformed
        int index = listNpcs.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        getCurLayer().npcData.entries.remove(index);
        modelNpcs.remove(index);
        
        reloadNpcUI();
    }//GEN-LAST:event_btnNpcRemoveActionPerformed

    private void listTargetsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listTargetsValueChanged
        int index = listTargets.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        TargetData.Entry target = getCurLayer().targetData.entries.get(index);
        
        spnTargetPosX.setValue(target.posX);
        spnTargetPosY.setValue(target.posY);
        spnTargetUnk4.setValue(target.unk4);
        spnTargetUnk5.setValue(target.unk5);
        cmoTargetID.setSelectedIndex(target.obstacleID);
        spnTargetUnk8.setValue(target.unk8);
        spnTargetUnk9.setValue(target.unk9);
        spnTargetUnkA.setValue(target.unkA);
        spnTargetUnkB.setValue(target.unkB);
    }//GEN-LAST:event_listTargetsValueChanged

    private void btnTargetSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTargetSaveActionPerformed
        int index = listTargets.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        TargetData.Entry target = getCurLayer().targetData.entries.get(index);
        
        target.posX = (short)spnTargetPosX.getValue();
        target.posY = (short)spnTargetPosY.getValue();
        target.unk4 = (byte)spnTargetUnk4.getValue();
        target.unk5 = (byte)spnTargetUnk5.getValue();
        target.obstacleID = cmoTargetID.getSelectedIndex();
        target.unk8 = (byte)spnTargetUnk8.getValue();
        target.unk9 = (byte)spnTargetUnk9.getValue();
        target.unkA = (byte)spnTargetUnkA.getValue();
        target.unkB = (byte)spnTargetUnkB.getValue();
        
        modelTargets.setElementAt(target.toString(), index);
    }//GEN-LAST:event_btnTargetSaveActionPerformed

    private void btnTargetAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTargetAddActionPerformed
        TargetData targets = getCurLayer().targetData;
        TargetData.Entry target = targets.new Entry();
        
        targets.entries.add(target);
        modelTargets.addElement(target.toString());
        
        reloadTargetUI();
    }//GEN-LAST:event_btnTargetAddActionPerformed

    private void btnTargetRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTargetRemoveActionPerformed
        int index = listTargets.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        getCurLayer().targetData.entries.remove(index);
        modelTargets.remove(index);
        
        reloadTargetUI();
    }//GEN-LAST:event_btnTargetRemoveActionPerformed

    private void listWarpsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listWarpsValueChanged
        int index = listWarps.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        WarpData.Entry warp = getCurLayer().warpData.entries.get(index);
        
        spnWarpPosX.setValue(warp.posX);
        spnWarpPosY.setValue(warp.posY);
        spnWarpUnk4.setValue(warp.unk4);
        spnWarpUnk6.setValue(warp.unk6);
        spnWarpUnk8.setValue(warp.unk8);
    }//GEN-LAST:event_listWarpsValueChanged

    private void btnWarpSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWarpSaveActionPerformed
        int index = listWarps.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        WarpData.Entry warp = getCurLayer().warpData.entries.get(index);
        
        warp.posX = (short)spnWarpPosX.getValue();
        warp.posY = (short)spnWarpPosY.getValue();
        warp.unk4 = (short)spnWarpUnk4.getValue();
        warp.unk6 = (short)spnWarpUnk6.getValue();
        warp.unk8 = (short)spnWarpUnk8.getValue();
        
        modelWarps.setElementAt(warp.toString(), index);
    }//GEN-LAST:event_btnWarpSaveActionPerformed

    private void btnWarpAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWarpAddActionPerformed
        WarpData warps = getCurLayer().warpData;
        WarpData.Entry warp = warps.new Entry();
        
        warps.entries.add(warp);
        modelWarps.addElement(warp.toString());
        
        reloadWarpUI();
    }//GEN-LAST:event_btnWarpAddActionPerformed

    private void btnWarpRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWarpRemoveActionPerformed
        int index = listWarps.getSelectedIndex();
        
        if (index < 0) {
            return;
        }
        
        getCurLayer().warpData.entries.remove(index);
        modelWarps.remove(index);
        
        reloadWarpUI();
    }//GEN-LAST:event_btnWarpRemoveActionPerformed

    private void spnSelectedLayerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnSelectedLayerStateChanged
        reloadLayer();
    }//GEN-LAST:event_spnSelectedLayerStateChanged

    private void btnLayerAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayerAddActionPerformed
        map.layers.add(new Layer(map));
        updateLayers();
    }//GEN-LAST:event_btnLayerAddActionPerformed

    private void btnLayerDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayerDeleteActionPerformed
        if (map.layers.size() > 1) {
            map.layers.remove((int)spnSelectedLayer.getValue());
            updateLayers();
        }
    }//GEN-LAST:event_btnLayerDeleteActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLayerAdd;
    private javax.swing.JButton btnLayerDelete;
    private javax.swing.JButton btnMonAdd;
    private javax.swing.JButton btnMonRemove;
    private javax.swing.JButton btnMonSave;
    private javax.swing.JButton btnNpcAdd;
    private javax.swing.JButton btnNpcRemove;
    private javax.swing.JButton btnNpcSave;
    private javax.swing.JButton btnTargetAdd;
    private javax.swing.JButton btnTargetRemove;
    private javax.swing.JButton btnTargetSave;
    private javax.swing.JButton btnTexInfoAdd;
    private javax.swing.JButton btnTexInfoRemove;
    private javax.swing.JButton btnTexInfoSave;
    private javax.swing.JButton btnWarpAdd;
    private javax.swing.JButton btnWarpRemove;
    private javax.swing.JButton btnWarpSave;
    private javax.swing.JCheckBox chkNpcIsVisible;
    private javax.swing.JCheckBox chkNpcUnkA;
    private javax.swing.JComboBox<String> cmoMonDataMon;
    private javax.swing.JComboBox<String> cmoNpcDirection;
    private javax.swing.JComboBox<String> cmoTargetID;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler fillerNpc;
    private javax.swing.Box.Filler fillerTarget;
    private javax.swing.Box.Filler fillerWarp;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JLabel lblMapInfoHeight;
    private javax.swing.JLabel lblMapInfoUnk8;
    private javax.swing.JLabel lblMapInfoUnkC;
    private javax.swing.JLabel lblMapInfoWidth;
    private javax.swing.JLabel lblMonDataBehavior;
    private javax.swing.JLabel lblMonDataMon;
    private javax.swing.JLabel lblMonDataPosX;
    private javax.swing.JLabel lblMonDataPosY;
    private javax.swing.JLabel lblMonDataUnk5;
    private javax.swing.JLabel lblMonDataUnk8;
    private javax.swing.JLabel lblMonDataUnk9a;
    private javax.swing.JLabel lblNpcDirection;
    private javax.swing.JLabel lblNpcNpc;
    private javax.swing.JLabel lblNpcPosX;
    private javax.swing.JLabel lblNpcPosY;
    private javax.swing.JLabel lblNpcUnk8;
    private javax.swing.JLabel lblNpcUnk9;
    private javax.swing.JLabel lblSelectedLayer;
    private javax.swing.JLabel lblTargetID;
    private javax.swing.JLabel lblTargetPosX;
    private javax.swing.JLabel lblTargetPosY;
    private javax.swing.JLabel lblTargetUnk4;
    private javax.swing.JLabel lblTargetUnk5;
    private javax.swing.JLabel lblTargetUnk8;
    private javax.swing.JLabel lblTargetUnk9;
    private javax.swing.JLabel lblTargetUnkA;
    private javax.swing.JLabel lblTargetUnkB;
    private javax.swing.JLabel lblTexInfoID;
    private javax.swing.JLabel lblTexInfoUnk0;
    private javax.swing.JLabel lblTexInfoUnk2;
    private javax.swing.JLabel lblTexInfoUnk4;
    private javax.swing.JLabel lblTexInfoUnk6;
    private javax.swing.JLabel lblWarpPosX;
    private javax.swing.JLabel lblWarpPosY;
    private javax.swing.JLabel lblWarpUnk4;
    private javax.swing.JLabel lblWarpUnk6;
    private javax.swing.JLabel lblWarpUnk8;
    private javax.swing.JList<String> listMons;
    private javax.swing.JList<String> listNpcs;
    private javax.swing.JList<String> listTargets;
    private javax.swing.JList<String> listTexInfo;
    private javax.swing.JList<String> listWarps;
    private javax.swing.JPanel pneMapInfo;
    private javax.swing.JPanel pneMonSettings;
    private javax.swing.JPanel pneMons;
    private javax.swing.JPanel pneNpcSettings;
    private javax.swing.JPanel pneNpcs;
    private javax.swing.JPanel pneTargetSettings;
    private javax.swing.JPanel pneTargets;
    private javax.swing.JPanel pneTexInfo;
    private javax.swing.JPanel pneTexInfoSettings;
    private javax.swing.JPanel pneWarpSettings;
    private javax.swing.JPanel pneWarps;
    private javax.swing.JButton saveAllButton;
    private javax.swing.JScrollPane scrMons;
    private javax.swing.JScrollPane scrNpcs;
    private javax.swing.JScrollPane scrTargets;
    private javax.swing.JScrollPane scrTexInfo;
    private javax.swing.JScrollPane scrWarps;
    private javax.swing.JSpinner spnMapInfoHeight;
    private javax.swing.JSpinner spnMapInfoUnk8;
    private javax.swing.JSpinner spnMapInfoUnkC;
    private javax.swing.JSpinner spnMapInfoWidth;
    private javax.swing.JSpinner spnMonDataBehavior;
    private javax.swing.JSpinner spnMonDataPosX;
    private javax.swing.JSpinner spnMonDataPosY;
    private javax.swing.JSpinner spnMonDataUnk5;
    private javax.swing.JSpinner spnMonDataUnk8;
    private javax.swing.JSpinner spnMonDataUnk9a;
    private javax.swing.JSpinner spnNpcID;
    private javax.swing.JSpinner spnNpcPosX;
    private javax.swing.JSpinner spnNpcPosY;
    private javax.swing.JSpinner spnNpcUnk8;
    private javax.swing.JSpinner spnNpcUnk9;
    private javax.swing.JSpinner spnSelectedLayer;
    private javax.swing.JSpinner spnTargetPosX;
    private javax.swing.JSpinner spnTargetPosY;
    private javax.swing.JSpinner spnTargetUnk4;
    private javax.swing.JSpinner spnTargetUnk5;
    private javax.swing.JSpinner spnTargetUnk8;
    private javax.swing.JSpinner spnTargetUnk9;
    private javax.swing.JSpinner spnTargetUnkA;
    private javax.swing.JSpinner spnTargetUnkB;
    private javax.swing.JSpinner spnTexInfoTextureID;
    private javax.swing.JSpinner spnTexInfoUnk0;
    private javax.swing.JSpinner spnTexInfoUnk2;
    private javax.swing.JSpinner spnTexInfoUnk4;
    private javax.swing.JSpinner spnTexInfoUnk6;
    private javax.swing.JSpinner spnWarpPosX;
    private javax.swing.JSpinner spnWarpPosY;
    private javax.swing.JSpinner spnWarpUnk4;
    private javax.swing.JSpinner spnWarpUnk6;
    private javax.swing.JSpinner spnWarpUnk8;
    private javax.swing.JTabbedPane tabPane;
    protected javax.swing.JToolBar toolbar;
    protected javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
}