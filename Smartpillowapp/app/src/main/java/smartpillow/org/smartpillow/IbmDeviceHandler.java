package smartpillow.org.smartpillow;
import java.io.File;
import java.util.Properties;
import com.google.gson.JsonObject;
import com.ibm.iotf.client.device.DeviceClient;
import com.ibm.iotf.client.device.Command;
import com.ibm.iotf.client.device.CommandCallback;
import com.ibm.iotf.client.device.DeviceClient;
import org.eclipse.paho.client.mqttv3.MqttException;
/**
 * Created by Armando on 10/19/2016.
 */
public class IbmDeviceHandler 
{

    public void sendMessage()
    {
        //Provide the device specific data, as well as Auth-key and token using Properties class
        Properties options = new Properties();
        
        options.setProperty("org", "ngw3xy");
        options.setProperty("type", "Nexus6P");
        options.setProperty("id", "nexus6p");
        options.setProperty("auth-method", "token");
        options.setProperty("auth-token", "quesadillas");
        
        DeviceClient myClient = null;
        try 
        {
            //Instantiate the class by passing the properties file
            myClient = new DeviceClient(options);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        //Connect to the Watson IoT Platform
        try 
        {
            myClient.connect();
        } catch (MqttException e) 
        {
            e.printStackTrace();
        }
        //Generate a JSON object of the event to be published
        JsonObject event = new JsonObject();
        event.addProperty("status", "connect");

        //Registered flow allows 0, 1 and 2 QoS
        myClient.publishEvent("status", event);
        System.out.println("SUCCESSFULLY POSTED......");
    }
}
