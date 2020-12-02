package com.example.marvel.comic.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.comic.model.HqModel
import com.example.marvel.comic.repository.HqRepository
import com.example.marvel.comic.viewmodel.HqViewModel

class HqListFragment : Fragment() {

    private lateinit var _viewModel: HqViewModel
    private lateinit var _view: View

    private lateinit var _listAdapter: HqListAdapter
    private lateinit var _recyclerView: RecyclerView

    private var _Hq = mutableListOf<HqModel>()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hq_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        val list = _view.findViewById<RecyclerView>(R.id.rvHqList)
        val manager = GridLayoutManager(_view.context, 3)

        _Hq = mutableListOf()
        _listAdapter = HqListAdapter(_Hq) {
            val bundle = bundleOf(HQ_ID to it.id,
                    HQ_DESCRIPTION to it.description,
                    HQ_PRICE to it.prices,
                    HQ_IMAGES to it.images,
                    HQ_THUMBNAIL to it.thumbnail?.getImagePath(),
                    HQ_PAGES to it.pageCount,
                    HQ_DATES to it.dates,
                    HQ_TITLE to it.title
            )
            _view.findNavController().navigate(R.id.action_hq_list_fragment_to_hqFragment, bundle)
        }
        _recyclerView = _view.findViewById<RecyclerView>(R.id.rvHqList)
        list.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = _listAdapter
        }
        _viewModel = ViewModelProvider(
                this,
                HqViewModel.HqViewModelFactory(HqRepository())
        ).get(HqViewModel::class.java)

        _viewModel.getList().observe(viewLifecycleOwner, Observer {
            _Hq.addAll(it)
            _listAdapter.notifyDataSetChanged()
            showLoading(false)
        })
        showLoading(true)
        setScrollView()
    }
    private fun showLoading(isLoading: Boolean) {
        val viewLoading = _view.findViewById<View>(R.id.txtLoading)
        if (isLoading) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
        }
    }
    private fun setScrollView() {
        _recyclerView.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val target = recyclerView.layoutManager as GridLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = lastVisible + 6 >= totalItemCount
                    if (totalItemCount > 0 && lastItem) {
                        showLoading(true)
                        _viewModel.nextPage().observe(viewLifecycleOwner, Observer {
                            _Hq.addAll(it)
                            _listAdapter.notifyDataSetChanged()
                            showLoading(false)
                        })
                    }
                }
            })
        }
    }

    companion object {
        const val HQ_ID = "HQ_ID"
        const val HQ_DESCRIPTION = "HQ_DESCRIPTION"
        const val HQ_PRICE = "HQ_PRICE"
        const val HQ_IMAGES = "HQ_IMAGES"
        const val HQ_THUMBNAIL = "HQ_THUMBNAIL"
        const val HQ_PAGES = "HQ_PAGES"
        const val HQ_DATES = "HQ_DATES"
        const val HQ_TITLE = "HQ_TITLE"
    }
}