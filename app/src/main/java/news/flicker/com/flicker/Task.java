package news.flicker.com.flicker;


import android.content.Context;
import android.os.AsyncTask;


public class Task extends AsyncTask<String, String, String> {

	private Connections conn_instance = Connections.getInstance();
	private String result;
	private FlickerActivity flickerActivity;
	private Context context;
	private MyProgressDialog_Splash myProgressDialog;

	Task(Context context){
		flickerActivity = (FlickerActivity) context;
		this.context = context;
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		result =  conn_instance.searchConnection(arg0[0]);
		return result;
	}

   @Override
   protected void onPreExecute() {
	// TODO Auto-generated method stub
	super.onPreExecute();
	   myProgressDialog = new MyProgressDialog_Splash(context);
	   myProgressDialog.setCancelable(false);
//	pDialog.setMessage(this.cx.getResources().getString(R.string.transactionINProccess));
	   myProgressDialog.show();
   }
   
   @Override

   protected void onPostExecute(String result) {
	// TODO Auto-generated method stub
	super.onPostExecute(result);
	   flickerActivity.action(result);
	   myProgressDialog.dismiss();
   }
   @Override

   protected void onProgressUpdate(String... values) {
	// TODO Auto-generated method stub
	super.onProgressUpdate(values);
}

}
