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

import java.awt.Color;
import java.awt.Graphics;

public final class RenderHelper {
    private RenderHelper() {}
    
    public static void drawBox(Graphics g, int x, int y, int w, int h, Color out, Color in, boolean aligned) {
        if (aligned) {
            x -= w / 2;
            y -= h / 2;
        }

        g.setColor(out);
        g.drawRect(x, y, w, h);
        g.setColor(in);
        g.fillRect(x + 1, y + 1, w - 1, h - 1);
    }
}
