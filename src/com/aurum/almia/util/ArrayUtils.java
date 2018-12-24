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

public final class ArrayUtils {
    private ArrayUtils() {}
    
    /**
     * Returns the next index of the specified value, starting from the given offset.
     * @param arr the array
     * @param val the value to be found
     * @param off the starting offset
     * @return the next index of the specified value. -1 if the value was not found.
     */
    public static int indexOf(byte[] arr, byte val, int off) {
        while(off < arr.length) {
            if (arr[off] == val)
                return off;
            off++;
        }
        
        return -1;
    }
    
    /**
     * Returns the next index of the specified value, starting from the given offset.
     * @param arr the array
     * @param val the value to be found
     * @param off the starting offset
     * @return the next index of the specified value. -1 if the value was not found.
     */
    public static int indexOf(short[] arr, short val, int off) {
        while(off < arr.length) {
            if (arr[off] == val)
                return off;
            off++;
        }
        
        return -1;
    }
    
    /**
     * Returns the next index of the specified value, starting from the given offset.
     * @param arr the array
     * @param val the value to be found
     * @param off the starting offset
     * @return the next index of the specified value. -1 if the value was not found.
     */
    public static int indexOf(int[] arr, int val, int off) {
        while(off < arr.length) {
            if (arr[off] == val)
                return off;
            off++;
        }
        
        return -1;
    }
    
    /**
     * Returns the next index of the specified value, starting from the given offset.
     * @param arr the array
     * @param val the value to be found
     * @param off the starting offset
     * @return the next index of the specified value. -1 if the value was not found.
     */
    public static int indexOf(long[] arr, long val, int off) {
        while(off < arr.length) {
            if (arr[off] == val)
                return off;
            off++;
        }
        
        return -1;
    }
    
    /**
     * Returns the next index of the specified value, starting from the given offset.
     * @param arr the array
     * @param val the value to be found
     * @param off the starting offset
     * @return the next index of the specified value. -1 if the value was not found.
     */
    public static int indexOf(float[] arr, float val, int off) {
        while(off < arr.length) {
            if (arr[off] == val)
                return off;
            off++;
        }
        
        return -1;
    }
    
    /**
     * Returns the next index of the specified value, starting from the given offset.
     * @param arr the array
     * @param val the value to be found
     * @param off the starting offset
     * @return the next index of the specified value. -1 if the value was not found.
     */
    public static int indexOf(double[] arr, double val, int off) {
        while(off < arr.length) {
            if (arr[off] == val)
                return off;
            off++;
        }
        
        return -1;
    }
    
    /**
     * Returns the next index of the specified value, starting from the given offset.
     * @param arr the array
     * @param val the value to be found
     * @param off the starting offset
     * @return the next index of the specified value. -1 if the value was not found.
     */
    public static int indexOf(boolean[] arr, boolean val, int off) {
        while(off < arr.length) {
            if (arr[off] == val)
                return off;
            off++;
        }
        
        return -1;
    }
    
    /**
     * Returns the next index of the specified value, starting from the given offset.
     * @param arr the array
     * @param val the value to be found
     * @param off the starting offset
     * @return the next index of the specified value. -1 if the value was not found.
     */
    public static int indexOf(char[] arr, char val, int off) {
        while(off < arr.length) {
            if (arr[off] == val)
                return off;
            off++;
        }
        
        return -1;
    }
    
    /**
     * Returns the next index of the specified value, starting from the given offset.
     * @param arr the array
     * @param val the value to be found
     * @param off the starting offset
     * @return the next index of the specified value. -1 if the value was not found.
     */
    public static int indexOf(Object[] arr, Object val, int off) {
        while(off < arr.length) {
            if (arr[off] == val)
                return off;
            off++;
        }
        
        return -1;
    }
    
    /**
     * Creates a sequence of the specified value with a length of {@code len} elements.
     * @param len the length of the sequence
     * @param val the value
     * @return the sequence as an array.
     */
    public static byte[] sequence(int len, byte val) {
        byte[] sequence = new byte[len];
        for (int i = 0 ; i < len ; i++)
            sequence[i] = val;
        
        return sequence;
    }
    
    /**
     * Creates a sequence of the specified value with a length of {@code len} elements.
     * @param len the length of the sequence
     * @param val the value
     * @return the sequence as an array.
     */
    public static short[] sequence(int len, short val) {
        short[] sequence = new short[len];
        for (int i = 0 ; i < len ; i++)
            sequence[i] = val;
        
        return sequence;
    }
    
    /**
     * Creates a sequence of the specified value with a length of {@code len} elements.
     * @param len the length of the sequence
     * @param val the value
     * @return the sequence as an array.
     */
    public static int[] sequence(int len, int val) {
        int[] sequence = new int[len];
        for (int i = 0 ; i < len ; i++)
            sequence[i] = val;
        
        return sequence;
    }
    
    /**
     * Creates a sequence of the specified value with a length of {@code len} elements.
     * @param len the length of the sequence
     * @param val the value
     * @return the sequence as an array.
     */
    public static long[] sequence(int len, long val) {
        long[] sequence = new long[len];
        for (int i = 0 ; i < len ; i++)
            sequence[i] = val;
        
        return sequence;
    }
    
    /**
     * Creates a sequence of the specified value with a length of {@code len} elements.
     * @param len the length of the sequence
     * @param val the value
     * @return the sequence as an array.
     */
    public static float[] sequence(int len, float val) {
        float[] sequence = new float[len];
        for (int i = 0 ; i < len ; i++)
            sequence[i] = val;
        
        return sequence;
    }
    
    /**
     * Creates a sequence of the specified value with a length of {@code len} elements.
     * @param len the length of the sequence
     * @param val the value
     * @return the sequence as an array.
     */
    public static double[] sequence(int len, double val) {
        double[] sequence = new double[len];
        for (int i = 0 ; i < len ; i++)
            sequence[i] = val;
        
        return sequence;
    }
    
    /**
     * Creates a sequence of the specified value with a length of {@code len} elements.
     * @param len the length of the sequence
     * @param val the value
     * @return the sequence as an array.
     */
    public static boolean[] sequence(int len, boolean val) {
        boolean[] sequence = new boolean[len];
        for (int i = 0 ; i < len ; i++)
            sequence[i] = val;
        
        return sequence;
    }
    
    /**
     * Creates a sequence of the specified value with a length of {@code len} elements.
     * @param len the length of the sequence
     * @param val the value
     * @return the sequence as an array.
     */
    public static char[] sequence(int len, char val) {
        char[] sequence = new char[len];
        for (int i = 0 ; i < len ; i++)
            sequence[i] = val;
        
        return sequence;
    }
    
    /**
     * Creates a sequence of the specified value with a length of {@code len} elements.
     * @param len the length of the sequence
     * @param val the value
     * @return the sequence as an array.
     */
    public static Object[] sequence(int len, Object val) {
        Object[] sequence = new Object[len];
        for (int i = 0 ; i < len ; i++)
            sequence[i] = val;
        
        return sequence;
    }
    
    /**
     * Reverses the order of the elements in the given array.
     * @param arr the array
     * @return the array with the order of its elements reversed.
     */
    public static byte[] reverse(byte[] arr) {
        byte[] reversed = new byte[arr.length];
        for (int l = 0, r = arr.length - 1 ; l <= arr.length && r >= 0 ; l++, r--)
            reversed[l] = arr[r];
        
        return reversed;
    }
    
    /**
     * Reverses the order of the elements in the given array.
     * @param arr the array
     * @return the array with the order of its elements reversed.
     */
    public static short[] reverse(short[] arr) {
        short[] reversed = new short[arr.length];
        for (int l = 0, r = arr.length - 1 ; l <= arr.length && r >= 0 ; l++, r--)
            reversed[l] = arr[r];
        
        return reversed;
    }
    
    /**
     * Reverses the order of the elements in the given array.
     * @param arr the array
     * @return the array with the order of its elements reversed.
     */
    public static int[] reverse(int[] arr) {
        int[] reversed = new int[arr.length];
        for (int l = 0, r = arr.length - 1 ; l <= arr.length && r >= 0 ; l++, r--)
            reversed[l] = arr[r];
        
        return reversed;
    }
    
    /**
     * Reverses the order of the elements in the given array.
     * @param arr the array
     * @return the array with the order of its elements reversed.
     */
    public static long[] reverse(long[] arr) {
        long[] reversed = new long[arr.length];
        
        for (int l = 0, r = arr.length - 1 ; l <= arr.length && r >= 0 ; l++, r--)
            reversed[l] = arr[r];
        
        return reversed;
    }
    
    /**
     * Reverses the order of the elements in the given array.
     * @param arr the array
     * @return the array with the order of its elements reversed.
     */
    public static float[] reverse(float[] arr) {
        float[] reversed = new float[arr.length];
        for (int l = 0, r = arr.length - 1 ; l <= arr.length && r >= 0 ; l++, r--)
            reversed[l] = arr[r];
        
        return reversed;
    }
    
    /**
     * Reverses the order of the elements in the given array.
     * @param arr the array
     * @return the array with the order of its elements reversed.
     */
    public static double[] reverse(double[] arr) {
        double[] reversed = new double[arr.length];
        for (int l = 0, r = arr.length - 1 ; l <= arr.length && r >= 0 ; l++, r--)
            reversed[l] = arr[r];
        
        return reversed;
    }
    
    /**
     * Reverses the order of the elements in the given array.
     * @param arr the array
     * @return the array with the order of its elements reversed.
     */
    public static boolean[] reverse(boolean[] arr) {
        boolean[] reversed = new boolean[arr.length];
        for (int l = 0, r = arr.length - 1 ; l <= arr.length && r >= 0 ; l++, r--)
            reversed[l] = arr[r];
        
        return reversed;
    }
    
    /**
     * Reverses the order of the elements in the given array.
     * @param arr the array
     * @return the array with the order of its elements reversed.
     */
    public static char[] reverse(char[] arr) {
        char[] reversed = new char[arr.length];
        for (int l = 0, r = arr.length - 1 ; l <= arr.length && r >= 0 ; l++, r--)
            reversed[l] = arr[r];
        
        return reversed;
    }
    
    /**
     * Reverses the order of the elements in the given array.
     * @param arr the array
     * @return the array with the order of its elements reversed.
     */
    public static Object[] reverse(Object[] arr) {
        Object[] reversed = new Object[arr.length];
        for (int l = 0, r = arr.length - 1 ; l <= arr.length && r >= 0 ; l++, r--)
            reversed[l] = arr[r];
        
        return reversed;
    }
    
    /**
     * Checks if the array contains the specified element.
     * @param arr the array
     * @param val the checked element
     * @return true if the array contains the element.
     */
    public static boolean arrayContains(byte[] arr, byte val) {
        for (byte i : arr)
            if (i == val)
                return true;
        
        return false;
    }
    
    /**
     * Checks if the array contains the specified element.
     * @param arr the array
     * @param val the checked element
     * @return true if the array contains the element.
     */
    public static boolean arrayContains(short[] arr, short val) {
        for (int i : arr)
            if (i == val)
                return true;
        
        return false;
    }
    
    /**
     * Checks if the array contains the specified element.
     * @param arr the array
     * @param val the checked element
     * @return true if the array contains the element.
     */
    public static boolean arrayContains(int[] arr, int val) {
        for (int i : arr)
            if (i == val)
                return true;
        
        return false;
    }
    
    /**
     * Checks if the array contains the specified element.
     * @param arr the array
     * @param val the checked element
     * @return true if the array contains the element.
     */
    public static boolean arrayContains(long[] arr, long val) {
        for (long i : arr)
            if (i == val)
                return true;
        
        return false;
    }
    
    /**
     * Checks if the array contains the specified element.
     * @param arr the array
     * @param val the checked element
     * @return true if the array contains the element.
     */
    public static boolean arrayContains(float[] arr, float val) {
        for (float i : arr)
            if (i == val)
                return true;
        
        return false;
    }
    
    /**
     * Checks if the array contains the specified element.
     * @param arr the array
     * @param val the checked element
     * @return true if the array contains the element.
     */
    public static boolean arrayContains(double[] arr, double val) {
        for (double i : arr)
            if (i == val)
                return true;
        
        return false;
    }
    
    /**
     * Checks if the array contains the specified element.
     * @param arr the array
     * @param val the checked element
     * @return true if the array contains the element.
     */
    public static boolean arrayContains(boolean[] arr, boolean val) {
        for (boolean i : arr)
            if (i == val)
                return true;
        
        return false;
    }
    
    /**
     * Checks if the array contains the specified element.
     * @param arr the array
     * @param val the checked element
     * @return true if the array contains the element.
     */
    public static boolean arrayContains(char[] arr, char val) {
        for (char i : arr)
            if (i == val)
                return true;
        
        return false;
    }
    
    /**
     * Checks if the array contains the specified element.
     * @param arr the array
     * @param val the checked element
     * @return true if the array contains the element.
     */
    public static boolean arrayContains(Object[] arr, Object val) {
        for (Object i : arr)
            if (i == val)
                return true;
        
        return false;
    }
}