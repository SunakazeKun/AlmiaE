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

import com.aurum.almia.util.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class Narc {
    public static final String NARC_MAGIC = "NARC";
    public static final String FATB_MAGIC = "BTAF";
    public static final String FNTB_MAGIC = "BTNF";
    public static final String FIMG_MAGIC = "GMIF";
    
    private class FatbEntry {
        int startOffset;
        int endOffset;
    }
    
    public static class NarcFile {
        public String name;
        public byte[] data;
        
        public NarcFile() {
            this.name = "Unnamed";
            this.data = null;
        }
    }
    
    public List<NarcFile> files;
    
    public Narc() {
        this.files = new ArrayList();
    }
    
    public Narc(byte[] raw) {
        this();
        
        raw = Compression.decompress(raw, ByteOrder.LITTLE_ENDIAN);
        ByteBuffer buf = new ByteBuffer(raw, ByteOrder.LITTLE_ENDIAN);
        
        int curSectionPos;
        int curSectionSize;
        
        // NARC header
        curSectionPos = buf.position();
        
        if (!buf.readMagic().equals(NARC_MAGIC))
            throw new IllegalArgumentException("No valid NARC data found!");
        
        buf.skip(0x8); // shitty bom, unk6, fileSize
        curSectionSize = buf.readShort();
        buf.skip(0x2); // numSections
        
        buf.seek(curSectionPos + curSectionSize);
        
        // FATB section
        curSectionPos = buf.position();
        buf.skip(0x4); // fatbMagic
        curSectionSize = buf.readInt();
        int numFiles = buf.readInt();
        FatbEntry[] fileEntries = new FatbEntry[numFiles];
        
        for (int i = 0 ; i < numFiles ; i++) {
            FatbEntry e = new FatbEntry();
            e.startOffset = buf.readInt();
            e.endOffset = buf.readInt();
            
            fileEntries[i] = e;
        }
        
        buf.seek(curSectionPos + curSectionSize);
        
        // FNTB section
        // needs to be revised...
        curSectionPos = buf.position();
        buf.skip(0x4); // fntbMagic
        curSectionSize = buf.readInt();
        buf.skip(0x8);
        
        buf.seek(curSectionPos + curSectionSize);
        
        // FIMG section
        curSectionPos = buf.position();
        buf.skip(0x4); // fimgMagic
        curSectionSize = buf.readInt();
        
        for (int i = 0 ; i < numFiles ; i++) {
            NarcFile file = new NarcFile();
            FatbEntry fatb = fileEntries[i];
            
            buf.seek(curSectionPos + 0x8 + fatb.startOffset);
            file.data = buf.readBytes(fatb.endOffset - fatb.startOffset);
            
            files.add(file);
        }
    }
    
    public byte[] pack() {
        // Calculate (approximate) file size, and create the buffer
        int headerSize = 0x10;
        int fatbSize = 0xC + files.size() * 0x8;
        int fntbSize = 0x10;
        int fimgSize = 0x8;
        
        for (NarcFile file : files)
            fimgSize += file.data.length;
        
        int totalSize = headerSize + fatbSize + fntbSize + fimgSize;
        ByteBuffer buf = new ByteBuffer(totalSize, ByteOrder.LITTLE_ENDIAN);
        
        // NARC header
        buf.writeMagic(NARC_MAGIC);
        buf.writeByte((byte)0xFE); // BOM... that does not represent LE
        buf.writeByte((byte)0xFF);
        buf.writeShort((short)0x0100); // unknown constant
        buf.writeInt(totalSize);
        buf.writeShort((short)headerSize);
        buf.writeShort((short)0x0003); // numSections
        
        // FATB section
        buf.writeMagic(FATB_MAGIC);
        buf.writeInt(fatbSize);
        buf.writeInt(files.size());
        int fatbEntriesPos = buf.position();
        buf.skip(files.size() * 0x8);
        
        // FNTB section
        // needs revision...
        buf.writeMagic(FNTB_MAGIC);
        buf.writeInt(fntbSize);
        buf.writeInt(0x00000004);
        buf.writeInt(0x00010000);
        
        // FIMG section
        buf.writeMagic(FIMG_MAGIC);
        buf.writeInt(fimgSize);
        int fimgEntriesPos = buf.position();
        
        for (int i = 0 ; i < files.size() ; i++) {
            NarcFile file = files.get(i);
            int inFimgPos = buf.position();
            int startPos = inFimgPos - fimgEntriesPos;
            int endPos = startPos + file.data.length;
            
            // Write FATB entry
            buf.seek(fatbEntriesPos + i * 0x8);
            buf.writeInt(startPos);
            buf.writeInt(endPos);
            
            // Write FIMG entry
            buf.seek(inFimgPos);
            buf.writeBytes(file.data);
        }
        
        return buf.getBuffer();
    }
    
    public void addFile(byte[] data) {
        NarcFile file = new NarcFile();
        
        file.data = data;
        files.add(file);
    }
}