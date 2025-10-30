package testers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.Pair;
import dom2app.IMeasurementVector;
import org.junit.jupiter.api.Test;

import dom.MeasurementVector;

class MeasurementVectorTest {

	@Test
	void testMeasurementVector() {
		  String countryName = "SampleCountry";
	        String indicatorString = "SampleIndicator";
	        List<Pair<Integer, Integer>> measurements = new ArrayList<>();
	        measurements.add(new Pair<>(1, 10));
	        measurements.add(new Pair<>(2, 20));
	        measurements.add(new Pair<>(3, 30));

	        MeasurementVector measurementVector = new MeasurementVector(countryName, indicatorString, measurements);
	        assertNotNull(measurementVector);
	}

	@Test
	void testGetCountryName() {
		String countryName = "SampleCountry";
        String indicatorString = "SampleIndicator";
        List<Pair<Integer, Integer>> measurements = new ArrayList<>();
        measurements.add(new Pair<>(1, 10));
        measurements.add(new Pair<>(2, 20));
        measurements.add(new Pair<>(3, 30));

        MeasurementVector measurementVector = new MeasurementVector(countryName, indicatorString, measurements);
        assertEquals(countryName, measurementVector.getCountryName());
		
	}

	@Test
	void testGetIndicatorString() {
		 String countryName = "SampleCountry";
	        String indicatorString = "SampleIndicator";
	        List<Pair<Integer, Integer>> measurements = new ArrayList<>();
	        measurements.add(new Pair<>(1, 10));
	        measurements.add(new Pair<>(2, 20));
	        measurements.add(new Pair<>(3, 30));

	        MeasurementVector measurementVector = new MeasurementVector(countryName, indicatorString, measurements);
	        assertEquals(indicatorString, measurementVector.getIndicatorString());
	}

	@Test
	void testGetMeasurements() {
		String countryName = "SampleCountry";
        String indicatorString = "SampleIndicator";
        List<Pair<Integer, Integer>> measurements = new ArrayList<>();
        measurements.add(new Pair<>(1, 10));
        measurements.add(new Pair<>(2, 20));
        measurements.add(new Pair<>(3, 30));

        MeasurementVector measurementVector = new MeasurementVector(countryName, indicatorString, measurements);
        List<Pair<Integer, Integer>> retrievedMeasurements = measurementVector.getMeasurements();        
        assertNotNull(retrievedMeasurements);
        assertEquals(measurements.size(), retrievedMeasurements.size());
	}

	@Test
	void testGetDescriptiveStatsAsString() {
		 String countryName = "SampleCountry";
	        String indicatorString = "SampleIndicator";
	        List<Pair<Integer, Integer>> measurements = new ArrayList<>();
	        measurements.add(new Pair<>(1, 10));
	        measurements.add(new Pair<>(2, 20));
	        measurements.add(new Pair<>(3, 30));

	        MeasurementVector measurementVector = new MeasurementVector(countryName, indicatorString, measurements);
	        String descriptiveStats = measurementVector.getDescriptiveStatsAsString();
	        assertNotNull(descriptiveStats);
	}

	@Test
	void testGetRegressionResultAsString() {
		String countryName = "SampleCountry";
        String indicatorString = "SampleIndicator";
        List<Pair<Integer, Integer>> measurements = new ArrayList<>();
        measurements.add(new Pair<>(1, 10));
        measurements.add(new Pair<>(2, 20));
        measurements.add(new Pair<>(3, 30));

        MeasurementVector measurementVector = new MeasurementVector(countryName, indicatorString, measurements);
        String regressionResult = measurementVector.getRegressionResultAsString();
        assertNotNull(regressionResult);
	}

}
