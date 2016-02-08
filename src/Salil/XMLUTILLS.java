package Salil;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Created by salil on 8/2/16.
 */
public class XMLUTILLS {

    public static void addFieldToDocElem(Document doc, Element rootElem, String name, String value, boolean isCDATA) {
        if (value == null) {
            return;
        }
        Element elem = doc.createElement("field");
        elem.setAttribute("name", name);
        if (isCDATA) {
            CDATASection cdataSection = doc.createCDATASection(value);
            elem.appendChild(cdataSection);
        } else {
            Text textNode = doc.createTextNode(value);
            elem.appendChild(textNode);
        }

        rootElem.appendChild(elem);
    }
}