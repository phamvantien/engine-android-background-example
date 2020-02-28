package com.example.backgroundtest;

import android.content.Context;
import android.util.Log;
import java.util.List;

import com.factual.engine.FactualEngine;
import com.factual.engine.api.FactualConfigMetadata;
import com.factual.engine.api.FactualError;
import com.factual.engine.api.FactualException;
import com.factual.engine.api.FactualInfo;
import com.factual.engine.FactualClientReceiver;
import com.factual.engine.api.CircumstanceResponse;

public class ConsoleLoggingFactualClientReceiver extends FactualClientReceiver {
    @Override
    public void onContext(Context context) {
        // If you need fields from the context
    }

    @Override
    public void onInitialized() {
        try {
            FactualEngine.setUserJourneyReceiver(FooUserJourneyReceiver.class);
            FactualEngine.start();
        } catch (Exception e) {
            Log.e("engine", e.getMessage());
        }
    }

    @Override
    public void onStarted() {
        Log.i("engine", "Engine has started.");
    }

    @Override
    public void onStopped() {
        Log.i("engine", "Engine has stopped.");
    }

    @Override
    public void onError(FactualError e) {
        Log.i("engine", e.getMessage());
    }

    @Override
    public void onInfo(FactualInfo i) {
        Log.i("engine", i.getInfo());
    }

    @Override
    public void onSyncWithGarageComplete() {
        Log.i("engine", "Garage synced.");
    }

    @Override
    public void onConfigLoad(FactualConfigMetadata data) {
        Log.i("engine", "Garage config loaded at: " + data.getVersion());
    }

    @Override
    public void onDiagnosticMessage(String diagnosticMessage) {
        // Delivers structured diagnostic data that is helpful for Factual when evaluating performance
        // and diagnosing issues.
    }

    @Override
    public void onCircumstancesMet(List<CircumstanceResponse> responses) {
        for (CircumstanceResponse response : responses) {
            List<String> tags = response.getCircumstance().getTags();
            String circumstanceId = response.getCircumstance().getCircumstanceId();
            String message = "Circumstance " + circumstanceId + " with tags: " + tags + " met.";
            Log.i("engine", message);
        }
    }
}