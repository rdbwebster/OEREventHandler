package com.example.eventhandlers;

import com.example.ConnectionPool;
import com.example.types.AssetAccepted;
import com.example.types.Event;

import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.Asset;
import com.flashline.registry.openapi.entity.AssignedUser;
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

/*
 * This Handler responds to the AssetAccepted OER event.
 * The Hanlder assigns the Asset to the OER user name
 * specified by the event.AssetSubmission.acceptor property.
 * If the Asset is a Service, set the Version metadata attribute
 * by incrementing the version id at the end of the namespace.
 *
 */


public class AssetAcceptedHandler implements EventHandler {

    private Log logger =
        LogFactory.getLog(AssetAcceptedHandler.class.getName()); // not static since contained in servlet
    public static final String ASSET_ASSIGNEE = "event.AssetAccepted.assignee";
    public static final long SERVICE_TYPE = 154;

    public AssetAcceptedHandler() {
        super();
    }

    /*
    //  If the asset accepted is a Service, set the Version Attribute  based on the suffix of the Service Namespace
    */

    public void process(Event event, Properties props,
                        ConnectionPool.OERConnection conn) {

        // Get The Extended Data from the Event

        JAXBElement<AssetAccepted> assetAccJ =
            (JAXBElement<AssetAccepted>)event.getExtendedData();
        AssetAccepted assetAccepted = assetAccJ.getValue();

        try {

            // Retrieve the submitted Asset

            Asset asset = conn.getConnection().assetRead(conn.getAuthToken(), assetAccepted.getAsset().getId());

            //
            // Assign the Asset to the specified user
            //

            // Lookup the user
            String assignee = props.getProperty(ASSET_ASSIGNEE);
            UserCriteria lCriteria = new UserCriteria();
            lCriteria.setNameCriteria(assignee);
            RegistryUser[] users =
                conn.getConnection().userQuery(conn.getAuthToken(), lCriteria);

            // No such user ?
            if (users.length == 1) {
                logger.info("Located User " + users[0].getUserName() + " id " +
                            users[0].getID());

                // if assigning to one user
                asset.setAssigned(true);
            //    asset.setAssignedToID(users[0].getID());
                
                asset.setAssignedDate(Calendar.getInstance());

                logger.info("Assigning Asset " + asset.getID() + " : " + asset.getLongName() +
                            " to User " + users[0].getID()  + " : " + assignee);
                AssignedUser[] assignedUsers = new AssignedUser[1];
                AssignedUser aUser = new AssignedUser();
                aUser.setID(users[0].getID());
                aUser.setAssignedDate(Calendar.getInstance());
                aUser.setAssetID(asset.getID());
                aUser.setUserID(users[0].getID());
                assignedUsers[0] = aUser;
                asset.setAssignedUsers(assignedUsers);

                

            } else
                logger.error("Unable to Assign Asset " + asset.getLongName() +
                             " to User " + assignee +
                             " User does not exist in Repository");

            //
            // If Asset is a Service, Set the Asset Version Attribute
            //

            if (assetAccepted.getAsset().getTypeID() == SERVICE_TYPE) {

                // get the namespace
                int offset1 = asset.getLongName().lastIndexOf('}');
                if (offset1 == -1) {
                    logger.error("Unable to determine namespace for Asset " +
                                 asset + " namespace. " +
                                 "Version Metadata will not be set");
                } else {
                    String namespace = asset.getLongName().substring(1, offset1);
                    logger.info("Asset Name contains Namespace " + namespace);
                    int offset2 = asset.getLongName().lastIndexOf('/');
                    if (offset2 == -1) {
                        logger.error("Unable to determine Version id from Asset " +
                                     asset + " namespace. " +
                                     "Version Metadata will not be set");
                    } else {
                        String versionid =
                            namespace.substring(offset2 + 1); // skip v prefix eg v1
                        logger.info("Asset Name contains versionid " +
                                    versionid);
                        
                        // set the namespace and update the asset
                        asset.setVersion(versionid);
                        logger.info("Set Version on Accepted Service Asset " +
                                    asset.getDisplayName());
                    }
                }

                // Update the Asset
                conn.getConnection().assetUpdate(conn.getAuthToken(), asset);

            }
        } catch (OpenAPIException lEx) {
            logger.error("ServerCode = " + lEx.getServerErrorCode());
            logger.error("Message = " + lEx.getMessage());
            logger.error("StackTrace:", lEx);
        }

        catch (RemoteException rEx) {
            logger.error("Caught Remote Exception", rEx);
        }

        catch (Exception ex) {
            logger.error("Caught Exception: ", ex);
        }
    }
}


