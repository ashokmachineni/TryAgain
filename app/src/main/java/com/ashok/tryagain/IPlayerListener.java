package com.ashok.tryagain;

/**
 * Created by ashok on 9/29/16.
 */
public interface IPlayerListener {

    void onBufferingStart();
    void onBufferingComplete();
    void onSetDuration(long durationMs);
    void onUpdateProgress(float percentComplete);
}
