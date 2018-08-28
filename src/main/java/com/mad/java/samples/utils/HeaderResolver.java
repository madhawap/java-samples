package com.mad.java.samples.utils;

import com.mad.java.samples.spatbeans.Headers;
import org.apache.axiom.om.OMElement;

import javax.xml.namespace.QName;
import java.util.Iterator;

/**
 *
 */
public class HeaderResolver {

    //private static Logger log = Logger.getLogger(XMLProcessor.class);

    public Headers mapHeaderValues(OMElement fileElement) {
        Headers headers = new Headers();

        if (fileElement != null) {
            Iterator<?> ite1 = fileElement.getChildrenWithName(new QName("header"));
            while (ite1.hasNext()) {
                OMElement ppH = (OMElement) ite1.next();
                Iterator ite2 = ppH.getChildElements();
                while (ite2.hasNext()) {
                    OMElement pp1 = (OMElement) ite2.next();
                    if (pp1.getLocalName().equals("protocolVersion")) {
                        headers.setProtocolVersion(pp1.getText());
                    } else if (pp1.getLocalName().equals("messageID")) {
                        headers.setMessageID(pp1.getText());
                    } else if (pp1.getLocalName().equals("stationID")) {
                        headers.setStationID(pp1.getText());
                    }
                }
            }

            //            log.error("===============================================header=============================================== ");
            //            log.error("protocolVersion : " + headers.getProtocolVersion());
            //            log.error("messageID : " + headers.getMessageID());
            //            log.error("stationID : " + headers.getStationID());
            //            log.error("===============================================header=============================================== ");
        }
        return headers;
    }

}
