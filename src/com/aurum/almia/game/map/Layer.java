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

import com.aurum.almia.util.ByteBuffer;
import com.aurum.almia.game.Narc;
import com.aurum.almia.game.Narc.NarcFile;
import java.awt.Graphics;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class Layer {
    public static final int MDATA_01           = 0x01;
    public static final int MDATA_TILES        = 0x02;
    public static final int MDATA_COLLISION    = 0x03;
    public static final int MDATA_TARGET       = 0x04;
    public static final int MDATA_UNKNOWN      = 0x05; // unused
    public static final int MDATA_COLL_OVERLAY = 0x06;
    public static final int MDATA_LOCATION     = 0x07;
    public static final int MDATA_NPC          = 0x08;
    public static final int MDATA_MON          = 0x09;
    public static final int MDATA_0A           = 0x0A;
    public static final int MDATA_0B           = 0x0B;
    public static final int MDATA_0C           = 0x0C;
    public static final int MDATA_0D           = 0x0D;
    public static final int MDATA_0E           = 0x0E;
    
    public final Map map;
    public List<byte[]> lyrUnk;
    public CollisionData collisionData;
    public TargetData targetData;
    public CollOverlayData collOverlayData;
    public LocationData locationData;
    public NpcData npcData;
    public MonData monData;
    
    public Layer(Map map) {
        this.map = map;
        this.lyrUnk = new ArrayList();
        this.collisionData = new CollisionData(this);
        this.targetData = new TargetData(this);
        this.collOverlayData = new CollOverlayData(this);
        this.locationData = new LocationData(this);
        this.npcData = new NpcData(this);
        this.monData = new MonData(this);
    }
    
    public Layer(Map map, Narc narc) {
        this(map);
        
        ByteBuffer buf;
        
        for (NarcFile file : narc.files) {
            buf = new ByteBuffer(file.data, ByteOrder.LITTLE_ENDIAN);
            int identifier = buf.readInt();

            switch(identifier) {
                case MDATA_COLLISION: collisionData = new CollisionData(this, buf); break;
                case MDATA_TARGET: targetData = new TargetData(this, buf); break;
                case MDATA_COLL_OVERLAY: collOverlayData = new CollOverlayData(this, buf); break;
                case MDATA_LOCATION: locationData = new LocationData(this, buf); break;
                case MDATA_NPC: npcData = new NpcData(this, buf); break;
                case MDATA_MON: monData = new MonData(this, buf); break;
                default: System.out.println(String.format("Unknown map format %02X, %d", identifier, buf.size())); lyrUnk.add(buf.getBuffer());
            }
        }
    }
    
    @Override
    public String toString() {
        return "Layer";
    }
    
    public byte[] pack() {
        Narc narc = new Narc();
        
        for (byte[] unk : lyrUnk)
            narc.addFile(unk);
        
        narc.addFile(collisionData.pack());
        narc.addFile(targetData.pack());
        narc.addFile(collOverlayData.pack());
        narc.addFile(locationData.pack());
        narc.addFile(npcData.pack());
        narc.addFile(monData.pack());
        
        return narc.pack();
    }
    
    public void render(Graphics g) {
        //collisionData.render(g);
        //collOverlayData.render(g);
        locationData.render(g);
        targetData.render(g);
        npcData.render(g);
        monData.render(g);
    }
}