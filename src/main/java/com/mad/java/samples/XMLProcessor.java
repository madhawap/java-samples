package com.mad.java.samples;

import com.mad.java.samples.beans.EventState;
import com.mad.java.samples.beans.Headers;
import com.mad.java.samples.beans.MovementEvent;
import com.mad.java.samples.beans.MovementState;
import com.mad.java.samples.beans.StateTimeSpeed;
import com.mad.java.samples.beans.Timing;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class XMLProcessor {

    private static Logger log = Logger.getLogger(XMLProcessor.class);
    private final String FILE_LOCATION =
            "/Users/madhawa/Downloads/CustomerSupport/TfL/packs/SampleMessages/SpatEx4.xml";
    private List<MovementState> movementStatelist = new ArrayList<MovementState>();
    private List<MovementState> movementStatelistTemp = new ArrayList<MovementState>();

    private  static Headers headers = new Headers();
    private boolean breakloop = true;
    OMElement pp = null;


    public XMLProcessor() {
        OMElement fileElement  = mapXMLtoDOM(FILE_LOCATION);
        elementWriter(fileElement);
        //sortList();
        for (MovementState temp : movementStatelist) {
            log.error("-----------------------------");
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
            log.error("-----------------------------");

        }
    }

    public static void main(String[] args) throws IOException {
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

        if (fileElement != null) {
            //log.info(fileElement.toString());
            Iterator<?> ite1 = fileElement.getChildrenWithName(new QName("header"));
            //log.info(fileElement.getLocalName());
            while (ite1.hasNext()){
                OMElement pp = (OMElement) ite1.next();
                log.info(pp.getLocalName());
                Iterator ite2 = pp.getChildElements();
                while (ite2.hasNext()){
                    OMElement pp1 = (OMElement) ite2.next();
                    log.info(pp1.getLocalName());
                    if(pp1.getLocalName().equals("protocolVersion")){
                        headers.setProtocolVersion(pp1.getText());
                    } else if (pp1.getLocalName().equals("messageID")){
                        headers.setMessageID(pp1.getText());
                    } else if(pp1.getLocalName().equals("stationID")){
                        headers.setStationID(pp1.getText());
                    }
                    //log.info(pp1.getQName().toString());
                }
            }

            log.info("---------------------------- " + headers.getMessageID() + ", "+ headers
                    .getStationID() + ", " + headers.getProtocolVersion());
        }
    }


    public void sortList(){

        for (int i = 0; i < movementStatelistTemp.size()-1; i++) {
            for (int k = i + 1; k < movementStatelistTemp.size(); k++) {
                if (movementStatelistTemp.get(i).getMovementName().equals(
                        movementStatelistTemp.get(k).getMovementName())) {
                    log.info(movementStatelistTemp.get(i).getMovementName() + " and " +
                            movementStatelistTemp.get(k).getMovementName() + " are same object");
                } else {
                    log.info(movementStatelistTemp.get(i).getMovementName() + "is the last form same "
                            + "movementNAme");
                    movementStatelist.add(movementStatelistTemp.get(i));
                }
            }
        }

    }

//    public void getEventStateSorted(MovementState movementStateX, Timing timingX, StateTimeSpeed
//            stateTimeSpeedX, List<MovementEvent> movementEventListX){
//
//        MovementEvent mvEventX = new MovementEvent();
//        log.info("0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0- " + pp.getLocalName());
//        if(pp.getLocalName().equals("stop-And-Remain") || pp.getLocalName().equals(
//                "protected-clearance") || pp
//                .getLocalName().equals("protected-Movement-Allowed")) {
//
//        }
//        log.info("No child nodes so came to else and set " + pp.getLocalName());
//        if(pp.getLocalName().equals("movementName")){
//            movementStateX.setMovementName(pp.getText());
//        } else if (pp.getLocalName().equals("signalGroup")){
//            movementStateX.setSignalGroup(pp.getText());
//        } else if (pp.getLocalName().equals(EventState.STOP_AND_REMAIN.getEventState())){
//
//            mvEventX.setEventState(EventState.STOP_AND_REMAIN.getEventState());
//            //                        movementEventX.setEventState(EventState.STOP_AND_REMAIN.getEventState());
//        } else if(pp.getLocalName().equals(EventState.PROTECTED_CLEARNANCE.getEventState())){
//
//            mvEventX.setEventState(EventState.PROTECTED_CLEARNANCE.getEventState());
//            //                        movementEventX.setEventState(EventState.PROTECTED_CLEARNANCE.getEventState());
//        } else if (pp.getLocalName().equals(EventState.PROTECTED_MOVEMENT_ALLOWED.getEventState
//                ())) {
//
//            mvEventX.setEventState(EventState.PROTECTED_MOVEMENT_ALLOWED.getEventState());
//            //                        movementEventX.setEventState(EventState.PROTECTED_MOVEMENT_ALLOWED.getEventState());
//        } else if (pp.getLocalName().equals("minEndTime")){
//            timingX.setMinEndTime(pp.getText());
//        } else if (pp.getLocalName().equals("maxEndTime")){
//            timingX.setMaxEndTime(pp.getText());
//        } else if (pp.getLocalName().equals("likelyTime")){
//            timingX.setLikelyTime(pp.getText());
//        } else if (pp.getLocalName().equals("confidence")){
//            timingX.setConfidence(pp.getText());
//        }
//
//        //                    movementEventX.setTiming(timingX);
//        mvEventX.setTiming(timingX);
//        movementEventListX.add(mvEventX);
//        stateTimeSpeedX.setMovementEventList(movementEventListX);
//        //                    stateTimeSpeedX.setMovementEvent(movementEventX);
//        //                    movementEventListX = stateTimeSpeedX.getMovementEventList();
//        movementStateX.setStateTimeSpeed(stateTimeSpeedX);
//
//        log.info(pp.getLocalName() + " ----- " + pp.getText());
//
//    }

    public Timing getMovementEventSorted(OMElement fileElementZ){
        Timing timingZ = new Timing();
        if(fileElementZ.getLocalName().equals("timing")){
            return getMovementEventSorted(fileElementZ,timingZ);
        }
        return timingZ;
    }

    public Timing getMovementEventSorted(OMElement fileElementY, Timing timingY){
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



    public MovementState getChildelementSorted(OMElement fileElement, MovementState movementStateX, StateTimeSpeed
                                                       stateTimeSpeedX, List<MovementEvent>
                                                       movementEventListX){
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
                        //                        movementEventX.setEventState(EventState.STOP_AND_REMAIN.getEventState());
                    } else if(pp.getLocalName().equals(EventState.PROTECTED_CLEARNANCE.getEventState())){

                        mvEventX.setEventState(EventState.PROTECTED_CLEARNANCE.getEventState());
//                        movementEventX.setEventState(EventState.PROTECTED_CLEARNANCE.getEventState());
                    } else if (pp.getLocalName().equals(EventState.PROTECTED_MOVEMENT_ALLOWED.getEventState
                            ())) {
                        mvEventX.setEventState(EventState.PROTECTED_MOVEMENT_ALLOWED.getEventState());
//                        movementEventX.setEventState(EventState.PROTECTED_MOVEMENT_ALLOWED.getEventState());
                    }

//                    movementEventX.setTiming(timingX);
                    if(pp.getLocalName().equals("timing")) {
                        log.info("+++++++++++++++++++++++++++++++++++++++++");
                        Timing timingX = getMovementEventSorted(pp);
                        log.info("MMMMMMMMMMMMMMMMMMMMMMMMM " + timingX.getMaxEndTime());
                        MovementEvent mvEventZ = new MovementEvent();
                        mvEventZ = movementEventListX.get(movementEventListX.size() -1);
                        log.info("-----------------00000000000--------------" + mvEventX.getEventState());
                        //mvEventZ.setEventState(mvEventX.getEventState());
                        mvEventZ.setTiming(timingX);
                        //mvEventX = mvEventZ;
                        //Iterator itr = movementEventListX.iterator();

                    }
                    movementEventListX.add(mvEventX);
                    stateTimeSpeedX.setMovementEventList(movementEventListX);
//                    stateTimeSpeedX.setMovementEvent(movementEventX);
//                    movementEventListX = stateTimeSpeedX.getMovementEventList();
                    movementStateX.setStateTimeSpeed(stateTimeSpeedX);

                    log.info(pp.getLocalName() + " ----- " + pp.getText());

                }

            }

        }

//        log.info(" This is the object add to the temp : " + movementStateX.getMovementName() + " " +
//                movementStateX.getSignalGroup() + "  " + movementStateX.getStateTimeSpeed().getMovementEvent
//                ().getEventState());
//        if (movementStateX.getStateTimeSpeed().getMovementEvent().getTiming() != null){
//            log.info("Confidence " + movementStateX.getStateTimeSpeed().getMovementEvent().getTiming()
//                    .getConfidence());
//        }

//        movementStatelistTemp1.add(movementState);

        return movementStateX;
    }

    public OMElement returnOMElement(OMElement fileElement, String element){

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
                    //log.info(pp.getLocalName() + " ----- " + pp.getText());
                }

            }

        }
        return pp;
    }

    public void elementWriter(OMElement fileElement){
        OMElement pp;
        if(fileElement != null){
            log.info("Start clipping from " + fileElement.getLocalName());

            OMElement mm = returnOMElement(fileElement, "states");
            log.info(" After clipping the xml  " + mm.getLocalName());

            Iterator iterator1 = mm.getChildElements();
            while (iterator1.hasNext()){
                pp = (OMElement) iterator1.next();
                log.info("First element " + pp.getLocalName());
                Iterator ite1 = pp.getChildElements();
                if(ite1.hasNext()){
//                    pp = (OMElement) ite1.next();
                    log.info("Send this " + pp.getLocalName() + " to getChildelementSorted ");
                    MovementState ms = new MovementState();
                    //MovementEvent me = new MovementEvent();
                    Timing t = new Timing();
                    StateTimeSpeed sts = new StateTimeSpeed();
                    List<MovementEvent> mel = new ArrayList<MovementEvent>();

                    ms = getChildelementSorted(pp, ms, sts,mel);
                    movementStatelist.add(ms);
                } else {
                    log.info("No child nodes so came to else and set " + pp.getLocalName());
//                    if(pp.getLocalName().equals("movementName")){
//                        movementState.setMovementName(pp.getText());
//                    } else if (pp.getLocalName().equals("signalGroup")){
//                        movementState.setSignalGroup(pp.getText());
//                    }
                    log.info(pp.getLocalName() + " ----- " + pp.getText());
                }

            }

        }
    }
}
