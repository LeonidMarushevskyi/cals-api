package gov.ca.cwds.cals.service.validation.field;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author CWDS CALS API Team
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = StateReferentialIntegrityValidator.class)
@Documented
public @interface CheckStateReferentialIntegrity {

  boolean checkEquality() default true;

  boolean enrich() default false;

  String message() default "Object is not found in database";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
  @Retention(RUNTIME)
  @Documented
  @interface List {

    CheckStateReferentialIntegrity[] value();
  }
}
