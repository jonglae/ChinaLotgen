package gotopark.buster.chinaLotgen;

/**
 * Created by ravi on 20/02/18.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gotopark.buster.chinaLotgen.database.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private List<Note> notesList;

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dot;
        TextView timestamp;
        TextView lstext1;
        TextView MAgroup;

        TextView Rtext1;
        TextView Rtext2;
        TextView Rtext3;
        TextView Rtext4;
        TextView Rtext5;
        TextView Rtext6;
        TextView Rtext7;

        MyViewHolder(View view) {
            super(view);


            dot = view.findViewById(R.id.dot);
            lstext1 = view.findViewById(R.id.lsttxt1);
            MAgroup = view.findViewById(R.id.MAgroup);
            timestamp = view.findViewById(R.id.timestamp);

            Rtext1 = view.findViewById(R.id.Rtext1);
            Rtext2 = view.findViewById(R.id.Rtext2);
            Rtext3 = view.findViewById(R.id.Rtext3);
            Rtext4 = view.findViewById(R.id.Rtext4);
            Rtext5 = view.findViewById(R.id.Rtext5);
            Rtext6 = view.findViewById(R.id.Rtext6);
            Rtext7 = view.findViewById(R.id.Rtext7);
        }
    }


    NotesAdapter(Context context, List<Note> notesList) {
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = notesList.get(position);
        String[] res = note.getNote().split(",");

        holder.lstext1.setText(note.getAlot());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));
        holder.MAgroup.setText(note.getMagroup());

        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(note.getTimestamp()));

        holder.Rtext1.setText(res[0]);
        holder.Rtext2.setText(res[1]);
        holder.Rtext3.setText(res[2]);
        holder.Rtext4.setText(res[3]);
        holder.Rtext5.setText(res[4]);
        holder.Rtext6.setText(res[5]);
        holder.Rtext7.setText(res[6]);

        holder.Rtext1.setBackgroundResource(R.drawable.ball3);
        holder.Rtext2.setBackgroundResource(R.drawable.ball3);
        holder.Rtext3.setBackgroundResource(R.drawable.ball3);
        holder.Rtext4.setBackgroundResource(R.drawable.ball3);
        holder.Rtext5.setBackgroundResource(R.drawable.ball3);
        holder.Rtext6.setBackgroundResource(R.drawable.ball3);
        holder.Rtext7.setBackgroundResource(R.drawable.ball2);
    }

    @Override
    public int getItemCount() {
        return notesList.size();

    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat fmtOut = new SimpleDateFormat(" MM月 dd日 (HH:mm) -");
            return fmtOut.format(date);
        } catch (ParseException ignored) {

        }

        return "";
    }
}
