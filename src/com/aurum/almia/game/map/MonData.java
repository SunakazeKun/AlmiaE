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
import com.aurum.almia.Resources;
import com.aurum.almia.game.param.PokeID;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MonData extends AbstractData<MonData.Entry> {
    public static final int HEADER_SIZE = 0x8;
    public static final int ENTRY_SIZE = 0xA;
    
    public class Entry extends AbstractEntry<MonData> {
        public short posX;
        public short posY;
        public short behavior;      // unsigned byte
        public short appearance;    // unsigned byte
        public int monID;           // unsigned short
        public byte unk8;
        public byte unk9;
        
        public Entry() {
            super(MonData.this);
            
            this.posX = 0;
            this.posY = 0;
            this.behavior = 0;
            this.appearance = 1;
            this.monID = 0;
            this.unk8 = 0;
            this.unk9 = 0;
        }
        
        @Override
        public String toString() {
            return layer.map.game.getPokemonList().get(monID);
        }
        
        @Override
        public void render(Graphics g) {
            PokeID.Entry monid = layer.map.game.monDataBase.entries.get(monID);
            BufferedImage img = Resources.getMonImg(monid.nameID, monid.formID);
            int x = posX - 48;
            int y = posY - 48;

            if (img == null)
                RenderHelper.drawBox(
                        g, posX, posY, 16, 16,
                        Color.BLACK, new Color(1f, 0f, 0f, 0.5f),
                        true
                );
            else
                g.drawImage(img, x, y, null);
        }
    }
    
    public MonData(Layer layer) {
        super(layer);
        
        this.entries = new ArrayList();
    }
    
    public MonData(Layer layer, ByteBuffer buf) {
        this(layer);
        
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        
        int entriesCount = buf.readInt();
        
        for (int i = 0 ; i < entriesCount ; i++) {
            Entry e = new Entry();
            
            e.posX = buf.readShort();
            e.posY = buf.readShort();
            e.behavior = buf.readUnsignedByte();
            e.appearance = buf.readUnsignedByte();
            e.monID = buf.readUnsignedShort();
            e.unk8 = buf.readByte();
            e.unk9 = buf.readByte();
            
            entries.add(e);
        }
    }
    
    @Override
    public String toString() {
        return "Pokémon";
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
            buf.writeUnsignedByte(e.behavior);
            buf.writeUnsignedByte(e.appearance);
            buf.writeUnsignedShort(e.monID);
            buf.writeByte(e.unk8);
            buf.writeByte(e.unk9);
        }
        
        return buf.getBuffer();
    }
    
    @Override
    public int getIdentifier() {
        return Layer.MDATA_MON;
    }
    
    @Override
    public void render(Graphics g) {
        for (Entry entry : entries)
            entry.render(g);
    }
}