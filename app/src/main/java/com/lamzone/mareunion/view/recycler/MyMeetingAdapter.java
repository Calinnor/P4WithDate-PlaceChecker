package com.lamzone.mareunion.view.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lamzone.mareunion.R;
import com.lamzone.mareunion.model.items.Meeting;
import com.lamzone.mareunion.view.event.DeleteMeetingEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 7/ extends with the class.viewholder
 * 8/implementation for 3 recycler methods
 * 9/implemente class.viewholder
 */
public class MyMeetingAdapter extends RecyclerView.Adapter<MyMeetingAdapter.MeetingViewHolder> {

    private List<Meeting> mMeetings;

    /**
     * 6/ recycleradpater creation:
     *
     * @param meetingsList once created from main. May be implemented correctly with the declaration of the list below (to create just after crete by main)
     */
    public MyMeetingAdapter(List<Meeting> meetingsList) {
        mMeetings = meetingsList;
    }


    /**
     * 13/implement methodes
     *
     * @return new class.viewholder(view)
     */
    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * 14/inflate model view
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_layout, parent, false);
        return new MeetingViewHolder(v);
    }

    /**
     * 15/ create view with displayed view with the integer position
     */
    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder meetingHolder, int position) {
        final Meeting currentItem = mMeetings.get(position);
        Glide.with(meetingHolder.mMeetingColorTag.getContext())
                .load(currentItem.getMeetingColorTag())
                .apply(RequestOptions.circleCropTransform())
                .into(meetingHolder.mMeetingColorTag);
        meetingHolder.mMeetingSubject.setText(currentItem.getMeetingSubject());
        meetingHolder.mMeetingHour.setText(currentItem.getMeetingHour());
        meetingHolder.mMeetingPlaceName.setText(currentItem.getMeetingPlaceName());
        meetingHolder.mMeetingParticipantsInformations.setText(currentItem.getMeetingParticipantsInformations());

        /**
         * evente delete 2/
         * listerner on class.holder.deletebutton..need to add it in viewholder.class ^^
         * glue eventbus on it
         * action in recycler configuration (here main activity)
         */
        meetingHolder.mDeleteMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(currentItem));
            }
        });
    }

    /**
     * @return 16/list size
     */
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    /**
     * 10/extends viewholder with recyclerview.viewholder
     * 11/reference xml
     */
    static class MeetingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.meeting_color_tag)
        ImageView mMeetingColorTag;
        @BindView(R.id.meeting_subject)
        TextView mMeetingSubject;
        @BindView(R.id.meeting_hour)
        TextView mMeetingHour;
        @BindView(R.id.meeting_place_name)
        TextView mMeetingPlaceName;
        @BindView(R.id.meeting_participants_informations)
        TextView mMeetingParticipantsInformations;
        @BindView(R.id.imageButton_delete_meeting)
        ImageButton mDeleteMeeting;

        /**
         * @param itemView 12/create constructor with xml id & super
         */
        MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void updateMeetings(List<Meeting> meetings) {
        this.mMeetings = meetings;
        notifyDataSetChanged();
    }

}
