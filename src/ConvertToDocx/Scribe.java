package ConvertToDocx;

import java.io.File;
import net.sourceforge.tess4j.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Scribe {
	public static void main(String[] args) throws Exception {
		System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name of document and file type (i.e. myfile.png): ");
		String fileName=sc.next();
		File imageFile = new File(fileName);

		ITesseract instance = new Tesseract();  // JNA Interface Mapping

		instance.setDatapath( "C:\\Users\\Sri\\workspace\\TesseractExample" );
		instance.setLanguage("eng");  //the language the reader is recognizing is english
		
		try {
			String result = instance.doOCR(imageFile);
			System.out.println("OCR complete. Writing to document now... ");
			System.out.println();
			System.out.println();
			System.out.println(result);
			//Blank Document
			XWPFDocument document= new XWPFDocument(); 
			//Write the Document in file system
			FileOutputStream out = new FileOutputStream(new File("Converted Document.docx"));
			//the following is used to print to a doc
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run=paragraph.createRun();
			
			run.setText(result);
			document.write(out);
			out.close();
			
			System.out.println("Document written successully");
			document.close();
			sc.close();
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
	}
}

