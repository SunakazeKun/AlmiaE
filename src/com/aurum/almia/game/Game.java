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

import com.aurum.almia.Lists;
import com.aurum.almia.game.param.BattlePokemon;
import com.aurum.almia.game.param.PokeID;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static Game current;
    
    public File filesystem;
    public PokeID monDataBase;
    public BattlePokemon monDataBattle;
    
    public Game(String fsdir) {
        this(new File(fsdir));
    }
    
    public Game(File fs) {
        this.filesystem = fs;
        this.monDataBase = null;
        this.monDataBattle = null;
    }
    
    @Override
    public String toString() {
        return filesystem.getAbsolutePath();
    }
    
    public String getFilePath(String file) {
        return String.format("%s/%s", toString(), file);
    }
    
    public File getFile(String file) {
        return new File(getFilePath(file));
    }
    
    public void init() throws IOException {
        monDataBase = new PokeID(this);
        //monDataBattle = new BattlePokemon(this);
    }
    
    public List<String> getMapsList() {
        File[] files = getFile("field/map").listFiles();
        List<String> list = new ArrayList();
        
        for (File file : files) {
            if (!file.isFile())
                continue;
            
            String filename = file.getName();
            
            if (filename.endsWith(".map.dat.lz"))
                list.add(filename.replace(".map.dat.lz", ""));
        }
        
        return list;
    }
    
    public List<String> getPokemonList() {
        List<String> list = new ArrayList();
        
        for (int i = 0 ; i < monDataBase.entries.size() ; i++)
            list.add(String.format("%03X: %s", i, monDataBase.entries.get(i).toShortString()));
        
        return list;
    }
    
    public List<String> getTargetList() {
        List<String> list = new ArrayList();
        
        for (int i = 0 ; i < Lists.obstacles.size() ; i++)
            list.add(String.format("%02X: %s", i, Lists.obstacles.get(i)));
        
        return list;
    }
    
    public List<String> getNpcList() {
        List<String> list = new ArrayList();
        
        for (int i = 0 ; i < Lists.npcs.size() ; i++)
            list.add(String.format("%02X: %s", i, Lists.npcs.get(i)));
        
        return list;
    }
}
