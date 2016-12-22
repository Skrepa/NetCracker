/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.IDManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import Model.Journal;
import Model.Task;
import View.MainScreen;
import View.View;
import exception.UnspecifiedEventException;

/**
 * класс для управления всей программой
 * @author Даниил
 */
public class Controller {

    private static Controller instance;

    private Journal journal;

    private View view;
    
    private static final String FileName = "TaskManager.file";
    
    /**
     * 
     * @return singleton instance
     */
    public static synchronized Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    private Controller() 
    {
        journal = new Journal();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FileName)))) 
        {
            journal = (Journal) in.readObject();
        } catch (IOException e) {
            if (view != null) {
                view.showException(e);
            }
        } catch (ClassNotFoundException e) {
            if (view != null) {
                view.showException(e);
            }
        }
        IDManager.addUsed(journal.getIDs());
    }
    /**
     * Создание события
     * @param name название события
     * @param author автор события
     * @param data дата события
     * @param time время за которое нужно напомнить о событии
     * @param text текст события
     */
    public void createTask(String name, String author, Date data, long time, String text) 
    {
        try {
            Task task = new Task(name, author, data, time, text);
            journal.addTask(task);
            save();
            update();
        } catch (Exception e) {
            if (view != null) {
                view.showException(e);
            } else {
                throw e;
            }
        }
    }
    /**
     * Удаление события
     * @param ID идентификатор события
     */
    public void deleteTask(int ID)
    {

        try {
            if (journal.getTask(ID) == null) {
                throw new UnspecifiedEventException("Вы не выбрали напоминание для удаления");
            }
            journal.deleteTask(ID);

            save();
            update();
        } catch (UnspecifiedEventException e) {
            if (view != null) {
                view.showException(e);
            }
        }
    }
    /**
     * Изменение события
     * @param ID идентификатор события
     * @param name название события
     * @param author автор события
     * @param data дата события
     * @param time время за которое нужно напомнить о событии
     * @param text текст события
     */
    public void setTask(int ID, String name, String author, Date data, long time, String text) 
    {
        {
            try {
                Task task = journal.getTask(ID);
                task.setName(name);
                task.setAuthor(author);
                task.setDate(data);
                task.setTime(time);
                task.setText(text);
                save();
                update();
            } catch (Exception e) {
                if (view != null) {
                    view.showException(e);
                } else {
                    throw e;
                }
            }
        }

    }
    /**
     * получение события по его идентфиатору
     * @param ID
     * @return событие
     */
    public Task getTask(int ID) 
    {
        try {
            return journal.getTask(ID);
        } catch (Exception e) {
            if (view != null) {
                view.showException(e);
            }
            throw e;
        }
    }
    /**
     * сохранение журнала
     */
    public synchronized void save() 
    {
        synchronized (journal) 
        {
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FileName))))
            {
                out.writeObject(journal);
                out.flush();
            } catch (IOException e) {
                if (view != null) {
                    view.showException(e);
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    /**
     * Открытие окна журнала
     */
    public void openView() 
    {
        this.view = new MainScreen();
        view.setVisible(true);
        update();
    }
    /**
     * Получение журнала
     * @return журнал
     */
    public Journal getJournal(){
        return journal;
    }
    public void update(){
        if(view!=null)
            view.update(journal);
    }
    
}
