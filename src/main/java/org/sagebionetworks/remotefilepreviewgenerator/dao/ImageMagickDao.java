package org.sagebionetworks.remotefilepreviewgenerator.dao;

import java.io.IOException;

public interface ImageMagickDao {

	void convertToPng(String inPath, String outPath) throws IOException;
	
}
