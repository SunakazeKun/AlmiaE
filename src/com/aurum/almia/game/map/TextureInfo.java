package com.aurum.almia.game.map;

import com.aurum.almia.ByteBuffer;
import com.aurum.almia.ByteOrder;
import static com.aurum.almia.game.map.Map.*;
import java.util.ArrayList;
import java.util.List;

public class TextureInfo {
    public static final int HEADER_SIZE = 0x8;
    public static final int ENTRY_SIZE = 0x8;
    
    //--------------------------------------------------------------------------
    
    public class Entry {
        public short unk0;
        public int textureID;   // unsigned short
        public short unk4;
        public short unk6;
        
        public Entry() {
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
    
    //--------------------------------------------------------------------------
    
    public Map map;
    public List<Entry> entries;
    public short unk2;
    
    //--------------------------------------------------------------------------
    
    public TextureInfo(Map map) {
        this.map = map;
        this.entries = new ArrayList();
        this.unk2 = 0;
    }
    
    public TextureInfo(Map map, ByteBuffer buf) {
        this(map);
        
        int numEntries = buf.readUnsignedShort();
        unk2 = buf.readShort();

        for (int i = 0 ; i < numEntries ; i++) {
            Entry e = new Entry();
            
            e.unk0 = buf.readShort();
            e.textureID = buf.readUnsignedShort();
            e.unk4 = buf.readShort();
            e.unk6 = buf.readShort();

            entries.add(e);
        }
    }
    
    public byte[] pack() {
        ByteBuffer buf = new ByteBuffer(HEADER_SIZE + entries.size() * ENTRY_SIZE);
        
        buf.writeInt(MAP_TXIF);
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
}