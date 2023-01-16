package com.wsl.mypokemonapp.base.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.wsl.mypokemonapp.common.LayoutFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass
import com.wsl.mypokemonapp.BR

abstract class BaseViewModelFragment<Bind: ViewDataBinding, ModelT: BaseViewModel>(
    viewModelClass: KClass<ModelT>
): Fragment() {
    private var bindingNullable: Bind? = null

    protected val binding get() = bindingNullable!!

    protected open val viewModel: ModelT by viewModel(clazz = viewModelClass)

    protected lateinit var navController: NavController

    val navigation get() = navController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNullable = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = this@BaseViewModelFragment
        binding.executePendingBindings()

        return binding.root
    }

    private fun getLayoutId(): Int =
        javaClass.getAnnotation(LayoutFragment::class.java)?.layoutId
            ?: throw IllegalArgumentException("Missing LayoutFragment Annotation")


    @CallSuper
    protected open fun setupViews(savedInstanceState: Bundle?) {
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(savedInstanceState)
        subscribeToViewModel(viewModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
    }

    protected open fun subscribeToViewModel(viewModel: ModelT) {}
}