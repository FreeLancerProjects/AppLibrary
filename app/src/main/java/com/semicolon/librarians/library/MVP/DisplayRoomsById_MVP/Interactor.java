package com.semicolon.librarians.library.MVP.DisplayRoomsById_MVP;


import android.content.Context;

import com.semicolon.librarians.library.Models.CommonUsersData;

import java.util.List;

/**
 * Created by Delta on 22/01/2018.
 */

public interface Interactor {
    interface onCompleteListener
    {
        void onChatRoomUserSuccess(List<CommonUsersData> commonUsersData);
        void onFailed(String error);
    }
    void DisplayAllRooms(String currUserId , Context context, Interactor.onCompleteListener listener);


}
