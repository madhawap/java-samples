package com.mad.java.samples;

import com.mad.java.samples.beans.MovementState;
import com.mad.java.samples.utils.HeaderResolver;
import com.mad.java.samples.utils.IntersectionStateResolver;
import com.mad.java.samples.utils.SpatResolver;
import com.mad.java.samples.utils.StatesResolver;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 *
 */
public class XMLProcessor {

    private static Logger log = Logger.getLogger(XMLProcessor.class);
    private static final String FILE_LOCATION =
            "/Users/madhawa/Downloads/CustomerSupport/TfL/packs/SampleMessages/SpatEx4.xml";

    public XMLProcessor() {
        //Get complete xml and convert it to a OMElement
        OMElement jt = mapXMLtoDOM(FILE_LOCATION);

        //Pass the above xml element and get the header values sorted
        HeaderResolver headersResolver = new HeaderResolver();
        headersResolver.mapHeaderValues(jt);

        List<MovementState> movementStatelist;
        SpatResolver spatResolver = new SpatResolver();
        //Get the Intersection State nodes in a list and then pass each 'IntersectionStateResolver' element
        // to sort 'state' element and get it's results to a list.
        IntersectionStateResolver intersectionStateResolver = new IntersectionStateResolver();
        StatesResolver statesResolver = new StatesResolver();
        spatResolver.mapSpatValues(jt);
        int i = 0;
        for(OMElement temp : intersectionStateResolver.getIntersectionStateNodes(jt) ){
            statesResolver.elementWriter(temp);
            movementStatelist = statesResolver.getMovementStatelist();
            statesResolver.resetMovementStatelist();
            spatResolver.setStatesToSpatMessage(movementStatelist, i);
            i++;
        }
        spatResolver.printSPATMMessage();
    }

    public static void main(String[] args) {
        new XMLProcessor();
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


}
