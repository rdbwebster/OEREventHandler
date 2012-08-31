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
    private static AuthToken authenticationToken;
    private static FlashlineRegistry alerInstance = null;



    public  String test(String repoURL, String username, String password, long assetID) 
               {
                       
                       StringBuffer value = new StringBuffer(10);
                       
                       try {
                       
                               if(repoURL.length() == 0)
                                       System.out.println("Error: a repository url is required.  For example: http://localhost:7101/aler/services/FlashlineRegistry");
                               
                               
                               if(username.length() == 0)
                                       System.out.println("Error: a repository userid id is required.    For example: admin");
                                       
                               if(password.length() == 0)
                               
                                       System.out.println("Error: a repository password id is required.  For example: admin");
                                             
                       
                               connect(repoURL, username, password);
                           
                               Asset asset = alerInstance.assetRead(authenticationToken, assetID);
                               
                               System.out.println("Connected to OER and read Asset " +  asset.getDisplayName());
                           
               
                       }
                       catch(OpenAPIException lEx) {
                               System.out.println("ServerCode = "+ lEx.getServerErrorCode());
                               System.out.println("Message = "+ lEx.getMessage());
                               System.out.println("StackTrace:");
                               lEx.printStackTrace();
                               }
                       catch (RemoteException lEx) {
                               lEx.printStackTrace();
                       } 
                   
                           
                       catch(Exception ex)
                       {               
                               System.out.println("Exception " + ex.getMessage());
                           
                       }

                       return "";
               }

       private void search(String repoURL, String userName, String password) {
           
         
           try {
           
                   if(repoURL.length() == 0)
                           System.out.println("Error: a repository url is required.  For example: http://localhost:7101/aler/services/FlashlineRegistry");
                   
                   
                   if(userName.length() == 0)
                           System.out.println("Error: a repository userid id is required.    For example: admin");
                           
                   if(password.length() == 0)
                   
                           System.out.println("Error: a repository password id is required.  For example: admin");
                                 
           
                    connect(repoURL, userName, password);
               
                    AssetCriteria criteria = new AssetCriteria();
                    AssetSummary[] assets = alerInstance.assetQuerySummary(authenticationToken, criteria);
               
                    criteria = new AssetCriteria();
                    SearchTerm lSearchTerm = new SearchTerm();
                    lSearchTerm.setKey("name");
                    lSearchTerm.setOperator("LIKE");
                    lSearchTerm.setValue("Department");
                    SearchTerm[] lTerms = new SearchTerm[1];
                    lTerms[0] = lSearchTerm;
                    criteria.setSearchTerms(lTerms);
                    assets = alerInstance.assetQuerySummary(authenticationToken, criteria);
                   
                   for(int i=0; i< assets.length; i++)
                   {
                        System.out.println("Connected to OER and searched for Asset " +  assets[i].getName());
                        if(assets[i].getName().equals("{http://departmentdetailsservice/v2}Service/DepartmentFinderService")) {
                           
                           Asset asset = alerInstance.assetRead(authenticationToken, assets[i].getID());
                           asset.setVersion("3.0");
                           alerInstance.assetUpdate(authenticationToken, asset);
                       }
                   }
               
         //      alerInstance.notificationCreate(authenticationToken, arg1, arg2, arg3);
               
           
           }
           catch(OpenAPIException lEx) {
                   System.out.println("ServerCode = "+ lEx.getServerErrorCode());
                   System.out.println("Message = "+ lEx.getMessage());
                   System.out.println("StackTrace:");
                   lEx.printStackTrace();
                   } 
           catch (RemoteException lEx) {
                   lEx.printStackTrace();
                   } 
                  
               
           catch(Exception ex)
           {               
                   System.out.println("Exception " + ex.getMessage());
               
           }

           return;
       }
       
       private void connect(String repoURL, String userName, String password)  
                                                                                               
               {
                       
                       try{
                               if (alerInstance == null)
                               {
                                       URL alerRepURL  = new URL(repoURL);
                       
                                       alerInstance = new FlashlineRegistryServiceLocator().getFlashlineRegistry(alerRepURL);
                       
                                       authenticationToken = alerInstance.authTokenCreate(userName, password);
                                       System.out.println("Connected to Repository : " + repoURL);
                               }
                               else
                                       System.out.println("Connected to Repository : " + repoURL +       " using existing connection.");
                       }
                       catch(Exception ox)
                       {               
                            System.out.println("Exception connecting to Repository " + ox.getMessage());
 
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
            
                    if(repoURL.length() == 0)
                            System.out.println("Error: a repository url is required.  For example: http://localhost:7101/aler/services/FlashlineRegistry");
                    
                    
                    if(username.length() == 0)
                            System.out.println("Error: a repository userid id is required.    For example: admin");
                            
                    if(password.length() == 0)
                            System.out.println("Error: a repository password id is required.  For example: admin");
                                         
                    connect(repoURL, username, password);
                
                
                    long ASSET_ID_1 = 50006;     // must be a valid, registered asset id in OER, see Asset Editor for ID
                    long PROJECT_ID = 50000;     // must be a valid project id in OER, 50000 is "common project"
                    long EXTRACTION_ID = 0;
                    // ----------------------------------------
                    // Create a new extraction
                    long[] lAssetIDs = { ASSET_ID_1 };
                    ExtractionDownload[] extractionDownloads =
                    alerInstance.extractionCreate(authenticationToken, PROJECT_ID, lAssetIDs);
                    System.out.println("Number of new extraction downloads created: " +
                    extractionDownloads.length);
                
                  
            }
            catch(OpenAPIException lEx) {
                    System.out.println("ServerCode = "+ lEx.getServerErrorCode());
                    System.out.println("Message = "+ lEx.getMessage());
                    System.out.println("StackTrace:");
                    lEx.printStackTrace();
                    }
                
            catch (RemoteException lEx) {
                    System.out.println("Caught Remote Exception " + lEx.getMessage());
                    lEx.printStackTrace();
            } 
        
                
            catch(Exception ex) {               
                    System.out.println("Caught Exception " + ex.getMessage());   
            }
            
            
     
            return "";
    }
    
    public  String notify(String repoURL, String username, String password, String[] recipients) 
               {
                       
                       StringBuffer value = new StringBuffer(10);
                       String templateId = "asset_registered";
                       String templateId2 = "extraction_email_initial";

                       
                       try {
                       
                               if(repoURL.length() == 0)
                                       System.out.println("Error: a repository url is required.  For example: http://localhost:7101/aler/services/FlashlineRegistry");
                               
                               
                               if(username.length() == 0)
                                       System.out.println("Error: a repository userid id is required.    For example: admin");
                                       
                               if(password.length() == 0)
                                       System.out.println("Error: a repository password id is required.  For example: admin");
                                                    
                               connect(repoURL, username, password);
                           
                           
                               // read the existing substitutions based on the given template
                               String[] lSubstitutions = alerInstance.notificationSubstitutionsRead(authenticationToken, templateId);
                               
                               
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
                                        lNameValues[i].setValue("{namespace}SuperDepartment/v1");
                                    else
                                        lNameValues[i].setValue("valueof-"+"notset");
                                System.out.println("valueof-"+lSubstitutions[i]);
                               }
                               
                               // Create the Notification
                               // Creates an entry in OER SMTPQUEUE Table
                               // OER Web App will send email through configured SMTP Server,
                               // This example shows how to create a notification in addition 
                               // to the normal notifications the OER Web app creates for example as tabs are approved.
                               // A running BPM Server is not required for emails to be generated and sent.
                               alerInstance.notificationCreate(authenticationToken, templateId, recipients, lNameValues);
                         
                       //    SystemSettingsCriteria lCriteria = new SystemSettingsCriteria();
                          //     lCriteria.setNameCriteria("enterprise.defaults.displayname.field");
                     //          SettingValue[] lValues = alerInstance.systemSettingsQuery(authenticationToken, lCriteria);
                      //         for (int i=0;i<lValues.length;i++) {
                       //          SettingValue lValue = lValues[i];
                        //         System.out.println("Setting Name: " + lValue.getDescriptor().getName());
                         //        System.out.println("Setting Value: " + lValue.getValue());
                            //   }
               
                       }
                       catch(OpenAPIException lEx) {
                               System.out.println("ServerCode = "+ lEx.getServerErrorCode());
                               System.out.println("Message = "+ lEx.getMessage());
                               System.out.println("StackTrace:");
                               lEx.printStackTrace();
                               }
                           
                       catch (RemoteException lEx) {
                               System.out.println("Caught Remote Exception " + lEx.getMessage());
                               lEx.printStackTrace();
                       } 
                   
                           
                       catch(Exception ex) {               
                               System.out.println("Caught Exception " + ex.getMessage());   
                       }
                       
                       
                
                       return "";
               }
   
    
    public static void main(String Args[]) {
    
        OERFunctions obj = new OERFunctions();
        
        // Read Asset Example
        //obj.test("http://localhost:7102/oer/services/FlashlineRegistry", "admin", "welcome1", 567);
        
        // Search Example
        //obj.search("http://localhost:7102/oer/services/FlashlineRegistry", "admin", "welcome1");
        
        // Notify Example
        String[] users = new String[1];
        users[0] = "architect@soabpm-server";
          
 
       
             
    //    obj.notify("http://localhost:7102/oer/services/FlashlineRegistry", "admin", "welcome1", users);
        obj.useForSurvey("http://localhost:7102/oer/services/FlashlineRegistry", "admin", "welcome1", users);
       
    }
}
