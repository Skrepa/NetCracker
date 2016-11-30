/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Task;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniil
 */
public class Notification extends Component {
    private static Notification instance;
    public static Notification getInstance(){
        if(instance == null)
            instance = new Notification();
        return instance;
    }
    private Notification(){}
    public void show(Task t){
        JOptionPane.showMessageDialog(this, t.getName() + "\n" + t.getText() + "\n" + t.getDate(), "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
}
