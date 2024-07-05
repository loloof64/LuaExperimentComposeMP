package core.lua_interpreter

import kotlin.random.Random

sealed class PredefinedVariable(open val name: String, open val value: Any)
data class PredifinedInt(override val name: String, override val value: Int) : PredefinedVariable(name, value) {
    companion object {
        fun generate(name: String, range: IntRange = Int.MIN_VALUE..Int.MAX_VALUE): PredifinedInt {
            val interval = range.endInclusive - range.start + 1
            val value = range.start + Random.nextInt(interval)
            return PredifinedInt(name, value)
        }
    }
}

data class PredifinedBoolean(override val name: String, override val value: Boolean) : PredefinedVariable(name, value) {
    companion object {
        fun generate(name: String) : PredifinedBoolean {
            val value = Random.nextBoolean()
            return PredifinedBoolean(name, value)
        }
    }
}