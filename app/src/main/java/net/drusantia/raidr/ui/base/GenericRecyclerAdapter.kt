package net.drusantia.raidr.ui.base

import android.view.*
import androidx.annotation.LayoutRes
import androidx.databinding.*
import androidx.recyclerview.widget.RecyclerView

@Suppress("MemberVisibilityCanBePrivate")
abstract class GenericRecyclerAdapter<ModelType, DataBindingType>(
    private var internalItems: MutableList<ModelType>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    @get:LayoutRes
    abstract val layoutResId: Int

    abstract fun onBindData(model: ModelType, position: Int, binding: DataBindingType)
    abstract fun onItemClick(model: ModelType, position: Int)
    override fun getItemCount() = internalItems.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutResId, parent, false)
        return GenericItemViewHolder(dataBinding)
    }

    @Suppress("UNCHECKED_CAST", "RemoveRedundantQualifierName")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as GenericRecyclerAdapter<*, *>.GenericItemViewHolder
        val binding = vh.binding as DataBindingType
        onBindData(internalItems[position], position, binding)
        (holder.binding as ViewDataBinding).root.setOnClickListener { onItemClick(internalItems[position], position) }
    }

    fun setItems(newItems: List<ModelType>) {
        internalItems.clear()
        internalItems.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<ModelType>) {
        if (newItems.isEmpty()) return
        val startPosition = internalItems.size
        val itemCount = newItems.size
        internalItems.addAll(newItems)
        notifyItemRangeInserted(startPosition, itemCount)
    }

    fun addItem(item: ModelType) {
        internalItems.add(item)
        notifyItemInserted(internalItems.size)
    }

    fun getItem(position: Int) = internalItems[position]
    fun getItems(): List<ModelType>? = internalItems
    fun removeItem(item: ModelType) {
        val position = internalItems.indexOf(item)
        if (position >= 0) {
            removeItem(position)
        } else throw IllegalStateException("Couldn't find item.")
    }

    fun removeItem(position: Int) {
        internalItems.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clearItems() {
        internalItems.clear()
        notifyDataSetChanged()
    }

    internal inner class GenericItemViewHolder(
        binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @Suppress("UNCHECKED_CAST")
        var binding = binding as DataBindingType
    }
}