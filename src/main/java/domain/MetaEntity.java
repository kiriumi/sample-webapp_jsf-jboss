package domain;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class MetaEntity {

	private String createdTime;

	private String updatedTime;
}
