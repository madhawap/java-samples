package com.mad.java.samples;

import com.mad.java.samples.dataholders.LookUpData;
import com.mad.java.samples.dataholders.PictoCodeLookUpData;
import com.mad.java.samples.ivstempbeans.IVSTempData;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class IVSMessageProcessor {

    private static Logger log = Logger.getLogger(IVSMessageProcessor.class);
    private static final String FILE_LOCATION
            = "/Users/madhawa/Downloads/CustomerSupport/TfL/packs/SampleMessages/IVSTest.xml";
    private List<PictoCodeLookUpData> pictolookUpDataList = new ArrayList<PictoCodeLookUpData>();
    private List<LookUpData> lookUpDataList = new ArrayList<LookUpData>();
    private List<IVSTempData> ivsTempDataList = new ArrayList<IVSTempData>();

    private IVSMessageProcessor() {
        loadMappingTableDetails();
        mediate();
        getIVSTempData();
    }

    public static void main(String[] args) {
        new IVSMessageProcessor();
    }

    private OMElement mapXMLtoDOM(String fileLocation) {
        File file = new File(fileLocation);
        OMElement fileElement = null;
        if (file.exists()) {
            try {
                InputStream xmlInputStream = new FileInputStream(file);
                fileElement = new StAXOMBuilder(xmlInputStream).getDocumentElement();
            } catch (Exception e) {
                log.error("Error while parsing XML file : " + file.getAbsolutePath());
            }
        }
        return fileElement;
    }

    private void mediate() {
        OMElement d2LogicalModel = mapXMLtoDOM(FILE_LOCATION);

        if (d2LogicalModel != null) {
            Iterator<?> d2LogicalModelElementCheckIterator = d2LogicalModel.getChildElements();
            while (d2LogicalModelElementCheckIterator != null && d2LogicalModelElementCheckIterator
                    .hasNext()) {
                OMElement d2LogicalModelChildren = (OMElement) d2LogicalModelElementCheckIterator.next();
                //log.error("mediate : " + d2LogicalModelChildren.getLocalName());
                if (d2LogicalModelChildren.getLocalName().equals("payloadPublication")) {
                    Iterator<?> payloadPublicationChildrenCheckIterator = d2LogicalModelChildren
                            .getChildElements();
                    int i = 0;
                    while (payloadPublicationChildrenCheckIterator != null
                            && payloadPublicationChildrenCheckIterator.hasNext()) {
                        OMElement payloadPublicationChildren
                                = (OMElement) payloadPublicationChildrenCheckIterator.next();
                        //log.error("mediate " + i + " : " + payloadPublicationChildren.getLocalName());
                        i++;
                        if (payloadPublicationChildren.getLocalName().equals("vmsUnit")) {
                            Iterator<?> vmsUnitChildrenCheckIterator = payloadPublicationChildren
                                    .getChildElements();
                            log.debug("mmmmmmmmmm");
                            vmsUnitDataHandler(vmsUnitChildrenCheckIterator);
                        }
                    }
                }
            }
        }
    }

    private void vmsUnitDataHandler(Iterator<?> vmsUnitChildrenCheckIterator) {
        if (vmsUnitChildrenCheckIterator != null) {
            while (vmsUnitChildrenCheckIterator.hasNext()) {
                OMElement vmsUnitChildren = (OMElement) vmsUnitChildrenCheckIterator.next();
                IVSTempData ivsTempData = new IVSTempData();
                ivsTempDataList.add(ivsTempData);
                if (vmsUnitChildren.getLocalName().equals("vmsUnitReference")) {
                    //log.error("vmsUnitDataHandler  : " + vmsUnitChildren.getAttributeValue(new QName
                    // ("id")));
                    String assetNumber = vmsUnitChildren.getAttributeValue(new QName("id"));
                    getRelatedLookuptableData(assetNumber, ivsTempData);
                } else if(vmsUnitChildren.getLocalName().equals("vms")){
                    Iterator<?> vmsIterator = vmsUnitChildren.getChildElements();
                    log.debug("gggggggggggggggggggg");
                    getHORUSPictoCode(ivsTempData, vmsIterator);
                }
            }
        }
    }

    private void getHORUSPictoCode(IVSTempData ivsTempData, Iterator<?> outervmsElementIterator){
        if (outervmsElementIterator != null) {
            //-------------------
            while (outervmsElementIterator.hasNext()) {
                OMElement invms = (OMElement) outervmsElementIterator.next();
                if (invms.getLocalName().equals("vms")) {
                    Iterator<?> invmsIterator = invms.getChildElements();
                    //-----------------
                    while (invmsIterator.hasNext()) {
                        OMElement outervmsMessage = (OMElement) invmsIterator.next();
                        log.debug("tttttttttttttttttttttt vmsWorking " + outervmsMessage.getLocalName());
                        if (outervmsMessage.getLocalName().equals("vmsMessage")) {
                            Iterator<?> outervmsMessageIterator = outervmsMessage.getChildElements();
                            //----------------
                            while (outervmsMessageIterator.hasNext()) {
                                OMElement invmsMessage = (OMElement) outervmsMessageIterator.next();
                                log.debug("+++++++++++++++++++++++++++++++++ vmsMessage " + invmsMessage
                                        .getLocalName());
                                if(invmsMessage.getLocalName().equals("vmsMessage")){
                                    Iterator<?> invmsMessageIterator = invmsMessage.getChildElements();
                                    //------------------
                                    while(invmsMessageIterator.hasNext()){
                                        OMElement outervmsPictogramDisplayArea = (OMElement)
                                                invmsMessageIterator.next();
                                        log.debug("wmwmwmwmwmwmwmwmwmwmwmwmwmw vmsPictogramDisplayArea " +
                                                outervmsPictogramDisplayArea
                                                .getLocalName());
                                        if(outervmsPictogramDisplayArea.getLocalName().equals(
                                                "vmsPictogramDisplayArea")){
                                            Iterator<?> outervmsPictogramDisplayAreaIterator =
                                                    outervmsPictogramDisplayArea
                                                    .getChildElements();
                                            //------------------
                                            while (outervmsPictogramDisplayAreaIterator.hasNext()){
                                                OMElement innervmsPictogramDisplayArea = (OMElement)
                                                        outervmsPictogramDisplayAreaIterator.next();
                                                log.debug("eeeeeeeeeeeeeeeeeeeeeeeeeee "
                                                        + "vmsPictogramDisplayArea " +
                                                        innervmsPictogramDisplayArea.getLocalName());
                                                if(innervmsPictogramDisplayArea.getLocalName().equals(
                                                        "vmsPictogramDisplayArea")){
                                                    Iterator<?> innervmsPictogramDisplayAreaIterator =
                                                            innervmsPictogramDisplayArea.getChildElements();
                                                    //------------------
                                                    while(innervmsPictogramDisplayAreaIterator.hasNext()){
                                                        OMElement outervmsPictogram = (OMElement)
                                                                innervmsPictogramDisplayAreaIterator.next();
                                                        log.debug("adadadadadadadadadadadadadadadada " +
                                                                outervmsPictogram.getLocalName());
                                                        if(outervmsPictogram.getLocalName().equals(
                                                                "vmsPictogram")){
                                                            Iterator<?> outervmsPictogramIterator =
                                                                    outervmsPictogram.getChildElements();
                                                            //-----------------
                                                            while (outervmsPictogramIterator.hasNext()){
                                                                OMElement innervmsPictogram = (OMElement)
                                                                outervmsPictogramIterator.next();
                                                                log.debug("lmlmlmlmlmlmlmlmlmlmlmlmlml "
                                                                        + "" + innervmsPictogram
                                                                        .getLocalName());
                                                                if(innervmsPictogram.getLocalName().equals(
                                                                        "vmsPictogram")){
                                                                    Iterator<?> innervmsPictogramIterator =
                                                                            innervmsPictogram
                                                                                    .getChildElements();
                                                                    //-----------------
                                                                    while(innervmsPictogramIterator.hasNext()){
                                                                        OMElement
                                                                                innervpictogramCodemsPictogram
                                                                                = (OMElement)
                                                                                innervmsPictogramIterator.next();
                                                                        log.debug
                                                                                ("oooooooooooooooooooooooooo "
                                                                                + "" + innervpictogramCodemsPictogram
                                                                                .getLocalName());
                                                                        if(innervpictogramCodemsPictogram
                                                                                .getLocalName().equals(
                                                                                "pictogramCode")) {
                                                                            String horuspictoCode
                                                                                    = innervpictogramCodemsPictogram
                                                                                    .getText();
                                                                            log.debug(
                                                                                    "-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0- "
                                                                                            + horuspictoCode);
                                                                            getrelatedPictoCodeDetails(
                                                                                    horuspictoCode,
                                                                                    ivsTempData);
                                                                            //----------------
                                                                        }
                                                                    }
                                                                }
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void getRelatedLookuptableData(String assetNumber, IVSTempData ivsTempData) {
        for (LookUpData lookUpData : lookUpDataList) {
            if (lookUpData.getAssetNumberLane1() != null && lookUpData.getAssetNumberLane1().equals(
                    assetNumber)) {
                ivsTempData.setLane("Lane1");
                setIVSTempValues(ivsTempData, lookUpData);
            } else if (lookUpData.getAssetNumberLane2() != null && lookUpData.getAssetNumberLane2().equals(
                    assetNumber)) {
                ivsTempData.setLane("Lane2");
                setIVSTempValues(ivsTempData, lookUpData);
            } else if (lookUpData.getAssetNumberLane3() != null && lookUpData.getAssetNumberLane3().equals(
                    assetNumber)) {
                ivsTempData.setLane("Lane3");
                setIVSTempValues(ivsTempData, lookUpData);
            } else if (lookUpData.getAssetNumberLane4() != null && lookUpData.getAssetNumberLane4().equals(
                    assetNumber)) {
                ivsTempData.setLane("Lane4");
                setIVSTempValues(ivsTempData, lookUpData);
            }
        }
    }

    private void setIVSTempValues(IVSTempData ivsTempData, LookUpData lookUpData) {
        ivsTempData.setGantryLatitude(lookUpData.getGantryLatitude());
        ivsTempData.setGantryLongitude(lookUpData.getGantryLongitude());
        ivsTempData.setGantryNumber(lookUpData.getGantryNumber());
        ivsTempData.setZone1Latitude(lookUpData.getZone1Latitude());
        ivsTempData.setGantryLongitude(lookUpData.getZone1Longitude());
        ivsTempData.setZone2Longitude(lookUpData.getZone2Longitude());
        ivsTempData.setZone2Latitude(lookUpData.getZone2Latitude());
        ivsTempData.setZone1DeltaLatitude(lookUpData.getZone1DeltaLatitude());
        ivsTempData.setZone1DeltaLongitude(lookUpData.getZone1DeltaLongitude());
        ivsTempData.setHeading(lookUpData.getHeading());
        ivsTempData.setZone2DeltaLatitude(lookUpData.getZone2DeltaLatitude());
        ivsTempData.setZone2DeltaLongitude(lookUpData.getZone2DeltaLongitude());
        ivsTempData.setHeading2(lookUpData.getHeading2());
        ivsTempData.setDirectionOfTravel(lookUpData.getDirectionOfTravel());
    }

    private void getrelatedPictoCodeDetails(String pictoCode, IVSTempData ivsTempData ) {
        for(PictoCodeLookUpData pictoCodeLookUpData : pictolookUpDataList ){
            if(pictoCodeLookUpData.getHorusCode() == Integer.parseInt(pictoCode)){
                log.error("YaY!!!!!!!!");
            }
        }
    }

    private void getIVSTempData() {
        int m = 0;
        for (IVSTempData ivsTempData : ivsTempDataList) {
            log.error("----------------------- Look up table Row " + m + "----------------------------");
            if (ivsTempData.getGantryLatitude() != 0.0) {
                log.error(ivsTempData.getGantryLatitude());
                log.error(ivsTempData.getGantryLongitude());
                log.error(ivsTempData.getGantryNumber());
                log.error(ivsTempData.getZone1Latitude());
                log.error(ivsTempData.getZone1Longitude());
                log.error(ivsTempData.getZone2Longitude());
                log.error(ivsTempData.getZone2Latitude());
                log.error(ivsTempData.getZone1DeltaLatitude());
                log.error(ivsTempData.getZone1DeltaLongitude());
                log.error(ivsTempData.getHeading());
                log.error(ivsTempData.getZone2DeltaLatitude());
                log.error(ivsTempData.getZone2DeltaLongitude());
                log.error(ivsTempData.getHeading2());
                log.error(ivsTempData.getDirectionOfTravel());
                log.error(ivsTempData.getLane());
            }
            m++;
        }
    }

    private void loadMappingTableDetails() {
        JsonToPOJO jsonToPOJO = new JsonToPOJO();

        Reader readerJsonArray = null;
        Reader readerPictoJsonArray = null;

        //Json look up table Array to a JavaList
        try {
            readerJsonArray = new FileReader(
                    "/Users/madhawa/Downloads/CustomerSupport/TfL/ASN/Lookuptables/gantry_lookup_table.json");
            lookUpDataList = jsonToPOJO.jsonArrayToList(readerJsonArray);
        } catch (IOException e) {
            log.error("");
        } finally {
            try {
                if (readerJsonArray != null) {
                    readerJsonArray.close();
                }

            } catch (IOException e) {
                log.error("");
            }
        }

        //Json Picto look up table Array to a JavaList
        try {
            readerPictoJsonArray = new FileReader(
                    "/Users/madhawa/Downloads/CustomerSupport/TfL/ASN/Lookuptables/Picto_lookup_table"
                            + ".json");
            pictolookUpDataList = jsonToPOJO.jsonPictoArrayToList(readerPictoJsonArray);
        } catch (IOException e) {
            log.error("");
        } finally {
            try {
                if (readerPictoJsonArray != null) {
                    readerPictoJsonArray.close();
                }

            } catch (IOException e) {
                log.error("");
            }
        }
    }
}
