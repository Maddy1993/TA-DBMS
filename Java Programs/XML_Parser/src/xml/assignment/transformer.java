package xml.assignment;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class transformer {

    static Website siteObj = new Website();

    public static void main(String[] args){
        try{
            //Get the document descriptor
            Document doc = getDocumentDescriptor();

            Element root = doc.getDocumentElement();
            NodeList child = root.getChildNodes();

            System.out.println("Node: " + root.getNodeName() + " " + root.getNodeType());

            if(root.hasAttributes())
                setNodeAttributes(root.getAttributes(), siteObj);

            setChildNodes(child, " ", siteObj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Document getDocumentDescriptor(){
        //Get the Document Builder Instance
        DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();

        Document doc = null;

        try {
            //using the factory instace, get the document builder
            DocumentBuilder docBuilder = dbFac.newDocumentBuilder();

            //Get the input file
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Enter the input XML file name: ");
            final String fileName = "site1.xml";
    //                    reader.nextLine(); //

            Path filePath = Paths.get(System.getProperty("user.dir"),
                    Paths.get(
                            Paths.get("src",
                                    Paths.get("xml",
                                            "assignment").toString()).toString(),
                            fileName).toString());

            File file = new File(filePath.toString());

            //get the parser object
            doc = docBuilder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }

        //normalize the document
        assert doc != null;
        doc.getDocumentElement().normalize();

        return doc;
    }

    private static void setNodeAttributes(NamedNodeMap attr, Object obj){

        if(obj != null) {
            if (obj instanceof Website) {
                setWebsiteAttributes((Website)obj, attr);
            } else if (obj instanceof Page){
                setPageAttributes((Page)obj, attr);
            } else if(obj instanceof Widget){
                setWidgetAttributes((Widget)obj, attr);
            }
        }
    }

    private static Website setWebsiteAttributes(Website obj, NamedNodeMap attr){

        //set description
        obj.setDescription("Loriem Ipsum");

        //for every attribute of site
        obj.findIDandName(attr);

        return obj;
    }

    private static Page setPageAttributes(Page obj, NamedNodeMap attr){

        //for every attribute of site
        obj.findIDandName(attr);

        return obj;
    }

    private static Widget setWidgetAttributes(Widget obj, NamedNodeMap attr){

        //for every attribute of widget
        obj.setAttributes(attr);

        return obj;
    }

    private static void setChildNodes(NodeList child, String separator, Object obj) {
        int length = child.getLength();

        for (int i = 0; i < length; i++) {
            Node element = child.item(i);

            Page newItem = null;
            Widget newWid = null;
            if (element.getNodeType() == Node.ELEMENT_NODE ||
                    element.getNodeType() == Node.ATTRIBUTE_NODE) {
                System.out.println(separator + "Node: " + element.getNodeName());


                if (element.hasAttributes()) {
                    if(element.getNodeName().equals("view")) {
                        newItem = new Page();
                        newItem.setParent((Website)obj);
                        newItem.getParent().getChildren().add(newItem);
                        setNodeAttributes(element.getAttributes(), newItem);

                    } else if (element.getNodeName().equals("component")) {
                        if (element.getParentNode().getNodeName().equals("components")){
                            NamedNodeMap t = element.getAttributes();
                            newWid = siteObj.
                                        getWidgets().
                                        get(
                                            t.getNamedItem("id").getNodeValue());

                            setNodeAttributes(t, newWid);

                        } else{
                            newWid = new Widget();
                            Page temp = (Page) obj;
                            newWid.setParent(temp);
                            temp.getChildren().add(newWid);
                            setNodeAttributes(element.getAttributes(), newWid);
                            temp.getParent()
                                    .getWidgets().put(newWid.getId(), newWid);
                        }
                    }
                }

                if (element.hasChildNodes()) {
                    NodeList childNodes = element.getChildNodes();
                    if(element.getNodeName().equals("views")) {
                        setChildNodes(childNodes, "  ", obj);
                    } else if(element.getNodeName().equals("view")){
                        setChildNodes(childNodes, "  ", newItem);
                    } else if (element.getNodeName().equals("components")){
                        setChildNodes(childNodes, "  ", null);
                    }
                }

                if (element.getNodeName().equals("description")){
                    Node parent = element.getParentNode();
                    if (parent.getNodeName().equals("view")){
                        Page newP = (Page) obj;
                        newP.setDescription(element.getFirstChild().getTextContent());
                    }
                }
            }
        }
    }
}
