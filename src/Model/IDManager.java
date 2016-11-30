/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashSet;
import java.util.Random;

/**
 * генератор идентификаторов
 * @author Daniil
 */
public class IDManager {

    private static HashSet<Integer> used = new HashSet<>();

    private static Random rand = new Random();
    /**
     * получение следующего не занятого идентификатора
     * @return следующий не занятый идентификатор
     */
    public synchronized static int nextID() 
    {
        int id;
        do 
        {
            id = rand.nextInt();
        } 
        while (used.contains(id) || id < 0);
        return id;
    }

}
