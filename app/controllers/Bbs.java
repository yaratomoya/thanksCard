package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;
import views.html.bbs.*;

public class Bbs extends Controller {
	 public static Form<Bbs> bbsForm = Form.form(Bbs.class);

    public static Result index() {
        return ok(index.render(bbsForm));
    }
    public static Result lastMonth() {
        return ok(lastMonth.render(bbsForm));
    }


    public static Result search(){
    	return ok("検索結果");
    }

}
