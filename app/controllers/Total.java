package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        List<User> user=User.find.all();
        ArrayList<Integer> user2 = new ArrayList<Integer>();
        user2.add(1);
        user2.add(1);user2.add(1);
        Iterator<Integer> it = user2.iterator();
        return ok(index.render(totalForm,reSections,dateSearch,user,it));
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
        long recID = Integer.parseInt(params.get("sectionfind")[0]);

        List<ThanksCard> dateSearch=ThanksCard.find.where().eq("receive.section.sectionID", recID).findList();
        List<User> user=User.find.where().eq("section.sectionID", recID).findList();

        User user3 = user.get(0);
        ArrayList<Integer> user2 = new ArrayList<Integer>();
        int kazu = 0;
        for(int i=0; i<user.size(); i++){
        	kazu = i;
        	user3 = user.get(kazu);
        	user2.add(ThanksCard.find.where().eq("receive.userID", user3.userID).findRowCount());
        }
        Iterator<Integer> it = user2.iterator();
        return ok(index.render(totalForm,reSections,dateSearch,user,it));
    }

}