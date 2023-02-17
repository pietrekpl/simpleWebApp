package com.mastery.java.task.exports.pdf;


import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mastery.java.task.model.Employee;
import com.mastery.java.task.model.Gender;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
@Data
@Slf4j
public class EmployeePdfExporter {

    private List<Employee> employees;

    public EmployeePdfExporter(List<Employee> employees) {
        this.employees = employees;
    }

    private void writeTableHeader(PdfPTable pdfTable) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("Employee Id", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Firstname", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Lastname", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Department Id", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Job title", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Date of Birth", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Gender", font));
        pdfTable.addCell(cell);
    }

    private void writeTableData(PdfPTable pdfTable) {
        for (Employee employee : employees) {
            pdfTable.addCell(String.valueOf(employee.getEmployeeId()));
            pdfTable.addCell(employee.getFirstName());
            pdfTable.addCell(employee.getLastName());
            pdfTable.addCell(String.valueOf(employee.getDepartmentId()));
            pdfTable.addCell(employee.getJobTitle());
            pdfTable.addCell(employee.getDateOfBirth().toString());
            if (employee.getGender().equals(Gender.MALE)){
                pdfTable.addCell("MALE");
            }else {
                pdfTable.addCell("FEMALE");
            }
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4.rotate());
        document.getPageNumber();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph paragraph = new Paragraph("List of Employees", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable pdfTable = new PdfPTable(7);
        pdfTable.setWidthPercentage(100f);
        pdfTable.setWidths(new float[]{2.0f, 3.5f, 3.5f, 2.5f, 3.5f, 3.5f, 3.5f});
        pdfTable.setSpacingBefore(10);

        writeTableHeader(pdfTable);
        writeTableData(pdfTable);

        document.add(pdfTable);

        document.close();
    }
}
