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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class NpcData extends AbstractData<NpcData.Entry> {
    public static final int HEADER_SIZE = 0x8;
    public static final int ENTRY_SIZE = 0xB;
    
    public class Entry extends AbstractEntry<NpcData> {
        public short posX;
        public short posY;
        public short direction;     // unsigned byte
        public boolean isVisible;
        public int npcID;           // unsigned short
        public byte unk8;
        public byte unk9;
        public boolean unkA;
        
        public Entry() {
            super(NpcData.this);
            
            this.posX = 0;
            this.posY = 0;
            this.direction = 0;
            this.isVisible = true;
            this.npcID = 0;
            this.unk8 = 0;
            this.unk9 = 0;
            this.unkA = false;
        }
        
        @Override
        public String toString() {
            return layer.map.game.getNpcList().get(npcID);
        }
        
        @Override
        public void render(Graphics g) {
            BufferedImage img = Resources.getNpcImg(npcID);
            
            if (img == null)
                RenderHelper.drawBox(
                        g, posX, posY, 16, 16,
                        Color.BLACK, new Color(1f, 1f, 0f, 0.5f),
                        true
                );
            else {
                img = img.getSubimage((img.getWidth() / 4) * (direction % 0x3), 0, img.getWidth() / 4, img.getHeight());

                g.drawImage(img, posX - (img.getWidth() / 2), posY - (img.getHeight() / 2), null);
            }
        }
    }
    
    public NpcData(Layer layer) {
        super(layer);
        
        this.entries = new ArrayList();
    }
    
    public NpcData(Layer layer, ByteBuffer buf) {
        this(layer);
        
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        
        int entriesCount = buf.readInt();
        
        for (int i = 0 ; i < entriesCount ; i++) {
            Entry e = new Entry();
            
            e.posX = buf.readShort();
            e.posY = buf.readShort();
            e.direction = buf.readUnsignedByte();
            e.isVisible = buf.readBoolean();
            e.npcID = buf.readUnsignedShort();
            e.unk8 = buf.readByte();
            e.unk9 = buf.readByte();
            e.unkA = buf.readBoolean();
            
            entries.add(e);
        }
    }
    
    @Override
    public String toString() {
        return "NPCs";
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
            buf.writeUnsignedByte(e.direction);
            buf.writeBoolean(e.isVisible);
            buf.writeUnsignedShort(e.npcID);
            buf.writeByte(e.unk8);
            buf.writeByte(e.unk9);
            buf.writeBoolean(e.unkA);
        }
        
        return buf.getBuffer();
    }
    
    @Override
    public int getIdentifier() {
        return Layer.MDATA_NPC;
    }
    
    @Override
    public void render(Graphics g) {
        for (Entry entry : entries)
            entry.render(g);
    }
}