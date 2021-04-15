package com.epam.ik.entity;

import com.epam.ik.util.logging.MyLevels;

import java.sql.Timestamp;

public class LogJson
{
    private Timestamp dateTime;
    private String message;
    private MyLevels severityLabel;

    public LogJson(String message, MyLevels severityLabel) {
        dateTime = new Timestamp(System.currentTimeMillis());
        this.message = message;
        this.severityLabel = severityLabel;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyLevels getSeverityLabel() {
        return severityLabel;
    }

    public void setSeverityLabel(MyLevels severityLabel) {
        this.severityLabel = severityLabel;
    }

    @Override
    public String toString() {
        return "LogJson{" +
                "dateTime=" + dateTime +
                ", message='" + message + '\'' +
                ", severityLabel=" + severityLabel +
                '}';
    }
}
