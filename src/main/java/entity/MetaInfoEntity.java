package entity;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class MetaInfoEntity {

	private String createdTime;

	private String updateTime;
}
