package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.AssetAccepted;
import com.example.types.Event;
import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.Asset;
import com.flashline.registry.openapi.entity.AuthToken;
import com.flashline.registry.openapi.entity.RegistryUser;
import com.flashline.registry.openapi.query.UserCriteria;
import com.flashline.registry.openapi.service.v300.FlashlineRegistry;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Properties;
import javax.xml.bind.JAXBElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AssetAcceptedHandler implements EventHandler {
        
      private Log logger = LogFactory.getLog(AssetAcceptedHandler.class.getName()); // not static since contained in servlet
      public static final String ASSET_ASSIGNEE = "event.AssetAccepted.assignee";
      public static final long SERVICE_TYPE  = 154;
      
        public AssetAcceptedHandler() {
            super();
        }

        /*
        //  If the asset accepted is a Service, set the Version Attribute  based on the suffix of the Service Namespace
        //
        */
        
        public void process(Event event, Properties props, ConnectionPool.OERConnection conn) {
            
            // Get The Extended Data from the Event
            
            JAXBElement<AssetAccepted> assetAccJ =  (JAXBElement<AssetAccepted>) event.getExtendedData();
            AssetAccepted assetAccepted = assetAccJ.getValue();
        
            try {
                  FlashlineRegistry repoInstance = conn.getConnection();
                  AuthToken authToken = conn.getAuthToken();
                          
                 // Retrieve the submitted Asset
                
                  Asset asset = repoInstance.assetRead(authToken, assetAccepted.getAsset().getId());
                
                  //
                  // Assign the Asset to the specified user
                  //
                                          
                  // Lookup the user
                  String assignee = props.getProperty(ASSET_ASSIGNEE);
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
                      asset.setAssignedDate(Calendar.getInstance());
                     
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
                
                //
                // If Asset is a Service, Set the Asset Version Attribute
                //
                
                if(assetAccepted.getAsset().getTypeID() == SERVICE_TYPE)
                {
                
                    // get the namespace
                    int offset1 = asset.getLongName().lastIndexOf('}');
                    if(offset1 == -1)
                    {
                        logger.error("Unable to determine namespace for Asset " + asset + " namespace. " +
                                     "Version Metadata will not be set");
                    }
                    else{
                        String namespace = asset.getLongName().substring(1, offset1);
                        logger.info("Asset Name contains Namespace " + namespace);
                        int offset2 = asset.getLongName().lastIndexOf('/');
                        if(offset2 == -1)
                        {
                            logger.error("Unable to determine Version id from Asset " + asset + " namespace. " +
                                         "Version Metadata will not be set");
                        }
                        else {
                            String versionid = namespace.substring(offset2+ 1);    // skip v prefix eg v1
                            logger.info("Asset Name contains versionid " + versionid);
                            // set the namespace and update the asset
                            asset.setVersion(versionid);
                            logger.info("Set Version on Accepted Service Asset " +  asset.getDisplayName());
                        }
                    } 
            
                    // Update the Asset
                    repoInstance.assetUpdate(authToken, asset);
                  
                    }               
            }
            catch(OpenAPIException lEx) {
                    logger.error("ServerCode = "+ lEx.getServerErrorCode());
                    logger.error("Message = "+ lEx.getMessage());
                    logger.error("StackTrace:", lEx);       
            }
                
            catch (RemoteException rEx) {
                logger.error("Caught Remote Exception", rEx);
            } 
                
            catch(Exception ex)    {               
                logger.error("Caught Exception: ", ex);  
            }
        }
 }


