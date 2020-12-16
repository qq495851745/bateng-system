package com.bateng.web.file;

import com.bateng.utils.ToJsonString;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import static com.bateng.utils.ToJsonString.*;

import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping
    public String upload(MultipartFile file){
        try {
            file.transferTo(new File("test.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toDisableCircularReferenceDetectString(new Object());
    }


    @GetMapping("/{id}")
    public void download(@PathVariable(required = true) String id, HttpServletResponse response){
        OutputStream outputStream =null;
        InputStream inputStream = null;
        inputStream = new ByteArrayInputStream(("abc"+id).getBytes());
        try {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
