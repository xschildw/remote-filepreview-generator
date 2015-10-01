package org.sagebionetworks.remotefilepreviewgenerator.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sagebionetworks.remotefilepreviewgenerator.provider.BackendService;
import org.sagebionetworks.remotefilepreviewgenerator.provider.ImageMagickProviderImpl;

@Singleton
public class ImageMagickDaoImpl implements ImageMagickDao {

	private static final Logger log = LogManager.getLogger(ImageMagickDaoImpl.class);

	@Inject
	public ImageMagickDaoImpl(BackendService prov) {
		this.setImageMagickProvider(prov);
	}
	
	private BackendService provider;
	public void setImageMagickProvider(BackendService p) {
		log.debug("ImageMagickDaoImpl.setImageMagickProvider.");
		this.provider = p;
	}
	
	@Override
	public void convertToPng(String inPath, String outPath) throws IOException {
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
