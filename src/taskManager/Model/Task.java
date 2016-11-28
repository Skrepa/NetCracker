/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Даниил
 */
public class Task implements Serializable 
{
    private String author;
    private String name;
    private String text;
    private Date date;
    private long time;
    private int id;
    
    public enum Status 
    {
        ACTIVE,
        IN_PROGRESS,
        COMPLETED
    }

    public Status getStatus() 
    {
        Date left = new Date(getDate().getTime() - getTime() * 1000);
        Date now = new Date();
        if (now.before(left)) {
            return Status.ACTIVE;
        }
        if (now.before(getDate())) {
            return Status.IN_PROGRESS;
        }
        return Status.COMPLETED;
    }

    public int getID() 
    {
        return id;
    }

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

    public Task(String name, String author, Date data, long time, String text) 
    {
        id = IDManager.nextID();
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
        if (o == null) {
            return false;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task other = (Task) o;
        if (!author.equals(other.author)) {
            return false;
        }
        if (!name.equals(other.name)) {
            return false;
        }
        if (!text.equals(other.text)) {
            return false;
        }
        if (!date.equals(other.date)) {
            return false;
        }
        if (time != other.time) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        String a = "Автор:" + author + "\n" + "Название:" + name + "\n" + "Дата:" + date + "\n" + "Напомнить за:" + time + "\n" + "Текст:" + text;
        return a;
    }

}
