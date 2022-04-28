package com.example.filmslibrary

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.filmslibrary.model.repository.FilmObject
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class FavouriteFragmentDirections private constructor() {
  private data class ActionFavouriteFragmentToDetailsPageFragment(
    public val movie: FilmObject
  ) : NavDirections {
    public override val actionId: Int = R.id.action_favourite_fragment_to_detailsPage_fragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(FilmObject::class.java)) {
          result.putParcelable("movie", this.movie as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(FilmObject::class.java)) {
          result.putSerializable("movie", this.movie as Serializable)
        } else {
          throw UnsupportedOperationException(FilmObject::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionFavouriteFragmentToDetailsPageFragment(movie: FilmObject): NavDirections =
        ActionFavouriteFragmentToDetailsPageFragment(movie)
  }
}
