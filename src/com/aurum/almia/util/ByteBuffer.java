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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;

public class ByteBuffer {
    public static final String STANDARD_CHARSET = "UTF-8";
    
    public static ByteBuffer read(String filepath) throws IOException {
        return read(new File(filepath));
    }
    
    public static ByteBuffer read(File file) throws IOException {
        return read(new FileInputStream(file));
    }
    
    public static ByteBuffer read(InputStream in) throws IOException {
        byte[] raw = new byte[in.available()];
        in.read(raw);
        in.close();
        return new ByteBuffer(raw);
    }
    
    public static void write(ByteBuffer buffer, String filepath) throws IOException {
        write(buffer, new File(filepath));
    }
    
    public static void write(ByteBuffer buffer, File file) throws IOException {
        write(buffer, new FileOutputStream(file));
    }
    
    public static void write(ByteBuffer buffer, OutputStream out) throws IOException {
        out.write(buffer.getBuffer());
        out.flush();
        out.close();
    }
    
    protected byte[] buffer;
    protected int position;
    protected ByteOrder endianness;
    protected HashMap<String, Integer> marks;
    
    public ByteBuffer(int length) {
        this(new byte[length], ByteOrder.nativeOrder());
    }
    
    public ByteBuffer(int length, ByteOrder endian) {
        this(new byte[length], endian);
    }
    
    public ByteBuffer(byte[] bytes) {
        this(bytes, ByteOrder.nativeOrder());
    }
    
    public ByteBuffer(byte[] bytes, ByteOrder endian) {
        this.buffer = bytes;
        this.position = 0;
        this.endianness = endian;
        this.marks = new HashMap();
    }
    
    /**
     * Returns a string representation.
     * @return a string representation.
     */
    @Override
    public String toString() {
        return "Size: " + buffer.length + ", Position: " + position;
    }
    
    /**
     * Returns the hash code value.
     * @return the hash code value.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(buffer) ^ position;
    }
    
    /**
     * Checks if another object is equal to this buffer.
     * @param o the other object
     * @return {@code true} if the both objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof ByteBuffer) ? hashCode() == o.hashCode() : false;
    }
    
    /**
     * Returns the content of this buffer.
     * @return the content of this buffer.
     */
    public byte[] getBuffer() {
        return buffer;
    }
    
    /**
     * Sets the content of this buffer.
     * @param bytes the new content
     */
    public void setBuffer(byte[] bytes) {
        buffer = bytes;
        position = 0;
        marks.clear();
    }
    
    /**
     * Returns the endianness.
     * @return the endianness.
     */
    public ByteOrder getEndianness() {
        return endianness;
    }
    
    /**
     * Sets the endianness.
     * @param endian the endianness
     */
    public void setEndianness(ByteOrder endian) {
        endianness = endian;
    }
    
    /**
     * Returns the size of this buffer.
     * @return the size of this buffer.
     */
    public int size() {
        return buffer.length;
    }
    
    /**
     * Returns the number of  bytes between the current position and the end
     * of the buffer.
     * @return the number of remaining bytes.
     */
    public int remaining() {
        return (position < buffer.length) ? buffer.length - position : 0;
    }
    
    /**
     * Checks if there are remaining bytes between the current position and
     * the end of the buffer.
     * @return {@code true} if the number of remaining bytes is greater than 0.
     */
    public boolean hasRemaining() {
        return remaining() > 0;
    }
    
    /**
     * Returns the current position.
     * @return the current position.
     */
    public int position() {
        return position;
    }
    
    /**
     * Sets the position.
     * @param newpos the new position
     */
    public void seek(int newpos) {
        if (newpos < 0)
            throw new IllegalArgumentException("new position " + newpos + " < 0");
        position = newpos;
    }
    
    /**
     * Sets the current position to a mark.
     * If the mark does not exist, the position will not be changed.
     * @param key the mark key.
     */
    public void seek(String key) {
        position = marks.containsKey(key) ? marks.get(key) : position;
    }
    
    /**
     * Skips over {@code len} bytes of data.
     * @param len the number of bytes to be skipped.
     */
    public void skip(int len) {
        position += len;
    }
    
    /**
     * Returns the next index of the given byte.
     * @param val the byte that is searched for
     * @param off the offset from which the searching is started
     * @return the index.
     */
    public int indexOf(byte val, int off) {
        while(off < buffer.length) {
            if (buffer[off] == val)
                return off;
            off++;
        }
        
        return -1;
    }
    
    /**
     * Returns the value of a marked position.
     * @param key the mark key.
     * @return the value of a marked position.
     */
    public int getMark(String key) {
        return marks.containsKey(key) ? marks.get(key) : 0;
    }
    
    /**
     * Marks the current position and assigns the key {@code key} to it.
     * @param key
     * @return the current position.
     */
    public int setMark(String key) {
        return addMark(key, position);
    }
    
    /**
     * Adds a new marked position and assigns the key {@code key} to it.
     * @param name the mark key.
     * @param pos the mark value.
     * @return the same value as {@code pos}.
     */
    public int addMark(String name, int pos) {
        return marks.containsKey(name) ? marks.replace(name, pos) : marks.put(name, pos);
    }
    
    /**
     * Deletes an existing marked position.
     * @param key the key for the mark
     */
    public void deleteMark(String key) {
        if (marks.containsKey(key))
            marks.remove(key);
    }
    
    /**
     * Clears all marked positions.
     */
    public void clearMarks() {
        marks.clear();
    }
    
    /**
     * Clears the buffer, resets the position to 0 and removes all marks.
     * The endianness remains untouched.
     * @param size the new size
     */
    public void allocate(int size) {
        buffer = new byte[size];
        position = 0;
        marks.clear();
    }
    
    /**
     * Clears the buffer, resets the position to 0 and removes all marks.
     * The endianness remains untouched.
     */
    public void clear() {
        allocate(0);
    }
    
    /**
     * Clears the bytes between the given indexes.
     * @param start the starting offset
     * @param end the end offset
     */
    public void clear(int start, int end) {
        if (start >= buffer.length || end >= buffer.length)
            throw new IllegalArgumentException("indexes out-of-bounds");
        if (end < start)
            throw new IllegalArgumentException("end index > start index");
        
        for ( ; start <= end ; start++)
            buffer[start] = (byte)0x0;
    }
    
    /**
     * Extends this buffer by the specified number.
     * @param addsize the length that is added to the current length.
     */
    public void extend(int addsize) {
        if (addsize == 0)
            return;
        
        byte[] extended = new byte[buffer.length + addsize];
        System.arraycopy(buffer, 0, extended, 0, buffer.length);
        buffer = extended;
    }
    
    /**
     * Reads up to {@code len} bytes from this buffer.
     * @param len the length of bytes to be read
     * @return an array of bytes.
     */
    public byte[] readBytes(int len) {
        byte[] b = new byte[len];
        if (remaining() >= len && len != 0) {
            for (int c = 0 ; c < len ; c++)
                b[c] = buffer[position++];
        }
        return b;
    }
    
    /**
     * Reads up to {@code len} bytes from this buffer at the given position.
     * @param pos the position
     * @param len the length of bytes to be read
     * @return an array of bytes.
     */
    public byte[] readBytesAt(int pos, int len) {
        seek(pos);
        return readBytes(len);
    }
    
    /**
     * Reads up all remaining bytes.
     * @return an array of all remaining bytes.
     */
    public byte[] readRemaining() {
        return readBytes(remaining());
    }
    
    /**
     * Reads up all remaining bytes at the given position.
     * @param pos the position
     * @return an array of all remaining bytes.
     */
    public byte[] readRemainingAt(int pos) {
        seek(pos);
        return readRemaining();
    }
    
    /**
     * Reads the next byte from this buffer.
     * @return the 8-bit value.
     */
    public byte readByte() {
        return (remaining() >= Byte.BYTES) ? buffer[position++] : 0;
    }
    
    /**
     * Reads the next byte from this buffer at the given position.
     * @param pos the position
     * @return the 8-bit value.
     */
    public byte readByteAt(int pos) {
        seek(pos);
        return readByte();
    }
    
    /**
     * Reads the next unsigned byte from this buffer.
     * @return the unsigned 8-bit value.
     */
    public short readUnsignedByte() {
        return (short) (readByte() & 0xFF);
    }
    
    /**
     * Reads the next unsigned byte from this buffer at the given position.
     * @param pos the position
     * @return the unsigned 8-bit value.
     */
    public short readUnsignedByteAt(int pos) {
        seek(pos);
        return readUnsignedByte();
    }
    
    /**
     * Reads the next short value from this buffer.
     * @return the 16-bit value.
     */
    public short readShort() {
        return (remaining() >= Short.BYTES) ? BitConverter.toShort(readBytes(Short.BYTES), endianness, 0) : 0;
    }
    
    /**
     * Reads the next short value from this buffer at the given position.
     * @param pos the position
     * @return the 16-bit value.
     */
    public short readShortAt(int pos) {
        seek(pos);
        return readShort();
    }
    
    /**
     * Reads the next unsigned short value from this buffer.
     * @return the unsigned 16-bit value.
     */
    public int readUnsignedShort() {
        return (remaining() >= Short.BYTES) ? BitConverter.toUnsignedShort(readBytes(Short.BYTES), endianness, 0) : 0;
    }
    
    /**
     * Reads the next unsigned short value from this buffer at the given position.
     * @param pos the position
     * @return the unsigned 16-bit value.
     */
    public int readUnsignedShortAt(int pos) {
        seek(pos);
        return readUnsignedShort();
    }
    
    /**
     * Reads the next int value from this buffer.
     * @return the 32-bit value.
     */
    public int readInt() {
        return (remaining() >= Integer.BYTES) ? BitConverter.toInt(readBytes(Integer.BYTES), endianness, 0) : 0;
    }
    
    /**
     * Reads the next int value from this buffer at the given position.
     * @param pos the position
     * @return the 32-bit value.
     */
    public int readIntAt(int pos) {
        seek(pos);
        return readInt();
    }
    
    /**
     * Reads the next unsigned int value from this buffer.
     * @return the unsigned 32-bit value.
     */
    public long readUnsignedInt() {
        return (remaining() >= Integer.BYTES) ? BitConverter.toUnsignedInt(readBytes(Integer.BYTES), endianness, 0) : 0;
    }
    
    /**
     * Reads the next unsigned int value from this buffer at the given position.
     * @param pos the position
     * @return the unsigned 32-bit value.
     */
    public long readUnsignedIntAt(int pos) {
        seek(pos);
        return readUnsignedInt();
    }
    
    /**
     * Reads the next long value from the buffer.
     * @return the 64-bit value.
     */
    public long readLong() {
        return (remaining() >= Long.BYTES) ? BitConverter.toLong(readBytes(Long.BYTES), endianness, 0) : 0;
    }
    
    /**
     * Reads the next long value from the buffer at the given position.
     * @param pos the position
     * @return the 64-bit value.
     */
    public long readLongAt(int pos) {
        seek(pos);
        return readLong();
    }
    
    /**
     * Reads the next float value from the buffer.
     * @return the float value.
     */
    public float readFloat() {
        return (remaining() >= Float.BYTES) ? BitConverter.toFloat(readBytes(Float.BYTES), endianness, 0) : 0;
    }
    
    /**
     * Reads the next float value from the buffer at the given position.
     * @param pos the position
     * @return the float value.
     */
    public float readFloatAt(int pos) {
        seek(pos);
        return readFloat();
    }
    
    /**
     * Reads the next double value from the buffer.
     * @return the double value.
     */
    public double readDouble() {
        return (remaining() >= Double.BYTES) ? BitConverter.toDouble(readBytes(Double.BYTES), endianness, 0) : 0;
    }
    
    /**
     * Reads the next double value from the buffer at the given position.
     * @param pos the position
     * @return the double value.
     */
    public double readDoubleAt(int pos) {
        seek(pos);
        return readDouble();
    }
    
    /**
     * Reads the next Unicode char from the buffer.
     * @return the Unicode char.
     */
    public char readCharacter() {
        return (remaining() >= Character.BYTES) ? BitConverter.toChar(readBytes(Character.BYTES), endianness, 0) : 0;
    }
    
    /**
     * Reads the next Unicode char from the buffer. at the given position
     * @param pos the position
     * @return the Unicode char.
     */
    public char readCharacterAt(int pos) {
        seek(pos);
        return readCharacter();
    }
    
    /**
     * Reads the next boolean value from the buffer.
     * @return the boolean value.
     */
    public boolean readBoolean() {
        return readByte() != 0;
    }
    
    /**
     * Reads the next boolean value from the buffer at the given position.
     * @param pos the position
     * @return the boolean value.
     */
    public boolean readBooleanAt(int pos) {
        seek(pos);
        return readBoolean();
    }
    
    /**
     * Reads the next String from the buffer.
     * @param charset the charset
     * @param len the length of the String
     * @return the String.
     */
    public String readString(String charset, int len) {
        return (len > 0) ? new String(readBytes(len), Charset.forName(charset)) : "";
    }
    
    /**
     * Reads the next String from the buffer at the given position.
     * @param pos the position
     * @param charset the charset
     * @param len the length of the String
     * @return the String.
     */
    public String readStringAt(int pos, String charset, int len) {
        seek(pos);
        return readString(charset, len);
    }
    
    /**
     * Reads the next String from the buffer.
     * @param len the length of the String
     * @return the String.
     */
    public String readString(int len) {
        return readString(STANDARD_CHARSET, len);
    }
    
    /**
     * Reads the next String from the buffer at the given position.
     * @param pos the position
     * @param len the length of the String
     * @return the String.
     */
    public String readStringAt(int pos, int len) {
        seek(pos);
        return readString(len);
    }
    
    /**
     * Decodes and returns the next null-terminated String from the buffer.
     * @param charset
     * @return A string.
     */
    public String readString(String charset) {
        return readString(charset, indexOf((byte) 0, position) - position);
    }
    
    /**
     * Decodes and returns the next null-terminated String from the buffer at the given position.
     * @param pos the position
     * @param charset
     * @return A string.
     */
    public String readStringAt(int pos, String charset) {
        seek(pos);
        return readString(charset);
    }
    
    /**
     * Decodes and returns the next null-terminated String from the buffer.
     * @return A string.
     */
    public String readString() {
        return readString(STANDARD_CHARSET);
    }
    
    /**
     * Decodes and returns the next null-terminated String from the buffer at the given position.
     * @param pos the position
     * @return A string.
     */
    public String readStringAt(int pos) {
        seek(pos);
        return readString();
    }
    
    public String readMagic() {
        return readString("ASCII", 4);
    }
    
    public String readMagicAt(int pos) {
        seek(pos);
        return readMagic();
    }
    
    /**
     * Writes the content of the specified byte array to this buffer.
     * @param val the byte array
     */
    public void writeBytes(byte[] val) {
        if (position + val.length > buffer.length)
            extend(val.length + (position > buffer.length ? position - buffer.length : 0));
        for (byte b : val)
            buffer[position++] = b;
    }
    
    /**
     * Writes the content of the specified byte array to this buffer at the given position.
     * @param pos the position
     * @param val the byte array
     */
    public void writeBytesAt(int pos, byte[] val) {
        seek(pos);
        writeBytes(val);
    }
    
    /**
     * Writes the specified byte to this buffer.
     * @param val the 8-bit integer value
     */
    public void writeByte(byte val) {
        if (position + 1 > buffer.length)
            extend(1);
        buffer[position++] = val;
    }
    
    /**
     * Writes the specified byte to this buffer at the given position.
     * @param pos the position
     * @param val the 8-bit integer value
     */
    public void writeByteAt(int pos, byte val) {
        seek(pos);
        writeByte(val);
    }
    
    /**
     * Writes the specified unsigned byte to this buffer.
     * @param val the unsigned 8-bit integer value
     */
    public void writeUnsignedByte(short val) {
        writeByte((byte) val);
    }
    
    /**
     * Writes the specified unsigned byte to this buffer at the given position.
     * @param pos the position
     * @param val the unsigned 8-bit integer value
     */
    public void writeUnsignedByteAt(int pos, short val) {
        seek(pos);
        writeUnsignedByte(val);
    }
    
    /**
     * Writes the specified short value to this buffer.
     * @param val the 16-bit integer value
     */
    public void writeShort(short val) {
        writeBytes(BitConverter.getBytes(val, endianness));
    }
    
    /**
     * Writes the specified short value to this buffer at the given position.
     * @param pos the position
     * @param val the 16-bit integer value
     */
    public void writeShortAt(int pos, short val) {
        seek(pos);
        writeShort(val);
    }
    
    /**
     * Writes the specified unsigned short value to this buffer.
     * @param val the unsigned 16-bit integer value
     */
    public void writeUnsignedShort(int val) {
        writeShort((short) val);
    }
    
    /**
     * Writes the specified unsigned short value to this buffer at the given position.
     * @param pos the position
     * @param val the unsigned 16-bit integer value
     */
    public void writeUnsignedShortAt(int pos, int val) {
        seek(pos);
        writeUnsignedShort(val);
    }
    
    /**
     * Writes the specified int value to this buffer.
     * @param val the 32-bit integer value
     */
    public void writeInt(int val) {
        writeBytes(BitConverter.getBytes(val, endianness));
    }
    
    /**
     * Writes the specified int value to this buffer at the given position.
     * @param pos the position
     * @param val the 32-bit integer value
     */
    public void writeIntAt(int pos, int val) {
        seek(pos);
        writeInt(val);
    }
    
    /**
     * Writes the specified unsigned int value to this buffer.
     * @param val the unsigned 32-bit integer value
     */
    public void writeUnsignedInt(long val) {
        writeInt((int) val);
    }
    
    /**
     * Writes the specified unsigned int value to this buffer at the given position.
     * @param pos the position
     * @param val the unsigned 32-bit integer value
     */
    public void writeUnsignedIntAt(int pos, long val) {
        seek(pos);
        writeUnsignedInt(val);
    }
    
    /**
     * Writes the specified long value to this buffer.
     * @param val the 64-bit integer value
     */
    public void writeLong(long val) {
        writeBytes(BitConverter.getBytes(val, endianness));
    }
    
    /**
     * Writes the specified long value to this buffer at the given position.
     * @param pos the position
     * @param val the 64-bit integer value
     */
    public void writeLongAt(int pos, long val) {
        seek(pos);
        writeLong(val);
    }
    
    /**
     * Writes the specified float value to this buffer.
     * @param val the float value
     */
    public void writeFloat(float val) {
        writeBytes(BitConverter.getBytes(val, endianness));
    }
    
    /**
     * Writes the specified float value to this buffer at the given position.
     * @param pos the position
     * @param val the float value
     */
    public void writeFloatAt(int pos, float val) {
        seek(pos);
        writeFloat(val);
    }
    
    /**
     * Writes the specified double value to this buffer.
     * @param val the double value
     */
    public void writeDouble(double val) {
        writeBytes(BitConverter.getBytes(val, endianness));
    }
    
    /**
     * Writes the specified double value to this buffer at the given position.
     * @param pos the position
     * @param val the double value
     */
    public void writeDoubleAt(int pos, double val) {
        seek(pos);
        writeDouble(val);
    }
    
    /**
     * Writes a Unicode char to this buffer.
     * @param val the Unicode char
     */
    public void writeCharacter(char val) {
        writeBytes(BitConverter.getBytes(val, endianness));
    }
    
    /**
     * Writes a Unicode char to this buffer at the given position.
     * @param pos the position
     * @param val the Unicode char
     */
    public void writeCharacterAt(int pos, char val) {
        seek(pos);
        writeCharacter(val);
    }
    
    /**
     * Writes a boolean value to this buffer.
     * @param val the boolean value
     */
    public void writeBoolean(boolean val) {
        writeByte((byte) (val ? 1 : 0));
    }
    
    /**
     * Writes a boolean value to this buffer at the given position.
     * @param pos the position
     * @param val the boolean value
     */
    public void writeBooleanAt(int pos, boolean val) {
        seek(pos);
        writeBoolean(val);
    }
    
    /**
     * Writes a String to this buffer.
     * @param val the String
     * @param charset the charset
     * @return the offset to this String.
     */
    public int writeString(String val, String charset) {
        int pos = position;
        writeBytes(val.getBytes(Charset.forName(charset)));
        writeByte((byte) 0x0);
        return pos;
    }
    
    /**
     * Writes a String to this buffer at the given position.
     * @param pos the position
     * @param val the String
     * @param charset the charset
     * @return the offset to this String.
     */
    public int writeStringAt(int pos, String val, String charset) {
        seek(pos);
        return writeString(val, charset);
    }
    
    /**
     * Writes a String to this buffer.
     * @param val the String
     * @return the offset to this String.
     */
    public int writeString(String val) {
        return writeString(val, STANDARD_CHARSET);
    }
    
    /**
     * Writes a String to this buffer at the given position.
     * @param pos the position
     * @param val the String
     * @return the offset to this String.
     */
    public int writeStringAt(int pos, String val) {
        seek(pos);
        return writeString(val);
    }
    
    public int writeMagic(String val) {
        int pos = position;
        writeBytes(val.getBytes(Charset.forName("ASCII")));
        return pos;
    }
    
    public int writeMagicAt(int pos, String val) {
        seek(pos);
        return writeMagic(val);
    }
}