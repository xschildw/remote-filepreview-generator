package org.sagebionetworks.remotefilepreviewgenerator.aws;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AwsModule extends AbstractModule {

	private final String AWS_PROFILE_DEV_SECTION = "remotefilepreviewgenerator-dev";
	
	@Override
	protected void configure() {
	}

	@Provides @Singleton
	public AmazonS3Client getS3Client() {
		AWSCredentialsProviderChain providerChain = new AWSCredentialsProviderChain(
			new InstanceProfileCredentialsProvider(),
			new ProfileCredentialsProvider(AWS_PROFILE_DEV_SECTION));
		return new AmazonS3Client(providerChain);
	}
}
