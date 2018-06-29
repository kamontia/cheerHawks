package models.request.Check;

import play.data.validation.Constraints;

public class ResultPostRequest {
    @Constraints.Required
    @Constraints.Pattern("[\\w]+")
    @Constraints.MaxLength(15)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
