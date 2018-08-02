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

package com.aurum.almia.game;

import com.aurum.almia.ByteBuffer;
import com.aurum.almia.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class NARC {
    public static final int NARC_MAGIC = 0x4E415243;
    public static final int FATB_MAGIC = 0x46415442;
    public static final int FNTB_MAGIC = 0x464E5442;
    public static final int FIMG_MAGIC = 0x46494D47;
    
    //--------------------------------------------------------------------------
    
    private class FatbEntry {
        public int startOffset;
        public int endOffset;
    }
    
    public class FimgEntry {
        public String name;
        public byte[] data;
        
        public FimgEntry() {
            this.name = "Unnamed";
            this.data = null;
        }
    }
    
    //--------------------------------------------------------------------------
    
    public List<FimgEntry> files;
    
    //--------------------------------------------------------------------------
    
    public NARC() {
        this.files = new ArrayList();
    }
    
    public NARC(ByteBuffer buf) {
        this();
        
        buf.setEndianness(ByteOrder.BIG_ENDIAN);
        
        
        int fileMagic = buf.readInt();
        if (fileMagic != NARC_MAGIC) {
            throw new IllegalArgumentException("NARC: No NARC data found.");
        }
        short endianMark = buf.readShort();
        if ((endianMark & 0xFFFF) == 0xFEFF) {
            buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        }
        short unkConst = buf.readShort();
        int totalSize = buf.readInt();
        if (buf.size() != totalSize) {
            System.out.println("NARC: Warning! Stored size does not equal actual buffer size!");
        }
        int headerSize = buf.readShort();
        int numSections = buf.readShort();
        
        
        int fatbPos = buf.position();
        int fatbMagic = buf.readInt();
        int fatbSize = buf.readInt();
        int fatbFiles = buf.readInt();
        FatbEntry[] fatbEntries = new FatbEntry[fatbFiles];
        for (int i = 0 ; i < fatbEntries.length ; i++) {
            FatbEntry e = new FatbEntry();
            e.startOffset = buf.readInt();
            e.endOffset = buf.readInt();
            fatbEntries[i] = e;
        }
        
        
        // this needs revision, really...
        int fntbPos = buf.position();
        int fntbMagic = buf.readInt();
        int fntbSize = buf.readInt();
        buf.skip(fntbSize - 0x8);
        
        
        int fimgPos = buf.position();
        int fimgMagic = buf.readInt();
        int fimgSize = buf.readInt();
        
        for (int i = 0 ; i < fatbFiles ; i++) {
            FimgEntry e = new FimgEntry();
            FatbEntry fatb = fatbEntries[i];
            
            buf.seek(fimgPos + 0x8 + fatb.startOffset);
            e.data = buf.readBytes(fatb.endOffset - fatb.startOffset);
            
            files.add(e);
        }
    }
    
    public byte[] pack() {
        int headerSize = 0x10;
        int fatbSize = 0xC + files.size() * 0x8;
        int fntbSize = 0x10;
        int fimgSize = 0x8;
        
        for (FimgEntry file : files) {
            fimgSize += file.data.length;
        }
        
        int totalsize = headerSize + fatbSize + fntbSize + fimgSize;
        
        
        ByteBuffer buf = new ByteBuffer(totalsize, ByteOrder.BIG_ENDIAN);
        
        
        buf.writeInt(NARC_MAGIC);
        buf.writeShort((short)0xFEFF);
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        buf.writeShort((short)0x0100);
        buf.writeInt(totalsize);
        buf.writeShort((short)headerSize);
        buf.writeShort((short)0x3);
        
        
        int fatbPos = buf.position();
        buf.writeInt(FATB_MAGIC);
        buf.writeInt(fatbSize);
        buf.writeInt(files.size());
        buf.skip(files.size() * 0x8);
        
        
        int fntbPos = buf.position();
        buf.writeInt(FNTB_MAGIC);
        buf.writeInt(fntbSize);
        buf.writeInt(0x4);
        buf.writeInt(0x10000);
        
        
        int fimgPos = buf.position();
        buf.writeInt(FIMG_MAGIC);
        buf.writeInt(fimgSize);
        
        for (int i = 0 ; i < files.size() ; i++) {
            FimgEntry file = files.get(i);
            
            int inFimgPos = buf.position();
            int startPos = inFimgPos - fimgPos - 0x8;
            int endPos = startPos + file.data.length;
            buf.seek(fatbPos + 0xC + i * 0x8);
            buf.writeInt(startPos);
            buf.writeInt(endPos);
            buf.seek(inFimgPos);
            buf.writeBytes(file.data);
        }
        
        return buf.getBuffer();
    }
    
    public void addFile(byte[] data) {
        FimgEntry file = new FimgEntry();
        
        file.data = data;
        files.add(file);
    }
}