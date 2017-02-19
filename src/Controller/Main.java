/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import snoozesoft.systray4j.SysTrayMenu;
import snoozesoft.systray4j.SysTrayMenuEvent;
import snoozesoft.systray4j.SysTrayMenuIcon;
import snoozesoft.systray4j.SysTrayMenuItem;
import snoozesoft.systray4j.SysTrayMenuListener;

/**
 * Главный класс для запуска программы
 *
 * @author Daniil
 */
public class Main implements SysTrayMenuListener {

    private SysTrayMenu menu;
    private static NotificationManager notificationManager;
    /**
     * Создание трея
     */
    public void createMenu() {
        SysTrayMenuItem itemOpen = new SysTrayMenuItem("Open", "Open");
        SysTrayMenuItem itemExit = new SysTrayMenuItem("Exit", "Exit");
        itemOpen.addSysTrayMenuListener(this);
        itemExit.addSysTrayMenuListener(new SysTrayMenuListener() {
            @Override
            public void menuItemSelected(SysTrayMenuEvent stme) {
                System.exit(0);
            }

            @Override
            public void iconLeftClicked(SysTrayMenuEvent stme) {
                System.exit(0);
            }

            @Override
            public void iconLeftDoubleClicked(SysTrayMenuEvent stme) {
            }

        });

        menu = new SysTrayMenu(new SysTrayMenuIcon("View/icon.ico"), "SysTray for Java rules!");
        menu.addItem(itemOpen);
        menu.addItem(itemExit);

    }

    @Override
    public void menuItemSelected(SysTrayMenuEvent stme) {
        Controller.getInstance().openView();
    }

    @Override
    public void iconLeftClicked(SysTrayMenuEvent stme) {
        Controller.getInstance().openView();
    }

    @Override
    public void iconLeftDoubleClicked(SysTrayMenuEvent stme) {
    }
    /**
     * Запуск программы
     * @param args 
     */
    public static void main(String[] args) {
        Main mainObject = new Main();
        notificationManager = new NotificationManager();
        notificationManager.setDaemon(true);
        notificationManager.start();
        mainObject.createMenu();
        Controller.getInstance().openView();
    }

}
