package gfriend_yerin.textblurgallery.view.Gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import gfriend_yerin.textblurgallery.R
import gfriend_yerin.textblurgallery.data.GalleryDataManager
import gfriend_yerin.textblurgallery.data.obj.PhotoVO
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity(), GalleryContract.View {

    private lateinit var presenter: GalleryContract.Presenter
    private val mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        presenter = GalleryPresenter(GalleryDataManager.getInstance(mContext))
        presenter.setView(this)

        presenter.loadPhotoList()
    }

    override fun updateCountUI(cnt: Int) {
        gallery_selected_text.text = String.format("%d개 선택", cnt)
    }

    override fun initAdapter(list : ArrayList<PhotoVO>) {
        gallery_recycler.apply {
            adapter = GalleryAdapter(mContext, R.layout.gallery_item, list)
            layoutManager = GridLayoutManager(mContext, 3)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                GridDividerDecoration(
                    mContext,
                    R.drawable.gallery_item_divider
                )
            )
        }
    }

    override fun updateAdapter() {
        gallery_recycler.adapter!!.notifyDataSetChanged()
    }

    override fun updateAdapter(pos: Int) {
        gallery_recycler.adapter!!.notifyItemChanged(pos)
    }
}
