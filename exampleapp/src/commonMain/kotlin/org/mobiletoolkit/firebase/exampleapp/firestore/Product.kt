package org.mobiletoolkit.firebase.exampleapp.firestore

import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl
import org.mobiletoolkit.firebase.firestore.FirestoreModel

/**
 * Created by Sebastian Owodzin on 18/05/2019.
 */
@Serializable
data class Product(
    val name: String,
    val description: String,
    val price: Double
) : FirestoreModel() {

//    @Serializer(forClass = Product::class)
//    companion object : KSerializer<Product> {
//        override val descriptor: SerialDescriptor = object : SerialClassDescImpl("Product") {
//            init {
//                addElement("name")
//                addElement("dek")
//                addElement("price")
//            }
//        }
//
//        override fun serialize(output: Encoder, obj: Product) {
//            with(output.beginStructure(descriptor)) {
//                encodeStringElement(descriptor, 0, obj.name)
//                encodeStringElement(descriptor, 1, obj.dek)
//                encodeDoubleElement(descriptor, 2, obj.price)
//
//                endStructure(descriptor)
//            }
//        }
//
//        override fun deserialize(decoder: Decoder): Product {
//            lateinit var name: String
//            lateinit var dek: String
//            var price = 0.0
//
//            with(decoder.beginStructure(descriptor)) {
//                loop@ while (true) {
//                    val i = decodeElementIndex(descriptor)
//
//                    println("i: $i")
//
//                    when (i) {
////                    when (val i = decodeElementIndex(descriptor)) {
//                        CompositeDecoder.READ_DONE -> break@loop
//                        CompositeDecoder.READ_ALL -> break@loop
//                        0 -> name = decodeStringElement(descriptor, i)
//                        1 -> dek = decodeStringElement(descriptor, i)
//                        2 -> price = decodeDoubleElement(descriptor, i)
//                        else -> throw SerializationException("Unknown index $i")
//                    }
//                }
//
//                endStructure(descriptor)
//            }
//
//            return Product(name, dek, price)
//        }
//    }

//    @Serializer(forClass = Product::class)
//    companion object : KSerializer<Product> {
//        override val descriptor = StringDescriptor.withName("Product")
//
//        override fun serialize(encoder: Encoder, obj: Product) {
//            encoder.encodeString(obj.name)
//            encoder.encodeString(obj.description)
//            encoder.encodeDouble(obj.price)
//        }
//
//        override fun deserialize(decoder: Decoder): Product {
//            val price = decoder.decodeDouble()
//            val name = decoder.decodeString()
//            val description = decoder.decodeString()
//
//            return Product(
//                name,
//                description,
//                price
//            )
//
////            return Product(
////                decoder.decodeString(),
////                decoder.decodeString(),
////                decoder.decodeDouble()
////            )
//        }
//    }
}