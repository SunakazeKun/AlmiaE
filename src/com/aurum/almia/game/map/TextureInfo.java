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
import java.awt.Graphics;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class TextureInfo extends AbstractData<TextureInfo.Entry> {
    public static final int HEADER_SIZE = 0x8;
    public static final int ENTRY_SIZE = 0x8;
    
    public class Entry extends AbstractEntry<TextureInfo> {
        public short unk0;
        public int textureID;   // unsigned short
        public short unk4;
        public short unk6;
        
        public Entry() {
            super(TextureInfo.this);
            
            this.unk0 = 1;
            this.textureID = 0;
            this.unk4 = 0;
            this.unk6 = 0;
        }
        
        @Override
        public String toString() {
            return Integer.toString(textureID);
        }
    }
    
    public final Map map;
    public short unk2;
    
    public TextureInfo(Map map) {
        super(null);
        
        this.map = map;
        this.entries = new ArrayList();
        this.unk2 = 0;
    }
    
    public TextureInfo(Map map, ByteBuffer buf) {
        this(map);
        
        int numEntries = buf.readUnsignedShort();
        this.unk2 = buf.readShort();

        for (int i = 0 ; i < numEntries ; i++) {
            Entry e = new Entry();
            
            e.unk0 = buf.readShort();
            e.textureID = buf.readUnsignedShort();
            e.unk4 = buf.readShort();
            e.unk6 = buf.readShort();
            
            entries.add(e);
        }
    }
    
    @Override
    public String toString() {
        return "Texture Info";
    }
    
    @Override
    public byte[] pack() {
        ByteBuffer buf = new ByteBuffer(HEADER_SIZE + entries.size() * ENTRY_SIZE);
        
        buf.writeMagic(Map.MAP_TXIF);
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        buf.writeUnsignedShort(entries.size());
        buf.writeShort(unk2);

        for (Entry e : entries) {
            buf.writeShort(e.unk0);
            buf.writeUnsignedShort(e.textureID);
            buf.writeShort(e.unk4);
            buf.writeShort(e.unk6);
        }
        
        return buf.getBuffer();
    }
    
    @Override
    public int getIdentifier() {
        return -1;
    }
    
    @Override
    public void render(Graphics g) {}
}