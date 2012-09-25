package com.example;

import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.AuthToken;
import com.flashline.registry.openapi.service.v300.FlashlineRegistry;
import com.flashline.registry.openapi.service.v300.FlashlineRegistryServiceLocator;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.rpc.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * This class implements a pool of  Oracle Enterprise Repository connection objects.
 * @author  Bob Webster
 */
public class ConnectionPool {
  private final OERConnection[] objects;
  private int available = 0;
  private int waiting = 0;
  private Log logger = LogFactory.getLog(ConnectionPool.class);   // not static since contained in servlet
  
  /*
  * Constructs a pool of connection objects 
  * Construction of the pool should be followed by a call to the initialize method
  * @param size (required)  - number of connections in pool
  */
  public ConnectionPool(int size) {
     objects = new OERConnection[size];
  }
      
 /*
  * Initializes a pool by creating connection pool objects 
  * @param repoUrl (required)   - The url of the OER repository
  * @param username (required)  - The user name of an OER user with Admin permissions
  * @param password (required)  - The password for the OER user
  */
  public void initialize(String repoUrl, String username, String password) throws Exception {
      
      try {
           URL alerRepURL  = new URL(repoUrl);
           for(int i=0; i< objects.length; i++)
           {
             objects[i] = new OERConnection();
             objects[i].setConnection(new FlashlineRegistryServiceLocator().getFlashlineRegistry(alerRepURL));
             objects[i].setAuthToken(objects[i].getConnection().authTokenCreate(username, password));
             available++;
               
             logger.info("Added connection to OER connection pool: " + objects[i].getConnection().hashCode() + " : " +
                         objects[i].getAuthToken().getToken());
           }   
      }
         catch(MalformedURLException me) {
             logger.error("Invalid repository url: " + repoUrl, me);
             throw me;
         }
                                                                                           
         catch(ServiceException se) {
             logger.error("Service Exception calling OER", se);
             throw se;
         }                                                                          
                                                                                            
         catch(OpenAPIException lEx) {
                 logger.error("ServerCode = "+ lEx.getServerErrorCode());
                 logger.error("Message = "+ lEx.getMessage());
                 logger.error("StackTrace:", lEx);
                 throw lEx;
                 }
     }
   
 /*
  * Retrieves an OERConnection object representing an active OER connection 
  * @return  an OERConnection object
  */ 
  public synchronized OERConnection getInstance() throws InterruptedException {
     while (available == 0) {
        waiting++;
        wait();
        waiting--;
     }
     available--;
     OERConnection conn = objects[available];
    // test?
     return conn;
  }

   /*
    * Returns an OERConnection object to the pool 
    * @param conn (required)   - An OERConnection previously retrieved from the pool
   */ 
   public synchronized void release(OERConnection conn) {
     objects[available] = conn;
     available++;
     if (waiting>0) {
        notify();
     }
     
  }

  /*
  * A object that represents an active OER repository connection. 
  */
  public static class OERConnection {
        
        private AuthToken authToken;
        private FlashlineRegistry connection;
        
        public OERConnection(FlashlineRegistry conn, AuthToken token) {
            connection = conn;
            authToken = token;
        }
        
        public OERConnection() {
        
        }


        public void setAuthToken(AuthToken authToken) {
            this.authToken = authToken;
        }

        public AuthToken getAuthToken() {
            return authToken;
        }

        public void setConnection(FlashlineRegistry connection) {
            this.connection = connection;
        }

        public FlashlineRegistry getConnection() {
            return connection;
        }
    }
}