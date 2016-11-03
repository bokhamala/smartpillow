package smartpillow.org.smartpillow;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by Armando on 10/17/2016.
 */
public class Home extends Fragment
{
    private View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.home, container, false);
        setText();
        return myView;
    }

    private void setText(  ) 
    {
        TextView percentageTextView = (TextView) myView.findViewById(R.id.percentage);
        percentageTextView.setText(MainActivity.percentage + "");
    }
}
