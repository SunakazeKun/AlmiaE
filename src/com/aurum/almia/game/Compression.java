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

import com.aurum.almia.util.BitConverter;
import java.nio.ByteOrder;

public final class Compression {
    public static final int LZ10 = 0x10;
    public static final int LZ11 = 0x11;
    
    public static byte[] decompress(byte[] compressed, ByteOrder endian) {
        switch(compressed[0] & 0xFF) {
            case LZ10: return decompressLZ10(compressed, endian);
            case LZ11: return decompressLZ11(compressed, endian);
            default: return compressed;
        }
    }
    
    public static byte[] decompressLZ10(byte[] compressed, ByteOrder endian) {
        int decompressedSize = BitConverter.toUnsignedInt24(compressed, endian, 1);
        int compressedStart = 4;
        
        if (decompressedSize == 0) {
            decompressedSize = BitConverter.toInt(compressed, endian, 4);
            compressedStart = 8;
        }
        
        byte[] decompressed = new byte[decompressedSize];
        int curIn = compressedStart;
        int curOut = 0;
        
        while (curOut < decompressed.length) {
            byte flags = compressed[curIn++];
            
            for (int fi = 0 ; fi < 8 && curIn < compressed.length ; fi++) {
                if ((flags & (0x80 >> fi)) != 0) {
                    int token = compressed[curIn++] & 0xFF;
                    int lenCopy = (token >> 4) + 3;
                    int disp = ((token & 0xF) << 8) | (compressed[curIn++] & 0xFF);
                    int offCopy = curOut - disp - 1;
                    
                    for (int i = 0 ; i < lenCopy ; i++)
                        decompressed[curOut++] = decompressed[offCopy + i];
                }
                else
                    decompressed[curOut++] = compressed[curIn++];
            }
        }
        
        return decompressed;
    }

    public static byte[] decompressLZ11(byte[] compressed, ByteOrder endian) {
        int decompressedSize = BitConverter.toUnsignedInt24(compressed, endian, 1);
        int compressedStart = 4;
        
        if (decompressedSize == 0) {
            decompressedSize = BitConverter.toInt(compressed, endian, 4);
            compressedStart = 8;
        }
        
        byte[] decompressed = new byte[decompressedSize];
        int curIn = compressedStart;
        int curOut = 0;
        
        while (curOut < decompressed.length) {
            byte flags = compressed[curIn++];
            
            for (int fi = 0 ; fi < 8 && curIn < compressed.length ; fi++) {
                if ((flags & (0x80 >> fi)) != 0) {
                    int token = compressed[curIn++] & 0xFF;
                    int token2, token3;
                    int lenCopy;
                    int disp;
                    int offCopy;
                    
                    switch(token >> 4) {
                        case 0:
                            token2 = compressed[curIn++] & 0xFF;
                            
                            lenCopy = (((token & 0xF) << 4) | (token2 >> 4)) + 0x11;
                            disp = ((token2 & 0xF) << 8) | (compressed[curIn++] & 0xFF);
                            break;
                        case 1:
                            token2 = compressed[curIn++] & 0xFF;
                            token3 = compressed[curIn++] & 0xFF;
                            
                            lenCopy = (((token & 0xF) << 12) | (token2 << 4) | (token3 >> 4)) + 0x111;
                            disp = ((token3 & 0xF) << 8) | (compressed[curIn++] & 0xFF);
                            break;
                        default:
                            lenCopy = (token >> 4) + 0x1;
                            disp = ((token & 0xF) << 8) | (compressed[curIn++] & 0xFF);
                    }
                    
                    offCopy = curOut + disp + 1;
                    
                    for (int i = 0 ; i < lenCopy ; i++)
                        decompressed[curOut++] = decompressed[offCopy + i];
                }
                else
                    decompressed[curOut++] = compressed[curIn++];
            }
        }
        
        return decompressed;
    }
}