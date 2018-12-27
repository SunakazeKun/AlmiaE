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
import com.aurum.almia.game.Game;
import com.aurum.almia.util.IOHelper;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattlePokemon {
    public static final int HEADER_SIZE = 0x10;
    public static final int ENTRY_SIZE = 0x18;
    private static final byte[] UNIQUE = {
        (byte)0x07, (byte)0x01, (byte)0x03, (byte)0x03,
        (byte)0x01, (byte)0x02, (byte)0x02, (byte)0x01,
        (byte)0x01, (byte)0x01, (byte)0x01, (byte)0x03,
        (byte)0x01, (byte)0x02, (byte)0x02, (byte)0x02,
        (byte)0x02, (byte)0x02, (byte)0x02, (byte)0x02,
        (byte)0x01, (byte)0x01
    };
    
    public static class Entry implements Cloneable {
        public byte[] unk0;
        
        public Entry() {
            this.unk0 = new byte[ENTRY_SIZE];
        }
        
        @Override
        public String toString() {
            return Arrays.toString(unk0);
        }
    }
    
    public Game game;
    public List<Entry> entries;
    public int unkC;
    
    public BattlePokemon(Game game) throws IOException {
        this.game = game;
        this.entries = new ArrayList();
        
        ByteBuffer buf = ByteBuffer.read(game.getFile("param/BattlePokemon.bin"));
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        
        int totalSize = buf.readInt();
        if (buf.size() != totalSize)
            System.out.println("BattlePokemon: Warning! Stored size does not equal actual buffer size!");
        
        int uniqueSize = buf.readInt();
        int dataSize = buf.readInt();
        this.unkC = buf.readInt();
        
        byte[] unique = buf.readBytes(uniqueSize);
        if (!Arrays.equals(unique, UNIQUE))
            System.out.println("PokeID: Warning! Are you sure this is PokeID data?");
        
        int entriesCount = dataSize / ENTRY_SIZE;
        
        for (int i = 0 ; i < entriesCount ; i++) {
            Entry e = new Entry();
            
            e.unk0 = buf.readBytes(ENTRY_SIZE);
            
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
        
        for (Entry e : entries)
            buf.writeBytes(e.unk0);
        
        return buf.getBuffer();
    }
    
    public void save() throws IOException {
        IOHelper.write(pack(), game.getFile("param/BattlePokemon.bin"));
    }
}
