package com.example.db;

import com.example.resource.ResourceProvisioner;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class DBOperations {
    private final Logger log = Logger.getLogger(DBOperations.class.getName());
    Connection conn = null;

    public DBOperations(){
        try {
            conn = new DatabaseConnFactory().createDbConn("sqlite");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeDbConnection(){
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void registerResourceInDb(String resourceId,String resourceName, String resourceType, String resourceTag) {
        String sql = "INSERT INTO resource VALUES (?,?,?,?,?,?,?,?);";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,resourceId);
            stmt.setString(2,resourceName);
            stmt.setString(3,resourceType);
            stmt.setString(4,resourceTag);
            stmt.setString(5,java.sql.Timestamp.from(java.time.Instant.now()).toString());
            stmt.setString(6, "user1");
            stmt.setString(7,"user2");
            stmt.setString(8,"aws");

            stmt.executeUpdate();
            log.info("Resource Named: " + resourceName + " and " + resourceType + " Type is entered into the database");
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void registerStateInDb(String resourceName,String resourceType, String state) {
        String sql = "INSERT INTO resource_state VALUES (?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,resourceName);
            stmt.setString(2,resourceType);
            stmt.setString(3,state);
            stmt.setString(4,java.sql.Timestamp.from(java.time.Instant.now()).toString());
            stmt.setInt(5,123);

            stmt.executeUpdate();
            log.info("Resource: " + resourceName +" state is saved into database");
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Map<String,List<String>> getResourcesFromDb(){
        String sql = "SELECT distinct resource_name,resource_type FROM resource_state where state != 'deprovisioned';";
        Statement stmt;
        Map<String,String> resourceMap = null;
        Map<String, List<String>> finalMap = new HashMap<>();
        try {
            resourceMap = new HashMap<>();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            log.info("Retrieving resources list from database");

                while(rs.next()){
                    resourceMap.put(rs.getString("resource_name"),rs.getString("resource_type"));
                }

            //create list of resourtyp -> list(resource_name)
            resourceMap.forEach((key, value) -> {

                Object o = finalMap.containsKey(value) ?
                        finalMap.get(value).add(key) :
                        finalMap.put(value, new ArrayList<>(Arrays.asList(key)));
            });

            stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return finalMap;
    }

    public void updateStateInDB(List<String> resourcesName, String state) throws SQLException {
        String sql = "UPDATE resource_state set state = ? where resource_name = ?";
        PreparedStatement stmt;
        stmt = conn.prepareStatement(sql);

        for(String resourceName: resourcesName){
                stmt.setString(1,state);
                stmt.setString(2,resourceName);
                stmt.executeUpdate();
                log.info("Resource: "+resourceName+" state is updated into database");

        }
        stmt.close();
    }
}
