package org.mobiletoolkit.coroutines

import org.mobiletoolkit.repository.Model

/**
 * Created by Sebastian Owodzin on 21/05/2019.
 */
interface Repository<Identifier, Entity : Model<Identifier>> {

//    suspend fun exists(identifier: Identifier): Boolean

    suspend fun get(identifier: Identifier): Entity?

    suspend fun create(entity: Entity, identifier: Identifier? = null): Boolean
//    fun create(vararg entities: Entity): Boolean
//    fun create(entities: List<Entity>, identifiers: List<Identifier?>? = null): Boolean

    suspend fun update(entity: Entity): Boolean
//    fun update(vararg entities: Entity): Boolean

    suspend fun delete(entity: Entity): Boolean
//    fun delete(identifier: Identifier): Boolean
//    fun delete(vararg entities: Entity): Boolean
//    fun delete(vararg identifiers: Identifier): Boolean

    suspend fun get(): List<Entity>
}