/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Journal;

/**
 * интерфейс 
 * @author Даниил
 */
public interface View 
{
    /**
     * обновление обрабатываемого журнала
     * @param journal журнал событий
     */
    void update(Journal journal);
    /**
     * вывод ошибки на экран
     * @param e сообщение ошибки
     */
    void showException(Exception e);
    /**
     * показать или скрыть окно на экране
     * @param b true or false
     */
    void setVisible(boolean b);
}
