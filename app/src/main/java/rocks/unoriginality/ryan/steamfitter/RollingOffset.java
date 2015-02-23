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
    TextView trueOffsetText,travelText,runText;
    EditText rollInput,setInput,angleInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_one);
        trueOffsetText =(TextView) findViewById(R.id.trueOffsetE1);
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
    /**
     * When the "Calculate" button is pressed
     * this method will check to see which numbers
     * the user typed in for the Rolling Offset equation
     * set. There are three different outputs that could
     * be genrated.
     *
     * Note this method uses the isEmpty method:
     * @see RollingOffset#isEmpty(android.widget.EditText)
     *
     * 1. If the roll or set is not provided then
     * no calculation will be complete and errors
     * will be shown to the user to indicate this.
     * If the user provides an angle without one or
     * both parameters errors will still be thrown
     * as those parameters are necessarry for all
     * calculations.
     *
     * 2. If roll and set are provided and angle
     * is not then the travel and run will not be
     * calculated and errors will be thrown for at
     * the output locations for these variables.
     * The true offset will still be calculated and
     * shown to the user.
     *
     * 3. If all three parameters are provided then
     * the run, travel, and true offset will be calculated
     * and given to the user.
     *
     * @param v specifies the view that was clicked.
     */
    public void onClick(View v) {
        if(isEmpty(rollInput) || isEmpty(setInput)) {
            trueOffsetText.setText("Invalid roll or set");
            travelText.setText("Invalid angle");
            runText.setText("Invalid angle");
        } else if(isEmpty(angleInput)){
            double roll=parseDouble(rollInput.getText().toString());
            double set=parseDouble(setInput.getText().toString());
            double A = Math.sqrt((roll * roll) + (set * set));
            trueOffsetText.setText("True offset: " + Double.toString(A));
            travelText.setText("Invalid angle");
            runText.setText("Invalid angle");
        } else {
            double roll=parseDouble(rollInput.getText().toString());
            double set=parseDouble(setInput.getText().toString());
            double angle=parseDouble(angleInput.getText().toString());
            double A = Math.sqrt((roll * roll) + (set * set));
            double travel=A*(1.0/Math.sin(Math.toRadians(angle)));
            double run=A*(1.0/Math.tan(Math.toRadians(angle)));

            trueOffsetText.setText("A: " + Double.toString(A));
            travelText.setText("Travel: "+Double.toString(travel));
            runText.setText("Run: "+Double.toString(run));
        }
    }

    /**
     * Checks to see if
     * @param myEditText the text input you want to check
     * @return a true/false that tells if
     * an input is empty
     */
    private boolean isEmpty(EditText myEditText) {
        return myEditText.getText().toString().trim().length() == 0;
    }
}
