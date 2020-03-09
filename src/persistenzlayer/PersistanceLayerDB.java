package persistenzlayer;

import infrastructure.lhc.Block;
import infrastructure.lhc.Experiment;
import infrastructure.lhc.IExperiment;

import java.sql.*;
import java.util.UUID;

public enum PersistanceLayerDB implements  IPersistanceLayer {
    instance;

    private final String userDirectory = System.getProperty("user.dir");
    private final String fileSeparator = System.getProperty("file.separator");
    private final String dataDirectory = userDirectory + fileSeparator + "dataDatabase" + fileSeparator;
    private final String databaseFile = dataDirectory + "datastore.db";
    private Connection connection;
    private String driverName = "jdbc:hsqldb:";
    private String username = "sa";
    private String password = "";

    PersistanceLayerDB() {
        // if DB doesnot exit, create DB



    }



    //    public static void main(String... args) {
//        PersistanceLayer application = new PersistanceLayer();
//
//        application.setupConnection();
//        application.dropTableCustomers();
//        application.createTableCustomers();
//
//        application.insert(new Customer(1, "A"));
//        application.insert(new Customer(2, "C"));
//        application.insert(new Customer(3, "B"));
//        application.insert(new Customer(4, "D"));
//        application.insert(new Customer(5, "E"));
//
//        application.select();
//
//        application.shutdown();
//    }

    public void setupConnection() {
        System.out.println("--- setupConnection");

        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + databaseFile;
            connection = DriverManager.getConnection(databaseURL, username, password);
            System.out.println("connection : " + connection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);

            if (result == -1) {
                System.out.println("error executing " + sqlStatement);
            }

            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
    // löschen der Tabelle
    public void dropTableCustomers() {
        System.out.println("--- dropTableCustomer");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("DROP TABLE customer");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());

        update(sqlStringBuilder.toString());
    }

    public void dropTableExperimentBlock() {
        System.out.println("--- dropTableExperiment");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("DROP TABLE experiment");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());

        update(sqlStringBuilder.toString());

        StringBuilder sqlStringBuilder2 = new StringBuilder();
        sqlStringBuilder2.append("DROP TABLE block");
        System.out.println("sqlStringBuilder2 : " + sqlStringBuilder2.toString());

        update(sqlStringBuilder2.toString());
    }



    public void createTableCustomers() {
        System.out.println("--- createTableCustomers");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE customer ( ");
        sqlStringBuilder.append("id INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("name VARCHAR(256) NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(" )");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());

        update(sqlStringBuilder.toString());
    }


    public  void createTableExperiment(){
        System.out.println("--- createTableExperiment");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE experiment ( ");
        sqlStringBuilder.append("uuid VARCHAR(256) NOT NULL").append(",");
        sqlStringBuilder.append("datetTimeStamp VARCHAR(256) NOT NULL").append(",");
        sqlStringBuilder.append("isHiggsBosonFound BOOLEAN").append(",");
        sqlStringBuilder.append("idProton1 INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("idProton2 INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (uuid)");
        sqlStringBuilder.append(" )");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());

        update(sqlStringBuilder.toString());
    }

    public  void createTableBlock(){
        System.out.println("--- createTableBlock");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE block ( ");
        sqlStringBuilder.append("uuid VARCHAR(256) NOT NULL").append(",");
        sqlStringBuilder.append("structure VARCHAR(256) NOT NULL").append(",");
        sqlStringBuilder.append("uuidExperiment VARCHAR(256) NOT NULL").append(",");

        sqlStringBuilder.append("PRIMARY KEY (uuid)");
        sqlStringBuilder.append(" )");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());

        update(sqlStringBuilder.toString());

    }



    public void insert(Customer customer) {
        StringBuilder sqlStringStringBuilder = new StringBuilder();
        sqlStringStringBuilder.append("INSERT INTO customer values (" + customer.getID() + ",'" + customer.getName() + "')");
        update(sqlStringStringBuilder.toString());
        
    }

    public void insert(IExperiment experiment) {
        UUID test = experiment.getUuid();
        StringBuilder sqlStringStringBuilder = new StringBuilder();
        sqlStringStringBuilder.append("INSERT INTO experiment values ('" + experiment.getUuid() + "','" + experiment.getDateTimeStamp() + "'," + experiment.isHiggsBosonFound()  + "," + experiment.getIdProton1()  + "," + experiment.getIdProton2()  +")");
        update(sqlStringStringBuilder.toString());
        for (int i = 0; i <experiment.getBlockArrayList().size() ; i++) {
            insert(experiment.getBlockArrayList().get(i),experiment.getUuid());
        }
    }

    public void insert(Block block, UUID uuidExpermiment) {
        StringBuilder sqlStringStringBuilder = new StringBuilder();
        sqlStringStringBuilder.append("INSERT INTO block values ('" + block.getUuid() + "','" + block.getStructure() + "','" + uuidExpermiment + "')");
        update(sqlStringStringBuilder.toString());
    }




    public void executeSQLStatement(String sqlStatement, int numberOfColumns) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                if (numberOfColumns == 1) {
                    System.out.println(resultSet.getString(1));
                } else if (numberOfColumns == 2) {
                    System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));
                }
            }

            resultSet.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void select() {
        executeSQLStatement("SELECT * FROM customer order by name", 2);
        System.out.println();
    }

    public void shutdown() {
        System.out.println("--- shutdown");

        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
            System.out.println("isClosed : " + connection.isClosed());
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
}