package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_retrofit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.vengateshm.kotlincoroutinesudemy.R
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_retrofit.model.Country

class CountryListAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryListItemViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.country_list_item, parent, false)
    )

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryListItemViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    fun updateCountries(countryList: List<Country>) {
        this.countryList.clear()
        this.countryList.addAll(countryList)
        notifyDataSetChanged()
    }

    class CountryListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var ivFlag: ImageView
        private var tvCountryName: TextView
        private var tvCountryCapital: TextView

        init {
            ivFlag = view.findViewById(R.id.ivFlag)
            tvCountryName = view.findViewById(R.id.tvCountryName)
            tvCountryCapital = view.findViewById(R.id.tvCapital)
        }

        fun bind(country: Country) {
            ivFlag.loadImage(country.flag)
            tvCountryName.text = country.countryName
            tvCountryCapital.text = country.capital
        }
    }
}