package xml.assignment;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement
@XmlType(propOrder = {"description", "children"})
public class Page {
    private String id;
    private String name;
    private String description;
    private Website parent;
    Collection<Widget> children = new ArrayList<>();

    public Page(){
        super();
    }

    public Page(String id, String name, Collection<Widget> children) {
        this.id = id;
        this.name = name;

        this.children = children;
    }

    public Website getParent() {
        return parent;
    }

    public void setParent(Website parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public Collection<Widget> getChildren() {
        return children;
    }

    @XmlElement
    public void setChildren(Collection<Widget> children) {
        this.children = children;
    }

    void findIDandName(NamedNodeMap attr){
        int length = attr.getLength();

        for (int i = 0; i < length; i++) {
            Node ne = attr.item(i);
            if (ne.getNodeName().equals("id"))
                this.setId(ne.getNodeValue());
            if (ne.getNodeName().equals("name"))
                this.setName(ne.getNodeValue());

            System.out.println("First child is: " + ne.getFirstChild().getNodeName());
        }
    }


}
