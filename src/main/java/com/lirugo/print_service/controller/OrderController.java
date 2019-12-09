package com.lirugo.print_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@MultipartConfig
@RequestMapping("/api/order")
public class OrderController {

    @Value("${storage.order.file}")
    private String storageOrderFile;

    @PostMapping(value = "/file", headers = ("content-type=multipart/*"))
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {

        File uploadDir = new File(storageOrderFile);
        if(!uploadDir.exists())
            uploadDir.mkdir();

        String uuidFile = UUID.randomUUID().toString();
        String uniqueFileName = uuidFile + "." + file.getOriginalFilename();

        try {
            file.transferTo(new File(storageOrderFile + "/" + uniqueFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uniqueFileName;
    }
}
