/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Journal;
import Model.Task;
import View.Notification;
import java.util.HashMap;
import java.util.Set;

/**
 * класс для обработки нотификации
 * @author Daniil
 */
public class NotificationManager extends Thread {

    private Journal journal = null;
    private static HashMap<Integer, Task.Status> statuses = new HashMap<Integer, Task.Status>();
    /**
     * конструктор обработки нотификации
     * @param journal обрабатываемый журнал
     */
    public NotificationManager(Journal journal) {
        this.journal = journal;
    }
    /**
     * обновление обрабатываемого журнала
     * @param journal обрабатываемый журнал
     */
    public void update(Journal journal) {
        this.journal = journal;
    }

    @Override
    public void run() {
        while (true) {
            Set<Integer> ids = journal.getIDs();
            for (int id : ids) {
                Task task = journal.getTask(id);
                if (statuses.containsKey(task.getID())) {
                    if (statuses.get(task.getID()) != task.getStatus()) {
                        Controller.getInstance().setTask(task.getID(), task.getName(), task.getAuthor(), task.getDate(), task.getTime(), task.getText());
                    }
                    if (task.getStatus() != Task.Status.ACTIVE && statuses.get(task.getID()) == Task.Status.ACTIVE) {
                        Notification.getInstance().show(task);
                        statuses.replace(task.getID(), task.getStatus());
                    }
                } else {
                    statuses.put(task.getID(), task.getStatus());
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}