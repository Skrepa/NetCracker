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
import java.util.Date;
import Model.Journal;
import Model.Task;
import View.MainScreen;
import View.View;
import snoozesoft.systray4j.SysTrayMenu;
import snoozesoft.systray4j.SysTrayMenuEvent;
import snoozesoft.systray4j.SysTrayMenuIcon;
import snoozesoft.systray4j.SysTrayMenuItem;
import snoozesoft.systray4j.SysTrayMenuListener;

/**
 * класс для управления всей программой
 * @author Даниил
 */
public class Controller implements SysTrayMenuListener {

    private static Controller instance;

    private Journal journal;

    private View view;

    private SysTrayMenu menu;
    
    NotificationManager notificationManager;
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
    static final String fileName = "TaskManager.file";

    private Controller() 
    {
        journal = new Journal();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) 
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
        notificationManager = new NotificationManager(journal);
        notificationManager.start();
    }
    /**
     * создание события
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
            view.update(journal);
        } catch (Exception e) {
            if (view != null) {
                view.showException(e);
            } else {
                throw e;
            }
        }
    }
    /**
     * удаление события
     * @param ID идентификатор события
     */
    public void deleteTask(int ID) 
    {

        try {
            if (journal.getTask(ID) == null) {
                throw new RuntimeException("Вы не выбрали напоминание для удаления");
            }
            journal.deleteTask(ID);

            save();
            view.update(journal);
        } catch (Exception e) {
            if (view != null) {
                view.showException(e);
            } else {
                throw e;
            }
        }
    }
    /**
     * изменение события
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
                journal.getTask(ID).setName(name);
                journal.getTask(ID).setAuthor(author);
                journal.getTask(ID).setDate(data);
                journal.getTask(ID).setTime(time);
                journal.getTask(ID).setText(text);
                save();
                view.update(journal);
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
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName))))
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

    public static void main(String[] args) {
        getInstance().createMenu();
        getInstance().openView();
        try {
            getInstance().notificationManager.join();
        } catch (InterruptedException ex) {
        }
    }

    private void openView() 
    {
        this.view = new MainScreen();
        view.setVisible(true);
        view.update(journal);
    }

    private void createMenu() 
    {
        SysTrayMenuItem itemOpen = new SysTrayMenuItem("Open", "Open");
        itemOpen.addSysTrayMenuListener(this);
        menu = new SysTrayMenu(new SysTrayMenuIcon("View/icon.ico"), "SysTray for Java rules!");
        menu.addItem(itemOpen);
    }
  
    @Override
    public void menuItemSelected(SysTrayMenuEvent stme) 
    {
        openView();
    }

    @Override
    public void iconLeftClicked(SysTrayMenuEvent stme) 
    {
        openView();
    }

    @Override
    public void iconLeftDoubleClicked(SysTrayMenuEvent stme) 
    {
    }
}
