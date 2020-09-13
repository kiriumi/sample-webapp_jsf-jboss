package application;

import javax.enterprise.context.RequestScoped;

@RequestScoped
//@Named("commonaized")
public class Commonaized2 extends CommonizationBean {

    @Override
    public String action() {

        System.out.println(getItem1());
        System.out.println("私は２の方だ");
        return null;
    }

}
