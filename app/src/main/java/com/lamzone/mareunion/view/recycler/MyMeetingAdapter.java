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

public class MyMeetingAdapter extends RecyclerView.Adapter<MyMeetingAdapter.MeetingViewHolder> {

    private List<Meeting> mMeetings;

    public MyMeetingAdapter(List<Meeting> meetingsList) {
        mMeetings = meetingsList;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_layout, parent, false);
        return new MeetingViewHolder(v);
    }

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

        meetingHolder.mDeleteMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(currentItem));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

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

        MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void updateMeetings(List<Meeting> meetings){
        this.mMeetings = meetings;
        notifyDataSetChanged();
    }
}
