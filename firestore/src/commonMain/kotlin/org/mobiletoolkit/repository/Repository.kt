package org.mobiletoolkit.repository

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
interface Repository<Identifier, Entity : Model<Identifier>> {

    fun exists(identifier: Identifier): Boolean

    fun get(identifier: Identifier): Entity?

    fun create(entity: Entity, identifier: Identifier? = null): Boolean
//    fun create(vararg entities: Entity): Boolean
//    fun create(entities: List<Entity>, identifiers: List<Identifier?>? = null): Boolean

    fun update(entity: Entity): Boolean
//    fun update(vararg entities: Entity): Boolean

    fun delete(entity: Entity): Boolean
//    fun delete(identifier: Identifier): Boolean
//    fun delete(vararg entities: Entity): Boolean
//    fun delete(vararg identifiers: Identifier): Boolean

    fun get(): List<Entity>
}