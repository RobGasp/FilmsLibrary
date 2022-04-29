package com.example.filmslibrary.ui.view

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.filmslibrary.R
import com.example.filmslibrary.model.repository.FilmObject
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class ActualFragmentDirections private constructor() {
  private data class ActionActualFragmentToDetailsPageFragment(
    public val movie: FilmObject
  ) : NavDirections {
    public override val actionId: Int = R.id.action_actual_fragment_to_detailsPage_fragment

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
    public fun actionActualFragmentToDetailsPageFragment(movie: FilmObject): NavDirections =
        ActionActualFragmentToDetailsPageFragment(movie)
  }
}
