package com.example.medicaldiagnosisapp.apiParser;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

    public static Location getCoordinates (Document doc, int index) {
        NodeList nListC = doc.getElementsByTagName("Point");
        Node nodeC = nListC.item(index);
        Element element3 = (Element) nodeC;
        String coordinates = getValue("coordinates", element3, 0);
        String [] parts = coordinates.split(",");
        double lon = Double.valueOf(parts[0]); double lat = Double.valueOf(parts[1]);
        Location loc = new Location(LocationManager.GPS_PROVIDER);
        loc.setLongitude(lon); loc.setLatitude(lat);
        return loc;
    }

    public static String getMarkerInfoName (Document doc, int KMLIndex, int infoIndex) {
        //get the right KML_id parent node
        NodeList nListC = doc.getElementsByTagName("SchemaData");
        Node nodeC = nListC.item(KMLIndex);
        Element element = (Element) nodeC;

        //avoid java.lang.NullPointerException
        if (element != null) {
            Node node = element.getElementsByTagName("SimpleData").item(infoIndex);
            Element element1 = (Element) node;
            return element1.getAttributes().getNamedItem("name").getNodeValue();
        }
        else
            return "";
    }

    public static String getMarkerInfoValue (Document doc, int KMLIndex, int infoIndex) {
        //get the right KML_id parent node
        NodeList nListC = doc.getElementsByTagName("SchemaData");
        Node nodeC = nListC.item(KMLIndex);
        Element element = (Element) nodeC;

        if (element != null) {
            NodeList nodeList = element.getElementsByTagName("SimpleData").item(infoIndex).getChildNodes();
            Node node = nodeList.item(0);
            return node.getNodeValue();
        }
        else
            return "";
    }

    public static String getValue(String tag, Element element, int index) {
        NodeList nodeList = element.getElementsByTagName(tag).item(index).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public static int getNoNodes (Document doc, String string) {
        NodeList nList = doc.getElementsByTagName(string);
        return nList.getLength();
    }

    public static Location getCoordinatesP (Document doc, int KMLindex) {
        NodeList nListC = doc.getElementsByTagName("Coordinates");
        Node nodeC = nListC.item(KMLindex);
        Element element3 = (Element) nodeC;

        String coordinates = element3.getTextContent();//getValue("coordinates", element3, 0);
        String [] parts = coordinates.split(",");
        double lon = Double.valueOf(parts[0]); double lat = Double.valueOf(parts[1]);
        Location loc = new Location(LocationManager.GPS_PROVIDER);
        loc.setLongitude(lon); loc.setLatitude(lat);
        return loc;
    }

    public static double getLongP (Document doc, int KMLindex) {
        NodeList nListC = doc.getElementsByTagName("Coordinates");
        Node nodeC = nListC.item(KMLindex);
        Element element3 = (Element) nodeC;

        String coordinates = element3.getTextContent();//getValue("coordinates", element3, 0);
        String [] parts = coordinates.split(",");
        double lon = Double.valueOf(parts[0]);
        return lon;
    }

    public static double getLatP (Document doc, int KMLindex) {
        NodeList nListC = doc.getElementsByTagName("Coordinates");
        Node nodeC = nListC.item(KMLindex);
        Element element3 = (Element) nodeC;

        String coordinates = element3.getTextContent();//getValue("coordinates", element3, 0);
        String [] parts = coordinates.split(",");
        double lat = Double.valueOf(parts[1]);
        return lat;
    }

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