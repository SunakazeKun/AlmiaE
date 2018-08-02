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

import com.aurum.almia.ByteBuffer;
import com.aurum.almia.ByteOrder;
import com.aurum.almia.Utils;
import com.aurum.almia.game.Game;
import com.aurum.almia.game.NARC;
import com.aurum.almia.game.NARC.FimgEntry;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {
    public static final int MAP_MPIF = 0x4D504946;
    public static final int MAP_TXIF = 0x54584946;
    public static final int MAP_LYR = 0x4C595200;
    public static final int MAP_CTA = 0x43544100;
    public static final int MAP_PLA = 0x504C4100;
    
    //--------------------------------------------------------------------------
    
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
        
        ByteBuffer buf = Utils.loadFileIntoBuffer(game.getFullPath(String.format("field/map/%s.map.dat.lz", name)));
        NARC root = new NARC(buf);
        buf.clear();
        
        for (FimgEntry file : root.files) {
            buf = new ByteBuffer(file.data, ByteOrder.BIG_ENDIAN);
            
            int dataMagic = buf.readInt();
            
            buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
            
            switch(dataMagic) {
                case MAP_MPIF: mapInfo = new MapInfo(this, buf); break;
                case MAP_TXIF: texInfo = new TextureInfo(this, buf); break;
                case MAP_CTA: cta = buf.readRemaining(); break;
                case MAP_PLA: pla = buf.readRemaining(); break;
                case MAP_LYR: {
                    NARC lyr = new NARC(new ByteBuffer(buf.readRemaining()));
                    
                    for (FimgEntry lyrfile : lyr.files) {
                        layers.add(new Layer(this, new NARC(new ByteBuffer(lyrfile.data))));
                    }
                } break;
            }
        }
    }
    
    public byte[] pack() {
        NARC root = new NARC();
        
        root.addFile(mapInfo.pack());
        root.addFile(texInfo.pack());
        
        
        NARC lyr = new NARC();
        
        for (Layer layer : layers) {
            lyr.addFile(layer.pack());
        }
        
        byte[] lyrData = lyr.pack();
        ByteBuffer lyrBuf = new ByteBuffer(0x4 + lyrData.length);
        
        lyrBuf.writeInt(MAP_LYR);
        lyrBuf.writeBytes(lyrData);
        
        root.addFile(lyrBuf.getBuffer());
        
        lyrBuf.clear();
        
        
        if (cta != null) {
            ByteBuffer ctaBuf = new ByteBuffer(0x4 + cta.length);
            ctaBuf.writeInt(MAP_CTA);
            ctaBuf.writeBytes(cta);
            root.addFile(ctaBuf.getBuffer());
        }
        
        
        if (pla != null) {
            ByteBuffer plaBuf = new ByteBuffer(0x4 + pla.length);
            plaBuf.writeInt(MAP_PLA);
            plaBuf.writeBytes(pla);
            root.addFile(plaBuf.getBuffer());
        }
        
        return root.pack();
    }
    
    public void save() throws IOException {
        Utils.saveBytesToFile(pack(), new File(game.getFullPath(String.format("field/map/%s.map.dat.lz", name))));
    }
}