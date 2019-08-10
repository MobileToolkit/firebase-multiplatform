package org.mobiletoolkit.repository

/**
 * Created by Sebastian Owodzin on 10/08/2019.
 */
interface ObservableAsyncRepository<Identifier, Entity : Model<Identifier>> : AsyncRepository<Identifier, Entity> {

    fun observe(listener: ObservableAsyncRepositoryListener<List<Entity>>): ObserverReference

    fun observe(identifier: Identifier, listener: ObservableAsyncRepositoryListener<Entity>): ObserverReference

    fun stopAllObservers()

    interface ObserverReference {
        fun stop()
    }
}

typealias ObservableAsyncRepositoryListener<DataType> = (
    data: DataType?,
    error: Exception?
) -> Unit
