package org.sagebionetworks.remotefilepreviewgenerator.dao;

import java.io.File;
import java.io.IOException;
import org.sagebionetworks.remotefilepreviewgenerator.provider.BackendService;

public class OpenOfficeDaoImpl implements OpenOfficeDao {
	
	public OpenOfficeDaoImpl(BackendService prov) {
		this.provider = prov;
	}
	
	private BackendService provider;
	public void setOpenOfficeProvider(BackendService p) {
		this.provider = p;
	}
	
	@Override
	public void convertToPdf(String inPath, String outPath) throws IOException {
		if ((inPath == null) || (outPath == null)) {
			throw new IllegalArgumentException("Arguments inPath and outPath cannot be null.");
		}
		File inputFile = new File(inPath);
		boolean inputExists = inputFile.exists() && inputFile.isFile();
		File outputFile = new File(outPath);
		boolean outputExists = outputFile.exists();
		if ((! inputExists) || (outputExists )) {
			throw new IllegalArgumentException("Input file must exist and output file cannot exist.");
		}
			// For now, just copy the file
		DaoUtils.copyFile(inputFile, outputFile);
	}
	
}
