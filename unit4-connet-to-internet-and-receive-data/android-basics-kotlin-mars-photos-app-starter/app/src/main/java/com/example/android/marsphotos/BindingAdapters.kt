package com.example.android.marsphotos

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

/*The @BindingAdapter annotation tells data
binding to execute this binding adapter when a
View item has the imageUrl attribute
 */
@BindingAdapter("imageUrl")
fun bindImage(imageView : ImageView,imageUrl:String?){
imageUrl?.let {
    //convert the URL string to a Uri object using the toUri() method
    //To use the HTTPS scheme, append buildUpon.scheme("https")
    // To the toUri builder. Call build() to build the object.
    val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
    imageView.load(imageUri){
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
    }
}

}


class BindingAdapters {
}