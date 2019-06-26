package gotopark.buster.chinaLotgen.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import gotopark.buster.chinaLotgen.R;

public class OneCardAdapter extends ArrayAdapter<Card> {
    private static final String TAG = "OneCardAdapter";
    private List<Card> cardList = new ArrayList<Card>();

    public OneCardAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    static class CardViewHolder {

        TextView line1;
        TextView line2;
        TextView line3;

        TextView Rtext1;
        TextView Rtext2;
        TextView Rtext3;
        TextView Rtext4;
        TextView Rtext5;
        TextView Rtext6;
        TextView Rtext7;
    }

    @Override
    public void add(Card object) {

        cardList.add(object);

        super.add(object);

    }

    @Override
    public int getCount() {

        return this.cardList.size();

    }

    @Override
    public Card getItem(int index) {

        return this.cardList.get(index);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        String[] Lotnum = new String[7];
        String sLotnum;

        if (row == null) {


            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            assert inflater != null;
            row = inflater.inflate(R.layout.tab1_lot_list, parent, false);

            viewHolder = new CardViewHolder();

            viewHolder.line1 = (TextView) row.findViewById(R.id.line1);
            viewHolder.line3 = (TextView) row.findViewById(R.id.line3);

            viewHolder.Rtext1 = (TextView) row.findViewById(R.id.Rtext1);
            viewHolder.Rtext2 = (TextView) row.findViewById(R.id.Rtext2);
            viewHolder.Rtext3 = (TextView) row.findViewById(R.id.Rtext3);
            viewHolder.Rtext4 = (TextView) row.findViewById(R.id.Rtext4);
            viewHolder.Rtext5 = (TextView) row.findViewById(R.id.Rtext5);
            viewHolder.Rtext6 = (TextView) row.findViewById(R.id.Rtext6);
            viewHolder.Rtext7 = (TextView) row.findViewById(R.id.Rtext7);


            row.setTag(viewHolder);


        } else {


            viewHolder = (CardViewHolder) row.getTag();

        }

        Card card = getItem(position);
        assert card != null;
        sLotnum = card.getLine2().replace(" ", "");
        Lotnum = sLotnum.split(",");


        assert card != null;
        viewHolder.line1.setText(card.getLine1());

        viewHolder.Rtext1.setText(Lotnum[0]);
        viewHolder.Rtext2.setText(Lotnum[1]);
        viewHolder.Rtext3.setText(Lotnum[2]);
        viewHolder.Rtext4.setText(Lotnum[3]);
        viewHolder.Rtext5.setText(Lotnum[4]);
        viewHolder.Rtext6.setText(Lotnum[5]);
        viewHolder.Rtext7.setText(Lotnum[6]);

        viewHolder.Rtext1.setBackgroundResource(R.drawable.ball3);
        viewHolder.Rtext2.setBackgroundResource(R.drawable.ball3);
        viewHolder.Rtext3.setBackgroundResource(R.drawable.ball3);
        viewHolder.Rtext4.setBackgroundResource(R.drawable.ball3);
        viewHolder.Rtext5.setBackgroundResource(R.drawable.ball3);
        viewHolder.Rtext6.setBackgroundResource(R.drawable.ball3);
        viewHolder.Rtext7.setBackgroundResource(R.drawable.ball2);
        return row;

    }

}
