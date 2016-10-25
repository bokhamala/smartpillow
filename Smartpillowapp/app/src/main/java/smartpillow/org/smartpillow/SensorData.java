package smartpillow.org.smartpillow;

// A Java class that can be serialized to JSON
public class SensorData {
    private String _id;
    private double temperature;
    private double humidity;
    private double light;
    private double sound;
    private double motion;
    private int hour;
    private int minute;
    private int second;
    private String date;
    private double low_beta;
    private double high_beta;
    private double delta;
    private double low_gamma;
    private double mid_gamma;
    private double low_alpha;
    private double high_alpha;
    private double theta;
    private double attention;
    private double meditation;
    private int number;

    public int getNumber(){
        return number;
    }
    public double getLow_beta() {
        return low_beta;
    }

    public double getHigh_beta() {
        return high_beta;
    }

    public double getDelta() {
        return delta;
    }

    public double getLow_gamma() {
        return low_gamma;
    }

    public double getMid_gamma() {
        return mid_gamma;
    }

    public double getLow_alpha() {
        return low_alpha;
    }

    public double getHigh_alpha() {
        return high_alpha;
    }

    public double getTheta() {
        return theta;
    }

    public double getAttention() {
        return attention;
    }

    public double getMeditation() {
        return meditation;
    }

    public String getId() {
        return _id;
    }

    public double getTemp() {
        return temperature;
    }
    public double getHum() {
        return humidity;
    }

    public double getLight() {
        return light;
    }

    public double getSound() {
        return sound;
    }

    public double getMotion() {
        return motion;
    }

    public String getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }


}
