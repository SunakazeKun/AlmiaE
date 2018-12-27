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

package com.aurum.almia.game.param;

import com.aurum.almia.util.ByteBuffer;
import com.aurum.almia.Lists;
import com.aurum.almia.game.Game;
import com.aurum.almia.util.IOHelper;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokeID {
    public static final int HEADER_SIZE = 0x10;
    public static final int ENTRY_SIZE = 0x1C;
    private static final byte[] UNIQUE = {
        (byte)0x07, (byte)0x03, (byte)0x01, (byte)0x07,
        (byte)0x01, (byte)0x01, (byte)0x01, (byte)0x01,
        (byte)0x02, (byte)0x02, (byte)0x01, (byte)0x02,
        (byte)0x02, (byte)0x01, (byte)0x01, (byte)0x02,
        (byte)0x02, (byte)0x01, (byte)0x01, (byte)0x02,
        (byte)0x02, (byte)0x03, (byte)0x03, (byte)0x05
    };
    
    public class Entry implements Cloneable {
        public int nameID;          // unsigned short
        public short formID;        // unsigned byte
        public short typeID;        // unsigned byte
        public short assistID;      // unsigned byte
        public short fieldID;       // unsigned byte
        public short fieldLevel;    // unsigned byte
        public byte unk7;           // always 0
        public int unk8;            // unsigned short
        public int unkA;            // unsigned short
        public short shadowWidth;   // unsigned byte
        public short shadowHeight;  // unsigned byte
        public byte unkE;
        public byte unkF;
        public int unk10;           // always 0
        public int unk14;           // unsigned short
        public int unk16;           // unsigned short
        public int unk18;           // 0, 1 or 100
        
        public Entry() {
            this.nameID = 0;
            this.formID = 0;
            this.typeID = 0;
            this.assistID = 0;
            this.fieldID = 0;
            this.fieldLevel = 0;
            this.unk7 = 0;
            this.unk8 = 0;
            this.unkA = 0;
            this.shadowWidth = 48;
            this.shadowHeight = 24;
            this.unkE = 0;
            this.unkF = 0;
            this.unk10 = 0;
            this.unk14 = 100;
            this.unk16 = 15;
            this.unk18 = 0;
        }
        
        @Override
        public String toString() {
            return String.format(
                    "%s (%s, %s x%d)", 
                    Lists.pokemon_name.get(nameID), 
                    Lists.assist_mes.get(assistID), 
                    Lists.fieldwaza_name.get(fieldID), 
                    fieldLevel
            );
        }
        
        public String toShortString() {
            return Lists.pokemon_name.get(nameID);
        }
    }
    
    public Game game;
    public List<Entry> entries;
    public int unkC;
    
    public PokeID(Game game) throws IOException {
        this.game = game;
        this.entries = new ArrayList();
        
        ByteBuffer buf = ByteBuffer.read(game.getFile("param/PokeID.bin"));
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        
        int totalSize = buf.readInt();
        if (buf.size() != totalSize)
            System.out.println("PokeID: Warning! Stored size does not equal actual buffer size!");
        
        int uniqueSize = buf.readInt();
        int dataSize = buf.readInt();
        this.unkC = buf.readInt();
        
        byte[] unique = buf.readBytes(uniqueSize);
        if (!Arrays.equals(unique, UNIQUE))
            System.out.println("PokeID: Warning! Are you sure this is PokeID data?");
        
        int entriesCount = dataSize / ENTRY_SIZE;
        
        for (int i = 0 ; i < entriesCount ; i++) {
            Entry e = new Entry();
            
            e.nameID = buf.readUnsignedShort();
            e.formID = buf.readUnsignedByte();
            e.typeID = buf.readUnsignedByte();
            e.assistID = buf.readUnsignedByte();
            e.fieldID = buf.readUnsignedByte();
            e.fieldLevel = buf.readUnsignedByte();
            e.unk7 = buf.readByte();
            e.unk8 = buf.readUnsignedShort();
            e.unkA = buf.readUnsignedShort();
            e.shadowWidth = buf.readUnsignedByte();
            e.shadowHeight = buf.readUnsignedByte();
            e.unkE = buf.readByte();
            e.unkF = buf.readByte();
            e.unk10 = buf.readInt();
            e.unk14 = buf.readUnsignedShort();
            e.unk16 = buf.readUnsignedShort();
            e.unk18 = buf.readInt();
            
            entries.add(e);
        }
    }
    
    public byte[] pack() {
        int dataSize = entries.size() * ENTRY_SIZE;
        int totalSize = HEADER_SIZE + UNIQUE.length + dataSize;
        
        ByteBuffer buf = new ByteBuffer(totalSize, ByteOrder.LITTLE_ENDIAN);
        
        buf.writeInt(totalSize);
        buf.writeInt(UNIQUE.length);
        buf.writeInt(dataSize);
        buf.writeInt(unkC);
        buf.writeBytes(UNIQUE);
        
        for (Entry e : entries) {
            buf.writeUnsignedShort(e.nameID);
            buf.writeUnsignedByte(e.formID);
            buf.writeUnsignedByte(e.typeID);
            buf.writeUnsignedByte(e.assistID);
            buf.writeUnsignedByte(e.fieldID);
            buf.writeUnsignedByte(e.fieldLevel);
            buf.writeByte(e.unk7);
            buf.writeUnsignedShort(e.unk8);
            buf.writeUnsignedShort(e.unkA);
            buf.writeUnsignedByte(e.shadowWidth);
            buf.writeUnsignedByte(e.shadowHeight);
            buf.writeByte(e.unkE);
            buf.writeByte(e.unkF);
            buf.writeInt(e.unk10);
            buf.writeUnsignedShort(e.unk14);
            buf.writeUnsignedShort(e.unk16);
            buf.writeInt(e.unk14);
        }
        
        return buf.getBuffer();
    }
    
    public void save() throws IOException {
        IOHelper.write(pack(), game.getFile("param/PokeID.bin"));
    }
}
