package controllers;

import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;

public class Apps extends Controller {

    public static Result fail(Call call, String flashKey, String flashMessage) {
        ctx().flash().put(flashKey,flashMessage);
        return redirect(call);
    }
}
