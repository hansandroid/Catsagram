package com.hansandroid.catsagram.presenter

import androidx.appcompat.widget.SearchView
import com.hansandroid.catsagram.api.BreedsApi
import com.hansandroid.catsagram.model.BreedModel
import com.hansandroid.catsagram.rx.RxThread
import com.hansandroid.catsagram.view.interfaces.ViewWithProgressbar
import com.hansandroid.catsagram.view.interfaces.ViewWithShowError
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class BreedListFragmentPresenter @Inject constructor(private val mBreedsApi: BreedsApi, private val mRxThread: RxThread) {

    private var mView: View? = null
    private val mDisposable = CompositeDisposable()
    private var mBreedList: Array<BreedModel>? = null

    interface View : ViewWithProgressbar,
        ViewWithShowError {
        fun showBreeds(breeds: Array<BreedModel>)
        fun filterBreedList(search: String)
    }

    fun attachView(view: View) {
        mView = view
    }

    fun getBreeds() {
        if (mBreedList == null) {
            mDisposable.add(mBreedsApi.getBreeds()
                .compose(mRxThread.applyAsync())
                .doOnSubscribe { mView?.showProgressbar(true) }
                .doOnTerminate { mView?.showProgressbar(false) }
                .doOnError { mView?.showError(it.toString()) }
                .onErrorReturnItem(emptyArray())
                .subscribe {
                    mBreedList = it
                    mView?.showBreeds(it)
                })
        } else {
            mView?.showBreeds(mBreedList!!)
        }
    }

    fun onStop() {
        mDisposable.clear()
    }

    fun detachView() {
        mView = null
    }

    fun search(searchView: SearchView) {
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mView?.filterBreedList(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mView?.filterBreedList(newText)
                return true
            }
        })
    }

}