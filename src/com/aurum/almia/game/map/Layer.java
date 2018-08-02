package com.aurum.almia.game.map;

import com.aurum.almia.ByteBuffer;
import com.aurum.almia.ByteOrder;
import com.aurum.almia.game.NARC;
import com.aurum.almia.game.NARC.FimgEntry;
import java.util.ArrayList;
import java.util.List;

public class Layer {
    public static final int MDATA_01 = 0x01;
    public static final int MDATA_02 = 0x02;
    public static final int MDATA_COLLISION = 0x03;
    public static final int MDATA_TARGET = 0x04;
    public static final int MDATA_UNKNOWN = 0x05;   // unused
    public static final int MDATA_06 = 0x06;
    public static final int MDATA_WARP = 0x07;
    public static final int MDATA_NPC = 0x08;
    public static final int MDATA_MON = 0x09;
    public static final int MDATA_0A = 0x0A;
    public static final int MDATA_0B = 0x0B;
    public static final int MDATA_0C = 0x0C;
    public static final int MDATA_0D = 0x0D;
    public static final int MDATA_0E = 0x0E;
    
    public Map map;
    public List<byte[]> lyrUnk;
    public TargetData targetData;
    public WarpData warpData;
    public NpcData npcData;
    public MonData monData;
    
    public Layer(Map map) {
        this.map = map;
        this.lyrUnk = new ArrayList();
        this.targetData = new TargetData(this);
        this.warpData = new WarpData(this);
        this.npcData = new NpcData(this);
        this.monData = new MonData(this);
    }
    
    public Layer(Map map, NARC narc) {
        this(map);
        
        ByteBuffer buf;
        
        for (FimgEntry file : narc.files) {
            buf = new ByteBuffer(file.data, ByteOrder.LITTLE_ENDIAN);

            int identifier = buf.readInt();

            switch(identifier) {
                case MDATA_TARGET: targetData = new TargetData(this, buf); break;
                case MDATA_WARP: warpData = new WarpData(this, buf); break;
                case MDATA_NPC: npcData = new NpcData(this, buf); break;
                case MDATA_MON: monData = new MonData(this, buf); break;
                default: System.out.println(String.format("Unknown map format %02X, %d", identifier, buf.size())); lyrUnk.add(buf.getBuffer());
            }
        }
    }
    
    public byte[] pack() {
        NARC narc = new NARC();
        
        for (byte[] unk : lyrUnk) {
            narc.addFile(unk);
        }
        
        narc.addFile(targetData.pack());
        narc.addFile(warpData.pack());
        narc.addFile(npcData.pack());
        narc.addFile(monData.pack());
        
        return narc.pack();
    }
}