package controllers.api;

import models.entity.Check;
import models.response.Check.CheckPagingResponse;
import models.response.Check.CheckResponse;
import models.response.Check.ChecksDefaultResponse;
import models.setting.CheckYouStatusSetting;
import play.libs.F;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.ConfigUtil;

import java.util.ArrayList;
import java.util.List;

public class Checks extends Controller {

    /**
     * id を指定して結果を取得
     *
     * @param id
     * @return
     */
    public static Result check(Long id) {
        ChecksDefaultResponse result = new ChecksDefaultResponse();
        F.Option<Check> checkOps = new Check(id).unique();

        if (checkOps.isDefined()) {
            /* enum */
            CheckYouStatusSetting status = CheckYouStatusSetting.OK;
            result.setCode(status.getCode());
            result.setStatus(status.getMessage());
            result.setResult(result.response(checkOps.get()).getOrElse(new CheckResponse()));
            return ok(Json.toJson(result));
        }

        return badRequest(Json.toJson(
                result.badRequest(ConfigUtil.get("checkYou.setting.message.error.noResult").getOrElse(""))));
    }

    /**
     * 複数の結果を一括取得
     *
     * @param page
     * @return
     */
    public static Result getList(Integer page) {
        CheckPagingResponse result = new CheckPagingResponse();
        F.Option<List<CheckResponse>> list = new Check().entitiesList(page).map(new CheckToCheckResponse());
        Integer maxPage = new Check().entitiesMaxPage(0);

        if (list.isDefined()) {
            CheckYouStatusSetting status = CheckYouStatusSetting.OK;
            result.setCode(status.getCode());
            result.setStatus(status.getMessage());
            result.setMaxPage(maxPage);
            result.setResults(list.get());
            return ok(Json.toJson(result));
        }
        return badRequest(Json.toJson(
                result.badRequest(ConfigUtil.get("checkYou.setting.message.error.noResultList").getOrElse(""))
                )

        );
    }


    /**
     * Check -> CheckResponseへの変換
     */
    private static class CheckToCheckResponse implements F.Function<List<Check>, List<CheckResponse>> {
        @Override
        public List<CheckResponse> apply(List<Check> checkList) throws Throwable {
            List<CheckResponse> result = new ArrayList<CheckResponse>();
            CheckResponse checkPostResponse;
            for (Check check : checkList) {
                checkPostResponse = new CheckResponse(
                        check.getId(),
                        check.getName(),
                        check.getResult(),
                        check.getCreated(),
                        check.getModified()
                );
                result.add(checkPostResponse);
            }
            return result;
        }
    }
}