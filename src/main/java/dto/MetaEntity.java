package dto;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@MappedSuperclass
public class MetaEntity {

    @NotBlank
    private String createdtime;

    @NotBlank
    private String updatedtime;
}
