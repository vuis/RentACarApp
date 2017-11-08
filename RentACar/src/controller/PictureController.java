package controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PictureController {

	@RequestMapping(value = "/picture.html", method = RequestMethod.GET)
	public String showImages(@RequestParam Integer carId, Model model) throws IOException {

		File dir = new File("E:/Cars/" + carId);
		List<String> listOfFiles = new ArrayList<String>();
		if (dir.exists()) {

			File[] directoryListing = dir.listFiles();
			for (File file : directoryListing) {
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int b;
				byte[] buffer = new byte[1024];
				while ((b = fis.read(buffer)) != -1) {
					bos.write(buffer, 0, b);
				}
				byte[] fileBytes = bos.toByteArray();
				fis.close();
				bos.close();

				byte[] encoded = Base64.encode(fileBytes);
				String encodedString = new String(encoded);
				listOfFiles.add(encodedString);
			}
		}

		model.addAttribute("listOfFiles", listOfFiles);
		return "picture";
	}

}
