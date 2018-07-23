package com.xmlFile;

public class DatabaseInformation {

    String user;
    String password;
    String url;
    String port;
    String database;

    public DatabaseInformation(String user, String password, String url, String port, String database) {
        this.user = user;
        this.password = password;
        this.url = url;
        this.port = port;
        this.database = database;
    }

    public DatabaseInformation() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
