package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import models.Computer;

import java.io.ByteArrayOutputStream;

public class PdfGenerator {

    public static byte[] generatePdfFromData(Computer objectData) throws DocumentException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();
            document.add(new Paragraph(objectData.name));
            document.add(new Paragraph(objectData.company.name));
            //document.add(new Paragraph(objectData.introduced));
            document.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new DocumentException("Error generating PDF", e);
        }
    }
}