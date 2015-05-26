package controllers;

import java.util.*;
import models.*;
import play.data.Form;
import play.mvc.*;
import views.html.send.*;

public class Send extends Controller {
	public static Form<Send> sendForm=Form.form(Send.class);



    public static Result index() {
        return ok(index.render(sendForm));
    }

    public static Result create() {
        return ok("感謝カード送信完了");
    }

}
