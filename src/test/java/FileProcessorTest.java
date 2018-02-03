
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.pizza.shop.model.Order;

public class FileProcessorTest {

	// write test case about crating 3 or 4 factory desing pattern object method
	// validate at least 4 extension of files
	String testFileName = "sample_data_ordered.txt";
	String destinationPath = "output/output.txt";
	File sourcefile;
	File destinationfile;
	BufferedReader br;
	String ext = "txt";
    /***
     * construction of object required for test cases
     * 
     * @throws FileNotFoundException
     */
	public FileProcessorTest() throws FileNotFoundException {
		sourcefile = new File(testFileName);
		destinationfile = new File(destinationPath);
		br = new BufferedReader(new FileReader(sourcefile));
	}

	@Test
	public void readInputFileTest() {
		assertEquals(true, br != null);
	}

	@Test
	public void createOuputFileTest() throws IOException{
		File file = new File(destinationPath);
		if(!file.isDirectory() && !file.exists())
			new File(file.getParent()).mkdirs();
		file.delete();
		assertEquals(true, file.createNewFile());
	}
	
	@Test
	public void constructOrderListTest() {

		List<Order> expectedList = Arrays.asList(
				new Order("BREAD", 1477405487),
				new Order("BREAD", 1477232687), 
				new Order("BREAD", 1474640687),
				new Order("MEAT", 1506176687),
				new Order("MEATMEAT", 1474295087),
				new Order("P1ZZA",1477491887),
				new Order("PIZZA", 1477578287),
				new Order("PIZZA", 1477319087),
				new Order("VEGVEG", 1474295087));
	    List<Order> resultlist = Arrays.asList(
	    		new Order("MEAT",1506176687),
	    		new Order("PIZZA",1477578287),
	    		new Order("P1ZZA",1477491887),
	    		new Order("BREAD",1477405487),
	    		new Order("PIZZA",1477319087),
	    		new Order("BREAD",1477232687),
	    		new Order("BREAD",1474640687),
	    		new Order("MEATMEAT",1474295087),
	    		new Order("VEGVEG",1474295087));
	    Collections.sort(resultlist);
	    assertEquals(expectedList, resultlist);
	}
	@Test
	public void checkIfSoruceFileExists() {

		assertEquals(true, sourcefile.exists());

	}

	@Test
	public void checkOutputFileWritePermission() throws IOException {

		assertEquals(true,
				Files.isWritable(destinationfile.getParentFile().toPath()));

	}

	@Test
	public void checkSourceFileExtension() {

		String extension = getFileExtension(testFileName);
		if (extension.equalsIgnoreCase(ext)) {
			assertEquals(true, true);
		} else {
			assertEquals(true, false);
		}

	}

	@Test
	public void checkDesinationFileExtension() {
		String extension = getFileExtension(destinationPath);
		if (extension.equalsIgnoreCase(ext)) {
			assertEquals(true, true);
		} else {
			assertEquals(true, false);
		}

	}

	@Test
	public void validateDataHeaderTest() throws IOException {
		String headerText = br.readLine();
		String[] inputOrderText = headerText.split("\\s+");
		if (inputOrderText.length == 2) {
			if (inputOrderText[0].trim().equalsIgnoreCase("Order")
					&& inputOrderText[1].trim().equalsIgnoreCase("time"))
				assertEquals(true, true);
		} else {
			assertEquals(true, false);
		}
	}

	@Test
	public void testGetExtension() {
	    assertEquals("", getFileExtension("D"));
	    assertEquals("ext", getFileExtension("D.ext"));
	    assertEquals("ext", getFileExtension("C:/ouput/input.ext"));
	    assertEquals("ext", getFileExtension("A/B/C.ext"));
	    assertEquals("doc", getFileExtension("D.doc"));
	    assertEquals("doc", getFileExtension("A/M/C.doc"));
	    assertEquals("docx", getFileExtension("E.docx"));
	    assertEquals("docx", getFileExtension("A/B/C.docx"));
	    assertEquals("pdf", getFileExtension("C.pdf"));
	    assertEquals("pdf", getFileExtension("A/B/C.pdf"));
	    assertEquals("", getFileExtension("E/B/C.ext/"));
	    assertEquals("", getFileExtension("D/B/C.ext/.."));

	}
	private String getFileExtension(String fileName) {
		int dot = fileName.lastIndexOf(".");
		return fileName.substring(dot + 1);
	}
}
