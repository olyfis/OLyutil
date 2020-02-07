package com.olympus.olyutil.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfTest {
/*******************************************************************************************************************************************************/
	
	private void addTableHeader(PdfPTable table) {
	    Stream.of("column header 1", "column header 2", "column header 3")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
/*******************************************************************************************************************************************************/
	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		 

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("c:/temp/iTextHelloWorld.pdf"));
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("Hello World --", font);
		 
		document.add(chunk);
		document.close();
		
	}
/*******************************************************************************************************************************************************/

}
