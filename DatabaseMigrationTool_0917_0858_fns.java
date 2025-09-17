// 代码生成时间: 2025-09-17 08:58:03
import org.apache.ibatis.session.SqlSession;
import java.io.Reader;
import java.util.Properties;

/**
 * DatabaseMigrationTool is a utility class that handles database migrations using
 * MyBatis framework. It provides methods to run migration scripts.
 */
public class DatabaseMigrationTool {

    private final String resourcePath;
    private Properties properties;

    /**
     * Constructor for DatabaseMigrationTool.
     * @param resourcePath The path to the MyBatis configuration file.
     */
    public DatabaseMigrationTool(String resourcePath) {
        this.resourcePath = resourcePath;
        properties = new Properties();
        loadProperties();
    }

    /**
     * Loads properties from a properties file.
     */
    private void loadProperties() {
        try {
            // Load properties from a file (e.g., mybatis-config.properties)
            properties.load(DatabaseMigrationTool.class.getResourceAsStream("config/mybatis-config.properties"));
        } catch (Exception e) {
            // Handle the exception, possibly logging it
            System.err.println("Error loading properties: " + e.getMessage());
        }
    }

    /**
     * Runs a migration script from a MyBatis mapper XML file.
     * @param mapperPath The path to the mapper XML file.
     * @param scriptName The name of the migration script.
     */
    public void runMigration(String mapperPath, String scriptName) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory(properties).openSession()) {
            Reader reader = session.getConfiguration().getMapperReader(mapperPath);
            // Assuming the mapper interface is named after the XML file without the extension
            Class<?> mapperClass = Class.forName("com.yourpackage.Mapper." + scriptName);
            Object mapper = session.getMapper(mapperClass);
            session.startBatch();
            // Run the migration script
            session.runMapperRunnable(mapper, mapper.getMethod(scriptName).invoke(mapper));
            session.commit();
        } catch (Exception e) {
            // Handle exceptions, such as ClassNotFoundException or NoSuchMethodException
            System.err.println("Error running migration: " + e.getMessage());
        }
    }

    /**
     * Main method to run the database migration tool.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java DatabaseMigrationTool <path_to_mapper_XML> <script_name>");
            System.exit(1);
        }

        DatabaseMigrationTool migrationTool = new DatabaseMigrationTool("config/mybatis-config.xml");
        migrationTool.runMigration(args[0], args[1]);
    }
}

/**
 * MyBatisUtil is a utility class for MyBatis operations.
 */
class MyBatisUtil {

    /**
     * Returns a SqlSessionFactory instance based on the provided properties.
     * @param properties The properties to configure the SqlSessionFactory.
     * @return The SqlSessionFactory instance.
     */
    public static SqlSessionFactory getSqlSessionFactory(Properties properties) {
        // Create an instance of SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        try {
            // Build a SqlSessionFactory based on the properties
            return builder.build(MyBatisUtil.class.getResourceAsStream(resourcePath), properties);
        } catch (Exception e) {
            // Handle exceptions, possibly logging them
            System.err.println("Error building SqlSessionFactory: " + e.getMessage());
            return null;
        }
    }
}