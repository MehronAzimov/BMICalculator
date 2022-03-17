package tj.ibt.bmicalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private TextView age, weight, height_value;
    private AppCompatSeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        seekBar = findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(listener);
        height_value = findViewById(R.id.height_value);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }

    private SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromuser) {
            height_value.setText(String.format("%d Cm", progress));

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void decreaseAge(View view) {

        if (Integer.parseInt(age.getText().toString()) > 1) {

            int age_value = Integer.parseInt(age.getText().toString()) - 1;
            age.setText(String.valueOf(age_value));
        }
    }

    public void increaseAge(View view) {


        if (Integer.parseInt(age.getText().toString()) > 0) {

            int age_value = Integer.parseInt(age.getText().toString()) + 1;
            age.setText(String.valueOf(age_value));
        }
    }

    public void decreaseWeight(View view) {

        if (Integer.parseInt(weight.getText().toString()) > 1) {

            int weight_value = Integer.parseInt(weight.getText().toString()) - 1;
            weight.setText(String.valueOf(weight_value));
        }
    }

    public void increaseWeight(View view) {

        if (Integer.parseInt(weight.getText().toString()) > 0) {

            int weight_value = Integer.parseInt(weight.getText().toString()) + 1;
            weight.setText(String.valueOf(weight_value));
        }
    }

    public void showResult(View view) {

        int get_age = Integer.parseInt(age.getText().toString());
        int weight_value = Integer.parseInt(weight.getText().toString());
        double get_height =(double) seekBar.getProgress() / 100;

        int bmi = weight_value / (int) (get_height * get_height);

        showBMI(bmi);

    }

    private void showBMI(int bmi) {

        if (bmi < 18.5){

            showCustomDialog(R.drawable.underweight, "Under Weight","use Some Healthy Fat");
        }
        else if (bmi > 18.5 && bmi < 29.9) {

            showCustomDialog(R.drawable.normalweight, "Normal Weight","Your Healthy");
        }
        else {

            showCustomDialog(R.drawable.overweight, "Over Weight","Do Some Exercise");
        }


    }

    private void showCustomDialog(int id, String title, String tip) {

        ViewGroup viewGroup = findViewById(R.id.content);
        View view = LayoutInflater.from(this).inflate(R.layout.custome_dialog, viewGroup, false);
        AppCompatButton ok = view.findViewById(R.id.ok);
        ImageView imageView = view.findViewById(R.id.image_view);
        TextView result_title = view.findViewById(R.id.result_title);
        TextView tips = view.findViewById(R.id.tips);
        imageView.setImageResource(id);
        result_title.setText(title);
        tips.setText(tip);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();

            }
        });
    }
}