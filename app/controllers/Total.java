package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;
import views.html.total.*;

public class Total extends Controller {
	public static Form<Total> totalForm=Form.form(Total.class);


    public static Result index() {
        return ok(index.render(totalForm));
    }

    public static Result search(){
    	return ok(index.render(totalForm));

    }

}