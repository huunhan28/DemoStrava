package com.example.demostrava.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.demostrava.R
import com.example.demostrava.data.api.ApiActivityHelper
import com.example.demostrava.data.model.input.InputDetailActivity
import com.example.demostrava.data.model.input.InputListAthleteActivity
import com.example.demostrava.ui.base.ViewModelActivityFactory
import com.example.demostrava.ui.main.viewmodel.ActivityViewModel
import com.example.demostrava.ui.main.viewmodel.ViewModelProviders
import com.example.demostrava.utils.Status
import com.google.android.gms.maps.model.LatLng
import com.mapbox.android.gestures.Utils
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PolylineAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PolylineAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPolylineAnnotationManager

class DetailActivityActivity : AppCompatActivity() {

    private var accessToken = ""
    var mapView: MapView? = null
    lateinit var btnBack: View
    lateinit var titleActivity: TextView
    var summaryPolyline = ""
    var name = ""
    private lateinit var viewModel: ActivityViewModel
    private lateinit var polylineAnnotationManager: PolylineAnnotationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activity)
        setControls()
        initListeners()
        initValue()
        setupViewModel()
        setupObserversDetailActivity()

    }

    private fun setControls(){
        mapView = findViewById(R.id.mapView)
        btnBack = findViewById(R.id.btnBack)
        titleActivity = findViewById(R.id.titleActivity)
    }

    private fun initListeners(){
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initValue(){
        intent.extras?.let {
            summaryPolyline = it.getString("summaryPolyline").toString()
            name = it.getString("name").toString()
            accessToken = it.getString("accessToken").toString()
        }

        titleActivity.text = name
    }

    private fun initMapbox(){
        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)
        // Create an instance of the Annotation API and get the polyline manager.
        val annotationApi = mapView?.annotations
        polylineAnnotationManager = annotationApi?.createPolylineAnnotationManager(mapView!!)!!
// Define a list of geographic coordinates to be connected.
//        val points = listOf(
//            Point.fromLngLat(17.94, 59.25),
//            Point.fromLngLat(18.18, 59.37)
//        )
//        val polylineString = "ki{eFvqfiVqAWQIGEEKAYJgBVqDJ{BHa@jAkNJw@Pw@V{APs@^aABQAOEQGKoJ_FuJkFqAo@{A}@sH{DiAs@Q]?WVy@`@oBt@_CB]KYMMkB{AQEI@WT{BlE{@zAQPI@ICsCqA_BcAeCmAaFmCqIoEcLeG}KcG}A}@cDaBiDsByAkAuBqBi@y@_@o@o@kB}BgIoA_EUkAMcACa@BeBBq@LaAJe@b@uA`@_AdBcD`@iAPq@RgALqAB{@EqAyAoOCy@AmCBmANqBLqAZkB\\iCPiBJwCCsASiCq@iD]eA]y@[i@w@mAa@i@k@g@kAw@i@Ya@Q]EWFMLa@~BYpAFNpA`Aj@n@X`@V`AHh@JfB@xAMvAGZGHIDIAWOEQNcC@sACYK[MSOMe@QKKKYOs@UYQISCQ?Q@WNo@r@OHGAGCKOQ_BU}@MQGG]Io@@c@FYNg@d@s@d@ODQAMOMaASs@_@a@SESAQDqBn@a@RO?KK?UBU\\kA@Y?WMo@Iy@GWQ_@WSSGg@AkABQB_Ap@_A^o@b@Q@o@IS@OHi@n@OFS?OI}@iAQMQGQC}@DOIIUK{@IUOMyBo@kASOKIQCa@L[|AgATWN[He@?QKw@FOPCh@Fx@l@TDLELKl@aAHIJEX@r@ZTDV@LENQVg@RkA@c@MeA?WFOPMf@Ej@Fj@@LGHKDM?_@_@iC?a@HKRIl@NT?FCHMFW?YEYGWQa@GYBiAIq@Gq@L_BHSHK|@WJETSLQZs@z@_A~@uA^U`@G\\CRB\\Tl@p@Th@JZ^bB`@lAHLXVLDP?LGFSKiDBo@d@wBVi@R]VYVE\\@`@Lh@Fh@CzAk@RSDQA]GYe@eAGWSiBAWBWBIJORK`@KPOPSTg@h@}Ad@o@F[E_@EGMKUGmAEYGMIMYKs@?a@J}@@_BD_@HQJMx@e@LKHKHWAo@UoAAWFmAH}@?w@C[YwAAc@HSNM|Ao@rA}@zAq@`@a@j@eAxAuBXQj@MXSR[b@gAFg@?YISOGaAHi@Xw@v@_@d@WRSFqARUHQJc@d@m@`A[VSFUBcAEU@WFULUPa@v@Y~@UrBc@dBI~@?l@P~ABt@N`HEjA]zAEp@@p@TrBCl@CTQb@k@dAg@jAU^KJYLK@k@A[Js@d@a@b@]RgBl@[FMAw@[]G]?m@D_@F]P[Vu@t@[TMF_@Do@E_@@q@P]PWZUZw@vAkAlAGJOj@IlAMd@OR{@p@a@d@sBpD]v@a@`Aa@n@]TODgBVk@Pe@^cBfBc@Rs@La@RSPm@|@wCpDS^Wp@QZML{@l@qBbCYd@k@lAIVCZBZNTr@`@RRHZANIZQPKDW@e@CaASU?I@YTKRQx@@\\VmALYRQLCL?v@P|@D\\GJEFKDM@OCa@COOYIGm@YMUCM@]JYr@uAx@kAt@}@jAeAPWbAkBj@s@bAiAz@oAj@m@VQlAc@VQ~@aA`Au@p@Q`AIv@MZORUV_@p@iB|AoCh@q@dAaANUNWH[N{AJ[^m@t@_Av@wA\\a@`@W`@In@Al@B^E`@Wl@u@\\[VQ\\K`@Eb@?R@dAZP@d@CRExAs@\\Yt@{@LG\\MjAATINOXo@d@kAl@_AHYBOCe@QiBCm@Fq@\\wADo@AyGEeBWuB@YHu@Tu@Lk@VcCTo@d@aA\\WJE`@G~@FP?VI\\U~@sANO`@SfAMj@U\\WjAsAXS`@UNENALBHFFL?^Ml@Uj@]b@q@RUJSPkChEc@XcAb@sA|@]PaA\\OJKNER?TDTNj@Jn@?p@OfC@ZR`B@VCV_@n@{@l@WbACv@OlABnAPl@LNNHbBBNBLFFJ@^GLg@x@i@|AMP[X}@XOJKPET?l@LhAFXp@fBDRCd@S\\_@Ps@PQ@}A]S?QDe@V]b@MR[fAKt@ErAF~CANILYDKGIKe@{@Yy@e@sB[gA[c@e@YUCU?WBUHUNQPq@`AiArAMV[^e@Zc@JQJKNMz@?r@Bb@PfAAfA@VVbADn@E`@KHSEe@SMAKDKFM\\^dDCh@m@LoAQ_@@MFOZLfBEl@QbASd@KLQBOAaAc@QAQ@QHc@v@ONMJOBOCg@c@]O[EMBKFGL?RHv@ARERGNe@h@{@h@WVGNDt@JLNFPFz@LdBf@f@PJNHPF`ADPJJJDl@I`@B^Tp@bALJNDNALIf@i@PGPCt@DNE`@Uv@[dAw@RITGRCtAARBPJLPJRZxB?VEX_@vAAR?RDNHJJBh@UnBm@h@IRDRJNNJPNbBFRJLLBLCzAmAd@Uf@Gf@?P@PFJNHPFTH`BDTHNJJJ@LG`@m@^YPER@RDPHNNJRLn@HRLN^VNPHTFX@\\UlDFb@FHh@NP@HKPsB?}ASkCQ{@[y@q@}@cA{@KOCQDa@t@{CFGJCf@Nl@ZtA~@r@p@`@h@rAxBd@rA\\fARdAPjANrB?f@AtBCd@QfBkAjJOlBChA?rBFrBNlBdAfKFzAC~@Iz@Mz@Sv@s@jBmAxBi@hAWt@Sv@Qx@O`BA`@?dAPfBVpAd@`BfBlFf@fBdA~Cr@pAz@fApBhBjAt@H?IL?FBFJLx@^lHvDvh@~XnElCbAd@pGhDbAb@nAr@`Ad@`GhDnBbAxCbBrWhNJJDPARGP_@t@Qh@]pAUtAoA`Ny@jJApBBNFLJFJBv@Hb@HBF?\\"
        val points = decodePolyline(summaryPolyline)
// Set options for the resulting line layer.
        val polylineAnnotationOptions: PolylineAnnotationOptions = PolylineAnnotationOptions()
            .withPoints(points)
            // Style the line that will be added to the map.
            .withLineColor("#ee4e8b")
            .withLineWidth(5.0)
// Add the resulting line to the map.
        polylineAnnotationManager?.create(polylineAnnotationOptions)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelActivityFactory(ApiActivityHelper(accessToken))
        ).get(ActivityViewModel::class.java)
    }
    private fun setupObserversDetailActivity(){
        var input = InputDetailActivity()
//        input.before = 0
//        input.after = 0
        input.id = 1
        viewModel.getDetailActivity(input).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { activityModel ->
                            initMapbox()
                            titleActivity.text = activityModel.name
                        }
                    }
                    Status.ERROR -> {

                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        })
    }

    private fun decodePolyline(polyline: String): List<Point> {
        val decodedPoints = mutableListOf<Point>()
        var index = 0
        var lat = 0
        var lng = 0

        while (index < polyline.length) {
            var shift = 0
            var result = 0
            var byte: Int

            do {
                byte = polyline[index++].toInt() - 63
                result = result or ((byte and 0x1F) shl shift)
                shift += 5
            } while (byte >= 0x20)

            val dlat = if (result and 1 != 0) -(result shr 1) else (result shr 1)
            lat += dlat

            shift = 0
            result = 0

            do {
                byte = polyline[index++].toInt() - 63
                result = result or ((byte and 0x1F) shl shift)
                shift += 5
            } while (byte >= 0x20)

            val dlng = if (result and 1 != 0) -(result shr 1) else (result shr 1)
            lng += dlng

            val point = Point.fromLngLat((lat.toDouble() / 1E5), (lng.toDouble() / 1E5))
            decodedPoints.add(point)
        }

        return decodedPoints
    }
}