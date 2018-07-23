package com.xmlFile;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class WriteXMLFile {

    public WriteXMLFile() {
    }

    public void createXml(DatabaseInformation databaseInformation) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("EspacoLife");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("Staff");
            rootElement.appendChild(staff);

            // set attribute to staff element
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            staff.setAttributeNode(attr);

            // User elements
            Element user = doc.createElement("user");
            user.appendChild(doc.createTextNode(databaseInformation.getUser()));
            staff.appendChild(user);

            // Password elements
            Element password = doc.createElement("password");
            password.appendChild(doc.createTextNode(databaseInformation.getPassword()));
            staff.appendChild(password);

            // Url elements
            Element url = doc.createElement("url");
            url.appendChild(doc.createTextNode(databaseInformation.getUrl()));
            staff.appendChild(url);

            // Port elements
            Element port = doc.createElement("port");
            port.appendChild(doc.createTextNode(databaseInformation.getPort()));
            staff.appendChild(port);

            // database elements
            Element database = doc.createElement("database");
            database.appendChild(doc.createTextNode(databaseInformation.getDatabase()));
            staff.appendChild(database);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("databaseFile.xml"));

            // Output to console for testing
            //result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        }
        catch (ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }
        catch (TransformerException tfe)
        {
            tfe.printStackTrace();
        }
    }

    public void parseXml(DatabaseInformation databaseInformation){
        try {
            File inputFile = new File("databaseFile.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Staff");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    databaseInformation.setUser(eElement
                            .getElementsByTagName("user")
                            .item(0)
                            .getTextContent());
                    System.out.println("User: "
                            + databaseInformation.getUser());

                    databaseInformation.setPassword(eElement
                            .getElementsByTagName("password")
                            .item(0)
                            .getTextContent());
                    System.out.println("Password: "
                            + databaseInformation.getPassword());

                    databaseInformation.setUrl(eElement
                            .getElementsByTagName("url")
                            .item(0)
                            .getTextContent());
                    System.out.println("Url: "
                            + databaseInformation.getUrl());

                    databaseInformation.setPort(eElement
                            .getElementsByTagName("port")
                            .item(0)
                            .getTextContent());
                    System.out.println("Port: "
                            + databaseInformation.getPort());

                    databaseInformation.setDatabase(eElement
                            .getElementsByTagName("database")
                            .item(0)
                            .getTextContent());
                    System.out.println("Database: "
                            + databaseInformation.getDatabase());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

