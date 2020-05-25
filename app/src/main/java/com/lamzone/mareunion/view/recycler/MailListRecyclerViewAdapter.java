package com.lamzone.mareunion.view.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareunion.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MailListRecyclerViewAdapter extends RecyclerView.Adapter<MailListRecyclerViewAdapter.ViewHolderMail> {

    @NonNull
    @Override
    public ViewHolderMail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_mail, parent, false);
        return new ViewHolderMail(v, mMailsToDelete);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMail holder, int position) {
        String mail = mMails.get(position);
        holder.updateMailList(mail);
    }

    @Override
    public int getItemCount() {
        return mMails.size();
    }

    public interface MailsToDelete {
        void clickToDelete(int position);
    }

    private List<String> mMails;
    private MailsToDelete mMailsToDelete;

    public MailListRecyclerViewAdapter(List<String> mMails, MailsToDelete mailToDelete) {
        this.mMails = mMails;
        this.mMailsToDelete = mailToDelete;
    }

    public static class ViewHolderMail extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.enter_e_mail)
        TextView mMail;
        @BindView(R.id.e_mail_delete_button)
        ImageButton mDeleteButton;
        private MailsToDelete mMailsToDelete;

        ViewHolderMail(@NonNull View itemView, MailsToDelete mailsToDelete) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mMailsToDelete = mailsToDelete;
            mDeleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mMailsToDelete.clickToDelete(getAdapterPosition());
        }

        void updateMailList(String mail) {
            mMail.setText(mail);
        }


    }
}
