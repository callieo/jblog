package taichitech.jblog.jse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileStreamTest {
	public static void main(String args[]) {
		FileStreamTest t = new FileStreamTest();
		t.fileCopyTest();

	}

	@SuppressWarnings("resource")
	public void fileCopyTest() {
		try {
			File f1 = new File("d:\\sourceFile");
			File f2 = new File("d:\\targetFile");
			FileInputStream fis = new FileInputStream(f1);
			FileOutputStream fos = new FileOutputStream(f2);
			int data = -1;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
