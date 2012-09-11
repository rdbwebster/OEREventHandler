package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.AssetSubmission;
import com.example.types.Event;
import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.Asset;
import com.flashline.registry.openapi.entity.AuthToken;
import com.flashline.registry.openapi.entity.RegistryUser;
import com.flashline.registry.openapi.query.UserCriteria;
import com.flashline.registry.openapi.service.v300.FlashlineRegistry;
import com.flashline.registry.openapi.service.v300.FlashlineRegistryServiceLocator;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Properties;
import javax.xml.bind.JAXBElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AssetSubmissionHandler implements EventHandler {
    
    private Log logger = LogFactory.getLog(AssetSubmissionHandler.class.getName()); // not static since contained in servlet
    
    public AssetSubmissionHandler() {
        super();
        
    }

    //
    // Auto Accept new Assets Submitted to the Repository
    // Auto Assign them to the configured user
    //
    public void process(Event event, Properties props, ConnectionPool.OERConnection conn) {
        
        try {
                String repoURL  = props.getProperty("oer.uri");
                String username = props.getProperty("oer.username"); 
                String password = props.getProperty("oer.password");
            
                // Connect to repository
                // Servlet Based Web Service, method must be thread safe 
                URL alerRepURL  = new URL(repoURL);
                FlashlineRegistry repoInstance = new FlashlineRegistryServiceLocator().getFlashlineRegistry(alerRepURL);
                AuthToken authToken = repoInstance.authTokenCreate(username, password);
                                                    
                logger.info("Connected to Repository : " + repoURL);       
      
                // Get The Extended Data of known subtype
            
                JAXBElement<AssetSubmission> assetSubJ =  (JAXBElement<AssetSubmission>) event.getExtendedData();
                AssetSubmission assetSub = assetSubJ.getValue();
            
                // Retrieve the submitted Asset
                
                Asset asset = repoInstance.assetRead(authToken, assetSub.getAsset().getId());
                Calendar cal = Calendar.getInstance();
                
                // Accept the Asset
            
                String acceptor = props.getProperty("event.AssetSubmission.acceptor");  
                repoInstance.assetAccept(authToken, asset.getID());
                asset.setAcceptedByName(acceptor);
                asset.setAcceptedDate(cal);
                logger.info("Asset " + asset.getDisplayName()  + " Accepted by " + acceptor );
            
                // Assign the Asset to the specified user
                String assignee = props.getProperty("event.AssetSubmission.assignee");  
               
                // Lookup the user
                UserCriteria lCriteria = new UserCriteria();
                lCriteria.setNameCriteria(assignee);
                RegistryUser[] users = repoInstance.userQuery(authToken, lCriteria);
            
                // No such user ?
                if(users.length == 1)
                {    
                    logger.info("Located User " + users[0].getUserName() + " id " + users[0].getID()); 
                    
                    // if assigning to one user
                    asset.setAssigned(true);
                    asset.setAssignedToID(users[0].getID());
                    asset.setAssignedDate(cal);
                   
               //     AssignedUser[] assignedUsers = new AssignedUser[1];
               //     AssignedUser user = new AssignedUser();
               //     user.setID(users[0].getID());
               //     user.setAssignedDate(cal);
               //     assignedUsers[0] = user;
                    
               //     asset.setAssignedUsers(assignedUsers);
                   
                    logger.info("Assigned Asset " + asset.getLongName() +
                                 " to User " + assignee); 

                } 
                    else  logger.error("Unable to Assign Asset " + asset.getLongName() +
                                       " to User " + assignee  + " User does not exist in Repository"); 

            
                // Update the Asset
                repoInstance.assetUpdate(authToken, asset);
            
                
                // Email the assigned user
        
                repoInstance.authTokenDelete(authToken);
            
        
        }
        catch(OpenAPIException aEx) {
                logger.error("ServerCode = "+ aEx.getServerErrorCode());
                logger.error("Message = "+ aEx.getMessage());
                logger.error("StackTrace:", aEx);
              
                }
        
        catch (RemoteException lEx) {
            logger.error("OER Functions: Caught Remote Exception  ",  lEx);
          
        } 
        
        catch(Exception ex)
        {               
            logger.error("OER Functions caught Exception: " , ex);  
          
        }
    }
      
    
}
