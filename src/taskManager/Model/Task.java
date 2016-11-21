/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Даниил
 */
public class Task implements Serializable
{
    
    private String author;

    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setText(String text) 
    {
        this.text = text;
    }

    public void setDate(Date date) 
    {
        this.date = date;
    }

    public void setTime(long time) 
    {
        this.time = time;
    }
    
    private String name;
    private String text;
    private Date date; 
    private long time;


    public Task(String name,String author,Date data,long time, String text)
    {
        this.author = author;
        this.name = name;
        this.text = text;  
        this.date = data;
        this.time = time;
    }

    public String getAuthor() 
    {
        return author;
    }

    public String getName() 
    {
        return name;
    }

    public String getText() 
    {
        return text;
    }

    public Date getDate() 
    {
        return date;
    }

    public long getTime() 
    {
        return time;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;
        if(! (o instanceof Task)) return false;
        Task other = (Task)o;
        if(!author.equals(other.author)) return false;
        if(!name.equals(other.name)) return false;
        if(!text.equals(other.text)) return false;
        if(!date.equals(other.date)) return false;
        if(time != other.time) return false;
        return true;
    }
    @Override
    public String toString()
    {
        return author + name + text + date + time;
    }
    
    
    
}
