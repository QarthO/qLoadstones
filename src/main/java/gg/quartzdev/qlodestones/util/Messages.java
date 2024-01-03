package gg.quartzdev.qlodestones.util;

public enum  Messages {

    //    Plugin info
    CONSOLE_PREFIX("<gray>[<red>q<aqua>Lodestones<gray>]"),
    CHAT_PREFIX("<red>q<aqua>Lodestones <bold><gray>></bold>"),
    PLUGIN_INFO("<prefix> <green>Running version <gray><version>"),
    RELOAD_COMPLETE("<prefix> <green>Config reloaded");

    private final String message;
    private String parsedMessage;

    Messages(String msg){
        this.message = msg;
        this.parsedMessage = msg;
    }

    @Override
    public String toString(){
        return this.message;
    }

    public String get(){
        String result = this.parsedMessage;
        this.parsedMessage = this.message;
        return result;
    }

    public Messages parse(String placeholder, String value){
        this.parsedMessage = this.parsedMessage.replaceAll("<" + placeholder + ">", value);
        return this;
    }


}
