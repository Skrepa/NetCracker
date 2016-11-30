/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * журнал содержащий события
 * @author Даниил
 */
public class Journal implements Serializable {

    private HashMap<Integer, Task> arr = new HashMap<>();

    /**
     * добавление события
     * @param task
     */
    public void addTask(Task task) {
        arr.put(task.getID(), task);
    }
    /**
     * получение события по идентификатору
     * @param ID идентификатор
     * @return событие
     */
    public Task getTask(int ID) {
        return arr.get(ID);
    }
    /**
     * @return все идентификаторы
     */
    public Set<Integer> getIDs() {
        return arr.keySet();
    }
    /**
     * удаление события по его идентификатору
     * @param ID идентификатор
     */
    public void deleteTask(int ID) {
        if (!arr.containsKey(ID)) {
            throw new IndexOutOfBoundsException();
        }
        arr.remove(ID);
    }
}