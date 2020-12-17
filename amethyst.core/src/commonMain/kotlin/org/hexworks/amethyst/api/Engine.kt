package org.hexworks.amethyst.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.internal.TurnBasedEngine
import kotlin.coroutines.CoroutineContext

/**
 * An [Engine] contains an aggregation of [Entity] objects and it is also responsible for updating them.
 * [Entity] objects are ordered by
 */
interface Engine<T : Context> {

    /**
     * Adds the given [Entity] to this [Engine].
     */
    fun addEntity(entity: Entity<out EntityType, T>)

    /**
     * Removes the given [Entity] from this [Engine].
     */
    fun removeEntity(entity: Entity<out EntityType, T>)

    /**
     * Updates the [Entity] objects in this [Engine] with the given [context].
     */
    fun start(context: T): Job

    companion object {

        /**
         * Creates a new [TurnBasedEngine] that has a [TurnBasedEngine.executeTurn] function
         * for executing the next turn. [start] will execute the first turn.
         */
        fun <T : Context> create(
            coroutineContext: CoroutineContext = Dispatchers.Default
        ): TurnBasedEngine<T> = TurnBasedEngine(coroutineContext)
    }
}
