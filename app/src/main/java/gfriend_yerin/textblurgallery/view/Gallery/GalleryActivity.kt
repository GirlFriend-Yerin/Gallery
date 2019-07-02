package gfriend_yerin.textblurgallery.view.Gallery

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import gfriend_yerin.textblurgallery.R
import gfriend_yerin.textblurgallery.data.GalleryDataManager
import gfriend_yerin.textblurgallery.data.obj.PhotoVO
import gfriend_yerin.textblurgallery.view.Bluring.BulrActivity
import gfriend_yerin.textblurgallery.view.OnItemClickListener
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.android.synthetic.main.activity_gallery.view.*

class GalleryActivity : AppCompatActivity(), GalleryContract.View {

    private lateinit var presenter: GalleryContract.Presenter
    private val mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        val actionBar = supportActionBar

        presenter = GalleryPresenter(GalleryDataManager.getInstance(mContext))
        presenter.setView(this)

        presenter.initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.gallery, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId){
            R.id.action_done -> {
                val intent = Intent(this, BulrActivity::class.java)
                startActivity(intent)
            }
        }


        return super.onOptionsItemSelected(item)
    }

    override fun updateCountUI(cnt: Int) {
        gallery_selected_text.text = String.format("%d개 선택", cnt)
    }

    override fun initAdapter(list : ArrayList<PhotoVO>) {
        gallery_recycler.apply {
            adapter = GalleryAdapter(mContext, R.layout.gallery_item, list).apply {
                setOnItemClickListener(object : OnItemClickListener{
                    override fun OnItemClick(photoViewHolder: GalleryAdapter.PhotoViewHolder, position: Int) {
                        list[position].selected = !list[position].selected

                        if (list[position].selected) presenter.countUp() else presenter.countDown()

                        notifyItemChanged(position)
                    }
                })
            }
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
