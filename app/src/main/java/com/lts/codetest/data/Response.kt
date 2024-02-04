package com.lts.codetest.data

/**
 * <pre>
 *     author : liutangsheng
 *     time   : 2024/02/04
 * </pre>
 */
data class Response(
    val results : List<Music>,
    val resultCount : Int
)

data class Music(
    val wrapperType: String,
    val kind : String,
    val collectionId : Long,
    val trackId : Long,
    val artistName : String,
    val collectionName : String,
    val trackName : String,
    val collectionCensoredName : String,
    val trackCensoredName : String,
    val collectionArtistId : Long,
    val collectionArtistViewUrl : String,
    val collectionViewUrl : String,
    val trackViewUrl : String,
    val previewUrl : String,
    val artworkUrl30 : String,
    val artworkUrl60 : String,
    val artworkUrl100 : String,
    val collectionPrice : Int,
    val trackPrice : Int,
    val trackRentalPrice : Int,
    val collectionHdPrice : Int,
    val trackHdPrice : Int,
    val trackHdRentalPrice : Int,
    val releaseDate : String,
    val collectionExplicitness : String,
    val trackExplicitness : String,
    val trackCount : Int,
    val trackNumber : Int,
    val trackTimeMillis : Int,
    val country : String,
    val currency : String,
    val primaryGenreName : String,
    val contentAdvisoryRating : String,
    val longDescription : String,
    val hasITunesExtras : Boolean

)
