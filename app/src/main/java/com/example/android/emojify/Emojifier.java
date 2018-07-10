package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class Emojifier {

    final static private String TAG = Emojifier.class.getSimpleName();

    // TODO (1): Create a Java class called Emojifier
    // TODO (2): Create a static method in the Emojifier class called detectFaces() which detects and logs the number of faces in a given bitmap.

    public static void detectFaces(Context context, Bitmap bitmap) {

        // Detect number of faces in image.  If no faces, show toast 'No Faces Detected'.
        // https://youtu.be/J0IxySlL6Ig?t=16

        // https://developers.google.com/vision/android/detect-faces-tutorial

        FaceDetector detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                // .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .build();

        // if (detector.isOperational()) {

            Frame frame = new Frame.Builder()
                    .setBitmap(bitmap)
                    .build();

            SparseArray<Face> faces = detector.detect(frame);

            Log.i(TAG, "detectFaces: Number of faces: [" + faces.size() + "]");
            if (faces.size() == 0) {
                Toast.makeText(context, R.string.msg_no_faces, Toast.LENGTH_SHORT).show();
            }

            detector.release();

//        } else {
//            Log.w(TAG, "detectFaces: Face detector is not operational!");
//        }
    }

}
