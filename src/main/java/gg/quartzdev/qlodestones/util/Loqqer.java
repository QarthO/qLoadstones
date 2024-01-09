package gg.quartzdev.qlodestones.util;

import org.bukkit.Bukkit;

public class Loqqer {
    /**
     * Logs a general message
     * @param msg
     */
    public void log(String msg){
        Sender.message(Bukkit.getConsoleSender(), msg);
    }
    /**
     * Logs a general message
     * @param msg
     */
    public void log(Messages msg){
        Sender.message(Bukkit.getConsoleSender(), msg);
    }

    /**
     * Logs a warning
     * @param msg
     */
    public void warning(String msg){
        log("<yellow>" + msg);
    }

    /**
     * Logs error
     * @param msg
     */
    public void error(String msg){
        log("<red>" + msg);
//        TODO: log errors to a file
    }

    public void error(Messages msg){
        log("<red>" + msg.get());
//        TODO: log errors to a file
    }

    public void error(StackTraceElement[] error){
        for(StackTraceElement e : error){
            error(e.toString());
        }
    }

}
