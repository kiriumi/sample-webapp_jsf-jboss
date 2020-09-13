package application;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("commonaized")
public class Commonaized1 extends CommonizationBean {

    @Override
    public String action() {

        System.out.println(getItem1());
        System.out.println("私は１の方だ");
        return null;
    }

}
