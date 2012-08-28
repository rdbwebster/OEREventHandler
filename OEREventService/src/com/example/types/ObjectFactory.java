
package com.example.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AssetUnregistered_QNAME = new QName("http://www.bea.com/aler/events", "AssetUnregistered");
    private final static QName _AssetTabApproved_QNAME = new QName("http://www.bea.com/aler/events", "AssetTabApproved");
    private final static QName _ExtractionReassign_QNAME = new QName("http://www.bea.com/aler/events", "ExtractionReassign");
    private final static QName _NewEventRequestString_QNAME = new QName("http://www.bea.com/infra/events", "newEventRequestString");
    private final static QName _ALERExtendedData_QNAME = new QName("http://www.bea.com/aler/events", "ALERExtendedData");
    private final static QName _AssetAllTabApproved_QNAME = new QName("http://www.bea.com/aler/events", "AssetAllTabApproved");
    private final static QName _AssetStatusChanged_QNAME = new QName("http://www.bea.com/aler/events", "AssetStatusChanged");
    private final static QName _AssetUnassign_QNAME = new QName("http://www.bea.com/aler/events", "AssetUnassign");
    private final static QName _RelatedAssetRegister_QNAME = new QName("http://www.bea.com/aler/events", "RelatedAssetRegister");
    private final static QName _AssetAssign_QNAME = new QName("http://www.bea.com/aler/events", "AssetAssign");
    private final static QName _AssetRegistered_QNAME = new QName("http://www.bea.com/aler/events", "AssetRegistered");
    private final static QName _PolicyAssertionChanged_QNAME = new QName("http://www.bea.com/aler/events", "PolicyAssertionChanged");
    private final static QName _MultiUse_QNAME = new QName("http://www.bea.com/aler/events", "MultiUse");
    private final static QName _ExtractionSubsequentNotify_QNAME = new QName("http://www.bea.com/aler/events", "ExtractionSubsequentNotify");
    private final static QName _RegistrationStatusChanged_QNAME = new QName("http://www.bea.com/aler/events", "RegistrationStatusChanged");
    private final static QName _ComplianceTemplateApplied_QNAME = new QName("http://www.bea.com/aler/events", "ComplianceTemplateApplied");
    private final static QName _NewVersionRegistered_QNAME = new QName("http://www.bea.com/aler/events", "NewVersionRegistered");
    private final static QName _NewEventRequestResponseString_QNAME = new QName("http://www.bea.com/infra/events", "newEventRequestResponseString");
    private final static QName _ExtractionIntialNotify_QNAME = new QName("http://www.bea.com/aler/events", "ExtractionIntialNotify");
    private final static QName _NewEventRequestResponse_QNAME = new QName("http://www.bea.com/infra/events", "newEventRequestResponse");
    private final static QName _AssetUnsubmitted_QNAME = new QName("http://www.bea.com/aler/events", "AssetUnsubmitted");
    private final static QName _AssetSubmissionRejected_QNAME = new QName("http://www.bea.com/aler/events", "AssetSubmissionRejected");
    private final static QName _MetaDataChanged_QNAME = new QName("http://www.bea.com/aler/events", "MetaDataChanged");
    private final static QName _AssetSubmission_QNAME = new QName("http://www.bea.com/aler/events", "AssetSubmission");
    private final static QName _StringVal_QNAME = new QName("http://www.bea.com/infra/events", "StringVal");
    private final static QName _CategorizationChanged_QNAME = new QName("http://www.bea.com/aler/events", "CategorizationChanged");
    private final static QName _AssetAccepted_QNAME = new QName("http://www.bea.com/aler/events", "AssetAccepted");
    private final static QName _Event_QNAME = new QName("http://www.bea.com/infra/events", "Event");
    private final static QName _NewEventRequest_QNAME = new QName("http://www.bea.com/infra/events", "newEventRequest");
    private final static QName _ExtractionStatusChanged_QNAME = new QName("http://www.bea.com/aler/events", "ExtractionStatusChanged");
    private final static QName _AssetUnaccepted_QNAME = new QName("http://www.bea.com/aler/events", "AssetUnaccepted");
    private final static QName _AssetTabUnApprove_QNAME = new QName("http://www.bea.com/aler/events", "AssetTabUnApprove");
    private final static QName _ExtendedData_QNAME = new QName("http://www.bea.com/infra/events", "ExtendedData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ALERExtendedData }
     * 
     */
    public ALERExtendedData createALERExtendedData() {
        return new ALERExtendedData();
    }

    /**
     * Create an instance of {@link AssetAllTabApproved }
     * 
     */
    public AssetAllTabApproved createAssetAllTabApproved() {
        return new AssetAllTabApproved();
    }

    /**
     * Create an instance of {@link AssetUnregistered }
     * 
     */
    public AssetUnregistered createAssetUnregistered() {
        return new AssetUnregistered();
    }

    /**
     * Create an instance of {@link AssetTabApproved }
     * 
     */
    public AssetTabApproved createAssetTabApproved() {
        return new AssetTabApproved();
    }

    /**
     * Create an instance of {@link ExtractionReassign }
     * 
     */
    public ExtractionReassign createExtractionReassign() {
        return new ExtractionReassign();
    }

    /**
     * Create an instance of {@link RegistrationStatusChanged }
     * 
     */
    public RegistrationStatusChanged createRegistrationStatusChanged() {
        return new RegistrationStatusChanged();
    }

    /**
     * Create an instance of {@link ComplianceTemplateApplied }
     * 
     */
    public ComplianceTemplateApplied createComplianceTemplateApplied() {
        return new ComplianceTemplateApplied();
    }

    /**
     * Create an instance of {@link AssetUnassign }
     * 
     */
    public AssetUnassign createAssetUnassign() {
        return new AssetUnassign();
    }

    /**
     * Create an instance of {@link AssetStatusChanged }
     * 
     */
    public AssetStatusChanged createAssetStatusChanged() {
        return new AssetStatusChanged();
    }

    /**
     * Create an instance of {@link RelatedAssetRegister }
     * 
     */
    public RelatedAssetRegister createRelatedAssetRegister() {
        return new RelatedAssetRegister();
    }

    /**
     * Create an instance of {@link AssetAssign }
     * 
     */
    public AssetAssign createAssetAssign() {
        return new AssetAssign();
    }

    /**
     * Create an instance of {@link AssetRegistered }
     * 
     */
    public AssetRegistered createAssetRegistered() {
        return new AssetRegistered();
    }

    /**
     * Create an instance of {@link PolicyAssertionChanged }
     * 
     */
    public PolicyAssertionChanged createPolicyAssertionChanged() {
        return new PolicyAssertionChanged();
    }

    /**
     * Create an instance of {@link MultiUse }
     * 
     */
    public MultiUse createMultiUse() {
        return new MultiUse();
    }

    /**
     * Create an instance of {@link ExtractionSubsequentNotify }
     * 
     */
    public ExtractionSubsequentNotify createExtractionSubsequentNotify() {
        return new ExtractionSubsequentNotify();
    }

    /**
     * Create an instance of {@link ExtractionIntialNotify }
     * 
     */
    public ExtractionIntialNotify createExtractionIntialNotify() {
        return new ExtractionIntialNotify();
    }

    /**
     * Create an instance of {@link AssetUnsubmitted }
     * 
     */
    public AssetUnsubmitted createAssetUnsubmitted() {
        return new AssetUnsubmitted();
    }

    /**
     * Create an instance of {@link AssetSubmissionRejected }
     * 
     */
    public AssetSubmissionRejected createAssetSubmissionRejected() {
        return new AssetSubmissionRejected();
    }

    /**
     * Create an instance of {@link MetaDataChanged }
     * 
     */
    public MetaDataChanged createMetaDataChanged() {
        return new MetaDataChanged();
    }

    /**
     * Create an instance of {@link AssetSubmission }
     * 
     */
    public AssetSubmission createAssetSubmission() {
        return new AssetSubmission();
    }

    /**
     * Create an instance of {@link NewVersionRegistered }
     * 
     */
    public NewVersionRegistered createNewVersionRegistered() {
        return new NewVersionRegistered();
    }

    /**
     * Create an instance of {@link ExtractionStatusChanged }
     * 
     */
    public ExtractionStatusChanged createExtractionStatusChanged() {
        return new ExtractionStatusChanged();
    }

    /**
     * Create an instance of {@link AssetUnaccepted }
     * 
     */
    public AssetUnaccepted createAssetUnaccepted() {
        return new AssetUnaccepted();
    }

    /**
     * Create an instance of {@link AssetTabUnApprove }
     * 
     */
    public AssetTabUnApprove createAssetTabUnApprove() {
        return new AssetTabUnApprove();
    }

    /**
     * Create an instance of {@link CategorizationStatusChanged }
     * 
     */
    public CategorizationStatusChanged createCategorizationStatusChanged() {
        return new CategorizationStatusChanged();
    }

    /**
     * Create an instance of {@link AssetAccepted }
     * 
     */
    public AssetAccepted createAssetAccepted() {
        return new AssetAccepted();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Extraction }
     * 
     */
    public Extraction createExtraction() {
        return new Extraction();
    }

    /**
     * Create an instance of {@link MultiUseParam }
     * 
     */
    public MultiUseParam createMultiUseParam() {
        return new MultiUseParam();
    }

    /**
     * Create an instance of {@link Asset }
     * 
     */
    public Asset createAsset() {
        return new Asset();
    }

    /**
     * Create an instance of {@link Relationship }
     * 
     */
    public Relationship createRelationship() {
        return new Relationship();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link EventSource }
     * 
     */
    public EventSource createEventSource() {
        return new EventSource();
    }

    /**
     * Create an instance of {@link EventInfo }
     * 
     */
    public EventInfo createEventInfo() {
        return new EventInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetUnregistered }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetUnregistered", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetUnregistered> createAssetUnregistered(AssetUnregistered value) {
        return new JAXBElement<AssetUnregistered>(_AssetUnregistered_QNAME, AssetUnregistered.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetTabApproved }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetTabApproved", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetTabApproved> createAssetTabApproved(AssetTabApproved value) {
        return new JAXBElement<AssetTabApproved>(_AssetTabApproved_QNAME, AssetTabApproved.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtractionReassign }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "ExtractionReassign", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<ExtractionReassign> createExtractionReassign(ExtractionReassign value) {
        return new JAXBElement<ExtractionReassign>(_ExtractionReassign_QNAME, ExtractionReassign.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/infra/events", name = "newEventRequestString")
    public JAXBElement<String> createNewEventRequestString(String value) {
        return new JAXBElement<String>(_NewEventRequestString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ALERExtendedData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "ALERExtendedData", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<ALERExtendedData> createALERExtendedData(ALERExtendedData value) {
        return new JAXBElement<ALERExtendedData>(_ALERExtendedData_QNAME, ALERExtendedData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetAllTabApproved }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetAllTabApproved", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetAllTabApproved> createAssetAllTabApproved(AssetAllTabApproved value) {
        return new JAXBElement<AssetAllTabApproved>(_AssetAllTabApproved_QNAME, AssetAllTabApproved.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetStatusChanged }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetStatusChanged", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetStatusChanged> createAssetStatusChanged(AssetStatusChanged value) {
        return new JAXBElement<AssetStatusChanged>(_AssetStatusChanged_QNAME, AssetStatusChanged.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetUnassign }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetUnassign", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetUnassign> createAssetUnassign(AssetUnassign value) {
        return new JAXBElement<AssetUnassign>(_AssetUnassign_QNAME, AssetUnassign.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RelatedAssetRegister }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "RelatedAssetRegister", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<RelatedAssetRegister> createRelatedAssetRegister(RelatedAssetRegister value) {
        return new JAXBElement<RelatedAssetRegister>(_RelatedAssetRegister_QNAME, RelatedAssetRegister.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetAssign }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetAssign", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetAssign> createAssetAssign(AssetAssign value) {
        return new JAXBElement<AssetAssign>(_AssetAssign_QNAME, AssetAssign.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetRegistered }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetRegistered", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetRegistered> createAssetRegistered(AssetRegistered value) {
        return new JAXBElement<AssetRegistered>(_AssetRegistered_QNAME, AssetRegistered.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicyAssertionChanged }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "PolicyAssertionChanged", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<PolicyAssertionChanged> createPolicyAssertionChanged(PolicyAssertionChanged value) {
        return new JAXBElement<PolicyAssertionChanged>(_PolicyAssertionChanged_QNAME, PolicyAssertionChanged.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiUse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "MultiUse", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<MultiUse> createMultiUse(MultiUse value) {
        return new JAXBElement<MultiUse>(_MultiUse_QNAME, MultiUse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtractionSubsequentNotify }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "ExtractionSubsequentNotify", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<ExtractionSubsequentNotify> createExtractionSubsequentNotify(ExtractionSubsequentNotify value) {
        return new JAXBElement<ExtractionSubsequentNotify>(_ExtractionSubsequentNotify_QNAME, ExtractionSubsequentNotify.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrationStatusChanged }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "RegistrationStatusChanged", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<RegistrationStatusChanged> createRegistrationStatusChanged(RegistrationStatusChanged value) {
        return new JAXBElement<RegistrationStatusChanged>(_RegistrationStatusChanged_QNAME, RegistrationStatusChanged.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComplianceTemplateApplied }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "ComplianceTemplateApplied", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<ComplianceTemplateApplied> createComplianceTemplateApplied(ComplianceTemplateApplied value) {
        return new JAXBElement<ComplianceTemplateApplied>(_ComplianceTemplateApplied_QNAME, ComplianceTemplateApplied.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewVersionRegistered }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "NewVersionRegistered", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<NewVersionRegistered> createNewVersionRegistered(NewVersionRegistered value) {
        return new JAXBElement<NewVersionRegistered>(_NewVersionRegistered_QNAME, NewVersionRegistered.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/infra/events", name = "newEventRequestResponseString")
    public JAXBElement<String> createNewEventRequestResponseString(String value) {
        return new JAXBElement<String>(_NewEventRequestResponseString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtractionIntialNotify }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "ExtractionIntialNotify", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<ExtractionIntialNotify> createExtractionIntialNotify(ExtractionIntialNotify value) {
        return new JAXBElement<ExtractionIntialNotify>(_ExtractionIntialNotify_QNAME, ExtractionIntialNotify.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Event }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/infra/events", name = "newEventRequestResponse")
    public JAXBElement<Event> createNewEventRequestResponse(Event value) {
        return new JAXBElement<Event>(_NewEventRequestResponse_QNAME, Event.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetUnsubmitted }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetUnsubmitted", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetUnsubmitted> createAssetUnsubmitted(AssetUnsubmitted value) {
        return new JAXBElement<AssetUnsubmitted>(_AssetUnsubmitted_QNAME, AssetUnsubmitted.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetSubmissionRejected }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetSubmissionRejected", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetSubmissionRejected> createAssetSubmissionRejected(AssetSubmissionRejected value) {
        return new JAXBElement<AssetSubmissionRejected>(_AssetSubmissionRejected_QNAME, AssetSubmissionRejected.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetaDataChanged }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "MetaDataChanged", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<MetaDataChanged> createMetaDataChanged(MetaDataChanged value) {
        return new JAXBElement<MetaDataChanged>(_MetaDataChanged_QNAME, MetaDataChanged.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetSubmission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetSubmission", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetSubmission> createAssetSubmission(AssetSubmission value) {
        return new JAXBElement<AssetSubmission>(_AssetSubmission_QNAME, AssetSubmission.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/infra/events", name = "StringVal")
    public JAXBElement<String> createStringVal(String value) {
        return new JAXBElement<String>(_StringVal_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategorizationStatusChanged }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "CategorizationChanged", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<CategorizationStatusChanged> createCategorizationChanged(CategorizationStatusChanged value) {
        return new JAXBElement<CategorizationStatusChanged>(_CategorizationChanged_QNAME, CategorizationStatusChanged.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetAccepted }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetAccepted", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetAccepted> createAssetAccepted(AssetAccepted value) {
        return new JAXBElement<AssetAccepted>(_AssetAccepted_QNAME, AssetAccepted.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Event }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/infra/events", name = "Event")
    public JAXBElement<Event> createEvent(Event value) {
        return new JAXBElement<Event>(_Event_QNAME, Event.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Event }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/infra/events", name = "newEventRequest")
    public JAXBElement<Event> createNewEventRequest(Event value) {
        return new JAXBElement<Event>(_NewEventRequest_QNAME, Event.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtractionStatusChanged }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "ExtractionStatusChanged", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<ExtractionStatusChanged> createExtractionStatusChanged(ExtractionStatusChanged value) {
        return new JAXBElement<ExtractionStatusChanged>(_ExtractionStatusChanged_QNAME, ExtractionStatusChanged.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetUnaccepted }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetUnaccepted", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetUnaccepted> createAssetUnaccepted(AssetUnaccepted value) {
        return new JAXBElement<AssetUnaccepted>(_AssetUnaccepted_QNAME, AssetUnaccepted.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssetTabUnApprove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/aler/events", name = "AssetTabUnApprove", substitutionHeadNamespace = "http://www.bea.com/infra/events", substitutionHeadName = "ExtendedData")
    public JAXBElement<AssetTabUnApprove> createAssetTabUnApprove(AssetTabUnApprove value) {
        return new JAXBElement<AssetTabUnApprove>(_AssetTabUnApprove_QNAME, AssetTabUnApprove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtendedData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bea.com/infra/events", name = "ExtendedData")
    public JAXBElement<ExtendedData> createExtendedData(ExtendedData value) {
        return new JAXBElement<ExtendedData>(_ExtendedData_QNAME, ExtendedData.class, null, value);
    }

}
