package models.response.Check;

import models.entity.Check;
import models.services.api.Check.CheckResponseService;
import play.libs.F;

public class ChecksDefaultResponse {

   private Integer code;
   private String status;
   private String message;
   private CheckResponse result;

    public ChecksDefaultResponse() {
    }

    /**
     *
     * @param response
     * @return
     */
    public F.Option<CheckResponse> response(Check response){
        return CheckResponseService.use().getCheckResponse(response);
    }

    public ChecksDefaultResponse badRequest(String message){
        return CheckResponseService.use().getBadRequest(message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CheckResponse getResult() {
        return result;
    }

    public void setResult(CheckResponse result) {
        this.result = result;
    }
}
