package edu.cpp.cs580.thunderbird.utils;

import java.io.File;

/**
 * Example from edu.cpp.cs580.util
 * The class provides file locations.
 * @author nchantarutai
 *
 */
public class ResourceResolver {
	private static final String BASE_DATA_DIR = "src/main/resources/static/data/";

	
	public static File getClassJson(){
		File file = new File(BASE_DATA_DIR + "/" + "class-cpp.json");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		return file;
	}
	
}
