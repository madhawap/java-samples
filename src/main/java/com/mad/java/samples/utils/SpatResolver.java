package com.mad.java.samples.utils;

import com.mad.java.samples.XMLProcessor;
import com.mad.java.samples.spatbeans.Id;
import com.mad.java.samples.spatbeans.IntersectionState;
import com.mad.java.samples.spatbeans.Intersections;
import com.mad.java.samples.spatbeans.MovementEvent;
import com.mad.java.samples.spatbeans.MovementState;
import com.mad.java.samples.spatbeans.Spat;
import com.mad.java.samples.spatbeans.States;
import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class SpatResolver {

    private static Logger log = Logger.getLogger(XMLProcessor.class);
    private Spat spatM = new Spat();
    private List<IntersectionState> intersectionStateList = new ArrayList<IntersectionState>();

    public void mapSpatValues(OMElement fileElement) {
        Intersections intersections = new Intersections();
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
                    while (ite3.hasNext()) {
                        IntersectionState intersectionState = new IntersectionState();
                        OMElement pp3 = (OMElement) ite3.next();
                        //intersectionsState
                        //log.error("pp3" + pp3.getLocalName());
                        Iterator ite4 = pp3.getChildElements();
                        while (ite4.hasNext() && !pp3.getLocalName().equals("states")) {
                            OMElement pp4 = (OMElement) ite4.next();
                            //name
                            //log.error("pp4" + pp4.getLocalName());
                            Iterator ite5 = pp4.getChildElements();
                            if (ite5.hasNext() && !pp4.getLocalName().equals("states")) {
                                while (ite5.hasNext() && !pp4.getLocalName().equals("states")) {
                                    OMElement pp5 = (OMElement) ite5.next();
                                    //log.error("pp5" + pp5.getLocalName());
                                    if (pp5.getLocalName().equals("id")) {
                                        id.setId(pp5.getText());
                                        //log.error(pp5.getText());
                                    } else if (pp5.getLocalName().equals("region")) {
                                        id.setRegion(pp5.getText());
                                        //log.error(pp5.getText());
                                    }
                                }
                            } else if (pp4.getLocalName().equals("name")) {
                                intersectionState.setName(pp4.getText());
                                //log.error(pp4.getText());
                            } else if (pp4.getLocalName().equals("revision")) {
                                intersectionState.setRevision(pp4.getText());
                                //log.error(pp4.getText());
                            } else if (pp4.getLocalName().equals("status")) {
                                intersectionState.setStatus(pp4.getText());
                                //log.error(pp4.getText());
                            } else if (pp4.getLocalName().equals("moy")) {
                                intersectionState.setMoy(pp4.getText());
                                //log.error(pp4.getText());
                            } else if (pp4.getLocalName().equals("timeStamp")) {
                                intersectionState.setTimeStamp(pp4.getText());
                                //log.error(pp4.getText());
                            }

                        }
                        intersectionState.setId(id);
                        intersectionStateList.add(intersectionState);
                        intersections.setIntersectionStateList(intersectionStateList);
                    }
                }

            }

        }
        //            states.setMovementStateList(movementStatelist);
        //            intersectionState.setStates(states);

        spatM.setIntersections(intersections);

    }

    public void setStatesToSpatMessage(List<MovementState> movementStatelist, int i) {
        States states = new States();
        states.setMovementStateList(movementStatelist);
        spatM.getIntersections().getIntersectionStateList().get(i).setStates(states);
    }

    public void printSPATMMessage() {
        log.error("===============================================spat"
                + "=================================================");
        int j = 0;
        for (IntersectionState tempState : spatM.getIntersections().getIntersectionStateList()) {
            log.error("name : " + spatM.getIntersections().getIntersectionStateList().get(j).getName());
            log.error("id -> region : " + spatM.getIntersections().getIntersectionStateList().get(j).getId()
                    .getRegion());
            log.error("id -> id : " + spatM.getIntersections().getIntersectionStateList().get(j).getId()
                    .getId());
            log.error(
                    "revision : " + spatM.getIntersections().getIntersectionStateList().get(j).getRevision());
            log.error("status : " + spatM.getIntersections().getIntersectionStateList().get(j).getStatus());
            log.error("moy : " + spatM.getIntersections().getIntersectionStateList().get(j).getMoy());
            log.error("timeStamp : " + spatM.getIntersections().getIntersectionStateList().get(j)
                    .getTimeStamp());
            int i = 0;

            for (MovementState temp : spatM.getIntersections().getIntersectionStateList().get(j).getStates()
                    .getMovementStateList()) {
                log.error("------------------------------MovementState - " + i +
                        " ------------------------------");
                log.error("MovementName : " + temp.getMovementName());
                log.error("Signal Group : " + temp.getSignalGroup());

                Iterator itr = temp.getStateTimeSpeed().getMovementEventList().iterator();
                while (itr.hasNext()) {
                    MovementEvent x = (MovementEvent) itr.next();
                    if (x.getEventState() == null)
                        itr.remove();
                }

                for (MovementEvent temp1 : temp.getStateTimeSpeed().getMovementEventList()) {
                    log.error("Movement Event : " + temp1.getEventState());
                    if (temp1.getTiming() != null) {
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
            j++;
        }
        log.error("===============================================spat"
                + "=================================================");

    }

}
