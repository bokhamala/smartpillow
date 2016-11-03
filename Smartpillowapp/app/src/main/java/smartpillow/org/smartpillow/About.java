package smartpillow.org.smartpillow;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Daniel on 10/24/2016.
 */
public class About extends Fragment 
{
    private View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
        myView = inflater.inflate(R.layout.about, container, false);
        return myView;
    }
}
