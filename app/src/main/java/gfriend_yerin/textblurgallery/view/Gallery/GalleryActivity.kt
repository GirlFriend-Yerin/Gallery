package gfriend_yerin.textblurgallery.view.Gallery

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import gfriend_yerin.textblurgallery.R
import gfriend_yerin.textblurgallery.data.GalleryDataManager
import gfriend_yerin.textblurgallery.data.obj.PhotoVO
import gfriend_yerin.textblurgallery.view.Bluring.BlurActivity
import gfriend_yerin.textblurgallery.view.OnItemClickListener
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity(), GalleryContract.View {

    private lateinit var presenter: GalleryContract.Presenter
    private val mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        presenter = GalleryPresenter(GalleryDataManager.getInstance(mContext))
        presenter.setView(this)

        presenter.initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.gallery, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.action_done -> {
                val items = presenter.getSelectedList()

                if (items.isEmpty())
                    Toast.makeText(mContext, "선택된 이미지가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                else {
                    val intent = Intent(this, BlurActivity::class.java)
                    intent.putExtra("Items", items)
                    startActivity(intent)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun updateCountUI(cnt: Int) {
        gallery_selected_text.text = String.format("%d개 선택", cnt)
    }

    override fun initAdapter(list: ArrayList<PhotoVO>) {
        gallery_recycler.apply {
            adapter = GalleryAdapter(mContext, R.layout.gallery_item, list).apply {
                setOnItemClickListener(object : OnItemClickListener {
                    override fun OnItemClick(photoViewHolder: GalleryAdapter.PhotoViewHolder, position: Int) {
                        list[position].selected = !list[position].selected

                        if (list[position].selected) {
                            presenter.addItem(position)
                            presenter.countUp()
                        } else {
                            presenter.removeItem(position)
                            presenter.countDown()
                        }

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
