package org.figurate.lambda;

/**
 * Represents the supported Lambda event structure.
 *
 * Supported event types:
 *
 *  * vevent-publish - transform input calendar for publishing events and send to SNS publish topic (if configured)
 */
public class CalendarUserAgentEvent {

    private String method;

    private String calendar;

    private String[] recipients;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }
}
