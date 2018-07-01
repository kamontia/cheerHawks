package models.services.api.Check;

import models.entity.Check;
import models.response.Check.CheckResponse;
import models.response.Check.ChecksDefaultResponse;
import models.setting.CheckYouStatusSetting;
import play.libs.F;
import utils.OptionUtil;

public class CheckResponseService {

    public static CheckResponseService use() {
        return new CheckResponseService();
    }

    public ChecksDefaultResponse getBadRequest(String message) {
        ChecksDefaultResponse result = new ChecksDefaultResponse();
        CheckYouStatusSetting status = CheckYouStatusSetting.NO_RESULT;
        result.setCode(status.getCode());
        result.setStatus(status.getMessage());
        result.setMessage(message);
        return result;
    }

    public F.Option<CheckResponse> getCheckResponse(Check response) {
        F.Option<Check> checkOps = OptionUtil.apply(response);
        if (checkOps.isDefined()) {
            CheckResponse checkResponse = new CheckResponse(
                    checkOps.get().getId(),
                    checkOps.get().getName(),
                    checkOps.get().getResult(),
                    checkOps.get().getCreated(),
                    checkOps.get().getModified()
            );
            return OptionUtil.apply(checkResponse);
        }
        return new F.None<CheckResponse>();
    }
}
