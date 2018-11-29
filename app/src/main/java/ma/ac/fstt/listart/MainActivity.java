package ma.ac.fstt.listart;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Etudiant> lstEtd;
    ListView            lst;
    EtdAdapter          ada;
    EditText            etPm, etNm;

    SharedPreferences   prf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        etPm = findViewById(R.id.etPm);
        etNm = findViewById(R.id.etNm);

        lst  = findViewById(R.id.lstLayout);
        lstEtd = new ArrayList<>();
        prf = this.getSharedPreferences("SaveList",MODE_PRIVATE);

        if (!getPrefs() )
        initLst();

        ada = new EtdAdapter(getApplicationContext(), lstEtd);

        lst.setAdapter(ada);

    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
        savePrefs();
        Toast.makeText(getApplicationContext(), "Prefs saved !", Toast.LENGTH_LONG).show();
    }

    private boolean getPrefs()
    {
        String ser = prf.getString("etudiants","");
        if (ser == "")
            return  false;

            try {
                lstEtd = (ArrayList<Etudiant>)ObjectSerializer.deserialize(ser);
                return true;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

       return  false;

    }

    private void savePrefs() {

        try {
            SharedPreferences.Editor edt = prf.edit();
            String ser = ObjectSerializer.serialize(ada.getEtudiants());
            edt.putString("etudiants",ser);
            edt.apply();

        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }
    private void initLst()
    {
        lstEtd.add(new Etudiant("Ali"   ,"Drissi"));
        lstEtd.add(new Etudiant("Fatima" ,"Alami"));
    }

    public void onAdd(View v) {

        Etudiant etd =  new Etudiant(
                etNm.getText().toString(),
                etPm.getText().toString() );

        ada.add(etd);
    }
    public void onSave(View v) {

        savePrefs();

    }

}
