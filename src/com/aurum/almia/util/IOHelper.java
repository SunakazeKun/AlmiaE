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

public final class IOHelper {
    private IOHelper() {}
    
    public static byte[] read(String filename) throws IOException {
        return read(new File(filename));
    }
    
    public static byte[] read(File file) throws IOException {
        return read(new FileInputStream(file));
    }
    
    public static byte[] read(InputStream in) throws IOException {
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();
        return data;
    }
    
    public static void write(byte[] data, String filename) throws IOException {
        write(data, new File(filename));
    }
    
    public static void write(byte[] data, File file) throws IOException {
        write(data, new FileOutputStream(file));
    }
    
    public static void write(byte[] data, OutputStream out) throws IOException {
        out.write(data);
        out.flush();
        out.close();
    }
}