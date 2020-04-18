package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "USER") // テーブル名＝エンティティ名なら、nameは省略可能
@Data
@EqualsAndHashCode(callSuper = false)
@XmlRootElement // RESTでXMLを使って、本エンティティを受渡しできるようにするため
public class UserEntity extends MetaEntity implements Serializable {

	@Id
	@Email
	private String emailAddress;

	@NotBlank
	private String name;

	@NotBlank
	private String password;
}
