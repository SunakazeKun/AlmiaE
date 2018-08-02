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

package com.aurum.almia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {
    public static ByteBuffer loadFileIntoBuffer(String filepath) throws IOException {
        FileInputStream in = new FileInputStream(new File(filepath));
        ByteBuffer buffer = new ByteBuffer(in.available());
        in.read(buffer.getBuffer());
        
        return buffer;
    }
    
    public static void saveBytesToFile(byte[] bytes, File file) throws IOException {
        if (!(file.exists() && file.isFile())) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(bytes);
            out.flush();
        }
    }
}