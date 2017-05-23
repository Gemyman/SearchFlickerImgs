package news.flicker.com.flicker;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Created by Gemy on 5/22/2017.
 */

public class FlickerActivity extends Activity implements UIAction {

    private EditText edit;
    private String searchKeywords;
    private Gson gson =new Gson();
    private Task task;
    private String[] imgLinks;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);
        button = (Button) findViewById(R.id.buttonPane2);

        super.onCreate(savedInstanceState);
    }


    public void search(View view){

        searchKeywords = edit.getText().toString();
        task = new Task(FlickerActivity.this);
        task.execute(searchKeywords);

    }

    @Override
    public void action(String result) {
        FlickerModel flickerModel = null;
        try{
            flickerModel = gson.fromJson(result, FlickerModel.class);
        if(flickerModel!=null) {
            imgLinks = new String[flickerModel.getPhotos().getPhoto().length];
            for (int i = 0; i < flickerModel.getPhotos().getPhoto().length; i++) {
                imgLinks[i] = "https://farm" + flickerModel.getPhotos().getPhoto()[i].getFarm() + ".staticflickr.com/" + flickerModel.getPhotos().getPhoto()[i].getServer() + "/" + flickerModel.getPhotos().getPhoto()[i].getId() + "_" + flickerModel.getPhotos().getPhoto()[i].getSecret() + ".jpg";
            }

            mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MyAdapter(imgLinks,this);
            mRecyclerView.setAdapter(mAdapter);

            button.setEnabled(true);
        }
            else {
            Toast.makeText(this, "connection problem", Toast.LENGTH_LONG).show();
        }
    }catch(Exception e){
            Toast.makeText(this, "connection problem", Toast.LENGTH_LONG).show();
        }
try {
    SQLiteDatabase mydatabase = openOrCreateDatabase("FlickerDB", MODE_PRIVATE, null);
    mydatabase.execSQL("CREATE TABLE IF NOT EXISTS ImgItems(ImgTitle VARCHAR,ImgLink VARCHAR);");
    for (int i = 0; i < imgLinks.length; i++) {
        mydatabase.execSQL("INSERT INTO ImgItems VALUES('" + flickerModel.getPhotos().getPhoto()[i].getOwner() + "','" + imgLinks[i] + "');");
    }
}catch(Exception e){
    Toast.makeText(this, "storage problem", Toast.LENGTH_LONG).show();
}

    }

    public void retrieve(View v){
        SQLiteDatabase mydatabase = openOrCreateDatabase("FlickerDB",MODE_PRIVATE,null);

        Cursor resultSet = mydatabase.rawQuery("Select * from ImgItems",null);
//        resultSet.moveToFirst();
//            Toast.makeText(this, ""+resultSet.getString(0)+"=="+resultSet.getString(1), Toast.LENGTH_LONG).show();
        while (resultSet.moveToNext()){
            Toast.makeText(this, ""+resultSet.getString(0)+"=="+resultSet.getString(1), Toast.LENGTH_LONG).show();
        }
    }

}
