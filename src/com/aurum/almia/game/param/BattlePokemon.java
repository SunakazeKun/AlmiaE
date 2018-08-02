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

import com.aurum.almia.ByteBuffer;
import com.aurum.almia.ByteOrder;
import com.aurum.almia.Utils;
import com.aurum.almia.game.Game;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BattlePokemon {
    public static final int HEADER_SIZE = 0x10;
    public static final int ENTRY_SIZE = 0x18;
    
    //--------------------------------------------------------------------------
    
    public static class Entry implements Cloneable {
        public byte[] unk0;
        
        public Entry() {
            this.unk0 = new byte[ENTRY_SIZE];
        }
        
        @Override
        public String toString() {
            return super.toString();
        }
    }
    
    //--------------------------------------------------------------------------
    
    public Game game;
    public List<Entry> entries;
    public byte[] unique;
    public int unkC;
    
    //--------------------------------------------------------------------------
    
    public BattlePokemon(Game game) {
        this.game = game;
        this.entries = new ArrayList();
        this.unique = new byte[0x0];
        this.unkC = 0x0;
    }
    
    public BattlePokemon(Game game, ByteBuffer buf) {
        this(game);
        
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        
        int totalsize = buf.readInt();
        if (buf.size() != totalsize) {
            System.out.println("BattlePokemon: Warning! Stored size does not equal actual buffer size!");
        }
        int uniquesize = buf.readInt();
        int datasize = buf.readInt();
        unkC = buf.readInt();
        unique = buf.readBytes(uniquesize);
        
        int entriesCount = datasize / ENTRY_SIZE;
        
        for (int i = 0 ; i < entriesCount ; i++) {
            Entry e = new Entry();
            
            e.unk0 = buf.readBytes(ENTRY_SIZE);
            
            entries.add(e);
        }
    }
    
    public byte[] pack() {
        int datasize = entries.size() * ENTRY_SIZE;
        int totalsize = HEADER_SIZE + unique.length + datasize;
        
        ByteBuffer buf = new ByteBuffer(totalsize, ByteOrder.LITTLE_ENDIAN);
        buf.writeInt(totalsize);
        buf.writeInt(unique.length);
        buf.writeInt(datasize);
        buf.writeInt(unkC);
        buf.writeBytes(unique);
        
        for (Entry e : entries) {
            buf.writeBytes(e.unk0);
        }
        
        return buf.getBuffer();
    }
    
    public void save() throws IOException {
        Utils.saveBytesToFile(pack(), new File(game.getFullPath("param/BattlePokemon.bin")));
    }
}