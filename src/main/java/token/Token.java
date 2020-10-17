package token;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import lombok.Getter;
import lombok.Setter;

@SessionScoped
public class Token implements Serializable {

    @Getter
    @Setter
    private String token;


//    @Getter
//    @Setter
//    private String parentToken;
//
//    private Map<String, String> childrenToken = new HashMap<>();
//
//    public void put(String namespace, String token) {
//
//        if (isParentWindow(namespace)) {
//            this.parentToken = token;
//            childrenToken.clear();
//
//        } else {
//            childrenToken.put(namespace, token);
//        }
//
//    }
//
//    public String get(String namespace) {
//
//        if (isParentWindow(namespace)) {
//            return parentToken;
//        }
//
//        return childrenToken.get(namespace);
//    }
//
//    private boolean isParentWindow(String namespace) {
//        return StringUtils.isBlank(namespace) || namespace.equals("main");
//    }

}
