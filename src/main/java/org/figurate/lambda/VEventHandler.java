package org.figurate.lambda;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import net.fortuna.ical4j.agent.VEventUserAgent;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.util.UidGenerator;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class VEventHandler {

    private final VEventUserAgent userAgent;

    private final AmazonSNS snsClient;

    public VEventHandler(ProdId prodId, Organizer organizer, UidGenerator uidGenerator) {
        userAgent = new VEventUserAgent(prodId, organizer, uidGenerator);
        snsClient = AmazonSNSClientBuilder.defaultClient();
    }

    public String publish(String calendarString) {
        try {
            Calendar calendar = new CalendarBuilder().build(new StringReader(calendarString));
            Calendar published = userAgent.publish(calendar.getComponents().get(Component.VEVENT).toArray(new VEvent[0]));

            StringWriter writer = new StringWriter();
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(published, writer);

            PublishRequest publishRequest = new PublishRequest().withTopicArn(System.getenv("PUBLISH_TOPIC_ARN"))
                    .withSubject("PUBLISH")
                    .withMessage(writer.toString());
            snsClient.publish(publishRequest);

            return writer.toString();
        } catch (IOException | ParserException e) {
            throw new RuntimeException(e);
        }
    }
}
