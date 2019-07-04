package org.mobiletoolkit.repository

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
interface AsyncRepository<Identifier, Entity : Model<Identifier>> {

    fun get(callback: AsyncRepositoryCallback<List<Entity>>)

    fun get(identifier: Identifier, callback: AsyncRepositoryCallback<Entity?>)

    fun create(entity: Entity, identifier: Identifier? = null, callback: AsyncRepositoryCallback<Boolean>)
////    fun create(vararg entities: Entity, callback: AsyncRepositoryCallback<Boolean>)
////    fun create(
////        entities: List<Entity>,
////        identifiers: List<Identifier?>? = null,
////        callback: AsyncRepositoryCallback<Boolean>
////    )
//
    fun update(entity: Entity, callback: AsyncRepositoryCallback<Boolean>)
////    fun update(vararg entities: Entity, callback: AsyncRepositoryCallback<Boolean>)
//
    fun delete(entity: Entity, callback: AsyncRepositoryCallback<Boolean>)
////    fun delete(identifier: Identifier, callback: AsyncRepositoryCallback<Boolean>)
////    fun delete(vararg entities: Entity, callback: AsyncRepositoryCallback<Boolean>)
////    fun delete(vararg identifiers: Identifier, callback: AsyncRepositoryCallback<Boolean>)
//

//    // Listeners
//
    fun observe(identifier: Identifier, listener: AsyncRepositoryListener<Entity>)

    fun observe(listener: AsyncRepositoryListener<List<Entity>>)
//
//    fun releaseListener(listener: AsyncRepositoryListener<*, Entity>)
//
//    interface Change<T> {
//
//        enum class Type {
//            Added, Modified, Removed
//        }
//
//        val type: Type
//
//        val oldIndex: Int
//            get() = -1
//
//        val newIndex: Int
//
//        val data: T?
//            get() = null
//    }
}

typealias AsyncRepositoryCallback<DataType> = (
    data: DataType,
    error: String?
) -> Unit

typealias AsyncRepositoryListener<DataType> = (
    data: DataType?,
    error: String?
) -> Unit

//typealias AsyncRepositoryListener<DataType, ChangeType> = (
//    data: DataType?,
//    changeSet: Set<AsyncRepository.Change<ChangeType>>?,
//    exception: Exception?
//) -> Unit