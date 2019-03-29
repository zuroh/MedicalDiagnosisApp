package com.example.medicaldiagnosisapp.ApiParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class KmlParser {

    //code graveyard, just to test kml parser in android
/*    public class MainActivity extends Activity {
        TextView tv1;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tv1 = (TextView) findViewById(R.id.textView1);

            try {
                InputStream is = getAssets().open("file.xml");

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(is);

                Element element = doc.getDocumentElement();
                element.normalize();

                //NodeList nList = doc.getElementsByTagName("employee");

                //NodeList nList = doc.getElementsByTagName("Point");

                NodeList nList = doc.getElementsByTagName("SchemaData");

                for (int i = 0; i < nList.getLength(); i++) {

                    Node node = nList.item(i);//SchemaData

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        //Element element2 = (Element) node;

                        //tv1.setText(tv1.getText()+element2.getAttributes().getNamedItem("schemaUrl").getNodeValue());
                        //tv1.setText(tv1.getText()+"\nName : " + getValue("name", element2)+"\n");


                        //tv1.setText(tv1.getText() + "Surname : " + getValue("surname", element2) + "\n");
                        //tv1.setText(tv1.getText() + "-----------------------");

                        //coordinates - start
        *//*                    NodeList nListC = doc.getElementsByTagName("Point");
                            Node nodeC = nListC.item(0);//SchemaData
                            Element element3 = (Element) nodeC;
                            String coordinates = getValue("coordinates", element3, 0);
                            String [] parts = coordinates.split(",");
                            String lon = parts[0];
                            String lat = parts[1];*//*
                        tv1.setText(tv1.getText() + "Coordinates : " + getCoordinates(doc, i));
                        //coordinates - end

                        //marker info - start
                        for (int j = 0; j < 6; j++) {
                            tv1.setText(tv1.getText() + getMarkerInfoName(doc, i, j) + "\n");
                            tv1.setText(tv1.getText() + getMarkerInfoValue(doc, i, j) + "\n\n");
                        }
                        //marker info - end
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    private static Document createDocumentFromKml{
        InputStream is = getAssets().open("file.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(is);

        Element element = doc.getDocumentElement();
        element.normalize();

        return doc;
    }
    private static String getCoordinates (Document doc, int index) {
        NodeList nListC = doc.getElementsByTagName("Point");
        Node nodeC = nListC.item(index);
        Element element3 = (Element) nodeC;
        String coordinates = getValue("coordinates", element3, 0);
        String [] parts = coordinates.split(",");
        String lon = parts[0];
        String lat = parts[1];
        return lon+ "\n" +lat + "\n\n";
    }
    private static String getMarkerInfoName (Document doc, int KMLindex, int infoIndex) {
        //get the right KML_id parent node
        NodeList nListC = doc.getElementsByTagName("SchemaData");
        Node nodeC = nListC.item(KMLindex);
        Element element = (Element) nodeC;

        Node node = element.getElementsByTagName("SimpleData").item(infoIndex);
        Element element1 = (Element) node;
        return element1.getAttributes().getNamedItem("name").getNodeValue();
    }

    private static String getMarkerInfoValue (Document doc, int KMLindex, int infoIndex) {
        //get the right KML_id parent node
        NodeList nListC = doc.getElementsByTagName("SchemaData");
        Node nodeC = nListC.item(KMLindex);
        Element element = (Element) nodeC;

        NodeList nodeList = element.getElementsByTagName("SimpleData").item(infoIndex).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    private static String getValue(String tag, Element element, int index) {
        NodeList nodeList = element.getElementsByTagName(tag).item(index).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}
