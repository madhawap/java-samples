package com.mad.java.samples;

import com.mad.java.samples.beans.Headers;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 *
 */
public class BasicXMLProcessor {

    private static Logger log = Logger.getLogger(XMLProcessor.class);
    private final String FILE_LOCATION =
            "/Users/madhawa/Downloads/CustomerSupport/TfL/packs/SampleMessages/SpatEx2.xml";
    private Headers headers = new Headers();

    public BasicXMLProcessor() {
        mapXMLtoDOM(FILE_LOCATION);
        mapHeaderValues();
    }

    public static void main(String[] args) {
        new BasicXMLProcessor();
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

    private void mapHeaderValues(){
        OMElement fileElement  = mapXMLtoDOM(FILE_LOCATION);

        if (fileElement != null) {
            Iterator<?> ite1 = fileElement.getChildrenWithName(new QName("header"));
            while (ite1.hasNext()){
                OMElement pp = (OMElement) ite1.next();
                    log.info(pp.getLocalName());
                    Iterator ite2 = pp.getChildElements();
                    while(ite2.hasNext()) {
                        OMElement pp1 = (OMElement) ite2.next();
                        log.info(pp1.getLocalName());
                        if (pp1.getLocalName().equals("protocolVersion")) {
                            headers.setProtocolVersion(pp1.getText());
                        } else if (pp1.getLocalName().equals("messageID")) {
                            headers.setMessageID(pp1.getText());
                        } else if (pp1.getLocalName().equals("stationID")) {
                            headers.setStationID(pp1.getText());
                        }
                    }
                }
            }

            log.info("---------------------------- " + headers.getMessageID() + ", "+ headers
                    .getStationID() + ", " + headers.getProtocolVersion());
        }

}
