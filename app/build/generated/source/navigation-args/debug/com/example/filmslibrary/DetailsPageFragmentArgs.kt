package com.example.filmslibrary

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.filmslibrary.model.repository.FilmObject
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class DetailsPageFragmentArgs(
  public val movie: FilmObject
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
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

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(FilmObject::class.java)) {
      result.set("movie", this.movie as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(FilmObject::class.java)) {
      result.set("movie", this.movie as Serializable)
    } else {
      throw UnsupportedOperationException(FilmObject::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DetailsPageFragmentArgs {
      bundle.setClassLoader(DetailsPageFragmentArgs::class.java.classLoader)
      val __movie : FilmObject?
      if (bundle.containsKey("movie")) {
        if (Parcelable::class.java.isAssignableFrom(FilmObject::class.java) ||
            Serializable::class.java.isAssignableFrom(FilmObject::class.java)) {
          __movie = bundle.get("movie") as FilmObject?
        } else {
          throw UnsupportedOperationException(FilmObject::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__movie == null) {
          throw IllegalArgumentException("Argument \"movie\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"movie\" is missing and does not have an android:defaultValue")
      }
      return DetailsPageFragmentArgs(__movie)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): DetailsPageFragmentArgs {
      val __movie : FilmObject?
      if (savedStateHandle.contains("movie")) {
        if (Parcelable::class.java.isAssignableFrom(FilmObject::class.java) ||
            Serializable::class.java.isAssignableFrom(FilmObject::class.java)) {
          __movie = savedStateHandle.get<FilmObject?>("movie")
        } else {
          throw UnsupportedOperationException(FilmObject::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__movie == null) {
          throw IllegalArgumentException("Argument \"movie\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"movie\" is missing and does not have an android:defaultValue")
      }
      return DetailsPageFragmentArgs(__movie)
    }
  }
}
