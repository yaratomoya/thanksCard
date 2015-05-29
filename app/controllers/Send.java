package controllers;

import java.util.*;

import models.*;
import play.data.Form;
import play.mvc.*;
import views.html.defaultpages.error;
import views.html.send.*;
import views.html.users.newForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Send extends Controller {
	//public static Form<Send> sendForm=Form.form(Send.class);
	public static Form<ThanksCard> sendForm=Form.form(ThanksCard.class);

    public static Result index() {

    	HashMap<String, String> reSections=new HashMap<>();
        for(Section section : Section.find.all()){
            reSections.put(section.sectionID.toString(), section.sectionName);
        }

        HashMap<String, String> reName=new HashMap<>();
        for(User user : User.find.all()){
        	reName.put(user.userID.toString(), user.userName);
        }

        HashMap<String, String> cate=new HashMap<>();
        	for(HelpCategory category : HelpCategory.find.all()){
        	cate.put(category.categoryID.toString(), category.categoryName);
        }

        return ok(index.render(sendForm,reSections,reName,cate));
    }

    public static Result create() {
    	Form<ThanksCard> form=sendForm.bindFromRequest();

//		if(form.hasErrors()){
//			return badRequest(error.render(form));
//		}

		ThanksCard card=form.get();
		User receive=User.find.where().eq("userName", card.receive.userName).findUnique();
		User send=User.find.where().eq("userName", session("login")).findUnique();
		HelpCategory cate=HelpCategory.find.where().eq("categoryName", card.category.categoryName).findUnique();
		Date sendDay=new Date();

		ThanksCard.create(card.helpText, card.thanksText, receive, cate, card.helpDate,
				send, sendDay);
        return redirect(routes.Send.index());
    }


}
