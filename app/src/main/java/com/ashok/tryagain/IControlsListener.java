package com.ashok.tryagain;

/**
 * Created by ashok on 9/29/16.
 */
public interface IControlsListener {

    void onPause();
    void onPlay();
    void onSeekTo(float percentComplete);
    void onControlsHidden();
    void onControlsShown();
}
