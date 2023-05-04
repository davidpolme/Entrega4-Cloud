package org.example.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.example.domain.dto.TaskDto;
import org.example.domain.service.impl.CronService;
import org.example.domain.service.impl.EmailService;
import org.example.domain.service.impl.TaskServiceImpl;
import org.example.domain.service.impl.CompressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Log
@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
@CrossOrigin(value = "*")
public class Controller {
    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private CompressService compressService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CronService cronService;

    @PostMapping("/compress")
    public ResponseEntity<byte[]> compressFile(@RequestParam("file") MultipartFile file,
                                               @RequestParam("format") String format,
                                               @RequestParam("email") String email) throws IOException {
        byte[] compressedFile = compressService.compressFile(file);
        String filePath = Paths.get("").toAbsolutePath().toString() + "/" + file.getOriginalFilename() + "."+format;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(compressedFile);
        }
        TaskDto taskDto = TaskDto.builder().time(LocalDateTime.now())
                .fileName(file.getOriginalFilename()).email(email)
                .status("uploaded").format(format).build();
        taskService.save(taskDto);
        emailService.sendEmail("oscar.bosigas@uptc.edu.co", "Archivo " + file.getOriginalFilename() + " subido correctamente");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/octet-stream")
                .header("Content-Disposition", "attachment; filename=" + file.getOriginalFilename() + "." + format)
                .body(compressedFile);
    }

}
