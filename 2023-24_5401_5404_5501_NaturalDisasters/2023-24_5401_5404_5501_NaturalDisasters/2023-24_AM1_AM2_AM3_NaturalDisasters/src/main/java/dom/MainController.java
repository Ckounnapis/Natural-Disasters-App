package dom;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.util.Pair;
import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import engine.IMainController;

public class MainController implements IMainController{
	private List<IMeasurementVector> measurementVectors;
	private List<Pair<Integer, Integer>> measurements;
	private String[] headers;
	private String DescriptiveStatsAsString;
	private IMeasurementVector answer;
	private Map<String,ISingleMeasureRequest> request;
	
	
	public MainController() {
		this.request = new HashMap<>();
	}
	
    @Override
    public List<IMeasurementVector> load(String fileName, String delimiter) throws FileNotFoundException, IOException {
        measurementVectors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Read the header line
            String headerLine = br.readLine();
            headers = headerLine.split(delimiter);

            // Read each data line
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);

                // Extract data from the line
                String country = values[1];
                String indicator = values[4];
                measurements = new ArrayList<>();

                // Assuming data starts from index 5 (1980 onwards)
                for (int i = 5; i < values.length; i++) {
                    int year = 1980 + (i - 5);
                    int value = values[i].isEmpty() ? 0 : Integer.parseInt(values[i]);
                    measurements.add(new Pair<>(year, value));
                }

                // Create IMeasurementVector object and add to the list
                IMeasurementVector measurementVector = new MeasurementVector(country, indicator, measurements);
                measurementVectors.add(measurementVector);
                //System.out.printf("Loaded: Country=%s, Indicator=%s, Measurements=%s%n", country, indicator, measurements);
            }
        }
        return measurementVectors;
    }

    
	@Override
	public ISingleMeasureRequest findSingleCountryIndicator(String requestName, String countryName,
			String indicatorString) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		for (IMeasurementVector measurementVector : measurementVectors) {
            if (measurementVector.getCountryName().equalsIgnoreCase(countryName)
                    && measurementVector.getIndicatorString().equalsIgnoreCase(indicatorString)) {
                // Found a matching combination
                ISingleMeasureRequest answer = new SingleMeasureRequest(
                        requestName,
                        countryName,
                        indicatorString,
                        measurementVector,
                        true // Set answered flag to true
                );
                request.put(requestName, answer);
                return answer;
            }
        }
		throw new IllegalArgumentException("Country does not exist");
	}

	
	@Override
	public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName,
			String indicatorString, int startYear, int endYear) throws IllegalArgumentException {
		    List<Pair<Integer, Integer>> measurements = new ArrayList<>();

		    for (IMeasurementVector measurementVector : measurementVectors) {
		        if (measurementVector.getCountryName().equalsIgnoreCase(countryName)
		                && measurementVector.getIndicatorString().equalsIgnoreCase(indicatorString)) {
		            // Found a matching combination

		            // Filter measurements based on the specified time range
		            for (Pair<Integer, Integer> measurement : measurementVector.getMeasurements()) {
		                int year = measurement.getKey();
		                if (year >= startYear && year <= endYear) {
		                    measurements.add(measurement);
		                }
		            }

		            // Create IMeasurementVector object with filtered measurements
		            IMeasurementVector measurementVectorInRange = new MeasurementVector(
		                    countryName,
		                    indicatorString,
		                    measurements
		            );

		            // Create and return the corresponding ISingleMeasureRequest
		            ISingleMeasureRequest answer = new SingleMeasureRequest(
		                    requestName,
		                    countryName,
		                    indicatorString,
		                    measurementVectorInRange,
		                    true // Set answered flag to true
		            );
		            request.put(requestName, answer);
		            return answer;
		        }
		    }

		    // If no matching combination is found, return null
		    throw new IllegalArgumentException("Country does not exist"); 
	}
	
	
	@Override
	public Set<String> getAllRequestNames() {
		return request.keySet();
	}

	@Override
	public ISingleMeasureRequest getRequestByName(String requestName) {
		return request.get(requestName);
	}

	@Override
	public ISingleMeasureRequest getRegression(String requestName) {
		ISingleMeasureRequest req = request.get(requestName);

		    if (req != null && req.getAnswer() != null) {
		        // Retrieve regression results from the associated SingleMeasureRequest
		        IMeasurementVector regressionResult = req.getAnswer();
		        if (regressionResult != null) {
		        	regressionResult.getRegressionResultAsString();
		        	return req;
		        }

		        
		    }

		    return null;  // Return null if the request or answer is null
	}

	
	@Override
	public ISingleMeasureRequest getDescriptiveStats(String requestName) {
		ISingleMeasureRequest req = request.get(requestName);

	    if (req != null && req.getAnswer() != null) {
	        // Retrieve regression results from the associated SingleMeasureRequest
	        IMeasurementVector descriptiveStats = req.getAnswer();
	        if (descriptiveStats != null) {
	        	descriptiveStats.getDescriptiveStatsAsString();
	        	return req;
	        }

	        
	    }

	    return null; 
	}
	
	
	@Override
	public int reportToFile(String outputFilePath, String requestName, String reportType) throws IOException {
		ISingleMeasureRequest req = getRequestByName(requestName);

        if (req != null) {
            String reportContent = generateReportContent(req, reportType);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                writer.write(reportContent);
            }

            return 1; // Successfully wrote to the file
        } else {
            return 0; // Request not found
        }
	}
	
	
	private String generateReportContent(ISingleMeasureRequest request, String reportType) {
        switch (reportType.toLowerCase()) {
            case "text":
                return generateTextReport(request);
            case "markdown":
            case "md":
                return generateMarkdownReport(request);
            case "html":
                return generateHtmlReport(request);
            default:
                throw new IllegalArgumentException("Unsupported report type: " + reportType);
        }
    }
	
	
	private String generateTextReport(ISingleMeasureRequest request) {
	    StringBuilder textReport = new StringBuilder();
	    textReport.append("Text Report for ").append(request.getRequestName()).append("\n\n");
	    textReport.append("Request Filter: ").append(request.getRequestFilter()).append("\n");
	    textReport.append("\n");
	    textReport.append("\nRequest Details:\n");
	    appendMeasurementVectorDetails(textReport, request.getAnswer());
	    
	    // Add descriptive stats
	    textReport.append("-----------------------\n");
	    textReport.append(request.getDescriptiveStatsString()).append("\n");

	    // Add regression result
	    textReport.append("-----------------------\n");
	    textReport.append(request.getRegressionResultString()).append("\n");

	    // Add request details, country, indicator, year, and value
	    textReport.append("-----------------------\n");
	    return textReport.toString();
	}

	
	private String generateMarkdownReport(ISingleMeasureRequest request) {
	    StringBuilder markdownReport = new StringBuilder();
	    markdownReport.append("# Markdown Report for ").append(request.getRequestName()).append("\n\n");
	    markdownReport.append("Request Filter: *").append(request.getRequestFilter()).append("*\n");
	    markdownReport.append("\n");
	    markdownReport.append("\n**Request Details:**\n");
	    appendMeasurementVectorDetails(markdownReport, request.getAnswer());
	    
	    // Add descriptive stats
	    markdownReport.append("\n");
	    markdownReport.append("---------------\n");
	    markdownReport.append("\n");
	    markdownReport.append(request.getDescriptiveStatsString()).append("\n");
	    markdownReport.append("---------------\n");

	    // Add regression result
	    markdownReport.append("\n");
	    markdownReport.append("---------------\n");
	    markdownReport.append(request.getRegressionResultString()).append("\n");
	    markdownReport.append("---------------\n");
	    

	    return markdownReport.toString();
	}

	
	private String generateHtmlReport(ISingleMeasureRequest request) {
	    StringBuilder htmlReport = new StringBuilder();
	    htmlReport.append("<h1>HTML Report for ").append(request.getRequestName()).append("</h1>\n\n");
	    htmlReport.append("<p><strong>Request Filter:</strong> ").append(request.getRequestFilter()).append("</p>\n");
	    
	    // Add request details, country, and indicator
	    htmlReport.append("\n<h2>Request Details:</h2>\n");
	    htmlReport.append("<p><strong>Country:</strong> ").append(request.getAnswer().getCountryName()).append("</p>\n");
	    htmlReport.append("<p><strong>Indicator:</strong> ").append(request.getAnswer().getIndicatorString()).append("</p>\n");
	    
	    // Add table for year and value
	    htmlReport.append("\n<h2>|Year | Value|</h2>\n");
	    htmlReport.append("<pre>\n");

	    for (Pair<Integer, Integer> measurement : request.getAnswer().getMeasurements()) {
	        int year = measurement.getKey();
	        int value = measurement.getValue();
	        htmlReport.append(String.format("%5d | %5d\n", year, value));
	    }

	    htmlReport.append("</pre>\n");

	    // Add descriptive stats
	    htmlReport.append("\n<h2>DescriptiveStats Results:</h2>\n");
	    htmlReport.append("<pre>").append(request.getDescriptiveStatsString()).append("</pre>\n");

	    // Add regression result
	    htmlReport.append("\n<h2>Regression Result:</h2>\n");
	    htmlReport.append("<pre>").append(request.getRegressionResultString()).append("</pre>\n");

	    return htmlReport.toString();
	}


	private void appendMeasurementVectorDetails(StringBuilder report, IMeasurementVector measurementVector) {
	   if (measurementVector != null) {
	        report.append("\nCountry: \n").append(measurementVector.getCountryName()).append("\n");
	        report.append("-----------------\n");
	        report.append("Indicator: \n").append(measurementVector.getIndicatorString()).append("\n");
	        report.append("-----------------\n");
	        List<Pair<Integer, Integer>> measurements = measurementVector.getMeasurements();
	        report.append("|Year: | Value:|\n");
	        
	        for (Pair<Integer, Integer> measurement : measurementVector.getMeasurements()) {
	            int year = measurement.getKey();
	            int value = measurement.getValue();
	            report.append(String.format("%5d  | %3d\n", year, value));
	        }
	    } 
	    else {
	        report.append("No measurement vector available");
	    }
	}
}
