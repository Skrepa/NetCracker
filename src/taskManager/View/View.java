/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Journal;

/**
 *
 * @author Даниил
 */
public interface View 
{
    void update(Journal journal);
    
    void showException(Exception e);
}
