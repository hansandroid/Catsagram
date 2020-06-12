package com.hansandroid.catsagram.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hansandroid.catsagram.CatsagramApp
import com.hansandroid.catsagram.R
import com.hansandroid.catsagram.model.BreedModel
import com.hansandroid.catsagram.presenter.BreedListFragmentPresenter
import com.hansandroid.catsagram.view.activity.MainActivity
import com.hansandroid.catsagram.view.adapter.breeds.BreedsAdapter
import kotlinx.android.synthetic.main.fragment_recycler.*
import javax.inject.Inject


class BreedsFragment : Fragment(), BreedListFragmentPresenter.View {

    @Inject lateinit var mPresenter: BreedListFragmentPresenter
    private lateinit var mBreedsAdapter: BreedsAdapter
    private val TAG = BreedsFragment::class.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as CatsagramApp).mComponent.inject(this)
        mPresenter.attachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.title = context?.getString(R.string.app_name)

        getBreeds()
        configureRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.breeds, menu)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        mPresenter.search(searchView)

    }

    private fun getBreeds() {
        mPresenter.getBreeds()
    }

    private fun configureRecyclerView() {
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun showBreeds(breeds: Array<BreedModel>) {
        val didTap: (id: String, name: String) -> Unit = { id, name ->
            (context as MainActivity).replaceFragment(CatImagesFragment.getInstance(id, name))
        }
        mBreedsAdapter =
            BreedsAdapter(
                breeds,
                didTap
            )
        recycler_view.adapter = mBreedsAdapter
    }

    override fun filterBreedList(search: String) {
        mBreedsAdapter.filter.filter(search)
    }

    override fun showError(message: String) {
        Log.d(TAG, message)
    }

    override fun showProgressbar(show: Boolean) {
        if (show) {
            progressbar.visibility = View.VISIBLE
        } else {
            progressbar.visibility = View.GONE
        }
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}