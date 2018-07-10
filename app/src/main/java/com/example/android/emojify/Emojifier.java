/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import java.util.Locale;

class Emojifier {

    private static final String TAG = Emojifier.class.getSimpleName();

    /**
     * Method for detecting faces in a bitmap.
     *
     * @param context The application context.
     * @param picture The picture in which to detect the faces.
     */
    static void detectFaces(Context context, Bitmap picture) {

        // Create the face detector, disable tracking and enable classifications
        FaceDetector detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .build();

        // Build the frame
        Frame frame = new Frame.Builder().setBitmap(picture).build();

        // Detect the faces
        SparseArray<Face> faces = detector.detect(frame);

        // Log the number of faces
        Log.d(TAG, "detectFaces: number of faces = " + faces.size());

        // If there are no faces detected, show a Toast message
        if(faces.size() == 0){
            Toast.makeText(context, R.string.no_faces_message, Toast.LENGTH_SHORT).show();
        } else {
            // TODO (2): Iterate through the faces, calling getClassifications() for each face.
            for (int i = 0; i < faces.size(); i++) {
                getClassifications(faces.valueAt(i), context);
            }
        }

        // Release the detector
        detector.release();
    }

    // TODO (1): Create a static method called getClassifications() which logs the probability of each eye being open and that the person is smiling.
    // https://youtu.be/WJuSR0jvIcc

    private static void getClassifications(Face face, Context context) {
        final String msg = "Face: "
                + "Left Eye " + String.format(Locale.getDefault(), "%."+ 1 +"f",face.getIsLeftEyeOpenProbability() * 100)+"%"
                + ", "
                + "Right Eye " + String.format(Locale.getDefault(), "%."+ 1 +"f",face.getIsRightEyeOpenProbability() * 100)+"%"
                + ", "
                + "Smiling " + String.format(Locale.getDefault(), "%."+ 1 +"f",face.getIsSmilingProbability() * 100)+"%";
        Log.i(TAG, msg);
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
