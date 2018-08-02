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

package com.aurum.almia;

import com.aurum.almia.game.Game;
import com.aurum.almia.game.map.Map;
import com.aurum.almia.swing.MapEditor;
import com.aurum.almia.swing.PokeIDEditor;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main extends javax.swing.JFrame {
    public static String name = "AlmiaE v0.1";
    public static Image icon = Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/res/icon.png"));
    public static boolean debug = false;
    
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            debug = args[0].equals("-d");
        }
        
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.print(ex);
        }
        
        Lists.init();
        
        new Main().setVisible(true);
    }
    
    public Main() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMapEditor = new javax.swing.JButton();
        btnPokemonEditor = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuOpen = new javax.swing.JMenuItem();
        sep1 = new javax.swing.JPopupMenu.Separator();
        mnuClose = new javax.swing.JMenuItem();
        mnuTools = new javax.swing.JMenu();
        mnuHelp = new javax.swing.JMenu();
        mnuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Main.name);
        setIconImage(Main.icon);
        setResizable(false);

        btnMapEditor.setText("Edit map");
        btnMapEditor.setEnabled(false);
        btnMapEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapEditorActionPerformed(evt);
            }
        });

        btnPokemonEditor.setText("Edit Pokémon data");
        btnPokemonEditor.setEnabled(false);
        btnPokemonEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokemonEditorActionPerformed(evt);
            }
        });

        mnuFile.setMnemonic('F');
        mnuFile.setText("File");

        mnuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnuOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/tl_open.png"))); // NOI18N
        mnuOpen.setMnemonic('O');
        mnuOpen.setText("Open");
        mnuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpenActionPerformed(evt);
            }
        });
        mnuFile.add(mnuOpen);
        mnuFile.add(sep1);

        mnuClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/tl_cancel.png"))); // NOI18N
        mnuClose.setMnemonic('C');
        mnuClose.setText("Exit");
        mnuClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCloseActionPerformed(evt);
            }
        });
        mnuFile.add(mnuClose);

        jMenuBar1.add(mnuFile);

        mnuTools.setMnemonic('T');
        mnuTools.setText("Tools");
        mnuTools.setEnabled(false);
        jMenuBar1.add(mnuTools);

        mnuHelp.setMnemonic('H');
        mnuHelp.setText("Help");

        mnuAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/tl_info.png"))); // NOI18N
        mnuAbout.setMnemonic('A');
        mnuAbout.setText("About");
        mnuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAboutActionPerformed(evt);
            }
        });
        mnuHelp.add(mnuAbout);

        jMenuBar1.add(mnuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPokemonEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMapEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPokemonEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMapEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpenActionPerformed
        final JFileChooser fc = new JFileChooser() {{
            setDialogTitle("Select extracted filesystem");
            setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            setAcceptAllFileFilterUsed(false);
        }};
        
        String lastdir = Preferences.userRoot().get("almiaE_lastDir", null);
        if (lastdir != null)
            fc.setCurrentDirectory(new File(lastdir));
        if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
            return;
        
        File file = fc.getSelectedFile();
        if (!(file.exists() && file.isDirectory()))
            return;
        
        Preferences.userRoot().put("almiaE_lastDir", file.getPath());
        
        boolean enableComponents = false;
        
        try {
            Game.current = new Game(file);
            Game.current.init();
            
            enableComponents = true;
        }
        catch (IOException ex) {
            enableComponents = false;
            System.err.print(ex);
        }
        
        btnPokemonEditor.setEnabled(enableComponents);
        btnMapEditor.setEnabled(enableComponents);
    }//GEN-LAST:event_mnuOpenActionPerformed

    private void mnuCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCloseActionPerformed
        dispose();
    }//GEN-LAST:event_mnuCloseActionPerformed

    private void mnuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAboutActionPerformed
        JOptionPane.showMessageDialog(this, "Copyright 2018 SunakazeKun\n--Github link--", Main.name, JOptionPane.PLAIN_MESSAGE, null);
    }//GEN-LAST:event_mnuAboutActionPerformed

    private void btnPokemonEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokemonEditorActionPerformed
        new PokeIDEditor(Game.current.monDataBase).setVisible(true);
    }//GEN-LAST:event_btnPokemonEditorActionPerformed

    private void btnMapEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapEditorActionPerformed
        Object[] maps = getMaps().toArray();
        if (maps.length == 0) return;
        
        String mapname = (String) JOptionPane.showInputDialog(this, "Select a map file.", Main.name, JOptionPane.PLAIN_MESSAGE, null, maps, maps[0]);
        if (mapname == null) return;
        if (mapname.isEmpty()) return;
        
        
        try {
            Map map = new Map(Game.current, mapname);
            new MapEditor(map).setVisible(true);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnMapEditorActionPerformed
    
    public List<String> getMaps() {
        File[] files = new File(Game.current.filesystem.getAbsolutePath() + "/field/map").listFiles();
        List<String> maps = new ArrayList();
        
        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }
            
            String filename = file.getName();
            
            if (filename.endsWith(".map.dat.lz")) {
                maps.add(filename.replace(".map.dat.lz", ""));
            }
        }
        
        return maps;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMapEditor;
    private javax.swing.JButton btnPokemonEditor;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mnuAbout;
    private javax.swing.JMenuItem mnuClose;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenuItem mnuOpen;
    private javax.swing.JMenu mnuTools;
    private javax.swing.JPopupMenu.Separator sep1;
    // End of variables declaration//GEN-END:variables
}