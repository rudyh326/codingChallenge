package com.example.codingchallenge

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.codingchallenge.domain.Repo
import com.example.codingchallenge.screens.repos.RepoGridAdapter

@BindingAdapter("repoListData")
fun bindMovieRecyclerView(recyclerView: RecyclerView, data: List<Repo>?) {
    val adapter = recyclerView.adapter as RepoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("repoAvatarURL")
fun bindAvatar(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("repoName")
fun bindTitle(textView: TextView, name: String?) {
    if (name==null) textView.visibility = View.GONE
    else {
        textView.text = name
        textView.visibility = View.VISIBLE
    }
}

@BindingAdapter("repoDate")
fun bindDate(textView: TextView, date: String?) {
    if (date==null) textView.visibility = View.GONE
    else {
        textView.text = date
        textView.visibility = View.VISIBLE
    }
}

@BindingAdapter("repoDetailName")
fun bindDetailName(textView: TextView, name: String?) {
    var fullText = "<b><u>${textView.context.getString(R.string.preName)}</u></b>"
    if (name==null) textView.visibility = View.GONE
    else {
        fullText += " : $name"
        textView.text = Html.fromHtml(fullText, Html.FROM_HTML_MODE_LEGACY)
        textView.visibility = View.VISIBLE
    }
}

@BindingAdapter("repoDetailDate")
fun bindDetailDate(textView: TextView, date: String?) {
    var fullText = "<b><u>${textView.context.getString(R.string.preDate)}</u></b>"
    if (date==null) textView.visibility = View.GONE
    else {
        fullText += " : $date"
        textView.text = Html.fromHtml(fullText, Html.FROM_HTML_MODE_LEGACY)
        textView.visibility = View.VISIBLE
    }
}

@BindingAdapter("repoDetailStargazersCount")
fun bindDetailStargazersCount(textView: TextView, stargazersCount: String?) {
    var fullText = "<b><u>${textView.context.getString(R.string.preStargazersCount)}</u></b>"
    if (stargazersCount==null) textView.visibility = View.GONE
    else {
        fullText += " : $stargazersCount"
        textView.text = Html.fromHtml(fullText, Html.FROM_HTML_MODE_LEGACY)
        textView.visibility = View.VISIBLE
    }
}

@BindingAdapter("loadingSpinner")
fun bindLoadingSpinner(imgView: ImageView, data: List<Repo>?) {
    if (data.isNullOrEmpty()) {
        imgView.setImageResource(R.drawable.loading_animation)
        imgView.visibility = View.VISIBLE
    }
    else imgView.visibility = View.GONE
}






