package application;

import lombok.Getter;
import lombok.Setter;

public abstract class CommonizationBean extends BaseBackingBean {

    @Getter
    @Setter
    private String item1;

    public abstract String action();

}
