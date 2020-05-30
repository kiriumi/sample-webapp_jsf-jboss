package application;

import javax.enterprise.inject.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Model
@Data
@EqualsAndHashCode(callSuper = false)
public class BootstrapSamplesBean extends BaseBackingBean {

}
