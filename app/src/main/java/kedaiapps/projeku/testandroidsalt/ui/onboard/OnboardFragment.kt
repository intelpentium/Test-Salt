package kedaiapps.projeku.testandroidsalt.ui.onboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kedaiapps.projeku.testandroidsalt.R
import kedaiapps.projeku.testandroidsalt.common.UiState
import kedaiapps.projeku.testandroidsalt.databinding.FragmentOnboardBinding
import kedaiapps.projeku.testandroidsalt.ext.observe
import kedaiapps.projeku.testandroidsalt.ext.toast
import kedaiapps.projeku.testandroidsalt.modules.base.BaseFragment
import kedaiapps.projeku.testandroidsalt.viewmodel.MainViewModel
import kedaiapps.projeku.testandroidsalt.viewmodel.RepoViewModel

class OnboardFragment : BaseFragment() {
    lateinit var mBinding: FragmentOnboardBinding
    private val viewModel by viewModels<MainViewModel>()
    private val viewModelRepo by viewModels<RepoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentOnboardBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
//        handleState()
    }

    private fun initView() {
        val slide_top = AnimationUtils.loadAnimation(requireActivity(), R.anim.slide_top)
        val slide_in = AnimationUtils.loadAnimation(requireActivity(), R.anim.slide_in)

        mBinding.ivLogo.startAnimation(slide_top)
        mBinding.etJudul.startAnimation(slide_in)
        mBinding.btnSubmit.startAnimation(slide_in)

        mBinding.progress.progressBar.setAnimation(R.raw.loading)

        mBinding.btnSubmit.setOnClickListener {
            viewModelRepo.deleteList()
            viewModel.home()
            findNavController().navigate(OnboardFragmentDirections.actionOnboardFragmentToHomeFragment())
        }
    }

//    private fun handleState() {
//        observe(viewModel.responseHome) {
//            it?.forEach { data ->
//                viewModelRepo.setList(
//                    data.author,
//                    data.title,
//                    data.description,
//                    data.urlToImage,
//                    data.publishedAt,
//                    data.content
//                )
//            }
//        }
//
//        // loading
//        observe(viewModel.loadState) {
//            when (it) {
//                UiState.Loading -> mBinding.progress.progressBar.isVisible = true
//                UiState.Success -> {
//                    mBinding.progress.progressBar.isVisible = false
//                    findNavController().navigate(OnboardFragmentDirections.actionOnboardFragmentToHomeFragment())
//                }
//                is UiState.Error -> {
//                    mBinding.progress.progressBar.isVisible = false
//                    requireActivity().toast(it.message)
//                    Log.e("BISMILLAH", it.message)
//                }
//            }
//        }
//    }
}