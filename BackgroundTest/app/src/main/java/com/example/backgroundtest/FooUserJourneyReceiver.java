package com.example.backgroundtest;

import android.content.Context;
import android.util.Log;

import com.factual.engine.api.mobile_state.UserJourneyEvent;
import com.factual.engine.api.mobile_state.UserJourneyReceiver;
import com.factual.engine.api.mobile_state.UserJourneySpan;

import org.json.JSONException;

public class FooUserJourneyReceiver extends UserJourneyReceiver {
    @Override
    public void onContext(Context context) {
        // If you need fields from the context
    }

    @Override
    public void onUserJourneyEvent(UserJourneyEvent userJourneyEvent) {
        try {
            Log.i("engine", "Received User Journey event: " + userJourneyEvent.toJson().toString());
        } catch (JSONException e) {
            Log.e("engine", "Error with User Journey json");
        }
    }

    @Override
    public void onUserJourneySpan(UserJourneySpan userJourneySpan) {
        try {
            Log.i("engine", "Received User Journey span: " + userJourneySpan.toJson().toString());
        } catch (JSONException e) {
            Log.e("engine", "Error with User Journey span json");
        }
    }
}