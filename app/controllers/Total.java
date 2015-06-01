package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.*;

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
        ArrayList<String> user2_st = new ArrayList<String>();
        User user3 = user.get(0);
        User t_user;
        int kazu = 0;

        int[][] arr = new int[user.size()][2];

        for(int i=0; i<user.size(); i++){
        	kazu = i;
        	user3 = user.get(kazu);
        	user2.add(ThanksCard.find.where().eq("receive.userID", user3.userID).findRowCount());
        	arr[i][0] = user2.get(i);
        	arr[i][1] = new Integer(user3.userID.toString());
        }
        MyComparator comparator = new MyComparator(0);
        Arrays.sort(arr, comparator);
        user2.clear();

        for(int i=0; i<user.size(); i++){
        	String uid_st = String.valueOf(arr[i][1]);
        	long uid = Integer.parseInt(uid_st);
        	t_user = User.find.byId(uid);
        	user2_st.add(t_user.userName);
        	user2.add(i+1);
        	user2.add(arr[i][0]);
        }

        Iterator<Integer> it = user2.iterator();
        Iterator<String> it2 = user2_st.iterator();
        return ok(index.render(totalForm,reSections,dateSearch,user,it,it2));
    }

    public static Result search(){
    	HashMap<String, String> reSections=new HashMap<>();
        for(Section section : Section.find.all()){
            reSections.put(section.sectionID.toString(), section.sectionName);
        }

        Map<String, String[]> params = request().body().asFormUrlEncoded();
        long recID = Integer.parseInt(params.get("sectionfind")[0]);

        List<ThanksCard> dateSearch=ThanksCard.find.where().eq("receive.section.sectionID", recID).findList();
        List<User> user=User.find.where().eq("section.sectionID", recID).findList();

        ArrayList<Integer> user2 = new ArrayList<Integer>();
        ArrayList<String> user2_st = new ArrayList<String>();
        User user3 = user.get(0);
        User t_user;
        int kazu = 0;

        int[][] arr = new int[user.size()][2];

        for(int i=0; i<user.size(); i++){
        	kazu = i;
        	user3 = user.get(kazu);
        	user2.add(ThanksCard.find.where().eq("receive.userID", user3.userID).findRowCount());
        	arr[i][0] = user2.get(i);
        	arr[i][1] = new Integer(user3.userID.toString());
        }
        MyComparator comparator = new MyComparator(0);
        Arrays.sort(arr, comparator);
        user2.clear();

        for(int i=0; i<user.size(); i++){
        	String uid_st = String.valueOf(arr[i][1]);
        	long uid = Integer.parseInt(uid_st);
        	t_user = User.find.byId(uid);
        	user2_st.add(t_user.userName);
        	user2.add(i+1);
        	user2.add(arr[i][0]);
        }

        Iterator<Integer> it = user2.iterator();
        Iterator<String> it2 = user2_st.iterator();
        return ok(index.render(totalForm,reSections,dateSearch,user,it,it2));
    }

    static class MyComparator implements Comparator<int[]> {
        public int sortIndex = 0;
        public MyComparator(int sortIndex) {
            this.sortIndex = sortIndex;
        }

        //比較
        @Override
        public int compare(int[] o1, int[] o2) {
            return o2[sortIndex] - o1[sortIndex];
        }
    }
}
