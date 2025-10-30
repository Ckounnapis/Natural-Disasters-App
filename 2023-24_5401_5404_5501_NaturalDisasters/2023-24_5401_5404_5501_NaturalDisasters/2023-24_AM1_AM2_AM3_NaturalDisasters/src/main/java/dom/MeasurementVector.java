package dom;

import java.util.List;
import dom2app.ISingleMeasureRequest;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.Pair;
import dom2app.IMeasurementVector;

public class MeasurementVector implements IMeasurementVector {
	private String countryName;
	private String indicatorString;
	private List<Pair<Integer, Integer>> measurements;
	private String DescriptiveStatsAsString;
	private String DescriptiveStatsString;

	public MeasurementVector(String countryName, String indicatorString, List<Pair<Integer, Integer>> measurements) {
         this.countryName = countryName;
         this.indicatorString = indicatorString;
         this.measurements = measurements;
         this.DescriptiveStatsAsString = DescriptiveStatsAsString;
         this.DescriptiveStatsString = DescriptiveStatsString;
     }
	
	@Override
	public String getCountryName() {
		// TODO Auto-generated method stub
		return countryName;
	}

	@Override
	public String getIndicatorString() {
		// TODO Auto-generated method stub
		return indicatorString;
	}

	@Override
	public List<Pair<Integer, Integer>> getMeasurements() {
		// TODO Auto-generated method stub
		return measurements;
	}

	@Override
	public String getDescriptiveStatsAsString() {
		// TODO Auto-generated method stub
		//return null;
		DescriptiveStatistics stats = new DescriptiveStatistics();

        for (Pair<Integer, Integer> measurement : getMeasurements()) {
        	
            int value = measurement.getSecond();
            stats.addValue(value);
        }
        
        // Format the descriptive stats as a string
        return String.format("DescriptiveStats Results :\n Count=%d,\n Sum=%.2f,\n Min=%.2f,\n Max=%.2f,\n Mean=%.2f,\n Median=%.2f,\n StdDev=%.2f,\n Kurtosis=%.2f,\n gMean=%.2f",
                stats.getN() ,stats.getSum() , stats.getMin(), stats.getMax(), stats.getMean(), stats.getPercentile(50),
                stats.getStandardDeviation(),stats.getKurtosis(), stats.getGeometricMean());
	}

	@Override
	public String getRegressionResultAsString() {
		// TODO Auto-generated method stub
		//return null;
		SimpleRegression regression = new SimpleRegression();
		for (Pair<Integer, Integer> measurement : measurements) {
            regression.addData(measurement.getKey(), measurement.getValue());
        }
		double slope = regression.getSlope();
		double slopeError = regression.getSlopeStdErr();
        double intercept = regression.getIntercept();
        
        String tendency = determineTendency(slope);
        return "Regression Result:\nSlope: \n" + slope  + " \nSlopeError: \n" + slopeError + " \nIntercept: \n" + intercept +  " \nTendency: " + tendency;
	}
	
	private String determineTendency(double slope) {
		if (slope > 0.1) {
			return "Increasing";
		}else if (slope < -0.1) {
			return "Decreasing";
		}else{
			return "Stable";
		}
	}

}
