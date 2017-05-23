
package news.flicker.com.flicker;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;


public class MyProgressDialog_Splash extends AlertDialog
{
    private Context context;
    public MyProgressDialog_Splash(Context context) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.progress_layout_splash);
    }
}
