package com.aurum.almia.game.map;

import com.aurum.almia.ByteBuffer;
import com.aurum.almia.ByteOrder;
import static com.aurum.almia.game.map.Map.*;

public class MapInfo {
    public static final int FILE_SIZE = 0x14;
    
    //--------------------------------------------------------------------------
    
    public Map map;
    public int width;
    public int height;
    public int unk8;
    public int unkC;
    
    //--------------------------------------------------------------------------

    public MapInfo(Map map) {
        this.map = map;
        this.width = 256;
        this.height = 256;
        this.unk8 = 16;
        this.unkC = 16;
    }
    
    public MapInfo(Map map, ByteBuffer buf) {
        this(map);
        
        this.width = buf.readInt();
        this.height = buf.readInt();
        this.unk8 = buf.readInt();
        this.unkC = buf.readInt();
    }
    
    public byte[] pack() {
        ByteBuffer buf = new ByteBuffer(FILE_SIZE);

        buf.writeInt(MAP_MPIF);
        buf.setEndianness(ByteOrder.LITTLE_ENDIAN);
        buf.writeInt(width);
        buf.writeInt(height);
        buf.writeInt(unk8);
        buf.writeInt(unkC);
        
        return buf.getBuffer();
    }
}