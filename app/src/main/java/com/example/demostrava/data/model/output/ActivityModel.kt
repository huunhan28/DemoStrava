package com.example.demostrava.data.model.output

import com.google.gson.annotations.SerializedName

data class ActivityModel(

    @SerializedName("resource_state")
    var resourceState: Int? = null,
    @SerializedName("athlete")
    var athlete: Athlete? = Athlete(),
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("distance")
    var distance: Double? = null
//    @SerializedName("moving_time")
//    var movingTime: Int? = null,
//    @SerializedName("elapsed_time")
//    var elapsedTime: Int? = null,
//    @SerializedName("total_elevation_gain")
//    var totalElevationGain: Int? = null,
//    @SerializedName("type")
//    var type: String? = null,
//    @SerializedName("sport_type")
//    var sportType: String? = null,
//    @SerializedName("workout_type")
//    var workoutType: String? = null,
//    @SerializedName("id")
//    var id: Int? = null,
//    @SerializedName("external_id")
//    var externalId: String? = null,
//    @SerializedName("upload_id")
//    var uploadId: Int? = null,
//    @SerializedName("start_date")
//    var startDate: String? = null,
//    @SerializedName("start_date_local")
//    var startDateLocal: String? = null,
//    @SerializedName("timezone")
//    var timezone: String? = null,
//    @SerializedName("utc_offset")
//    var utcOffset: Int? = null,
//    @SerializedName("start_latlng")
//    var startLatlng: String? = null,
//    @SerializedName("end_latlng")
//    var endLatlng: String? = null,
//    @SerializedName("location_city")
//    var locationCity: String? = null,
//    @SerializedName("location_state")
//    var locationState: String? = null,
//    @SerializedName("location_country")
//    var locationCountry: String? = null,
//    @SerializedName("achievement_count")
//    var achievementCount: Int? = null,
//    @SerializedName("kudos_count") var kudosCount: Int? = null,
//    @SerializedName("comment_count") var commentCount: Int? = null,
//    @SerializedName("athlete_count") var athleteCount: Int? = null,
//    @SerializedName("photo_count") var photoCount: Int? = null,
//    @SerializedName("map") var map: Map? = Map(),
//    @SerializedName("trainer") var trainer: Boolean? = null,
//    @SerializedName("commute") var commute: Boolean? = null,
//    @SerializedName("manual") var manual: Boolean? = null,
//    @SerializedName("private") var private: Boolean? = null,
//    @SerializedName("flagged") var flagged: Boolean? = null,
//    @SerializedName("gear_id") var gearId: String? = null,
//    @SerializedName("from_accepted_tag") var fromAcceptedTag: Boolean? = null,
//    @SerializedName("average_speed") var averageSpeed: Double? = null,
//    @SerializedName("max_speed") var maxSpeed: Int? = null,
//    @SerializedName("average_cadence") var averageCadence: Double? = null,
//    @SerializedName("average_watts") var averageWatts: Double? = null,
//    @SerializedName("weighted_average_watts") var weightedAverageWatts: Int? = null,
//    @SerializedName("kilojoules") var kilojoules: Double? = null,
//    @SerializedName("device_watts") var deviceWatts: Boolean? = null,
//    @SerializedName("has_heartrate") var hasHeartrate: Boolean? = null,
//    @SerializedName("average_heartrate") var averageHeartrate: Double? = null,
//    @SerializedName("max_heartrate") var maxHeartrate: Int? = null,
//    @SerializedName("max_watts") var maxWatts: Int? = null,
//    @SerializedName("pr_count") var prCount: Int? = null,
//    @SerializedName("total_photo_count") var totalPhotoCount: Int? = null,
//    @SerializedName("has_kudoed") var hasKudoed: Boolean? = null,
//    @SerializedName("suffer_score") var sufferScore: Int? = null

)

data class Athlete(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("resource_state") var resourceState: Int? = null

)

data class Map(

    @SerializedName("id") var id: String? = null,
    @SerializedName("summary_polyline") var summaryPolyline: String? = null,
    @SerializedName("resource_state") var resourceState: Int? = null

)