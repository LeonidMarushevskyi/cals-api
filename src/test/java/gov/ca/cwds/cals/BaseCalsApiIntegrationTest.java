package gov.ca.cwds.cals;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.ClassRule;

/**
 * @author CWDS CALS API Team
 */

public class BaseCalsApiIntegrationTest {

    protected static DatabaseHelper fasDatabaseHelper;

    @ClassRule
    public static final DropwizardAppRule<CalsApiConfiguration> appRule =
            new DropwizardAppRule<CalsApiConfiguration>(CalsApiApplication.class, ResourceHelpers.resourceFilePath("config/test-cals-api.yml"));

    @BeforeClass
    public static void setUp() throws Exception {
        DataSourceFactory fasDataSourceFactory = appRule.getConfiguration().getFasDataSourceFactory();
        fasDatabaseHelper = new DatabaseHelper(
                fasDataSourceFactory.getUrl(),
                fasDataSourceFactory.getUser(),
                fasDataSourceFactory.getPassword());

        fasDatabaseHelper.runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_schema.xml");
        fasDatabaseHelper.runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_structure.xml", "fas");
        fasDatabaseHelper.runScript("gov/ca/cwds/cals/model/fas/liquibase/fas_constraints.xml", "fas");
    }

    @After
    public void tearDown() throws Exception {

    }
}