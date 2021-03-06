package gov.ca.cwds.cals.service.dto.rfa;

import gov.ca.cwds.cals.service.BeanValidationTestSupport;
import org.junit.Test;

import javax.validation.ConstraintViolation;

import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantDTOTest extends BeanValidationTestSupport<ApplicantDTO> {

    @Test
    public void applicantHasLongMiddleName() {
        String middleName = "aefesfersdfvv21symbol";
        ApplicantDTO applicant = new ApplicantDTO();
        applicant.setMiddleName(middleName);
        Set<ConstraintViolation<ApplicantDTO>> violations = validate(applicant);
        assertEquals(violations.size(), 1);
        String actualMessage = violations.iterator().next().getMessage();
        String expectedMessage = getMaxLengthMessage(middleName, 20);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void applicantHasNonAlphaNumericMiddleName() {
        String middleName = ".";
        ApplicantDTO applicant = new ApplicantDTO();
        applicant.setMiddleName(middleName);
        Set<ConstraintViolation<ApplicantDTO>> violations = validate(applicant);
        assertEquals(violations.size(), 1);
        String actualMessage = violations.iterator().next().getMessage();
        String expectedMessage = getAlphaNumericMessage(middleName);
        assertEquals(expectedMessage, actualMessage);
    }
}
