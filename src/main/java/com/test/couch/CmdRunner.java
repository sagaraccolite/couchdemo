package com.test.couch;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... strings) throws Exception {
	try {
		 User u1 = createUser("u0001", "Perry", "Manson", "perry.mason@acme.com", "Who can we get on the case?");
	     userRepository.save(u1);
	
	     User u2 = createUser("u0002", "Major", "Tom", "major.tom@acme.com", "Send me up a drink");
	     userRepository.save(u2);
	
	
	     User u3 = createUser("u0003", "Jerry", "Wasaracecardriver", "jerry.wasaracecardriver@acme.com", "el sob number one");
	     userRepository.save(u3);
	
	     Optional<User> user = userRepository.findById("u0001");
	     System.out.println("User found = "+user.get().getFirstName());
	
	     List<User> result = userRepository.findByEmailLike("%@acme.com");
	
	     System.out.println( "Total of @acme.com users = "+result.size()  );
	
	} catch (Exception e) {
		e.printStackTrace();
	}
       
    }

    public static User createUser(String id, String firstName, String lastName,
                                  String email, String tagLine) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setTagLine(tagLine);
        return user;
    }

}