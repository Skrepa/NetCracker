/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Даниил
 */
public class Journal implements Serializable
{
    private HashMap<Integer,Task> arr = new HashMap<>();

    public void addTask(Task task)
    {
        arr.put(task.getID(), task);
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
    }
}
