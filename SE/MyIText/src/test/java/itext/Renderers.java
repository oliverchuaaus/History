package itext;

import org.junit.Test;

public class Renderers {
	@Test
	public void testNewYorkTimes() throws Exception {
		C03E01_NewYorkTimes c = new C03E01_NewYorkTimes();
		c.createPdf(".output/NewYorkTimes.pdf");
	}

	@Test
	public void testPremierLeague() throws Exception {
		C03E02_PremierLeague c = new C03E02_PremierLeague();
		c.createPdf(".output/PremierLeague.pdf");
	}

	@Test
	public void testUFO() throws Exception {
		C03E03_UFO c = new C03E03_UFO();
		c.createPdf(".output/UFO.pdf");
	}
}
