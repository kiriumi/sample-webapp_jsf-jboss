package application;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import domain.ChildWindowData;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Model
@Slf4j
public class GrandChildWindowBean extends BaseBackingBean {

    @Inject
    @Getter
    @Setter
    private ChildWindowData data;

}
