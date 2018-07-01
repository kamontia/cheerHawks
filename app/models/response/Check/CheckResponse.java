package models.response.Check;

import java.util.Date;

public class CheckResponse {

    private Long id;
    private String name;
    private String result;
    private Date created;
    private Date modified;

    public CheckResponse() {
    }

    public CheckResponse(Long id, String name, String result, Date created, Date modified) {
        this.id = id;
        this.name = name;
        this.result = result;
        this.created = created;
        this.modified = modified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
