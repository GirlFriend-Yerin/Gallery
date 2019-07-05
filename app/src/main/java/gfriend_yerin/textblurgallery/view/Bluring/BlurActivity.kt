package gfriend_yerin.textblurgallery.view.Bluring

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import gfriend_yerin.textblurgallery.R
import kotlinx.android.synthetic.main.activity_bulr.*

class BlurActivity : AppCompatActivity(), BlurContract.View {
    private val mContext: Context = this
    private lateinit var presenter: BlurContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulr)

        presenter = BlurPresenter()
        presenter.setView(this)

        presenter.initPageView(intent.getStringArrayListExtra("Items"))

        blur_recognize_button.setOnClickListener { RecogAsnyc().execute(blur_view_pager.currentItem) }
    }

    override fun updateAdapter(list: ArrayList<String>) {
        blur_view_pager.apply {
            adapter = BlurAdapter(mContext, R.layout.blur_item, list)
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    presenter.movePage(position + 1)
                }

            })
        }
    }

    override fun updatePage(pos: Int, max: Int) {
        blur_top_textview.text = String.format("%d / %d", pos, max)
    }

    override fun showRecognizeText(list: ArrayList<String>) {
        val builder = StringBuilder()

        list.forEach{ builder.append(it + '\n')}

        val dialog = AlertDialog.Builder(mContext)
            .setTitle("결과")
            .setMessage(builder.toString())
            .setPositiveButton("확인") { di, _ -> di.dismiss()}
            .create()

        dialog.show()
    }

    @SuppressLint("StaticFieldLeak")
    inner class RecogAsnyc : AsyncTask<Int, Void, Void?>(){

        override fun doInBackground(vararg p0: Int?): Void? {
            presenter.recognizeText(mContext, p0[0]!!)

            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)

            presenter.displayRecognizeList()
        }
    }

}
