package org.figurate.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;

import java.net.URI;

public class LambdaHandler implements RequestHandler<CalendarUserAgentEvent, String> {

    private final VEventHandler vEventHandler;

    public LambdaHandler() {
        ProdId prodId = new ProdId("-//figurate.org//calendar-user-agent//EN");
        Organizer organizer = new Organizer(URI.create("mailto:calendar-user-agent@figurate.org"));
        UidGenerator uidGenerator = new RandomUidGenerator();
        vEventHandler = new VEventHandler(prodId, organizer, uidGenerator);
    }

    @Override
    public String handleRequest(CalendarUserAgentEvent input, Context context) {
        return vEventHandler.publish(input.getCalendar());
    }
}
