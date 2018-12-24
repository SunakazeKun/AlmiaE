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
import java.nio.ByteOrder;
import com.aurum.almia.util.RenderHelper;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class LocationData extends AbstractData<LocationData.Entry> {
    public static final int HEADER_SIZE = 0x8;
    public static final int ENTRY_SIZE = 0xA;
    
    public class Entry extends AbstractEntry<LocationData> {
        public short posX;
        public short posY;
        public int width;
        public int height;
        public short unk8;
        
        public Entry() {
            super(LocationData.this);
            
            this.posX = 0;
            this.posY = 0;
            this.width = 16;
            this.height = 16;
            this.unk8 = 0;
        }
        
        @Override
        public String toString() {
            return String.format("(%d, %d)", posX, posY);
        }
        
        @Override
        public void render(Graphics g) {
            RenderHelper.drawBox(
                    g, posX, posY, width, height, 
                    Color.BLACK, new Color(0f, 0f, 1f, 0.5f), 
                    false
            );
        }
    }
    
    public LocationData(Layer layer) {
        super(layer);
        
        this.entries = new ArrayList();
    }
    
    public LocationData(Layer layer, ByteBuffer buf) {
        this(layer);
        
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        
        int entriesCount = buf.readInt();
        
        for (int i = 0 ; i < entriesCount ; i++) {
            Entry e = new Entry();
            
            e.posX = buf.readShort();
            e.posY = buf.readShort();
            e.width = buf.readUnsignedShort();
            e.height = buf.readUnsignedShort();
            e.unk8 = buf.readShort();
            
            entries.add(e);
        }
    }
    
    @Override
    public String toString() {
        return "Locations";
    }
    
    @Override
    public byte[] pack() {
        int totalsize = HEADER_SIZE + entries.size() * ENTRY_SIZE;
        
        ByteBuffer buf = new ByteBuffer(totalsize, ByteOrder.LITTLE_ENDIAN);
        
        buf.writeInt(getIdentifier());
        buf.writeInt(entries.size());
        
        for (Entry e : entries) {
            buf.writeShort(e.posX);
            buf.writeShort(e.posY);
            buf.writeUnsignedShort(e.width);
            buf.writeUnsignedShort(e.height);
            buf.writeShort(e.unk8);
        }
        
        return buf.getBuffer();
    }
    
    @Override
    public int getIdentifier() {
        return Layer.MDATA_LOCATION;
    }
    
    @Override
    public void render(Graphics g) {
        for (Entry entry : entries)
            entry.render(g);
    }
}