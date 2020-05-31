package com.example.covid19.services;


import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.covid19.models.LocationStats;

import java.util.stream.Collectors;

@Service
public class covid19DataService {
     	
	private static String DATA_URL ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/04-17-2020.csv";
	
	private List<LocationStats> allStats = new ArrayList<>();
	
	public List<LocationStats> getAllStats(){
	     return allStats; 
    }

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
        	LocationStats locationStat = new LocationStats();
           
        	locationStat.setProvince_State(record.get("Province_State"));
            locationStat.setCountry_Region(record.get("Country_Region"));
        	
			  int confirmed = Integer.parseInt(record.get("Confirmed")); 
			  int recovered = Integer.parseInt(record.get("Recovered")); 
			  int deaths = Integer.parseInt(record.get("Deaths"));
			 
				/*
				 * LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();
				 * 
				 * ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);
				 * 
				 * System.out.println(listWithoutDuplicates);
				 */
		        
            //int latestCases = Integer.parseInt(record.get(record.size() - 1));
            //int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
           // locationStat.setLatestTotalCases(latestCases);
            //locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
            newStats.add(locationStat);
            
        }
        this.allStats = newStats;
    }

}

