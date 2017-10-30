package kov.develop.server.repository;

import kov.develop.shared.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import kov.develop.shared.PointType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XmlService {

    public Object getObject(File file, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(file);

        return object;
    }

    public void saveObject(File file, Object o) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(o, file);
    }



    public static List<Point> getDbFromXml (String file) {
        File xmlFile = new File(file);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        List<Point> points = new ArrayList<Point>();
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile.getAbsolutePath());
            document.getDocumentElement().normalize();
            //Get all nodes Point
            NodeList nodeList = document.getElementsByTagName("point");
            for (int i = 0; i < nodeList.getLength(); i++) {
                points.add(getPoint(nodeList.item(i)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return points;
    }

    private static Point getPoint(Node node) {
        Point point = new Point();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            point.setId(Integer.parseInt(getTagValue("id", element)));
            point.setCountry(getTagValue("country", element));
            point.setSity(getTagValue("sity", element));
            point.setAdress(getTagValue("adress", element));
            point.setName(getTagValue("name", element));
            point.setPhone(getTagValue("phone", element));
            point.setType(PointType.valueOf(getTagValue("type", element)));
        }

        return point;
    }

    // получаем значение элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}
