package dto;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "Dali", date = "2020-08-01T23:36:54.584+0900")
@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, String> emailaddress;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> postalCode;
    public static volatile SingularAttribute<User, String> region;
    public static volatile SingularAttribute<User, String> locality;
    public static volatile SingularAttribute<User, String> streetAddress;
    public static volatile SingularAttribute<User, String> extendedAddress;
    public static volatile SingularAttribute<User, Integer> version;
    public static volatile SingularAttribute<User, LocalDateTime> createdtime;
    public static volatile SingularAttribute<User, LocalDateTime> updatedtime;
    public static volatile ListAttribute<User, Role> roles;
}
