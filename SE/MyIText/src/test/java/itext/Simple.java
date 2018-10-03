package itext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import org.junit.Test;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

public class Simple {

	private Document getDocument(String dest) throws Exception {
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		return document;
	}

	@Test
	public void testHelloWorld() throws Exception {
		String dest = ".output/HelloWorld.pdf";
		Document document = getDocument(dest);
		document.add(new Paragraph("Hello World!"));
		document.close();
	}

	@Test
	public void testRickAstley() throws Exception {
		String dest = ".output/RickAstley.pdf";
		Document document = getDocument(dest);
		// Create a PdfFont
		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		// Add a Paragraph
		document.add(new Paragraph("iText is:").setFont(font));
		// Create a List
		List list = new List().setSymbolIndent(12).setListSymbol("\u2022").setFont(font);
		// Add ListItem objects
		list.add(new ListItem("Never gonna give you up")).add(new ListItem("Never gonna let you down"))
				.add(new ListItem("Never gonna run around and desert you"))
				.add(new ListItem("Never gonna make you cry")).add(new ListItem("Never gonna say goodbye"))
				.add(new ListItem("Never gonna tell a lie and hurt you"));
		// Add the list
		document.add(list);
		document.close();
	}

	@Test
	public void testImages() throws Exception {
		String dest = ".output/Images.pdf";
		Document document = getDocument(dest);
		Image fox = new Image(ImageDataFactory.create("input/images/fox.jpg"));
		Image dog = new Image(ImageDataFactory.create("input/images/dog.jpg"));
		Paragraph p = new Paragraph("The quick brown ").add(fox).add(" jumps over the lazy ").add(dog);
		document.add(p);
		document.close();
	}

	@Test
	public void testCSV() throws Exception {
		String DATA = "input/csv/united_states.csv";
		String dest = ".output/SimpleCSV.pdf";

		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf, PageSize.A4.rotate());
		document.setMargins(20, 20, 20, 20);
		PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
		PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		Table table = new Table(new float[] { 4, 1, 3, 4, 3, 3, 3, 3, 1 });
		table.setWidth(UnitValue.createPercentValue(100));
		BufferedReader br = new BufferedReader(new FileReader(DATA));
		String line = br.readLine();
		process(table, line, bold, true);
		while ((line = br.readLine()) != null) {
			process(table, line, font, false);
		}
		br.close();
		document.add(table);
		document.close();
	}

	private void process(Table table, String line, PdfFont font, boolean isHeader) {
		StringTokenizer tokenizer = new StringTokenizer(line, ";");
		while (tokenizer.hasMoreTokens()) {
			if (isHeader) {
				table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
			} else {
				table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
			}
		}
	}
}
