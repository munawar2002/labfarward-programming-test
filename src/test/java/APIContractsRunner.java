import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;
import com.labfarward.programmingtest.ProgrammingtestApplication;
import cucumber.api.CucumberOptions;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;

@Slf4j
@CucumberOptions (
        tags = {"~@ignore", "@CDC"}
)
@ActiveProfiles(profiles = "test")
public class APIContractsRunner {

    @Rule
    public static MySQLContainer mysql = new MySQLContainer().withDatabaseName("test-labfarward");
    public static ConfigurableApplicationContext context;

    @BeforeAll
    static void before() {
        mysql.start();
        log.info("Mysql is started...");

        System.setProperty("DB_URL", mysql.getJdbcUrl());
        System.setProperty("DB_USERNAME", mysql.getUsername());
        System.setProperty("DB_PASSWORD", mysql.getPassword());

        SpringApplication springApplication = new SpringApplication(ProgrammingtestApplication.class);
        context = springApplication.run();
        log.info("Application has been started!");
    }

    @AfterAll
    static void after() {
        log.info("Stopping mysql...");
        mysql.stop();

        log.info("Stopping the Application now!");
        context.close();

    }

    @Test
    public void testParallel() {
        KarateStats stats = CucumberRunner.parallel(getClass(), 5);
        Assertions.assertEquals(0, stats.getFailCount(), "scenarios failed!");
        Assertions.assertTrue(stats.getTestCount() > 0, "scenarios not found!");
    }
}