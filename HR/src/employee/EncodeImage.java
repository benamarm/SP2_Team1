package employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class EncodeImage {
	public static byte[] encode(String filePath) {
		File image = new File(filePath);
		byte[] bFile = new byte[(int) image.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(image);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFile;
	}
}
