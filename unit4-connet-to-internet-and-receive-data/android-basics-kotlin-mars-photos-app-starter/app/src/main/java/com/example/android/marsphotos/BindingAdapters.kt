package com.example.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.MarsApiStatus
import com.example.android.marsphotos.overview.PhotoGridAdapter

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



@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}


@BindingAdapter("marsApiStatus")
fun bindingStatus(statusImageView : ImageView, status:MarsApiStatus){

    when(status){
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}