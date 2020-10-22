package com.test.couch;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    
	@Override
	public String getConnectionString() {
		return "127.0.0.1";
	}

	@Override
	public String getUserName() {
		 return "Administrator";
	}

	@Override
	public String getPassword() {
		 return "admin@123";
	}

	@Override
	public String getBucketName() {
		return "dummyBucket";
	}
}