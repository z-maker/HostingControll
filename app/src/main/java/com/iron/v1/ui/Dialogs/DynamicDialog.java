package com.iron.v1.ui.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.iron.hostingcontroll.R;
import com.iron.v1.model.HostingEntry;

public class DynamicDialog {

    private Builder builder;

    public DynamicDialog(Builder b){
        this.builder=b;
    }

    public DynamicDialog build(){
        return builder.build();
    }

    public void cancel(){
        builder.cancel();
    }

    public void dismiss(){
        builder.dismiss();
    }

    public void show(){
        builder.show();
    }

    public interface DynamicDialogTriggerListener{
        void show();
        void dismiss();
        void cancel();
    }

    public static class Builder  implements DynamicDialogTriggerListener{

        private Context activity;
        private DynamicDialogListener pListener, nListener;
        private boolean cancel;
        private Dialog dialog;
        private Animation animation;

        private DynamicDialogFormListener pFormListener;

        public Builder(Context activity) {
            this.activity = activity;
        }

        public DynamicDialog build() {

            Button nBtn, pBtn;
            final TextInputLayout txtUsername,txtDomain, txtAdminEmail;
            final HostingEntry post = new HostingEntry();

            if(animation==Animation.POP)
                dialog=new Dialog(activity,R.style.PopTheme);
            else if(animation==Animation.SIDE)
                dialog=new Dialog(activity,R.style.SideTheme);
            else if(animation==Animation.SLIDE)
                dialog=new Dialog(activity,R.style.SlideTheme);
            else
                dialog=new Dialog(activity);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(R.layout.dynamic_dialog);

            nBtn = dialog.findViewById(R.id.negativeBtn);
            pBtn = dialog.findViewById(R.id.positiveBtn);

            txtUsername = dialog.findViewById(R.id.txtUsername);
            txtDomain = dialog.findViewById(R.id.txtDomain);
            txtAdminEmail = dialog.findViewById(R.id.txtEmail);


            pBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String username = txtUsername.getEditText().getText().toString();
                    String domain = txtDomain.getEditText().getText().toString();
                    String email = txtAdminEmail.getEditText().getText().toString();

                    post.setUsername(username);
                    post.setDomain(domain);
                    post.setAdminEmail(email);

                    post.setSuspended(false);
                    post.setStartDate(System.currentTimeMillis());

                    if (pFormListener!=null){
                        pFormListener.OnClick(post);
                    }

                }
            });

            nBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            //dialog.show();

            return new DynamicDialog(this);

        }


        public Builder setAnimation(Animation animation){
            this.animation=animation;
            return this;
        }

        //set Negative listener
        public Builder OnNegativeClicked(DynamicDialogListener nListener) {
            this.nListener = nListener;
            return this;
        }
        //set Negative listener
        public Builder OnPositiveClicked(DynamicDialogFormListener pListener) {
            this.pFormListener = pListener;
            return this;
        }

        public Builder isCancellable(boolean cancel) {
            this.cancel = cancel;
            return this;
        }

        @Override
        public void show() {
            dialog.show();
        }

        @Override
        public void dismiss() {
            dialog.dismiss();
        }

        @Override
        public void cancel() {
            dialog.cancel();
        }
    }


}
