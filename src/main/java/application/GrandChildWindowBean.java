package application;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import domain.ChildWindowData;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import token.TokenCheck;

@Model
@Slf4j
@TokenCheck(child = true)
public class GrandChildWindowBean extends BaseBackingBean {

    @Inject
    @Getter
    @Setter
    private ChildWindowData data;
}
