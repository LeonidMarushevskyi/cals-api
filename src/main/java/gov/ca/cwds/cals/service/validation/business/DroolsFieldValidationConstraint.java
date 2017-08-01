package gov.ca.cwds.cals.service.validation.business;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import gov.ca.cwds.cals.service.validation.business.configuration.DroolsValidationConfigurationRegistry;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author CWDS CALS API Team
 */

@Target({FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DroolsFieldValidator.class)
@Documented
public @interface DroolsFieldValidationConstraint {

  DroolsValidationConfigurationRegistry validationConfiguration();

  String session() default "inProgressValidationSession";

  String message() default "";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
  @Retention(RUNTIME)
  @Documented
  @interface List {

    DroolsFieldValidationConstraint[] value();
  }

}