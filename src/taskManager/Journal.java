/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Даниил
 */
public class Journal implements Serializable{
    private final ArrayList<Task> arr = new ArrayList<>();
    
    public Task createTask(String name,String author,LocalDateTime data,LocalTime time,String text)
    {
        Task task = new Task(name,author,data,time,text);
        arr.add(task);
        return task;
    }
    
    public void deleteTask(Task o)
    {
        arr.remove(o);
    }
    
    public Task getTask(int i)
    {
        return arr.get(i);
    }
    
    public int getSize()
    {
        return arr.size();
    }
    
    public void setTask(int i, Task o)
    {
        arr.set(i, o);
    }
    
    
}
