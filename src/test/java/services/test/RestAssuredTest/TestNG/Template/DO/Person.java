package services.test.RestAssuredTest.TestNG.Template.DO;

import java.util.Arrays;
import java.util.List;

public class Person implements Payload{
	
		String firstName,lastName,phone,email;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return "Person [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email
					+ "]";
		}
		
		

}
