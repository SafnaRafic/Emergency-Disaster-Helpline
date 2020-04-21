package org.SafnaRafic.emergencydisasterhelpline.services;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.SafnaRafic.emergencydisasterhelpline.models.Inneed;
import org.SafnaRafic.emergencydisasterhelpline.models.data.InneedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import static com.itextpdf.text.PageSize.A4;

@Service
@Transactional
public class SeekersServiceImpl implements SeekersService {
    @Autowired
    private InneedRepository inneedRepository;

    @Override
    public List<Inneed> getAllSeekers() {
        return (List<Inneed>) inneedRepository.findAll();
    }

    @Override
    public boolean createPdf(List<Inneed> inneeds, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        Document document=new Document(PageSize.A4,15,15,45,30);
        try{
            String filePath= context.getRealPath("/resources/reports");
            File file=new File(filePath);
            boolean exists=new File(filePath).exists();
            if(!exists){
                new File(filePath).mkdirs();
            }

            PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file +"/"+"seekers"+".pdf"));
            document.open();

            Font mainFont = FontFactory.getFont("Arial",10, BaseColor.BLACK);

            Paragraph paragraph=new Paragraph("All Seekers",mainFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable table=new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);

            Font tableHeader = FontFactory.getFont("Arial",10,BaseColor.BLACK);
            Font tableBody = FontFactory.getFont("Arial",9,BaseColor.BLACK);

            float[] columnwidths= {2f , 2f, 2f, 2f,2f};
            table.setWidths(columnwidths);

            PdfPCell name = new PdfPCell(new Paragraph("Name",tableHeader));
            name.setBorderColor(BaseColor.BLACK);
            name.setPaddingLeft(10);
            name.setHorizontalAlignment(Element.ALIGN_CENTER);
            name.setVerticalAlignment(Element.ALIGN_CENTER);
            name.setBackgroundColor(BaseColor.GRAY);
            name.setExtraParagraphSpace(5f);
            table.addCell(name);

            PdfPCell email = new PdfPCell(new Paragraph("Email -ID",tableHeader));
            email.setBorderColor(BaseColor.BLACK);
            email.setPaddingLeft(10);
            email.setHorizontalAlignment(Element.ALIGN_CENTER);
            email.setVerticalAlignment(Element.ALIGN_CENTER);
            email.setBackgroundColor(BaseColor.GRAY);
            email.setExtraParagraphSpace(5f);
            table.addCell(email);

            PdfPCell zipcode = new PdfPCell(new Paragraph("Zip code",tableHeader));
            zipcode.setBorderColor(BaseColor.BLACK);
            zipcode.setPaddingLeft(10);
            zipcode.setHorizontalAlignment(Element.ALIGN_CENTER);
            zipcode.setVerticalAlignment(Element.ALIGN_CENTER);
            zipcode.setBackgroundColor(BaseColor.GRAY);
            zipcode.setExtraParagraphSpace(5f);
            table.addCell(zipcode);

            PdfPCell needs = new PdfPCell(new Paragraph("Needs",tableHeader));
            needs.setBorderColor(BaseColor.BLACK);
            needs.setPaddingLeft(10);
            needs.setHorizontalAlignment(Element.ALIGN_CENTER);
            needs.setVerticalAlignment(Element.ALIGN_CENTER);
            needs.setBackgroundColor(BaseColor.GRAY);
            needs.setExtraParagraphSpace(5f);
            table.addCell(needs);

            PdfPCell quantity = new PdfPCell(new Paragraph("Quantity",tableHeader));
            quantity.setBorderColor(BaseColor.BLACK);
            quantity.setPaddingLeft(10);
            quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantity.setVerticalAlignment(Element.ALIGN_CENTER);
            quantity.setBackgroundColor(BaseColor.GRAY);
            quantity.setExtraParagraphSpace(5f);
            table.addCell(quantity);

            for(Inneed inneed: inneeds){
                PdfPCell nameValue = new PdfPCell(new Paragraph(inneed.getName(),tableBody));
                nameValue.setBorderColor(BaseColor.BLACK);
                nameValue.setPaddingLeft(10);
                nameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                nameValue.setVerticalAlignment(Element.ALIGN_CENTER);
                nameValue.setBackgroundColor(BaseColor.WHITE);
                nameValue.setExtraParagraphSpace(5f);
                table.addCell(nameValue);

                PdfPCell emailValue = new PdfPCell(new Paragraph(inneed.getEmailId(),tableBody));
                emailValue.setBorderColor(BaseColor.BLACK);
                emailValue.setPaddingLeft(10);
                emailValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                emailValue.setVerticalAlignment(Element.ALIGN_CENTER);
                emailValue.setBackgroundColor(BaseColor.WHITE);
                emailValue.setExtraParagraphSpace(5f);
                table.addCell(emailValue);

                PdfPCell zipcodeValue = new PdfPCell(new Paragraph(inneed.getZipcode(),tableBody));
                zipcodeValue.setBorderColor(BaseColor.BLACK);
                zipcodeValue.setPaddingLeft(10);
                zipcodeValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                zipcodeValue.setVerticalAlignment(Element.ALIGN_CENTER);
                zipcodeValue.setBackgroundColor(BaseColor.WHITE);
                zipcodeValue.setExtraParagraphSpace(5f);
                table.addCell(zipcodeValue);

                PdfPCell needsValue = new PdfPCell(new Paragraph(String.valueOf(inneed.getNeeds()),tableBody));
                needsValue.setBorderColor(BaseColor.BLACK);
                needsValue.setPaddingLeft(10);
                needsValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                needsValue.setVerticalAlignment(Element.ALIGN_CENTER);
                needsValue.setBackgroundColor(BaseColor.WHITE);
                needsValue.setExtraParagraphSpace(5f);
                table.addCell(needsValue);

                PdfPCell quantityValue = new PdfPCell(new Paragraph(String.valueOf(inneed.getQuantity()),tableBody));
                quantityValue.setBorderColor(BaseColor.BLACK);
                quantityValue.setPaddingLeft(10);
                quantityValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantityValue.setVerticalAlignment(Element.ALIGN_CENTER);
                quantityValue.setBackgroundColor(BaseColor.WHITE);
                quantityValue.setExtraParagraphSpace(5f);
                table.addCell(quantityValue);
            }
            document.add(table);
            document.close();
            writer.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
