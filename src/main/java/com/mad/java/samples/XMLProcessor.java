package com.mad.java.samples;

import com.mad.java.samples.beans.EventState;
import com.mad.java.samples.beans.Headers;
import com.mad.java.samples.beans.Id;
import com.mad.java.samples.beans.IntersectionState;
import com.mad.java.samples.beans.Intersections;
import com.mad.java.samples.beans.MovementEvent;
import com.mad.java.samples.beans.MovementState;
import com.mad.java.samples.beans.Spat;
import com.mad.java.samples.beans.StateTimeSpeed;
import com.mad.java.samples.beans.States;
import com.mad.java.samples.beans.Timing;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class XMLProcessor {

    private static Logger log = Logger.getLogger(XMLProcessor.class);
    private static final String FILE_LOCATION =
            "/Users/madhawa/Downloads/CustomerSupport/TfL/packs/SampleMessages/SpatEx2.xml";
    private List<MovementState> movementStatelist = new ArrayList<MovementState>();
    private boolean breakloop = true;
    OMElement pp = null;


    public XMLProcessor() {
        OMElement fileElement  = mapXMLtoDOM(FILE_LOCATION);
        mapHeaderValues();
        elementWriter(fileElement);
        mapSpatValues();
        //        for (MovementState temp : movementStatelist) {
//            log.error("-----------------------------");
//            log.error("MovementName : " + temp.getMovementName());
//            log.error("Signal Group : " + temp.getSignalGroup());
//
//            Iterator itr = temp.getStateTimeSpeed().getMovementEventList().iterator();
//            while (itr.hasNext())
//            {
//                MovementEvent x = (MovementEvent)itr.next();
//                if (x.getEventState() == null)
//                    itr.remove();
//            }
//
//            for (MovementEvent temp1 : temp.getStateTimeSpeed().getMovementEventList()){
//                log.error("Movement Event : " + temp1.getEventState());
//                if(temp1.getTiming() != null){
//                    log.error("Min End Time : " + temp1.getTiming().getMinEndTime());
//                    log.error("Max End Time : " + temp1.getTiming().getMaxEndTime());
//                    log.error("Likely Time : " + temp1.getTiming().getLikelyTime());
//                    log.error("Confidence : " + temp1.getTiming().getConfidence());
//                }
//            }
//            log.error("-----------------------------");
//
//        }
    }

    public static void main(String[] args) {
        new XMLProcessor();
    }


    private static OMElement mapXMLtoDOM(String fileLocation) {
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




    private void mapHeaderValues(){
        OMElement fileElement  = mapXMLtoDOM(FILE_LOCATION);
        Headers headers = new Headers();

        if (fileElement != null) {
            Iterator<?> ite1 = fileElement.getChildrenWithName(new QName("header"));
            while (ite1.hasNext()){
                OMElement ppH = (OMElement) ite1.next();
                //log.error(ppH.getLocalName());
                Iterator ite2 = ppH.getChildElements();
                while (ite2.hasNext()){
                    OMElement pp1 = (OMElement) ite2.next();
                    //log.error(pp1.getLocalName());
                    if(pp1.getLocalName().equals("protocolVersion")){
                        headers.setProtocolVersion(pp1.getText());
                    } else if (pp1.getLocalName().equals("messageID")){
                        headers.setMessageID(pp1.getText());
                    } else if(pp1.getLocalName().equals("stationID")){
                        headers.setStationID(pp1.getText());
                    }
                }
            }

            log.error("===============================================header=============================================== ");
            log.error("protocolVersion : " + headers.getProtocolVersion());
            log.error("messageID : " + headers.getMessageID());
            log.error("stationID : " + headers.getStationID());
            log.error("===============================================header=============================================== ");
        }
    }


    private void mapSpatValues() {
        OMElement fileElement = mapXMLtoDOM(FILE_LOCATION);
        Spat spat = new Spat();
        Intersections intersections = new Intersections();
        IntersectionState intersectionState = new IntersectionState();
        States states = new States();
        Id id = new Id();

        if (fileElement != null) {
            Iterator<?> ite1 = fileElement.getChildrenWithName(new QName("spat"));
            while (ite1.hasNext()) {
                OMElement pp1 = (OMElement) ite1.next();
                //log.error("pp1" + pp1.getLocalName());
                Iterator ite2 = pp1.getChildElements();
                    while (ite2.hasNext()) {
                        OMElement pp2 = (OMElement) ite2.next();
                        //log.error("pp2" + pp2.getLocalName());
                        Iterator ite3 = pp2.getChildElements();
                        while(ite3.hasNext()){
                            OMElement pp3 = (OMElement) ite3.next();
                            //log.error("pp3" + pp3.getLocalName());
                            Iterator ite4 = pp3.getChildElements();
                            while(ite4.hasNext() && !pp3.getLocalName().equals("states")){
                                OMElement pp4 = (OMElement) ite4.next();
                                //log.error("pp4" + pp4.getLocalName());
                                Iterator ite5 = pp4.getChildElements();
                                if(pp4.getLocalName().equals("name")){
                                    intersectionState.setName(pp4.getText());
                                    //log.error(pp4.getText());
                                } else if(pp4.getLocalName().equals("revision")){
                                    intersectionState.setRevision(pp4.getText());
                                    //log.error(pp4.getText());
                                } else if(pp4.getLocalName().equals("status")){
                                    intersectionState.setStatus(pp4.getText());
                                    //log.error(pp4.getText());
                                } else if(pp4.getLocalName().equals("moy")){
                                    intersectionState.setMoy(pp4.getText());
                                    //log.error(pp4.getText());
                                } else if(pp4.getLocalName().equals("timeStamp")){
                                    intersectionState.setTimeStamp(pp4.getText());
                                    //log.error(pp4.getText());
                                }
                                while(ite5.hasNext() && !pp4.getLocalName().equals("states")) {
                                        OMElement pp5 = (OMElement) ite5.next();
                                        //log.error("pp5" + pp5.getLocalName());
                                        if(pp5.getLocalName().equals("id")){
                                            id.setId(pp5.getText());
                                            //log.error(pp5.getText());
                                        } else if(pp5.getLocalName().equals("region")){
                                            id.setRegion(pp5.getText());
                                            //log.error(pp5.getText());
                                        }
                                }
                            }
                        }
                    }

            }
            intersectionState.setId(id);
            states.setMovementStateList(movementStatelist);
            intersectionState.setStates(states);
            intersections.setIntersectionState(intersectionState);
            spat.setIntersections(intersections);
        }

        log.error("===============================================spat"
                + "=================================================");
        log.error("name : " + spat.getIntersections().getIntersectionState().getName());
        log.error("id -> region : " + spat.getIntersections().getIntersectionState().getId().getRegion());
        log.error("id -> id : " + spat.getIntersections().getIntersectionState().getId().getId());
        log.error("revision : " + spat.getIntersections().getIntersectionState().getRevision());
        log.error("status : " + spat.getIntersections().getIntersectionState().getStatus());
        log.error("moy : " + spat.getIntersections().getIntersectionState().getMoy());
        log.error("timeStamp : " + spat.getIntersections().getIntersectionState().getTimeStamp());
        int i = 0;

        for (MovementState temp : spat.getIntersections().getIntersectionState().getStates()
                .getMovementStateList()) {
            log.error("------------------------------MovementState - " + i +
                    " ------------------------------");
            log.error("MovementName : " + temp.getMovementName());
            log.error("Signal Group : " + temp.getSignalGroup());

            Iterator itr = temp.getStateTimeSpeed().getMovementEventList().iterator();
            while (itr.hasNext())
            {
                MovementEvent x = (MovementEvent)itr.next();
                if (x.getEventState() == null)
                    itr.remove();
            }

            for (MovementEvent temp1 : temp.getStateTimeSpeed().getMovementEventList()){
                log.error("Movement Event : " + temp1.getEventState());
                if(temp1.getTiming() != null){
                    log.error("Min End Time : " + temp1.getTiming().getMinEndTime());
                    log.error("Max End Time : " + temp1.getTiming().getMaxEndTime());
                    log.error("Likely Time : " + temp1.getTiming().getLikelyTime());
                    log.error("Confidence : " + temp1.getTiming().getConfidence());
                }
            }
            log.error("------------------------------MovementState - " + i + " "
                    + "------------------------------");
            i++;

        }
        log.error("===============================================spat"
                + "=================================================");



    }

    private Timing getMovementEventSorted(OMElement fileElementZ){
        Timing timingZ = new Timing();
        if(fileElementZ.getLocalName().equals("timing")){
            return getMovementEventSorted(fileElementZ,timingZ);
        }
        return timingZ;
    }

    private Timing getMovementEventSorted(OMElement fileElementY, Timing timingY){
        OMElement ppY;

            log.info("++++++++++++++++The element sent to sort out is  " + fileElementY.getLocalName());
            Iterator iterator1 = fileElementY.getChildElements();
            while (iterator1.hasNext()) {
                ppY = (OMElement) iterator1.next();
                log.info("+++++++++++++++I got " + ppY.getLocalName());
                Iterator ite1 = ppY.getChildElements();
                if (ite1.hasNext()) {
                    getMovementEventSorted(ppY, timingY);
                } else {
                    log.info("++++++++++++++++++No child nodes so came to else and set " + ppY.getLocalName());

                    if (ppY.getLocalName().equals("minEndTime")) {
                        timingY.setMinEndTime(ppY.getText());
                    } else if (ppY.getLocalName().equals("maxEndTime")) {
                        timingY.setMaxEndTime(ppY.getText());
                    } else if (ppY.getLocalName().equals("likelyTime")) {
                        timingY.setLikelyTime(ppY.getText());
                    } else if (ppY.getLocalName().equals("confidence")) {
                        timingY.setConfidence(ppY.getText());
                    }

                }
            }
        log.info("+++++++++++++++++++++++++++" +timingY.getConfidence());
        return timingY;
    }


    private MovementState getChildelementSorted(OMElement fileElement, MovementState movementStateX,
                                                StateTimeSpeed stateTimeSpeedX,
                                                List<MovementEvent> movementEventListX){
        OMElement pp;
        if(fileElement != null){
            log.info("The element sent to sort out is " + fileElement.getLocalName());
            Iterator iterator1 = fileElement.getChildElements();
            while (iterator1.hasNext()){
                pp = (OMElement) iterator1.next();
                log.info("I got " + pp.getLocalName());
                Iterator ite1 = pp.getChildElements();
                if(ite1.hasNext() && !pp.getLocalName().equals("timing")){
                    getChildelementSorted(pp, movementStateX, stateTimeSpeedX, movementEventListX);
                } else {
                    MovementEvent mvEventX = new MovementEvent();

                    log.info("No child nodes so came to else and set " + pp.getLocalName());
                    if(pp.getLocalName().equals("movementName")){
                        movementStateX.setMovementName(pp.getText());
                    } else if (pp.getLocalName().equals("signalGroup")){
                        movementStateX.setSignalGroup(pp.getText());
                    } else if (pp.getLocalName().equals(EventState.STOP_AND_REMAIN.getEventState())){

                        mvEventX.setEventState(EventState.STOP_AND_REMAIN.getEventState());
                    } else if(pp.getLocalName().equals(EventState.PROTECTED_CLEARNANCE.getEventState())){

                        mvEventX.setEventState(EventState.PROTECTED_CLEARNANCE.getEventState());
                    } else if (pp.getLocalName().equals(EventState.PROTECTED_MOVEMENT_ALLOWED.getEventState
                            ())) {
                        mvEventX.setEventState(EventState.PROTECTED_MOVEMENT_ALLOWED.getEventState());
                    }

                    if(pp.getLocalName().equals("timing")) {
                        log.info("+++++++++++++++++++++++++++++++++++++++++");
                        Timing timingX = getMovementEventSorted(pp);
                        log.info("MMMMMMMMMMMMMMMMMMMMMMMMM " + timingX.getMaxEndTime());
                        MovementEvent mvEventZ;
                        mvEventZ = movementEventListX.get(movementEventListX.size() -1);
                        log.info("-----------------00000000000--------------" + mvEventX.getEventState());
                        mvEventZ.setTiming(timingX);
                    }
                    movementEventListX.add(mvEventX);
                    stateTimeSpeedX.setMovementEventList(movementEventListX);
                    movementStateX.setStateTimeSpeed(stateTimeSpeedX);

                    log.info(pp.getLocalName() + " ----- " + pp.getText());
                }
            }
        }
        return movementStateX;
    }

    private OMElement returnOMElement(OMElement fileElement, String element){

        if(fileElement != null){
            log.info("Element clipping started form  " + fileElement.getLocalName());
            Iterator iterator1 = fileElement.getChildElements();
            while (iterator1.hasNext()){
                pp = (OMElement) iterator1.next();

                if (pp.getLocalName().equals(element)){
                    log.info("Element  " + pp.getLocalName() + " clipped and sent");
                    breakloop = false;
                    return pp;
                }
                Iterator ite1 = pp.getChildElements();
                if(ite1.hasNext() && breakloop){
                    returnOMElement(pp, element);
                } else {
                    log.info("-----mmmm-------");
                }
            }

        }
        return pp;
    }

    private void elementWriter(OMElement fileElement){
        OMElement ppL;
        if(fileElement != null){
            log.info("Start clipping from " + fileElement.getLocalName());

            OMElement mm = returnOMElement(fileElement, "states");
            log.info(" After clipping the xml  " + mm.getLocalName());

            Iterator iterator1 = mm.getChildElements();
            while (iterator1.hasNext()){
                ppL = (OMElement) iterator1.next();
                log.info("First element " + ppL.getLocalName());
                Iterator ite1 = ppL.getChildElements();
                if(ite1.hasNext()){
                    log.info("Send this " + ppL.getLocalName() + " to getChildelementSorted ");
                    MovementState ms = new MovementState();
                    StateTimeSpeed sts = new StateTimeSpeed();
                    List<MovementEvent> mel = new ArrayList<MovementEvent>();

                    ms = getChildelementSorted(ppL, ms, sts,mel);
                    movementStatelist.add(ms);
                } else {
                    log.info("No child nodes so came to else and set " + ppL.getLocalName());
                    log.info(ppL.getLocalName() + " ----- " + ppL.getText());
                }

            }

        }
    }
}
