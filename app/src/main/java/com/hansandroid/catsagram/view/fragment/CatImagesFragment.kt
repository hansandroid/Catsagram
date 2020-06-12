package com.hansandroid.catsagram.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hansandroid.catsagram.CatsagramApp
import com.hansandroid.catsagram.R
import com.hansandroid.catsagram.model.CatImageModel
import com.hansandroid.catsagram.presenter.CatImagesFragmentPresenter
import com.hansandroid.catsagram.view.adapter.catimages.CatsImagesAdapter
import kotlinx.android.synthetic.main.fragment_recycler.*
import javax.inject.Inject

class CatImagesFragment : Fragment(), CatImagesFragmentPresenter.View {

    @Inject lateinit var mPresenter: CatImagesFragmentPresenter
    private lateinit var mImagesAdapter: CatsImagesAdapter
    private val TAG = CatImagesFragment::class.java.simpleName
    private val COLUMNS_COUNT = 3

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as CatsagramApp).mComponent.inject(this)
        mPresenter.injectView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString(ID_KEY)
        mPresenter.getImages(id ?: "")
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, COLUMNS_COUNT)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun showImages(images: Array<CatImageModel>) {
        mImagesAdapter = CatsImagesAdapter(images)
        recycler_view.adapter = mImagesAdapter
    }

    override fun showProgressbar(show: Boolean) {
        if (show) {
            progressbar.visibility = View.VISIBLE
        } else {
            progressbar.visibility = View.GONE
        }
    }

    override fun showError(message: String) {
        Log.d(TAG, message)
    }

    companion object {
        private val ID_KEY = "id"

        @JvmStatic
        fun getInstance(id: String) : CatImagesFragment {
            val fragment =
                CatImagesFragment()
            val args = Bundle()
            args.putString(ID_KEY, id)
            fragment.arguments = args
            return fragment
        }
    }

}