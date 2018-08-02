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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Lists {
    public static List<String> pokemon_name, fieldwaza_name, assist_mes, yakumono_name, obstacles;
    
    public static void init() {
        pokemon_name = populate("/res/lists/pokemon_name.txt");
        fieldwaza_name = populate("/res/lists/fieldwaza_name.txt");
        assist_mes = populate("/res/lists/assist_mes.txt");
        yakumono_name = populate("/res/lists/yakumono_name.txt");
        obstacles = populate("/res/lists/obstacles.txt");
    }
    
    private static List<String> populate(String filepath) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Lists.class.getResourceAsStream(filepath)));
        List<String> list = new ArrayList();
        
        String line;
        try {
            while ((line = reader.readLine()) != null)
                list.add(line.trim());
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        
        return list;
    }
}