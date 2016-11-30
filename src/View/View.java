/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Journal;
import Model.Task;

/**
 *
 * @author Даниил
 */
public interface View 
{
    void update(Journal journal);
    
    void showException(Exception e);
    
    void setVisible(boolean b);
}
