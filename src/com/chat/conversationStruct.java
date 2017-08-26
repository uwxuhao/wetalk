package com.chat;

public class conversationStruct {
    private String content;
    private String from;
    private String to;

    public conversationStruct(String content, String from, String to) {
        this.content = content;
        this.from = from;
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String toString(){
        return from + ": " + content + "\n";
    }

}
