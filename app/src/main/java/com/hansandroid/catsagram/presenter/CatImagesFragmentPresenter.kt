package com.hansandroid.catsagram.presenter

import com.hansandroid.catsagram.api.ImagesApi
import com.hansandroid.catsagram.model.CatImageModel
import com.hansandroid.catsagram.rx.RxThread
import com.hansandroid.catsagram.view.interfaces.ViewWithProgressbar
import com.hansandroid.catsagram.view.interfaces.ViewWithShowError
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class CatImagesFragmentPresenter @Inject constructor(private val mImagesApi: ImagesApi, private val mRxThread: RxThread) {

    private lateinit var mView: View
    private val mDisposable = CompositeDisposable()
    private val IMAGES_COUNT = 20

    interface View :
        ViewWithProgressbar, ViewWithShowError {
        fun showImages(images: Array<CatImageModel>)
    }

    fun injectView(view: View) {
        mView = view
    }

    fun getImages(id: String) {
        mDisposable.add(mImagesApi.getImages(id, IMAGES_COUNT)
            .compose(mRxThread.applyAsync())
            .doOnSubscribe { mView.showProgressbar(true) }
            .doOnTerminate { mView.showProgressbar(false) }
            .doOnError { response -> mView.showError(response.toString()) }
            .onErrorReturnItem(emptyArray())
            .subscribe { mView.showImages(it) })

    }

    fun onStop() {
        mDisposable.clear()
    }

}