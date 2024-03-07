package com.learn.dicodingprojectakhirandroidpemula

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.dicodingprojectakhirandroidpemula.databinding.ItemRowProgrammingLanguagesBinding

class ListProgrammingLanguagesAdapter(private val listProgramingLanguages: ArrayList<ProgrammingLanguages>) :
    RecyclerView.Adapter<ListProgrammingLanguagesAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowProgrammingLanguagesBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listProgramingLanguages[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listProgramingLanguages[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listProgramingLanguages.size

    class ListViewHolder(var binding: ItemRowProgrammingLanguagesBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: ProgrammingLanguages)
    }
}