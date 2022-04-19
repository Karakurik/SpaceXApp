package ru.itis.karakurik.spacexapp.presentation.launchesList.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.itis.karakurik.spacexapp.R
import ru.itis.karakurik.spacexapp.databinding.ItemLaunchBinding
import ru.itis.karakurik.spacexapp.domain.entity.Launch

class LaunchListViewHolder(
    private val binding: ItemLaunchBinding,
    private val onItemClick: (launchId: String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(launch: Launch) {
        with(binding) {
            tvLaunchName.text = launch.name

            ivLaunchItem.load(launch.smallImageUrl) {
                crossfade(true)
                placeholder(R.drawable.launch_icon_small)
            }

            root.setOnClickListener {
                onItemClick(launch.id)
            }
        }
    }



    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: (launchId: String) -> Unit,
        ) = LaunchListViewHolder(
            ItemLaunchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }
}
