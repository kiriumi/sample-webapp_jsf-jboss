package common;

import javax.enterprise.inject.Model;

import lombok.Getter;
import lombok.Setter;

@Model
public class SharedBean {

    @Getter
    @Setter
    private String name;
}
