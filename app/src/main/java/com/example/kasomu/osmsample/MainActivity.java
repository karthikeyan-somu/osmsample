package com.example.kasomu.osmsample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateJobCards();
        populateListView();
        regiseterClickCallback();
    }

    private void populateJobCards() {
        myJobCards.add(new jobCard(R.mipmap.ic_launcher, "Lkarthik - This is for testing. Long text", "karthik@oracle.com", "house address", "962064032", "male"));
        myJobCards.add(new jobCard(R.mipmap.ic_launcher, "abishek", "abishek@oracle.com", "new house address", "278617286", "male"));
    }
    private void populateListView() {
        ArrayAdapter<jobCard> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.jobCardListView);
        list.setAdapter(adapter);
    }

    private void regiseterClickCallback() {
        ListView list = (ListView)findViewById(R.id.jobCardListView);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jobCard jobCardSelected = myJobCards.get(position);
                String message = "you clicked postition "+position+" whose name is "+jobCardSelected.getName();
                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
    private class MyListAdapter extends ArrayAdapter<jobCard>
    {
        private MyListAdapter()
        {
            super(MainActivity.this, R.layout.item_view, myJobCards);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView==null)
            {
                itemView = getLayoutInflater().inflate(R.layout.item_view,parent,false);
            }
            //Find the jobcard to work with
            jobCard currentJobCard = myJobCards.get(position);

            //Fill the view
            //Fill the image
            ImageView image = (ImageView)itemView.findViewById(R.id.job_image);
            image.setImageResource(currentJobCard.getId());

            //For text name
            TextView name = (TextView)itemView.findViewById(R.id.job_name);
            name.setText(currentJobCard.getName());

            //For text email
            TextView email = (TextView)itemView.findViewById(R.id.job_email);
            email.setText(currentJobCard.getEmail());

            //For text Address
            TextView address = (TextView)itemView.findViewById(R.id.job_Address);
            address.setText(currentJobCard.getAddress());

            //For text gender
            TextView gender = (TextView)itemView.findViewById(R.id.job_gender);
            gender.setText(currentJobCard.getGender());
            return itemView;
        }
    }
   /* login() {
        List<String> dirtyData = getUserData();
        List<String> validatedData = validateUserData(users);
        boolean isauthenticated = isUserAuthenticated(validatedData);
        String user = validatedData.username
        if (isauthenticated) {
            boolean isSesssionOpen = checkIfSessionOpen(user);
            if (isSesssionOpen) {
                closePrevSession(user);
            }
            createNewSessoion(user);
            showSuccessDialog();
        }
        else {
            showErrorDialog();
        }

    }

    getUserData() {
        request = getCurrentRequest();
        requestData = getDataFromRequest(request);
        return requestData;
    }

    getCurrentRequest() {
        String url = getUrl();
        http.get(url)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        seekers = callmethod1();
        if (seekers.length < 1) {
            callValidateSeekers();
        }
        else {
            showErrorDialog();
        }
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private List<jobCard> myJobCards = new ArrayList<jobCard>();



    public void getData(View view)
    {
        new TheTask().execute();
    }
    class TheTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            HttpClient client = new DefaultHttpClient();
            String url = "http://api.androidhive.info/contacts/";
            HttpGet request = new HttpGet(url);
            try {
                HttpResponse response = client.execute(request);
                Log.d("Response : ", response.toString());
                InputStream is = response.getEntity().getContent();
                try {
                    String response1;
                    BufferedReader reader = new BufferedReader(new InputStreamReader( is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    is.close();
                    response1 = sb.toString();
                    Log.d("Latest response : ",response1);
                }
                catch (Exception e)
                {
                    Log.e("Buffer Error", "Error: " + e.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public void setData(View view) throws IOException {
            InputStream content = null;
            String url = "http://api.androidhive.info/contacts/";
            HttpGet httpGet = new HttpGet(url);
            HttpClient httpclient = new DefaultHttpClient();
            // Execute HTTP Get Request
            HttpResponse response = httpclient.execute(httpGet);
            Log.d("Response : ", response.toString());
            InputStream is = response.getEntity().getContent();
            try {
                String response1;
                BufferedReader reader = new BufferedReader(new InputStreamReader( is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                is.close();
                response1 = sb.toString();
                Log.d("Latest response : ",response1);
            }
            catch (Exception e)
            {
                Log.e("Buffer Error", "Error: " + e.toString());
            }
    }
}
