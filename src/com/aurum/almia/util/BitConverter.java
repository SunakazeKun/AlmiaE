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

package com.aurum.almia.util;

import java.math.BigInteger;
import java.nio.ByteOrder;

public final class BitConverter {
    private BitConverter() {}
    
    private static byte[] createBuffer(byte[] data, ByteOrder endian, int offset, int size) {
        byte[] bytes = new byte[size];
        
        for (int i = 0 ; i < size ; i++)
            bytes[i] = data[i + offset];
        
        return endian == ByteOrder.LITTLE_ENDIAN ? ArrayUtils.reverse(bytes) : bytes;
    }
    
    public static short toShort(byte[] data, ByteOrder endian, int offset) {
        byte[] bs = createBuffer(data, endian, offset, Short.BYTES);
        
        return (short)(
                ((bs[0] & 0xFF) <<  8) | 
                 (bs[1] & 0xFF)
                );
    }
    
    public static int toUnsignedShort(byte[] data, ByteOrder endian, int offset) {
        return (int)(toShort(data, endian, offset) & 0xFFFF);
    }
    
    public static int toInt(byte[] data, ByteOrder endian, int offset) {
        byte[] bs = createBuffer(data, endian, offset, Integer.BYTES);
        
        return (
                ((bs[0] & 0xFF) << 24) | 
                ((bs[1] & 0xFF) << 16) | 
                ((bs[2] & 0xFF) <<  8) | 
                 (bs[3] & 0xFF)
                );
    }
    
    public static long toUnsignedInt(byte[] data, ByteOrder endian, int offset) {
        return (long)(toInt(data, endian, offset) & 0xFFFFFFFF);
    }
    
    public static int toInt24(byte[] data, ByteOrder endian, int offset) {
        byte[] bs = createBuffer(data, endian, offset, 3);
        
        return (
                ((bs[0] & 0xFF) << 16) | 
                ((bs[1] & 0xFF) <<  8) | 
                 (bs[2] & 0xFF)
                );
    }
    
    public static int toUnsignedInt24(byte[] data, ByteOrder endian, int offset) {
        return toInt24(data, endian, offset) & 0xFFFFFF;
    }
    
    public static long toLong(byte[] data, ByteOrder endian, int offset) {
        byte[] bs = createBuffer(data, endian, offset, Long.BYTES);
        
        return (
                ((long) (bs[0] & 0xFF) << 56) | 
                ((long) (bs[1] & 0xFF) << 48) | 
                ((long) (bs[2] & 0xFF) << 40) | 
                ((long) (bs[3] & 0xFF) << 32) | 
                ((long) (bs[4] & 0xFF) << 24) | 
                ((long) (bs[5] & 0xFF) << 16) | 
                ((long) (bs[6] & 0xFF) <<  8) | 
                 (long) (bs[7] & 0xFF)
                );
    }
    
    public static BigInteger toUnsignedLong(byte[] data, ByteOrder endian, int offset) {
        return new BigInteger(createBuffer(data, endian, offset, Long.BYTES));
    }
    
    public static float toFloat(byte[] data, ByteOrder endian, int offset) {
        return Float.intBitsToFloat(toInt(data, endian, offset));
    }
    
    public static double toDouble(byte[] data, ByteOrder endian, int offset) {
        return Double.longBitsToDouble(toLong(data, endian, offset));
    }
    
    public static char toChar(byte[] data, ByteOrder endian, int offset) {
        return (char)toShort(data, endian, offset);
    }
    
    public static boolean toBoolean(byte[] data, int offset) {
        return data[offset] != 0;
    }
    
    public static byte[] getBytes(short val, ByteOrder endian) {
        byte[] bs = new byte[Short.BYTES];
        
        bs[0] = (byte)((val >>  8) & 0xFF);
        bs[1] = (byte) (val        & 0xFF);
        
        return endian == ByteOrder.LITTLE_ENDIAN ? ArrayUtils.reverse(bs) : bs;
    }
    
    public static byte[] getBytes(int val, ByteOrder endian) {
        byte[] bs = new byte[Integer.BYTES];
        
        bs[0] = (byte)((val >> 24) & 0xFF);
        bs[1] = (byte)((val >> 16) & 0xFF);
        bs[2] = (byte)((val >>  8) & 0xFF);
        bs[3] = (byte) (val        & 0xFF);
        
        return endian == ByteOrder.LITTLE_ENDIAN ? ArrayUtils.reverse(bs) : bs;
    }
    
    public static byte[] getBytes(long val, ByteOrder endian) {
        byte[] bs = new byte[Long.BYTES];
        
        bs[0] = (byte)((val >> 56) & 0xFF);
        bs[1] = (byte)((val >> 48) & 0xFF);
        bs[2] = (byte)((val >> 40) & 0xFF);
        bs[3] = (byte)((val >> 32) & 0xFF);
        bs[4] = (byte)((val >> 24) & 0xFF);
        bs[5] = (byte)((val >> 16) & 0xFF);
        bs[6] = (byte)((val >>  8) & 0xFF);
        bs[7] = (byte) (val        & 0xFF);
        
        return endian == ByteOrder.LITTLE_ENDIAN ? ArrayUtils.reverse(bs) : bs;
    }
    
    public static byte[] getBytes24(int val, ByteOrder endian) {
        byte[] bs = new byte[3];
        
        bs[0] = (byte)((val >> 16) & 0xFF);
        bs[1] = (byte)((val >>  8) & 0xFF);
        bs[2] = (byte) (val        & 0xFF);
        
        return endian == ByteOrder.LITTLE_ENDIAN ? ArrayUtils.reverse(bs) : bs;
    }
    
    public static byte[] getBytes(float val, ByteOrder endian) {
        return getBytes(Float.floatToIntBits(val), endian);
    }
    
    public static byte[] getBytes(double val, ByteOrder endian) {
        return getBytes(Double.doubleToLongBits(val), endian);
    }
    
    public static byte[] getBytes(char val, ByteOrder endian) {
        return getBytes((short)val, endian);
    }
    
    public static byte[] getBytes(boolean val) {
        return new byte[] { (byte)(val ? 1 : 0) };
    }
}
