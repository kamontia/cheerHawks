package models.entity;

import models.services.Check.CheckModelService;
import models.services.Check.CheckService;
import play.db.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.libs.F;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "checks")
public class Check extends Model {
    @Id
    private Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    private String result;

    @Formats.DateTime(pattern = "yyyy/MM/dd")
    private Date created;

    @Formats.DateTime(pattern = "yyy/MM/dd")
    private Date modified;


    @Transient
    private CheckService checkService = new CheckService();

    @Transient
    private CheckModelService checkModelService = new CheckModelService();

    /*
     * 結果を取得
     * @return
     */
    public F.Option<String> result() {
        return checkService.getResult(this.name);
    }

    /*
     * 結果を保存
     * @return
     */
    public F.Option<Check> store() {
        return checkModelService.save(this);

    }

    /*
     * 結果を取得
     * @return
     */
    public F.Option<Check> unique() {
        return checkModelService.findById(id);
    }

    /* 指定ページの一覧
     * @param page
     * @return
     */
    public F.Option<List<Check>> entitiesList(Integer page) {
        return checkModelService.findWithPage(page);
    }

    /* ページ結果を取得
     * @param value
     * @return
     */
    public Integer entitiesMaxPage(Integer value) {
        return checkModelService.getMaxPage().getOrElse(value);
    }

    public Check() {
        super();
    }

    public Check(Long id) {
        this.id = id;
    }

    public Check(String name){
        this.name = name;
    }

    public Check(String name, String result) {
        this.name = name;
        this.result = result;
        this.created = new Date();
        this.modified = new Date();
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
