package gfriend_yerin.textblurgallery.view.Splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat
import gfriend_yerin.textblurgallery.view.Gallery.GalleryActivity

class InitActivity : AppCompatActivity() {

    companion object {
        private const val PER_REQ = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermission()
    }

    private fun checkPermission() {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

        ActivityCompat.requestPermissions(this, permissions,
            PER_REQ
        )
    }

    private fun goMainActivity() {
        Handler().postDelayed({
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PER_REQ) {
            for (res in grantResults)
                if (res == PackageManager.PERMISSION_DENIED) finish()

            goMainActivity()
        }
    }

    override fun onBackPressed() {
    }

}

