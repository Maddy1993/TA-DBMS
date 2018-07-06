package xml.assignment;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
@XmlType(propOrder = {"description", "children"})
public class Website {

    private String description;
    private String id;
    private String name;
    Collection<Page> children = new ArrayList<>();
    Map<String, Widget> widgets = new HashMap<>();

    public Website(){
        super();
    }

    public Website(String description, String id, String name, Collection<Page> children) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(Map<String, Widget> widgets) {
        this.widgets = widgets;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute
    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    private void setName(String name) {
        this.name = name;
    }

    Collection<Page> getChildren() {
        return children;
    }

    @XmlElement
    private void setChildren(Collection<Page> children) {
        this.children = children;
    }

    public String getDesciption() {
        return description;
    }

    @XmlElement
    void setDescription(String desciption) {
        this.description = desciption;
    }

    void findIDandName(NamedNodeMap attr){
        int length = attr.getLength();

        for (int i = 0; i < length; i++) {
            Node ne = attr.item(i);
            if (ne.getNodeName().equals("id"))
                this.setId(ne.getNodeValue());
            if (ne.getNodeName().equals("name"))
                this.setName(ne.getNodeValue());

        }
    }
}
