package dom;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;

public class SingleMeasureRequest implements ISingleMeasureRequest {
	
	private String requestName;
	private String countryName;
	private String indicatorString;
	IMeasurementVector answer;

	private boolean answeredFlag;
	
	public  SingleMeasureRequest(String requestName, String countryName, String indicatorString, IMeasurementVector answer, boolean answeredFlag) {
        this.requestName = requestName;
        this.countryName = countryName;
        this.indicatorString = indicatorString;
        this.answer = answer;
        this.answeredFlag = answeredFlag;
    }

	@Override
	public String getRequestName() {
		// TODO Auto-generated method stub
		return requestName;
	}

	@Override
	public String getRequestFilter() {
		// TODO Auto-generated method stub
		return "Country ~ " + countryName  + " Indicator: " + indicatorString;
	}
	
	@Override
	public boolean isAnsweredFlag() {
		// TODO Auto-generated method stub
		return answeredFlag;
	}

	@Override
	public IMeasurementVector getAnswer() {
		// TODO Auto-generated method stub
		return answer;
	}

	@Override
	public String getDescriptiveStatsString() {
		// TODO Auto-generated method stub
		//return descriptiveStatsString;
		if (answer == null) {
            return "No descriptive stats available";

        } 
		else {
			return answer.getDescriptiveStatsAsString();
        }
	}

	@Override
	public String getRegressionResultString() {
		// TODO Auto-generated method stub
		if (answer == null) {
			return "No Regression stats available";
           
        } else {
			return answer.getRegressionResultAsString();
        }
	}
		
}
