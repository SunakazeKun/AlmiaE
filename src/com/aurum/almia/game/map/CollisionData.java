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

import com.aurum.almia.util.RenderHelper;
import com.aurum.almia.util.ByteBuffer;
import java.awt.Color;
import java.awt.Graphics;
import java.nio.ByteOrder;

public class CollisionData extends AbstractData {
    public static final int HEADER_SIZE = 0x14;
    public static final int ENTRY_SIZE = 0x2;
    
    public int width;
    public int height;
    public int[][] entries;
    
    public CollisionData(Layer layer) {
        super(layer);
        
        this.width = 0;
        this.height = 0;
        this.entries = null;
    }
    
    public CollisionData(Layer layer, ByteBuffer buf) {
        this(layer);
        
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        
        int headerSize = buf.readInt();
        int entriesCount = buf.readInt();
        this.width = buf.readInt();
        this.height = buf.readInt();
        this.entries = new int[height][width];
        
        if (width * height != entriesCount)
            System.out.println("CollisionData: Warning! Calculated entry count does not equal written count.");
        
        for (int y = 0; y < height ; y++) {
            for (int x = 0; x < width ; x++) {
                entries[y][x] = buf.readUnsignedShort();
            }
        }
    }
    
    @Override
    public String toString() {
        return "Collision";
    }
    
    @Override
    public byte[] pack() {
        int entriesCount = width * height;
        int totalsize = HEADER_SIZE + entriesCount * ENTRY_SIZE;
        
        ByteBuffer buf = new ByteBuffer(totalsize, ByteOrder.LITTLE_ENDIAN);
        
        buf.writeInt(getIdentifier());
        buf.writeInt(HEADER_SIZE - 0x4);    // header size without identifier
        buf.writeInt(entriesCount);
        buf.writeInt(width);
        buf.writeInt(height);
        
        for (int y = 0; y < height ; y++) {
            for (int x = 0; x < width ; x++)
                buf.writeUnsignedShort(entries[y][x]);
        }
        
        return buf.getBuffer();
    }
    
    @Override
    public int getIdentifier() {
        return Layer.MDATA_COLLISION;
    }
    
    @Override
    public void render(Graphics g) {
        for (int y = 0 ; y < height ; y++) {
            for (int x = 0 ; x < width ; x++)
                RenderHelper.drawBox(
                        g, x * 16, 
                        y * 16, 16, 16, 
                        Color.GRAY, 
                        new Color(Integer.rotateLeft(0x595A9556, entries[y][x])), 
                        false
                );
        }
    }
}