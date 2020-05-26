package security;

import java.time.LocalTime;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
public class AviableValidator {

    @Inject
    ExternalContext externalContext;

    public boolean available() {

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        if (request.isUserInRole("admin")) {
            return true;
        }

        if (isAvailableTime()) {
            return true;
        }

        return false;
    }

    private boolean isAvailableTime() {

        LocalTime now = LocalTime.now();
        LocalTime nightTime = LocalTime.of(23, 59);
        LocalTime morningTime = LocalTime.of(7, 0);

        return now.isAfter(morningTime) && now.isBefore(nightTime);
    }
}
