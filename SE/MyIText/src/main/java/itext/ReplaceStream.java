package itext;

import com.itextpdf.kernel.pdf.*;

import java.io.File;

public class ReplaceStream {
	public static final String DEST = "./.output/replace_stream.pdf";
	public static final String SRC = "./input/pdf/hello.pdf";

	public static void main(String[] args) throws Exception {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new ReplaceStream().manipulatePdf(DEST);
	}

	protected void manipulatePdf(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
		PdfPage page = pdfDoc.getFirstPage();
		PdfDictionary dict = page.getPdfObject();
		PdfObject object = dict.get(PdfName.Contents);
		if (object instanceof PdfStream) {
			PdfStream stream = (PdfStream) object;
			byte[] data = stream.getBytes();
			stream.setData(new String(data).replace("Hello World", "HELLO WORLD").getBytes("UTF-8"));
		}
		pdfDoc.close();
	}
}
