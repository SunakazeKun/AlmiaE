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

package com.aurum.almia.game.map;

import com.aurum.almia.game.Compression;
import com.aurum.almia.util.ByteBuffer;
import java.nio.ByteOrder;
import com.aurum.almia.game.Game;
import com.aurum.almia.game.Narc;
import com.aurum.almia.game.Narc.NarcFile;
import com.aurum.almia.util.IOHelper;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {
    public static final String MAP_MPIF = "MPIF";
    public static final String MAP_TXIF = "TXIF";
    public static final String MAP_LYR  = "LYR\0";
    public static final String MAP_CTA  = "CTA\0";
    public static final String MAP_PLA  = "PLA\0";
    
    public Game game;
    public String name;
    public MapInfo mapInfo;
    public TextureInfo texInfo;
    public List<Layer> layers;
    public byte[] cta;
    public byte[] pla;
    
    public Map(Game game, String name) throws IOException {
        this.game = game;
        this.name = name;
        this.mapInfo = new MapInfo(this);
        this.texInfo = new TextureInfo(this);
        this.layers = new ArrayList();
        this.cta = null;
        this.pla = null;
        
        Narc root = new Narc(IOHelper.read(game.getFile(String.format("field/map/%s.map.dat.lz", name))));
        
        for (NarcFile file : root.files) {
            ByteBuffer buf = new ByteBuffer(file.data, ByteOrder.LITTLE_ENDIAN);
            String dataMagic = buf.readMagic();
            
            switch(dataMagic) {
                case MAP_MPIF: mapInfo = new MapInfo(this, buf); break;
                case MAP_TXIF: texInfo = new TextureInfo(this, buf); break;
                case MAP_CTA: cta = file.data; break;
                case MAP_PLA: pla = file.data; break;
                case MAP_LYR: {
                    Narc lyr = new Narc(buf.readRemaining());
                    
                    for (NarcFile lyrfile : lyr.files)
                        layers.add(new Layer(this, new Narc(lyrfile.data)));
                } break;
            }
        }
    }
    
    public byte[] pack() {
        Narc root = new Narc();
        
        root.addFile(mapInfo.pack());
        root.addFile(texInfo.pack());
        
        
        Narc lyr = new Narc();
        
        for (Layer layer : layers)
            lyr.addFile(layer.pack());
        
        byte[] lyrData = lyr.pack();
        ByteBuffer lyrBuf = new ByteBuffer(0x4 + lyrData.length);
        
        lyrBuf.writeMagic(MAP_LYR);
        lyrBuf.writeBytes(lyrData);
        
        root.addFile(lyrBuf.getBuffer());
        
        
        if (cta != null)
            root.addFile(cta);
        
        if (pla != null)
            root.addFile(pla);
        
        return Compression.compress(
                root.pack(),
                ByteOrder.LITTLE_ENDIAN,
                Compression.LZ10
        );
    }
    
    public void save() throws IOException {
        IOHelper.write(
                pack(),
                game.getFile(String.format("field/map/%s.map.dat.lz", name))
        );
    }
    
    public void render(Graphics g) throws IOException {
        g.setColor(Color.white);
        g.fillRect(0, 0, mapInfo.width, mapInfo.height);
        
        // Draw grid
        int gridlX = (mapInfo.width + 16) / 16;
        int gridlY = (mapInfo.height + 16) / 16;
        
        g.setColor(Color.lightGray);
        
        for (int i = 0 ; i < gridlX ; i++)
            g.drawLine(i * 16, 0, i * 16, mapInfo.height - 1);
        
        for (int i = 0 ; i < gridlY ; i++)
            g.drawLine(0, i * 16, mapInfo.width - 1, i * 16);
        
        // Draw layers
        for (Layer layer : layers)
            layer.render(g);
    }
}