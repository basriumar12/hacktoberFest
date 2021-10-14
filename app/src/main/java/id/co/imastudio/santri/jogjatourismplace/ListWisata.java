package id.co.imastudio.santri.jogjatourismplace;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.imastudio.santri.jogjatourismplace.adapter.JSONfunctions;
import id.co.imastudio.santri.jogjatourismplace.adapter.ListViewAdapter;

public class ListWisata extends AppCompatActivity {
    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
   public static String JUDUL = "nama_pariwisata";
    public static String ALMAT = "alamat_pariwisata";
    public static String DETAIL = "detail_pariwisata";
    public static String GAMBAR = "gambar_pariwisata";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_list_wisata);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        // Execute DownloadJSON AsyncTask

        new DownloadJSON().execute();
    }


    // DownloadJSON AsyncTask
   public class DownloadJSON extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ListWisata.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Ambil Data Dari Server");
            // Set progressdialog message
            mProgressDialog.setMessage("Sabar Ya . . .");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL("http://www.erporate.com/bootcamp/jsonBootcamp.php");

            try {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("data");

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    // Retrive JSON Objects
                    map.put("nama_pariwisata", jsonobject.getString("nama_pariwisata"));
                    map.put("alamat_pariwisata", jsonobject.getString("alamat_pariwisata"));
                    map.put("detail_pariwisata", jsonobject.getString("detail_pariwisata"));
                    map.put("gambar_pariwisata", jsonobject.getString("gambar_pariwisata"));
                    // Set the JSON Objects into the array
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(ListWisata.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
    @Override
    public void onBackPressed() {

        finish();

    }
}
