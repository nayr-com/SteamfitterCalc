package rocks.unoriginality.ryan.steamfitter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Integer.*;


public class EquationOne extends ActionBarActivity implements View.OnClickListener {
    Button calculate;
    TextView answerText;
    EditText a,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_one);
        answerText=(TextView) findViewById(R.id.answerE1);
        a=(EditText) findViewById(R.id.editText);
        b=(EditText) findViewById(R.id.editText2);
        c=(EditText) findViewById(R.id.editText3);
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
        if(isEmpty(a)||isEmpty(b)||isEmpty(c)){
            answerText.setText("Blank input(s)");
        }else {
            int x=parseInt(a.getText().toString());
            int y=parseInt(b.getText().toString());
            int z=parseInt(c.getText().toString());
            int calculatedAnswer = x + y + z;
            answerText.setText(Integer.toString(calculatedAnswer));
        }
    }
    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }
}
