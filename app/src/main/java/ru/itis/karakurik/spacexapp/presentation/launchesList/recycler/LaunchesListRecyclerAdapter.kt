package ru.itis.karakurik.spacexapp.presentation.launchesList.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.itis.karakurik.spacexapp.domain.entity.Launch

class LaunchesListRecyclerAdapter(
    private val onItemClick: (launchId: String) -> Unit,
) : ListAdapter<Launch, LaunchListViewHolder>(LaunchDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = LaunchListViewHolder.create(
        parent,
        onItemClick
    )

    override fun onBindViewHolder(holder: LaunchListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
