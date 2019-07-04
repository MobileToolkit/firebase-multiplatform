package org.mobiletoolkit.firebase.exampleapp.firestore

import kotlinx.serialization.Serializable
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
//                addElement("description")
//                addElement("price")
//            }
//        }
//
//        override fun serialize(encoder: Encoder, obj: Product) {
//            val compositeOutput = encoder.beginStructure(descriptor)
//            compositeOutput.encodeStringElement(descriptor, 0, obj.name)
//            compositeOutput.encodeStringElement(descriptor, 1, obj.description)
//            compositeOutput.encodeDoubleElement(descriptor, 2, obj.price)
//            compositeOutput.endStructure(descriptor)
//        }
//
//        override fun deserialize(decoder: Decoder): Product {
//            val dec: CompositeDecoder = decoder.beginStructure(descriptor)
//            var name: String? = null
//            var description: String? = null
//            var price: Double? = null
//            loop@ while (true) {
//                when (val i = dec.decodeElementIndex(descriptor)) {
//                    CompositeDecoder.READ_DONE -> break@loop
//                    0 -> name = dec.decodeStringElement(descriptor, i)
//                    1 -> description = dec.decodeStringElement(descriptor, i)
//                    2 -> price = dec.decodeDoubleElement(descriptor, i)
//                    else -> throw SerializationException("Unknown index $i")
//                }
//            }
//            dec.endStructure(descriptor)
//            return Product(
//                name ?: throw MissingFieldException("name"),
//                description ?: throw MissingFieldException("description"),
//                price ?: throw MissingFieldException("price")
//            )
//        }
//    }
}