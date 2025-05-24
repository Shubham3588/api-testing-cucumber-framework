package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.getMaps;
import pojo.location;

public class TestDataBuild {
	public getMaps addPayload(String name,String language,String address) {
		getMaps gm = new getMaps();
		gm.setAccuracy(62);
		gm.setAddreess(address);
		gm.setLanguage(language);
		gm.setPhoneNumber("7070707070");
		gm.setName(name);
		gm.setWebsite("www.facebook.com");
		List<String> myList = new ArrayList<String>();
		myList.add("Shoe Park");
		myList.add("Shoe");
		gm.setTypes(myList);
		location loc = new location();
		loc.setLat(-24.589);
		loc.setLng(58.3654);
		gm.setLocation(loc);
		return gm;

	}

}
