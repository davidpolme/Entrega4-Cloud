package org.example.domain.service.impl;

import com.google.api.client.util.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CompressService {

    public byte[] compressFile(MultipartFile file) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(bos);

        ZipEntry zipEntry = new ZipEntry(file.getOriginalFilename());
        zipOut.putNextEntry(zipEntry);
        zipOut.write(file.getBytes());
        zipOut.closeEntry();

        zipOut.finish();
        zipOut.close();

        return bos.toByteArray();
    }
}