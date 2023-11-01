package kedaiapps.projeku.testandroidsalt.ui.home

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kedaiapps.projeku.testandroidsalt.databinding.FragmentHomeDetailBinding
import kedaiapps.projeku.testandroidsalt.ext.getformatDateTime
import kedaiapps.projeku.testandroidsalt.modules.base.BaseFragment
import kedaiapps.projeku.testandroidsalt.viewmodel.MainViewModel


class HomeDetailFragment : BaseFragment() {
    lateinit var mBinding: FragmentHomeDetailBinding
    private val viewModel by viewModels<MainViewModel>()
    private val args by navArgs<HomeDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeDetailBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    private var image = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initView()
    }

    private fun initToolbar() {
        mBinding.tlbr.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvTitle.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initView() {
        mBinding.tlbr.tvTitle.text = args.title

        Glide.with(this).load(args.urlToImage)
            .apply(
                RequestOptions()
                    .transform(RoundedCorners(16))
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .dontAnimate()
            ).into(mBinding.image)

        mBinding.author.text = args.author
        mBinding.judul.text = args.title
        mBinding.date.text = getformatDateTime(args.publishedAt, "dd MMMM yyyy")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding.description.text =
                Html.fromHtml(args.description, Html.FROM_HTML_MODE_COMPACT);
        } else {
            mBinding.description.text = Html.fromHtml(args.description);
        }

//        viewModel.homeDetail(args.pokemonId)
//
//        mBinding.btnSubmit.setOnClickListener {
//            viewModel.download(image, ""+getDateTimeNow(), "image/png")
//        }


    }

//    private fun setData(data: ResponseHomeDetail) {
//        background_image = data.background_image
//        name = data.name
//        released = data.released
//        rating = data.rating
//
//        mBinding.tlbr.tvTitle.text = name
//
//        Glide.with(this).load(background_image)
//            .apply(
//                RequestOptions()
//                    .transform(RoundedCorners(16))
//                    .skipMemoryCache(true)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .dontAnimate()
//            ).into(mBinding.image)
//
//        mBinding.slug.text = data.slug
//        mBinding.judul.text = name
//        mBinding.date.text = "Release date $released"
//        mBinding.rating.text = rating
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            mBinding.description.text =
//                Html.fromHtml(data.description, Html.FROM_HTML_MODE_COMPACT);
//        } else {
//            mBinding.description.text = Html.fromHtml(data.description);
//        }
//    }
}