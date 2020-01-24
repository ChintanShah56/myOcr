package com.app.myOcr.MyOcr;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sourceforge.tess4j.Tesseract;

@Controller
public class MyOcrApplicationController {

	@RequestMapping(value = "/readOcr", method = RequestMethod.POST)
	public ResponseEntity<Object> readOcr(@RequestBody Image file) throws Exception {

		byte[] decodedImg = Base64.getDecoder().decode(file.getImage().getBytes(StandardCharsets.UTF_8));
		Path destinationFile = Paths.get("src/main/resources/Images", "card7.jpg");
		Files.write(destinationFile, decodedImg);

		Tesseract tesseract = new Tesseract();
		String output = tesseract.doOCR(new File("src/main/resources/Images/card7.jpg"));

		return new ResponseEntity<Object>(output, HttpStatus.OK);

	}

}
