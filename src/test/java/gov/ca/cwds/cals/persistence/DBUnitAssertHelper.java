package gov.ca.cwds.cals.persistence;

import gov.ca.cwds.cals.web.rest.utils.VelocityHelper;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

/**
 * @author CWDS CALS API Team
 */

public class DBUnitAssertHelper {

  private String tableName;
  private DBUnitSupport dbUnitSupport;
  private List<DataFilter> filters = new LinkedList<>();
  private ReplacementDataSet expectedDataSet;
  private ReplacementDataSet actualDataSet;
  private String templatePath;
  private Map<String, Object> templateParams = new HashMap<>();

  private DBUnitAssertHelper(DBUnitSupport dbUnitSupport) {

    this.dbUnitSupport = dbUnitSupport;
    try {
      this.templateParams = prepareInitialParametersMap();
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }

  }

  private void setFixture(String templatePath) throws Exception {
    this.templatePath = templatePath;
  }

  private void processTemplate() {
    VelocityHelper velocityHelper = new VelocityHelper();
    velocityHelper.setParameters(templateParams);
    try {
      expectedDataSet = getReplacementDataSet(velocityHelper.process(templatePath));
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private Map<String, Object> prepareInitialParametersMap() throws URISyntaxException {
    Map<String, Object> parameters = new HashMap<>();
    Path path = Paths.get(getClass().getClassLoader().getResource("dbunit/CWSCMS.dtd").toURI());
    parameters.put("schemaPath", path.toString());
    return parameters;
  }

  private void setTableName(String tableName) {
    this.tableName = tableName;
  }

  private void addFilter(String filterColumnName, String filterColumnValue) {
    filters.add(new DataFilter(filterColumnName, filterColumnValue));
  }

  public void assertEquals() throws Exception {
    assertEquals(null, null);
  }

  public void assertEquals(String[] ignoreCols) throws Exception {
    assertEquals(ignoreCols, null);
  }

  public void assertEquals(String[] ignoreCols, String sortedCols[]) throws Exception {
    ITable expectedData = dbUnitSupport.getTableFromDataSet(expectedDataSet, tableName);
    ITable actualData = getActualTable();
    if (ArrayUtils.isNotEmpty(sortedCols)) {
      expectedData = new SortedTable(expectedData, sortedCols);
      actualData = new SortedTable(actualData, sortedCols);
    }

    if (ArrayUtils.isNotEmpty(ignoreCols)) {
      Assertion.assertEqualsIgnoreCols(expectedData, actualData, ignoreCols);
    } else {
      Assertion.assertEquals(expectedData, actualData);
    }
  }

  private ReplacementDataSet getReplacementDataSet(IDataSet dataSet) throws Exception {
    ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
    replacementDataSet.addReplacementObject("[NULL]", null);
    replacementDataSet.addReplacementObject("[CURRENT_DATE]", LocalDate.now().toString());
    return replacementDataSet;
  }

  private ReplacementDataSet getReplacementDataSet(String fixture) throws Exception {
    InputStream is = IOUtils.toInputStream(fixture, "UTF-8");
    return getReplacementDataSet(new FlatXmlDataSetBuilder().build(is));
  }

  public ReplacementDataSet getExpectedDataSet() {
    return expectedDataSet;
  }

  public ReplacementDataSet getActualDataSet() {
    try {
      return new ReplacementDataSet(dbUnitSupport.getSchemaDataSet());
    } catch (DatabaseUnitException e) {
      throw new IllegalStateException(e);
    }
  }

  public ITable getActualTable() {
    ITable actualTable = dbUnitSupport.getTableFromDB(tableName);
    if (!filters.isEmpty()) {
      try {
        actualTable = dbUnitSupport
            .doFilter(actualTable, filters.toArray(new DataFilter[filters.size()]));
      } catch (DataSetException e) {
        throw new IllegalStateException(e);
      }
    }
    return actualTable;
  }

  public static DBUnitAssertHelperBuilder builder(DBUnitSupport dbUnitSupport) {
    return new DBUnitAssertHelperBuilder(dbUnitSupport);
  }

  public static class DBUnitAssertHelperBuilder {

    private DBUnitAssertHelper helper;

    public DBUnitAssertHelperBuilder(DBUnitSupport dbUnitSupport) {
      this.helper = new DBUnitAssertHelper(dbUnitSupport);
    }

    public DBUnitAssertHelperBuilder appendTemplateParameter(String name, Object value) {
      helper.templateParams.put(name, value);
      return this;
    }

    public DBUnitAssertHelperBuilder setExpectedResultTemplatePath(String fixturePath) {
      try {
        helper.setFixture(fixturePath);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
      return this;
    }

    public DBUnitAssertHelperBuilder setTestedTableName(String tableName) {
      helper.setTableName(tableName);
      return this;
    }

    public DBUnitAssertHelperBuilder appendTableFilter(String filterColumnName,
        String filterColumnValue) {
      helper.addFilter(filterColumnName, filterColumnValue);
      return this;
    }

    public DBUnitAssertHelper build() {
      helper.processTemplate();
      return helper;
    }

  }
}
