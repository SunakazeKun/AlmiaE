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

import com.aurum.almia.game.param.BattlePokemon;
import com.aurum.almia.game.param.PokeID;
import com.aurum.almia.Utils;
import java.io.File;
import java.io.IOException;

public class Game {
    public static Game current;
    
    //--------------------------------------------------------------------------
    
    public File filesystem;
    public PokeID monDataBase;
    public BattlePokemon monDataBattle;
    
    //--------------------------------------------------------------------------
    
    public Game(File fs) {
        this.filesystem = fs;
        this.monDataBase = null;
        this.monDataBattle = null;
    }
    
    public Game(String fsdir) {
        this(new File(fsdir));
    }
    
    @Override
    public String toString() {
        return filesystem.getAbsolutePath();
    }
    
    public String getPath() {
        return filesystem.getAbsolutePath();
    }
    
    public String getFullPath(String file) {
        return String.format("%s/%s", getPath(), file);
    }
    
    public void init() throws IOException {
        monDataBase = new PokeID(this, Utils.loadFileIntoBuffer(getFullPath("param/PokeID.bin")));
        monDataBattle = new BattlePokemon(this, Utils.loadFileIntoBuffer(getFullPath("param/BattlePokemon.bin")));
    }
}