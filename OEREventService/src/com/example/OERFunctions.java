package com.example;

import com.flashline.registry.openapi.base.OpenAPIException;
import com.flashline.registry.openapi.entity.Asset;
import com.flashline.registry.openapi.entity.AssetSummary;
import com.flashline.registry.openapi.entity.AuthToken;
import com.flashline.registry.openapi.entity.ExtractionDownload;
import com.flashline.registry.openapi.entity.IExtraction;
import com.flashline.registry.openapi.entity.NameValue;
import com.flashline.registry.openapi.entity.SettingValue;
import com.flashline.registry.openapi.query.AssetCriteria;
import com.flashline.registry.openapi.query.SearchTerm;
import com.flashline.registry.openapi.query.SystemSettingsCriteria;
import com.flashline.registry.openapi.service.v300.FlashlineRegistry;
import com.flashline.registry.openapi.service.v300.FlashlineRegistryServiceLocator;

import java.net.MalformedURLException;
import java.net.URL;

import java.rmi.RemoteException;

import javax.jws.WebMethod;

import javax.xml.rpc.ServiceException;


public class OERFunctions {
    public OERFunctions() {
        super();
    }


    public  String readAsset(String repoURL, String username, String password, long assetID) 
   {
           
           try {
                   // Connect to repository
                   // Servlet Based Web Service, method must be thread safe 
                   URL alerRepURL  = new URL(repoURL);
                   FlashlineRegistry repoInstance = new FlashlineRegistryServiceLocator().getFlashlineRegistry(alerRepURL);
                   AuthToken authToken = repoInstance.authTokenCreate(username, password);
                                                       
                   System.out.println("Connected to Repository : " + repoURL);       
               
                   Asset asset = repoInstance.assetRead(authToken, assetID);
                   
                   System.out.println("Connected to OER and read Asset " +  asset.getDisplayName());
               
                   repoInstance.authTokenDelete(authToken);
               
   
           }
           catch(OpenAPIException lEx) {
                   System.out.println("ServerCode = "+ lEx.getServerErrorCode());
                   System.out.println("Message = "+ lEx.getMessage());
                   System.out.println("StackTrace:");
                   lEx.printStackTrace(System.out);
                   }
           catch (RemoteException lEx) {
               System.out.println("OER Functions: Caught Remote Exception  " + lEx.getMessage());
               lEx.printStackTrace(System.out);
           } 
           
           catch(Exception ex)
           {               
               System.out.println("OER Functions caught Exception: " + ex.getMessage());  
               ex.printStackTrace(System.out);
           }

           return "";
        }



       private void search(String repoURL, String username, String password, String searchString) {
           
         
           try {
               // Connect to repository
               // Servlet Based Web Service, method must be thread safe 
               URL alerRepURL  = new URL(repoURL);
               FlashlineRegistry repoInstance = new FlashlineRegistryServiceLocator().getFlashlineRegistry(alerRepURL);
               AuthToken authToken = repoInstance.authTokenCreate(username, password);                                    
               System.out.println("Connected to Repository : " + repoURL);          
                    
               AssetCriteria criteria = new AssetCriteria();
               SearchTerm lSearchTerm = new SearchTerm();
               lSearchTerm.setKey("name");
               lSearchTerm.setOperator("LIKE");
               lSearchTerm.setValue(searchString);
               SearchTerm[] lTerms = new SearchTerm[1];
               lTerms[0] = lSearchTerm;
               criteria.setSearchTerms(lTerms);
               AssetSummary[]  assets = repoInstance.assetQuerySummary(authToken, criteria);
               
               for(int i=0; i< assets.length; i++)
               {
                    System.out.println("Connected to OER and searched for Asset " +  assets[i].getName());
                    if(assets[i].getName().equals("{http://departmentdetailsservice/v2}Service/DepartmentFinderService")) {
                       
                       Asset asset = repoInstance.assetRead(authToken, assets[i].getID());
                       asset.setVersion("3.0");
                       repoInstance.assetUpdate(authToken, asset);
                   }
               }
               
               repoInstance.authTokenDelete(authToken);
               
           
           }
           catch(OpenAPIException lEx) {
                   System.out.println("ServerCode = "+ lEx.getServerErrorCode());
                   System.out.println("Message = "+ lEx.getMessage());
                   System.out.println("StackTrace:");
                   lEx.printStackTrace(System.out);
                   } 
           
           catch (RemoteException lEx) {
                       System.out.println("OER Functions: Caught Remote Exception  " + lEx.getMessage());
                       lEx.printStackTrace(System.out);
                   } 
                  
               
           catch(Exception ex)
           {               
               System.out.println("OER Functions caught Exception: " + ex.getMessage());  
               ex.printStackTrace(System.out);
           }

           return;
       }
       
      
       
    // This Uses an Asset, apparently the only way to create a survey.
    // Survey can be seen My Stuff, Consumed Assets
    // But no survey email sent, set reminder to 1 day
    public  String useForSurvey(String repoURL, String username, String password, String[] recipients) 
    {
            
            StringBuffer value = new StringBuffer(10);
            String templateId = "usage_query_1";
            
            try {
                    // Connect to repository
                    // Servlet Based Web Service, method must be thread safe 
                    URL alerRepURL  = new URL(repoURL);
                    FlashlineRegistry repoInstance = new FlashlineRegistryServiceLocator().getFlashlineRegistry(alerRepURL);
                    AuthToken authToken = repoInstance.authTokenCreate(username, password);                         
                    System.out.println("Connected to Repository : " + repoURL);          
                
                
                    long ASSET_ID_1 = 50006;     // must be a valid, registered asset id in OER, see Asset Editor for ID
                    long PROJECT_ID = 50000;     // must be a valid project id in OER, 50000 is "common project"
                    long EXTRACTION_ID = 0;
                    // ----------------------------------------
                    // Create a new extraction
                    long[] lAssetIDs = { ASSET_ID_1 };
                    ExtractionDownload[] extractionDownloads =
                    repoInstance.extractionCreate(authToken, PROJECT_ID, lAssetIDs);
                    System.out.println("Number of new extraction downloads created: " +
                    extractionDownloads.length);
                
                    repoInstance.authTokenDelete(authToken);
                
                  
            }
            catch(OpenAPIException lEx) {
                    System.out.println("ServerCode = "+ lEx.getServerErrorCode());
                    System.out.println("Message = "+ lEx.getMessage());
                    System.out.println("StackTrace:");
                    lEx.printStackTrace(System.out);
                    }
                
            catch (RemoteException lEx) {
                    System.out.println("OER Functions: Caught Remote Exception  " + lEx.getMessage());
                    lEx.printStackTrace(System.out);
            } 
        
                
            catch(Exception ex) {               
                System.out.println("OER Functions caught Exception: " + ex.getMessage());  
                ex.printStackTrace(System.out);
            }
            
            
     
            return "";
    }
    
    public  String notify(String repoURL, String username, String password, String[] recipients, String assetName) 
       {
               
             
               String templateId = "asset_registered";
            //  String templateId2 = "extraction_email_initial";

               
               try {
                      // Connect to repository
                      // Servlet Based Web Service, method must be thread safe 
                       URL alerRepURL  = new URL(repoURL);
                       FlashlineRegistry repoInstance = new FlashlineRegistryServiceLocator().getFlashlineRegistry(alerRepURL);
                       AuthToken authToken = repoInstance.authTokenCreate(username, password);                         
                       System.out.println("Connected to Repository : " + repoURL);          
                           
                   
                       // read the existing substitutions based on the given template
                       String[] lSubstitutions = repoInstance.notificationSubstitutionsRead(authToken, templateId);
                       
                       
                       // create an array of namevalue pairs; a namevalue pair for each required substitution
                       NameValue[] lNameValues = new NameValue[lSubstitutions.length];
                        
                    
                       // populate the namevalues
                       for(int i=0; i<lSubstitutions.length; i++) {
                        lNameValues[i] = new NameValue();
                        lNameValues[i].setName(lSubstitutions[i]);
                        if(lSubstitutions[i].equals("registrar.email"))
                            lNameValues[i].setValue("admin@soabpm-server");
                        else 
                            if(lSubstitutions[i].equals("asset.name"))
                                lNameValues[i].setValue(assetName);
                            else
                                lNameValues[i].setValue("valueof-"+"notset");
                        System.out.println("valueof-"+lSubstitutions[i]);
                       }
                       
                       // Create the OER Notification
                       // This creates an entry in OER SMTPQUEUE Table
                       // OER Web App will send an email through configured SMTP Server,
                       // This example shows how to trigger a notification in addition 
                       // to the normal notifications the OER Web app creates for example as tabs are approved.
                       // A running BPM Server is not required for emails to be generated and sent.
                       // Email sends are visible in the cmee.log
                       repoInstance.notificationCreate(authToken, templateId, recipients, lNameValues);
                   
                       repoInstance.authTokenDelete(authToken);
                  
               }
               catch(OpenAPIException lEx) {
                       System.out.println("ServerCode = "+ lEx.getServerErrorCode());
                       System.out.println("Message = "+ lEx.getMessage());
                       System.out.println("StackTrace:");
                       lEx.printStackTrace(System.out);
                       }
                   
               catch (RemoteException lEx) {
                       System.out.println("OER Functions: Caught Remote Exception " + lEx.getMessage());
                       lEx.printStackTrace(System.out);
               } 
           
                   
               catch(Exception ex) {               
                       System.out.println("OER Functions caught Exception: " + ex.getMessage());  
                       ex.printStackTrace(System.out);
               }    
        
               return "";
       }

    
    // Can be run from command line as simple unit test
    public static void main(String Args[]) {
    
        OERFunctions obj = new OERFunctions();
        
        // Read Asset Example
        //obj.readAsset("http://localhost:7102/oer/services/FlashlineRegistry", "admin", "welcome1", 567);
        
        // Search Example
        //obj.search("http://localhost:7102/oer/services/FlashlineRegistry", "admin", "welcome1", "department");
        
        // Notify Example
        String[] users = new String[1];
        users[0] = "architect@soabpm-server";
             
    //    obj.notify("http://localhost:7102/oer/services/FlashlineRegistry", "admin", "welcome1", users, "Asset123");
        obj.useForSurvey("http://localhost:7102/oer/services/FlashlineRegistry", "admin", "welcome1", users);
       
    }
}
