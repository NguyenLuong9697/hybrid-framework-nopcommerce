package utilities;

import com.github.javafaker.Faker;

public class DataUtil {
	private Faker faker;
	public static DataUtil getData() {
		return new DataUtil();
	}
	
	private DataUtil() {
		faker = new Faker();
	}
	
	public String getFirstName() {
		return faker.name().firstName();
	}
	public String getLastName() {
		return faker.name().lastName();
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
}
