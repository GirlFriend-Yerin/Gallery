package gfriend_yerin.textblurgallery.util

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import java.util.concurrent.CountDownLatch

object TextRecogUtils{

    fun findText(context : Context, imagePath: String, findText : String) : ArrayList<String>? {
        var res : ArrayList<String>? = null

        val visionImage = FirebaseVisionImage.fromBitmap(BitmapFactory.decodeFile(imagePath))

        val detector: FirebaseVisionTextRecognizer = FirebaseVision.getInstance().onDeviceTextRecognizer

        val latch = CountDownLatch(1)

        detector
            .processImage(visionImage)
            .addOnCompleteListener {
                if(it.isSuccessful)
                    res = processTextRecognizeResult(it.result)
                else
                    Log.e("Detector","Recognize Fail")
                latch.countDown()
            }

        latch.await()

        return res
    }

    private fun processTextRecognizeResult(visionText : FirebaseVisionText?) : ArrayList<String>{
        val res = ArrayList<String>()


        for (item in visionText!!.textBlocks) {
            res.add(item.text)
            Log.e("Detector", item.text)
        }

        return res
    }

}