package com.mastery.java.task.exports.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Data
public class ExportResponseUtils {
    private  HttpServletResponse httpServletResponse;
    private String contentType;
    private String fileExtension;

    public  void setResponse(HttpServletResponse response, String contentType, String fileExtension) {
        response.setContentType(contentType);

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String headerValue = "attachment; filename=employees_"+currentDateTime+fileExtension;

        response.setHeader(header, headerValue);
    }
}
