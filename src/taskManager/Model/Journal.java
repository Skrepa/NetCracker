/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Controller;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Даниил
 */
public class Journal implements Serializable
{
    static class IDManager
    {
        
        private static HashSet<Integer> used = new HashSet<>();
        
        private static Random rand = new Random();
        
        public static void freeID(int id)
        {
            used.remove(id);
        }
        
        public static int nextID()
        {
            int id;
            do
            {
                id = rand.nextInt();
            }
            while(used.contains(id) || id<0);
            return id;
        }
    }
    private HashMap<Integer,Task> arr = new HashMap<>();
    
  
    public int addTask(Task task)
    {
        int id = IDManager.nextID();
        arr.put(id, task);
        return id;
    }
    
    public Task getTask(int ID)
    {
        return arr.get(ID);
    }
    public Set<Integer> getIDs()
    {
        return arr.keySet();
    }
    
    public void deleteTask(int ID)
    {
        if(!arr.containsKey(ID)) throw new IndexOutOfBoundsException();
        arr.remove(ID);
        IDManager.freeID(ID);
    }
}
