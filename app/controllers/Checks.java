package controllers;

import models.entity.Check;
import models.request.Check.ResultPostRequest;
import play.Play;
import play.data.Form;
import play.libs.F;
import play.mvc.Result;
import utils.ConfigUtil;

import java.util.List;

import static play.data.Form.form;

public class Checks extends Apps {

    public static Result index() {
        Form<ResultPostRequest> form = form(ResultPostRequest.class);
        String config = Play.application().configuration().getString("TestKey");
        String config1 = Play.application().configuration().getString("checkYou.setting.message.title");
        return ok(views.html.check.index.render(
                ConfigUtil.get("checkYou.setting.message.title").getOrElse("NoTittle"),
                ConfigUtil.get("checkYou.setting.message.question").getOrElse("質問はありません"),
                form));
    }

    /**
     * 結果表示用
     *
     * @return
     */
    public static Result results() {
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bindFromRequest();
        if (form.error("name") != null && form.error("name").message().contains("error.required")) {
            return validationErrorIndexResult("名前欄が空欄です", form);
        }
        if (form.error("name") != null && form.error("name").message().contains("error.pattern")) {
            return validationErrorIndexResult("TwitterのID形式ではありません", form);
        }
        if (form.error("name") != null && form.error("name").message().contains("error.maxLength")) {
            return validationErrorIndexResult("文字数が15文字を超えています", form);
        }

        String name = form.data().get("name");

        Check checkOrigin = new Check(name, new Check(name).result().getOrElse(ConfigUtil.get("checkYou.setting.message.failCheck").getOrElse("")));
        F.Option<Check> checkOps = checkOrigin.store();
        return checkOps.isDefined()
                ? ok(views.html.check.result.render(
                checkOps.get().getName() + ConfigUtil.get("checkYou.setting.message.resultTitle").getOrElse(""), checkOps.get()))
                : Apps.fail(routes.Checks.index(), "error", "診断エラーです");



    }

    /**
     * IDを指定して結果を取得
     *
     * @param id
     * @return
     */
    public static Result resultId(Long id) {
        F.Option<Check> check = new Check(id).unique();
        return check.isDefined()
                ? ok(views.html.check.result.render(check.get().getName() + ConfigUtil.get("checkYou.setting.message.resultTitle").getOrElse(""), check.get()))
                : Apps.fail(routes.Checks.index(), "error", "診断エラーです");
    }

    /**
     * 結果の一覧を表示
     * @param page
     * @return
     */
    public static Result recent(Integer page) {
        F.Option<List<Check>> result = new Check().entitiesList(page);
        Integer maxPage = new Check().entitiesMaxPage(1);

        return result.isDefined()
                ? ok(views.html.check.recent.render(
                ConfigUtil.get("checkYou.setting.message.recentTitle").getOrElse(""),
                ConfigUtil.get("checkYou.setting.message.recentDescription").getOrElse(""),
                result.get(),
                page,
                maxPage))
                : ok(views.html.check.recentEmpty.render(
                ConfigUtil.get("checkYou.setting.message.resultTitle").getOrElse(""),
                ConfigUtil.get("checkYou.setting.message.recentDescriptionNone").getOrElse("")
        ));
    }

    /**
     * @param message
     * @param form
     * @return
     */
    private static Result validationErrorIndexResult(String message, Form<ResultPostRequest> form) {
        flash("error", message);
        return badRequest(views.html.check.index.render(
                ConfigUtil.get("checkYou.setting.message.title").getOrElse(""),
                ConfigUtil.get("checkyou.setting.message.question").getOrElse(""),
                form
        ));
    }

}
