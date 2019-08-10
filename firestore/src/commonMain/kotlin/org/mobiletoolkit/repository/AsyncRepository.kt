package org.mobiletoolkit.repository

/**
 * Created by Sebastian Owodzin on 10/08/2019.
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
}

typealias AsyncRepositoryCallback<DataType> = (
    data: DataType,
    error: Exception?
) -> Unit
