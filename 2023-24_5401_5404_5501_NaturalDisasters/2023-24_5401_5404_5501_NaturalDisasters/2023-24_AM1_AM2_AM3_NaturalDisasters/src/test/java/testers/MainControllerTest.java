package testers;

import org.apache.commons.math3.util.Pair;
import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import engine.IMainController;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dom.MainController;

class MainControllerTest {
	private  List<IMeasurementVector> measurementVectors = new ArrayList<>();

    @Test
    void testLoad() throws FileNotFoundException, IOException {
        MainController mainController = new MainController();
        String fileName = "src/test/resources/input/_ClimateRelatedDisastersFull.tsv";
        String delimiter = "\t";
        
        measurementVectors = mainController.load(fileName, delimiter);

        // Check if the list is not null
        assertNotNull(measurementVectors);
    }

    @Test
    void testFindSingleCountryIndicator() throws FileNotFoundException, IOException {
        MainController mainController = new MainController();
        String fileName = "src/test/resources/input/_ClimateRelatedDisastersFull.tsv";
        String delimiter = "\t";
        
        mainController.load(fileName, delimiter);
        
        String requestName = "TestRequest";
        String countryName = "Cyprus";
        String indicatorString = "Total";
        
        assertNotNull(mainController.findSingleCountryIndicator(requestName, countryName, indicatorString));
    }

    @Test
    void testFindSingleCountryIndicatorYearRange() throws FileNotFoundException, IOException {
        MainController mainController = new MainController();
        String fileName = "src/test/resources/input/_ClimateRelatedDisastersFull.tsv";
        String delimiter = "\t";
        
        mainController.load(fileName, delimiter);
        
        String requestName = "TestRequest";
        String countryName = "Cyprus";
        String indicatorString = "Total";
        int startYear = 1980;
        int endYear = 1990;
        assertNotNull(mainController.findSingleCountryIndicatorYearRange(requestName, countryName, indicatorString, startYear, endYear));
    }

    @Test
    void testGetAllRequestNames() {
        MainController mainController = new MainController();
        assertNotNull(mainController.getAllRequestNames());
    }

    @Test
    void testGetRequestByName() {
        MainController mainController = new MainController();
        String requestName = "TestRequest";
        assertNull(mainController.getRequestByName(requestName));
    }

    @Test
    void testGetRegression() {
        MainController mainController = new MainController();
        String requestName = "TestRequest";
        assertNull(mainController.getRegression(requestName));
    }

    @Test
    void testGetDescriptiveStats() {
        MainController mainController = new MainController();
        String requestName = "TestRequest";
        assertNull(mainController.getDescriptiveStats(requestName));
    }

    @Test
    void testReportToFile() {
        MainController mainController = new MainController();
        String outputFilePath = "output.txt";
        String requestName = "TestRequest";
        String reportType = "text";
        try {
            int result = mainController.reportToFile(outputFilePath, requestName, reportType);
            assertEquals(0, result); // Assuming the request is not found for the given name
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
}
