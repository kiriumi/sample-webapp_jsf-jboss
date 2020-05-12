package application;

import javax.enterprise.inject.Model;

import lombok.Getter;

@Model
public class SystemErrorBean extends BaseBackingBean {

    @Getter
    private String sample;

    public void viewAction() {
        this.sample = "エラーだよ";
    }
}
