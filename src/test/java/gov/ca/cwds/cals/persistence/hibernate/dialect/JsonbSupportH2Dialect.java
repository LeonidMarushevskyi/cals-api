package gov.ca.cwds.cals.persistence.hibernate.dialect;

import java.sql.Types;
import org.hibernate.dialect.H2Dialect;

/**
 * @author CWDS CALS API Team
 */

public class JsonbSupportH2Dialect extends H2Dialect {

  public JsonbSupportH2Dialect() {
    this.registerColumnType(Types.JAVA_OBJECT, "CLOB");
  }

}
