package testers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import org.junit.jupiter.api.Test;

import dom.MeasurementVector;
import dom.SingleMeasureRequest;

class SingleMeasureRequestTest {

	@Test
	void testSingleMeasureRequest() {
		 IMeasurementVector measurementVector = new MeasurementVector("Country", "Indicator", null);
		 ISingleMeasureRequest singleMeasureRequest = new SingleMeasureRequest("RequestName", "Country", "Indicator", measurementVector, true);
		 assertNotNull(singleMeasureRequest);
		 
	}

	@Test
	void testGetRequestName() {
		 ISingleMeasureRequest singleMeasureRequest = new SingleMeasureRequest("RequestName", "Country", "Indicator", null, true);
		 assertEquals("RequestName", singleMeasureRequest.getRequestName());
		
	}

	@Test
	void testGetRequestFilter() {
		ISingleMeasureRequest singleMeasureRequest = new SingleMeasureRequest("RequestName", "Country", "Indicator", null, true);
		 assertNotNull("Country~ Country - Indiator: Indicator", singleMeasureRequest.getRequestFilter());
		 
	}

	@Test
	void testIsAnsweredFlag() {
		 ISingleMeasureRequest singleMeasureRequest = new SingleMeasureRequest("RequestName", "Country", "Indicator", null, true);
		 assertTrue(singleMeasureRequest.isAnsweredFlag());
		
	}

	@Test
	void testGetAnswer() {
		 IMeasurementVector measurementVector = new MeasurementVector("Country", "Indicator", null);
		 ISingleMeasureRequest singleMeasureRequest = new SingleMeasureRequest("RequestName", "Country", "Indicator", measurementVector, true);
		 assertEquals(measurementVector, singleMeasureRequest.getAnswer());
		
	}

	@Test
	void testGetDescriptiveStatsString() {
		List<Pair<Integer , Integer>> nullList = new ArrayList<>();
		IMeasurementVector measurementVector = new MeasurementVector("Country", "Indicator", nullList);
		ISingleMeasureRequest singleMeasureRequest = new SingleMeasureRequest("RequestName", "Country", "Indicator", measurementVector, true);
		assertNotEquals("No descriptive stats available", singleMeasureRequest.getDescriptiveStatsString());
		
	}

	@Test
	void testGetRegressionResultString() {
		List<Pair<Integer, Integer>> nullList = new ArrayList<>();
		IMeasurementVector measurementVector = new MeasurementVector("Country", "Indicator", nullList);
		ISingleMeasureRequest singleMeasureRequest = new SingleMeasureRequest("RequestName", "Country", "Indicator", measurementVector, true);
		assertNotEquals(" No Regression stats available " , singleMeasureRequest.getRegressionResultString());
		
	}

}
