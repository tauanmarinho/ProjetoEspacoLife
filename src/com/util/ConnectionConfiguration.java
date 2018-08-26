package com.util;

import com.xmlFile.DatabaseInformation;
import com.xmlFile.WriteXMLFile;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfiguration {

    public static Connection getConnection () {
        Connection connection = null;
        WriteXMLFile write = new WriteXMLFile();
        DatabaseInformation databaseInformation = new DatabaseInformation();
        write.parseXml(databaseInformation);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + databaseInformation.getUrl() + ":" +
                    databaseInformation.getPort() + "/" + databaseInformation.getDatabase(),
                    databaseInformation.getUser(), databaseInformation.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnectionSchedule () {
        Connection connection = null;
        WriteXMLFile write = new WriteXMLFile();
        DatabaseInformation databaseInformation = new DatabaseInformation();
        write.parseXml(databaseInformation);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + databaseInformation.getUrl() + ":" +
                            databaseInformation.getPort() + "/" + databaseInformation.getDatabase() + "Schedule",
                    databaseInformation.getUser(), databaseInformation.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
