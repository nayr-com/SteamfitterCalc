package rocks.unoriginality.ryan.steamfitter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Double.parseDouble;


public class RollingOffset extends ActionBarActivity implements View.OnClickListener {
    Button calculate;
    TextView A_Text,travelText,runText;
    EditText rollInput,setInput,angleInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_one);
        A_Text=(TextView) findViewById(R.id.A_E1);
        travelText=(TextView) findViewById(R.id.travelE1);
        runText=(TextView) findViewById(R.id.runE1);
        rollInput=(EditText) findViewById(R.id.rollTextInput);
        setInput=(EditText) findViewById(R.id.setTextInput);
        angleInput=(EditText) findViewById(R.id.angleTextInput);
        calculate=(Button) findViewById(R.id.calculateButtonE1);
        calculate.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_equation_one, menu);
        return true;
    }

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

    public void onClick(View v) {
        if(isEmpty(rollInput) || isEmpty(setInput)) {
            A_Text.setText("Invalid roll or set");
            travelText.setText("Invalid angle");
            runText.setText("Invalid angle");
        } else if(isEmpty(angleInput)){
            double roll=parseDouble(rollInput.getText().toString());
            double set=parseDouble(setInput.getText().toString());
            double A = Math.sqrt((roll * roll) + (set * set));
            A_Text.setText("A: "+Double.toString(A));
            travelText.setText("Invalid angle");
            runText.setText("Invalid angle");
        } else {
            double roll=parseDouble(rollInput.getText().toString());
            double set=parseDouble(setInput.getText().toString());
            double angle=parseDouble(angleInput.getText().toString());
            double A = Math.sqrt((roll * roll) + (set * set));
            double travel=A*(1.0/Math.sin(angle));
            double run=A*(1.0/Math.tan(angle));
            A_Text.setText("A: "+Double.toString(A));
            travelText.setText("Travel: "+Double.toString(travel));
            runText.setText("Run: "+Double.toString(run));
        }
    }
    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }
}
