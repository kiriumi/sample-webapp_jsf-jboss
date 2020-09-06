package dto.custom;

import inject.Dto;
import lombok.Data;

@Dto
@Data
public class UserRole {

    private String emailaddress;

    private String lastName;

    private String firstName;

    private String role;

}