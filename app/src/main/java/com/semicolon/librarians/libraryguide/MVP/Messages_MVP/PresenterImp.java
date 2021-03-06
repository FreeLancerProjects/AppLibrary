package com.semicolon.librarians.libraryguide.MVP.Messages_MVP;

import android.content.Context;

import com.semicolon.librarians.libraryguide.Models.MessageModel;

import java.util.List;


public class PresenterImp implements Presenter, Interactor.onCompleteListener {
    ViewData viewData;
    Context context;
    Interactor interactor;

    public PresenterImp(ViewData viewData, Context context) {
        this.viewData = viewData;
        this.context = context;
        interactor = new InteractorImp();


    }

    @Override
    public void getMessages(String currUser_userName, String chatUser_userName) {
        interactor.getMessages(currUser_userName,chatUser_userName,context,this);
    }

    @Override
    public void sendMessage(String senderid, String receiverid, String sender_name, String receiver_name, String message, String chat_user_token, String sender_photo) {
        interactor.sendMessage(senderid,receiverid,sender_name,receiver_name,message,chat_user_token,sender_photo,context,this);
    }


    @Override
    public void onMessagesSuccess(List<MessageModel> messageModelList) {
        if (viewData!=null)
        {
            viewData.onMessagesSuccess(messageModelList);
        }
    }

    @Override
    public void onMessageSendSuccess() {
        if (viewData!=null)
        {
            viewData.onMessageSendSuccess();
        }
    }

    @Override
    public void onFailed(String error) {
        if (viewData!=null)
        {
            viewData.onFailed(error);
        }
    }
}
