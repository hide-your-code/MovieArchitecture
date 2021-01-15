package minhdtm.example.shared.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import timber.log.Timber

object GsonHelper {

    fun create(): Gson = GsonBuilder().apply {
        registerTypeAdapter(Int::class.java, IntType())
        registerTypeAdapter(Int::class.javaObjectType, IntPrimitiveType())
        registerTypeAdapter(Long::class.java, LongType())
        registerTypeAdapter(Long::class.javaObjectType, LongPrimitiveType())
        registerTypeAdapter(Float::class.java, FloatType())
        registerTypeAdapter(Float::class.javaObjectType, FloatPrimitiveType())
        registerTypeAdapter(Double::class.java, DoubleType())
        registerTypeAdapter(Double::class.javaObjectType, DoublePrimitiveType())
        registerTypeAdapter(Boolean::class.java, BooleanType())
        setLenient()
    }.create()

    private class IntType : JsonDeserializer<Int?> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Int? = try {
            json.asInt
        } catch (e: UnsupportedOperationException) {
            try {
                json.toString().toInt()
            } catch (ex: NumberFormatException) {
                messageError("Int")
                null
            }
        }
    }

    private class IntPrimitiveType : JsonDeserializer<Int?> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Int = try {
            json.asInt
        } catch (e: UnsupportedOperationException) {
            try {
                json.toString().toInt()
            } catch (ex: NumberFormatException) {
                messageError("Int")
                0
            }
        }
    }

    private class LongType : JsonDeserializer<Long?> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Long? = try {
            json.asLong
        } catch (e: UnsupportedOperationException) {
            try {
                json.toString().toLong()
            } catch (ex: NumberFormatException) {
                messageError("Long")
                null
            }
        }
    }

    private class LongPrimitiveType : JsonDeserializer<Long?> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Long = try {
            json.asLong
        } catch (e: UnsupportedOperationException) {
            try {
                json.toString().toLong()
            } catch (ex: NumberFormatException) {
                messageError("Long")
                0L
            }
        }
    }

    private class FloatType : JsonDeserializer<Float?> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Float? = try {
            json.asFloat
        } catch (e: UnsupportedOperationException) {
            messageError("Float")
            null
        }
    }

    private class FloatPrimitiveType : JsonDeserializer<Float> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Float = try {
            json.asFloat
        } catch (e: UnsupportedOperationException) {
            try {
                json.toString().toFloat()
            } catch (ex: NumberFormatException) {
                messageError("Float")
                0F
            }
        }
    }

    private class DoubleType : JsonDeserializer<Double?> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Double? = try {
            json.asDouble
        } catch (e: UnsupportedOperationException) {
            messageError("Double")
            null
        }
    }

    private class DoublePrimitiveType : JsonDeserializer<Double> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Double = try {
            json.asDouble
        } catch (e: UnsupportedOperationException) {
            try {
                json.toString().toDouble()
            } catch (ex: NumberFormatException) {
                messageError("Double")
                0.0
            }
        }
    }

    private class BooleanType : JsonDeserializer<Boolean> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Boolean = try {
            json.asBoolean
        } catch (ex: IllegalStateException) {
            when (json.toString()) {
                "true", "1" -> true
                "false" -> false
                else -> {
                    messageError("Boolean")
                    false
                }
            }
        }
    }

    private fun messageError(value: String) {
        Timber.e("Server return wrong data type. Expect: [$value]")
    }
}
