package xml.assignment;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement
public class Widget {
    private String id = null;
    private String type = null;

    private String src = null;
    private String url = null;
    private String size = null;
    private String html = null;
    private String text = null;

    private Page parent;

    public Widget(String id, String type, String src, String url, String size, String html, String text) {
        this.id = id;
        this.type = type;
        this.src = src;
        this.url = url;
        this.size = size;
        this.html = html;
        this.text = text;
    }

    public Widget(){
        super();
    }

    public Page getParent() {
        return parent;
    }

    public void setParent(Page parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    @XmlAttribute
    public void setSrc(String src) {
        this.src = src;
    }

    public String getText() {
        return text;
    }

    @XmlElement
    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    @XmlAttribute
    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    @XmlAttribute
    public void setSize(String size) {
        this.size = size;
    }

    public String getHtml() {
        return html;
    }

    @XmlAttribute
    public void setHtml(String html) {
        this.html = html;
    }

    void setAttributes(NamedNodeMap attr){
        int length = attr.getLength();

        for (int i = 0; i < length; i++) {
            Node ne = attr.item(i);

            switch (ne.getNodeName()){
                case "id":
                    if(this.getId() == null)
                        this.setId(ne.getNodeValue());
                    break;
                case "type":
                    this.setType(ne.getNodeValue());
                    break;
                case "src":
                    if(this.getType().equals("IMG"))
                        this.setSrc(ne.getNodeValue());
                    break;
                case "url":
                    if (this.getType().equals("YOUTUBE"))
                        this.setUrl(ne.getNodeValue());
                    break;
                case "size":
                    if (this.getType().equals("HEADING"))
                        this.setSize(ne.getNodeValue());
                    break;
                case "html":
                    if (this.getType().equals("HTML"))
                        this.setHtml(ne.getNodeValue());
                    break;
                default:
                    break;
            }

        }
    }
}