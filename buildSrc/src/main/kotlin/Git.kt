import org.ajoberstar.grgit.Grgit
import org.ajoberstar.grgit.operation.DescribeOp
import org.ajoberstar.grgit.operation.LogOp

/**
 * Created by Sebastian Owodzin on 23/06/2020
 */
object Git {

    private val git by lazy {
        Grgit.open()
    }

    fun gitVersionCode(): Int {
        try {
            val log = LogOp(git.repository).apply { includes = listOf("HEAD") }.call()

            println("gitVersionCode: ${log.size}")

            return log.size
        } catch (ex: RuntimeException) {

        }

        return 1
    }

    fun gitVersionName(): String {
        try {
            val describe = DescribeOp(git.repository).apply { tags = true }.call()

            println("gitVersionName: $describe")

            return describe
        } catch (ex: RuntimeException) {

        }

        return "0.0.0"
    }
}