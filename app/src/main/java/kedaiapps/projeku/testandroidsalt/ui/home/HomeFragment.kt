package kedaiapps.projeku.testandroidsalt.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kedaiapps.projeku.testandroidsalt.R
import kedaiapps.projeku.testandroidsalt.common.UiState
import kedaiapps.projeku.testandroidsalt.databinding.FragmentHomeBinding
import kedaiapps.projeku.testandroidsalt.ext.hideKeyboard
import kedaiapps.projeku.testandroidsalt.ext.observe
import kedaiapps.projeku.testandroidsalt.ext.toast
import kedaiapps.projeku.testandroidsalt.modules.base.BaseFragment
import kedaiapps.projeku.testandroidsalt.services.entity.ResponseHome
import kedaiapps.projeku.testandroidsalt.ui.home.adapter.AdapterHome
import kedaiapps.projeku.testandroidsalt.viewmodel.MainViewModel
import kedaiapps.projeku.testandroidsalt.viewmodel.RepoViewModel
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment() {
    lateinit var mBinding: FragmentHomeBinding
    private val viewModel by viewModels<MainViewModel>()
    private val viewModelRepo by viewModels<RepoViewModel>()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        AdapterHome(::onClick)
    }

    private var list: List<ResponseHome> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initView()
        handleState()
    }

    private fun initToolbar() {
        mBinding.tlbr.apply {
            ivBack.isVisible = false
            tvTitle.text = "Daftar Berita"
        }
    }

    private fun initView() {
        mBinding.progress.progressBar.setAnimation(R.raw.loading)

        viewModel.home()

        mBinding.search.addTextChangedListener {
            val filteredModelList: List<ResponseHome> = filter(list, it.toString())
            adapter.clearData()
            adapter.insertData(filteredModelList)
        }

        mBinding.search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                return@setOnEditorActionListener true
            }
            false
        }

        // home
        mBinding.rv.adapter = adapter
    }

    private fun filter(
        models: List<ResponseHome>,
        query: String
    ): List<ResponseHome> {

        val filteredModelList: MutableList<ResponseHome> = ArrayList()
        for (model in models) {
            val name: String = model.title.toLowerCase()
            if (name.contains(query.toLowerCase())) {
                filteredModelList.add(model)
            }
        }
        return filteredModelList
    }

    private fun handleState() {
//        observe(viewModelRepo.getList()) {
//            if (it != null) {
//                adapter.clearData()
//                adapter.insertData(it)
//
//                list = it
//            }
//        }

        observe(viewModel.responseHome) {
            if(it != null){
                adapter.clearData()
                adapter.insertData(it)

                list = it
            }
        }

        // loading
        observe(viewModel.loadState) {
            when (it) {
                UiState.Loading -> mBinding.progress.progressBar.isVisible = true
                UiState.Success -> {
                    mBinding.progress.progressBar.isVisible = false
                }
                is UiState.Error -> {
                    mBinding.progress.progressBar.isVisible = false
                    requireActivity().toast(it.message)
                    Log.e("BISMILLAH", it.message)
                }
            }
        }
    }

    private fun onClick(data: ResponseHome) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToHomeDetailFragment(
                data.author, data.title, data.description, data.urlToImage, data.publishedAt, data.content
            )
        )
    }
}