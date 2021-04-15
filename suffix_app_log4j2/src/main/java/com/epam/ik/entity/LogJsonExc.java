package com.epam.ik.entity;

import com.epam.ik.util.logging.MyLevels;

import java.sql.Timestamp;
import java.util.Arrays;

public class LogJsonExc
{
    private Timestamp dateTime;
    private String message;
    private MyLevels severityLabel;
    private String exception;

    public LogJsonExc(String message, MyLevels severityLabel, Throwable e) {
        dateTime = new Timestamp(System.currentTimeMillis());
        this.message = message;
        this.severityLabel = severityLabel;
        this.exception = Arrays.toString(e.getStackTrace()) + e;
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

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "LogJsonExc{" +
                "dateTime=" + dateTime +
                ", message='" + message + '\'' +
                ", severityLabel=" + severityLabel +
                ", exception='" + exception + '\'' +
                '}';
    }
}
