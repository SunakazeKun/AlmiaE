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
import com.aurum.almia.Lists;
import static com.aurum.almia.game.map.Layer.MDATA_TARGET;
import java.util.ArrayList;
import java.util.List;

public class TargetData {
    public static final int HEADER_SIZE = 0x8;
    public static final int ENTRY_SIZE = 0xC;
    
    //--------------------------------------------------------------------------
    
    public class Entry implements Cloneable {
        public short posX;
        public short posY;
        public byte unk4;
        public byte unk5;
        public int obstacleID;  // unsigned short
        public byte unk8;
        public byte unk9;
        public byte unkA;
        public byte unkB;
        
        public Entry() {
            this.posX = 0;
            this.posY = 0;
            this.unk4 = 0;
            this.unk5 = 0;
            this.obstacleID = 0;
            this.unk8 = 0;
            this.unk9 = 0;
            this.unkA = 0;
            this.unkB = 0;
        }
        
        @Override
        public String toString() {
            return String.format("%03X: %s", obstacleID, Lists.obstacles.get(obstacleID));
        }
    }
    
    //--------------------------------------------------------------------------
    
    public Layer layer;
    public List<Entry> entries;
    
    //--------------------------------------------------------------------------
    
    public TargetData(Layer layer) {
        this.layer = layer;
        this.entries = new ArrayList();
    }
    
    public TargetData(Layer layer, ByteBuffer buf) {
        this(layer);
        
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        
        int entriesCount = buf.readInt();
        
        for (int i = 0 ; i < entriesCount ; i++) {
            Entry e = new Entry();
            
            e.posX = buf.readShort();
            e.posY = buf.readShort();
            e.unk4 = buf.readByte();
            e.unk5 = buf.readByte();
            e.obstacleID = buf.readUnsignedShort();
            e.unk8 = buf.readByte();
            e.unk9 = buf.readByte();
            e.unkA = buf.readByte();
            e.unkB = buf.readByte();
            
            entries.add(e);
        }
    }
    
    public byte[] pack() {
        int totalsize = HEADER_SIZE + entries.size() * ENTRY_SIZE;
        
        ByteBuffer buf = new ByteBuffer(totalsize, ByteOrder.LITTLE_ENDIAN);
        
        buf.writeInt(MDATA_TARGET);
        buf.writeInt(entries.size());
        
        for (Entry e : entries) {
            buf.writeShort(e.posX);
            buf.writeShort(e.posY);
            buf.writeByte(e.unk4);
            buf.writeByte(e.unk5);
            buf.writeUnsignedShort(e.obstacleID);
            buf.writeByte(e.unk8);
            buf.writeByte(e.unk9);
            buf.writeByte(e.unkA);
            buf.writeByte(e.unkB);
        }
        
        return buf.getBuffer();
    }
}