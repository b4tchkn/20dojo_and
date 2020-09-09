package jp.co.cyberagent.dojo2020.ui.create

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

@Suppress("UNCHECKED_CAST")
class MemoCreateViewModelFactory(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle,
    private val context: Context
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = MemoCreateViewModel(context) as T
}