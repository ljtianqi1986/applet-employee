package com.sk.meikelai.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.Button;
import com.sk.meikelai.R;

/**
 * 发送验证码按钮
 */

public class VerificationCodeButton extends Button {

    private static final int ONE_SECOND_IN_MILLIS = 1000;
    private static final int ONE_MIN_BY_SECOND = 60;
    private TimeCount mTimeCount;

    public VerificationCodeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTimeCount = new TimeCount(ONE_MIN_BY_SECOND * ONE_SECOND_IN_MILLIS, ONE_SECOND_IN_MILLIS);
    }

    public void startTimer() {
        mTimeCount.start();
    }

    public void cancelTimerRefresh() {
        mTimeCount.cancel();
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            setClickable(true);
            setText(getResources().getString(R.string.send_out));
        }

        @Override
        public void onTick(long millisUntilFinished) {
            setClickable(false);
            long time = millisUntilFinished / ONE_SECOND_IN_MILLIS;
            String timeStr = getContext().getString(R.string.timer_refresh, String.valueOf(time));
            setText(timeStr);
        }
    }
}
