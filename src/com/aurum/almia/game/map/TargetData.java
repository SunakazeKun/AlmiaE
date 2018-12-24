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

public class TargetData extends AbstractData<TargetData.Entry> {
    public static final int HEADER_SIZE = 0x8;
    public static final int ENTRY_SIZE = 0xC;
    
    public class Entry extends AbstractEntry<TargetData> {
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
            super(TargetData.this);
            
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
            return layer.map.game.getTargetList().get(obstacleID);
        }
        
        @Override
        public void render(Graphics g) {
            BufferedImage img = Resources.getTargetImg(obstacleID);
            
            if (img == null)
                RenderHelper.drawBox(
                        g, posX, posY, 16, 16,
                        Color.BLACK, new Color(0f, 1f, 0f, 0.5f),
                        true
                );
            else
                g.drawImage(img, posX - (img.getWidth() / 2), posY - (img.getHeight() / 2), null);
        }
    }
    
    public TargetData(Layer layer) {
        super(layer);
        
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
    
    @Override
    public String toString() {
        return "Targets";
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
    
    @Override
    public int getIdentifier() {
        return Layer.MDATA_TARGET;
    }
    
    @Override
    public void render(Graphics g) {
        for (Entry entry : entries)
            entry.render(g);
    }
}