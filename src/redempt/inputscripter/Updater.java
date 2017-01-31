package redempt.inputscripter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Updater {
	
	public static void updateAvailable(URL url) throws IOException {
		URLConnection connection = url.openConnection();
		InputStream data = connection.getInputStream();
		int i = 0;
		List<Byte> bytes = new ArrayList<>();
		while ((i = data.read()) != -1) {
			bytes.add((byte) i);
		}
		Byte[] byteArray = bytes.toArray(new Byte[bytes.size()]);
		byte[] newBytes = new byte[byteArray.length];
		int pos = 0;
		for (byte b : byteArray) {
			newBytes[pos] = b;
			pos++;
		}
		String string = new String(newBytes);
		System.out.println(string);
	}
	
}
