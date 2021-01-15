import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.add

object Modules {
    const val mobile = ":mobile"
    const val shared = ":shared"
    const val model = ":model"
}

/**
 * Adds a dependency module for this module
 *
 * @param module module for the dependency to be added.
 * @return The dependency
 *
 * @see [DependencyHandler.add]
 */
fun DependencyHandler.dependencyModule(module: String): Dependency? =
    add("implementation", project(mapOf("path" to module)))
