package main.java;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PatientList {

    private Patient[] patients; // Made non-final to allow resizing during import
    private int size;
    private int indexOfIteration = -1; // -1 indicates no iteration in progress
    private Map<Patient, PrescriptionList> prescriptionsMap = new HashMap<>();

    public PatientList() {
        this.patients = new Patient[1000];
        this.size = 0;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean add(Patient patient) {
        if (size >= patients.length) {
            // Resize the array if it's full
            patients = Arrays.copyOf(patients, patients.length * 2);
        }
        patients[size++] = patient;
        prescriptionsMap.put(patient, new PrescriptionList());
        return true;
    }

    public void sort() {
        mergeSort(patients, 0, size - 1);
    }

    public Patient find(PatientIdentity identity) {
        return binarySearch(identity);
    }

    private Patient binarySearch(PatientIdentity identity) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison =
                    patients[mid].patientIdentity().compareTo(identity);
            if (comparison == 0) {
                return patients[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public void initIteration() {
        sort(); // Sort before iterating
        if (size > 0) {
            indexOfIteration = 0;
        } else {
            indexOfIteration = -1;
        }
    }

    public Patient next() {
        if (indexOfIteration == -1 || indexOfIteration >= size) {
            indexOfIteration = -1;
            return null;
        }

        Patient currentPatient = patients[indexOfIteration];
        indexOfIteration++;

        if (indexOfIteration >= size) {
            indexOfIteration = -1;
        }

        return currentPatient;
    }

    public boolean saveToFile(String filename) {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename))
        ) {
            Patient[] sortedPatients = Arrays.copyOf(patients, size);
            mergeSort(sortedPatients, 0, sortedPatients.length - 1);

            for (Patient patient : sortedPatients) {
                writer.write(patient.toCSV());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean importFromFile(String filename) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(filename))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Patient patient = Patient.makePatient(line);
                    add(patient);
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            sort();
        }
    }

    public void readPrescriptionsFromCSV(String filename) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 6) continue;

                String name = parts[0].trim();
                String dob = parts[1].trim();

                String[] nameParts = name.split(" ", 2);
                if (nameParts.length < 2) continue;
                Name patientName = new Name(nameParts[0], nameParts[1]);

                Date dobDate;
                try {
                    dobDate = dateFormat.parse(dob);
                } catch (ParseException e) {
                    continue;
                }

                PatientIdentity identity = new PatientIdentity(patientName, dobDate);

                Patient patient = find(identity);
                if (patient != null) {
                    Prescription prescription = Prescription.fromCSVLine(line);
                    PrescriptionList list = prescriptionsMap.get(patient);
                    if (list != null) {
                        list.add(prescription);
                    }
                }
            }
        }
    }

    private void mergeSort(Patient[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(Patient[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Patient[] L = new Patient[n1];
        Patient[] R = new Patient[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i].patientIdentity().compareTo(R[j].patientIdentity()) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
