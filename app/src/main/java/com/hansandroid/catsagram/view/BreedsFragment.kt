package com.hansandroid.catsagram.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hansandroid.catsagram.CatsagramApp
import com.hansandroid.catsagram.R
import com.hansandroid.catsagram.model.BreedModel
import com.hansandroid.catsagram.presenter.BreedListFragmentPresenter
import com.hansandroid.catsagram.view.adapter.BreedsAdapter
import kotlinx.android.synthetic.main.fragment_breeds.*
import javax.inject.Inject

class BreedsFragment : Fragment(), BreedListFragmentPresenter.View {

    @Inject lateinit var mPresenter: BreedListFragmentPresenter
    private lateinit var mBreedsAdapter: BreedsAdapter
    private val TAG = BreedsFragment::class.simpleName

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
        return inflater.inflate(R.layout.fragment_breeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBreeds()
        configureRecyclerView()
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
        val didTap: (String) -> Unit = {id ->
            Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
        }
        mBreedsAdapter = BreedsAdapter(breeds, didTap)
        mBreedsAdapter.notifyDataSetChanged()
        recycler_view.adapter = mBreedsAdapter
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


}