package dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "user", schema = "public") // Postgresの場合、スキーマ名が必要
@Data
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends MetaEntity implements Serializable {

    @Id
    private String emailaddress;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
