/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskManager;

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

/**
 *
 * @author Даниил
 */
public class Controller {

    private static Controller instance;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    private Journal journal;
    View view;

    private Controller() {
        journal = new Journal();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("TaskManager.file")))) {
            journal=(Journal) in.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }

   
    
    public void add(String name,String author,LocalDateTime data,LocalTime time, String text)
    {
        journal.createTask(name, author, data, time, text);
        view.update(journal);
    }
    
    
    public void Start(View view)
    {
        this.view=view;
        view.update(journal);
    }
    
    public void Stop()
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("TaskManager.file")))) {
            out.writeObject(journal);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    

}
