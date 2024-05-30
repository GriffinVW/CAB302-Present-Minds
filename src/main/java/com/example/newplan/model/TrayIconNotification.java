package com.example.newplan.model;

import java.awt.*;

public class TrayIconNotification {
    public void displayTray(String app, String message,String message2) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        String imagePath = "src/main/resources/com/example/newplan/images/THE LOGO.jpg";
        Image image = Toolkit.getDefaultToolkit().createImage(imagePath);
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("com/example/newplan/images/Present_Minds.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        String string = String.format("%1$s"+" "+"%2$s", app,message2);

        trayIcon.displayMessage(message, string, TrayIcon.MessageType.INFO);
    }
}
