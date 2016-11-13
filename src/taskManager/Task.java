/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Даниил
 */
public class Task implements Serializable{
    public final String author;
    public final String name;
    public final String text;
    public final LocalDateTime date; 
    public final LocalTime time;


    Task(String name,String author,LocalDateTime data,LocalTime time, String text)
    {
        this.author=author;
        this.name=name;
        this.text=text;  
        this.date=data;
        this.time=time;
    }
    
    
    
    
    
}
