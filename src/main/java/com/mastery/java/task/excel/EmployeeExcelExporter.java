package com.mastery.java.task.excel;


import com.mastery.java.task.model.Employee;
import com.mastery.java.task.model.Gender;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Component
@Data
@Slf4j

public class EmployeeExcelExporter {


    private final XSSFWorkbook workbook;

    private XSSFSheet sheet;

    private List<Employee> employees;


    public EmployeeExcelExporter(List<Employee> employees) {
        this.workbook = new XSSFWorkbook();
        this.employees = employees;
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("sheet");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(15);
        style.setFont(font);

        createCell(row, 0, "Employee Id", style);
        createCell(row, 1, "Firstname", style);
        createCell(row, 2, "Lastname", style);
        createCell(row, 3, "Department Id", style);
        createCell(row, 4, "Job Title", style);
        createCell(row, 5, "Date of Birth", style);
        createCell(row, 6, "Gender", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Cell cell = row.createCell(columnCount);
        if (value instanceof LocalDate) {
            cell.setCellValue(((LocalDate) value).format(formatter));
        } else if (value instanceof Long) {
            cell.setCellValue(String.valueOf(value));
        } else if (value instanceof Gender) {
            if (value.equals(Gender.MALE)){
                cell.setCellValue("Male");
            }else {
                cell.setCellValue("Female");
            }
        } else {
            cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();

        XSSFFont font = workbook.createFont();
        font.setFontHeight(13);
        style.setFont(font);

        for (Employee employee : employees) {

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;

            createCell(row, columnCount++, employee.getEmployeeId(), style);
            createCell(row, columnCount++, employee.getFirstName(), style);
            createCell(row, columnCount++, employee.getLastName(), style);
            createCell(row, columnCount++, employee.getDepartmentId(), style);
            createCell(row, columnCount++, employee.getJobTitle(), style);
            createCell(row, columnCount++, employee.getDateOfBirth(), style);
            createCell(row, columnCount++, employee.getGender(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
    public  void setResponse(HttpServletResponse response) {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String headerValue = "attachment; filename=employees_"+currentDateTime+".xlsx";

        response.setHeader(header, headerValue);
    }

}
