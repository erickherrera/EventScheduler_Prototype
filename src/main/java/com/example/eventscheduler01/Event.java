package com.example.eventscheduler01;

import java.time.LocalDateTime;

public class Event {
    private String title; // Title of the event
    private LocalDateTime dateTime; // Date and time of the event

    // Constructor to initialize the Event object
    public Event(String title, LocalDateTime dateTime) {
        this.title = title;
        this.dateTime = dateTime;
    }

    // Getter for the title
    public String getTitle() {
        return title;
    }

    // Setter for the title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for the date and time
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Setter for the date and time
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // Optional: Override toString() method for easy printing of Event details
    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}

