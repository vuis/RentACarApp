package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import domain.FileUpload;

@Controller
public class PictureUploadController {
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String DisplayForm(Model model, @RequestParam Integer carId) {
		model.addAttribute("carId", carId);
		return "uploadFile";
	}

	@RequestMapping(value = "/savefiles", method = RequestMethod.POST)
	public String save(@ModelAttribute("uploadForm") FileUpload uploadForm, @RequestParam Integer carId, Model map)
			throws IllegalStateException, IOException {
		File saveDirectory = new File("E:/cars/" + carId);
		if (!saveDirectory.exists()) {
			saveDirectory.mkdir();
		}

		List<MultipartFile> uploadedFiles = uploadForm.getUploadedFiles();

		List<String> fileNames = new ArrayList<String>();

		if (null != uploadedFiles && uploadedFiles.size() > 0) {
			for (MultipartFile multipartFile : uploadedFiles) {
				String fileName = multipartFile.getOriginalFilename();
				fileNames.add(fileName);
				try {
					multipartFile.transferTo(new File(saveDirectory + "/" + fileName));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		map.addAttribute("files", fileNames);
		return "redirect:picture.html?carId="+carId;
	}

}
