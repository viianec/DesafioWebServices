package com.example.marvel.comic.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marvel.R
import com.example.marvel.comic.model.HqDate
import com.example.marvel.comic.model.HqPrice
import com.example.marvel.comic.repository.HqRepository
import com.example.marvel.comic.viewmodel.HqViewModel
import com.example.marvel.data.model.ThumbnailModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_hq.*
import java.util.*


class HqFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _viewModel: HqViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hq, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view
        _viewModel = ViewModelProvider(
                this,
                HqViewModel.HqViewModelFactory(HqRepository())
        ).get(HqViewModel::class.java)

        showLoading(true)
        val hqDescription = arguments?.getString(HqListFragment.HQ_DESCRIPTION)
        val hqTitle = arguments?.getString(HqListFragment.HQ_TITLE)
        val hqDates = arguments?.get(HqListFragment.HQ_DATES)
        val hqPrices = arguments?.get(HqListFragment.HQ_PRICE)
        val hqPages = arguments?.getInt(HqListFragment.HQ_PAGES)
        val hqImages = arguments?.get(HqListFragment.HQ_IMAGES)
        val hqThumbnail = arguments?.getString(HqListFragment.HQ_THUMBNAIL)

        val imgCover = _view.findViewById<ImageView>(R.id.imgCover)
        val imgHqCover = _view.findViewById<ImageView>(R.id.imgHqCover)
        val txtTitleHq = _view.findViewById<TextView>(R.id.txtTitleHQ)
        val txtDescriptionHq = _view.findViewById<TextView>(R.id.txtDescriptionHQ)
        val txtPublished = _view.findViewById<TextView>(R.id.txtPublished)
        val txtPrice = _view.findViewById<TextView>(R.id.txtPrice)
        val txtPage = _view.findViewById<TextView>(R.id.txtPage)

        showLoading(true)
        txtTitleHQ.text = hqTitle
        if (hqDescription != null) {
            txtDescriptionHq.text = hqDescription
        }
        if (hqDates != null) {
            for (date in hqDates as List<HqDate>) {
                if (date.type?.contains("onsaleDate") == true){
                    var calendar = Calendar.getInstance()
                    calendar.time = date.date!!
                    txtPublished.text = "${calendar.getDisplayName(
                            Calendar.MONTH,
                            Calendar.LONG,
                            Locale.getDefault()
                    )} ${calendar.get(Calendar.DAY_OF_MONTH)}, ${calendar.get(Calendar.YEAR)}"
                }
            }
        }
        if (hqPrices != null) {
            txtPrice.text = "$ ${(hqPrices as List<HqPrice>)[0].price.toString()}"
        }
        txtPage.text = hqPages?.toString()
        Picasso.get().load(hqThumbnail).into(imgHqCover)
        if (hqImages != null) {
            Picasso.get().load(
                    (hqImages as List<ThumbnailModel>)[(hqImages as List<ThumbnailModel>).size - 1].getImagePath(
                            "landscape_incredible"
                    )
            ).into(imgCover)
        }
        showLoading(false)
        setBackNavigation()

        imgHqCover.setOnClickListener {
            val navController = findNavController()
            val bundle = bundleOf(HQ_IMAGE to hqThumbnail)
            navController.navigate(R.id.action_hqFragment_to_hq_expandedFragment, bundle)
        }
    }
    private fun setBackNavigation() {
        _view.findViewById<ImageView>(R.id.imgBack).setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }
    private fun showLoading(isLoading: Boolean) {
        val viewLoading = _view.findViewById<View>(R.id.imgLoading)

        if (isLoading) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
        }
    }

    companion object {
        const val HQ_IMAGE = "HQ_IMAGE"
    }
}