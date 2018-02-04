import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.pizza.shop.factory.DataParserFactory;
import com.pizza.shop.model.Order;
import com.pizza.shop.model.SortByOrderName;
import com.pizza.shop.model.SortByTime;
import com.pizza.shop.service.impl.DocFileParser;
import com.pizza.shop.service.impl.PdfFileParser;
import com.pizza.shop.service.impl.TextFileParser;
import com.pizza.shop.validate.Utility;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileProcessorTest {

	String sourceFileName = "sample_data_ordered.txt";
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
		sourcefile = new File(sourceFileName);
		destinationfile = new File(destinationPath);
		br = new BufferedReader(new FileReader(sourcefile));
	}

	@Test
	public void testReadInputFile() {
		assertEquals(true, br != null);
	}

	@Test
	public void testCreateOuputFile() throws IOException {
		File file = new File(destinationPath);
		if (!file.isDirectory() && !file.exists())
			new File(file.getParent()).mkdirs();
		file.delete();
		assertEquals(true, file.createNewFile());
	}

	@Test
	public void testConstructSortByNameObjectList() {

		List<Order> expectedList = Arrays.asList(
				new Order("BREAD", 1477405487), new Order("BREAD", 1477232687),
				new Order("BREAD", 1474640687), new Order("MEAT", 1506176687),
				new Order("MEATMEAT", 1474295087), new Order("P1ZZA",
						1477491887), new Order("PIZZA", 1477578287), new Order(
						"PIZZA", 1477319087), new Order("VEGVEG", 1474295087));
		List<Order> resultlist = Arrays.asList(new Order("MEAT", 1506176687),
				new Order("PIZZA", 1477578287), new Order("P1ZZA", 1477491887),
				new Order("BREAD", 1477405487), new Order("PIZZA", 1477319087),
				new Order("BREAD", 1477232687), new Order("BREAD", 1474640687),
				new Order("MEATMEAT", 1474295087), new Order("VEGVEG",
						1474295087));
		Collections.sort(resultlist, new SortByOrderName());
		assertEquals(expectedList, resultlist);
	}

	@Test
	public void testConstructSortByTimeObjectList() {

		List<Order> expectedList = Arrays.asList(new Order("MEATMEAT",
				1474295087), new Order("VEGVEG", 1474295087), new Order(
				"BREAD", 1474640687), new Order("BREAD", 1477232687),
				new Order("PIZZA", 1477319087), new Order("BREAD", 1477405487),
				new Order("P1ZZA", 1477491887), new Order("PIZZA", 1477578287),
				new Order("MEAT", 1506176687));
		List<Order> resultlist = Arrays.asList(new Order("MEAT", 1506176687),
				new Order("PIZZA", 1477578287), new Order("P1ZZA", 1477491887),
				new Order("BREAD", 1477405487), new Order("PIZZA", 1477319087),
				new Order("BREAD", 1477232687), new Order("BREAD", 1474640687),
				new Order("MEATMEAT", 1474295087), new Order("VEGVEG",
						1474295087));
		Collections.sort(resultlist, new SortByTime());
		assertEquals(expectedList, resultlist);
	}

	@Test
	public void testCheckIfSoruceFileExists() {

		assertEquals(true, sourcefile.exists());

	}

	@Test
	public void testFileWritePermission() throws IOException {

		assertEquals(true,
				Files.isWritable(destinationfile.getParentFile().toPath()));

	}

	@Test
	public void testCheckSourceFileHasExtension() {

		assertEquals(true, !Utility.getExtension(sourceFileName).isEmpty());

	}

	@Test
	public void testCheckDesinationFileHasExtension() {

		assertEquals(true, !Utility.getExtension(destinationPath).isEmpty());

	}

	@Test
	public void testValidateDataHeader() throws IOException {
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
		assertEquals("txt", getFileExtension("D.txt"));
		assertEquals("txt", getFileExtension("C:/ouput/input.txt"));
		assertEquals("txt", getFileExtension("A/B/C.TXT"));
		assertEquals("doc", getFileExtension("D.doc"));
		assertEquals("doc", getFileExtension("A/M/C.doc"));
		assertEquals("docx", getFileExtension("E.docx"));
		assertEquals("docx", getFileExtension("A/B/C.docx"));
		assertEquals("pdf", getFileExtension("C.pdf"));
		assertEquals("pdf", getFileExtension("A/B/C.pdf"));
		assertEquals("", getFileExtension("E/B/C.ext/"));
		assertEquals("", getFileExtension("D/B/C.ext/.."));

	}

	@Test
	public void testGetParserObject() {

		assertEquals(
				true,
				DataParserFactory.getInstance().getParserObject("txt") instanceof TextFileParser);
		assertEquals(
				true,
				DataParserFactory.getInstance().getParserObject("pdf") instanceof PdfFileParser);
		assertEquals(
				true,
				DataParserFactory.getInstance().getParserObject("doc") instanceof DocFileParser);
		assertEquals(
				true,
				DataParserFactory.getInstance().getParserObject("docx") instanceof DocFileParser);
	}

	@Test
	public void testSingletonObject() {

		DataParserFactory instance1 = DataParserFactory.getInstance();
		DataParserFactory instance2 = DataParserFactory.getInstance();
		assertSame("2 objects are same", instance1, instance2);
	}

	@Test
	public void testSingletonObjectInstance() {

		DataParserFactory instance1 = DataParserFactory.getInstance();
		DataParserFactory instance2 = DataParserFactory.getInstance();
		assertSame(instance1, instance2);
	}

	@Test
	public void testWriteToFile() {
		List<Order> list = Arrays.asList(new Order("MEATMEAT", 1474295087));

		StringWriter stringWriter = new StringWriter();
		write(stringWriter, list);
		assertEquals("name=MEATMEAT,time=1474295087", stringWriter.toString()
				.trim());
	}

	private void write(Writer writer, List<Order> orderList) {
		PrintWriter out = new PrintWriter(writer);
		try {
			for (int i = 0; i < orderList.size(); i++) {
				out.println(orderList.get(i).toString());
			}
		} finally {
			out.close();
		}
	}

	private String getFileExtension(String fileName) {
		char ch;
		int len;
		if (fileName == null || (len = fileName.length()) == 0
				|| (ch = fileName.charAt(len - 1)) == '/' || ch == '\\'
				|| ch == '.')
			return "";
		int dotInd = fileName.lastIndexOf('.'), sepInd = Math.max(
				fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
		if (dotInd <= sepInd)
			return "";
		else
			return fileName.substring(dotInd + 1).toLowerCase();
	}
}
