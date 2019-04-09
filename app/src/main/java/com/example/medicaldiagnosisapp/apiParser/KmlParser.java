package com.example.medicaldiagnosisapp.apiParser;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * KmlParser parses a kml file, creating a document for easy usage in java
 * Specific methods are specified to obtain application relevant information
 * from the document.
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class KmlParser {

    /**
     * Creates a DOM from a kml/xml file
     * @param context required for accessing assets where the files are stored
     * @param fileName required to know which kml/xml file to process
     * @return doc Document returned for repeat usages
     * @throws Exception In case context is null or no file with fillname is found
     */
    public static Document createDocumentFromKml(Context context, String fileName) throws Exception{
        InputStream is = context.getAssets().open(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(is);
        Element element = doc.getDocumentElement();
        element.normalize();
        return doc;
    }

    /**
     * Gets the number of Nodes with specified name
     * @param doc the KML document
     * @param string the specified name to search for
     * @return number of nodes
     */
    public static int getNoNodes (Document doc, String string) {
        NodeList nList = doc.getElementsByTagName(string);
        return nList.getLength();
    }

    /**
     * Gets the relevant information for Polyclinic (Longitude)
     * @param doc the KML document
     * @param KMLindex the index where the marker information is contained in the document
     * @return lon the longitude in double
     */
    public static double getLongP (Document doc, int KMLindex) {
        NodeList nListC = doc.getElementsByTagName("Coordinates");
        Node nodeC = nListC.item(KMLindex);
        Element element3 = (Element) nodeC;

        String coordinates = element3.getTextContent();
        String [] parts = coordinates.split(",");
        double lon = Double.valueOf(parts[0]);
        return lon;
    }

    /**
     * Gets the relevant information for Polyclinic (Latitude)
     * @param doc the KML document
     * @param KMLindex the index where the marker information is contained in the document
     * @return lat the latitude in double
     */
    public static double getLatP (Document doc, int KMLindex) {
        NodeList nListC = doc.getElementsByTagName("Coordinates");
        Node nodeC = nListC.item(KMLindex);
        Element element3 = (Element) nodeC;

        String coordinates = element3.getTextContent();
        String [] parts = coordinates.split(",");
        double lat = Double.valueOf(parts[1]);
        return lat;
    }

    /**
     * Gets the relevant information for Polyclinic (Name)
     * @param doc the KML document
     * @param KMLIndex the index where the marker information is contained in the document
     * @return the marker information as a String. An empty String is returned if null.
     */
    public static String getMarkerTitleP (Document doc, int KMLIndex) {
        //get the right KML_id parent node
        NodeList nListC = doc.getElementsByTagName("Placemark");
        Node nodeC = nListC.item(KMLIndex);
        Element element = (Element) nodeC;

        //avoid java.lang.NullPointerException
        if (element != null) {
            Node node = element.getElementsByTagName("Name").item(0);
            return node.getFirstChild().getNodeValue();
        }
        else
            return "";
    }

    /**
     * Gets the relevant information for Polyclinic (Telephone number)
     * @param doc the KML document
     * @param KMLIndex the index where the marker information is contained in the document
     * @return the marker information as a String. An empty String is returned if null.
     */
    public static String getMarkerInfoP (Document doc, int KMLIndex) {
        //get the right KML_id parent node
        NodeList nListC = doc.getElementsByTagName("Placemark");
        Node nodeC = nListC.item(KMLIndex);
        Element element = (Element) nodeC;

        //avoid java.lang.NullPointerException
        if (element != null) {
            Node node1 = element.getElementsByTagName("Tel").item(0);
            return node1.getFirstChild().getNodeValue();
        }
        else
            return "";
    }
}
