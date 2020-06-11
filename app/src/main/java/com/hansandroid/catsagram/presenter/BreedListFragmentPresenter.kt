package com.hansandroid.catsagram.presenter

import com.hansandroid.catsagram.api.BreedsApi
import com.hansandroid.catsagram.model.BreedModel
import com.hansandroid.catsagram.rx.RxThread
import com.hansandroid.catsagram.view.ViewWithProgressbar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class BreedListFragmentPresenter @Inject constructor(private val mBreedsApi: BreedsApi, private val mRxThread: RxThread) {

    private lateinit var mView: View
    private val mDisposable = CompositeDisposable()

    interface View : ViewWithProgressbar {
        fun showBreeds(breeds: Array<BreedModel>)
        fun showError(message: String)
    }

    fun injectView(view: View) {
        mView = view
    }

    fun getBreeds() {
        mDisposable.add(mBreedsApi.getBreeds()
            .compose(mRxThread.applyAsync())
            .doOnSubscribe { mView.showProgressbar(true) }
            .doOnTerminate { mView.showProgressbar(false) }
            .doOnError { mView.showError(it.toString() ?: "Error")}
            .onErrorReturnItem(emptyArray())
            .subscribe { mView.showBreeds(it) })
    }

    fun onStop() {
        mDisposable.clear()
    }


}