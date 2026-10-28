package com.zd.featuredemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zd.featuredemo.bean.RiveBean
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RiveViewModel: ViewModel(){
    private val _beans = MutableStateFlow<List<RiveBean>>(emptyList())
    val beans: StateFlow<List<RiveBean>> = _beans.asStateFlow()


    fun loadData(list: MutableList<RiveBean>) {
        viewModelScope.launch {
            // 这里可以走网络请求 ，我这边修改成本地加载了
            val newList = list
            _beans.value = newList   // 赋值新列表
        }
    }
    fun<T> notify(property: String,type:T){

    }

}