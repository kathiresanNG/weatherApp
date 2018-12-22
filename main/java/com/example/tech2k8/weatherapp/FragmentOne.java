package com.example.tech2k8.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentOne extends Fragment {

    private final String currentWeatherUrl="https://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22";

    private  TextView latView,lonView,descView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layoutView=inflater.inflate(R.layout.fragment_one,container,false);
        final TextView label = layoutView.findViewById(R.id.label_2);
        latView = layoutView.findViewById(R.id.lat);
        lonView = layoutView.findViewById(R.id.lon);
        descView = layoutView.findViewById(R.id.desc);

        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNetworkRequests();
                label.setText("Resquest is in progress");
            }
        });

        return layoutView;
    }

    private void handleNetworkRequests()
    {
        StringRequest request = new StringRequest(Request.Method.GET,
                currentWeatherUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                processJsonData(response);
                Log.i("FragmentOne","Success response "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("FragmentOne","Error response "+error.getMessage());
            }
        });
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                HashMap<String,String> map =new HashMap<>();
//                map.put("id","1245454");
//                map.put("appid","dshgksdgksdjgb");
//                return super.getParams();
//            }
//        }

//                ;

        Volley.newRequestQueue(getContext()).add(request);

    }

    private void processJsonData(String data)
    {
        try {
            JSONObject root =new JSONObject(data);
            JSONObject coordObj=root.getJSONObject("coord");
            double lon=coordObj.getDouble("lon");
            double lat =coordObj.getDouble("lat");


            latView.setText("Latitude : "+lat);
            lonView.setText("Longitude : "+lon);
            JSONArray weatherArr=root.getJSONArray("weather");
            for (int i=0;i<weatherArr.length();i++)
            {
                JSONObject posObj = weatherArr.getJSONObject(i);
                String desp=posObj.getString("description");
                descView.setText("Descp : "+desp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
