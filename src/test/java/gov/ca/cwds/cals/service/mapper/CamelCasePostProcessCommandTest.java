package gov.ca.cwds.cals.service.mapper;

import org.junit.Assert;
import org.junit.Test;

/** @author CWDS CALS API Team */
public class CamelCasePostProcessCommandTest {


  @Test
  public void execute() throws Exception {
    
  }

  @Test
  public void reformatToCammelCase() throws Exception {

    String target = "qwerty asdfg ZXCVBN  ZXC_VBN ";
    String expected = "Qwerty Asdfg Zxcvbn Zxc Vbn";

    String result = CamelCasePostProcessCommand.reformatToCammelCase(target);

    System.out.println("Source: " + target);
    System.out.println("Result: " + result);

    Assert.assertEquals(expected, result);
  }
}
