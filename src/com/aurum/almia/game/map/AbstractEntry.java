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

package com.aurum.almia.game.map;

import java.awt.Graphics;

public abstract class AbstractEntry<D extends AbstractData> implements Cloneable {
    public final D parent;
    
    public AbstractEntry(D parent) {
        this.parent = parent;
    }
    
    public AbstractEntry clone() {
        try {
            return (AbstractEntry)super.clone();
        }
        catch(CloneNotSupportedException ex) {}
        
        return null;
    }
    
    public void render(Graphics g) {}
}