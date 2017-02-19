/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Journal;
import Model.Task;
import View.Notification;
import View.View;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

/**
 * класс для обработки нотификации
 * @author Daniil
 */
public class NotificationManager extends Thread {

    private HashMap<Integer, Task.Status> statuses = new HashMap<Integer, Task.Status>();
    
    private View view;
    
    private Journal journal;

    private static final Logger log = Logger.getLogger(Journal.class.getName());

    /**
     * Изменение статуса и обновление окна журнала
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            Set<Integer> ids = Controller.getInstance().getJournal().getIDs();
            for (int id : ids) {
                Task task = Controller.getInstance().getJournal().getTask(id);
                if (statuses.containsKey(task.getID())) {
                    if (statuses.get(task.getID()) != task.getStatus()) {
                        Controller.getInstance().update();
                    }
                    if (task.getStatus() != Task.Status.ACTIVE && statuses.get(task.getID()) == Task.Status.ACTIVE) {
                        Notification.getInstance().show(task);
                    }
                    statuses.replace(task.getID(), task.getStatus());
                } else {
                    statuses.put(task.getID(), task.getStatus());
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                log.info(ex.getMessage());
            }
        }
    }
}
