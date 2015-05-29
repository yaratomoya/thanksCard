package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Section;
import models.ThanksCard;
import models.GoodCount;
import models.User;
import play.*;
import play.mvc.*;
import play.data.Form;
import views.html.total.*;

public class Total extends Controller {
	public static Form<ThanksCard> totalForm=Form.form(ThanksCard.class);


    public static Result index() {
    	HashMap<String, String> reSections=new HashMap<>();
        for(Section section : Section.find.all()){
            reSections.put(section.sectionID.toString(), section.sectionName);
        }
        List<ThanksCard> dateSearch=ThanksCard.find.all();
        return ok(index.render(totalForm,reSections,dateSearch,null));
    }

    public static Result search(){
    	HashMap<String, String> reSections=new HashMap<>();
        for(Section section : Section.find.all()){
            reSections.put(section.sectionID.toString(), section.sectionName);
        }
/*
        Form<ThanksCard> form=totalForm.bindFromRequest();
        ThanksCard to = form.get();*/
        Map<String, String[]> params = request().body().asFormUrlEncoded();
        ThanksCard card = new ThanksCard();
        long recID = Integer.parseInt(params.get("sectionfind")[0]);
        //card.receive.section.sectionID = recID;
        List<ThanksCard> dateSearch=ThanksCard.find.where().eq("receive.section.sectionID", recID).findList();
        List<User> user=User.find.where().eq("section.sectionID", recID).findList();

        ArrayList user2 = new ArrayList();
        for(int i=0; i<user2.length; i++){
        	user2[i]=ThanksCard.find.where().eq("receive.userID", user.get(i)).findRowCount();
        }
        return ok(index.render(totalForm,reSections,dateSearch,user));
    }

}