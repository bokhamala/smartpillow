package smartpillow.org.smartpillow;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Armando on 10/17/2016.
 */
public class Statistic extends Fragment {
    private View myView;
    LineChart lineChart;
    private ArrayList<Entry> tempSeries;
    private ArrayList<Entry> humSeries;
    private ArrayList<Entry> motionSeries;
    private ArrayList<Entry> lightSeries;
    private ArrayList<Entry> soundSeries;
    private ArrayList<Entry> lowBettaSeries;
    private ArrayList<Entry> highBettaSeries;
    private ArrayList<Entry> deltaSeries;
    private ArrayList<Entry> lowGammaSeries;
    private ArrayList<Entry> midGammaSeries;
    private ArrayList<Entry> lowAlphaSeries;
    private ArrayList<Entry> highAlphaSeries;
    private ArrayList<Entry> thetaSeries;
    private ArrayList<Entry> attentionSeries;
    private ArrayList<Entry> meditationSeries;

    private String DB_NAME = "sensor_data";
    private String ACCOUNT = "1cb9f2e7-744c-4ff4-8f08-99569ee60a8b-bluemix";
    private String USERNAME = "hentridesolideselfwaspro";
    private String PASSWORD = "f090ebbd9645b0fa1041caaeee5a07dc823c7f89";
    private List<SensorData> sensorData = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.statistic, container, false);
        Spinner spinner = (Spinner) myView.findViewById(R.id.graph_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(myView.getContext(), R.array.graph_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    createGraph(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (sensorData == null)
            new ReadAsyncTask().execute("9b5bf2f54a9eaae294e9ac7c283e0b70");

        return myView;
    }

    public void createGraph(int graphType) {
        lineChart = (LineChart) myView.findViewById(R.id.lineChart);

        switch (graphType) {
            case 1:
                tempSeries = new ArrayList<Entry>();
                break;
            case 2:
                humSeries = new ArrayList<Entry>();
                break;
            case 3:
                soundSeries = new ArrayList<Entry>();
                break;
            case 4:
                motionSeries = new ArrayList<Entry>();
                break;
            case 5:
                lightSeries = new ArrayList<Entry>();
                break;
            case 6:
                lowBettaSeries = new ArrayList<Entry>();
                highBettaSeries = new ArrayList<Entry>();
                break;
            case 7:
                deltaSeries = new ArrayList<Entry>();
                break;
            case 8:
                lowGammaSeries = new ArrayList<Entry>();
                midGammaSeries = new ArrayList<Entry>();
                break;
            case 9:
                thetaSeries = new ArrayList<Entry>();
                break;
            case 10:
                lowAlphaSeries = new ArrayList<Entry>();
                highAlphaSeries = new ArrayList<Entry>();
                break;
            case 11:
                attentionSeries = new ArrayList<Entry>();
                break;
            case 12:
                meditationSeries = new ArrayList<Entry>();
        }

        for(int i=0; i<sensorData.size(); i++){
            switch (graphType) {
                case 1:
                    tempSeries.add(new Entry(i, (float)sensorData.get(i).getTemp()));
                    break;
                case 2:
                    humSeries.add(new Entry(i, (float)sensorData.get(i).getHum()));
                    break;
                case 3:
                    soundSeries.add(new Entry(i, (float)sensorData.get(i).getSound()));
                    break;
                case 4:
                    motionSeries.add(new Entry(i, (float)sensorData.get(i).getMotion()));
                    break;
                case 5:
                    lightSeries.add(new Entry(i, (float)sensorData.get(i).getLight()));
                    break;
                case 6:
                    lowBettaSeries.add(new Entry(i, (float)sensorData.get(i).getLow_beta()));
                    highBettaSeries.add(new Entry(i, (float)sensorData.get(i).getHigh_beta()));;
                    break;
                case 7:
                    deltaSeries.add(new Entry(i, (float)sensorData.get(i).getDelta()));
                    break;
                case 8:
                    lowGammaSeries.add(new Entry(i, (float)sensorData.get(i).getLow_gamma()));
                    midGammaSeries.add(new Entry(i, (float)sensorData.get(i).getMid_gamma()));
                    break;
                case 9:
                    thetaSeries.add(new Entry(i, (float)sensorData.get(i).getTheta()));
                    break;
                case 10:
                    lowAlphaSeries.add(new Entry(i, (float)sensorData.get(i).getLow_alpha()));
                    highAlphaSeries.add(new Entry(i, (float)sensorData.get(i).getHigh_alpha()));
                    break;
                case 11:
                    attentionSeries.add(new Entry(i, (float)sensorData.get(i).getAttention()));
                    break;
                case 12:
                    meditationSeries.add(new Entry(i, (float)sensorData.get(i).getMeditation()));
                    break;
            }
        }

        AxisValueFormatter formatter = new AxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return sensorData.get((int)value).getHour() + ":" + sensorData.get((int)value).getMinute() + ":" + sensorData.get((int)value).getSecond();
            }

            // we don't draw numbers, so no decimal digits needed
            @Override
            public int getDecimalDigits() {  return 0; }
        };
        
        //Set X axis formatter
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        xAxis.setLabelRotationAngle(45F);

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = null;
        LineDataSet lineDataSet2 = null;

        switch (graphType) {
            case 1:
                lineDataSet1 = new LineDataSet(tempSeries, "Temperature");
                lineDataSets.add(lineDataSet1);
                break;
            case 2:
                lineDataSet1 = new LineDataSet(humSeries, "Humidity");
                lineDataSets.add(lineDataSet1);
                break;
            case 3:
                lineDataSet1 = new LineDataSet(soundSeries, "Sound");
                lineDataSets.add(lineDataSet1);
                break;
            case 4:
                lineDataSet1 = new LineDataSet(humSeries, "Motion");
                lineDataSets.add(lineDataSet1);
                break;
            case 5:
                lineDataSet1 = new LineDataSet(lightSeries, "Light");
                lineDataSets.add(lineDataSet1);
                break;
            case 6:
                lineDataSet1 = new LineDataSet(lowBettaSeries, "Low Betta");
                lineDataSets.add(lineDataSet1);
                lineDataSet2 = new LineDataSet(highBettaSeries, "High Beta");
                lineDataSets.add(lineDataSet2);
                break;
            case 7:
                lineDataSet1 = new LineDataSet(deltaSeries, "Delta");
                lineDataSets.add(lineDataSet1);
                break;
            case 8:
                lineDataSet1 = new LineDataSet(lowGammaSeries, "Low Gamma");
                lineDataSets.add(lineDataSet1);
                lineDataSet2 = new LineDataSet(midGammaSeries, "Mid Gamma");
                lineDataSets.add(lineDataSet2);
                break;
            case 9:
                lineDataSet1 = new LineDataSet(thetaSeries, "Theta");
                lineDataSets.add(lineDataSet1);
                break;
            case 10:
                lineDataSet1 = new LineDataSet(lowAlphaSeries, "Low Alpha");
                lineDataSets.add(lineDataSet1);
                lineDataSet2 = new LineDataSet(highAlphaSeries, "High Alpha");
                lineDataSets.add(lineDataSet2);
                break;
            case 11:
                lineDataSet1 = new LineDataSet(attentionSeries, "Attention");
                lineDataSets.add(lineDataSet1);
                break;
            case 12:
                lineDataSet1 = new LineDataSet(meditationSeries, "Meditation");
                lineDataSets.add(lineDataSet1);
                break;
        }
        if(lineDataSet1 != null){
            lineDataSet1.setDrawValues(false);
            lineDataSet1.setDrawCircles(false);
            lineDataSet1.setColor(Color.BLUE);
        }
        if(lineDataSet2 != null){
            lineDataSet2.setDrawValues(false);
            lineDataSet2.setDrawCircles(false);
            lineDataSet2.setColor(Color.RED);
        }
        lineChart.setData(new LineData(lineDataSets));
        //lineChart.notifyDataSetChanged();

        lineChart.setVisibleXRangeMaximum(65f);

    }

    class ReadAsyncTask extends AsyncTask<String, Void, List<SensorData>> {
        @Override
        protected List<SensorData> doInBackground(String... arg0) {
            SensorData user = null;
            List<SensorData> users = null;
            try {
                String id = arg0[0];

                // Create a new CloudantClient instance for account endpoint <ACCOUNT>.cloudant.com
                CloudantClient client = ClientBuilder.account(ACCOUNT)
                        .username(USERNAME)
                        .password(PASSWORD)
                        .build();
                // Get a Database instance to interact with. Do not create it if it doesn't already exist
                Database db = client.database(DB_NAME, false);
                // Get an ExampleDocument out of the database and deserialize the JSON into a Java type
                String json = "{\n" +
                        "  \"selector\": {\n" +
                        "    \"number\": {\n" +
                        "      \"$gte\": 0\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"fields\": [\n" +
                        "    \"_id\",\n" +
                        "    \"temperature\",\n" +
                        "    \"humidity\",\n" +
                        "    \"motion\",\n" +
                        "    \"light\",\n" +
                        "    \"sound\",\n" +
                        "    \"date \",\n" +
                        "    \"day \",\n" +
                        "    \"month \",\n" +
                        "    \"year \",\n" +
                        "    \"hour \",\n" +
                        "    \"minute \",\n" +
                        "    \"second \",\n" +
                        "    \"high_beta\",\n" +
                        "    \"low_beta\",\n" +
                        "    \"low_gamma \",\n" +
                        "    \"meditation\",\n" +
                        "    \"high_alpha \",\n" +
                        "    \"theta \",\n" +
                        "    \"low_alpha \",\n" +
                        "    \"attention \",\n" +
                        "    \"delta \",\n" +
                        "    \"mid_gamma \",\n" +
                        "    \"number \"\n" +
                        "  ],\n" +
                        "  \"sort\": [\n" +
                        "    {\n" +
                        "      \"number\": \"asc\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";

                users = db.findByIndex(json, SensorData.class);

                //user = db.findAny(User.class, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return users;
        }

        @Override
        protected void onPostExecute(List<SensorData> users) {
            super.onPostExecute(users);
            Collections.sort(users, new Comparator<SensorData>() {
                @Override
                public int compare(SensorData p1, SensorData p2) {
                    return p1.getNumber() - p2.getNumber(); // Ascending
                }

            });
            sensorData = users;
            createGraph(1);
        }
    }

}