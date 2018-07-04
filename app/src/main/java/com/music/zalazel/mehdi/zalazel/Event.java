package com.music.zalazel.mehdi.zalazel;

public class Event {
    private String title;
    private long time;
    private int tsunamiAlert;
    Event(String eventTitle, long eventTime, int eventTsunamiAlert) {
        title = eventTitle;
        time = eventTime;
        tsunamiAlert = eventTsunamiAlert;
    }
    public String getTitle() {
        return title;
    }
    public int getTsunamiAlert() {
        return tsunamiAlert;
    }
    public long getTime() {
        return time;
    }}
