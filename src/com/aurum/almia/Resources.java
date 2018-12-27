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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Resources {
    public static InputStream loadStream(String resdir) {
        return Resources.class.getResourceAsStream("/res/" + resdir);
    }
    
    public static Reader loadReader(String resdir) {
        return new InputStreamReader(loadStream(resdir));
    }
    
    public static BufferedImage loadImage(String resdir) {
        BufferedImage image = null;
        InputStream in = loadStream(resdir);

        if (in == null)
            return null;

        try {
            image = ImageIO.read(in);
            in.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return image;
    }
    
    public static Icon loadIcon(String resdir) {
        return new ImageIcon(loadImage(resdir));
    }
    
    // Helpers for map object sprites
    public static BufferedImage getTargetImg(int target) {
        switch(target) {
            case 82: target = 78; break;
            case 95: target = 93; break;
        }
        
        return loadImage(String.format("targets/%d.png", target));
    }
    
    public static BufferedImage getNpcImg(int npc) {
        return loadImage(String.format("npcs/%d.png", npc));
    }
    
    public static BufferedImage getMonImg(int mon, int form) {
        return loadImage(String.format("mons/%d_%d.png", mon, form));
    }
}
