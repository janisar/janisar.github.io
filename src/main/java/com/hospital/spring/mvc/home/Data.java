package com.hospital.spring.mvc.home;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Data {
	
	private ArrayList<Disease> diseases;
	private Disease[] topThreeDiseases;
	private Symptom[] topThreeSymptoms;
	private HashSet<Symptom> uniqueSymptoms;
	
	public Data() {
		this.diseases = getDiseasesInfo("/home/janis/Desktop/haigused.csv");
		this.topThreeDiseases = countTopThreeDiseases();
		this.uniqueSymptoms = countUniqueSymtpoms();
		this.topThreeSymptoms = countTopThreeSymptoms();
	}

	private Symptom[] countTopThreeSymptoms() {
		Symptom[] topThree = new Symptom[3];
		ArrayList<Symptom> allSymptoms = new ArrayList<Symptom>(uniqueSymptoms);
		Collections.sort(allSymptoms);
		if (allSymptoms.size() > 3) {
			for (int i = 0; i < 3; i++) 
				topThree[i] = allSymptoms.get(i);
		} else {
			for (int i = 0; i < allSymptoms.size(); i++) 
				topThree[i] = allSymptoms.get(i);
		}
		return topThree;
	}

	private HashSet<Symptom> countUniqueSymtpoms() {
		HashSet<Symptom> symptoms = new HashSet<Symptom>();
		for (Disease d : diseases) {
			symptoms.addAll(d.getAllSymptoms());
		}
		return symptoms;
	}

	private Disease[] countTopThreeDiseases() {
		Disease[] topThree = new Disease[3];
		Collections.sort(diseases);
		if (diseases.size() > 3) {
			for (int i = 0; i < 3; i++) 
				topThree[i] = diseases.get(i);
		} else {
			for (int i = 0; i < diseases.size(); i++) 
				topThree[i] = diseases.get(i);
		}
		return topThree;
	}

	private ArrayList<Disease> getDiseasesInfo(String csvFile) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<Disease> diseases = new ArrayList<Disease>();
		HashSet<Symptom> totalSymptoms = new HashSet<Symptom>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF8"));
			String[] disease = null;
			while ((line = br.readLine()) != null) {
				disease = line.split(cvsSplitBy);
				Disease newDisease = new Disease(disease[0]);
				for (int i = 1; i < disease.length; i++) {
					totalSymptoms.add(new Symptom(disease[i]));
					Iterator<Symptom> it = totalSymptoms.iterator();
					while (it.hasNext()) {
						Symptom s = it.next();
						if (s.equals(new Symptom(disease[i]))) {
							newDisease.addSymptom(s);
							s.addDisease(newDisease);
							break;
						}
					}
				}
				diseases.add(newDisease);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return diseases;
	}
	
	public Disease[] getTopThreeDiseases() {
		return topThreeDiseases;
	}
	
	public Symptom[] getTopThreeSymptoms() {
		return topThreeSymptoms;
	}

	public int getUniqueSymptomsCount() {
		return uniqueSymptoms.size();
	}
	public ArrayList<Symptom> getUniqueSymptoms() {
		return new ArrayList<Symptom>(uniqueSymptoms);
	}
	public ArrayList<Disease> getDiseases() {
		return diseases;
	}
}
