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

public class ConnectionPool {
  private final OERConnection[] objects;
  private int available = 0;
  private int waiting = 0;
  private Log logger = LogFactory.getLog(ConnectionPool.class);   // not static since contained in servlet
    
  public ConnectionPool(int size) {
     objects = new OERConnection[size];
  }
      
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
      
   public synchronized void release(OERConnection obj) {
     objects[available] = obj;
     available++;
     if (waiting>0) {
        notify();
     }
     
  }
   
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