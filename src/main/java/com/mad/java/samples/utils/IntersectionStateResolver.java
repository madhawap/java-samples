package com.mad.java.samples.utils;

import org.apache.axiom.om.OMElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by madhawa on 7/30/18.
 */
public class IntersectionStateResolver {

    public List<OMElement> getIntersectionStateNodes(OMElement fileElement) {
        List<OMElement> intersectionsStateList = new ArrayList<OMElement>();
        OMElement fileElement1;
        OMElement fileElement2;
        if (fileElement != null) {
            Iterator<?> ite1 = fileElement.getChildElements();
            while (ite1.hasNext()) {
                fileElement1 = (OMElement) ite1.next();
                Iterator<?> ite2 = fileElement1.getChildElements();
                if(ite2.hasNext() && fileElement1.getLocalName().equals("spat")) {
                    while(ite2.hasNext()){
                        fileElement2 = (OMElement) ite2.next();
                        Iterator<?> ite3 = fileElement2.getChildElements();
                        if(ite3.hasNext() && fileElement2.getLocalName().equals("intersections")) {
                            while(ite3.hasNext() && fileElement2.getLocalName().equals("intersections")) {
                                OMElement fileElement3 = (OMElement) ite3.next();
                                intersectionsStateList.add(fileElement3);
                            }
                        }
                    }
                }
            }
        }
        return intersectionsStateList;
    }

}
