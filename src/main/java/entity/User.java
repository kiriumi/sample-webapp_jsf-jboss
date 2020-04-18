package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = false)
@XmlRootElement
public class User extends MetaInfoEntity {

	@Id
	@Email
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String emailAddress;

	@NotBlank
	private String name;

	@NotBlank
	private String password;
}
