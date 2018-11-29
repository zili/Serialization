package ma.ac.fstt.listart;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EtdAdapter extends ArrayAdapter<Etudiant>
{

    public ArrayList<Etudiant> getEtudiants()
    {
        ArrayList<Etudiant> lst = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            lst.add(getItem(i));
        }

        return  lst;
    }

    public EtdAdapter(Context context, List<Etudiant> objects)
    {
        super(context, R.layout.item_etd, objects);

    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent)
    {
        LayoutInflater li = LayoutInflater.from(getContext());
        convertView = li.inflate(R.layout.item_etd, parent, false);


        Etudiant etd = getItem(position);

        TextView nm = convertView.findViewById(R.id.nm);
        TextView pm = convertView.findViewById(R.id.pm);

        nm.setText(etd.getNom());
        pm.setText(etd.getPrenom());

        return convertView;
    }
}
