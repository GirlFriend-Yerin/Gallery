package gfriend_yerin.textblurgallery.util

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer

class TextRecogUtils(
    private val context: Context
) {

    fun findText(imagePath: String, findText : String) {
        val visionImage: FirebaseVisionImage = FirebaseVisionImage.fromFilePath(context, Uri.parse(imagePath))
        val detector: FirebaseVisionTextRecognizer = FirebaseVision.getInstance().onDeviceTextRecognizer

        detector
            .processImage(visionImage)
            .addOnSuccessListener{ processTextRecognizeResult(it) }
            .addOnFailureListener{ Log.e("Detector","Recognize Fail")}
    }

    private fun processTextRecognizeResult(visionText : FirebaseVisionText){

        for (item in visionText.textBlocks)
            Log.e("Detector", item.text)

    }

}