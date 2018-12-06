/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import dbAccess.Connector;
import exceptions.FogException;
import functionLayer.LogicFacade;
import functionLayer.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jesper
 */
public class UserMapperTest {
    
    private static Connection testConnection;
    private static final String USER = "joerg";
    private static final String USERPW = "joerg/3085";
    private static final String DBNAME = "carportTest";
    private static final String HOST = "142.93.173.199";

    @Before
    public void setUp() {
        try {
            // avoid making a new connection for each test
            if (testConnection == null || testConnection.isClosed()) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.jdbc.Driver");
                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test 
                Connector.setConnection(testConnection);
            }
            //Reset database
            try (Statement stmt = testConnection.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS user;");
                stmt.execute("CREATE TABLE user LIKE userTest;");
                stmt.execute("INSERT into user SELECT * from userTest;");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(testConnection);
    }
    
    @Test
    public void testGetUser() throws FogException {
        //Arrange
        
        //Act
        String expected = "oerteljoerg@gmail.com";
        User actual = LogicFacade.login("oerteljoerg@gmail.com", "1234");
        //Assert
        assertEquals(expected, actual.getEmail());
    }
    
    @Test
    public void testCreateUser() {
        //Arrange
        
        //Act
        
        //Assert
    }
}
