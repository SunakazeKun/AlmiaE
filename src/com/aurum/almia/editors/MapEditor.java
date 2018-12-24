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

import com.aurum.almia.Main;
import com.aurum.almia.Resources;
import com.aurum.almia.game.map.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class MapEditor extends javax.swing.JFrame {
    private class MapRenderer extends JPanel implements Runnable {
        static final int FPS = 30;
        
        Thread thread;
        boolean isRunning;

        public MapRenderer() {
            update();
        }
        
        public void update() {
            setPreferredSize(new Dimension(map.mapInfo.width, map.mapInfo.height));
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                map.render(g);
            }
            catch (IOException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void run() {
            double timePerTick = 1000000000 / FPS;
            double delta = 0;
            long now = 0;
            long diff = 0;
            long last = System.nanoTime();
            long timer = 0;

            while(isRunning) {
                now = System.nanoTime();
                diff = now - last;
                timer += diff;
                delta += diff / timePerTick;

                if (delta >= 1) {
                    update();
                    delta--;
                }

                if (timer >= 1000000000) {
                    timer = 0;
                }
                
                last = now;
            }
        }

        public synchronized void start() {
            if (isRunning)
                return;
            
            isRunning = true;

            thread = new Thread(this);
            thread.start();
        }

        public synchronized void stop() {
            if (!isRunning)
                return;
            
            isRunning = false;

            try {
                thread.join();
            }
            catch(InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
    
    private class ObjTreeCellRenderer extends DefaultTreeCellRenderer {
        final Icon ICON_MISC     = Resources.loadIcon("mapEditor/node_misc.png");
        final Icon ICON_LOCATION = Resources.loadIcon("mapEditor/node_location.png");
        final Icon ICON_TARGET   = Resources.loadIcon("mapEditor/node_target.png");
        final Icon ICON_POKEMON  = Resources.loadIcon("mapEditor/node_mon.png");
        final Icon ICON_NPC      = Resources.loadIcon("mapEditor/node_npc.png");
        
        @Override
        public Component getTreeCellRendererComponent(JTree tree,
                Object value, boolean selected, boolean expanded,
                boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

            Object val = ((DefaultMutableTreeNode)value).getUserObject();

            if (val != null) {
                if (val instanceof MapInfo)                 setIcon(ICON_MISC);
                else if (val instanceof CollisionData)      setIcon(ICON_MISC);
                else if (val instanceof TextureInfo)        setIcon(ICON_MISC);
                else if (val instanceof TextureInfo.Entry)  setIcon(ICON_MISC);
                else if (val instanceof LocationData)       setIcon(ICON_LOCATION);
                else if (val instanceof LocationData.Entry) setIcon(ICON_LOCATION);
                else if (val instanceof TargetData)         setIcon(ICON_TARGET);
                else if (val instanceof TargetData.Entry)   setIcon(ICON_TARGET);
                else if (val instanceof MonData)            setIcon(ICON_POKEMON);
                else if (val instanceof MonData.Entry)      setIcon(ICON_POKEMON);
                else if (val instanceof NpcData)            setIcon(ICON_NPC);
                else if (val instanceof NpcData.Entry)      setIcon(ICON_NPC);
            }

            return this;
        }
    }
    
    private Map map;
    private Object selectedData;
    
    private JPanel editorWidget;
    private MapRenderer renderer;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode treeRoot;
    
    public MapEditor(Map map) {
        this.map = map;
        this.selectedData = null;
        this.editorWidget = null;
        this.renderer = new MapRenderer();
        this.treeModel = null;
        this.treeRoot = null;
        
        initComponents();
        initData();
    }
    
    public void initData() {
        setTitle(String.format("Map Editor: Editing %s", map.name));
        treeModel = (DefaultTreeModel)tree.getModel();
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        scrRender.getViewport().add(renderer);
        renderer.start();
        
        treeRoot = new DefaultMutableTreeNode(map.name, true);
        
        // Generic map info blocks
        DefaultMutableTreeNode mapInfoNode = new DefaultMutableTreeNode(map.mapInfo, false);
        DefaultMutableTreeNode texInfoNode = new DefaultMutableTreeNode(map.texInfo, true);
        
        for (TextureInfo.Entry entry : map.texInfo.entries) {
            texInfoNode.add(new DefaultMutableTreeNode(entry, false));
        }
        
        treeRoot.add(mapInfoNode);
        treeRoot.add(texInfoNode);
        
        // Layer object blocks
        for (Layer layer : map.layers) {
            DefaultMutableTreeNode layerNode = new DefaultMutableTreeNode(layer, true);
            DefaultMutableTreeNode collisionNode = new DefaultMutableTreeNode(layer.collisionData, false);
            DefaultMutableTreeNode collOverlayNode = new DefaultMutableTreeNode(layer.collOverlayData, false);
            DefaultMutableTreeNode locationsNode = new DefaultMutableTreeNode(layer.locationData, true);
            DefaultMutableTreeNode targetsNode = new DefaultMutableTreeNode(layer.targetData, true);
            DefaultMutableTreeNode monsNode = new DefaultMutableTreeNode(layer.monData, true);
            DefaultMutableTreeNode npcsNode = new DefaultMutableTreeNode(layer.npcData, true);
            
            for (LocationData.Entry entry : layer.locationData.entries)
                locationsNode.add(new DefaultMutableTreeNode(entry, false));
            
            for (TargetData.Entry entry : layer.targetData.entries)
                targetsNode.add(new DefaultMutableTreeNode(entry, false));
            
            for (MonData.Entry entry : layer.monData.entries)
                monsNode.add(new DefaultMutableTreeNode(entry, false));
            
            for (NpcData.Entry entry : layer.npcData.entries)
                npcsNode.add(new DefaultMutableTreeNode(entry, false));
            
            layerNode.add(collisionNode);
            layerNode.add(collOverlayNode);
            layerNode.add(locationsNode);
            layerNode.add(targetsNode);
            layerNode.add(monsNode);
            layerNode.add(npcsNode);
            
            treeRoot.add(layerNode);
        }
        
        treeModel.setRoot(treeRoot);
    }
    
    public DefaultMutableTreeNode getSelectedNode() {
        return (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
    }
    
    public Object getSelectedNodeObject() {
        DefaultMutableTreeNode node = getSelectedNode();
        return node == null ? null : node.getUserObject();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        treeMenu = new javax.swing.JPopupMenu();
        mnuAdd = new javax.swing.JMenuItem();
        mnuDelete = new javax.swing.JMenuItem();
        mnuClone = new javax.swing.JMenuItem();
        scrTree = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        scrRender = new javax.swing.JScrollPane();
        scrSettings = new javax.swing.JScrollPane();
        tlb1 = new javax.swing.JToolBar();
        btnSaveAll = new javax.swing.JButton();

        mnuAdd.setText("Add");
        mnuAdd.setToolTipText("");
        mnuAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAddActionPerformed(evt);
            }
        });
        treeMenu.add(mnuAdd);

        mnuDelete.setText("Delete");
        mnuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDeleteActionPerformed(evt);
            }
        });
        treeMenu.add(mnuDelete);

        mnuClone.setText("Copy");
        mnuClone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCloneActionPerformed(evt);
            }
        });
        treeMenu.add(mnuClone);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Map Editor: Editing <null>");
        setIconImage(Main.ICON);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        tree.setCellRenderer(new ObjTreeCellRenderer());
        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked(evt);
            }
        });
        tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treeValueChanged(evt);
            }
        });
        scrTree.setViewportView(tree);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        getContentPane().add(scrTree, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        getContentPane().add(scrRender, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 2);
        getContentPane().add(scrSettings, gridBagConstraints);

        tlb1.setFloatable(false);
        tlb1.setRollover(true);

        btnSaveAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/tl_save.png"))); // NOI18N
        btnSaveAll.setText("Save all data");
        btnSaveAll.setFocusable(false);
        btnSaveAll.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSaveAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSaveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAllActionPerformed(evt);
            }
        });
        tlb1.add(btnSaveAll);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(tlb1, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAllActionPerformed
        try {
            map.save();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnSaveAllActionPerformed

    private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeValueChanged
        if (editorWidget != null) {
            scrSettings.getViewport().remove(editorWidget);
            editorWidget = null;
            selectedData = null;
        }
        
        Object userObject = getSelectedNodeObject();
        
        if (userObject == null)
            return;
        
        if (userObject instanceof MapInfo)
            editorWidget = new MapInfoWidget((MapInfo)userObject);
        else if (userObject instanceof TextureInfo.Entry)
            editorWidget = new TexInfoWidget((TextureInfo.Entry)userObject);
        else if (userObject instanceof MonData.Entry)
            editorWidget = new MonWidget((MonData.Entry)userObject);
        else if (userObject instanceof NpcData.Entry)
            editorWidget = new NpcWidget((NpcData.Entry)userObject);
        else if (userObject instanceof LocationData.Entry)
            editorWidget = new LocationWidget((LocationData.Entry)userObject);
        else if (userObject instanceof TargetData.Entry)
            editorWidget = new TargetWidget((TargetData.Entry)userObject);
        else if (userObject instanceof CollisionData)
            editorWidget = new CollisionWidget((CollisionData)userObject);
        
        selectedData = userObject;
        
        if (editorWidget != null)
            scrSettings.getViewport().add(editorWidget);
        
        scrSettings.repaint();
    }//GEN-LAST:event_treeValueChanged

    private void treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseClicked
        if (!SwingUtilities.isRightMouseButton(evt))
            return;
        
        // Find the node
        int x = evt.getX();
        int y = evt.getY();
        TreePath path = tree.getPathForLocation(x, y);
        if (path == null)
            return;
        
        tree.setSelectionPath(path);
        
        Object userObject = getSelectedNodeObject();
        
        if (userObject == null)
            return;
        
        // Display operations
        if (userObject instanceof AbstractData) {
            mnuAdd.setEnabled(true);
            mnuDelete.setEnabled(false);
            mnuClone.setEnabled(false);
            treeMenu.show(evt.getComponent(), x, y);
        }
        else if (userObject instanceof AbstractEntry) {
            mnuAdd.setEnabled(false);
            mnuDelete.setEnabled(true);
            mnuClone.setEnabled(true);
            treeMenu.show(evt.getComponent(), x, y);
        }
    }//GEN-LAST:event_treeMouseClicked

    private void mnuAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAddActionPerformed
        // Get the node and object
        DefaultMutableTreeNode selected = getSelectedNode();
        if (selected == null)
            return;
        AbstractData data = (AbstractData)selected.getUserObject();
        if (data == null)
            return;
        
        // Create the entry
        AbstractEntry entry = null;
        
        if (data instanceof TextureInfo) entry = ((TextureInfo) data).new Entry();
        else if (data instanceof MonData) entry = ((MonData) data).new Entry();
        else if (data instanceof NpcData) entry = ((NpcData) data).new Entry();
        else if (data instanceof LocationData) entry = ((LocationData) data).new Entry();
        else if (data instanceof TargetData) entry = ((TargetData) data).new Entry();
        
        // Create the node, and add this and the data
        if (entry == null)
            return;
        
        data.entries.add(entry);
        
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(entry, false);
        treeModel.insertNodeInto(node, selected, selected.getChildCount());
        
        // Select new node
        TreePath path = new TreePath(treeModel.getPathToRoot(node));
        tree.setSelectionPath(path);
        tree.scrollPathToVisible(path);
    }//GEN-LAST:event_mnuAddActionPerformed

    private void mnuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDeleteActionPerformed
        // The code here is made for entry nodes only. Any folders or the root
        // will not work with this. Of course, they have been taken care of
        // earlier already (treeMouseClicked event), so nothing should go wrong
        // in theory.
        
        // Get the node, object and parent
        DefaultMutableTreeNode selected = getSelectedNode();
        if (selected == null)
            return;
        AbstractEntry entry = (AbstractEntry)selected.getUserObject();
        AbstractData data = (AbstractData)((DefaultMutableTreeNode)selected.getParent()).getUserObject();
        
        // Delete the node and data
        treeModel.removeNodeFromParent(selected);
        
        if (data == null || entry == null)
            return;
        
        data.entries.remove(entry);
    }//GEN-LAST:event_mnuDeleteActionPerformed

    private void mnuCloneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCloneActionPerformed
        // Similar to the node deletion code, as written above. They are used
        // in the same context, which is not very suprising...

        // Get the node, object and parent
        DefaultMutableTreeNode selected = getSelectedNode();
        if (selected == null)
            return;
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selected.getParent();
        AbstractEntry entry = (AbstractEntry)selected.getUserObject();
        AbstractData data = (AbstractData)parent.getUserObject();
        if (data == null || entry == null)
            return;
        
        // Create the entry and node, and add them as well
        AbstractEntry clone = entry.clone();
        if (clone == null)
            return;
        
        data.entries.add(clone);
        
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(clone, false);
        treeModel.insertNodeInto(node, parent, parent.getChildCount());
        
        // Select new node
        TreePath path = new TreePath(treeModel.getPathToRoot(node));
        tree.setSelectionPath(path);
        tree.scrollPathToVisible(path);
    }//GEN-LAST:event_mnuCloneActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        renderer.stop();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSaveAll;
    private javax.swing.JMenuItem mnuAdd;
    private javax.swing.JMenuItem mnuClone;
    private javax.swing.JMenuItem mnuDelete;
    private javax.swing.JScrollPane scrRender;
    private javax.swing.JScrollPane scrSettings;
    private javax.swing.JScrollPane scrTree;
    private javax.swing.JToolBar tlb1;
    private javax.swing.JTree tree;
    private javax.swing.JPopupMenu treeMenu;
    // End of variables declaration//GEN-END:variables
}