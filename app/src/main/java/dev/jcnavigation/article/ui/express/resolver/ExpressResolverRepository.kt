package dev.jcnavigation.article.ui.express.resolver

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

interface ExpressResolverRepository {
    val isResolved: StateFlow<Boolean>
    fun resolve()
}

@Suppress("ObjectPropertyName")
object ExpressResolverRepositoryImpl : ExpressResolverRepository {
    private val _isResolved = MutableStateFlow(false)
    override val isResolved: StateFlow<Boolean> = _isResolved

    override fun resolve() {
        _isResolved.update { true }
    }
}