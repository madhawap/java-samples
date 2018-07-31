package com.mad.java.samples.utils;

import com.mad.java.samples.XMLProcessor;
import com.mad.java.samples.beans.EventState;
import com.mad.java.samples.beans.MovementEvent;
import com.mad.java.samples.beans.MovementState;
import com.mad.java.samples.beans.StateTimeSpeed;
import com.mad.java.samples.beans.Timing;
import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class StatesResolver {

    private static Logger log = Logger.getLogger(XMLProcessor.class);
    private List<MovementState> movementStatelist = new ArrayList<MovementState>();


    public List<MovementState> getMovementStatelist() {
        return movementStatelist;
    }

    public void resetMovementStatelist(){
        this.movementStatelist = new ArrayList<MovementState>();
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


    private OMElement returnOMElement(OMElement fileElement, OMElement ppG, String element){

        if(fileElement != null){
            log.info("Element clipping started form  " + fileElement.getLocalName());
            Iterator iterator1 = fileElement.getChildElements();
            while (iterator1.hasNext()){
                ppG = (OMElement) iterator1.next();

                if (ppG.getLocalName().equals(element)){
                    log.info("Element  " + ppG.getLocalName() + " clipped and sent");
                    return ppG;
                }
                Iterator ite1 = ppG.getChildElements();
                if(ite1.hasNext()){
                    returnOMElement(ppG, ppG, element);
                } else {
                    log.info("-----mmmm-------");
                }
            }

        }
        return ppG;
    }

    public void elementWriter(OMElement fileElement){
        OMElement ppL;
        if(fileElement != null){
            log.info("Start clipping from " + fileElement.getLocalName());
            OMElement ppGG = null;
            OMElement mm = returnOMElement(fileElement, ppGG, "states");
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
