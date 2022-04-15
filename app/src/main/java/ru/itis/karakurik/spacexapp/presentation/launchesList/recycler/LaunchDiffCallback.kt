package ru.itis.karakurik.spacexapp.presentation.launchesList.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.itis.karakurik.spacexapp.domain.entity.Launch

object LaunchDiffCallback : DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem == newItem
    }
}
