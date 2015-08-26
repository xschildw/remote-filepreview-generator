package org.sagebionetworks.remotefilepreviewgenerator.dao;

import java.io.IOException;

public interface OpenOfficeDao {

	/**
	 * Converts file located at inPath to .pdf and place output
	 * at outPath.
	 *
	 * @param inPath
	 * @param outPath
	 */
	void convertToPdf(String inPath, String outPath) throws IOException;
	
}
