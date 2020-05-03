package domain;

import javax.ejb.Stateless;

import lombok.Getter;

@Stateless
@Getter
public class StatelessEjb {

    private final String hoge = "ほげ";
}
