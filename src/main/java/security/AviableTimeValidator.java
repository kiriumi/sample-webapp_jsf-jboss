package security;

import java.time.LocalTime;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

@RequestScoped
public class AviableTimeValidator {

    @Inject
    ExternalContext externalContext;

    public boolean available() {

        if (isAvailableTime()) {
            return true;
        }
        return false;
    }

    private boolean isAvailableTime() {

        LocalTime now = LocalTime.now();
        LocalTime startTime = LocalTime.of(0, 0);
        LocalTime endTime = LocalTime.of(23, 59);

        return now.isAfter(startTime) && now.isBefore(endTime);
    }
}
