package kr.cr.ddit.service;
import net.datafaker.Faker;
public class MessegeService{
	public String getMessege(){
		
		Faker faker = new Faker();
		
		return faker.name().fullName();
	}
}