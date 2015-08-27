package org.sagebionetworks.remotefilepreviewgenerator.utils;

import org.json.JSONObject;

public class MessagePayload {
	
	private String sourceBucketName;
	private String sourceKey;
	private String destinationBucketName;
	private String destinationKey;
	private final String KEY_SOURCE_BUCKET_NAME = "sourceBucketName";
	private final String KEY_SOURCE_KEY = "sourceKey";
	private final String KEY_DESTINATION_BUCKET_NAME = "destinationBucketName";
	private final String KEY_DESTINATION_KEY = "destinationKey";
	
	
	public MessagePayload(JSONObject o) {
		this.sourceBucketName = o.getString(KEY_SOURCE_BUCKET_NAME);
		this.sourceKey = o.getString(KEY_SOURCE_KEY);
		this.destinationBucketName = o.getString(KEY_DESTINATION_BUCKET_NAME);
		this.destinationKey = o.getString(KEY_DESTINATION_KEY);
	}

	public String getSourceBucketName() {
		return sourceBucketName;
	}

	public void setSourceBucketName(String sourceBucketName) {
		this.sourceBucketName = sourceBucketName;
	}

	public String getSourceKey() {
		return sourceKey;
	}

	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}

	public String getDestinationBucketName() {
		return destinationBucketName;
	}

	public void setDestinationBucketName(String destinationBucketName) {
		this.destinationBucketName = destinationBucketName;
	}

	public String getDestinationKey() {
		return destinationKey;
	}

	public void setDestinationKey(String destinationKey) {
		this.destinationKey = destinationKey;
	}
}
