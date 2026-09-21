import java.util.ArrayList;

public class Practice_Q3_Patient_Vitals_Encapsulation_Guard {
    static class PatientVitals {
        private final ArrayList<Double> readings = new ArrayList<>();

        PatientVitals(double[] initialReadings) {
            if (initialReadings != null) {
                for (double reading : initialReadings)
                    recordReading(reading);
            }
        }

        void recordReading(double reading) {
            if (reading > 0 && reading <= 45)
                readings.add(reading);
        }

        double getAverage() {
            if (readings.isEmpty())
                return 0.0;

            double sum = 0;
            for (double reading : readings)
                sum += reading;

            return sum / readings.size();
        }

        double[] getAllReadings() {
            double[] result = new double[readings.size()];
            for (int i = 0; i < readings.size(); i++)
                result[i] = readings.get(i);
            return result;
        }
    }

    public static void main(String[] args) {
        PatientVitals v = new PatientVitals(new double[]{36.5, -2, 37.1});

        double[] copy = v.getAllReadings();
        copy[0] = 999;

        for (double x : v.getAllReadings())
            System.out.print(x + " ");
    }
}
