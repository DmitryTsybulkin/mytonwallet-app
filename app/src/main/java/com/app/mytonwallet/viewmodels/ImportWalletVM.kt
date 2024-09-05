package com.app.mytonwallet.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mytonwallet.web.GetMnemonicWordListMsg
import com.app.mytonwallet.web.ValidateMnemonicMsg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.ton.mnemonic.Mnemonic

class ImportWalletVM : ViewModel() {

    private val _defaultWords = MutableStateFlow(Mnemonic.mnemonicWords())

    var words = List(24, init = { _ -> "" }).toMutableStateList()
        private set
    private var searchQuery by mutableStateOf("")

    val searchResults: StateFlow<List<String>> =
        snapshotFlow { searchQuery }
            .combine(_defaultWords) { searchQuery, arr ->
                when {
                    searchQuery.isNotEmpty() -> arr.filter { word ->
                        word.startsWith(searchQuery, ignoreCase = true)
                                && word.contains(searchQuery, ignoreCase = true)
                    }
                    else -> arr
                }
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.WhileSubscribed(5_000)
            )

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    fun validMnemonic(): Boolean {
        return Mnemonic.isValid(words)
    }

    // legacy
    private val _validMnemonic = MutableLiveData<Boolean>()

    fun onDefaultWords(msg: GetMnemonicWordListMsg) {
        _defaultWords.value = msg.words.asList()
    }

    fun onValidateMnemonic(msg: ValidateMnemonicMsg) {
        _validMnemonic.postValue(msg.valid)
    }
}