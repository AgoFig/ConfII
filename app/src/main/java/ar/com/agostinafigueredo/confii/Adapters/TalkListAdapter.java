package ar.com.agostinafigueredo.confii.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ar.com.agostinafigueredo.confii.Entities.Talk;
import ar.com.agostinafigueredo.confii.R;

/**
 * Created by ago on 25/06/15.
 */
public class TalkListAdapter extends RecyclerView.Adapter<TalkListAdapter.Holder> {

    private List<Talk> talks = new ArrayList<Talk>();

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_talk, viewGroup, false);

        Holder holder = new Holder(item);

        return holder;

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        final Talk talk = this.talks.get(position);
        holder.title.setText(talk.getTitle());

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lo que va a pasar cuando se cliquee un item de la charla.
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.talks.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public View item;
        public TextView title;
        public TextView speaker;

        public Holder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            this.title = (TextView) itemView.findViewById(R.id.label_talk);
            this.speaker = (TextView) itemView.findViewById(R.id.label_speaker);

        }

        public void setOnClickListener(View.OnClickListener clickListener) {
            this.item.setOnClickListener(clickListener);
        }

    }
}
