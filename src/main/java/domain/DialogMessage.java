package domain;

import lombok.Getter;

@Getter
public class DialogMessage {

    public enum Level {
        INFO, WARN, ERROR,
    }

    private final Level level;

    private final String message;

    public DialogMessage(final Level level, final String message) {
        this.level = level;
        this.message = message;
    }

}
