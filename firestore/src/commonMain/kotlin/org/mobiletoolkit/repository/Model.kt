package org.mobiletoolkit.repository

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
interface Model<Identifier> {
    val identifier: Identifier?
        get() = null
}