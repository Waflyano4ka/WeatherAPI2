package com.example.weatherapi.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.weatherapi.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class AddFragment : Fragment() {
    private lateinit var viewMain: View
    private lateinit var searchView: SearchView
    private lateinit var listOfTheCities: LinearLayout
    private var mainContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMain = inflater.inflate(R.layout.fragment_add, container, false)
        mainContext = context

        return viewMain
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        searchView = viewMain.findViewById<SearchView>(R.id.SearchCity)
        listOfTheCities = viewMain.findViewById<LinearLayout>(R.id.ListOfTheCities)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                CheckCity(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun CheckCity(query: String?){
        var request: Request = Request.Builder()
            .url(GetSource(query, "metric"))
            .build()
        var okHttpClient: OkHttpClient = OkHttpClient()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
            }
            override fun onResponse(call: Call, response: Response) {
                val json = JSONObject(response!!.body!!.string())

                if (json.toString().contains("message")){
                    var exception: String = json.getString("message")

                    activity?.runOnUiThread {
                        Toast.makeText(mainContext, exception, Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    var name: String = json.getString("name")

                    activity?.runOnUiThread {
                        val builder = AlertDialog.Builder(mainContext)
                        builder.setMessage("Do you want to add " + name + " to the list?")
                        builder.setPositiveButton("Yes") { dialog, witch ->
                            Toast.makeText(mainContext, "You have added a city", Toast.LENGTH_SHORT).show()
                            AddCityInList(name)
                        }
                        builder.setNeutralButton("Cancel") { dialog, witch ->
                            Toast.makeText(mainContext, "Cancel", Toast.LENGTH_SHORT).show()
                        }

                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    }
                }
            }
        })
    }

    var cityList: ArrayList<DataCity> = ArrayList<DataCity>()

    private fun AddCityInList(name: String){
        var largestElement: Int

        if (cityList.isEmpty()){
            largestElement = 0
        }
        else{
            largestElement = cityList[0].id

            for (number in cityList){
                if(largestElement < number.id)
                    largestElement = number.id
            }
            largestElement++
        }

        cityList.add(DataCity(largestElement, name))

        var list = LinearLayout(mainContext)
        list.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        list.setPadding(50,0,20,0)
        list.id = largestElement

        var textName = TextView(mainContext)
        textName.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f)
        textName.text = largestElement.toString() + " " + name
        list.addView(textName)

        var deleteButton = Button(mainContext)
        deleteButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f)
        deleteButton.text = "X"
        deleteButton.setOnClickListener {
            var delete: Int = 0
            for (number in cityList){
                if(list.id == number.id)
                    break
                delete++
            }
            cityList.removeAt(delete)
            listOfTheCities.removeView(list)
        }
        list.addView(deleteButton)

        listOfTheCities.addView(list)

        var getCityData: City = MainActivity()
        getCityData.DataCitySave(cityList)
    }

    private fun GetSource(city: String?, units: String): String{
        var source = BuildConfig.FORECAST_BASE_URL + city +
                BuildConfig.UNITS_PARAM + units +
                BuildConfig.APPID_PARAM + BuildConfig.API_KEY
        return source
    }
}

interface City{
    fun DataCitySave(cityList: ArrayList<DataCity>)
}