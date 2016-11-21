/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import Model.Journal;
import Model.Task;
import View.View;
import java.util.ArrayList;

/**
 *
 * @author Даниил
 */
public class Controller
{

    private static Controller instance;

    public static synchronized Controller getInstance()
    {
        if (instance == null) 
        {
            instance = new Controller();
        }
        return instance;
    }
    static final String fileName = "TaskManager.file";
    
    private Journal journal;

    View view;


    private Controller() 
    {
        journal = new Journal();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) 
        {
            journal=(Journal) in.readObject();
        } catch (IOException e) 
        {
            if(view!=null)
                view.showException(e);
        } catch (ClassNotFoundException e)
        {
            if(view!=null)
                view.showException(e);
        }
    }
    
    public void createTask(String name,String author,Date data,long time,String text)
    {
        synchronized(journal)
        {
            Task task = new Task(name,author,data,time,text);
            journal.addTask(task);
            save();
            view.update(journal);
        }
    }
    
    public void deleteTask(int ID)
    {
        synchronized(journal)
        {
        try{
        journal.deleteTask(ID);
        save();
        view.update(journal);
        }catch(Exception e)
        {
            if(view!=null)
            view.showException(e);
        }
        }
    }
    
    public void setTask(int ID , String name,String author,Date data,long time,String text)
    {
        synchronized(journal)
        {
            journal.deleteTask(ID);
            Task task = new Task(name, author, data, time, text);
            journal.addTask(task);
            save();
            view.update(journal);
        }
        
    }
    public Task getTask(int ID)
    {
        synchronized(journal)
        {
            return journal.getTask(ID);
        }
        
    }
    
    public void setView(View view)
    {
        this.view=view;
        view.update(journal);
    }
    
    public void save()
    {
        synchronized(journal)
        {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("TaskManager.file"))))
        {
            out.writeObject(journal);
            out.flush();
        } catch (IOException e)
        {
            
            if(view!=null)
            view.showException(e);
        }
        }
    }
    

}
