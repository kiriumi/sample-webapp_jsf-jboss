package validation;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

import domain.UserService;

/**
 * 認証の相関チェックアノテーション
 * ∴JSFはメソッドバリデーションができないので、使用不可
 * ∵JSFは、1つの入力値に対してValidate#validatePropertyによるプロパティ単位でのバリデーションを行うため、入力値の存在しないプロパティのバリデーションは行ってくれない
 *
 * @author kengo
 *
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class AuthSpringValidator implements ConstraintValidator<Auth, Object[]> {

	@Inject
	private UserService userService;

	@Override
	public boolean isValid(final Object[] values, final ConstraintValidatorContext context) {

		String emailAddress = (String) values[0];
		String password = (String) values[1];

		return userService.find(emailAddress, password);
	}
}