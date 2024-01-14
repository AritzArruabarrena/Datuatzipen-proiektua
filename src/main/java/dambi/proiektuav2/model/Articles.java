package dambi.proiektuav2.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Articles {
    
    @Id
    private Integer id;
    private String url;
    private String title;
    private String feed;
    private String type;
    private Date pub;
    private Date ret;
    private String lang;
    private List<String> refs;
    private String sum;
    private String body;
    private String text;

    
    public Articles() {
        // Empty constructor
    }

    public Articles(Integer id, String url, String title, String feed, String type, Date pub, Date ret, String lang,
        List<String> refs, String sum, String body, String text) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.feed = feed;
        this.type = type;
        this.pub = pub;
        this.ret = ret;
        this.lang = lang;
        this.refs = refs;
        this.sum = sum;
        this.body = body;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getFeed() {
        return feed;
    }


    public void setFeed(String feed) {
        this.feed = feed;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Date getPub() {
        return pub;
    }


    public void setPub(Date pub) {
        this.pub = pub;
    }


    public Date getRet() {
        return ret;
    }


    public void setRet(Date ret) {
        this.ret = ret;
    }


    public String getLang() {
        return lang;
    }


    public void setLang(String lang) {
        this.lang = lang;
    }


    public List<String> getRefs() {
        return refs;
    }


    public void setRefs(List<String> refs) {
        this.refs = refs;
    }


    public String getSum() {
        return sum;
    }


    public void setSum(String sum) {
        this.sum = sum;
    }


    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    };

    

}
