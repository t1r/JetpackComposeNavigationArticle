package dev.jcnavigation.article.ui.express.resolver

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

interface ExpressResolverRepository {
    val addressIdFlow: StateFlow<Long?>
    fun resolve()
}

@Suppress("ObjectPropertyName")
object ExpressResolverRepositoryImpl : ExpressResolverRepository {
    private val _addressIdFlow = MutableStateFlow<Long?>(null)
    override val addressIdFlow: StateFlow<Long?> = _addressIdFlow

    override fun resolve() {
        _addressIdFlow.update { 1L }
    }
}