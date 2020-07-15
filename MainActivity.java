package in.santiniketan.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<CoronaItem> coronaItemArrayList;
    private RequestQueue requestQueue;
    private TextView dailyDeaths, dailyConfirm, dailyReco,dateHeaders, totalDeath, totalConfirm, totalRecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dailyConfirm = findViewById(R.id.dailyConfirmed);
        dailyDeaths = findViewById(R.id.dailyDeath);
        dailyReco = findViewById(R.id.dailyRecovered);
        dateHeaders = findViewById(R.id.dateHeader);
        totalDeath = findViewById(R.id.totalDeath);
        totalConfirm = findViewById(R.id.totalConfirm);
        totalRecovered = findViewById(R.id.totalRecovered);

        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        coronaItemArrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        jsonParse();
    }

    private void jsonParse() {

        String url ="https://api.covid19india.org/data.json";
        final JsonObjectRequest request = new
                JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    //first fetch the header and display it
                    // Header consists Total and Today's statistics
                    //for Today's details

                    //statewise hold data of Today's cases at index 0

                    JSONArray todayAndTotalDataArray = response.getJSONArray("statewise");
                    JSONObject todayAndTotalDataJsonObject = todayAndTotalDataArray.getJSONObject(0);
                    String dailyConfirmed = todayAndTotalDataJsonObject.getString("deltaconfirmed");
                    String dailyDeath = todayAndTotalDataJsonObject.getString("deltadeaths");
                    String dailyRec = todayAndTotalDataJsonObject.getString("deltarecovered");
                    String dataHeader = todayAndTotalDataJsonObject.getString("lastupdatedtime").substring(0,5);
                    dataHeader = getFormattedDate(dataHeader);

                    // Method implemented

                    dailyConfirm.setText(dailyConfirmed);
                    dailyReco.setText(dailyRec);
                    dailyDeaths.setText(dailyDeath);
                    dateHeaders.setText(dataHeader);

                    //for total details
                    //todayAndTotalDateArray holds data of all the states
                    //At the index 0 of the todayAndTotalDataArray "Total Details" is stored

                    String totalDeathsFetched = todayAndTotalDataJsonObject.getString("deaths");
                    String totalRecoveredFetched = todayAndTotalDataJsonObject.getString("recovered");
                    String totalConfirmedFetched = todayAndTotalDataJsonObject.getString("confirmed");
                    totalConfirm.setText(totalConfirmedFetched);
                    totalDeath.setText(totalDeathsFetched);
                    totalRecovered.setText(totalRecoveredFetched);

                    //now fetch and display the dat6a for all the states
                    //this data is also present in the todayAndTotalDataArray from index 1

                   for (int i=1;i<todayAndTotalDataArray.length();i++){

                       JSONObject stateWiseArrayJSONObject = todayAndTotalDataArray.getJSONObject(i);
                       String  active = stateWiseArrayJSONObject.getString("active");
                       String  death = stateWiseArrayJSONObject.getString("deaths");
                       String  recovered = stateWiseArrayJSONObject.getString("recovered");
                       String  state = stateWiseArrayJSONObject.getString("state");
                       String  confirmed = stateWiseArrayJSONObject.getString("confirmed");
                       String  lastUpdated = stateWiseArrayJSONObject.getString("lastupdatedtime");

                       //Now the details of today cases for each individual state

                       String todayActive = stateWiseArrayJSONObject.getString("deltaconfirmed");
                       String todayDeath = stateWiseArrayJSONObject.getString("deltadeaths");
                       String todayRecovered = stateWiseArrayJSONObject.getString("deltarecovered");

                       //now pass all details to coronaItem class

                       CoronaItem coronaItem = new CoronaItem(state,death,active,recovered,confirmed,lastUpdated,todayDeath,todayRecovered,todayActive);
                       coronaItemArrayList.add(coronaItem);

                   }

                   //now we just have to set up the recyclerView to display  the content or data

                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,coronaItemArrayList);


                }
                // Try statement must have the catch statement.
                catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
    }

    private String getFormattedDate(String dateHeader){

        switch (dateHeader.substring(3,5)){

            case "01" :
                return dateHeader.substring(0,2)+" Jan";
            case "02" :
                return dateHeader.substring(0,2)+" Feb";
            case "03" :
                return dateHeader.substring(0,2)+" Mar";
            case "04" :
                return dateHeader.substring(0,2)+" Apr";
            case "05" :
                return dateHeader.substring(0,2)+" May";
            case "06" :
                return dateHeader.substring(0,2)+" Jun";
            case "07" :
                return dateHeader.substring(0,2)+" Jul";
            case "08" :
                return dateHeader.substring(0,2)+" Aug";
            case "09" :
                return dateHeader.substring(0,2)+" Sep";
            case "10" :
                return dateHeader.substring(0,2)+" Oct";
            case "11" :
                return dateHeader.substring(0,2)+" Nov";
            case "12" :
                return dateHeader.substring(0,2)+" Dec";

            default:
                return null;

        }
    }
}
