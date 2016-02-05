package com.sample.recyclerviewbrownbag.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.recyclerviewbrownbag.R;
import com.sample.recyclerviewbrownbag.model.Email;

import java.util.List;

/**
 * Created by S. Reyes on 1/31/16.
 */
public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.EmailViewHolder> {

    private Context context;
    private List<Email> emails;

    public EmailAdapter(Context context, List<Email> emails) {
        this.context = context;
        this.emails = emails;
    }

    @Override
    public EmailAdapter.EmailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_email, parent, false);
        EmailViewHolder emailViewHolder = new EmailViewHolder(v);
        return emailViewHolder;
    }

    @Override
    public void onBindViewHolder(EmailAdapter.EmailViewHolder holder, int position) {
        final Email email = emails.get(position);
        holder.tvSender.setText(email.getSender());
        holder.tvContent.setText(email.getContent());
        holder.tvSubj.setText(email.getSubj());
        holder.tvDate.setText(email.getDate());

        if (email.getStatus() == Email.Status.Read) {
            holder.ivEmailStatus.
                    setImageResource(R.drawable.ic_mail_open);
        } else {
            holder.ivEmailStatus.
                    setImageResource(R.drawable.ic_mail_close);
        }

        holder.emailLayout.
                setBackgroundColor(context.getResources().getColor(R.color.item_email_selector));

    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    public void removeEmail(int position) {
        emails.remove(position);
        notifyItemRemoved(position);
    }

    public void addEmail(Email email, int position) {
        emails.add(position, email);
        notifyItemInserted(position);
    }

    public void openEmail(int position) {
        emails.get(position).setStatus(Email.Status.Read);
        notifyItemChanged(position);
    }

    class EmailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener{

        View emailLayout;
        ImageView ivEmailStatus;
        TextView tvSender, tvSubj, tvContent, tvDate;

        public EmailViewHolder(View itemView) {
            super(itemView);
            emailLayout = itemView.findViewById(R.id.email_item_layout);
            ivEmailStatus = (ImageView) itemView.findViewById(R.id.status_iv);
            tvSender = (TextView) itemView.findViewById(R.id.sender_tv);
            tvSubj = (TextView) itemView.findViewById(R.id.subj_tv);
            tvContent = (TextView) itemView.findViewById(R.id.content_tv);
            tvDate = (TextView) itemView.findViewById(R.id.date_tv);

            tvSubj.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    return false;
                }
            });
            emailLayout.setOnClickListener(this);
            emailLayout.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            openEmail(position);
            Email email = emails.get(position);
            new AlertDialog.Builder(context)
                    .setTitle(email.getSender())
                    .setMessage("Subject: " + email.getSubj() + "\n\n" + email.getContent())
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_email)
                    .show();
        }

        @Override
        public boolean onLongClick(View v) {
            final int position = getAdapterPosition();
            new AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.delete_title))
                    .setMessage(context.getString(R.string.delete_spiel))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            removeEmail(position);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_delete)
                    .show();
            return false;
        }
    }
}
